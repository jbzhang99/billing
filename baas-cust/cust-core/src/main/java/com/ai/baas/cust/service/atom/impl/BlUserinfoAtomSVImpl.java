package com.ai.baas.cust.service.atom.impl;

import com.ai.baas.cust.api.custinfo.params.UserInfoRequest;
import com.ai.baas.cust.api.initbasedata.params.InitBaseParam;
import com.ai.baas.cust.constants.BmcCacheConstant;
import com.ai.baas.cust.dao.interfaces.BlUserinfoMapper;
import com.ai.baas.cust.dao.mapper.bo.BlUserinfo;
import com.ai.baas.cust.dao.mapper.bo.BlUserinfoCriteria;
import com.ai.baas.cust.service.atom.interfaces.IBlUserinfoAtomSV;
import com.ai.baas.cust.util.BusinessUtil;
import com.ai.baas.cust.util.DshmUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.util.DateUtil;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class BlUserinfoAtomSVImpl implements IBlUserinfoAtomSV {

	@Autowired
	private BlUserinfoMapper blUserinfoMapper;

	@Override
	public int getUserInfoCount(UserInfoRequest param) {
		BlUserinfoCriteria sql=new BlUserinfoCriteria();
		BlUserinfoCriteria.Criteria creteria=sql.createCriteria();
		creteria.andTenantIdEqualTo(param.getTenantId());
		creteria.andCustIdEqualTo(param.getCustId());
		if(!StringUtils.isEmpty(param.getServiceId())){
			creteria.andServiceIdEqualTo(param.getServiceId());
		}

		return blUserinfoMapper.countByExample(sql);
	}
	@Override
	public List<BlUserinfo> getUserInfo(UserInfoRequest param) {
		BlUserinfoCriteria sql=new BlUserinfoCriteria();
		BlUserinfoCriteria.Criteria creteria=sql.createCriteria();
		creteria.andTenantIdEqualTo(param.getTenantId());
		creteria.andCustIdEqualTo(param.getCustId());
		if(!StringUtils.isEmpty(param.getServiceId())){
			creteria.andServiceIdEqualTo(param.getServiceId());
		}
		if(param.getPageSize()!=null&&param.getPageNo()!=null){
			sql.setLimitStart((param.getPageNo()-1)*param.getPageSize());
			sql.setLimitEnd(param.getPageSize());
		}
		return blUserinfoMapper.selectByExample(sql);
	}
	@Override
	public int userInfoInsert(InitBaseParam param) {
		BlUserinfo userinfo=new BlUserinfo();
		userinfo.setAcctId(param.getAcctId());
		userinfo.setCustId(param.getCustId());
		userinfo.setServiceId(param.getServiceId());
		userinfo.setSubsId(param.getSubsId());
		userinfo.setTenantId(param.getTenantId());
		userinfo.setUserState("NORMAL");
		userinfo.setUserType("NORMAL");
		userinfo.setDealTime(DateUtil.getSysDate());
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
		Date dDate=null;
		try{
			dDate=dateFormat.parse("20160101120000");
			Timestamp activeTime=new Timestamp(dDate.getTime());
			userinfo.setActiveTime(activeTime);
			dDate=dateFormat.parse("20990101120000");
			Timestamp inactiveTime=new Timestamp(dDate.getTime());
			userinfo.setInactiveTime(inactiveTime);
		}catch(ParseException e){
			throw new SystemException("时间格式转换异常");
		}

		if(blUserinfoMapper.insert(userinfo)!=0){
			//开始进行缓存的刷新
			JSONObject json = new JSONObject();
			json.put("tenant_id",userinfo.getTenantId());
			json.put("subs_id", userinfo.getSubsId());
			json.put("cust_id", userinfo.getCustId());
			json.put("acct_id", userinfo.getAcctId());
			json.put("service_id", userinfo.getServiceId());
			json.put("deal_time", userinfo.getDealTime().toString());
			json.put("active_time", userinfo.getActiveTime().toString());
			json.put("inactive_time", userinfo.getInactiveTime().toString());
			json.put("province_code", userinfo.getProvinceCode());
			json.put("city_code", userinfo.getCityCode());
			json.put("chl_id", userinfo.getChlId());
			json.put("dev_id", userinfo.getDevId());
			json.put("serv_type", userinfo.getServType());
			json.put("user_type", userinfo.getUserType());
			json.put("user_state", userinfo.getUserState());
			json.put("policy_id", userinfo.getPolicyId());
			return DshmUtil.getIdshmSV().initLoader("bl_userinfo", json.toString(),1);
		}
		else
			return 0;
	}

    @Override
    public void addDshmData(BlUserinfo blUserinfo) {
        int result = DshmUtil.getIdshmSV().initLoader(BmcCacheConstant.Dshm.TableName.BL_USERINFO,
                JSON.toJSONString(BusinessUtil.assebleDshmData(blUserinfo)),
                BmcCacheConstant.Dshm.OptType.INSERT); // redis 0更新 1插入
        if (BmcCacheConstant.Dshm.InitLoaderReault.SUCCESS != result) {
            throw new BusinessException("用户信息写入缓存失败");
        }
    }
}
