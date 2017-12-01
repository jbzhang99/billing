package com.ai.citic.billing.web.filter;

import com.ai.baas.bmc.api.queryidinfo.params.BlAcctInfoInfo;
import com.ai.citic.billing.web.common.CommonCaller;
import com.ai.citic.billing.web.model.sso.CiticSSOClientUser;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sys.api.citicrestcommon.interfaces.ICiticRestReqWrapperSV;
import com.ai.opt.sys.api.citicrestcommon.param.UserInfo;
import com.ai.opt.sys.api.citicrestcommon.param.UserInfoQueryVo;
import com.ai.opt.sys.api.citicrestcommon.param.UserQueryResponse;
import com.alibaba.fastjson.JSON;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.Principal;
import java.util.List;
import java.util.Map;


public class AssembleUserInfoFilter implements Filter {

    private String[] ignor_suffix = {};
    private static final Logger LOG = LoggerFactory.getLogger(AssembleUserInfoFilter.class);
    private static final String CITIC_USER_TENANT_ID = "ECITIC";

    public void init(FilterConfig filterConfig) throws ServletException {
        String ignore_res = filterConfig.getInitParameter("ignore_suffix");
        if (!"".equals(ignore_res)) {
            this.ignor_suffix = filterConfig.getInitParameter("ignore_suffix").split(",");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (!shouldFilter(req)) {
            chain.doFilter(req, response);
            return;
        }
        HttpSession session = req.getSession();
        CiticSSOClientUser user = (CiticSSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
        if (user == null) {
            user = assembleUser(req);
            session.setAttribute(SSOClientConstants.USER_SESSION_KEY, user);
            LOG.info("已封装的用户信息为：" + JSON.toJSONString(user));
        } else {
        	//刷新用户昵称
        	LOG.info("【citic-billing-web】user="+JSON.toJSONString(user));
        }
        chain.doFilter(req, response);
    }

    @Override
    public void destroy() {

    }
    
    /**
     * 封装用户信息
     *
     * @param request
     * @return
     */
    private CiticSSOClientUser assembleUser(HttpServletRequest request) {
        CiticSSOClientUser user = null;
        try {
            Principal principal = request.getUserPrincipal();
            if (principal != null) {
                user = new CiticSSOClientUser();
                user.setTenantId(CITIC_USER_TENANT_ID);
                AttributePrincipal attributePrincipal = (AttributePrincipal) principal;
                Map<String, Object> attributes = attributePrincipal.getAttributes();
                Field[] fields = CiticSSOClientUser.class.getDeclaredFields();
                for (Field field : fields) {
                    String value = (String) attributes.get(field.getName());
                    if (value != null) {
                        field.setAccessible(true);
                        if ("long".equalsIgnoreCase(field.getType().toString())) {
                            field.set(user, Long.parseLong(value));
                        } else {
                            field.set(user, value);
                        }
                    }
                }
                
                //根据用户Id查询其他用户信息
                ICiticRestReqWrapperSV iCiticRestReqWrapperSV = DubboConsumerFactory.getService(ICiticRestReqWrapperSV.class);
                UserInfoQueryVo query = new UserInfoQueryVo();
                query.setTenantId(CITIC_USER_TENANT_ID);
                query.setSelectId(user.getUserId());
                query.setSelectType("14");
                UserQueryResponse queryResponse = iCiticRestReqWrapperSV.searchUser(query);
                if(!CollectionUtil.isEmpty(queryResponse.getUsers())){
                    UserInfo userInfo = queryResponse.getUsers().get(0);
                    LOG.info("已封装的用户信息为userInfo：" + JSON.toJSONString(userInfo));
                    BeanUtils.copyProperties(user,userInfo);
                    //根据orgid(外部客户id)查询系统内部账户及客户id
                    CommonCaller commonCaller = new CommonCaller();
                    List<BlAcctInfoInfo> acctAndCustInfos = commonCaller.getAcctAndCustInfo(user.getTenantId(), user.getTenant());
                    LOG.info("根据组织机构id查询的用户信息为acctAndCustInfo：" + JSON.toJSONString(userInfo));
                    if(!CollectionUtil.isEmpty(acctAndCustInfos)){
                        BeanUtils.copyProperties(user,acctAndCustInfos.get(0));
                    }
                }

            }
        } catch (Exception e) {
            LOG.error("封装用户信息失败", e);
        }
        return user;
    }

    private boolean shouldFilter(HttpServletRequest req) {
        if (ignor_suffix != null && ignor_suffix.length > 0) {
            String uri = req.getRequestURI().toLowerCase();
            for (String suffix : ignor_suffix) {
                if (uri.endsWith(suffix)) {
                    return false;
                }
            }
        }
        return true;
    }
}
