package com.ai.baas.bmc.service.business.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.net.ntp.TimeStamp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.bmc.api.citic.params.InstanceInfo;
import com.ai.baas.bmc.dao.mapper.bo.BlSubsComm;
import com.ai.baas.bmc.dao.mapper.bo.BlUserinfoZx;
import com.ai.baas.bmc.dao.mapper.bo.BlUserinfoZxBak;
import com.ai.baas.bmc.dao.mapper.bo.CpPriceInfo;
import com.ai.baas.bmc.service.atom.interfaces.IBlSubsCommAtomSV;
import com.ai.baas.bmc.service.atom.interfaces.IBlSubsCommonAtomSV;
import com.ai.baas.bmc.service.atom.interfaces.IBlUserinfoZXAtomSV;
import com.ai.baas.bmc.service.atom.interfaces.IBlUserinfoZXBakAtomSV;
import com.ai.baas.bmc.service.atom.interfaces.ICpPriceInfoAtom;
import com.ai.baas.bmc.service.business.interfaces.IorderManageBusiSV;
import com.ai.baas.bmc.util.DshmUtil;
import com.ai.opt.sdk.util.BeanUtils;
import com.alibaba.fastjson.JSON;

@Component
@Transactional
public class OrderManageBusiSVImpl implements IorderManageBusiSV {
	private static final Logger logger = LogManager.getLogger(OrderManageBusiSVImpl.class);
	@Autowired
	private IBlUserinfoZXAtomSV iBlUserinfoZXAtomSV;
	@Autowired
	private IBlSubsCommonAtomSV iBlSubsCommonAtomSV;
	@Autowired
	private IBlSubsCommAtomSV iBlSubsCommAtomSV;
	@Autowired
	ICpPriceInfoAtom iCpPriceInfoAtom;
	@Autowired
	private IBlUserinfoZXBakAtomSV iBlUserinfoZXBakAtomSV;

	@Override
	public String OrderUpdate(InstanceInfo info) {
		List<BlUserinfoZx> zxList = new ArrayList<>();
		zxList = iBlUserinfoZXAtomSV.getUserInfoZx(info.getInstanceId());
		if (zxList.size() == 0) {
			logger.error("未找到instance_id :" + info.getInstanceId() + " 对应的信息");
			return "error";
		}

		BlUserinfoZx param = new BlUserinfoZx();
		param = zxList.get(0);
		BlUserinfoZxBak bak = new BlUserinfoZxBak();
		BeanUtils.copyProperties(bak, param);
		String productIds = param.getProductId();
		
		try {
			String[] priceCodes = productIds.split(";");
			for (String p : priceCodes) {
				logger.error("找到priceCode:" + p + " 对应的信息");

				disableOrder(p,info.getInactiveTime());
			//	disableProduct(p);
			}
			logger.info("备份bluserinfo信息");
			iBlUserinfoZXBakAtomSV.addUserInfoBak(bak);
			
			iBlUserinfoZXAtomSV.deleteByInstanceId(param.getInstanceId());
			DelBlUserInfoZX(param);
		} catch (Exception e) {
			logger.error("修改失败！", e);
			return "error";
		}
		return "success";
	}

	/**
	 * 
	 * 使得使用信息失效
	 * 
	 * @param p
	 */
	private String disableOrder(String p,Timestamp inactiveTime) {
	  // Timestamp inactiveTime = new Timestamp(System.currentTimeMillis());
		BlSubsComm blSubsComm = new BlSubsComm();
		List<BlSubsComm> blSubsCommList = new ArrayList<>();
		blSubsCommList = iBlSubsCommonAtomSV.getSubsCommonByProductId(p);
        if(blSubsCommList.size()==0){
            logger.info("productId: "+p+"的订购信息不存在");
            return null;
        }
		blSubsComm = blSubsCommList.get(0);
		logger.info("原始订购数据: " + JSON.toJSONString(blSubsComm));
		blSubsComm.setInactiveTime(inactiveTime);
		logger.info("修改失效时间: " + JSON.toJSONString(blSubsComm));
		iBlSubsCommonAtomSV.updateSubsCommon(p, blSubsComm);
		iBlSubsCommAtomSV.updateDshmData(blSubsComm);
		return p;
	}

	/**
	 * 使得产品信息失效
	 * 
	 * @param p
	 */
	private void disableProduct(String p) {
		Timestamp inactiveTime = new Timestamp(System.currentTimeMillis());
		CpPriceInfo priceInfo = new CpPriceInfo();
		priceInfo.setPriceCode(p);
		priceInfo.setInactiveTime(inactiveTime);
		iCpPriceInfoAtom.updatePriceInfoByPriceCode(priceInfo);

	}
	
	 public void DelBlUserInfoZX(BlUserinfoZx blUserinfoZx) {
	        JSONObject json = new JSONObject();
	        SimpleDateFormat dfor=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        
	        json.put("create_time", dfor.format(blUserinfoZx.getCreateTime()));
	        json.put("instance_id", blUserinfoZx.getInstanceId());
	        json.put("service_id", blUserinfoZx.getServiceId());
	        json.put("user_id", blUserinfoZx.getUserId());
	        //DshmUtil.getIdshmSV().initLoader("bl_userinfo_zx", json.toString(),0); 
	        DshmUtil.getIdshmSV().initdel("bl_userinfo_zx", json.toString()); 
	    }

}
