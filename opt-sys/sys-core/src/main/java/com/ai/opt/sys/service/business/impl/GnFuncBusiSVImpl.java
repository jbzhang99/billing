package com.ai.opt.sys.service.business.impl;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sys.api.gnfunc.param.QueryGnFuncInfoRequest;
import com.ai.opt.sys.api.gnfunc.param.QueryGnFuncPageRequest;
import com.ai.opt.sys.dao.mapper.bo.GnFunc;
import com.ai.opt.sys.dao.mapper.bo.GnFuncCriteria;
import com.ai.opt.sys.dao.mapper.bo.GnFuncCriteria.Criteria;
import com.ai.opt.sys.service.atom.interfaces.IGnFuncAtomSV;
import com.ai.opt.sys.service.atom.interfaces.IGnRoleFuncRelAtomSV;
import com.ai.opt.sys.service.business.interfaces.IGnFuncBusiSV;
import com.ai.paas.ipaas.util.StringUtil;

@Service
@Transactional
public class GnFuncBusiSVImpl implements IGnFuncBusiSV {
	@Autowired
	private IGnFuncAtomSV iGnFuncAtomSV;
	@Autowired
	private IGnRoleFuncRelAtomSV iGnRoleFuncRelSV;

	@Override
	public PageInfo<GnFunc> queryFuncInfo(QueryGnFuncPageRequest request) {
		GnFuncCriteria sql = new GnFuncCriteria();
		GnFuncCriteria.Criteria criteria = sql.createCriteria();
		if (!StringUtil.isBlank(request.getFuncType())) {
			criteria.andFuncTypeEqualTo(request.getFuncType());
		}
		if (!StringUtil.isBlank(request.getFuncName())) {
			criteria.andFuncNameEqualTo(request.getFuncName());
		}
		PageInfo<GnFunc> pageInfo = new PageInfo<GnFunc>();
		Integer pageNo = request.getPageNo();
		Integer pageSize = request.getPageSize();
		int funcCount = iGnFuncAtomSV.queryFuncCount(sql);
		pageInfo.setCount(funcCount);
		sql.setLimitStart((pageNo - 1) * pageSize);
		sql.setLimitEnd(pageSize);
		List<GnFunc> queryFuncInfoList = iGnFuncAtomSV.queryFuncInfoList(sql);
		pageInfo.setResult(queryFuncInfoList);
		pageInfo.setPageNo(pageNo);
		pageInfo.setPageSize(pageSize);
		return pageInfo;
	}

	@Override
	public GnFunc queryFuncInfo(QueryGnFuncInfoRequest queryRequest) {
		return iGnFuncAtomSV.queryFuncById(queryRequest.getFuncId());
	}

	@Override
	public int updateFuncById(GnFunc func) throws SystemException {
		Timestamp updateTime = func.getUpdateTime();
		if (updateTime != null) {
			func.setUpdateTime(DateUtil.getSysDate());
		}
		return iGnFuncAtomSV.updateFuncById(func);
	}

	@Override
	public int insertFunc(GnFunc func) throws SystemException {
		return iGnFuncAtomSV.insertFunc(func);
	}

	@Override
	public int deleteFuncById(long funcId) throws BusinessException {
		int deleteResult = iGnFuncAtomSV.deleteFuncById(funcId);
		if (deleteResult > 0) {
			List<Long> childerIdList = getChilderFuncIdList(funcId);
			if (childerIdList != null && childerIdList.size() > 0) {
				int deleteChilderCount = iGnFuncAtomSV.deleteFuncByListId(childerIdList);
				deleteResult = +deleteChilderCount;
				iGnRoleFuncRelSV.deleteFuncRole(funcId,null);
			}
		}
		return deleteResult;
	}

	@Override
	public List<GnFunc> queryChilderFuncInfo(long funcId) throws BusinessException {
		List<Long> childerFuncIdList = getChilderFuncIdList(funcId);
		GnFuncCriteria FuncCriteria = new GnFuncCriteria();
		Criteria createCriteria = FuncCriteria.createCriteria();
		createCriteria.andFuncIdIn(childerFuncIdList);
		return iGnFuncAtomSV.queryFuncInfoList(FuncCriteria);
	}

	private List<Long> getChilderFuncIdList(long funcId) {
		List<Long> funcIdList = new LinkedList<Long>();
		List<GnFunc> sonFuncList = iGnFuncAtomSV.querySonFuncById(funcId);
		if (sonFuncList == null || sonFuncList.size() == 0) {
			return funcIdList;
		}
		List<Long> sonIdList = new LinkedList<Long>();
		for (GnFunc gnFunc : sonFuncList) {
			long sonFuncId = gnFunc.getFuncId();
			sonIdList.add(sonFuncId);
			funcIdList.add(sonFuncId);
		}
		List<GnFunc> sonFuncById = iGnFuncAtomSV.querySonFuncById(sonIdList);
		if (sonFuncById != null && sonFuncById.size() > 0) {
			for (GnFunc gnFunc : sonFuncById) {
				long sonFuncId = gnFunc.getFuncId();
				List<Long> childerFuncIdList = getChilderFuncIdList(sonFuncId);
				funcIdList.addAll(childerFuncIdList);
			}
		}
		return funcIdList;
	}

	@Override
	public List<GnFunc> querySonFuncInfo(long funcId) throws BusinessException {
		return iGnFuncAtomSV.querySonFuncById(funcId);
	}
}
