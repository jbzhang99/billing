package com.ai.runner.center.bmc.resdeposit.application;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ai.runner.center.bmc.resdeposit.util.KafkaUtil;
import com.ai.runner.center.bmc.resdeposit.util.LoggerUtil;
import com.ai.runner.center.bmc.resdeposit.util.MyJsonUtil;
import com.ai.runner.center.bmc.resdeposit.vo.CommMsg;
import com.ai.runner.center.bmc.resdeposit.vo.UserMsg;
import com.google.gson.reflect.TypeToken;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

public class StartServer {
    private static final String[] PATH = { "classpath:context/core-context.xml" };

    private static ClassPathXmlApplicationContext context;

    public static void main(String[] args) {
        // 启动spring容器
        context = new ClassPathXmlApplicationContext(PATH);
        context.registerShutdownHook();
        context.start();
        KafkaUtil kafka = new KafkaUtil();
        List<KafkaStream<byte[], byte[]>> result = kafka.getStream();
        // 线程池
        ExecutorService executor = Executors.newFixedThreadPool(kafka.getThreadNum());
        for (final KafkaStream<byte[], byte[]> r : result) {
            executor.submit(new ConsumerThread(r, context.getBean(EnterAccount.class)));
        }
    }
}

class ConsumerThread implements Runnable {
    private KafkaStream<byte[], byte[]> aStream;

//    private ExecutorService executor;
    private EnterAccount account;

    public ConsumerThread(KafkaStream<byte[], byte[]> stream, EnterAccount enterAccount) {
        aStream = stream;
        account = enterAccount;
//        executor = Executors.newFixedThreadPool(3);
    }

    public void run() {
        ConsumerIterator<byte[], byte[]> it = aStream.iterator();
        while (it.hasNext()) {
            String msg = new String(it.next().message());
//            System.out.println(Thread.currentThread().getName() + ":" + msg);
            try {
                Map<String, String> result = MyJsonUtil.toBean(msg,
                        new TypeToken<Map<String, String>>() {
                }.getType());
                LoggerUtil.log.debug("获得kafka消息：" + msg);
                if ("USR".equals(result.get("MSG_TYPE"))) {
                    account.userStrike(MyJsonUtil.toBean(msg, UserMsg.class));
                } else if ("COM".equals(result.get("MSG_TYPE"))) {
                    account.commStrike(MyJsonUtil.toBean(msg, CommMsg.class));
                } else if ("BATCH".equals(result.get("MSG_TYPE"))){
                    account.BatchStrike(MyJsonUtil.toBean(msg, UserMsg.class));
                }
            } catch (Exception e) {
                continue;
            }
        }
    }
}


//class RunThread implements Runnable{
//    private EnterAccount account;
//    private String msg;
//    public RunThread() {
//    }
//    @Override
//    public void run() {
//    }
//    
//}