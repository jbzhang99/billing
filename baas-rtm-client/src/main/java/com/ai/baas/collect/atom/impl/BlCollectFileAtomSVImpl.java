package com.ai.baas.collect.atom.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ai.baas.collect.atom.interfaces.IBlCollectFileAtomSV;
import com.ai.baas.collect.dao.bo.BlCollectFiles;
import com.ai.baas.collect.dao.interfaces.BlCollectFilesMapper;

@Component
public class BlCollectFileAtomSVImpl implements IBlCollectFileAtomSV{
	
	@Autowired
	BlCollectFilesMapper blCollectFilesMapper;

	@Override
	@Transactional(readOnly = true)
	public int selectCountByName(String tenantid, String fileName) {
		// TODO Auto-generated method stub
		
		return this.blCollectFilesMapper.selectCountByName(tenantid, fileName);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveCollectFile(BlCollectFiles blCollectFiles) {
		// TODO Auto-generated method stub
		this.blCollectFilesMapper.insertSelective(blCollectFiles);
		return;
	}

}
