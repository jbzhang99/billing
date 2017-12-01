package com.ai.baas.pkgfee.dst;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.pkgfee.api.feecal.params.FeeCalAddParam;
import com.ai.baas.pkgfee.api.feecal.params.ProductItemVO;
import com.ai.baas.pkgfee.constants.CpPkgfeeConstants;
import com.ai.baas.pkgfee.dao.mapper.bo.DstDiscountExt;
import com.ai.baas.pkgfee.dao.mapper.bo.DstDiscountInfo;
import com.ai.baas.pkgfee.service.atom.interfaces.IDiscountExtAtomSV;
import com.ai.baas.pkgfee.service.atom.interfaces.IDiscountInfoAtomSV;
import com.ai.baas.pkgfee.service.entity.DstActivityGroup;
import com.ai.baas.pkgfee.service.entity.DstResult;
import com.ai.baas.pkgfee.service.entity.PkgFeeRecord;
import com.ai.baas.pkgfee.util.DateUtils;
import com.ai.baas.pkgfee.util.NumberUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 普通商品优惠活动
 * @author majun
 *
 */
public class DiscountActivity implements IActivity {
	private static Logger logger = LoggerFactory.getLogger(DiscountActivity.class);
	
	private IDiscountInfoAtomSV discountInfoAtomSV;
	private IDiscountExtAtomSV discountExtAtomSV;
	private List<ProductItemVO> productItems;
	private String tenantId;
	private String deductMode;
	private String dstSubjectCode = CpPkgfeeConstants.DONATION_SUBJECT;
	
	public DiscountActivity(IDiscountInfoAtomSV discountInfoAtomSV,
			IDiscountExtAtomSV discountExtAtomSV) {
		this.discountInfoAtomSV = discountInfoAtomSV;
		this.discountExtAtomSV = discountExtAtomSV;
	}
	
	public void setFeeCalParamData(FeeCalAddParam feeCalAddParam){
		//this.feeCalAddParam = feeCalAddParam;
		this.tenantId = feeCalAddParam.getTenantId();
		this.deductMode = feeCalAddParam.getDeductMode();
		this.productItems = feeCalAddParam.getProductList();
	}

	@Override
	public List<PkgFeeRecord> calculateAmount() {
		List<PkgFeeRecord> feeRecordList = Lists.newArrayList();
		ListMultimap<String,DstActivityGroup> dstMultimap = ArrayListMultimap.create();
		String discountId = "";
		for (ProductItemVO productItemVO : productItems) {
			discountId = productItemVO.getDiscountId();
			//没有优惠活动
			if (StringUtils.isBlank(discountId)) {
				addFeeRecord(productItemVO, feeRecordList);
			}else{
				DstDiscountInfo dstInfo = discountInfoAtomSV.queryByDiscountId(discountId);
				String calcType = dstInfo.getCalcType();
				//满减和打折处理
				if (calcType.equalsIgnoreCase(CpPkgfeeConstants.DST_CALC_TYPE_MJ)
						|| calcType.equalsIgnoreCase(CpPkgfeeConstants.DST_CALC_TYPE_DZ)) {
					DstActivityGroup dstActivityGroup = new DstActivityGroup();
					dstActivityGroup.setCalcType(calcType);
					dstActivityGroup.setProductItem(productItemVO);
					dstMultimap.put(discountId, dstActivityGroup);
				}else{//满赠不参与活动处理,在订购阶段处理
					addFeeRecord(productItemVO, feeRecordList);
				}
			}
		}
		if (dstMultimap.size() != 0) {
			////////////////////////
			assembleDstActiveRecord(dstMultimap, feeRecordList);
		}

		return feeRecordList;
	}
	
	private void addFeeRecord(ProductItemVO productItem, List<PkgFeeRecord> feeRecordList) {
		PkgFeeRecord feeRecord = new PkgFeeRecord();
		feeRecord.setTenantId(tenantId);
		feeRecord.setDeductMode(deductMode);
		feeRecord.setProductItem(productItem);
		
		feeRecordList.add(feeRecord);
	}
	
	private void addFeeRecordWithDst(ProductItemVO productItem, List<PkgFeeRecord> feeRecordList, BigDecimal dstAmount){
		PkgFeeRecord feeRecord = new PkgFeeRecord();
		feeRecord.setTenantId(tenantId);
		feeRecord.setDeductMode(deductMode);
		feeRecord.setProductItem(productItem);
		
		DstResult dstResult = new DstResult();
		dstResult.setDstAmount(dstAmount);
		dstResult.setDstSubjectCode(dstSubjectCode);
		feeRecord.setDstResult(dstResult);
		
		feeRecordList.add(feeRecord);
	}
	
	/**
	 * 拼装优惠活动对象(满减和打折)
	 * @param dstMultimap
	 * @param param
	 * @param feeRecordList
	 */
	private void assembleDstActiveRecord(ListMultimap<String,DstActivityGroup> dstMultimap,
			List<PkgFeeRecord> feeRecordList) {
		/**
		 * 活动ID 价格  时长 
		 * 10001  100 12    ---活动满1500减100
		 * 10001  100  6    ---活动满1500减100
		 * 10002  120 12
		 * 
		 */
		List<DstActivityGroup> dstActGroupList = null;
		List<DstDiscountExt> dstExtList = null;
		Map<String,String> dstExtMap = null;
		//DstActivityGroup dstActivityGroup = null;
		for (String dstKey : dstMultimap.keySet()) {
			dstActGroupList = dstMultimap.get(dstKey);
			dstExtList = discountExtAtomSV.queryByDiscountId(dstKey, tenantId);
			dstExtMap = convertCollection(dstExtList);
			if (dstActGroupList.size() == 1) {
				singleDstHandler(dstActGroupList.get(0), dstExtMap, feeRecordList);
			} else {
				//multipleDstHandler(dstActGroupList);
				//String calcType = dstActivityGroup.getCalcType();
				if (checkIfCombineCal(dstActGroupList.get(0),dstExtMap)) {
					multipleDstHandler(dstActGroupList, dstExtMap,feeRecordList);
				}else{
					for (DstActivityGroup dstActivityGroup : dstActGroupList) {
						singleDstHandler(dstActivityGroup, dstExtMap, feeRecordList);
					}
				}
			}
			
		}
	}
	
	/**
	 * 判断优惠产品是否需要合并计算满减
	 * @param dstExtMap
	 * @return
	 */
	private boolean checkIfCombineCal(DstActivityGroup dstActivityGroup,Map<String,String> dstExtMap){
		String calcType = dstActivityGroup.getCalcType();
		if (calcType.equalsIgnoreCase(CpPkgfeeConstants.DST_CALC_TYPE_DZ)) {
			return false;
		}
		String dstRullType = dstExtMap.get(CpPkgfeeConstants.DST_RULL_TYPE);
		String discountUnit = dstExtMap.get(CpPkgfeeConstants.DST_DISCOUNT_UNIT_ID);
		String fullCostUnit = dstExtMap.get(CpPkgfeeConstants.DST_FULL_COST_UNIT_ID); 
		if (dstRullType.equalsIgnoreCase(CpPkgfeeConstants.DST_RULL_TYPE_REDUCE)
				&& fullCostUnit.equalsIgnoreCase(CpPkgfeeConstants.DST_UNIT_YUAN)
				&& discountUnit.equalsIgnoreCase(CpPkgfeeConstants.DST_UNIT_YUAN)) {
			return true;
		}else{
			return false;
		}
	}
	
	private void multipleDstHandler(List<DstActivityGroup> dstActGroupList, 
			Map<String,String> dstExtMap, List<PkgFeeRecord> feeRecordList) {
		//只对满减单位为元做组合处理
		BigDecimal total = new BigDecimal("0.00");
		ProductItemVO productItem = null;
		BigDecimal productPrice = null;
		BigDecimal productAmount = null;
		for(DstActivityGroup dstActivityGroup:dstActGroupList){
			productItem = dstActivityGroup.getProductItem();
			productPrice = new BigDecimal(productItem.getProductPrice());
			String purchaseNum = productItem.getPurchaseNum();
			productAmount = productPrice.multiply(new BigDecimal(purchaseNum)).setScale(2, BigDecimal.ROUND_HALF_UP);
			total = total.add(productAmount);
		}
		String strFullCostAmount = dstExtMap.get(CpPkgfeeConstants.DST_FULL_COST_AMOUNT);
		BigDecimal fullCost = new BigDecimal(strFullCostAmount);
		if (total.compareTo(fullCost) == 1 || total.compareTo(fullCost) == 0) {
			String discountAmount = dstExtMap.get(CpPkgfeeConstants.DST_DISCOUNT_AMOUNT);
			List<BigDecimal> apportionAmountList = NumberUtil.getApportionByRatio(new BigDecimal(discountAmount), dstActGroupList.size());
			for(int i=0;i<dstActGroupList.size();i++){
				addFeeRecordWithDst(dstActGroupList.get(i).getProductItem(),feeRecordList,apportionAmountList.get(i));
			}
		}else{
			for(DstActivityGroup dstActivityGroup:dstActGroupList){
				addFeeRecordWithDst(dstActivityGroup.getProductItem(),feeRecordList, BigDecimal.ZERO);
			}
		}
		
	}
	
	/**
	 * 单个优惠活动处理
	 * @param dstActivityGroup
	 * @param feeRecordList
	 */
	private void singleDstHandler(DstActivityGroup dstActivityGroup, Map<String,String> dstExtMap,
			List<PkgFeeRecord> feeRecordList) {
		ProductItemVO productItem = dstActivityGroup.getProductItem();
		//String discountId = productItem.getDiscountId();
		String calcType = dstActivityGroup.getCalcType();
		
		BigDecimal dstAmount = null;
		//List<DstDiscountExt> dstExtList = discountExtAtomSV.queryByDiscountId(discountId, tenantId);
		//Map<String,String> dstExtMap = convertCollection(dstExtList);
		if (calcType.equalsIgnoreCase(CpPkgfeeConstants.DST_CALC_TYPE_MJ)) {
			String dstRullType = dstExtMap.get(CpPkgfeeConstants.DST_RULL_TYPE);
			//满1000减100或满12月减2月
			if (dstRullType.equalsIgnoreCase(CpPkgfeeConstants.DST_RULL_TYPE_REDUCE)) {
				dstAmount = fullCost(dstExtMap, productItem);
			}else{
				//满1000,9折
				//满折暂时不提供
			}
		}else if(calcType.equalsIgnoreCase(CpPkgfeeConstants.DST_CALC_TYPE_DZ)){
			dstAmount = discount(dstExtMap, productItem);
		}
		addFeeRecordWithDst(productItem, feeRecordList, dstAmount);
	}
	
	
	private Map<String,String> convertCollection(List<DstDiscountExt> discountExtList){
		Map<String,String> dstExtMap = Maps.newHashMap();
		for(DstDiscountExt discountExt:discountExtList){
			dstExtMap.put(discountExt.getExtName(), discountExt.getExtValue());
		}
		return dstExtMap;
	}
	
	/**
	 * 满减处理
	 * @param dstExtMap
	 */
	private BigDecimal fullCost(Map<String,String> dstExtMap,ProductItemVO productItem){
		BigDecimal dstAmount = null;
		String discountUnit = dstExtMap.get(CpPkgfeeConstants.DST_DISCOUNT_UNIT_ID);
		String fullCostUnit = dstExtMap.get(CpPkgfeeConstants.DST_FULL_COST_UNIT_ID);
		BigDecimal productPrice = new BigDecimal(productItem.getProductPrice());
		String strFullCostAmount = dstExtMap.get(CpPkgfeeConstants.DST_FULL_COST_AMOUNT);
		BigDecimal fullCost = new BigDecimal(strFullCostAmount);
		String purchaseNum = productItem.getPurchaseNum();
		//满减单位必须都是元或都是月,其他不予处理
		if(discountUnit.equalsIgnoreCase(CpPkgfeeConstants.DST_UNIT_YUAN) && 
				fullCostUnit.equalsIgnoreCase(CpPkgfeeConstants.DST_UNIT_YUAN)){
			BigDecimal total = productPrice.multiply(new BigDecimal(purchaseNum)).setScale(2, BigDecimal.ROUND_HALF_UP);
			if (total.compareTo(fullCost) == 1 || total.compareTo(fullCost) == 0) {
				String discountAmount = dstExtMap.get(CpPkgfeeConstants.DST_DISCOUNT_AMOUNT);
				dstAmount = new BigDecimal(discountAmount);
			}
		}else if(discountUnit.equalsIgnoreCase(CpPkgfeeConstants.DST_UNIT_MONTH) && 
				fullCostUnit.equalsIgnoreCase(CpPkgfeeConstants.DST_UNIT_MONTH)){
			int purchaseMonthVal = DateUtils.convertMonth(purchaseNum, productItem.getPurchaseUnit());
			BigDecimal purchaseMonth = new BigDecimal(purchaseMonthVal);
			if (purchaseMonth.compareTo(fullCost) == 1 || purchaseMonth.compareTo(fullCost) == 0) {
				String discountAmountStr = dstExtMap.get(CpPkgfeeConstants.DST_DISCOUNT_AMOUNT);
				BigDecimal discountAmount = new BigDecimal(discountAmountStr); 
				dstAmount = discountAmount.multiply(productPrice).setScale(2, BigDecimal.ROUND_HALF_UP);
			}
		}else{
			///其他情况暂时不处理
		}
		return dstAmount;
	}
	
	/**
	 * 打折处理
	 * @param dstExtMap
	 * @param productItem
	 * @return
	 */
	private BigDecimal discount(Map<String,String> dstExtMap,ProductItemVO productItem){
		BigDecimal productPrice = new BigDecimal(productItem.getProductPrice());
		String purchaseNum = productItem.getPurchaseNum();
		BigDecimal total = productPrice.multiply(new BigDecimal(purchaseNum)).setScale(2, BigDecimal.ROUND_HALF_UP);
		String disPercentVal = dstExtMap.get(CpPkgfeeConstants.DST_DISCOUNT_PERCENT);
		BigDecimal dstPercent = new BigDecimal("10");
		BigDecimal dstAmount = dstPercent.subtract(new BigDecimal(disPercentVal));
		dstAmount = dstAmount.divide(dstPercent, 3, BigDecimal.ROUND_HALF_UP);
		dstAmount = dstAmount.multiply(total).setScale(2, BigDecimal.ROUND_HALF_UP);
		return dstAmount;
	}
	
	
	/**
	 * 满折处理
	 * @param dstExtMap
	 * @param productItem
	 * @return
	 */
	private BigDecimal fullCostDiscount(Map<String,String> dstExtMap,ProductItemVO productItem){
		BigDecimal productPrice = new BigDecimal(productItem.getProductPrice());
		String purchaseNum = productItem.getPurchaseNum();
		BigDecimal total = productPrice.multiply(new BigDecimal(purchaseNum)).setScale(2, BigDecimal.ROUND_HALF_UP);
		
		String strFullCostAmount = dstExtMap.get(CpPkgfeeConstants.DST_FULL_COST_AMOUNT);
		BigDecimal fullCost = new BigDecimal(strFullCostAmount);
		BigDecimal dstAmount = null;
		if (total.compareTo(fullCost) == 1 || total.compareTo(fullCost) == 0) {
			String disPercentVal = dstExtMap.get(CpPkgfeeConstants.DST_DISCOUNT_AMOUNT);
			dstAmount = new BigDecimal("10");
			dstAmount = dstAmount.subtract(new BigDecimal(disPercentVal));
			dstAmount = dstAmount.multiply(total).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		return dstAmount;
	}
	
	
//	private BigDecimal queryActivityMessage(String discountId,String calcType){
//	BigDecimal dstAmount = null;
//	List<DiscountExt> dstExtList = discountExtAtomSV.queryByDiscountId(discountId, tenantId);
//	Map<String,String> dstExtMap = convertCollection(dstExtList);
//	if (calcType.equalsIgnoreCase(CpPkgfeeConstants.DST_CALC_TYPE_MJ)) {
//		String dstRullType = dstExtMap.get(CpPkgfeeConstants.DST_RULL_TYPE);
//		//满1000减100或满12月减2月
//		if(dstRullType.equalsIgnoreCase(CpPkgfeeConstants.DST_RULL_TYPE_REDUCE)){
//			fullCost(dstExtMap);
//			
//			
//		}else{//满1000,9折
//			
//		}
//		
//	}else if(calcType.equalsIgnoreCase(CpPkgfeeConstants.DST_CALC_TYPE_DZ)){
//		
//		
//	}
//	
//	return null;
//}
	

}
