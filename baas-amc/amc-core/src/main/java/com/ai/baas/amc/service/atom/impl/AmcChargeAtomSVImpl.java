package com.ai.baas.amc.service.atom.impl;

import com.ai.baas.amc.api.bill.params.BillSearchRequest;
import com.ai.baas.amc.dao.mapper.bo.AmcCharge;
import com.ai.baas.amc.dao.mapper.bo.AmcChargeCriteria;
import com.ai.baas.amc.dao.mapper.bo.AmcChargeCriteria.Criteria;
import com.ai.baas.amc.dao.mapper.factory.MapperFactory;
import com.ai.baas.amc.service.atom.interfaces.IAmcChargeAtomSV;
import com.ai.baas.amc.util.DateUtils;
import com.ai.opt.base.exception.SystemException;

import com.ai.opt.sdk.util.CollectionUtil;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 账单明细操作
 * Created by jackieliu on 16/3/31.
 */
@Component
public class AmcChargeAtomSVImpl implements IAmcChargeAtomSV {
	
	private final String CHARGE_START_TABLE = "201602";
	public static final Logger LOGGER = LoggerFactory.getLogger(AmcChargeAtomSVImpl.class);
    @Override
    public List<AmcCharge> query(String accId, String tenantId, String unsettledMonths) {
        AmcChargeCriteria example = new AmcChargeCriteria();
        example.setTableMonth(unsettledMonths);
        example.createCriteria()
                .andAcctIdEqualTo(accId)
                .andTenantIdEqualTo(tenantId);
        return MapperFactory.getAmcChargeMapper().selectByExample(example);
    }

    @Override
    public List<AmcCharge> queryAmcChargeList(String tenantId, String custId, String accDate)
            throws SystemException {
        AmcChargeCriteria sql = new AmcChargeCriteria();
        sql.setTableMonth(accDate);
        Criteria criteria = sql.createCriteria();
        criteria.andTenantIdEqualTo(tenantId);
        criteria.andCustIdEqualTo(Long.parseLong(custId));
        return MapperFactory.getAmcChargeMapper().selectByExample(sql);
    }

    /**
     * 分页查询账单列表
     * @param vo
     * @return
     */
    @Override
    public List<AmcCharge> searchBillPages(BillSearchRequest vo) {
        AmcChargeCriteria example = new AmcChargeCriteria();
        example.setLimitStart((vo.getPageNo()-1)*vo.getPageSize());
        example.setLimitEnd(vo.getPageSize());
        example.setOrderByClause("AMC_BILL_MONTH");
        AmcChargeCriteria.Criteria or = example.or();
        or.andTenantIdEqualTo(vo.getTenantId());
        
        //if(StringUtils.equals("1", vo.getUserType())){
        	  if("1".equals(vo.getPayState())){
                  or.andTotalAmountGreaterThan(0l);
                  or.andBalanceEqualTo(0l);
              }else if("0".equals(vo.getPayState())){
                  or.andTotalAmountGreaterThan(0l);
                  or.andBalanceGreaterThan(0l);
              }
              if(vo.getBillFeeMin()!=null){
                  or.andTotalAmountGreaterThanOrEqualTo(vo.getBillFeeMin().longValue());
              }
              if(vo.getBillFeeMax()!=null){
                  or.andTotalAmountLessThanOrEqualTo(vo.getBillFeeMax().longValue());
              }
              if(!CollectionUtil.isEmpty(vo.getCustIdList())){
                  or.andCustIdIn(vo.getCustIdList());
              }
              if(!CollectionUtil.isEmpty(vo.getAcctIdList())){
                  or.andAcctIdIn(vo.getAcctIdList());
              }
              List<String> billMonths = new ArrayList<>();
              billMonths.add(vo.getBillMonth());
              example.setBillMonths(billMonths);
        /*}else{
        	List<String> billMonths = new ArrayList<>();
        	for(int i=0; i<6; i++){
        		String month = DateUtils.getDateStrByMouth(i, "YYYYMM");
        		if(DateUtils.monthDiffs(CHARGE_START_TABLE, month)>=0){
        			billMonths.add(month);
				}
			}
        	example.setBillMonths(billMonths);
        	if(!CollectionUtil.isEmpty(vo.getAcctIdList())){
                or.andAcctIdIn(vo.getAcctIdList());
            }
        }*/
        LOGGER.info("账单查询后场入參"+JSONArray.fromObject(example).toString());
        return MapperFactory.getAmcChargeMapper().selectByExampleAndMonthsForBill(example);
    }

    /**
     * 查询符合调价的账单总条数
     * @param vo
     * @return
     */
    @Override
    public Integer countBills(BillSearchRequest vo) {
        AmcChargeCriteria example = new AmcChargeCriteria();
        example.setTableMonth(vo.getBillMonth());
        AmcChargeCriteria.Criteria or = example.or();
        or.andTenantIdEqualTo(vo.getTenantId());
        if("1".equals(vo.getPayState())){
            or.andTotalAmountGreaterThan(0l);
            or.andBalanceEqualTo(0l);
        }else if("0".equals(vo.getPayState())){
            or.andTotalAmountGreaterThan(0l);
            or.andBalanceGreaterThan(0l);
        }
        if(vo.getBillFeeMin()!=null){
            or.andTotalAmountGreaterThanOrEqualTo(vo.getBillFeeMin().longValue());
        }
        if(vo.getBillFeeMax()!=null){
            or.andTotalAmountLessThanOrEqualTo(vo.getBillFeeMax().longValue());
        }
        if(!CollectionUtil.isEmpty(vo.getCustIdList())){
            or.andCustIdIn(vo.getCustIdList());
        }
        return MapperFactory.getAmcChargeMapper().countByExampleForBill(example);
    }
    
    /**
     * 查询符合调价的账单总条数
     * @param vo
     * @return
     */
    @Override
    public Integer countBillsByMonths(BillSearchRequest vo) {
        AmcChargeCriteria example = new AmcChargeCriteria();
        example.setTableMonth(vo.getBillMonth());
        AmcChargeCriteria.Criteria or = example.or();
        or.andTenantIdEqualTo(vo.getTenantId());
        //if(StringUtils.equals("1", vo.getUserType())){
      	  if("1".equals(vo.getPayState())){
                or.andTotalAmountGreaterThan(0l);
                or.andBalanceEqualTo(0l);
            }else if("0".equals(vo.getPayState())){
                or.andTotalAmountGreaterThan(0l);
                or.andBalanceGreaterThan(0l);
            }
            if(vo.getBillFeeMin()!=null){
                or.andTotalAmountGreaterThanOrEqualTo(vo.getBillFeeMin().longValue());
            }
            if(vo.getBillFeeMax()!=null){
                or.andTotalAmountLessThanOrEqualTo(vo.getBillFeeMax().longValue());
            }
            if(!CollectionUtil.isEmpty(vo.getCustIdList())){
                or.andCustIdIn(vo.getCustIdList());
            }
            if(!CollectionUtil.isEmpty(vo.getAcctIdList())){
                or.andAcctIdIn(vo.getAcctIdList());
            }
            List<String> billMonths = new ArrayList<>();
            billMonths.add(vo.getBillMonth());
            example.setBillMonths(billMonths);
	      /*}else{
	      	List<String> billMonths = new ArrayList<>();
	      	for(int i=1; i<6; i++){
	      		String month = DateUtils.getDateStrByMouth(i, "YYYYMM");
	      		if(DateUtils.monthDiffs(CHARGE_START_TABLE, month)>=0){
	      			billMonths.add(month);
                }
            }
            if(!CollectionUtil.isEmpty(vo.getAcctIdList())){
                or.andAcctIdIn(vo.getAcctIdList());
            }
	      	example.setBillMonths(billMonths);
	      }*/
        return MapperFactory.getAmcChargeMapper().countByExampleAndMonthsForBill(example);
    }
}
