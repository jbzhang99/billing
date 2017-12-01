//package com.ailk.mvno.crm.subs.action.subssync.group;
//
//import java.sql.Timestamp;
//import java.util.List;
//
//import com.ai.mvno.uip.pi.api.retailInfo.IRetailInfo2SV;
//import com.ai.mvno.uip.pi.api.retailInfo.bss133.DTBSS133Req;
//import com.ai.mvno.uip.pi.api.retailInfo.bss133.DTBSS133Resp;
//import com.ai.mvno.uip.pi.api.retailInfo.bss133.DTBSS133Resp.ITEMS.ITEM;
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
// * 
// * Title: MVNO-CRM <br>
// * Description: 购家电送话费活动同步UIP<br>
// * Date: 2015年2月8日 <br>
// * Copyright (c) 2015 AILK <br>
// * 
// * @author zhangxianwei
// */
//public class GroupGomeActiveInfoSync extends AbstractGroupSubsSync {
//
//    @Override
//    public Object constructRequest(String reqBody) throws Exception {
//        DTBSS133Req obj = JSON.parseObject(reqBody, DTBSS133Req.class);
//        return obj;
//    }
//
//    @Override
//    public SyncResult process(Object request, IBOSUBSSyncSubscribeValue subscribe,
//            IBOSUBSSyncRecordValue record) throws Exception {
//        if (request == null) {
//            throw new SystemException("购家电送话费活动信息同步失败:没有传入请求参数");
//        }
//        if (!(request instanceof DTBSS133Req)) {
//            throw new SystemException("购家电送话费活动信息同步失败:传入请求类型不匹配");
//        }
//        DTBSS133Req dtbss133Rep = (DTBSS133Req) request;
//        /* 1.获取UIP的DUBBO服务 */
//        IRetailInfo2SV sv = DubboConsoumerFactory.getService("retailInfo2SV", IRetailInfo2SV.class);
//        if (sv == null) {
//            throw new SystemException("购家电送话费活动信息同步失败:没有获取到同步服务，请检查UIP的部署");
//        }
//        /* 2.调用服务 */
//        SyncResult result = new SyncResult();
//        Timestamp reqAckTime = null;
//        String reqAckBody = null;
//        String reqAckStatus = null;
//        String reqAckMessage = null;
//        String state = null;
//        DTBSS133Resp response = sv.retailInfo2(dtbss133Rep);
//        List<ITEM> items = response.getITEMS().getITEM();
//        reqAckBody = JSON.toJSONString(response);
//        if (!CollectionUtil.isEmpty(items)) {
//            ITEM item = items.get(0);
//            reqAckTime = DateUtil.getSysDate();
//            reqAckStatus = item.getRECODE();
//            reqAckMessage = item.getREMSG();
//            if ("S".equals(reqAckStatus)) {
//                state = SubsConstants.SubsSyncSubscribeRecord.State.SUCCESS;
//            } else {
//                state = SubsConstants.SubsSyncSubscribeRecord.State.FAILURE;
//            }
//            /* 组织结果数据 */
//            result.setReqAckTime(reqAckTime);
//            result.setReqAckBody(reqAckBody);
//            result.setReqAckStatus(reqAckStatus);
//            result.setReqAckMessage(reqAckMessage);
//            result.setStateDesc(reqAckMessage);
//            result.setState(state);
//        }
//        return result;
//    }
//}
