package com.ai.baas.pkgfee.service.atom.interfaces;

import java.util.*;
import com.ai.baas.pkgfee.dao.mapper.bo.BlSubsComm;

/**
 * BlSubsComm接口
 *
 * Date: 2017年2月27日 <br>
 * Copyright (c) 2017 asiainfo.com <br>
 * @author wangjing19
 */
public interface IBlSubsCommAtomSV {

	/**
	 * 根据TENANT_ID获取BlSubsComm信息
	 * @param blSubsComm
	 * @return
	 */
	public List<BlSubsComm> getBlSubsComm(BlSubsComm blSubsComm, Date inactiveMore, Date activeLess);

}
