package com.ai.baas.ccp.action.notice.group.ecitic;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.ai.baas.ccp.action.notice.group.base.AbstractGroupNotice;
import com.ai.baas.ccp.action.notice.vo.NoticeResult;
import com.ai.baas.ccp.constants.CcpConstants;
import com.ai.baas.ccp.dao.mapper.bo.NoticeRecord;
import com.ai.baas.ccp.dao.mapper.bo.NoticeSubscribe;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.dubbo.util.HttpClientUtil;
import com.ai.opt.sdk.util.DateUtil;

@Component
public class EciticCcpStopGroupNotice extends AbstractGroupNotice {

    @Override
    public Object constructRequest(String reqBody) throws Exception {
        return reqBody;
    }

    @Override
    public NoticeResult process(Object request, NoticeSubscribe subscribe, NoticeRecord record)
            throws Exception {
        if (request == null) {
            throw new SystemException("购家电送话费活动信息同步失败:没有传入请求参数");
        }
        if (!(request instanceof String)) {
            throw new SystemException("购家电送话费活动信息同步失败:传入请求类型不匹配");
        }
        String reqBody = (String) request;
        /* 2.调用服务 */
        NoticeResult result = new NoticeResult();
        Timestamp reqAckTime = null;
        String reqAckBody = null;
        String reqAckStatus = null;
        String state = null;
        String response = HttpClientUtil.sendPost(subscribe.getServiceUrl(), reqBody);
        reqAckTime = DateUtil.getSysDate();
        state = CcpConstants.NoticeSubscribeRecord.State.SUCCESS;
        /* 组织结果数据 */
        result.setReqAckTime(reqAckTime);
        result.setReqAckBody(reqAckBody);
        result.setReqAckStatus(reqAckStatus);
        result.setReqAckMessage(response);
        result.setStateDesc(response);
        result.setState(state);
        return result;
    }

    @Override
    public String assembleReqBody() {
        return "test reqbody";
    }
}
