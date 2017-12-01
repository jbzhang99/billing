//package com.ailk.mvno.crm.subs.action.subssync.group;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//import com.ai.mvno.uip.pi.api.masterdata.IStorageChargesSendMachine309;
//import com.ai.mvno.uip.pi.api.masterdata.bss309.DTBSS309Req;
//import com.ai.mvno.uip.pi.api.masterdata.bss309.DTBSS309Resp;
//import com.ai.mvno.uip.pi.api.masterdata.bss309.DTBSS309Resp.BSS309;
//import com.ailk.mvno.crm.subs.action.subssync.group.base.AbstractGroupSubsSync;
//import com.ailk.mvno.crm.subs.action.subssync.vo.SyncResult;
//import com.ailk.mvno.crm.subs.constants.SubsConstants;
//import com.ailk.mvno.crm.subs.ivalues.IBOSUBSSyncRecordValue;
//import com.ailk.mvno.crm.subs.ivalues.IBOSUBSSyncSubscribeValue;
//import com.ailk.mvno.crm.utils.consoumer.factory.DubboConsoumerFactory;
//import com.ailk.mvno.crm.utils.exception.SystemException;
//import com.ailk.mvno.crm.utils.util.CollectionUtil;
//import com.ailk.mvno.crm.utils.util.DateUtil;
//import com.alibaba.fastjson.JSON;
//
///**
// * Title: MVNO-CRM <br>
// * Description: 机价补贴卡信息同步金立接口<br>
// * Date: 2014年8月25日 <br>
// * Copyright (c) 2014 AILK <br>
// * 
// * @author zhangchao
// */
//public class GroupJiJiaSubsidycardSync extends AbstractGroupSubsSync {
//
//    @Override
//    public Object constructRequest(String reqBody) throws Exception {
//        DTBSS309Req obj = JSON.parseObject(reqBody, DTBSS309Req.class);
//        return obj;
//    }
//
//    @Override
//    public SyncResult process(Object request, IBOSUBSSyncSubscribeValue subscribe,IBOSUBSSyncRecordValue record) throws Exception {
//        if (request == null) {
//            throw new SystemException("机价补贴卡信息同步失败:没有传入请求参数");
//        }
//        if (!(request instanceof DTBSS309Req)) {
//            throw new SystemException("机价补贴卡信息同步失败:传入请求类型不匹配");
//        }
//        DTBSS309Req subsidyCardRequest = (DTBSS309Req) request;
//        /* 1.获取UIP的DUBBO服务 */
//        IStorageChargesSendMachine309 sv = DubboConsoumerFactory.getService(
//                "IStorageChargesSendMachine309", IStorageChargesSendMachine309.class);
//        if (sv == null) {
//            throw new SystemException("机价补贴卡信息同步失败:没有获取到同步服务，请检查UIP的部署");
//        }
//        /* 2.调用服务 */
//        SyncResult result = new SyncResult();
//        Timestamp reqAckTime = null;
//        String reqAckBody = null;
//        String reqAckStatus = null;
//        String reqAckMessage = null;
//        String state = null;
//        DTBSS309Resp response = sv.StorageChargesSendMachine309(subsidyCardRequest);
//        List<BSS309> bss309s = response.getBSS309();
//        reqAckBody= JSON.toJSONString(response);
//        if(!CollectionUtil.isEmpty(bss309s)){
//            BSS309 bss309 = bss309s.get(0);
//            reqAckTime = DateUtil.getSysDate();
//            reqAckStatus = bss309.getSTATE();
//            reqAckMessage = bss309.getMESSAGE();
//            if("S".equals(bss309.getSTATE())){
//                state = SubsConstants.SubsSyncSubscribeRecord.State.SUCCESS;
//            }else{
//                state = SubsConstants.SubsSyncSubscribeRecord.State.FAILURE;
//            }
//            /* 组织结果数据 */
//            result.setReqAckTime(reqAckTime);
//            result.setReqAckBody(reqAckBody);
//            result.setReqAckStatus(reqAckStatus);
//            result.setReqAckMessage(reqAckMessage);
//            result.setStateDesc(bss309.getMESSAGE());
//            result.setState(state);
//        }
//        return result;
//    }
//}
