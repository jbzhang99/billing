package com.ai.runner.center.bmc.resdeposit.application;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.runner.center.bmc.resdeposit.constants.TypeEnum;
import com.ai.runner.center.bmc.resdeposit.service.interfaces.IDuplicateCheck;
import com.ai.runner.center.bmc.resdeposit.service.interfaces.IFunResBookSv;
import com.ai.runner.center.bmc.resdeposit.service.interfaces.IPackResult;
import com.ai.runner.center.bmc.resdeposit.util.LoggerUtil;
import com.ai.runner.center.bmc.resdeposit.util.MdsUtil;
import com.ai.runner.center.bmc.resdeposit.util.MyJsonUtil;
import com.ai.runner.center.bmc.resdeposit.vo.CommMsg;
import com.ai.runner.center.bmc.resdeposit.vo.FunResBook;
import com.ai.runner.center.bmc.resdeposit.vo.UserMsg;
import com.ai.runner.utils.util.DateUtil;
import com.ai.runner.utils.util.DubboConsumerFactory;
import com.ai.runner.utils.util.StringUtil;
import com.ai.runner.viv.api.balance.interfaces.IResBalanceSV;

@Component
public class EnterAccount {
    @Autowired
    // @Qualifier("testFun")
    private IFunResBookSv aIFunResBookSv;

    @Autowired
    private IDuplicateCheck aIDuplicateCheck;

    @Autowired
    private IPackResult aIPackResult;

    private IResBalanceSV resDepositSv = DubboConsumerFactory.getService("IResDepositSV",
            IResBalanceSV.class);

    // private KafkaUtil kafka = new KafkaUtil(
    // PropUtil.getProp("context/kafka.properties").getProperty("xinkong.topic"));

    public static Random random = new Random();

    /**
     * 用户表触发的入账
     */
    public void userStrike(UserMsg msg) {
        LoggerUtil.log.debug("进入用户表触发入账的方法!");
        // 当service_status为2时表示停机，直接返回
//        if ("2".equals(msg.getSERVICE_STATUS())) {
//            LoggerUtil.log.debug("service_status为2，认定为停机用户");
//            return;
//        }
        for (FunResBook f : aIFunResBookSv.getFunResBook(msg)) {
            LoggerUtil.log.debug("封装后的FunResBook:" + MyJsonUtil.toJson(f));
            enterAccount(f, true);
        }
    }

    /**
     * 资源表触发的入账
     */
    public void commStrike(CommMsg msg) {
        LoggerUtil.log.debug("进入资源表触发入账方法!");
        for (FunResBook c : aIFunResBookSv.getFunResBook(msg)) {
            LoggerUtil.log.debug("封装后的FunResBook:" + MyJsonUtil.toJson(c));
            enterAccount(c, true);
        }
    }

    /**
     * 批量入账触发入账
     */
    public void BatchStrike(UserMsg msg) {
        LoggerUtil.log.debug("进入批量入账触发入账的方法!");
        // 当service_status为2时表示停机，直接返回
//        if ("2".equals(msg.getSERVICE_STATUS())) {
//            LoggerUtil.log.debug("service_status为2，认定为停机用户");
//            return;
//        }

        for (FunResBook f : aIFunResBookSv.getFunResBook(msg)) {
            LoggerUtil.log.debug("封装后的FunResBook:" + MyJsonUtil.toJson(f));
            enterAccount(f, false);
        }
    }

    // 用户表和资源表统一的入账流程
    private void enterAccount(FunResBook book, boolean isThisMonth) {

        if (book == null) {
            return;
        }
        // 当失效时间小于当前时间跳出
        try {
            if (Timestamp.valueOf(book.getINACTIVE_TIME()).getTime() < System.currentTimeMillis()) {
                LoggerUtil.log.debug("失效时间小于当前时间");
                return;
            }
        } catch (Exception e) {
            // try {
            // if (DateUtil.getTimestamp(book.getINACTIVE_TIME(), DateUtil.YYYYMMDDHHMMSS)
            // .getTime() < System.currentTimeMillis()) {
            // LoggerUtil.log.debug("失效时间小于当前时间");
            // return;
            // }
            // } catch (Exception e2) {
            LoggerUtil.log.debug("时间格式错误");
            return;
            // }
        }

        // 对清零标识进行判断，并修改生效时间和失效时间
        Timestamp time;
        String date;
        if (isThisMonth) {
            time = new Timestamp(System.currentTimeMillis());
            date = DateUtil.getDateString(DateUtil.YYYYMM);
            // 如果清零，失效时间为当月最后一秒
            if ("Y".equals(book.getRES_CLEAR_FLAG())) {
                // 生效时间大于当月最后一秒不入账
                try {
                    if (Timestamp.valueOf(book.getACTIVE_TIME()).getTime() > DateUtil
                            .getTimeThisMonthLastSec(time).getTime()) {
                        LoggerUtil.log.debug("实时入账：生效时间大于当月最后一秒不入账");
                        return;
                    }
                } catch (Exception e) {
                    LoggerUtil.log.debug("时间格式错误");
                    return;
                }
                LoggerUtil.log.debug("进行清零设置");
                book.setINACTIVE_TIME(DateUtil.getTimeThisMonthLastSec(time).toString());
            }
        }else {
         // Temp:如果不清零，生效日期为当月第一秒，失效日期为当月最后一秒
            time = DateUtil.getTimeThisMonthFirstSec(new Timestamp(System.currentTimeMillis()));
            date = DateUtil.getDateString(time, DateUtil.YYYYMM);
            if (Timestamp.valueOf(book.getINACTIVE_TIME()).getTime() < time.getTime()) {
                //LoggerUtil.log.debug("批量入账：失效小于下月第一秒，不入账");
                LoggerUtil.log.debug("批量入账：失效小于当月第一秒，不入账");
                return;
            }
            if ("Y".equals(book.getRES_CLEAR_FLAG())) {
                try {
                    if (Timestamp.valueOf(book.getACTIVE_TIME()).getTime() > DateUtil.getTimeThisMonthLastSec(time).getTime()) {
//                        LoggerUtil.log.debug("批量入账：生效时间大于下月最后一秒不入账");
                        LoggerUtil.log.debug("批量入账：生效时间大于当月最后一秒不入账");
                        return;
                    }
                } catch (Exception e) {
                    LoggerUtil.log.debug("时间格式错误");
                    return;
                }
                LoggerUtil.log.debug("进行清零设置");
                book.setACTIVE_TIME(time.toString());
                time = DateUtil.getTimeThisMonthLastSec(time);
                book.setINACTIVE_TIME(time.toString());
            } 
        }
//                else {
//            // 如果清零，生效日期为下月第一秒，失效日期为下月最后一秒
//            time = DateUtil.getTimeThisMonthFirstSec(new Timestamp(System.currentTimeMillis()));
//            date = DateUtil.getDateString(time, DateUtil.YYYYMM);
//            if (Timestamp.valueOf(book.getINACTIVE_TIME()).getTime() < time.getTime()) {
//                LoggerUtil.log.debug("批量入账：失效小于下月第一秒，不入账");
//                return;
//            }
//            if ("Y".equals(book.getRES_CLEAR_FLAG())) {
//                try {
//                    if (Timestamp.valueOf(book.getACTIVE_TIME()).getTime() > DateUtil
//                            .getTimeThisMonthLastSec(time).getTime()) {
//                        LoggerUtil.log.debug("批量入账：生效时间大于下月最后一秒不入账");
//                        return;
//                    }
//                } catch (Exception e) {
//                    LoggerUtil.log.debug("时间格式错误");
//                    return;
//                }
//                LoggerUtil.log.debug("进行清零设置");
//                book.setACTIVE_TIME(time.toString());
//                time = DateUtil.getTimeThisMonthLastSec(time);
//                book.setINACTIVE_TIME(time.toString());
//            }
//        }

        // 当判重表res_dup_log_yyyymm中存在跳出
        try {
            if (aIDuplicateCheck.hasRecord(book, date)) {
                LoggerUtil.log.debug("已存在判重表中");
                return;
            }
        } catch (IOException e) {
            // e.printStackTrace();
            LoggerUtil.log.error("判重发生错误，入参：" + MyJsonUtil.toJson(book), e);
            return;
        }

        for (TypeEnum t : TypeEnum.values()) {
            String amount = aIFunResBookSv.getAmount(book.getPRODUCT_ID(), t.type);
            if (StringUtil.isBlank(amount)) {
                LoggerUtil.log.debug("查询amount为空");
                continue;
            }
            book.setResourceType(t.type);
            book.setTotalAmount(amount);
            LoggerUtil.log.debug(MyJsonUtil.toJson(book));
            // System.out.println(book.getResourceType());
            // System.out.println("dubbo:" +
            // MyJsonUtil.toJson(aIPackResult.chResourceDeposit(book)));
            try {
                LoggerUtil.log.debug(
                        "调用dubbo，入参：" + MyJsonUtil.toJson(aIPackResult.chResourceDeposit(book)));
                resDepositSv.depositResource(aIPackResult.chResourceDeposit(book));
            } catch (Exception e) {
                // e.printStackTrace();
                LoggerUtil.log.error("dubboError:调用dubbo发生错误，入参："
                        + MyJsonUtil.toJson(aIPackResult.chResourceDeposit(book)));
                return;
            }
            // System.out.println("信控:"+MyJsonUtil.toJson(aIPackResult.chMsgControl(book)));

            MdsUtil.getSender().send(MyJsonUtil.toJson(aIPackResult.chMsgControl(book)),
                    random.nextInt(2) % 2);
            LoggerUtil.log.debug("信控:" + MyJsonUtil.toJson(aIPackResult.chMsgControl(book)));
        }
        // 写入查重表
        // try {
        // aIDuplicateCheck.insertResDupTable(book,date);
        // } catch (IOException e) {
        // e.printStackTrace();
        // LoggerUtil.log.error("写入判重表发生错误，入参："+MyJsonUtil.toJson(book), e);
        // }

    }
    

}
