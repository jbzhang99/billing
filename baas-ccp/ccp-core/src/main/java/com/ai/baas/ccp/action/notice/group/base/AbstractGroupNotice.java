package com.ai.baas.ccp.action.notice.group.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.baas.ccp.action.notice.vo.NoticeResult;
import com.ai.baas.ccp.dao.mapper.bo.NoticeRecord;
import com.ai.baas.ccp.dao.mapper.bo.NoticeSubscribe;
import com.ai.baas.ccp.service.factory.ServiceFactory;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.util.StringUtil;

public abstract class AbstractGroupNotice {

    protected Logger getLogger() {
        Logger log = LoggerFactory.getLogger(this.getClass());
        return log;
    }

    public static AbstractGroupNotice getInstance(String clazzName) {
        AbstractGroupNotice groupBase = null;
        if (StringUtil.isBlank(clazzName)) {
            throw new SystemException("获取notice组件信息出错：没有指定实现类");
        }
        try {
            groupBase = (AbstractGroupNotice) ServiceFactory.getService(clazzName);
        } catch (Exception e) {
            throw new SystemException("获取notice组件信息出错", e);
        }
        return groupBase;
    }

    /**
     * 根据存储的报文构建实际服务调用的入参JAVA对象
     * 
     * @param reqBody
     * @return
     * @throws Exception
     * @author zhangchao
     */
    public abstract Object constructRequest(String reqBody) throws Exception;

    /**
     * 数据调用处理
     * 
     * @param request
     * @param subscribe
     * @return
     * @throws Exception
     * @author zhangchao
     */
    public abstract NoticeResult process(Object request, NoticeSubscribe subscribe,NoticeRecord record)
            throws Exception;

    /**
     * 组装消息报文
     * @return
     * @author mayt
     * @ApiDocMethod
     */
    public abstract String assembleReqBody();

}
