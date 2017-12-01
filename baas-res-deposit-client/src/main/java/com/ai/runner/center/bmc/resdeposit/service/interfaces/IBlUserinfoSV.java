package com.ai.runner.center.bmc.resdeposit.service.interfaces;

import java.util.List;
import com.ai.runner.center.bmc.resdeposit.dao.mapper.bo.BlUserinfo;

/**
 * Date: 2016年5月4日 <br>
 * @author zhoushanbin
 * Copyright (c) 2016 asiainfo.com <br>
 */
public interface IBlUserinfoSV {
	
	List<BlUserinfo> querUserinfos(String tenantId , List<String> subsIds);	
	
}
