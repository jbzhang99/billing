package com.ai.baas.bmc.api.orderinfo.impl;

import com.ai.baas.bmc.api.orderinfo.params.InitCustomerRelatedInfoReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.baas.bmc.api.orderinfo.interfaces.IOrderInfoSV;
import com.ai.baas.bmc.api.orderinfo.params.OrderExt;
import com.ai.baas.bmc.api.orderinfo.params.OrderInfoParams;
import com.ai.baas.bmc.api.orderinfo.params.Product;
import com.ai.baas.bmc.service.business.interfaces.IOrderinfoBusiSV;
import com.ai.baas.bmc.util.BusinessUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.BaseResponse;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;

@Service(validation = "true")
@Component
public class OrderInfoSVImpl implements IOrderInfoSV {
    @Autowired
    private IOrderinfoBusiSV business;

    @Override
    public BaseResponse orderInfo(OrderInfoParams request) throws BusinessException,
            SystemException {
        BaseResponse resultCode = new BaseResponse();

        // 入参检验
        BusinessUtil.checkBaseInfo(request);
        if (StringUtil.isBlank(request.getTradeSeq())) {
            throw new BusinessException("交易流水为空");
        }
        if (StringUtil.isBlank(request.getExtCustId())) {
            throw new BusinessException("外部客户ID为空");
        }
        if (StringUtil.isBlank(request.getUsetype())) {
            throw new BusinessException("订购类型为空");
        }
        if (StringUtil.isBlank(request.getServiceId())) {
            throw new BusinessException("服务标识为空");
        }
        if (!CollectionUtil.isEmpty(request.getOrderExtInfo())) {
            for (OrderExt orderExt : request.getOrderExtInfo()) {
                if (StringUtil.isBlank(orderExt.getExtName())) {
                    throw new BusinessException("订购扩展信息名称为空");
                }
                if (StringUtil.isBlank(orderExt.getExtValue())) {
                    throw new BusinessException("订购扩展信息值为空");
                }
                if (StringUtil.isBlank(orderExt.getUpdateFlag())) {
                    throw new BusinessException("订购扩展信息更新标识为空");

                }
            }
        }
        if (!CollectionUtil.isEmpty(request.getProductList())) {
            for (Product product : request.getProductList()) {
                if (StringUtil.isBlank(product.getProductId())) {
                    throw new BusinessException("产品ID为空");
                }
                if (StringUtil.isBlank(product.getProductType())) {
                    throw new BusinessException("产品类型为空");
                }
                if ((product.getProductNumber() == null) || product.getProductNumber() == 0) {
                    throw new BusinessException("产品数量为空");
                }
                if (StringUtil.isBlank(product.getActiveTime())) {
                    throw new BusinessException("产品生效时间为空");
                }
                if (StringUtil.isBlank(product.getInactiveTime())) {
                    throw new BusinessException("产品失效时间为空");

                }
            }
        }
        // 幂等性判断（判重）
        // try {
        // if (business.hasSeq(record)) {
        // resultParams.setResponseHeader(new ResponseHeader(false, "000001", "tradeSeq已存在"));
        // return resultParams;
        // }
        // } catch (IOException e) {
        // LoggerUtil.log.error(e);
        // resultParams.setResponseHeader(new ResponseHeader(false, "000001", "幂等性判断失败"));
        // return resultParams;
        // }

        // 写入MySQL表中
        business.writeData(request);

        resultCode.setResponseHeader(new ResponseHeader(true, "000000", "成功"));
        return resultCode;

    }

    @Override
    public BaseResponse initCustomerRelatedInfo(InitCustomerRelatedInfoReq request) throws BusinessException, SystemException {
        if (StringUtil.isBlank(request.getTenantId())) {
            throw new BusinessException("租户ID为空");
        }
        if (StringUtil.isBlank(request.getExtCustId())) {
            throw new BusinessException("外部客户ID为空");
        }

        BaseResponse response = new BaseResponse();
        try {
            business.initInfo(request);
            response.setResponseHeader(new ResponseHeader(true, "000000", "客户、账户信息初始化成功"));
        } catch (Exception e) {
            response.setResponseHeader(new ResponseHeader(false, "000001", "客户、账户信息初始化失败"));
            throw new BusinessException("初始化客户、账户信息失败",e);
        }

        return response;
    }

}
