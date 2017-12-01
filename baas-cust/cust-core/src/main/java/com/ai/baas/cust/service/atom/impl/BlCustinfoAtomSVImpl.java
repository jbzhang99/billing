package com.ai.baas.cust.service.atom.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.cust.api.custinfo.params.BlCustinfoParams;
import com.ai.baas.cust.api.custinfo.params.ExtCustParams;
import com.ai.baas.cust.api.custinfo.params.QueryCustInfoRequest;
import com.ai.baas.cust.api.initbasedata.params.InitBaseParam;
import com.ai.baas.cust.constants.BmcCacheConstant;
import com.ai.baas.cust.constants.BmcConstants;
import com.ai.baas.cust.dao.interfaces.BlCustinfoMapper;
import com.ai.baas.cust.dao.mapper.bo.BlCustinfo;
import com.ai.baas.cust.dao.mapper.bo.BlCustinfoCriteria;
import com.ai.baas.cust.service.atom.interfaces.IBlCustinfoAtomSV;
import com.ai.baas.cust.util.BusinessUtil;
import com.ai.baas.cust.util.DshmUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.fastjson.JSON;

import net.sf.json.JSONObject;

@Component
public class BlCustinfoAtomSVImpl implements IBlCustinfoAtomSV {

    @Override
    public void addDshmData(BlCustinfo blCustinfo) {
        int result = DshmUtil.getIdshmSV().initLoader(BmcCacheConstant.Dshm.TableName.BL_CUSTINFO,
                JSON.toJSONString(BusinessUtil.assebleDshmData(blCustinfo)),
                BmcCacheConstant.Dshm.OptType.INSERT);// redis 0更新 1插入
        if (BmcCacheConstant.Dshm.InitLoaderReault.SUCCESS != result) {
            throw new BusinessException("客户信息写入缓存失败");
        }
    }

    @Autowired
	private BlCustinfoMapper blCustinfoMapper;
	@Override
	public List<BlCustinfo> getCustInfos(QueryCustInfoRequest param) {
		BlCustinfoCriteria sql =new BlCustinfoCriteria();
		BlCustinfoCriteria.Criteria criteria=sql.createCriteria();
		criteria.andTenantIdEqualTo(param.getTenantId());
		criteria.andStateEqualTo(BmcConstants.CUST.NORMAL);
		if(!StringUtil.isBlank(param.getCustGrade())){
			criteria.andCustGradeEqualTo(param.getCustGrade());
		}
		if(!StringUtil.isBlank(param.getCustName())){
			criteria.andCustNameLike("%"+param.getCustName()+"%");
		}
		if(!StringUtil.isBlank(param.getIdNumber())){
			criteria.andIdNumberEqualTo(param.getIdNumber());
		}
		/*if(param.getPageNo()!=null&&param.getPageSize()!=null){
			sql.setLimitStart((param.getPageNo()-1)*param.getPageSize());
			sql.setLimitEnd(param.getPageSize());
		}*/

		return blCustinfoMapper.selectByExample(sql);
	}
	@Override
	public int getCustInfoCount(QueryCustInfoRequest param) {
		BlCustinfoCriteria sql =new BlCustinfoCriteria();
		BlCustinfoCriteria.Criteria criteria=sql.createCriteria();
		criteria.andTenantIdEqualTo(param.getTenantId());
		if(!StringUtil.isBlank(param.getCustGrade())){
			criteria.andCustGradeEqualTo(param.getCustGrade());
		}
		if(!StringUtil.isBlank(param.getCustName())){
			criteria.andCustNameLike("%"+param.getCustName()+"%");
		}
		if(!StringUtil.isBlank(param.getIdNumber())){
			criteria.andIdNumberEqualTo(param.getIdNumber());
		}

		return blCustinfoMapper.countByExample(sql);
	}
	@Override
	public int custInfoInsert(InitBaseParam param) {
		BlCustinfo custInfo=new BlCustinfo();
		custInfo.setCustId(param.getCustId());
		custInfo.setCustType("G");
		custInfo.setExtCustId(param.getExtCustId());
		custInfo.setState("NORMAL");
		custInfo.setStateChgTime(DateUtil.getSysDate());
		custInfo.setTenantId(param.getTenantId());
		custInfo.setCustName("NULL");
		if(blCustinfoMapper.insert(custInfo)!=0){
			//开始向缓存中刷新
			JSONObject json=new JSONObject();
			json.put("tenant_id", custInfo.getTenantId());
			json.put("cust_id", custInfo.getCustId());
			json.put("cust_name", custInfo.getCustName());
			json.put("ext_cust_id", custInfo.getExtCustId());
			json.put("cust_type", custInfo.getCustType());
			json.put("cust_grade", custInfo.getCustGrade());
			json.put("province_code", custInfo.getProvinceCode());
			json.put("city_code", custInfo.getCityCode());
			json.put("state", custInfo.getState());
			json.put("state_chg_time", custInfo.getStateChgTime().toString());
			json.put("remark", custInfo.getRemark());
			json.put("contact_no", custInfo.getContactNo());
			json.put("email", custInfo.getEmail());
			json.put("cust_address", custInfo.getCustAddress());
			json.put("id_number", custInfo.getIdNumber());
			return DshmUtil.getIdshmSV().initLoader("bl_custinfo", json.toString(),1);
		}
		return 0;
	}
	@Override
	public List<BlCustinfo> getCustInfosByParams(BlCustinfoParams param) {
		// TODO Auto-generated method stub
		BlCustinfoCriteria sql =new BlCustinfoCriteria();
		BlCustinfoCriteria.Criteria criteria=sql.createCriteria();
		if(!StringUtil.isBlank(param.getCustId())){
			criteria.andCustIdEqualTo(param.getCustId());
		}
		if(!StringUtil.isBlank(param.getEmail())){
			criteria.andEmailEqualTo(param.getEmail());
		}
		if(!StringUtil.isBlank(param.getPolicyId())){
			criteria.andPolicyIdEqualTo(param.getPolicyId());
		}
		if(!StringUtil.isBlank(param.getState())){
			criteria.andStateEqualTo(param.getState());
		}
		if(!StringUtil.isBlank(param.getCustGrade())){
			criteria.andCustGradeEqualTo(param.getCustGrade());
		}
		if(!StringUtil.isBlank(param.getCustName())){
			criteria.andCustNameLike("%"+param.getCustName()+"%");
		}
		if(param.getExtCustIds()!=null){
			criteria.andExtCustIdIn(param.getExtCustIds());
		}
		if(param.getCustType()!=null){
			criteria.andCustTypeEqualTo(param.getCustType());
		}
		criteria.andTenantIdEqualTo(param.getTenantId());
		
		return blCustinfoMapper.selectByExample(sql);
	}
	@Override
	public void setPolicyId(ExtCustParams custInfo) {
		// TODO Auto-generated method stub
		BlCustinfo record = new BlCustinfo();
		record.setPolicyId(custInfo.getPolicyId());
		
		BlCustinfoCriteria sql =new BlCustinfoCriteria();
		BlCustinfoCriteria.Criteria criteria=sql.createCriteria();
		criteria.andTenantIdEqualTo(custInfo.getTenantId());
		criteria.andCustIdEqualTo(custInfo.getCustId());
		criteria.andExtCustIdEqualTo(custInfo.getExtCustId());
		blCustinfoMapper.updateByExampleSelective(record, sql);
	}

}
