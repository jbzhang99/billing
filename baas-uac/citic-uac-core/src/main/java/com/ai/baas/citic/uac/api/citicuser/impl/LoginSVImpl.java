package com.ai.baas.citic.uac.api.citicuser.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.citic.uac.api.citicuser.interfaces.ILoginSV;
import com.ai.baas.citic.uac.api.citicuser.param.UserLoginResponse;
import com.ai.baas.citic.uac.constants.AccountConstants.ResultCode;
import com.ai.baas.citic.uac.dao.mapper.bo.SysUser;
import com.ai.baas.citic.uac.service.busi.interfaces.ILoginBusiSV;
import com.ai.baas.citic.uac.service.busi.interfaces.IVoValidateSV;
import com.ai.baas.citic.uac.util.RegexUtils;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.BeanUtils;
import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
public class LoginSVImpl implements ILoginSV {
    @Autowired
    private ILoginBusiSV iLoginBusiSV;
    @Autowired
	IVoValidateSV iVoValidateSV;

    @Override
    public UserLoginResponse queryUserByUserName(String username)
            throws BusinessException,SystemException {
        iVoValidateSV.validateLogin(username);
        // 判断用户名是手机还是邮箱
        boolean isEmial = RegexUtils.checkIsEmail(username);
        boolean isPhone = RegexUtils.checkIsPhone(username);
        SysUser account = new SysUser();
        if (isPhone == true) {
            account.setMobile(username);
        }else if (isEmial == true) {
            account.setEmail(username);
        }else{
            account.setLoginName(username); 
        }
        SysUser sysUser = iLoginBusiSV.queryByUserName(account);
        // 组织返回对象
        UserLoginResponse response = new UserLoginResponse();
        if (sysUser != null) {
            BeanUtils.copyProperties(response, sysUser);
            response.setUserId(sysUser.getId());
			response.setLoginName(sysUser.getLoginName());
			response.setEmail(sysUser.getEmail());
			response.setMobile(sysUser.getMobile());
			response.setLoginPassword(sysUser.getPassword());
            ResponseHeader responseHeaders = new ResponseHeader(true, ResultCode.SUCCESS_CODE,
                    "成功");
            response.setResponseHeader(responseHeaders);
        } else {
            ResponseHeader responseHeaders = new ResponseHeader(false, ResultCode.FAIL_CODE,
                    "用户不存在");
            response.setResponseHeader(responseHeaders);
        }
        return response;
    }


}
