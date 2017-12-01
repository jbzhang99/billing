package com.ai.baas.op.web.controller.account;

import com.ai.baas.op.web.constants.BaaSOPConstants;
import com.ai.baas.smc.api.queryimportlog.interfaces.IQueryImportLogSV;
import com.ai.baas.smc.api.queryimportlog.param.ImportLogVo;
import com.ai.baas.smc.api.queryimportlog.param.QueryImportLogRequest;
import com.ai.baas.smc.api.queryimportlog.param.QueryImportLogResponse;
import com.ai.opt.base.vo.PageInfo;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.opt.sso.client.filter.SSOClientConstants;
import com.ai.opt.sso.client.filter.SSOClientUser;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;





@RestController
@RequestMapping("/account1")
public class QueryResultController {
    private static final Logger LOG = Logger.getLogger(QueryResultController.class);
    
    @RequestMapping("/toQueryResult")
    public ModelAndView toQueryResult(HttpServletRequest request) {

    	return new ModelAndView("jsp/account/queryResult");
    }
    /**
     * 数据查询
     * @param request
     * @return
     */
    @RequestMapping("/getList")
    public ResponseData<PageInfo<ImportLogVo>> getList(HttpServletRequest request){
        HttpSession session = request.getSession();
        SSOClientUser user = (SSOClientUser) session.getAttribute(SSOClientConstants.USER_SESSION_KEY);
        QueryImportLogRequest req = new QueryImportLogRequest();
        IQueryImportLogSV iQueryImportLogSV = DubboConsumerFactory.getService("iQueryImportLogSV");
        ResponseData<PageInfo<ImportLogVo>> responseData = null;
        req.setTenantId(user.getTenantId());
        PageInfo<ImportLogVo> pageInfo = new PageInfo<ImportLogVo> ();
        String strPageNo=(null==request.getParameter(BaaSOPConstants.PAGENO))?"1":request.getParameter(BaaSOPConstants.PAGENO);
        String strPageSize=(null==request.getParameter(BaaSOPConstants.PAGESIZE))?"10":request.getParameter(BaaSOPConstants.PAGESIZE);
        pageInfo.setPageNo(Integer.parseInt(strPageNo));
        pageInfo.setPageSize(Integer.parseInt(strPageSize));
        try {
            req.setPageInfo(pageInfo);
            QueryImportLogResponse resultInfo = iQueryImportLogSV.queryImportLog(req);
            PageInfo<ImportLogVo> result= resultInfo.getPageInfo();
            //翻译状态
            if(result!=null){
               List<ImportLogVo>  logList = result.getResult();
               if(!CollectionUtil.isEmpty(logList)){
                   for(ImportLogVo log: logList){
                      String stateName =  ImportLogUtil.getImportStateName(log.getState());
                      log.setState(stateName);
                   }
               }
               
            }
            responseData = new ResponseData<PageInfo<ImportLogVo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", result);
        } catch (Exception e) {
            responseData = new ResponseData<PageInfo<ImportLogVo>>(ResponseData.AJAX_STATUS_FAILURE, "查询失败");
            LOG.error("获取信息出错：", e);
        }
        return responseData;
    }
}
