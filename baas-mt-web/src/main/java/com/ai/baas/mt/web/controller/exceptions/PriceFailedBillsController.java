package com.ai.baas.mt.web.controller.exceptions;

import javax.servlet.http.HttpServletRequest;

import com.ai.baas.bmc.api.failedbills.params.BillIdList;
import com.ai.baas.bmc.api.failedbills.params.FailedBillDetail;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ai.baas.bmc.api.failedbills.interfaces.IFailedBillsSV;
import com.ai.baas.bmc.api.failedbills.params.FailedBillQueryVo;
import com.ai.baas.bmc.api.failedbills.params.FailedBillVo;
import com.ai.baas.mt.web.constants.BaaSMTConstants;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.web.model.ResponseData;

import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 批价错单管理
 * @author wangyx13
 *
 */
@RestController
@RequestMapping("/priceFailed")
public class PriceFailedBillsController {
    private static final Logger LOG = Logger.getLogger(PriceFailedBillsController.class);

    @RequestMapping("/list")
    public ModelAndView toList(HttpServletRequest request) {
        return new ModelAndView("jsp/exceptions/priceFailedBillList");
    }
    
    /**
     * 错单查询
     * @param query
     * @return
     */
   @RequestMapping("/getList")
   public ResponseData<PageInfoResponse<FailedBillVo>> getList(FailedBillQueryVo query){
   	ResponseData<PageInfoResponse<FailedBillVo>> responseData = null;
   	try {
       	IFailedBillsSV failedBillsSV = DubboConsumerFactory.getService(IFailedBillsSV.class);
       	query.setTenantId(BaaSMTConstants.TENANTID);
		query.setFailStep(BaaSMTConstants.FailStep.PRICING);
       	LOG.info("错单查询入參"+JSONObject.fromObject(query).toString());
       	PageInfoResponse<FailedBillVo> queryFailedBills = failedBillsSV.queryFailedBills(query);
       	responseData = new ResponseData<PageInfoResponse<FailedBillVo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询错单成功", queryFailedBills);
   	} catch (Exception e) {
			responseData = new ResponseData<PageInfoResponse<FailedBillVo>>(ResponseData.AJAX_STATUS_FAILURE,"获取信息异常");
			LOG.error("获取信息出错：", e);
		}
       return responseData;
   }

	/**
	 * 根据id查询错单
	 * @param id
	 * @return
	 */
	@RequestMapping("/getFailedBillDetail/{id}")
	public ResponseData<FailedBillVo> getFailedBillDetail(@PathVariable("id") String id){
		ResponseData<FailedBillVo> responseData = null;
		try {
			if(!StringUtil.isBlank(id)){
				IFailedBillsSV failedBillsSV = DubboConsumerFactory.getService(IFailedBillsSV.class);
				FailedBillVo query = new FailedBillVo();
				query.setTenantId(BaaSMTConstants.TENANTID);
				query.setId(Long.parseLong(id));
				LOG.info("错单查询入參"+JSONObject.fromObject(query).toString());
				FailedBillDetail failedBillDetail = failedBillsSV.getFailedBillDetail(query);
				if(failedBillDetail!=null&&failedBillDetail.getResponseHeader().isSuccess()&&failedBillDetail.getFailedBillVo()!=null){
					responseData = new ResponseData<FailedBillVo>(ResponseData.AJAX_STATUS_SUCCESS, "查询错单成功", failedBillDetail.getFailedBillVo());
				}else{
					responseData = new ResponseData<FailedBillVo>(ResponseData.AJAX_STATUS_SUCCESS, "查询错单["+id+"]不存在");
				}
			}else {
				responseData = new ResponseData<FailedBillVo>(ResponseData.AJAX_STATUS_FAILURE, "错单id为空，无法查询");
			}
		} catch (Exception e) {
			responseData = new ResponseData<FailedBillVo>(ResponseData.AJAX_STATUS_FAILURE,"查询错单异常");
			LOG.error("查询错单异常：", e);
		}
		return responseData;
	}

	/**
	 * 发送编辑后错单
	 * @param failedBillVo
	 * @return
	 */
	@RequestMapping("/resendEditBill")
	public ResponseData<String> resendEditBill(FailedBillVo failedBillVo){
		ResponseData<String> responseData = null;
		try {
			if(failedBillVo.getId()!=null){
				IFailedBillsSV failedBillsSV = DubboConsumerFactory.getService(IFailedBillsSV.class);
				failedBillVo.setTenantId(BaaSMTConstants.TENANTID);
				LOG.info("重发编辑错单入參"+JSONObject.fromObject(failedBillVo).toString());
				BaseResponse response = failedBillsSV.resendFailedBill(failedBillVo);
				if(response.getResponseHeader().isSuccess()){
					responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, "重发编辑错单成功");
				}else{
					responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "重发失败");
					LOG.error("重发错单失败："+response.getResponseHeader().getResultMessage());
				}
			}else {
				responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "错单id为空，无法重发");
			}
		} catch (Exception e) {
			responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"重发错单异常");
			LOG.error("重发错单异常：", e);
		}
		return responseData;
	}

	/**
	 * 批量重处理错单
	 * @param bills
	 * @return
	 */
	@RequestMapping("/batchResendBill")
	public ResponseData<String> batchResendBill(String bills){
		ResponseData<String> responseData = null;
		try {
			if(!StringUtil.isBlank(bills)){
				List<Long> list = JSON.parseArray(bills, Long.class);
				IFailedBillsSV failedBillsSV = DubboConsumerFactory.getService(IFailedBillsSV.class);
				BillIdList vo = new BillIdList();
				vo.setTenantId(BaaSMTConstants.TENANTID);
				vo.setBillIds(list);
				LOG.info("批量重处理错单入參"+JSONObject.fromObject(vo).toString());
				BaseResponse response = failedBillsSV.batchResendBill(vo);
				if(response.getResponseHeader().isSuccess()){
					responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, "批量重处理错单成功");
				}else{
					responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "批量重处理失败");
					LOG.error("批量重处理错单失败："+response.getResponseHeader().getResultMessage());
				}
			}else {
				responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "错单id列表为空，无法重发");
			}
		} catch (Exception e) {
			responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"批量重处理错单异常");
			LOG.error("批量重处理错单异常：", e);
		}
		return responseData;
	}

	/**
	 * 批量删除错单
	 * @param bills
	 * @return
	 */
	@RequestMapping("/batchDeleteBill")
	public ResponseData<String> batchDeleteBill(String bills){
		ResponseData<String> responseData = null;
		try {
			if(!StringUtil.isBlank(bills)){
				List<Long> list = JSON.parseArray(bills, Long.class);
				IFailedBillsSV failedBillsSV = DubboConsumerFactory.getService(IFailedBillsSV.class);
				BillIdList vo = new BillIdList();
				vo.setTenantId(BaaSMTConstants.TENANTID);
				vo.setBillIds(list);
				LOG.info("批量删除错单入參"+JSONObject.fromObject(vo).toString());
				BaseResponse response = failedBillsSV.batchDeleteBill(vo);
				if(response.getResponseHeader().isSuccess()){
					responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, "批量删除错单成功");
				}else{
					responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "批量删除错单失败");
					LOG.error("批量删除错单失败："+response.getResponseHeader().getResultMessage());
				}
			}else {
				responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE, "错单id列表为空，无法删除");
			}
		} catch (Exception e) {
			responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_FAILURE,"批量重处理错单异常");
			LOG.error("批量删除错单异常：", e);
		}
		return responseData;
	}
   
}
