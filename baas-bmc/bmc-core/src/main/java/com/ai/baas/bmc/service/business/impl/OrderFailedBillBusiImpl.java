package com.ai.baas.bmc.service.business.impl;

import com.ai.baas.bmc.api.baseInfo.params.BaseCodeInfo;
import com.ai.baas.bmc.api.baseInfo.params.QueryInfoParams;
import com.ai.baas.bmc.api.failedbills.params.FailedOrderQueryVo;
import com.ai.baas.bmc.api.failedbills.params.FailedOrderVo;
import com.ai.baas.bmc.dao.mapper.bo.BatchFailureBill;
import com.ai.baas.bmc.service.atom.interfaces.IBatchFailureBillAtomSV;
import com.ai.baas.bmc.service.business.interfaces.IBaseInfoBussiness;
import com.ai.baas.bmc.service.business.interfaces.IOrderFailedBillBusiSV;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.sdk.dubbo.util.HttpClientUtil;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.opt.sdk.util.CollectionUtil;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderFailedBillBusiImpl implements IOrderFailedBillBusiSV{
    private static final Logger LOG = LogManager.getLogger(OrderFailedBillBusiImpl.class);
    @Resource
    private IBatchFailureBillAtomSV batchFailureBillAtomSV;
    @Resource
    private IBaseInfoBussiness baseInfoBussiness;

    @Override
    public Integer countFailedOrders(FailedOrderQueryVo queryVo) {
        return batchFailureBillAtomSV.countFailedOrders(queryVo);
    }

    @Override
    public List<FailedOrderVo> queryFailedOrders(FailedOrderQueryVo queryVo) {
        List<BatchFailureBill> failureBills = batchFailureBillAtomSV.queryFailedOrders(queryVo);
        if(!CollectionUtil.isEmpty(failureBills)){
            List<FailedOrderVo> orderVos = new ArrayList<>();
            for(BatchFailureBill failureBill:failureBills){
                FailedOrderVo orderVo = new FailedOrderVo();
                BeanUtils.copyProperties(orderVo,failureBill);
                orderVos.add(orderVo);
            }
            return orderVos;
        }
        return null;
    }

    @Override
    public FailedOrderVo getFailedOrderById(Long id) {
        BatchFailureBill failureBill = batchFailureBillAtomSV.getFailedOrderById(id);
        FailedOrderVo orderVo = null;
        if(failureBill!=null){
            orderVo = new FailedOrderVo();
            BeanUtils.copyProperties(orderVo,failureBill);
        }
        return orderVo;
    }

    @Override
    public void resendOrder(FailedOrderVo orderVo) {
        try {
            BatchFailureBill failureBill = batchFailureBillAtomSV.getFailedOrderById(orderVo.getId());
            if(failureBill!=null){
                QueryInfoParams param = new QueryInfoParams();
                param.setTenantId(failureBill.getTenantId());
                param.setParamType("ORDER_URL");
                BaseCodeInfo baseInfo = baseInfoBussiness.getBaseInfo(param);
                if(baseInfo!=null&&!CollectionUtil.isEmpty(baseInfo.getParamList())){
                    String orderUrl = baseInfo.getParamList().get(0).getParamCode();
                    LOG.info("从baseCode表获取订购url："+orderUrl);
                    HttpClientUtil.sendPost(orderUrl,orderVo.getOrderJson());
                    LOG.info("重发编辑订单错单成功,重发json为："+ orderVo.getOrderJson());
                    batchFailureBillAtomSV.deleteBillById(orderVo.getId());
                }else{
                    throw new BusinessException("未在bmc_basedata_code表配置订购接口URL地址");
                }
            }else {
                LOG.error("原订单错单id【"+orderVo.getId()+"】不存在，无法重发");
            }
        } catch (Exception e) {
            throw new BusinessException("重发编辑订单错单失败",e);
        }
    }
}
