package com.ai.baas.amc.service.business.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ai.baas.amc.api.amcdrbillsubject.param.AmcDrBillSubjectRelatedParamVO;
import com.ai.baas.amc.api.amcdrbillsubject.param.DrSubjectParamVO;
import com.ai.baas.amc.dao.mapper.bo.AmcDrBillSubjectMap;
import com.ai.baas.amc.service.atom.interfaces.IAmcDrBillSubjectAtomSV;
import com.ai.baas.amc.service.business.interfaces.IAmcDrBillSubjectBusiSV;
import com.ai.opt.sdk.util.CollectionUtil;
@Service
public class AmcDrBillSubjectBusiSVImpl implements IAmcDrBillSubjectBusiSV {
	@Autowired
	private IAmcDrBillSubjectAtomSV iAmcDrBillSubjectAtomSV;
	@Override
	public List<String> queryDrSubjectIdByTenantIdAndBillSubjectId(String tenantId, String billSubjectId) {
		AmcDrBillSubjectMap amcDrBillSubjectMap = new AmcDrBillSubjectMap();
		//
		amcDrBillSubjectMap.setBillSubject(billSubjectId);
		amcDrBillSubjectMap.setTenantId(tenantId);
		//
		List<AmcDrBillSubjectMap> amcDrBillSubjectMapList = this.iAmcDrBillSubjectAtomSV.queryAmcDrBillSubjectMap(amcDrBillSubjectMap);
		//
		String[] drSubjectIdList = new String[]{};
		//
		if(!CollectionUtil.isEmpty(amcDrBillSubjectMapList)){
			for(AmcDrBillSubjectMap amcDrBillSubjectMapDb : amcDrBillSubjectMapList){
				//
				drSubjectIdList = StringUtils.addStringToArray(drSubjectIdList, amcDrBillSubjectMapDb.getDrSubject());
				
			}
			 
		} 
		return Arrays.asList(drSubjectIdList);
	}
	@Override
	public void delAmcDrBillSubject(AmcDrBillSubjectRelatedParamVO vo) {
		AmcDrBillSubjectMap amcDrBillSubjectMap = null;
		if(!CollectionUtil.isEmpty(vo.getDrSubjectParamVOList())){
			//
			for(DrSubjectParamVO drSubjectParamVO : vo.getDrSubjectParamVOList()){
				amcDrBillSubjectMap = new AmcDrBillSubjectMap();
				amcDrBillSubjectMap.setTenantId(vo.getTenantId());
				amcDrBillSubjectMap.setBillSubject(vo.getBillSubjectId());
				amcDrBillSubjectMap.setDrSubject(drSubjectParamVO.getDrSubjectId());
				//
				this.iAmcDrBillSubjectAtomSV.delAmcDrBillSubject(amcDrBillSubjectMap);
			}
		}
	}
	@Override
	public void addAmcDrBillSubject(AmcDrBillSubjectRelatedParamVO vo) {
		AmcDrBillSubjectMap amcDrBillSubjectMap = null;
		if(!CollectionUtil.isEmpty(vo.getDrSubjectParamVOList())){
			//
			for(DrSubjectParamVO drSubjectParamVO : vo.getDrSubjectParamVOList()){
				amcDrBillSubjectMap = new AmcDrBillSubjectMap();
				amcDrBillSubjectMap.setTenantId(vo.getTenantId());
				amcDrBillSubjectMap.setBillSubject(vo.getBillSubjectId());
				amcDrBillSubjectMap.setDrSubject(drSubjectParamVO.getDrSubjectId());
				//
				this.iAmcDrBillSubjectAtomSV.addAmcDrBillSubject(amcDrBillSubjectMap);
			}
		}
		
	}

}
