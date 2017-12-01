package com.ai.opt.sys.service.validate.interfaces;

import java.sql.Timestamp;

import com.ai.opt.base.exception.BusinessException;

public interface IGnFuncValidateSV {

	// private Long funcId;
	void checkFuncId(Long funcId) throws BusinessException;

	// private String funcName;
	void checkFuncName(String funcName) throws BusinessException;

	// private String state;
	void checkState(String state) throws BusinessException;

	// private String funcUrl;
	void checkFuncUrl(String funcUrl) throws BusinessException;

	// private String funcType;
	void checkFuncType(String funcType) throws BusinessException;

	// private Long funcSort;
	void checkFuncSort(Long funcSort) throws BusinessException;

	// private Long parentFuncId;
	void checkParentFuncId(Long parentFuncId) throws BusinessException;

	// private String funcCssClass;
	void checkFuncCssClass(String funcCssClass) throws BusinessException;

	// private String funcPic;
	void checkFuncPic(String funcPic) throws BusinessException;

	// private Timestamp activeTime;
	void checkActiveTime(Timestamp activeTime) throws BusinessException;

	// private Timestamp inactiveTime;
	void checkInactiveTime(Timestamp inactiveTime) throws BusinessException;

	// private Timestamp createTime;
	void checkCreateTime(Timestamp createTime) throws BusinessException;

	// private Timestamp updateTime;
	void checkUpdateTime(Timestamp updateTime) throws BusinessException;

	// private Long createAccountId;
	void checkCreateAccountId(Long createAccountId) throws BusinessException;

	// private Long updateAccountId;
	void checkUpdateAccountId(Long updateAccountId) throws BusinessException;
	
	void checkPageNo(Integer pageNo) throws BusinessException;
	
	void checkPageSize(Integer pageSize) throws BusinessException;
}
