package com.ai.baas.op.web.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ai.baas.bmc.api.baseInfo.interfaces.IBaseInfoSV;
import com.ai.baas.bmc.api.baseInfo.params.BaseCode;
import com.ai.baas.bmc.api.baseInfo.params.BaseCodeInfo;
import com.ai.baas.bmc.api.baseInfo.params.QueryInfoParams;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.dubbo.util.DubboConsumerFactory;

public final class CustGradeUtil {
	
	private static final Logger LOG = Logger
			.getLogger(CustGradeUtil.class);
	
    public static String getCustLevelName(String code) {
        Map<String,String> map =new HashMap<String,String>();
        QueryInfoParams param = new QueryInfoParams();
        try{
            param.setTradeSeq(TradeSeqUtil.newTradeSeq("PUB"));
            param.setTenantId("PUB");
            param.setParamType("CUST_LEVEL");
            IBaseInfoSV iBaseInfoSV = DubboConsumerFactory.getService("iBaseInfoSV");
            BaseCodeInfo info = iBaseInfoSV.getBaseInfo(param);
            if(info!=null){
                List<BaseCode> list = info.getParamList();
                if(!CollectionUtil.isEmpty(list)){
                    for(BaseCode c:list){
                        map.put(c.getParamCode(), c.getParamName()); 
                    }
                }
                if(map!=null){
                  return  map.get(code);
                }
            }
        }catch(Exception e){
        	LOG.info("获取数据失败", e);
        }
        return null;
    }
}
