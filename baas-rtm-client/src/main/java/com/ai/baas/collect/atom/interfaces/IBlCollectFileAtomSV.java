package com.ai.baas.collect.atom.interfaces;

import com.ai.baas.collect.dao.bo.BlCollectFiles;

/**
 * 
 * Copyright: Copyright (c) 2016 Asiainfo
 * 
 * @ClassName: IBlCollectFileAtomSV.java
 * @Description: 访问采集记录表
 *
 * @version: v1.0.0
 * @author: hanzf
 * @date: 2017年3月20日 下午3:26:56
 *
 * Modification History:
 * Date             Author          Version            Description
 *---------------------------------------------------------*
 * 2017年3月20日     hanzf           v1.0.0               创建
 */
public interface IBlCollectFileAtomSV {
	
	public int selectCountByName(String tenantid,String fileName);
	
	public void saveCollectFile(BlCollectFiles blCollectFiles);

}
