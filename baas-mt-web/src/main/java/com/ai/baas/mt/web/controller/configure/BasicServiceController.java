package com.ai.baas.mt.web.controller.configure;

import com.ai.baas.dshm.api.dshmprocess.interfaces.IdshmSV;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.web.model.ResponseData;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 配置管理-基础服务-Key查询
 * Date: Mar 31, 2016 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 * 
 * @author liangbs
 */
@RestController
@RequestMapping("/config/basic")
public class BasicServiceController {
	
    private static final Logger LOG = Logger.getLogger(BasicServiceController.class);

    /**
     * 进入key查询
     * @param request
     * @return
     * @author liangbs
     * @ApiDocMethod
     */
    @RequestMapping("/key")
    public ModelAndView discountProduct(HttpServletRequest request) {
    	String tableName=request.getParameter("tableName");
    	String tableId=request.getParameter("tableId");
    	String indexKey=request.getParameter("indexKey");
    	Map<String,Object> model = new HashMap<String,Object>(); 
    	model.put("tableName", tableName.toLowerCase());
    	model.put("tableId", tableId);
    	model.put("indexKey", indexKey.toLowerCase());
        return new ModelAndView("jsp/configure/key",model);
    }
    
    
   /**
    * key查询
    * @param request
    * @return
    * @author haifeng
    * @ApiDocMethod
    */
    @RequestMapping("/query")
    public ResponseData<String> query(HttpServletRequest request){
    	ResponseData<String> responseData = null;
    	try{
    		String keyTable = (String)request.getParameter("keyTable");
    		String keyColumn = (String)request.getParameter("keyColumn");
    		IdshmSV idshmSV = DubboConsumerFactory.getService("idshmSV");
    		String keyDesc = idshmSV.queryByHashKey(keyTable, keyColumn);
    		LOG.error("keyTable:"+keyTable);
    		LOG.error("keyColumn:"+keyColumn);
    		LOG.error("keyDesc:"+keyDesc);
    		 responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, "查询成功", keyDesc);
             LOG.error("成功！");
    	} catch (Exception e) {
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "查询失败");
            LOG.error("失败！",e);
        }
    	
    	return responseData;
    }
    
    
    /**
     * key删除
     * @param request
     * @return
     * @author haifeng
     * @ApiDocMethod
     */
     @RequestMapping("/delete")
     public ResponseData<String> delete(HttpServletRequest request){
     	ResponseData<String> responseData = null;
     	try{
     		String keyTable = (String)request.getParameter("keyTable");
     		String keyColumn = (String)request.getParameter("keyColumn");
     		String[]  columnArray  = new String[1];
     		columnArray[0]= keyColumn;
     		IdshmSV idshmSV = DubboConsumerFactory.getService("idshmSV");
     		long deleteValue = idshmSV.delByHashKey(keyTable, columnArray);
     		LOG.error("keyTable:"+keyTable);
     		LOG.error("keyColumn:"+keyColumn);
     		if(deleteValue!=0){
     			 responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, "删除成功!", null);
     		}else{
     			 responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "删除失败!");
     		}
            LOG.error("成功！");
     	} catch (Exception e) {
             responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "删除失败!");
             LOG.error("失败！",e);
         }
     	
     	return responseData;
     }

}