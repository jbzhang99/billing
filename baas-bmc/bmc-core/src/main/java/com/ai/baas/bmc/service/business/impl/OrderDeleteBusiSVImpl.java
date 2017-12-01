package com.ai.baas.bmc.service.business.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.baas.bmc.api.citic.params.InstanceUpdateInfo;
import com.ai.baas.bmc.dao.mapper.bo.BlSubsComm;
import com.ai.baas.bmc.dao.mapper.bo.BlUserinfoZx;
import com.ai.baas.bmc.service.atom.interfaces.IBlSubsCommAtomSV;
import com.ai.baas.bmc.service.atom.interfaces.IBlSubsCommonAtomSV;
import com.ai.baas.bmc.service.atom.interfaces.IBlUserinfoZXAtomSV;
import com.ai.baas.bmc.service.business.interfaces.IOrderDeleteBusiSV;
import com.alibaba.fastjson.JSON;

@Service
public class OrderDeleteBusiSVImpl implements IOrderDeleteBusiSV{
    private static final Logger logger = LogManager.getLogger(OrderDeleteBusiSVImpl.class);
    @Autowired
    private IBlUserinfoZXAtomSV iBlUserinfoZXAtomSV;
    @Autowired
    private IBlSubsCommonAtomSV iBlSubsCommonAtomSV;
    @Autowired
    private IBlSubsCommAtomSV iBlSubsCommAtomSV;
    @Override
    public String OrderDelete(InstanceUpdateInfo instanceUpdateInfo) {
        
        List <BlUserinfoZx> zxList = new ArrayList<>();
        zxList = iBlUserinfoZXAtomSV.getUserInfoZx(instanceUpdateInfo.getInstance_id());
        if(zxList.size() == 0){
            logger.error("未找到instance_id :"+instanceUpdateInfo.getInstance_id()+" 对应的信息");
            return "0";
        }
        logger.info("list.size : "+zxList.size());       
        BlUserinfoZx param = new BlUserinfoZx();
        param = zxList.get(0); 
        String productIds = param.getProductId();   
        if(productIds == null){
            return"0";
        }
        String[] priceCodes = productIds.split(";");
        logger.info("产品数量："+priceCodes.length);
        for(String p :priceCodes){
            logger.info("将productId : "+p+" 的订购信息置为失效");
            try{
                disableOrder(p);
            }catch(Exception e){
                logger.error("修改失败！",e);   
                return "0";
            }
        }     
        return "1";
    }
    private String disableOrder(String p) {
        Timestamp inactiveTime = new Timestamp(System.currentTimeMillis());
        BlSubsComm blSubsComm = new BlSubsComm();
        List <BlSubsComm> blSubsCommList = new ArrayList<>();
        blSubsCommList = iBlSubsCommonAtomSV.getSubsCommonByProductId(p);
        if(blSubsCommList.size()==0){
            logger.info("productId: "+p+"的订购信息不存在");
            return null;
        }
        blSubsComm = blSubsCommList.get(0);
        logger.info("原始订购数据: "+JSON.toJSONString(blSubsComm));
        blSubsComm.setInactiveTime(inactiveTime);
        logger.info("修改失效时间: "+JSON.toJSONString(blSubsComm));
        iBlSubsCommonAtomSV.updateSubsCommon(p, blSubsComm);   
        iBlSubsCommAtomSV.updateDshmData(blSubsComm);
        logger.info("订购删除成功: "+JSON.toJSONString(blSubsComm)); 
        return p;
    }
    

}
