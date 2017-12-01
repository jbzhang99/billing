package com.ai.baas.pkgfee.service.atom.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.pkgfee.dao.mapper.interfaces.BlSubsCommMapper;
import com.ai.baas.pkgfee.dao.mapper.bo.BlSubsComm;
import com.ai.baas.pkgfee.dao.mapper.bo.BlSubsCommCriteria;
import com.ai.baas.pkgfee.dao.mapper.factory.MapperFactory;
import com.ai.baas.pkgfee.service.atom.interfaces.IBlSubsCommAtomSV;
import com.ai.opt.base.exception.SystemException;

@Component
public class BlSubsCommAtomSVImpl implements IBlSubsCommAtomSV{

	@Override
	public List<BlSubsComm> getBlSubsComm(BlSubsComm blSubsComm, Date inactiveMore, Date activeLess) throws SystemException {
		// TODO Auto-generated method stub
		BlSubsCommMapper subsCommMapper = MapperFactory.getBlSubsCommInfoMapper();
		BlSubsCommCriteria subsCommCriteria = new BlSubsCommCriteria();
	    
		Timestamp tempInavctive = new Timestamp(inactiveMore.getTime());
		Timestamp tempAvctive = new Timestamp(activeLess.getTime());
		
		//根据资费编码获取包年包月一条记录
		subsCommCriteria.createCriteria()
        .andTenantIdEqualTo(blSubsComm.getTenantId())
        .andActiveTimeLessThanOrEqualTo(tempAvctive)   //产品的有效性检查
        .andInactiveTimeGreaterThanOrEqualTo(tempInavctive);    //产品的有效性检查
        
        List<BlSubsComm> dataList = subsCommMapper.selectByExample(subsCommCriteria);
        return dataList;
	}

}
