package com.ai.baas.cust.service.business.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.ai.baas.cust.api.custinfo.params.CustIdParams;
import com.ai.baas.cust.api.custinfo.params.CustInfoParams;
import com.ai.baas.cust.api.custinfo.params.ExtInfo;
import com.ai.baas.cust.constants.BmcConstants;
import com.ai.baas.cust.constants.CustState;
import com.ai.opt.sdk.util.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.hbase.client.Table;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.cust.context.Context;
import com.ai.baas.cust.context.ErrorCode;
import com.ai.baas.cust.context.TableCon;
import com.ai.baas.cust.context.TableCon.ConTradeSeqLog;
import com.ai.baas.cust.dao.interfaces.BlAcctInfoMapper;
import com.ai.baas.cust.dao.interfaces.BlCustinfoExtMapper;
import com.ai.baas.cust.dao.interfaces.BlCustinfoMapper;
import com.ai.baas.cust.dao.interfaces.BlUserinfoMapper;
import com.ai.baas.cust.dao.mapper.bo.BlAcctInfo;
import com.ai.baas.cust.dao.mapper.bo.BlAcctInfoCriteria;
import com.ai.baas.cust.dao.mapper.bo.BlCustinfo;
import com.ai.baas.cust.dao.mapper.bo.BlCustinfoCriteria;
import com.ai.baas.cust.dao.mapper.bo.BlCustinfoExt;
import com.ai.baas.cust.dao.mapper.bo.BlCustinfoExtCriteria;
import com.ai.baas.cust.dao.mapper.bo.BlUserinfo;
import com.ai.baas.cust.service.atom.interfaces.IBlAcctInfoAtomSV;
import com.ai.baas.cust.service.atom.interfaces.IBlCustinfoAtomSV;
import com.ai.baas.cust.service.atom.interfaces.IBlUserinfoAtomSV;
import com.ai.baas.cust.service.business.interfaces.ICustinfoBusiness;
import com.ai.baas.cust.service.business.interfaces.ISysSequenceSvc;
import com.ai.baas.cust.util.BmcSeqUtil;
import com.ai.baas.cust.util.DshmUtil;
import com.ai.baas.cust.util.MyHbaseUtil;
import com.ai.baas.cust.util.MyJsonUtil;
import com.ai.baas.cust.util.MyHbaseUtil.CellTemp;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.paas.ipaas.util.StringUtil;
import com.alibaba.fastjson.JSON;

import net.sf.json.JSONObject;


@Service
@Transactional
public class ICustinfoBusinessImpl implements ICustinfoBusiness{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	@Autowired
	private BlCustinfoExtMapper blCustinfoExtMapper;
	@Autowired
	private BlCustinfoMapper aBlCustinfoMapper;
	@Autowired
	private ISysSequenceSvc aISysSequenceSvc;
	@Autowired
    private transient BlAcctInfoMapper blAcctInfoMapper;
    @Autowired
    private transient BlUserinfoMapper blUserinfoMapper;
    @Autowired
    private transient IBlAcctInfoAtomSV iBlAcctInfoAtomSV;
    @Autowired
    private transient IBlUserinfoAtomSV iBlUserinfoAtomSV;
	
	private static final Log log = LogFactory.getLog(ICustinfoBusinessImpl.class);
	@Override
	public boolean hasSeq(CustInfoParams custInfo) throws IOException {
		 String rowkey = custInfo.getTenantId() + Context.SPLIT + Context.CUST_INFO_CODE
	                + Context.SPLIT + custInfo.getTradeSeq();
	        Table table = MyHbaseUtil.getTable(TableCon.TRADE_SEQ_LOG);

	        if (MyHbaseUtil.hasExists(table, rowkey)) {
	            return true;
	        }
	        MyHbaseUtil.addData(table, rowkey,
	                CellTemp.inst(ConTradeSeqLog.TENANT_ID, custInfo.getTenantId()),
	                CellTemp.inst(ConTradeSeqLog.INTERFACE_CODE, Context.CUST_INFO_CODE),
	                CellTemp.inst(ConTradeSeqLog.TRADE_SEQ, custInfo.getTradeSeq()),
	                CellTemp.inst(ConTradeSeqLog.RECEIVE_TIME,
	                        DateUtil.getDateString(DateUtil.YYYYMMDDHHMMSS)),
	                CellTemp.inst(ConTradeSeqLog.MSG_CONTENT, MyJsonUtil.toJson(custInfo)));
	        return false;
	    
		
	}



	@Override
	public void writeData(CustInfoParams custInfo, boolean saveAcctInfo) {
		JSONObject custobject = new JSONObject();
		BlCustinfo blCustinfo = new BlCustinfo();
		log.info("*******************************");
		if (!StringUtil.isBlank(custInfo.getState())) {
			if(CustState.hasState(custInfo.getState())){
				blCustinfo.setState(custInfo.getState().toUpperCase());
				custobject.put("STATE", custInfo.getState().toUpperCase());
			}else{
				throw new BusinessException("BMC-00002", "State不合法");
			}
		} else {
			blCustinfo.setState(CustState.NORMAL.name());
			custobject.put("STATE", CustState.NORMAL.name());
		}
		if (!StringUtil.isBlank(custInfo.getStateChgTime())) {
			if (custInfo.getState().length() <= 14) {
				blCustinfo.setStateChgTime(DateUtil.getTimestamp(custInfo.getStateChgTime(), DateUtil.YYYYMMDDHHMMSS));
				custobject.put("STATE_CHG_TIME", DateUtil.getTimestamp(custInfo.getStateChgTime(), DateUtil.YYYYMMDDHHMMSS).toString().substring(0, 19));
				
			} else {
				throw new BusinessException("BMC-00002", "StateChgTime超长");
			}
		} else {
			  blCustinfo.setStateChgTime(DateUtil.getSysDate());
		        custobject.put("STATE_CHG_TIME",DateUtil.getDateString(DateUtil.getSysDate(),
				DateUtil.YYYYMMDDHHMMSS));
		}    
	        
		blCustinfo.setTenantId(custInfo.getTenantId());
		custobject.put("TENANT_ID", custInfo.getTenantId());
		
		blCustinfo.setExtCustId(custInfo.getExtCustId());
		custobject.put("EXT_CUST_ID", custInfo.getExtCustId());
		
		blCustinfo.setCustName(custInfo.getCustName());
		custobject.put("CUST_NAME", custInfo.getCustName());
		
		blCustinfo.setProvinceCode(custInfo.getProvinceCode());
		custobject.put("PROVINCE_CODE", custInfo.getProvinceCode());
		
		blCustinfo.setCityCode(custInfo.getCityCode());
		custobject.put("CITY_CODE", custInfo.getCityCode());
			
		blCustinfo.setCustGrade(custInfo.getCustGrade());
		custobject.put("CUST_GRADE", custInfo.getCustGrade());
		
		blCustinfo.setCustType(custInfo.getCustType());
		custobject.put("CUST_TYPE", custInfo.getCustType());
		
		blCustinfo.setRemark(custInfo.getRemark());
		custobject.put("REMARK", custInfo.getRemark());
		
		blCustinfo.setContactNo(custInfo.getContactNo());
		custobject.put("CONTACT_NO", custInfo.getContactNo());
		
		blCustinfo.setEmail(custInfo.getEmail());
		custobject.put("EMAIL", custInfo.getEmail());
		
		blCustinfo.setCustAddress(custInfo.getCustAddress());
		custobject.put("CUST_ADDRESS", custInfo.getCustAddress());
		
		blCustinfo.setIdNumber(custInfo.getIdNumber());
		custobject.put("ID_NUMBER", custInfo.getIdNumber());
		
		String custId = null;
		Map<String, String> params = new TreeMap<String, String>();
        params.put("EXT_CUST_ID", custInfo.getExtCustId());
        params.put("TENANT_ID", custInfo.getTenantId());
        
        List<Map<String, String>> result = DshmUtil.getClient().list("bl_custinfo").where(params)
                .executeQuery(DshmUtil.getCacheClient());
        log.info("dshm custInfo---->>>:"+JSON.toJSONString(result));
        //获得缓存中第一条有效数据
        boolean bool = true;
        if(!(result==null||result.isEmpty())){
            for(Map<String, String> r : result){
                if(!r.isEmpty()){
                    custId = r.get("cust_id");
                    blCustinfo.setCustId(custId);
                    custobject.put("CUST_ID", custId);
                    bool = false;
                    break;
                }
            }
        }
        // bool=fasle时说明获得了一条数据，不需要执行下面部分
        if(bool){
        	custId = aISysSequenceSvc.terrigerSysSequence("CUST_ID", 1).get(0);
    		blCustinfo.setCustId(custId);
    		custobject.put("CUST_ID", custId);
        }
//        custId ="3";
//        blCustinfo.setCustId(custId);
		if (custInfo.getExtInfoList() != null) {
			for (ExtInfo e : custInfo.getExtInfoList()) {
				log.info("extinfo.extValue--->>>:"+e.getExtValue());
				writeBlCustinfoExt(custId,e);

			}
		}
		
		BlCustinfoCriteria blCustinfoCriteria = new BlCustinfoCriteria();
		blCustinfoCriteria.createCriteria()
		    .andTenantIdEqualTo(custInfo.getTenantId())
		    .andCustIdEqualTo(custId);
		
		List<BlCustinfo> blCustInfoList = aBlCustinfoMapper.selectByExample(blCustinfoCriteria);
		//如果信息不存在 就添加信息
		if(CollectionUtil.isEmpty(blCustInfoList)){
			log.info("---------------custInfo not exist------------------");
			aBlCustinfoMapper.insert(blCustinfo);
			DshmUtil.getIdshmSV().initLoader("bl_custinfo", JSON.toJSONString(custobject),1);
			if(saveAcctInfo){
				this.saveAcctInfo(blCustinfo);
			}
		}else{
			updateBlCustInfo(blCustinfo, custobject, custId);
		}
		
	}

	private void updateBlCustInfo(BlCustinfo custInfo, JSONObject custobject, String custId) {
		try{
            log.info("---------------custInfo exist------------------");
            //如果信息存在就修改信息
            BlCustinfo blCustInfoUpdate = new BlCustinfo();
			BeanUtils.copyProperties(blCustInfoUpdate,custInfo);
            blCustInfoUpdate.setStateChgTime(DateUtil.getSysDate());//(DateUtil.getSysDate());
            BlCustinfoCriteria blCustinfoCriteriaUpdate = new BlCustinfoCriteria();
            blCustinfoCriteriaUpdate.createCriteria()
                .andTenantIdEqualTo(custInfo.getTenantId())
                .andCustIdEqualTo(custId);

            final int state = aBlCustinfoMapper.updateByExampleSelective(blCustInfoUpdate, blCustinfoCriteriaUpdate);
            if(state>0&&custobject.size()==0) {
                List<BlCustinfo> blCustinfos = aBlCustinfoMapper.selectByExample(blCustinfoCriteriaUpdate);
                if (!CollectionUtil.isEmpty(blCustinfos)) {
                    BlCustinfo custinfo = blCustinfos.get(0);
                    custobject.put("TENANT_ID", custinfo.getTenantId());
                    custobject.put("EXT_CUST_ID", custinfo.getExtCustId());
                    custobject.put("CUST_NAME", custinfo.getCustName());
                    custobject.put("PROVINCE_CODE", custinfo.getProvinceCode());
                    custobject.put("CITY_CODE", custinfo.getCityCode());
                    custobject.put("CUST_GRADE", custinfo.getCustGrade());
                    custobject.put("CUST_TYPE", custinfo.getCustType());
                    custobject.put("REMARK", custinfo.getRemark());
                    custobject.put("CONTACT_NO", custinfo.getContactNo());
                    custobject.put("EMAIL", custinfo.getEmail());
                    custobject.put("CUST_ADDRESS", custinfo.getCustAddress());
                    custobject.put("ID_NUMBER", custinfo.getIdNumber());
                    custobject.put("STATE", custinfo.getState());
                    custobject.put("STATE_CHG_TIME", DateUtil.getDateString(custinfo.getStateChgTime(), DateUtil.DATETIME_FORMAT));
                    custobject.put("CUST_ID", custinfo.getCustId());
                    DshmUtil.getIdshmSV().initLoader("bl_custinfo", JSON.toJSONString(custobject), 0);
                }
            }
            log.info("------------update custInfo:修改客户信息完毕！");
        }catch(Exception e){
            log.error("------------update custInfo exception:修改客户信息异常------------------",e);
        }
	}

	@Override
	public void updateCustInfo(CustInfoParams custInfo) {
		BlCustinfo blCustinfo = new BlCustinfo();
		log.info("*******************************");
		if (!StringUtil.isBlank(custInfo.getState())) {
			if(CustState.hasState(custInfo.getState())){
				blCustinfo.setState(custInfo.getState().toUpperCase());
			}else{
				throw new BusinessException("BMC-00002", "State不合法");
			}
		}
		if (!StringUtil.isBlank(custInfo.getStateChgTime())) {
			if (custInfo.getState().length() <= 14) {
				blCustinfo.setStateChgTime(DateUtil.getTimestamp(custInfo.getStateChgTime(), DateUtil.YYYYMMDDHHMMSS));
			} else {
				throw new BusinessException("BMC-00002", "StateChgTime超长");
			}
		} else {
			blCustinfo.setStateChgTime(DateUtil.getSysDate());
		}

		blCustinfo.setTenantId(custInfo.getTenantId());
		blCustinfo.setExtCustId(custInfo.getExtCustId());
		blCustinfo.setCustName(custInfo.getCustName());
		blCustinfo.setProvinceCode(custInfo.getProvinceCode());
		blCustinfo.setCityCode(custInfo.getCityCode());
		blCustinfo.setCustGrade(custInfo.getCustGrade());
		blCustinfo.setCustType(custInfo.getCustType());
		blCustinfo.setRemark(custInfo.getRemark());
		blCustinfo.setContactNo(custInfo.getContactNo());
		blCustinfo.setEmail(custInfo.getEmail());
		blCustinfo.setCustAddress(custInfo.getCustAddress());
		blCustinfo.setIdNumber(custInfo.getIdNumber());

		String custId = null;
		Map<String, String> params = new TreeMap<String, String>();
		params.put("EXT_CUST_ID", custInfo.getExtCustId());
		params.put("TENANT_ID", custInfo.getTenantId());

		List<Map<String, String>> result = DshmUtil.getClient().list("bl_custinfo").where(params)
				.executeQuery(DshmUtil.getCacheClient());
		log.info("dshm custInfo---->>>:"+JSON.toJSONString(result));
		//获得缓存中第一条有效数据
		boolean bool = false;
		if(!(result==null||result.isEmpty())){
			for(Map<String, String> r : result){
				if(!r.isEmpty()){
					custId = r.get("cust_id");
					blCustinfo.setCustId(custId);
					bool = true;
					break;
				}
			}
		}
		// bool=true时说明获得了一条数据，不需要执行下面部分
		if(bool){
			BlCustinfoCriteria blCustinfoCriteria = new BlCustinfoCriteria();
			blCustinfoCriteria.createCriteria()
					.andTenantIdEqualTo(custInfo.getTenantId())
					.andExtCustIdEqualTo(custInfo.getExtCustId());

			List<BlCustinfo> blCustInfoList = aBlCustinfoMapper.selectByExample(blCustinfoCriteria);
			if(!CollectionUtil.isEmpty(blCustInfoList)){
				blCustinfo.setCustId(custId);
				updateBlCustInfo(blCustinfo, new JSONObject(), custId);
				//更新扩展信息
				if (custInfo.getExtInfoList() != null) {
					for (ExtInfo e : custInfo.getExtInfoList()) {
						log.info("extinfo.extValue--->>>:"+e.getExtValue());
						writeBlCustinfoExt(custId,e);
					}
				}
			}else {
				throw new BusinessException("客户信息【"+custInfo.getExtCustId()+"】不存在，无法修改");
			}
		}else {
			throw new BusinessException("客户信息【"+custInfo.getExtCustId()+"】在缓存中不存在，无法修改");
		}
	}

	@Override
	public void deleteCustInfo(CustIdParams params) {
		log.info("---------------custInfo exist------------------");
		//如果信息存在就修改信息
		BlCustinfo blCustInfoUpdate = new BlCustinfo();
		blCustInfoUpdate.setState(CustState.DELETE.name());
		blCustInfoUpdate.setStateChgTime(DateUtil.getSysDate());
		BlCustinfoCriteria blCustinfoCriteriaUpdate = new BlCustinfoCriteria();
		blCustinfoCriteriaUpdate.createCriteria()
				.andTenantIdEqualTo(params.getTenantId())
				.andExtCustIdEqualTo(params.getExtCustId())
                .andStateNotEqualTo(CustState.DELETE.name());

        final int state = aBlCustinfoMapper.updateByExampleSelective(blCustInfoUpdate, blCustinfoCriteriaUpdate);
        //
        if(state>0){
            BlCustinfoCriteria con = new BlCustinfoCriteria();
            con.createCriteria().andTenantIdEqualTo(params.getTenantId()).andExtCustIdEqualTo(params.getExtCustId());
            List<BlCustinfo> blCustinfos = aBlCustinfoMapper.selectByExample(con);
            if(!CollectionUtil.isEmpty(blCustinfos)){
                BlCustinfo custinfo = blCustinfos.get(0);
                JSONObject custobject = new JSONObject();
                custobject.put("TENANT_ID", custinfo.getTenantId());
                custobject.put("EXT_CUST_ID", custinfo.getExtCustId());
                custobject.put("CUST_NAME", custinfo.getCustName());
                custobject.put("PROVINCE_CODE", custinfo.getProvinceCode());
                custobject.put("CITY_CODE", custinfo.getCityCode());
                custobject.put("CUST_GRADE", custinfo.getCustGrade());
                custobject.put("CUST_TYPE", custinfo.getCustType());
                custobject.put("REMARK", custinfo.getRemark());
                custobject.put("CONTACT_NO", custinfo.getContactNo());
                custobject.put("EMAIL", custinfo.getEmail());
                custobject.put("CUST_ADDRESS", custinfo.getCustAddress());
                custobject.put("ID_NUMBER", custinfo.getIdNumber());
                custobject.put("STATE", custinfo.getState());
                custobject.put("STATE_CHG_TIME", DateUtil.getDateString(custinfo.getStateChgTime(),DateUtil.YYYYMMDDHHMMSS));
                custobject.put("CUST_ID", custinfo.getCustId());
                DshmUtil.getIdshmSV().initLoader("bl_custinfo", JSON.toJSONString(custobject),0);
            }
            log.info("------------delete custInfo:删除客户信息完毕！");
        }else{
            log.info("------------delete custInfo:客户不存在，删除失败！");
            throw new BusinessException("客户信息【"+params.getExtCustId()+"】不存在，无法删除");
        }
	}

	private void writeBlCustinfoExt(String custId, ExtInfo extInfo) {
		BlCustinfoExt blCustinfoExt = new BlCustinfoExt();
		JSONObject extobject = new JSONObject();
		
		if (extInfo.getUpdateFlag().equals("N")){
			blCustinfoExt.setExtName(extInfo.getExtName());
//			extobject.put("EXT_NAME", extInfo.getExtName());

			blCustinfoExt.setExtValue(extInfo.getExtValue());
//			extobject.put("EXTA_VALUE", extInfo.getExtValue());

			blCustinfoExt.setCustId(custId);
//			extobject.put("CUST_ID", custId);
			
			
			BlCustinfoExtCriteria example = new BlCustinfoExtCriteria();
            example.createCriteria().andExtNameEqualTo(extInfo.getExtName())
            .andCustIdEqualTo(custId);
            
            List<BlCustinfoExt> blCustinfoExtList = blCustinfoExtMapper.selectByExample(example);
            if(CollectionUtil.isEmpty(blCustinfoExtList)){
            	try{
            	blCustinfoExtMapper.insert(blCustinfoExt);
            	//
            	BlCustinfoExt blCustInfoExtNew = blCustinfoExtMapper.selectByExample(example).get(0);
            	extobject.put("EXT_ID", blCustInfoExtNew.getExtId());
            	extobject.put("EXT_NAME", blCustInfoExtNew.getExtName());
            	extobject.put("EXT_VALUE", blCustInfoExtNew.getExtValue());
            	extobject.put("CUST_ID", blCustInfoExtNew.getCustId());
            	log.info("------ insert ext info---->>>:"+MyJsonUtil.toJson(blCustInfoExtNew));
            	DshmUtil.getIdshmSV().initLoader("bl_custinfo_ext", JSON.toJSONString(extobject),1);
            	}catch(Exception e){
            		log.error(e);
            	}
            }
	            

		}else if(extInfo.getUpdateFlag().equals("U")){
			 blCustinfoExt.setExtValue(extInfo.getExtValue());
			 
			 BlCustinfoExtCriteria example = new BlCustinfoExtCriteria();
	            example.createCriteria()
	            .andCustIdEqualTo(custId)
	            .andExtNameEqualTo(extInfo.getExtName());
	            try {
	            	
	            	blCustinfoExtMapper.updateByExampleSelective(blCustinfoExt, example);
	            	List<BlCustinfoExt> blCustinfoExtList = blCustinfoExtMapper.selectByExample(example);
	            	if(!CollectionUtil.isEmpty(blCustinfoExtList)){
	            		BlCustinfoExt blCustInfoExtNew = blCustinfoExtList.get(0);
	            		extobject.put("EXT_ID", blCustInfoExtNew.getExtId());
	                	extobject.put("EXT_NAME", blCustInfoExtNew.getExtName());
	                	extobject.put("EXT_VALUE", blCustInfoExtNew.getExtValue());
	                	extobject.put("CUST_ID", blCustInfoExtNew.getCustId());
	                	
	            		DshmUtil.getIdshmSV().initLoader("bl_custinfo_ext", JSON.toJSONString(extobject),0);
	            	}
	             
	            } catch (Exception e) {
	                throw new BusinessException("用户扩展信息更新失败", e);
	            }
			
		}else if(extInfo.getUpdateFlag().equals("D")){
			 BlCustinfoExtCriteria example = new BlCustinfoExtCriteria();
	            example.createCriteria()
	            .andCustIdEqualTo(custId)
	            .andExtNameEqualTo(extInfo.getExtName());
	            //.andExtValueEqualTo(extInfo.getExtValue())
	            
	            try {
	            	List<BlCustinfoExt> blCustInfoExtList = blCustinfoExtMapper.selectByExample(example);
	            	
	            	if(!CollectionUtil.isEmpty(blCustInfoExtList)){
	            		BlCustinfoExt blCustInfoExtNew = blCustInfoExtList.get(0);
	            		extobject.put("EXT_ID", blCustInfoExtNew.getExtId());
	                	extobject.put("EXT_NAME", blCustInfoExtNew.getExtName());
	                	extobject.put("EXT_VALUE", blCustInfoExtNew.getExtValue());
	                	extobject.put("CUST_ID", blCustInfoExtNew.getCustId());
	                	//
	            		blCustinfoExtMapper.deleteByExample(example);
	            		DshmUtil.getIdshmSV().initdel("bl_custinfo_ext", JSON.toJSONString(extobject));
	            	}
	            } catch (NullPointerException e) {
	                throw new BusinessException(ErrorCode.NULL, "用户扩展信息不在表中，无法删除");
	            }
		}
	}
	
	private void saveAcctInfo(BlCustinfo blCustinfo){
		// 查询账户信息
        BlAcctInfoCriteria blAcctInfoCriteria = new BlAcctInfoCriteria();
        blAcctInfoCriteria.createCriteria().andTenantIdEqualTo(blCustinfo.getTenantId())
                .andOwnerTypeEqualTo(BmcConstants.BlAcctInfo.OwnerType.CUST)
                .andOwnerIdEqualTo(blCustinfo.getCustId());
        List<BlAcctInfo> blAcctInfos = blAcctInfoMapper.selectByExample(blAcctInfoCriteria);
        String acctId = "";
        if (CollectionUtil.isEmpty(blAcctInfos)) {
            // 账户不存在，创建账户
            acctId = BmcSeqUtil.getAcctId();
            BlAcctInfo blAcctInfo = new BlAcctInfo();
            blAcctInfo.setTenantId(blCustinfo.getTenantId());
            blAcctInfo.setAcctId(acctId);
            blAcctInfo.setOwnerType(BmcConstants.BlAcctInfo.OwnerType.CUST);
            blAcctInfo.setOwnerId(blCustinfo.getCustId());
            blAcctInfo.setCreateTime(DateUtil.getSysDate());
            blAcctInfoMapper.insertSelective(blAcctInfo);
            // 写入缓存
            iBlAcctInfoAtomSV.addDshmData(blAcctInfo);
        } else {
            acctId = blAcctInfos.get(0).getAcctId();
        }
        // 新增用户信息表
        BlUserinfo blUserinfo = new BlUserinfo();
        blUserinfo.setTenantId(blCustinfo.getTenantId());
        blUserinfo.setCustId(blCustinfo.getCustId());
        blUserinfo.setSubsId(BmcSeqUtil.getSubsId());
        blUserinfo.setAcctId(acctId);
        blUserinfo.setServiceId(blCustinfo.getExtCustId());
        blUserinfo.setDealTime(DateUtil.getSysDate());
        blUserinfo.setActiveTime(DateUtil.getTimestamp("20160101120000", DateUtil.YYYYMMDDHHMMSS));
        blUserinfo.setInactiveTime(DateUtil.getTimestamp("20990101120000", DateUtil.YYYYMMDDHHMMSS));
//        blUserinfo.setProvinceCode(request.getProvinceCode());
//        blUserinfo.setCityCode(request.getCityCode());
//        blUserinfo.setChlId(request.getChlId());
//        blUserinfo.setDevId(request.getDevId());
//        blUserinfo.setRemark(request.getRemark());
//        blUserinfo.setUserType(request.getUsetype());
//        blUserinfo.setUserState(request.getState());
        blUserinfoMapper.insertSelective(blUserinfo);
        // 写入缓存
        iBlUserinfoAtomSV.addDshmData(blUserinfo);
	}

}
