package com.ai.baas.amc.service.business.impl;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.amc.constants.AmcConstants;
import com.ai.baas.amc.dao.mapper.bo.AmcCcChargeYyyydd;
import com.ai.baas.amc.dao.mapper.bo.AmcCcDetailYyyydd;
import com.ai.baas.amc.dao.mapper.bo.AmcChargeYyyydd;
import com.ai.baas.amc.dao.mapper.bo.AmcInvoiceYyyydd;
import com.ai.baas.amc.dao.mapper.bo.AmcOweInfo;
import com.ai.baas.amc.dao.mapper.bo.AmcOweInfoKey;
import com.ai.baas.amc.mds.AccountPreferentialProcessorImpl;
import com.ai.baas.amc.service.atom.interfaces.IAmcCcChargeYyyyddAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcCcDetailYyyyddAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcChargeYyyyddAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcInvoiceYyyyddAtomSV;
import com.ai.baas.amc.service.atom.interfaces.IAmcOweInfoAtomSV;
import com.ai.baas.amc.service.business.interfaces.IAccountPreferentialBusiSV;
import com.ai.baas.amc.vo.AccountPreferentialVo;
import com.ai.baas.amc.vo.CostCenterVo;
import com.ai.baas.dshm.client.CacheFactoryUtil;
import com.ai.baas.dshm.client.impl.CacheBLMapper;
import com.ai.baas.dshm.client.impl.DshmClient;
import com.ai.baas.dshm.client.interfaces.IDshmClient;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.components.mds.MDSClientFactory;
import com.ai.opt.sdk.components.sequence.util.SeqUtil;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.ai.paas.ipaas.mds.IMessageSender;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
@Service
@Transactional
public class AccountPreferentialBusiSVImpl implements IAccountPreferentialBusiSV {

    private static final Logger LOG = LogManager.getLogger(AccountPreferentialProcessorImpl.class);
    @Autowired
    IAmcChargeYyyyddAtomSV amcChargeAtomSV;
    @Autowired
    IAmcInvoiceYyyyddAtomSV amcInvoiceAtomSV;
    @Autowired
    IAmcOweInfoAtomSV amcOweInfoAtomSV;
    @Autowired
    IAmcCcChargeYyyyddAtomSV amcCcChargeYyyyddAtomSV;
    @Autowired
    IAmcCcDetailYyyyddAtomSV amcCcDetailYyyyddAtomSV;
    
    private ICacheClient cacheClient = null;

    private IDshmClient client = null;

    @Override
    public void accountPreferentialApportion(JSONObject jsonRoot) {
    	String tenantId = jsonRoot.getString(AmcConstants.APMessage.TENANT_ID);
        String subsId = jsonRoot.getString(AmcConstants.APMessage.SUBS_ID);
        String serviceIdRoot = jsonRoot.getString(AmcConstants.APMessage.SERVICE_ID);
        String accountMonth = jsonRoot.getString(AmcConstants.APMessage.ACCOUNT_MONTH);
        if(!StringUtil.isBlank(accountMonth)&&accountMonth.length()>6){
            accountMonth = accountMonth.substring(0,6);
        }
        String custIdRoot = jsonRoot.getString(AmcConstants.APMessage.CUST_ID);
        String acctId = jsonRoot.getString(AmcConstants.APMessage.ACCT_ID);
        String drKey = jsonRoot.getString(AmcConstants.APMessage.DR_KEY);
        String custId = jsonRoot.getString(AmcConstants.APMessage.CUST_ID);
    	Set<String> acctSet = new HashSet<String>();
        
    	JSONArray feeInfoList = jsonRoot.getJSONArray(AmcConstants.APMessage.FEE_INFO);
		for (Object feeInfoItem : feeInfoList) {
			JSONObject feeInfo = (JSONObject)feeInfoItem;
			String subject = feeInfo.getString(AmcConstants.APMessage.SUBJECT);
			if(StringUtils.isBlank(subject)){
				continue;
			}
			String feeStr = feeInfo.getString(AmcConstants.APMessage.FEE);
			BigDecimal fee = new BigDecimal(feeStr);
			
			String apportionStr = feeInfo.getString(AmcConstants.APMessage.APPORTION_LIST);
			LOG.info("接收到的分摊信息：["+apportionStr+"]");
			//System.out.println("接收到的分摊信息：["+apportionStr+"]");
			JSONArray apportionList = JSONArray.parseArray(apportionStr);
			for(Object apportionChild : apportionList){
				JSONObject apportion = (JSONObject)apportionChild;
				String apportionAcctId = apportion.getString(AmcConstants.APMessage.Apportion.SERVICE_ID);
				acctSet.add(apportionAcctId);
		        String method = apportion.getString(AmcConstants.APMessage.Apportion.METHOD);
		        String value = apportion.getString(AmcConstants.APMessage.Apportion.VALUE);
		        BigDecimal feeResult = null;
		        BigDecimal drFeeResult = null;
		        if(AmcConstants.APMessage.Apportion.METHOD_RATIO.equals(method)){
	                LOG.info("对详单金额进行四舍五入：详单金额["+fee.toPlainString()+"]");
	                //System.out.println("对详单金额进行四舍五入：详单金额["+fee.toPlainString()+"]");
	                feeResult = fee.multiply(new BigDecimal(value)).setScale(-1,BigDecimal.ROUND_HALF_UP);
	                //feeResult = BigDecimal.valueOf(fee1Sum*value).setScale(-1,BigDecimal.ROUND_HALF_UP).longValue();
	                LOG.info("对详单金额进行四舍五入：账单金额["+feeResult.toPlainString()+"]");
	                //System.out.println("对详单金额进行四舍五入：账单金额["+feeResult.toPlainString()+"]");
	                drFeeResult = fee.multiply(new BigDecimal(value));
	            }else{
	                LOG.info("对详单金额进行四舍五入：详单金额["+value+"]");
	                feeResult = new BigDecimal(value).setScale(-1,BigDecimal.ROUND_HALF_UP);
	                drFeeResult = new BigDecimal(value);
	            }
		        
		        AccountPreferentialVo accountPreferentialVo = new AccountPreferentialVo();
	            accountPreferentialVo.setAcctId(apportionAcctId);
	            accountPreferentialVo.setBillMonth(accountMonth);
	            accountPreferentialVo.setCustId(custIdRoot);
	            accountPreferentialVo.setFee1(feeResult.toPlainString());
	            accountPreferentialVo.setFee2("0");
	            accountPreferentialVo.setFee3("0");
	            accountPreferentialVo.setSubject1(subject);
	            accountPreferentialVo.setSubject2("");
	            accountPreferentialVo.setSubject3("");
	            accountPreferentialVo.setServiceId(serviceIdRoot);
	            accountPreferentialVo.setTenantId(tenantId);
	            accountPreferentialVo.setSubsId(subsId);
	            accountPreferentialVo.setDrTotalAmount(drFeeResult.toString());
	            this.accountPreferential(accountPreferentialVo);
	            //System.out.println("调用累帐方法："+accountPreferentialVo.toString());
	            CostCenterVo costCenterVo = new CostCenterVo();
	            costCenterVo.setTenantId(tenantId);
	            costCenterVo.setCostCenterId(apportion.getString(AmcConstants.APMessage.Apportion.COST_CENTER_ID));
	            costCenterVo.setSubjectId(subject);
	            costCenterVo.setApportionAcctId(apportionAcctId);
	            costCenterVo.setApportionMethod(method);
	            costCenterVo.setApportionValue(value);
	            costCenterVo.setAmount(feeResult.toPlainString());
	            if(StringUtils.equalsIgnoreCase(acctId, apportionAcctId)){
	            	costCenterVo.setIsApportion("N");//分摊
	            }else{
	            	costCenterVo.setIsApportion("Y");//不分摊
	            }
	            costCenterVo.setDrKey(drKey);
	            costCenterVo.setAcctId(acctId);
	            costCenterVo.setCustId(custId);
	            costCenterVo.setSubsId(subsId);
	            costCenterVo.setBillMonth(accountMonth);
	            this.costCenterAction(costCenterVo);
			}
			
			
    	}
		//实时销账
		IMessageSender msgSender = MDSClientFactory.getSenderClient(AmcConstants.MDSTopic.WO_TOPIC);
		for(String acctStr:acctSet){
			JSONObject json = new JSONObject();
			json.put(AmcConstants.APMessage.TENANT_ID,tenantId);
			json.put(AmcConstants.APMessage.ACCT_ID,acctStr);
			//System.out.println("tenant_id="+tenantId+",acct_id="+acctStr);
			msgSender.send(json.toString(), Long.valueOf(new SecureRandom().nextInt(1000)));
		}
		
    }
    
    /**
     * 成本中心计算
     * @param costCenterVo
     */
    private void costCenterAction(CostCenterVo costCenterVo){
    	String tenantId = costCenterVo.getTenantId();
    	String drSubject = costCenterVo.getSubjectId();
    	String billSubject = this.queryBillSubject(tenantId, drSubject);
    	//保存成本中心流水信息
    	this.saveCostCenterDetail(costCenterVo, billSubject);
    	this.saveOrUpdateCcCharge(costCenterVo, billSubject);
    	
    }
    
    /**
     * 保存成本中心明细表
     * @param costCenterVo
     * @param billSubject
     */
    private void saveCostCenterDetail(CostCenterVo costCenterVo,String billSubject) {
    	AmcCcDetailYyyydd amcCcDetailBean = new AmcCcDetailYyyydd();
    	String ccDetailSeq = SeqUtil.getNewId(AmcConstants.SeqName.AMC_CC_DETAIL$SERIAL_CODE$SEQ, 10);
    	amcCcDetailBean.setCcDetailSeq(Long.parseLong(ccDetailSeq));
    	amcCcDetailBean.setTenantId(costCenterVo.getTenantId());
    	amcCcDetailBean.setCostCenterId(costCenterVo.getCostCenterId());
    	amcCcDetailBean.setSubjectId(Long.parseLong(billSubject));
    	amcCcDetailBean.setApportionAcctId(costCenterVo.getApportionAcctId());
    	amcCcDetailBean.setApportionMethod(costCenterVo.getApportionMethod());
    	amcCcDetailBean.setApportionValue(costCenterVo.getApportionValue());
    	amcCcDetailBean.setAmount(Long.parseLong(costCenterVo.getAmount()));
    	amcCcDetailBean.setAcctId(costCenterVo.getAcctId());
    	amcCcDetailBean.setCustId(Long.parseLong(costCenterVo.getCustId()));
    	amcCcDetailBean.setSubsId(Long.parseLong(costCenterVo.getSubsId()));
    	amcCcDetailBean.setIsApportion(costCenterVo.getIsApportion());
    	amcCcDetailBean.setDrKey(costCenterVo.getDrKey());
    	amcCcDetailBean.setCreateDate(new Timestamp(System.currentTimeMillis()));
    	amcCcDetailBean.setBillMonth(costCenterVo.getBillMonth());
    	amcCcDetailYyyyddAtomSV.addAmcCcDetail(amcCcDetailBean);
    }
    
    /**
     * 更新成本中心汇总表
     * @param costCenterVo
     * @param billSubject
     */
    private void saveOrUpdateCcCharge(CostCenterVo costCenterVo,String billSubject){
    	Long subjectId = new Long(billSubject);
    	List<AmcCcChargeYyyydd> amcCcChargeResults = amcCcChargeYyyyddAtomSV
				.queryCcChargeByCcidAndSubjectId(costCenterVo.getTenantId(),
						costCenterVo.getCostCenterId(), subjectId,
						costCenterVo.getBillMonth());
    	AmcCcChargeYyyydd amcCcChargeBean = null;
		if (amcCcChargeResults == null || amcCcChargeResults.size() == 0) {
			amcCcChargeBean = new AmcCcChargeYyyydd();
    		String ccChargeSeq = SeqUtil.getNewId(AmcConstants.SeqName.AMC_CC_CHARGE$SERIAL_CODE$SEQ, 10);
    		amcCcChargeBean.setCcChargeSeq(Long.parseLong(ccChargeSeq));
    		amcCcChargeBean.setTenantId(costCenterVo.getTenantId());
    		amcCcChargeBean.setCostCenterId(costCenterVo.getCostCenterId());
    		amcCcChargeBean.setSubjectId(Long.parseLong(billSubject));
    		amcCcChargeBean.setApportionAcctId(costCenterVo.getApportionAcctId());
    		amcCcChargeBean.setAmount(Long.parseLong(costCenterVo.getAmount()));
    		amcCcChargeBean.setLastDate(new Timestamp(System.currentTimeMillis()));
    		amcCcChargeBean.setBillMonth(costCenterVo.getBillMonth());
    		amcCcChargeYyyyddAtomSV.addAmcCcCharge(amcCcChargeBean);
    	}else{
    		amcCcChargeBean = amcCcChargeResults.get(0);
    		long add = amcCcChargeBean.getAmount().longValue() + Long.parseLong(costCenterVo.getAmount());
    		amcCcChargeBean.setAmount(add);
    		amcCcChargeBean.setLastDate(new Timestamp(System.currentTimeMillis()));
    		amcCcChargeBean.setBillMonth(costCenterVo.getBillMonth());
    		amcCcChargeYyyyddAtomSV.updateAmcCcCharge(amcCcChargeBean);
    	}
    
    }
    
    
    @Override
    public int accountPreferential(AccountPreferentialVo accountPreferentialVo) {
        String tenantId = accountPreferentialVo.getTenantId();
        String acctId = accountPreferentialVo.getAcctId();
        /* 2.根据传入的详单科目查询对应的账单科目 */
        String drSubject1 = accountPreferentialVo.getSubject1();
        String billSubject1 = this.queryBillSubject(tenantId, drSubject1);

        String fee1Str = accountPreferentialVo.getFee1();
        long fee1 = (Long.parseLong(fee1Str));
        String billMonth = accountPreferentialVo.getBillMonth();
        /* 3.累账，将记录中的数据，增加到对应账单中(记录到内存中，不沉淀到数据库) */
        List<AmcChargeYyyydd> chargeListDB = amcChargeAtomSV.queryChargeByAcctId(acctId, tenantId, billMonth);
        /* 3.1 对fee1判断并累账到对应的科目 */
        AmcChargeYyyydd beanAfter1 = new AmcChargeYyyydd();
        if (fee1 != 0) {// 如果费用不为0，则进行累账
            /* 3.1.1 遍历db中的数据，找到科目1对应的数据，如果找到，则将金额加入totalAmount */
            for (AmcChargeYyyydd amcChargeBean : chargeListDB) {
                if (billSubject1.equals(amcChargeBean.getSubjectId()+"")) {
                    beanAfter1 = amcChargeBean;
                }
            }
            this.saveOrUpdateChargeBean(beanAfter1, accountPreferentialVo, fee1, Long.parseLong(accountPreferentialVo.getDrTotalAmount()),
                        Long.parseLong(billSubject1),billMonth);
            
        }
        /* 3.2 对fee2判断并累账到对应的科目 */
        
        this.saveOrUpdateInvoiceBean(acctId, tenantId, accountPreferentialVo.getCustId(), (fee1), billMonth,accountPreferentialVo.getSubsId(),accountPreferentialVo.getServiceId());
        this.updateOwnInfo(acctId, tenantId, (fee1));
        
        return 0;
    }
    /**
     * 根据传入的详单科目查询对应的账单科目
     * 
     * @param drSubject
     * @return
     * @author LiangMeng
     */
    private String queryBillSubject(String tenantId, String drSubject) {

        if (client == null) {
            client = new DshmClient();
        }
        Properties p = new Properties();
        if (cacheClient == null) {
            cacheClient = CacheFactoryUtil.getCacheClient(p, CacheBLMapper.CACHE_BL_CAL_PARAM);
        }
        String billSubject = null;
        Map<String, String> params = new TreeMap<String, String>();
        params.put(AmcConstants.FmtFeildName.TENANT_ID, tenantId);
        params.put("dr_subject", drSubject);
        List<Map<String, String>> results = client
                .list(AmcConstants.CacheConfig.AMC_DR_BILL_SUBJECT_MAP).where(params)
                .executeQuery(cacheClient);
        if (results != null && results.size() > 0) {
            billSubject = results.get(0).get("bill_subject");
        } else {
            throw new BusinessException("999999", "根据详单科目[" + drSubject + "]查询账单科目不存在");
        }
        return billSubject;
    }
    /**
     * 初始化
     * @param amcChargeBean
     * @param data
     * @param fee
     * @param subjectId
     * @author LiangMeng
     */
//    private void saveOrUpdateChargeBean(AmcChargeYyyydd amcChargeBean,AccountPreferentialVo accountPreferentialVo, long fee,
//            long subjectId,String billMonth) {
    private void saveOrUpdateChargeBean(AmcChargeYyyydd amcChargeBean,AccountPreferentialVo accountPreferentialVo, long fee, long drTotalFee, 
            long subjectId,String billMonth) {
        amcChargeBean.setBillMonth(billMonth);
        amcChargeBean.setTenantId(accountPreferentialVo.getTenantId());
        amcChargeBean.setAcctId(accountPreferentialVo.getAcctId());
        if(!StringUtil.isBlank(accountPreferentialVo.getCustId())){
            amcChargeBean.setCustId(Long.parseLong(accountPreferentialVo.getCustId()));
        }
        amcChargeBean.setDiscTotalAmount(0l);
        amcChargeBean.setAdjustAfterwards(0l);
        amcChargeBean.setLastPayDate(new Timestamp(new Date().getTime()));
        amcChargeBean.setServiceId(accountPreferentialVo.getServiceId());
        amcChargeBean.setSubjectId(subjectId);
        if(!StringUtil.isBlank(accountPreferentialVo.getSubsId())){
            amcChargeBean.setSubsId(Long.parseLong(accountPreferentialVo.getSubsId()));
        }
        if (amcChargeBean.getChargeSeq() == null || amcChargeBean.getChargeSeq() == 0) {

            String chargeSeq = SeqUtil
                    .getNewId(AmcConstants.SeqName.AMC_CHARGE$SERIAL_CODE$SEQ, 10);
            amcChargeBean.setChargeSeq(Long.parseLong(chargeSeq));
            amcChargeBean.setBalance(fee);
            amcChargeBean.setPayStatus(0l);
            amcChargeBean.setTotalAmount(fee);
            amcChargeBean.setDrTotalAmount(drTotalFee);
            amcChargeAtomSV.addAmcCharge(amcChargeBean);
        }else{
            long balanceAfter = amcChargeBean.getBalance()+fee;
            long totalAfter = amcChargeBean.getTotalAmount()+fee;
            if(balanceAfter==totalAfter){
                amcChargeBean.setPayStatus(0l);
            }else if(balanceAfter<totalAfter){
                amcChargeBean.setPayStatus(2l);
            }
            amcChargeBean.setBalance(amcChargeBean.getBalance()+fee);
            amcChargeBean.setTotalAmount(amcChargeBean.getTotalAmount()+fee);
            amcChargeBean.setDrTotalAmount(amcChargeBean.getDrTotalAmount() + drTotalFee);
            amcChargeBean.setBillMonth(billMonth);
            amcChargeAtomSV.updateAmcCharge(amcChargeBean);
        }
        
    }
    /**
     * 更新账单总表
     * @param acctId
     * @param tenantId
     * @param custId
     * @param fee
     * @param billMonth
     * @author LiangMeng
     */
    private void saveOrUpdateInvoiceBean(String acctId,String tenantId,String custId, long fee,String billMonth,String subsId,String serviceId) {
        List<AmcInvoiceYyyydd> amcinvoiceBeans = amcInvoiceAtomSV.queryInvoiceByAcctId(acctId, tenantId, billMonth);
        AmcInvoiceYyyydd amcinvoiceBean;
        if(CollectionUtil.isEmpty(amcinvoiceBeans)){
            String invoiceSeq = SeqUtil
                    .getNewId(AmcConstants.SeqName.AMC_INVOICE$SERIAL_CODE$SEQ, 10);
            amcinvoiceBean = new AmcInvoiceYyyydd();
            amcinvoiceBean.setAcctId(acctId);
            amcinvoiceBean.setCustId(Long.parseLong(custId));
            amcinvoiceBean.setAdjustAfterwards(0l);
            amcinvoiceBean.setBalance(fee);
            amcinvoiceBean.setBillMonth(billMonth);
            amcinvoiceBean.setDiscTotalAmount(0l);
            amcinvoiceBean.setInvoiceSeq(Long.parseLong(invoiceSeq));
            amcinvoiceBean.setLastPayDate(new Timestamp(System.currentTimeMillis()));
            amcinvoiceBean.setPayStatus(0l);
            amcinvoiceBean.setPrintTimes(0l);
            amcinvoiceBean.setTenantId(tenantId);
            amcinvoiceBean.setTotalAmount(fee);
            if(!StringUtil.isBlank(subsId)){
                amcinvoiceBean.setSubsId(Long.parseLong(subsId));
            }
            amcinvoiceBean.setServiceId(serviceId);
            amcInvoiceAtomSV.addAmcInvoice(amcinvoiceBean);
        }else if(amcinvoiceBeans.size()>1){
            throw new BusinessException(ExceptCodeConstants.Special.NO_RESULT, "账单总表获取异常，大于1条");
        }else{
            amcinvoiceBean = amcinvoiceBeans.get(0);
            long balanceAfter = amcinvoiceBean.getBalance()+fee;
            long totalAfter = amcinvoiceBean.getTotalAmount()+fee;
            if(balanceAfter==totalAfter){
                amcinvoiceBean.setPayStatus(0l);
            }else if(balanceAfter<totalAfter){
                amcinvoiceBean.setPayStatus(2l);
            }
            amcinvoiceBean.setLastPayDate(new Timestamp(System.currentTimeMillis()));
            amcinvoiceBean.setBillMonth(billMonth);
            amcinvoiceBean.setBalance(amcinvoiceBean.getBalance()+fee);
            amcinvoiceBean.setTotalAmount(amcinvoiceBean.getTotalAmount()+fee);
            amcInvoiceAtomSV.updateAmcInvoice(amcinvoiceBean);
        }       
    }
    /**
     * 更新欠费表
     * @param acctId
     * @param tenantId
     * @param fee
     * @param billMonth
     * @author LiangMeng
     */
    private void updateOwnInfo(String acctId,String tenantId, long fee){
        AmcOweInfoKey oweInfoKey = new AmcOweInfoKey();
        oweInfoKey.setAcctId(acctId);
        oweInfoKey.setTenantId(tenantId);
        AmcOweInfo oweInfo = amcOweInfoAtomSV.selectByInfoKey(oweInfoKey);
        if(oweInfo==null){
            throw new BusinessException(ExceptCodeConstants.Special.NO_RESULT, "欠费信息异常");
        }
        BigDecimal balance = oweInfo.getBalance();
        LOG.info("欠费更新前：["+balance+"]");
        oweInfo.setBalance(balance.add(BigDecimal.valueOf(fee)));
        LOG.info("欠费更新后：["+oweInfo.getBalance()+"]");
        amcOweInfoAtomSV.updateOweInfo(oweInfo);
    }
    

}
