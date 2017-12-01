//package com.ailk.mvno.crm.subs.action.subssync.vo;
//
//import com.ailk.mvno.crm.subs.ivalues.IBOSUBSSyncSubscribeRecordValue;
//import com.ailk.mvno.crm.utils.taskprocess.base.AbstractDataSets;
//import com.ailk.mvno.crm.utils.util.CollectionUtil;
//
//public class SubsSyncDataSets extends AbstractDataSets {
//
//    // 每次抓取的订阅发布记录信息
//    private IBOSUBSSyncSubscribeRecordValue[] datas;
//
//    @Override
//    public int getTotal() {
//        return CollectionUtil.isEmpty(datas) ? 0 : datas.length;
//    }
//
//    public IBOSUBSSyncSubscribeRecordValue[] getDatas() {
//        return datas;
//    }
//
//    public void setDatas(IBOSUBSSyncSubscribeRecordValue[] datas) {
//        this.datas = datas;
//    }
//
//}
