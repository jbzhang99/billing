package com.ai.baas.ccp.action.notice.group.ecitic;

import com.ai.baas.ccp.action.notice.group.base.AbstractGroupNotice;
import com.ai.baas.ccp.action.notice.vo.NoticeResult;
import com.ai.baas.ccp.dao.mapper.bo.NoticeRecord;
import com.ai.baas.ccp.dao.mapper.bo.NoticeSubscribe;

public class EciticCcpWarningSmsGroupNotice extends AbstractGroupNotice {

    @Override
    public Object constructRequest(String reqBody) throws Exception {
        return null;
    }

    @Override
    public NoticeResult process(Object request, NoticeSubscribe subscribe, NoticeRecord record)
            throws Exception {
        return null;
    }

    @Override
    public String assembleReqBody() {
        return null;
    }

}
