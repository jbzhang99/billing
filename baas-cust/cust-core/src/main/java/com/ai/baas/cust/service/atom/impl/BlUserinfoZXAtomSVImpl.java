package com.ai.baas.cust.service.atom.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.cust.dao.interfaces.BlUserinfoZxMapper;
import com.ai.baas.cust.dao.mapper.bo.BlUserinfoZx;
import com.ai.baas.cust.dao.mapper.bo.BlUserinfoZxCriteria;
import com.ai.baas.cust.service.atom.interfaces.IBlUserinfoZXAtomSV;

@Component
public class BlUserinfoZXAtomSVImpl implements IBlUserinfoZXAtomSV {

    @Autowired
    private BlUserinfoZxMapper blUserinfoZxMapper;
    
    @Override
    public List<BlUserinfoZx> getUserInfoZx(String instanceId) {
        BlUserinfoZxCriteria sql=new BlUserinfoZxCriteria();
        BlUserinfoZxCriteria.Criteria creteria=sql.createCriteria();
        creteria.andInstanceIdEqualTo(instanceId);
 
        return blUserinfoZxMapper.selectByExample(sql);
    }

	@Override
	public void deleteByInstanceId(String id) {
		
		blUserinfoZxMapper.deleteByPrimaryKey(id);
	}
}
