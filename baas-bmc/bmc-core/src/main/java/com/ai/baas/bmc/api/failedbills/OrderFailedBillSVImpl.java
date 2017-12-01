package com.ai.baas.bmc.api.failedbills;

import com.ai.baas.bmc.api.failedbills.interfaces.IOrderFailedBillSV;
import com.ai.baas.bmc.api.failedbills.params.*;
import com.ai.baas.bmc.service.business.interfaces.IOrderFailedBillBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.PageInfoResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Service
@Component
public class OrderFailedBillSVImpl implements IOrderFailedBillSV{
    private static final Logger LOG = LogManager.getLogger(OrderFailedBillSVImpl.class);

    @Resource
    private IOrderFailedBillBusiSV orderFailedBillBusiSV;

    @Override
    public PageInfoResponse<FailedOrderVo> queryFailedOrders(FailedOrderQueryVo queryVo) throws BusinessException, SystemException {
        if (StringUtil.isBlank(queryVo.getTenantId())) {
            throw new BusinessException("租户id为空");
        }
        if (queryVo.getPageNo()==null) {
            throw new BusinessException("pageNo为空");
        }
        if (queryVo.getPageSize()==null) {
            throw new BusinessException("pageSize为空");
        }
        PageInfoResponse<FailedOrderVo> pageInfoResponse = null;
        ResponseHeader header = new ResponseHeader();
        try {
            Integer count = orderFailedBillBusiSV.countFailedOrders(queryVo);
            pageInfoResponse = new PageInfoResponse<FailedOrderVo>();
            header = new ResponseHeader();
            if(count!=null&&count>0){
                List<FailedOrderVo> orderVos = orderFailedBillBusiSV.queryFailedOrders(queryVo);
                pageInfoResponse.setPageNo(queryVo.getPageNo());
                pageInfoResponse.setPageSize(queryVo.getPageSize());
                pageInfoResponse.setCount(count);
                pageInfoResponse.setResult(orderVos);

                header.setResultMessage("订单错单查询成功");
                header.setIsSuccess(true);
                header.setResultCode("000000");
            }else{
                pageInfoResponse.setPageNo(queryVo.getPageNo());
                pageInfoResponse.setPageSize(queryVo.getPageSize());
                pageInfoResponse.setCount(count);
            }
        } catch (Exception e) {
            LOG.error("查询订单错单失败",e);
            throw new BusinessException("查询订单错单失败",e);
        }
        pageInfoResponse.setResponseHeader(header);
        return pageInfoResponse;
    }

    @Override
    public FailedOrderDetail getFailedOrderDetail(FailedOrderVo queryVo) throws BusinessException, SystemException {
        if(StringUtil.isBlank(queryVo.getTenantId())){
            throw new BusinessException("租户id为空");
        }
        if(queryVo.getId()==null){
            throw new BusinessException("id为空");
        }

        FailedOrderDetail orderDetail = new FailedOrderDetail();
        ResponseHeader header = new ResponseHeader();
        try {
            FailedOrderVo orderVo = orderFailedBillBusiSV.getFailedOrderById(queryVo.getId());
            orderDetail.setFailedOrderVo(orderVo);

            header.setSuccess(true);
            header.setResultCode("000000");
            header.setResultMessage("查询订单错单成功");
        } catch (Exception e) {
            LOG.error("订单错单查询失败",e);
            header.setSuccess(false);
            header.setResultCode("000001");
            header.setResultMessage("查询订单错单失败:"+e.getMessage());
        }
        orderDetail.setResponseHeader(header);

        return orderDetail;
    }

    @Override
    public BaseResponse resendFailedOrder(FailedOrderVo orderVo) throws BusinessException, SystemException {
        if(StringUtil.isBlank(orderVo.getTenantId())){
            throw new BusinessException("租户id为空");
        }
        if(orderVo.getId()==null){
            throw new BusinessException("id为空");
        }

        BaseResponse response = new BaseResponse();
        ResponseHeader header = new ResponseHeader();
        try {
            orderFailedBillBusiSV.resendOrder(orderVo);
            header.setSuccess(true);
            header.setResultCode("000000");
            header.setResultMessage("重发编辑订单错单成功");
        } catch (Exception e) {
            LOG.error("重发编辑订单错单失败",e);
            header.setSuccess(false);
            header.setResultCode("000001");
            header.setResultMessage("重发编辑订单错单失败:"+e.getMessage());
        }
        response.setResponseHeader(header);
        return response;
    }
}
