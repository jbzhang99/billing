package com.ai.baas.cust.service.atom.interfaces;

import java.util.List;

import com.ai.baas.cust.api.custinfo.params.UserInfoRequest;
import com.ai.baas.cust.api.initbasedata.params.InitBaseParam;
import com.ai.baas.cust.dao.mapper.bo.BlUserinfo;

public interface IBlUserinfoAtomSV {

	int getUserInfoCount(UserInfoRequest param);
	List<BlUserinfo> getUserInfo(UserInfoRequest param);
	int userInfoInsert(InitBaseParam param);

	void addDshmData(BlUserinfo blUserinfo);
}
