package com.ai.baas.pt.web.filter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.security.Principal;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sso.client.filter.SSOClientUser;
import com.ai.opt.uac.api.account.interfaces.IAccountManageSV;
import com.ai.opt.uac.api.account.interfaces.IIndustryManageSV;
import com.ai.opt.uac.api.account.interfaces.ITenantManageSV;
import com.ai.opt.uac.api.account.param.AccountQueryRequest;
import com.ai.opt.uac.api.account.param.AccountQueryResponse;
import com.ai.opt.uac.api.account.param.IndustryQueryResponse;
import com.ai.opt.uac.api.account.param.TenantQueryResponse;
import com.ai.paas.ipaas.util.StringUtil;
import com.alibaba.fastjson.JSON;


public class AssembleUserInfoFilter implements Filter {
    private String[] ignor_suffix = {};
    private static final Logger LOG = LoggerFactory.getLogger(AssembleUserInfoFilter.class);
    private static final String TENANT_ID_DEFAULT="0";
    private static final String INDUSTRY_CODE_DEFAULT="0";

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
        SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
        if (user == null) {
            user = assembleUser(req);
            if(user!=null){
            	session.setAttribute(SSOClientConstants.USER_SESSION_KEY, user);
            	LOG.info("已封装的用户信息为：" + user.toString());
            }
            chain.doFilter(req, response);

        } else {
        	//刷新用户昵称
        	refreshUser(user);
        	LOG.info("【baas-pt-web】user="+JSON.toJSONString(user));
            chain.doFilter(req, response);
        }
    }
    public void refreshUser(SSOClientUser user){
    	try{
    		IAccountManageSV accSv=DubboConsumerFactory.getService(IAccountManageSV.class);
    		AccountQueryRequest req=new AccountQueryRequest();
    		req.setAccountId(user.getAccountId());
    		AccountQueryResponse resp=accSv.queryBaseInfo(req);
    		if(resp.getResponseHeader().isSuccess()){
    			user.setNickName(resp.getNickName());
    		}
    	}
    	catch(Exception e){
    		LOG.error("刷新用户信息失败", e);
    	}
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
    private SSOClientUser assembleUser(HttpServletRequest request) {
    	SSOClientUser user = null;
        try {
            Principal principal = request.getUserPrincipal();
            if (principal != null) {
                user = new SSOClientUser();
                AttributePrincipal attributePrincipal = (AttributePrincipal) principal;
                Map<String, Object> attributes = attributePrincipal.getAttributes();
                Field[] fields = SSOClientUser.class.getDeclaredFields();
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
                }//end for
                
                
                String tenantId=user.getTenantId();
                //租户ID不存在
                if(TENANT_ID_DEFAULT.equals(tenantId)){
                	user.setTenantName("");
                	user.setIndustryName("");
                	user.setIndustryCode(INDUSTRY_CODE_DEFAULT);
                }
                else{
                	BaseInfo baseInfo=new BaseInfo();
                	baseInfo.setTenantId(user.getTenantId());
                	TenantQueryResponse tenantResponse=DubboConsumerFactory.getService(ITenantManageSV.class).queryTenantInfo(baseInfo);
                	if(tenantResponse!=null){
                		user.setTenantName(tenantResponse.getTenantName());
                		String industryCode=tenantResponse.getIndustryCode();
                		
                		if(!StringUtil.isBlank(industryCode)&&!INDUSTRY_CODE_DEFAULT.equals(industryCode)){
                			user.setIndustryCode(industryCode);
                			IndustryQueryResponse industryResponse=DubboConsumerFactory.getService(IIndustryManageSV.class).queryByIndustryCode(industryCode);
                			if(industryResponse!=null){
                				user.setIndustryName(industryResponse.getIndustryName());
                			}
                		}
                		else{
                			user.setIndustryCode(INDUSTRY_CODE_DEFAULT);
                			user.setIndustryName("");
                		}
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
