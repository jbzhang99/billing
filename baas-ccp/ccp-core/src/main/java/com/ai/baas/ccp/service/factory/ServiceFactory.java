package com.ai.baas.ccp.service.factory;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.action.notice.interfaces.INoticeFrameSV;
import com.ai.baas.ccp.service.atom.interfaces.INoticeRecordAtomSV;
import com.ai.baas.ccp.service.atom.interfaces.INoticeSubscribeRecordAtomSV;
import com.ai.baas.ccp.service.business.interfaces.IConfigContainerBusiSV;
import com.ai.baas.ccp.service.business.interfaces.IInformationProcessorBusiSV;
import com.ai.baas.ccp.service.business.interfaces.INoticeBusiSV;
import com.ai.baas.ccp.service.business.interfaces.ISubscribeInvalid2StopNoticeBusiSV;

@Component
public class ServiceFactory {
    @Autowired
    private transient ApplicationContext applicationContext;

    private static ApplicationContext context;

    @PostConstruct
    void init() {
        setApplicationContext(applicationContext);
    }

    private void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getService(String beanId) {
        return (T) context.getBean(beanId);
    }

    public static INoticeFrameSV getINoticeFrameSV() {
        return context.getBean(INoticeFrameSV.class);
    }

    public static INoticeRecordAtomSV getINoticeRecordAtomSV() {
        return context.getBean(INoticeRecordAtomSV.class);
    }

    public static INoticeSubscribeRecordAtomSV getINoticeSubscribeRecordAtomSV() {
        return context.getBean(INoticeSubscribeRecordAtomSV.class);
    }

    public static INoticeBusiSV getINoticeBusiSV() {
        return context.getBean(INoticeBusiSV.class);
    }

    public static ISubscribeInvalid2StopNoticeBusiSV getISubscribeInvalid2StopNoticeBusiSV() {
        return context.getBean(ISubscribeInvalid2StopNoticeBusiSV.class);        
    }

    public static IConfigContainerBusiSV getIConfigContainerBusiSV() {
        return context.getBean(IConfigContainerBusiSV.class);
    }

    public static IInformationProcessorBusiSV getIInformationProcessorBusiSV() {
        return context.getBean(IInformationProcessorBusiSV.class);
    }

}
