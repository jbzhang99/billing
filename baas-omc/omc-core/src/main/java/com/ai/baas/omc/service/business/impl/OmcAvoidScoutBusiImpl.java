package com.ai.baas.omc.service.business.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ai.baas.omc.api.avoidscout.params.OmcAvoidScoutInfoVO;
import com.ai.baas.omc.constants.ExceptCodeConstant;
import com.ai.baas.omc.constants.TableCon;
import com.ai.baas.omc.constants.TableCon.CustInfo;
import com.ai.baas.omc.constants.TableCon.UserInfo;
import com.ai.baas.omc.dao.mapper.bo.OmcAvoidScout;
import com.ai.baas.omc.service.atom.interfaces.IOmcAvoidScoutAtom;
import com.ai.baas.omc.service.business.interfaces.IOmcAvoidScoutBusi;
import com.ai.baas.omc.util.CheckUtil;
import com.ai.baas.omc.util.OmcSeqUtil;
import com.ai.baas.omc.service.atom.impl.AccquireDshm;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;

@Service
@Transactional
public class OmcAvoidScoutBusiImpl implements IOmcAvoidScoutBusi{
	private static final Logger log = LogManager
			.getLogger(OmcAvoidScoutBusiImpl.class);
	
	@Autowired
	private IOmcAvoidScoutAtom omcAvoidScoutAtom;
	
	@Override
	public BaseResponse addAvoidScout(OmcAvoidScoutInfoVO vo) {
		if (null == vo) {
			log.debug("------>>>addAvoidScout() vo = [null]");
			return null;
		} else {
			log.debug("------>>>addAvoidScout() vo = " + vo.toString() + "]");
		}
		BaseResponse response = new BaseResponse();
		ResponseHeader responseHeader = new ResponseHeader();
		//check除了时间类型的其他输入
		checkInputData(vo);
		//check时间类型的输入
		checkInputDate(vo);
		//check特殊处理类型
		checkSpeType(vo.getSpecialType());
		//数据库操作
		try {
			OmcAvoidScout avoidScout = getAvoidScoutByVO(vo);
			avoidScout.setAvoidSeq(OmcSeqUtil.getAvoidSeq());
			//数据库中查看该数据内容是否重复存在
			List<OmcAvoidScout> lstData = omcAvoidScoutAtom.queryByData(avoidScout);
			if (!CollectionUtil.isEmpty(lstData)) {
				String msg = "新增失败：OmcAvoidScout数据库中已存在该数据内容";
				log.info(msg);
				throw new BusinessException(msg);
				
		    }else{
				omcAvoidScoutAtom.addOmcAvoidScout(avoidScout);
				omcAvoidScoutAtom.addDshmData(avoidScout);
				responseHeader.setResultCode(ExceptCodeConstant.SUCCESS);
				responseHeader.setIsSuccess(true);
				responseHeader.setSuccess(true);
				responseHeader.setResultMessage("新增成功！");
				log.info("-------------->添加成功！！！");
		    }
		} catch (Exception e) {
		    log.error(e);
		    throw new BusinessException(ExceptCodeConstant.FALSE + e.getMessage(), e);
		}
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public BaseResponse updAvoidScout(OmcAvoidScoutInfoVO vo) {
		// TODO Auto-generated method stub
		BaseResponse response = new BaseResponse();
		ResponseHeader responseHeader = new ResponseHeader();
		try{
			//check除了时间类型的其他输入
			checkInputData(vo);
			//check时间类型的输入
			checkInputDate(vo);
			//check特殊处理类型
			checkSpeType(vo.getSpecialType());
			
			OmcAvoidScout avoidScout = getAvoidScoutByVO(vo);
			//数据库中查看该主键数据是否存在
			List<OmcAvoidScout> lstData = omcAvoidScoutAtom.queryByData(avoidScout);
			if (CollectionUtil.isEmpty(lstData)) {
				String msg = "更新失败：OmcAvoidScout数据库中不存在该条数据";
				log.info(msg);
				throw new BusinessException(msg);
		    }else{
		    	avoidScout.setAvoidSeq(lstData.get(0).getAvoidSeq());
				omcAvoidScoutAtom.updateOmcAvoidScout(avoidScout);
				omcAvoidScoutAtom.updDshmData(avoidScout);
				responseHeader.setResultCode(ExceptCodeConstant.SUCCESS);
				responseHeader.setIsSuccess(true);
				responseHeader.setSuccess(true);
				responseHeader.setResultMessage("更新成功！");
		    }
		} catch (Exception e) {
		    log.error(e);
		    throw new BusinessException(ExceptCodeConstant.FALSE + e.getMessage(), e);
		}
		response.setResponseHeader(responseHeader);
		return response;
	}

	@Override
	public BaseResponse delAvoidScout(OmcAvoidScoutInfoVO vo) {
		BaseResponse BaseResponse = new BaseResponse();
		ResponseHeader responseHeader = new ResponseHeader();
		try{
			//输入数据check
			checkInputData(vo);
			//check特殊处理类型
			checkSpeType(vo.getSpecialType());
			// TODO Auto-generated method stub
			OmcAvoidScout avoidScout = getAvoidScoutByVO(vo);
			//数据库中查看该主键数据是否存在
			List<OmcAvoidScout> lstData = omcAvoidScoutAtom.queryByData(avoidScout);
			if (CollectionUtil.isEmpty(lstData)) {
				String msg = "删除失败：OmcAvoidScout数据库中不存在该条数据";
				log.info(msg);
				throw new BusinessException(ExceptCodeConstant.NOEXIST + ":" + msg);
		    }else{
		    	avoidScout.setAvoidSeq(lstData.get(0).getAvoidSeq());
		    	avoidScout.setEffDate(lstData.get(0).getEffDate());
		    	avoidScout.setExpDate(DateUtil.getSysDate());
		    	//omcAvoidScoutAtom.deleteOmcAvoidScout(avoidScout);
		    	omcAvoidScoutAtom.updateOmcAvoidScout(avoidScout);
		    	//omcAvoidScoutAtom.delDshmData(avoidScout);
		    	omcAvoidScoutAtom.updDshmData(avoidScout);
		    	responseHeader.setResultCode(ExceptCodeConstant.SUCCESS);
		    	responseHeader.setIsSuccess(true);
				responseHeader.setSuccess(true);
				responseHeader.setResultMessage("删除成功！");
		    }
			
		} catch (Exception e) {
		    log.error(e);
		    throw new BusinessException(ExceptCodeConstant.FALSE + e.getMessage(), e);
		}
		BaseResponse.setResponseHeader(responseHeader);
		return BaseResponse;
	}

	private OmcAvoidScout getAvoidScoutByVO(OmcAvoidScoutInfoVO vo) {
		OmcAvoidScout avoidScout = new OmcAvoidScout();
		avoidScout.setTenantId(vo.getTenantId());
		
		Map<String, String> blCustinfo = AccquireDshm.getBlCustinfo(vo.getExtCustId(),vo.getTenantId());
		Map<String, String> blUserinfo = AccquireDshm.getBlUserinfo(blCustinfo.get(CustInfo.CUST_ID),vo.getTenantId());
		
		avoidScout.setOwnerId(blUserinfo.get(UserInfo.SUBS_ID));
		avoidScout.setOwnerType(TableCon.OWNER_TYPE_SUBS);
		avoidScout.setSpeType(vo.getSpecialType().toUpperCase());
		
		if(!StringUtils.isEmpty(vo.getActiveTime()) && !StringUtils.isEmpty(vo.getInactiveTime())){
			SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMddhhmmss");
			try {
				avoidScout.setEffDate(new Timestamp(sf1.parse(vo.getActiveTime()).getTime()));
				avoidScout.setExpDate(new Timestamp(sf1.parse(vo.getInactiveTime()).getTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				throw new BusinessException(e);
			}
		}
		
		return avoidScout;
	}
	
	private void checkInputData(OmcAvoidScoutInfoVO vo){
		String result = CheckUtil.check(vo.getTenantId(), "租户ID", false, 32);
        if (!ExceptCodeConstant.SUCCESS.equals(result)) {
        	throw new BusinessException(result);
        }
        
        result = CheckUtil.check(vo.getExtCustId(), "外部客户ID", false, 64);
        if (!ExceptCodeConstant.SUCCESS.equals(result)) {
        	throw new BusinessException(result);
        }
		
        result = CheckUtil.check(vo.getSpecialType(), "特殊处理类型", false, 32);
        if (!ExceptCodeConstant.SUCCESS.equals(result)) {
        	throw new BusinessException(result);
        }
	}
	
	private void checkInputDate(OmcAvoidScoutInfoVO vo){
		String result = CheckUtil.check(vo.getActiveTime(), "生效日期", false, 14);
        if (!ExceptCodeConstant.SUCCESS.equals(result)) {
        	throw new BusinessException(result);
        }
        
		if(!CheckUtil.check(vo.getActiveTime(), "yyyyMMddhhmmss")){
			throw new BusinessException(ExceptCodeConstant.UNFORMATE + ":生效日期格式错误，正确格式应为：YYYYMMDDHH24MISS");
		}
		
		result = CheckUtil.check(vo.getInactiveTime(), "失效日期", false, 14);
        if (!ExceptCodeConstant.SUCCESS.equals(result)) {
        	throw new BusinessException(result);
        }
		
		if(!CheckUtil.check(vo.getInactiveTime(), "yyyyMMddhhmmss")){
        	throw new BusinessException(ExceptCodeConstant.UNFORMATE + ":失效日期格式错误，正确格式应为：YYYYMMDDHH24MISS");
		}
	}
	
	private void checkSpeType(String speType){
		if(!speType.equalsIgnoreCase(TableCon.SpeType.SPETYPE_STOP.toString()) &&
				!speType.equalsIgnoreCase(TableCon.SpeType.SPETYPE_URGE.toString()) &&
				!speType.equalsIgnoreCase(TableCon.SpeType.SPETYPE_STOPANDURGE.toString())){
			throw new BusinessException(ExceptCodeConstant.FALSE + ":特殊处理类型填写错误");
		}
	}
}
