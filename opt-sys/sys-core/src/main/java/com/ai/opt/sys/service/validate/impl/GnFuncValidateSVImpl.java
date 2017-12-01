package com.ai.opt.sys.service.validate.impl;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sys.constants.SystemExceptCode;
import com.ai.opt.sys.service.validate.interfaces.IGnFuncValidateSV;

@Component
public class GnFuncValidateSVImpl implements IGnFuncValidateSV {

	/** 功能名称最大长度 */
	public static final int FUNCNAME_MAXSIZE = 50;
	/** 功能名称最小长度*/
	public static final int FUNCNAME_MINSIZE = 2;
	/** 功能名称最大长度 */
	public static final int URL_MAXSIZE = 200;
	
	@Override
	public void checkFuncId(Long funcId) throws BusinessException {
		if (funcId == null) {
			throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "功能ID（funcId）不能为空");
		}
	}

	@Override
	public void checkFuncName(String funcName) throws BusinessException {
		if(StringUtil.isBlank(funcName)){
			throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "功能名称（funcName）不能为空");
		}
		int nameSize = StringUtil.getByteLength(funcName);
		if (nameSize < FUNCNAME_MINSIZE || nameSize > FUNCNAME_MAXSIZE) {
			throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_VALUE_ERROR, "功能名称（funcName）长度在" + FUNCNAME_MINSIZE + "~" + FUNCNAME_MAXSIZE + "个字符，不能包含空格");
		}
	}

	@Override
	public void checkState(String state) throws BusinessException {
		if(StringUtil.isBlank(state)){
			throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "状态（state）不能为空");
		}
	}

	@Override
	public void checkFuncUrl(String funcUrl) throws BusinessException {
		if(StringUtil.isBlank(funcUrl)){
			throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "功能URL（funcUrl）不能为空");
		}
		int urlSize = StringUtil.getByteLength(funcUrl);
		if (urlSize > URL_MAXSIZE) {
			throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_VALUE_ERROR, "功能URL（funcUrl）长度不大于" + URL_MAXSIZE + "个字符，不能包含空格");
		}
		
	}

	@Override
	public void checkFuncType(String funcType) throws BusinessException {
		if(StringUtil.isBlank(funcType)){
			throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "功能类型（funcType）不能为空");
		}
	}

	@Override
	public void checkFuncSort(Long funcSort) throws BusinessException {
		if(funcSort == null){
			throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "功能排序（funcSort）不能为空");
		}
	}

	@Override
	public void checkParentFuncId(Long parentFuncId) throws BusinessException {
		if(parentFuncId == null){
			throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "父功能ID（parentFuncId）不能为空");
		}
	}

	@Override
	public void checkFuncCssClass(String funcCssClass) throws BusinessException {
		if(StringUtil.isBlank(funcCssClass)){
			throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "功能css样式（funcCssClass）不能为空");
		}
	}

	@Override
	public void checkFuncPic(String funcPic) throws BusinessException {
		if(StringUtil.isBlank(funcPic)){
			throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "功能图片（funcPic）不能为空");
		}
	}

	@Override
	public void checkActiveTime(Timestamp activeTime) throws BusinessException {
		if(activeTime == null){
			throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "生效时间（activeTime）不能为空");
		}
	}

	@Override
	public void checkInactiveTime(Timestamp inactiveTime) throws BusinessException {
		if(inactiveTime == null){
			throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "失效时间（inactiveTime）不能为空");
		}
	}

	@Override
	public void checkCreateTime(Timestamp createTime) throws BusinessException {
		if(createTime == null){
			throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "创建时间（createTime）不能为空");
		}
	}

	@Override
	public void checkUpdateTime(Timestamp updateTime) throws BusinessException {
		if(updateTime == null){
			throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "修改时间（updateTime）不能为空");
		}
	}

	@Override
	public void checkCreateAccountId(Long createAccountId) throws BusinessException {
		if(createAccountId == null){
			throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "创建人ID（createAccountId）不能为空");
		}
	}

	@Override
	public void checkUpdateAccountId(Long updateAccountId) throws BusinessException {
		if(updateAccountId == null){
			throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "修改人ID（updateAccountId）不能为空");
		}
	}

	@Override
	public void checkPageNo(Integer pageNo) throws BusinessException {
		if(pageNo == null){
			throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "pageNo不能为空");
		}
	}

	@Override
	public void checkPageSize(Integer pageSize) throws BusinessException {
		if(pageSize == null){
			throw new BusinessException(SystemExceptCode.ErrorCode.PARAM_NULL_ERROR, "pageSize不能为空");
		}
	}
}
