package com.ai.baas.smc.cache;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ai.baas.smc.constants.SmcCacheConstant;
import com.ai.baas.smc.constants.SmcConstants;
import com.ai.baas.smc.dao.mapper.bo.StlBillDetailStyleItem;
import com.ai.baas.smc.dao.mapper.bo.StlBillDetailStyleItemCriteria;
import com.ai.baas.smc.dao.mapper.bo.StlBillStyle;
import com.ai.baas.smc.dao.mapper.bo.StlBillStyleCriteria;
import com.ai.baas.smc.dao.mapper.factory.MapperFactory;
import com.ai.opt.sdk.cache.base.AbstractCache;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import com.alibaba.fastjson.JSON;

@Component
public class BillStyleCache extends AbstractCache {

    @Override
    public void write() throws Exception {
        StlBillStyleCriteria criteria = new StlBillStyleCriteria();
        criteria.createCriteria().andStateEqualTo(SmcConstants.StlBillStyle.State.NORMAL);
        List<StlBillStyle> billStyles = MapperFactory.getStlBillStyleMapper().selectByExample(
                criteria);
        if (CollectionUtil.isEmpty(billStyles)) {
            return;
        }
        ICacheClient cacheClient = MCSClientFactory
                .getCacheClient(SmcCacheConstant.NameSpace.BILL_STYLE_CACHE);
        for (StlBillStyle billStyle : billStyles) {
            // key:tenantId.billStyleSn,value:StlBillStyle
            StringBuilder keyOne = new StringBuilder();
            keyOne.append(billStyle.getTenantId());
            keyOne.append(".");
            keyOne.append(billStyle.getBillStyleSn());
            cacheClient.hset(SmcCacheConstant.NameSpace.BILL_STYLE_CACHE, keyOne.toString(),
                    JSON.toJSONString(billStyle));
            // key:#tenantId#.#billStyleSn#.#bill.detail.item#,value:List<StlBillDetailStyleItem>
            StlBillDetailStyleItemCriteria billDetailStyleItemCriteria = new StlBillDetailStyleItemCriteria();
            billDetailStyleItemCriteria.setOrderByClause("sort_id");
            billDetailStyleItemCriteria.createCriteria().andBillStyleIdEqualTo(
                    billStyle.getBillStyleId());
            List<StlBillDetailStyleItem> stlBillDetailStyleItems = MapperFactory
                    .getStlBillDetailStyleItemMapper().selectByExample(billDetailStyleItemCriteria);
            if (!CollectionUtil.isEmpty(stlBillDetailStyleItems)) {
                StringBuilder keyTwo = new StringBuilder();
                keyTwo.append(billStyle.getTenantId());
                keyTwo.append(".");
                keyTwo.append(billStyle.getBillStyleSn());
                keyTwo.append(".");
                keyTwo.append(SmcCacheConstant.BILL_DETAIL_ITEM);
                cacheClient.hset(SmcCacheConstant.NameSpace.BILL_STYLE_CACHE, keyTwo.toString(),
                        JSON.toJSONString(stlBillDetailStyleItems));
            }
        }
    }

}
