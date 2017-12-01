//package com.ailk.mvno.crm.subs.action.subssync.task;
//
//import java.util.concurrent.CountDownLatch;
//
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import com.ailk.mvno.crm.subs.action.subssync.vo.SubsSyncDataSets;
//import com.ailk.mvno.crm.subs.ivalues.IBOSUBSSyncSubscribeRecordValue;
//import com.ailk.mvno.crm.subs.util.SubsServiceUtil;
//import com.ailk.mvno.crm.utils.taskprocess.base.AbstractBaseProcess;
//import com.ailk.mvno.crm.utils.taskprocess.base.AbstractDataSets;
//import com.ailk.mvno.crm.utils.taskprocess.core.ProcessParam;
//import com.ailk.mvno.crm.utils.taskprocess.threads.ProcessThreadPool;
//import com.ailk.mvno.crm.utils.util.CollectionUtil;
//import com.ailk.mvno.crm.utils.util.ExceptionUtil;
//import com.alibaba.fastjson.JSON;
//
///**
// * Title: MVNO-CRM <br>
// * Description:数据同步定时任务 <br>
// * Date: 2014年8月25日 <br>
// * Copyright (c) 2014 AILK <br>
// * 
// * @author zhangchao
// */
//public class SubsSyncProcess extends AbstractBaseProcess {
//
//    private static Log log = LogFactory.getLog(SubsSyncProcess.class);
//
//    @Override
//    public ProcessParam initParam() throws Exception {
//        String conf = this.getProcessCfg(processType);
//        ProcessParam p = JSON.parseObject(conf, ProcessParam.class);
//        return p;
//    }
//
//    @Override
//    public AbstractDataSets fetchData(ProcessParam p) throws Exception {
//        SubsSyncDataSets dataSets = new SubsSyncDataSets();
//        IBOSUBSSyncSubscribeRecordValue[] beans = SubsServiceUtil.getISubsSyncFrameSV()
//                .fetchSubscribeRecords(p.getProcessNo(), p.getProcessTotal(), p.getRecordNum(),
//                        p.getRetryTimes());
//        dataSets.setDatas(beans);
//        return dataSets;
//    }
//
//    @Override
//    public void excute(AbstractDataSets dataSets) throws Exception {
//        if (dataSets == null) {
//            return;
//        }
//        SubsSyncDataSets subsSyncDataSets = (SubsSyncDataSets) dataSets;
//        IBOSUBSSyncSubscribeRecordValue[] datas = subsSyncDataSets.getDatas();
//        if (CollectionUtil.isEmpty(datas)) {
//            log.debug("没有抓取到待处理的数据");
//            return;
//        }
//        CountDownLatch lanch = new CountDownLatch(datas.length);
//        for (IBOSUBSSyncSubscribeRecordValue v : datas) {
//            SubsSyncProcessThread thread = new SubsSyncProcessThread(v, lanch);
//            ProcessThreadPool.getInstance().executeThread(thread);
//        }
//        try {
//            lanch.await();
//        } catch (InterruptedException e) {
//            log.warn("线程执行期间，系统异常，具体的原因是：[" + ExceptionUtil.getTrace(e) + "]");
//        }
//    }
//
//}
