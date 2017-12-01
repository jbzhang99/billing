//package com.ailk.mvno.crm.subs.action.subssync.task;
//
//import java.util.concurrent.CountDownLatch;
//
//import com.ailk.mvno.crm.subs.constants.SubsConstants;
//import com.ailk.mvno.crm.subs.ivalues.IBOSUBSSyncSubscribeRecordValue;
//import com.ailk.mvno.crm.subs.util.SubsServiceUtil;
//import com.ailk.mvno.crm.utils.taskprocess.threads.AbstractProcessThread;
//
//public class SubsSyncProcessThread extends AbstractProcessThread<IBOSUBSSyncSubscribeRecordValue> {
//
//    public SubsSyncProcessThread(IBOSUBSSyncSubscribeRecordValue businessModel, CountDownLatch lanch) {
//        super(businessModel, lanch);
//    }
//
//    @Override
//    public void execute(IBOSUBSSyncSubscribeRecordValue businessModel) throws Exception {
//        // 更新成处理中
//        SubsServiceUtil.getISubsSyncFrameSV().updateSyncSubscribeRecordState(
//                businessModel.getSeqId(), SubsConstants.SubsSyncSubscribeRecord.State.ING,
//                "由处理线程更新成处理中");
//        // 执行数据同步
//        SubsServiceUtil.getISubsSyncFrameSV().process(businessModel.getSeqId());
//    }
//
//}
