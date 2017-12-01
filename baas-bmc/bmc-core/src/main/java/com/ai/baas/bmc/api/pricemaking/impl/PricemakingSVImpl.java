package com.ai.baas.bmc.api.pricemaking.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.ai.baas.bmc.api.pricemaking.interfaces.IPricemakingSV;
import com.ai.baas.bmc.api.pricemaking.params.Cost;
import com.ai.baas.bmc.api.pricemaking.params.CostInfo;
import com.ai.baas.bmc.api.pricemaking.params.ElementInfo;
import com.ai.baas.bmc.api.pricemaking.params.ExtInfo;
import com.ai.baas.bmc.api.pricemaking.params.FeeInfo;
import com.ai.baas.bmc.api.pricemaking.params.OrderTypeInfo;
import com.ai.baas.bmc.api.pricemaking.params.PriceElementInfo;
import com.ai.baas.bmc.api.pricemaking.params.PriceElementInfoZX;
import com.ai.baas.bmc.api.pricemaking.params.PriceInfo;
import com.ai.baas.bmc.api.pricemaking.params.PricemakingResponseZX;
import com.ai.baas.bmc.api.pricemaking.params.ResponseMessage;
import com.ai.baas.bmc.api.pricemaking.params.ShoppingList;
import com.ai.baas.bmc.constants.BmcConstants;
import com.ai.baas.bmc.constants.BmcConstants.ZxServiceId;
import com.ai.baas.bmc.constants.BmcConstants.CpPricemakingFactor.FactorName;
import com.ai.baas.bmc.service.business.interfaces.IPricemakingBusiSV;
import com.ai.baas.bmc.util.BusinessUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.dubbo.config.annotation.Service;

@Service
public class PricemakingSVImpl implements IPricemakingSV {
    @Autowired
    private IPricemakingBusiSV iPricemakingBusiSV;

    @Override
    public PricemakingResponseZX queryPricemakingZX(PriceElementInfoZX request)
            throws BusinessException, SystemException {
        if (request == null) {
            throw new BusinessException(ExceptCodeConstants.Special.PARAM_IS_NULL, "请求报文为空为空");
        }
        if (CollectionUtil.isEmpty(request.getShopping_lists())) {
            throw new BusinessException(ExceptCodeConstants.Special.PARAM_IS_NULL, "购买的清单不能为空");
        }
        for (ShoppingList shoppingList : request.getShopping_lists()) {
            if (StringUtil.isBlank(shoppingList.getService_id())) {
                throw new BusinessException(ExceptCodeConstants.Special.PARAM_IS_NULL,
                        "service_id不能为空");
            }
            if (null == (shoppingList.getParameters())) {
                throw new BusinessException(ExceptCodeConstants.Special.PARAM_IS_NULL,
                        "parameters不能为空");
            }
        }
        List<CostInfo> detail_costs = new ArrayList<CostInfo>();
        for (ShoppingList shoppingList : request.getShopping_lists()) {
            // 参数转换
            PriceElementInfo priceElementInfo = assembleRequest(shoppingList);
            // 业务层实现
            List<PriceInfo> priceInfoList = iPricemakingBusiSV.queryPricemaking(priceElementInfo);
            // 参数转换
            List<CostInfo> detail_costs1 = assembleResponse(priceInfoList);
            detail_costs.addAll(detail_costs1);
        }

        PricemakingResponseZX response = new PricemakingResponseZX();
        response.setResponseHeader(new ResponseHeader(true, ExceptCodeConstants.Special.SUCCESS,
                "成功"));
        response.setDetail_costs(detail_costs);
        return response;
    }

    private List<CostInfo> assembleResponse(List<PriceInfo> priceInfoList) {
        List<CostInfo> costInfos = new ArrayList<CostInfo>();
        CostInfo costInfo = new CostInfo();
        for (PriceInfo priceInfo : priceInfoList) {
            costInfo.setList_id(priceInfo.getListId());
            for (FeeInfo feeInfo : priceInfo.getFeeInfos()) {
                boolean isEmpty = true;
                Cost cost = null;
                if (!CollectionUtil.isEmpty(costInfo.getCost())) {
                    isEmpty = false;
                    for (Cost costTmp : costInfo.getCost()) {
                        if (costTmp.getCost_unit().equals(feeInfo.getPriceUnit())) {
                            cost = costTmp;
                            break;
                        }
                    }
                }
                if (cost != null) {
                    cost.setCost_value(String.valueOf(Double.parseDouble(cost.getCost_value())
                            + Double.parseDouble(feeInfo.getPrice())));
                    if (!StringUtil.isBlank(feeInfo.getPriceUnitName())) {
                        if (!cost.getCost_unit_name().equals(feeInfo.getPriceUnitName())) {
                            cost.setCost_unit_name(cost.getCost_unit_name() + ","
                                    + feeInfo.getPriceUnitName());
                        }
                    }
                } else {
                    cost = new Cost();
                    cost.setCost_unit(feeInfo.getPriceUnit());
                    cost.setCost_unit_name(feeInfo.getPriceUnitName());
                    cost.setCost_value(feeInfo.getPrice());
                    if (BmcConstants.CpPricemakingRule.PriceUnit.HOUR.equals(cost.getCost_unit())) {
                        cost.setCost_name("配置费用");
                    } else if (BmcConstants.CpPricemakingRule.PriceUnit.GB.equals(cost
                            .getCost_unit())) {
                        cost.setCost_name("公网流量费用");
                    } else {
                        cost.setCost_name("配置费用");
                    }
                    if (isEmpty) {
                        List<Cost> list = new ArrayList<Cost>();
                        list.add(cost);
                        costInfo.setCost(list);
                    } else {
                        List<Cost> list = costInfo.getCost();
                        list.add(cost);
                    }
                }
            }
        }
        if (!CollectionUtil.isEmpty(costInfo.getCost())) {
            for (Cost costTmp : costInfo.getCost()) {
                costTmp.setCost_value(new BigDecimal(costTmp.getCost_value()).setScale(2,
                        BigDecimal.ROUND_HALF_UP).toString());
            }
        }
        costInfos.add(costInfo);

        return costInfos;
    }

    private PriceElementInfo assembleRequest(ShoppingList shoppingList) {
        this.setDefault(shoppingList);
        List<OrderTypeInfo> orderTypeList = new ArrayList<OrderTypeInfo>();
        String service_id = shoppingList.getService_id();
        if (BmcConstants.ZxServiceId.ECS.equals(service_id)) {
            assembleEcsPriceElementInfo(orderTypeList, shoppingList);
        } else if (BmcConstants.ZxServiceId.RDS.equals(service_id)) {
            assembleRdsElementInfo(shoppingList, orderTypeList);
        } else if (BmcConstants.ZxServiceId.CS.equals(service_id)) {
            throw new BusinessException("暂不支持此类定价:[" + service_id + "]");
        } else if (BmcConstants.ZxServiceId.OSS.equals(service_id)) {
            throw new BusinessException("暂不支持此类定价:[" + service_id + "]");
        } else if (BmcConstants.ZxServiceId.ONS.equals(service_id)) {
            throw new BusinessException("暂不支持此类定价:[" + service_id + "]");
        } else if (BmcConstants.ZxServiceId.KVS.equals(service_id)) {
            assembleKvsElementInfo(shoppingList, orderTypeList);
        } else if (BmcConstants.ZxServiceId.SMART_CLOUD.equals(service_id)) {
            assembleGeneralElementInfo(shoppingList, orderTypeList);
            setPriceType(orderTypeList,
                    BmcConstants.CpPricemakingFactor.PriceProductType.SMART_CLOUD);
        } else if (BmcConstants.ZxServiceId.SLB.equals(service_id)) {
            assembleSlbElementInfo(shoppingList, orderTypeList);
        } else if (BmcConstants.ZxServiceId.EIP.equals(service_id)) {
            assembleEipElementInfo(shoppingList, orderTypeList);
        } else {
            assembleGeneralElementInfo(shoppingList, orderTypeList);
            setPriceType(orderTypeList, service_id);
        }

        PriceElementInfo priceElementInfo = new PriceElementInfo();
        priceElementInfo.setTenantId(BmcConstants.TenantId.ZX);
        priceElementInfo.setOrderTypeList(orderTypeList);
        return priceElementInfo;
    }

    private void assembleEipElementInfo(ShoppingList shoppingList, List<OrderTypeInfo> orderTypeList) {
        // 实例（一个）
        orderTypeList.add(this.assembleGeneralElementInfo(shoppingList, BmcConstants.CpPricemakingFactor.PriceProductType.EIP_INSTANCE));
        // 网络一个
        orderTypeList.add(this.assembleGeneralElementInfo(shoppingList, BmcConstants.CpPricemakingFactor.PriceProductType.EIP_BANDWITH));
    }

    /**
     * 如果价格因子不是必填的，则需要赋默认值
     * @param shoppingList
     * @author mayt
     */
    private void setDefault(ShoppingList shoppingList) {
        Map<String, String> map = shoppingList.getParameters();
        if (ZxServiceId.WAF.equals(shoppingList.getService_id())) {
            if (StringUtil.isBlank(map.get(FactorName.EXTDOMAINPACKAGE))) {
                map.put(FactorName.EXTDOMAINPACKAGE, "0");
            }
            if (StringUtil.isBlank(map.get(FactorName.EXTBANDWIDTH))) {
                map.put(FactorName.EXTBANDWIDTH, "0");
            }
        }
    }

    private void assembleSlbElementInfo(ShoppingList shoppingList, List<OrderTypeInfo> orderTypeList) {
        // 设定默认值
        this.setSlbDefaultParams(shoppingList.getParameters());
        // 实例（一个）
        orderTypeList.add(this.assembleGeneralElementInfo(shoppingList, BmcConstants.CpPricemakingFactor.PriceProductType.SLB_INSTANCE));
        // 网络一个
        orderTypeList.add(this.assembleGeneralElementInfo(shoppingList, BmcConstants.CpPricemakingFactor.PriceProductType.SLB_BANDWITH));
    }

    private void setSlbDefaultParams(Map<String, String> map) {
        if (!map.containsKey(BmcConstants.CpPricemakingFactor.FactorName.BANDWIDTH) || StringUtil.isBlank(map.get(BmcConstants.CpPricemakingFactor.FactorName.BANDWIDTH))) {
            map.put(BmcConstants.CpPricemakingFactor.FactorName.BANDWIDTH, "0");
        }
    }

    private void setPriceType(List<OrderTypeInfo> orderTypeList, String priceType) {
        for (OrderTypeInfo orderTypeInfo : orderTypeList) {
            orderTypeInfo.setPriceType(priceType);
        }
    }

    private void assembleGeneralElementInfo(ShoppingList shoppingList,
            List<OrderTypeInfo> orderTypeList) {
        List<ElementInfo> elementInfoList = new ArrayList<ElementInfo>();
        Map<String, String> map = (shoppingList.getParameters());
        for (Entry<String, String> entry : map.entrySet()) {
            ElementInfo elementInfo = new ElementInfo();
            elementInfo.setName(entry.getKey());
            elementInfo.setValue(entry.getValue());
            elementInfoList.add(elementInfo);
        }
        ElementInfo elementInfo2 = new ElementInfo();
        elementInfo2.setName(BmcConstants.CpPricemakingRule.INNER_PRICE_TYPE);
        elementInfo2.setValue(BmcConstants.CpPricemakingRule.PriceType.PER_HOUR);
        elementInfoList.add(elementInfo2);

        OrderTypeInfo orderTypeInfo = new OrderTypeInfo();
        String listId = shoppingList.getList_id();
        orderTypeInfo.setListId(listId);
        orderTypeInfo.setElementInfoList(elementInfoList);
        orderTypeInfo.setPriceType(shoppingList.getService_id());
        orderTypeList.add(orderTypeInfo);
    }

    private OrderTypeInfo assembleGeneralElementInfo(ShoppingList shoppingList, String priceType) {
        if (StringUtil.isBlank(priceType)) {
            priceType = shoppingList.getService_id();
        }
        List<ElementInfo> elementInfoList = new ArrayList<ElementInfo>();
        Map<String, String> map = (shoppingList.getParameters());
        for (Entry<String, String> entry : map.entrySet()) {
            ElementInfo elementInfo = new ElementInfo();
            elementInfo.setName(entry.getKey());
            elementInfo.setValue(entry.getValue());
            elementInfoList.add(elementInfo);
        }

        OrderTypeInfo orderTypeInfo = new OrderTypeInfo();
        String listId = shoppingList.getList_id();
        orderTypeInfo.setListId(listId);
        orderTypeInfo.setElementInfoList(elementInfoList);
        orderTypeInfo.setPriceType(priceType);
        return orderTypeInfo;
    }
    
    private void assembleKvsElementInfo(ShoppingList shoppingList, List<OrderTypeInfo> orderTypeList) {
        List<ElementInfo> elementInfoList = new ArrayList<ElementInfo>();
        Map<String, String> map = (shoppingList.getParameters());
        for (Entry<String, String> entry : map.entrySet()) {
            ElementInfo elementInfo = new ElementInfo();
            elementInfo.setName(entry.getKey());
            elementInfo.setValue(entry.getValue());
            elementInfoList.add(elementInfo);
        }
        ElementInfo elementInfo2 = new ElementInfo();
        elementInfo2.setName(BmcConstants.CpPricemakingRule.INNER_PRICE_TYPE);
        elementInfo2.setValue(BmcConstants.CpPricemakingRule.PriceType.PER_HOUR);
        elementInfoList.add(elementInfo2);

        OrderTypeInfo orderTypeInfo = new OrderTypeInfo();
        String listId = shoppingList.getList_id();
        orderTypeInfo.setListId(listId);
        orderTypeInfo.setElementInfoList(elementInfoList);
        orderTypeInfo.setPriceType(BmcConstants.CpPricemakingFactor.PriceProductType.KVS);
        orderTypeList.add(orderTypeInfo);
    }

    private void assembleRdsElementInfo(ShoppingList shoppingList, List<OrderTypeInfo> orderTypeList) {
        List<ElementInfo> elementInfoList = new ArrayList<ElementInfo>();
        Map<String, String> map = (shoppingList.getParameters());
        for (Entry<String, String> entry : map.entrySet()) {
            ElementInfo elementInfo = new ElementInfo();
            elementInfo.setName(entry.getKey());
            elementInfo.setValue(entry.getValue());
            elementInfoList.add(elementInfo);
        }
        ElementInfo elementInfo2 = new ElementInfo();
        elementInfo2.setName(BmcConstants.CpPricemakingRule.INNER_PRICE_TYPE);
        elementInfo2.setValue(BmcConstants.CpPricemakingRule.PriceType.PER_HOUR);
        elementInfoList.add(elementInfo2);

        OrderTypeInfo orderTypeInfo = new OrderTypeInfo();
        String listId = shoppingList.getList_id();
        orderTypeInfo.setListId(listId);
        orderTypeInfo.setElementInfoList(elementInfoList);
        orderTypeInfo.setPriceType(BmcConstants.CpPricemakingFactor.PriceProductType.RDS);
        orderTypeList.add(orderTypeInfo);
    }

    private void assembleEcsPriceElementInfo(List<OrderTypeInfo> orderTypeList,
            ShoppingList shoppingList) {
        String listId = shoppingList.getList_id();
        // 实例（一个）
        List<ElementInfo> elementInfoList = new ArrayList<ElementInfo>();
        Map<String, String> map = (shoppingList.getParameters());
        this.setEcsDefaultParams(map);
        for (Entry<String, String> entry : map.entrySet()) {
                ElementInfo elementInfo = new ElementInfo();
                elementInfo.setName(entry.getKey());
                elementInfo.setValue(entry.getValue());
                elementInfoList.add(elementInfo);
        }
        ElementInfo elementInfo2 = new ElementInfo();
        elementInfo2.setName(BmcConstants.CpPricemakingRule.INNER_PRICE_TYPE);
        elementInfo2.setValue(BmcConstants.CpPricemakingRule.PriceType.PER_HOUR);
        elementInfoList.add(elementInfo2);

        OrderTypeInfo orderTypeInfo = new OrderTypeInfo();
        orderTypeInfo.setListId(listId);
        orderTypeInfo.setElementInfoList(elementInfoList);
        orderTypeInfo.setPriceType(BmcConstants.CpPricemakingFactor.PriceProductType.ECS_INSTANCE);
        orderTypeList.add(orderTypeInfo);
        // 系统盘一个
        elementInfoList = new ArrayList<ElementInfo>();
        for (Entry<String, String> entry : map.entrySet()) {
                ElementInfo elementInfo = new ElementInfo();
                elementInfo.setName(entry.getKey());
                elementInfo.setValue(entry.getValue());
                elementInfoList.add(elementInfo);
        }
        ElementInfo elementInfo21 = new ElementInfo();
        elementInfo21.setName(BmcConstants.CpPricemakingRule.INNER_PRICE_TYPE);
        elementInfo21.setValue(BmcConstants.CpPricemakingRule.PriceType.PER_HOUR);
        elementInfoList.add(elementInfo21);

        orderTypeInfo = new OrderTypeInfo();
        orderTypeInfo.setListId(listId);
        orderTypeInfo.setElementInfoList(elementInfoList);
        orderTypeInfo
                .setPriceType(BmcConstants.CpPricemakingFactor.PriceProductType.ECS_SYSTEM_DISK);
        orderTypeList.add(orderTypeInfo);
        // 数据盘多个
        Map<String, Map<String, String>> dataDickMap = new HashMap<String, Map<String, String>>();
        for (Entry<String, String> entry : map.entrySet()) {
            if (entry.getKey().startsWith("DataDisk".toLowerCase())) {
                String seq = entry.getKey().split("\\.")[1];
                if (dataDickMap.containsKey(seq)) {
                    dataDickMap.get(seq).put(entry.getKey().replace(seq, "n"), entry.getValue());
                } else {
                    Map<String, String> mapTmp = new HashMap<String, String>();
                    mapTmp.put(entry.getKey().replace(seq, "n"), entry.getValue());
                    dataDickMap.put(seq, mapTmp);
                }
            }
        }
        if (!dataDickMap.isEmpty()) {
            for (Entry<String, Map<String, String>> entry : dataDickMap.entrySet()) {
                for (Entry<String, String> entry1 : map.entrySet()) {
                        entry.getValue().put(entry1.getKey(), entry1.getValue());
                }
            }
            elementInfoList = new ArrayList<ElementInfo>();
            for (Entry<String, Map<String, String>> entry : dataDickMap.entrySet()) {
                for (Entry<String, String> entry2 : entry.getValue().entrySet()) {
                    ElementInfo elementInfo = new ElementInfo();
                    elementInfo.setName(entry2.getKey());
                    elementInfo.setValue(entry2.getValue());
                    elementInfoList.add(elementInfo);
                }
                ElementInfo elementInfo211 = new ElementInfo();
                elementInfo211.setName(BmcConstants.CpPricemakingRule.INNER_PRICE_TYPE);
                elementInfo211.setValue(BmcConstants.CpPricemakingRule.PriceType.PER_HOUR);
                elementInfoList.add(elementInfo211);

                orderTypeInfo = new OrderTypeInfo();
                orderTypeInfo.setListId(listId);
                orderTypeInfo.setElementInfoList(elementInfoList);
                orderTypeInfo
                        .setPriceType(BmcConstants.CpPricemakingFactor.PriceProductType.ECS_DATA_DISK);
                orderTypeList.add(orderTypeInfo);
            }
        }
        // 网络一个
        elementInfoList = new ArrayList<ElementInfo>();
        for (Entry<String, String> entry : map.entrySet()) {
                ElementInfo elementInfo = new ElementInfo();
                elementInfo.setName(entry.getKey());
                elementInfo.setValue(entry.getValue());
                elementInfoList.add(elementInfo);
        }
        orderTypeInfo = new OrderTypeInfo();
        orderTypeInfo.setListId(listId);
        orderTypeInfo.setElementInfoList(elementInfoList);
        orderTypeInfo.setPriceType(BmcConstants.CpPricemakingFactor.PriceProductType.ECS_BANDWITH);
        orderTypeList.add(orderTypeInfo);
    }

    private void setEcsDefaultParams(Map<String, String> map) {
        if (!map.containsKey(BmcConstants.CpPricemakingFactor.FactorName.INSTANCE_CHARGE_TYPE) || StringUtil.isBlank(map.get(BmcConstants.CpPricemakingFactor.FactorName.INSTANCE_CHARGE_TYPE))) {
            map.put(BmcConstants.CpPricemakingFactor.FactorName.INSTANCE_CHARGE_TYPE, BmcConstants.CpPricemakingFactor.FactorValue.POST_PAID);
        }
        if (!map.containsKey(BmcConstants.CpPricemakingFactor.FactorName.INTERNET_MAX_BANDWIDTH_OUT) || StringUtil.isBlank(map.get(BmcConstants.CpPricemakingFactor.FactorName.INTERNET_MAX_BANDWIDTH_OUT))) {
            map.put(BmcConstants.CpPricemakingFactor.FactorName.INTERNET_MAX_BANDWIDTH_OUT, "0");
        }
        String imageId = map.get(BmcConstants.CpPricemakingFactor.FactorName.IMAGE_ID);
        if (!StringUtil.isBlank(imageId)) {
            if (imageId.contains("win")) {
                map.put(BmcConstants.CpPricemakingFactor.FactorName.OPERATING_SYSTEM, BmcConstants.CpPricemakingFactor.FactorValue.WINDOWS);
            } else {
                map.put(BmcConstants.CpPricemakingFactor.FactorName.OPERATING_SYSTEM, BmcConstants.CpPricemakingFactor.FactorValue.OTHER);
            }
        }
    }

    @Override
    public ResponseMessage queryPricemaking(PriceElementInfo request) throws BusinessException,
            SystemException {
        // 参数校验
        BusinessUtil.checkBaseInfo(request);
        if (StringUtil.isBlank(request.getTradeSeq())) {
            throw new BusinessException("交易流水为空");
        }
        if (CollectionUtil.isEmpty(request.getOrderTypeList())) {
            throw new BusinessException("类型列表为空");
        }
        for (OrderTypeInfo orderTypeInfo : request.getOrderTypeList()) {
            if (StringUtil.isBlank(orderTypeInfo.getListId())) {
                throw new BusinessException("list_id为空");
            }
            if (StringUtil.isBlank(orderTypeInfo.getPriceType())) {
                throw new BusinessException("定价类型为空");
            }
            if (CollectionUtil.isEmpty(orderTypeInfo.getElementInfoList())) {
                throw new BusinessException("定价元素列表列表为空");
            }
            for (ElementInfo elementInfo : orderTypeInfo.getElementInfoList()) {
                if (StringUtil.isBlank(elementInfo.getName())) {
                    throw new BusinessException("元素名称为空");
                }
                if (StringUtil.isBlank(elementInfo.getValue())) {
                    throw new BusinessException("元素值为空");
                }
            }
        }
        if (!CollectionUtil.isEmpty(request.getExtInfoList())) {
            for (ExtInfo extInfo : request.getExtInfoList()) {
                if (StringUtil.isBlank(extInfo.getExtName())) {
                    throw new BusinessException("扩展信息名称为空");
                }
                if (StringUtil.isBlank(extInfo.getExtValue())) {
                    throw new BusinessException("扩展信息值为空");
                }
            }
        }

        ResponseMessage response = new ResponseMessage();
        response.setResponseHeader(new ResponseHeader(true, ExceptCodeConstants.Special.SUCCESS,
                "成功"));
        List<PriceInfo> priceInfoList = iPricemakingBusiSV.queryPricemaking(request);
        response.setPriceInfoList(priceInfoList);
        return response;
    }

}
