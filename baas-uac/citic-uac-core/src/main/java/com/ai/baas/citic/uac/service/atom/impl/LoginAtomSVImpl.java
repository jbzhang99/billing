package com.ai.baas.citic.uac.service.atom.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.citic.uac.dao.mapper.bo.SysUser;
import com.ai.baas.citic.uac.dao.mapper.bo.SysUserCriteria;
import com.ai.baas.citic.uac.dao.mapper.factory.MapperFactory;
import com.ai.baas.citic.uac.service.atom.interfaces.ILoginAtomSV;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;

@Component
public class LoginAtomSVImpl implements ILoginAtomSV {

    @Override
    public SysUser queryByUserName(SysUser user) {

        SysUserCriteria conditon = new SysUserCriteria();
        SysUserCriteria.Criteria criteria = conditon.or();
        if (!StringUtil.isBlank(user.getMobile())) {
            criteria.andMobileEqualTo(user.getMobile());
        }
        if (!StringUtil.isBlank(user.getEmail())) {
            criteria.andEmailEqualTo(user.getEmail());
        }
        if (!StringUtil.isBlank(user.getLoginName())) {
            criteria.andLoginNameEqualTo(user.getLoginName());
        }
        // 登录标记为1
        criteria.andLoginFlagEqualTo("1");
        // 删除标记为0
        criteria.andDelFlagEqualTo("0");

        List<SysUser> list = MapperFactory.getSysUserMapper().selectByExample(conditon);
        if (!CollectionUtil.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }


}
