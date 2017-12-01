package com.ai.baas.mt.web.controller.exceptions;

import com.ai.baas.bmc.api.baseInfo.params.BaseCode;
import com.ai.baas.bmc.api.failedbills.interfaces.IOrderFailedBillSV;
import com.ai.baas.bmc.api.failedbills.params.FailedOrderDetail;
import com.ai.baas.bmc.api.failedbills.params.FailedOrderQueryVo;
import com.ai.baas.bmc.api.failedbills.params.FailedOrderVo;
import com.ai.baas.mt.web.constants.BaaSMTConstants;
import com.ai.baas.mt.web.controller.common.ParamController;
import com.ai.baas.mt.web.model.FailedOrderShowVo;
import com.ai.baas.mt.web.util.TradeSeqUtil;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单错单管理
 * @author wangyx13
 *
 */
@RestController
@RequestMapping("/orderRedo")
public class OrderRedoController {
    private static final Logger LOG = Logger.getLogger(OrderRedoController.class);

    @RequestMapping("/list")
    public ModelAndView toList(HttpServletRequest request) {
        return new ModelAndView("jsp/exceptions/orderRedoList");
    }

    /**
     * 订单错单查询
     * @param query
     * @return
     */
    @RequestMapping("/getList")
    public ResponseData<PageInfoResponse<FailedOrderShowVo>> getList(FailedOrderQueryVo query){
        ResponseData<PageInfoResponse<FailedOrderShowVo>> responseData = null;
        try {
            IOrderFailedBillSV orderFailedBillSV = DubboConsumerFactory.getService(IOrderFailedBillSV.class);
            query.setTenantId(BaaSMTConstants.TENANTID);
            LOG.info("错单查询入參"+ JSONObject.fromObject(query).toString());
            PageInfoResponse<FailedOrderVo> queryFailedOrders = orderFailedBillSV.queryFailedOrders(query);
            PageInfoResponse<FailedOrderShowVo> response = new PageInfoResponse<>();
            if(queryFailedOrders!=null){
                if(!CollectionUtil.isEmpty(queryFailedOrders.getResult())){
                    List<BaseCode> orderFailCodeList = ParamController.getSysParams(BaaSMTConstants.TENANTID, "ORDER_FAIL_CODE", TradeSeqUtil.newTradeSeq("PUB"));
                    int index = (queryFailedOrders.getPageNo()-1)*query.getPageSize()+1;
                    List<FailedOrderShowVo> orderShowVos = new ArrayList<>();
                    for(FailedOrderVo failedOrderVo:queryFailedOrders.getResult()){
                        FailedOrderShowVo orderShowVo = new FailedOrderShowVo();
                        BeanUtils.copyProperties(orderShowVo,failedOrderVo);
                        orderShowVo.setIndex(index);
                        index++;
                        for(BaseCode baseCode:orderFailCodeList){
                            if(failedOrderVo.getFailCode().equals(baseCode.getParamCode())){
                                orderShowVo.setFailCodeDesc(baseCode.getParamName());
                                break;
                            }
                        }
                        orderShowVos.add(orderShowVo);
                    }
                    response.setResult(orderShowVos);
                }
                response.setResponseHeader(queryFailedOrders.getResponseHeader());
                response.setPageNo(queryFailedOrders.getPageNo());
                response.setCount(queryFailedOrders.getCount());
                response.setPageCount(queryFailedOrders.getPageCount());
                response.setPageSize(queryFailedOrders.getPageSize());
            }
            responseData = new ResponseData<PageInfoResponse<FailedOrderShowVo>>(ResponseData.AJAX_STATUS_SUCCESS, "查询错单成功", response);
        } catch (Exception e) {
            responseData = new ResponseData<PageInfoResponse<FailedOrderShowVo>>(ResponseData.AJAX_STATUS_FAILURE,"获取信息异常");
            LOG.error("获取信息出错：", e);
        }
        return responseData;
    }

    /**
     * 根据id查询错单
     * @param id
     * @return
     */
    @RequestMapping("/getFailedOrderDetail/{id}")
    public ResponseData<FailedOrderVo> getFailedOrderDetail(@PathVariable("id") String id){
        ResponseData<FailedOrderVo> responseData = null;
        try {
            if(!StringUtil.isBlank(id)){
                IOrderFailedBillSV orderFailedBillSV = DubboConsumerFactory.getService(IOrderFailedBillSV.class);
                FailedOrderVo query = new FailedOrderVo();
                query.setTenantId(BaaSMTConstants.TENANTID);
                query.setId(Long.parseLong(id));
                LOG.info("错单查询入參"+JSONObject.fromObject(query).toString());
                FailedOrderDetail failedOrderDetail = orderFailedBillSV.getFailedOrderDetail(query);
                if(failedOrderDetail!=null&&failedOrderDetail.getResponseHeader().isSuccess()&&failedOrderDetail.getFailedOrderVo()!=null){
                    responseData = new ResponseData<FailedOrderVo>(ResponseData.AJAX_STATUS_SUCCESS, "查询错单成功", failedOrderDetail.getFailedOrderVo());
                }else{
                    responseData = new ResponseData<FailedOrderVo>(ResponseData.AJAX_STATUS_SUCCESS, "查询错单["+id+"]不存在");
                }
            }else {
                responseData = new ResponseData<FailedOrderVo>(ResponseData.AJAX_STATUS_FAILURE, "错单id为空，无法查询");
            }
        } catch (Exception e) {
            responseData = new ResponseData<FailedOrderVo>(ResponseData.AJAX_STATUS_FAILURE,"查询错单异常");
            LOG.error("查询错单异常：", e);
        }
        return responseData;
    }

    /**
     * 发送编辑后错单
     * @param orderVo
     * @return
     */
    @RequestMapping("/resendEditOrder")
    public ResponseData<String> resendEditOrder(FailedOrderVo orderVo){
        ResponseData<String> responseData = null;
        try {
            if(orderVo.getId()!=null){
                IOrderFailedBillSV orderFailedBillSV = DubboConsumerFactory.getService(IOrderFailedBillSV.class);
                orderVo.setTenantId(BaaSMTConstants.TENANTID);
                orderVo.setOrderJson(orderVo.getOrderJson().replaceAll("\\n"," "));
                LOG.info("重发编辑错单入參"+JSONObject.fromObject(orderVo).toString());
                BaseResponse response = orderFailedBillSV.resendFailedOrder(orderVo);
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
}
