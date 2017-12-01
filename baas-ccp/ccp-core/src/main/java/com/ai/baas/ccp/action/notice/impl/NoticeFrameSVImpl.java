package com.ai.baas.ccp.action.notice.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ai.baas.ccp.action.notice.group.base.AbstractGroupNotice;
import com.ai.baas.ccp.action.notice.interfaces.INoticeFrameSV;
import com.ai.baas.ccp.action.notice.param.base.AbstractBaseNoticeBusiVO;
import com.ai.baas.ccp.action.notice.vo.NoticeRecordVO;
import com.ai.baas.ccp.action.notice.vo.NoticeResult;
import com.ai.baas.ccp.constants.CcpConstants;
import com.ai.baas.ccp.dao.mapper.bo.NoticeRecord;
import com.ai.baas.ccp.dao.mapper.bo.NoticeSubject;
import com.ai.baas.ccp.dao.mapper.bo.NoticeSubjectCriteria;
import com.ai.baas.ccp.dao.mapper.bo.NoticeSubscribe;
import com.ai.baas.ccp.dao.mapper.bo.NoticeSubscribeCriteria;
import com.ai.baas.ccp.dao.mapper.bo.NoticeSubscribeRecord;
import com.ai.baas.ccp.dao.mapper.bo.NoticeSubscribeRecordCriteria;
import com.ai.baas.ccp.dao.mapper.factory.MapperFactory;
import com.ai.baas.ccp.service.factory.ServiceFactory;
import com.ai.baas.ccp.util.CcpSeqUtil;
import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.opt.sdk.constants.ExceptCodeConstants;
import com.ai.opt.sdk.util.CollectionUtil;
import com.ai.opt.sdk.util.DateUtil;
import com.ai.opt.sdk.util.ExceptionUtil;
import com.ai.opt.sdk.util.StringUtil;
import com.alibaba.fastjson.JSON;

@Component
public class NoticeFrameSVImpl implements INoticeFrameSV {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoticeFrameSVImpl.class);

    //
    // /**
    // * 更新记录状态
    // */
    // public void updateSyncSubscribeRecordState(long seqId, String state, String stateDesc)
    // throws Exception {
    // log.debug("将单笔订阅记录流水[" + seqId + "]状态更新成[" + state + "]...");
    // /* 1.获取单笔订阅记录流水信息 */
    // NoticeSubscribeRecord subscribeRecord = this.getSubscribeRecord(seqId);
    // if (subscribeRecord == null) {
    // throw new SystemException("更新状态失败:订阅记录流水[" + seqId + "]记录不存在");
    // }
    // /* 2.更新状态 */
    // subscribeRecord.setStsToOld();
    // subscribeRecord.setStateChgTime(DateUtil.getSysDate());
    // subscribeRecord.setState(state);
    // subscribeRecord.setStateDesc(stateDesc);
    // ServiceFactory.getISubsSyncSubscribeRecordSV().saveBean(subscribeRecord);
    // }
    //
    // /**
    // * 获取可供处理的订阅记录
    // */
    // public NoticeSubscribeRecord[] fetchSubscribeRecords(int processNo,
    // int processTotalNum, int total, int retryTimes) throws Exception {
    // log.debug("开始获取进程[" + processNo + "]可以处理的订阅记录..");
    // StringBuffer sql = new StringBuffer(" 1 = 1 ");
    // Map<String, Object> params = new HashMap<String, Object>();
    // sql.append(" and (" + NoticeSubscribeRecord.S_State
    // + " = :waitState or ((state =:failureState and send_times <:retryTimes)))");
    // sql.append(" and mod(seq_id, :processTotalNum) =:processNo");
    // sql.append(" order by create_time asc");
    // params.put("waitState", SubsConstants.SubsSyncSubscribeRecord.State.WAIT);
    // params.put("failureState", SubsConstants.SubsSyncSubscribeRecord.State.FAILURE);
    // params.put("retryTimes", retryTimes);
    // params.put("processTotalNum", processTotalNum);
    // params.put("processNo", processNo - 1);
    // NoticeSubscribeRecord[] beans = SubsServiceUtil.getISubsSyncSubscribeRecordSV()
    // .getBeans(sql.toString(), params);
    // return beans;
    // }

    /**
     * 处理单笔订阅记录流水
     */
    public void process(String seqId) {
        LOGGER.info("开始处理单笔订阅记录流水[" + seqId + "]...");
        /* 1.获取单笔订阅记录流水信息 */
        NoticeSubscribeRecord subscribeRecord = this.getSubscribeRecord(seqId);
        if (subscribeRecord == null) {
            return;
        }
        /* 2.对单笔订阅记录进行处理 */
        NoticeResult noticeResult = null;
        String state = null;
        String stateDesc = null;
        Timestamp reqTime = null;
        try {
            /* 2.1 获取订阅者信息 */
            NoticeSubscribe subscribe = this.getSubscribe(subscribeRecord.getSubscribeId());
            if (subscribe == null) {
                throw new SystemException("数据发布失败:订阅记录流水[" + seqId + "]对应的订阅者["
                        + subscribeRecord.getSubscribeId() + "]记录不存在");
            }
            /* 2.2 获取发布记录信息 */
            NoticeRecord record = this.getRecord(subscribeRecord.getRecordId());
            if (record == null) {
                throw new SystemException("数据发布失败:订阅记录流水[" + seqId + "]对应的发布记录["
                        + subscribeRecord.getRecordId() + "]不存在");
            }
            /* 2.3 根据订阅者的配置获取其处理类 */
            String processClass = subscribe.getProcessClass();
            if (StringUtil.isBlank(processClass)) {
                throw new SystemException("数据发布失败:订阅记录流水[" + seqId + "]对应的订阅者["
                        + subscribeRecord.getSubscribeId() + "]的处理类没有配置");
            }
            AbstractGroupNotice base = AbstractGroupNotice.getInstance(processClass);
            /* 2.4 调用组件处理类构建入参信息 */
            String reqBody = this.concateReqBody(record);
            Object request = base.constructRequest(reqBody);
            reqTime = DateUtil.getSysDate();
            /* 2.5 调用组件处理核心类 */
            noticeResult = base.process(request, subscribe, record);
            if (noticeResult == null) {
                throw new SystemException("数据发布失败:订阅记录流水[" + seqId + "]对应的订阅者["
                        + subscribeRecord.getSubscribeId() + "]的处理类[" + processClass + "]没有正确返回结果");
            }
            if (StringUtil.isBlank(noticeResult.getState())) {
                throw new SystemException("数据发布失败:订阅记录流水[" + seqId + "]对应的订阅者["
                        + subscribeRecord.getSubscribeId() + "]的处理类[" + processClass
                        + "]没有返回任何处理状态");
            }
            state = noticeResult.getState();
            stateDesc = noticeResult.getStateDesc();
            LOGGER.debug("单笔订阅记录流水[" + seqId + "]处理完成，业务组件端返回的信息:" + JSON.toJSONString(noticeResult));
        } catch (Exception ex) {
            LOGGER.error("单笔订阅记录流水[" + seqId + "]处理失败:" + ExceptionUtil.getTrace(ex));
            state = CcpConstants.NoticeSubscribeRecord.State.FAILURE;
            stateDesc = StringUtil.restrictLength(ExceptionUtil.getTrace(ex), 3999);
        }
        /* 3.更新订阅记录的状态 */
        if (reqTime != null)
            subscribeRecord.setReqTime(reqTime);
        if (noticeResult != null) {
            subscribeRecord.setReqAckTime(noticeResult.getReqAckTime());
            subscribeRecord.setReqAckBody(noticeResult.getReqAckBody());
            subscribeRecord.setReqAckMessage(noticeResult.getReqAckMessage());
            subscribeRecord.setReqAckStatus(noticeResult.getReqAckStatus());
        }
        subscribeRecord.setState(state);
        subscribeRecord.setStateChgTime(DateUtil.getSysDate());
        subscribeRecord.setStateDesc(StringUtil.restrictLength(stateDesc, 3999));
        subscribeRecord.setSendTimes(subscribeRecord.getSendTimes() + 1);
        MapperFactory.getNoticeSubscribeRecordMapper().updateByPrimaryKey(subscribeRecord);
        LOGGER.debug("单笔订阅记录流水[" + seqId + "]最终状态更新完成");
    }

    /**
     * 保存发布记录信息
     * 
     * @return
     */
    public List<String> saveBatchRecord(List<NoticeRecordVO> records) {
        if (CollectionUtil.isEmpty(records)) {
            throw new BusinessException(ExceptCodeConstants.Special.PARAM_IS_NULL,
                    "保存发布记录信息失败：传入数据为空");
        }
        LOGGER.debug("保存发布记录信息..");
        /* 1.校验数据 */
        this.validate(records);
        List<String> recordIds = new ArrayList<String>();
        Timestamp sysdate = DateUtil.getSysDate();
        List<NoticeRecord> recordList = new ArrayList<NoticeRecord>();
        List<NoticeSubscribeRecord> subscribeRecordList = new ArrayList<NoticeSubscribeRecord>();
        /* 2.组织发布记录数据 */
        for (NoticeRecordVO recordVO : records) {
            /* 2.1 组织发布记录表数据 */
            String recordId = CcpSeqUtil.createNoticeRecordId();
            NoticeRecord record = new NoticeRecord();
            record.setRecordId(recordId);
            record.setCustId(recordVO.getCustId());
            record.setSubjectId(recordVO.getSubjectId());
            record.setSubsId(recordVO.getSubsId());
            record.setTenantId(recordVO.getTenantId());

            String reqBody = recordVO.getReqBody();
            String body1 = null;
            String body2 = null;
            String body3 = null;
            if (reqBody.length() <= 4000) {
                body1 = reqBody;
            } else if (reqBody.length() > 4000 && reqBody.length() <= 8000) {
                body1 = reqBody.substring(0, 4000);
                body2 = reqBody.substring(4001, reqBody.length());
            } else if (reqBody.length() > 8000 && reqBody.length() <= 12000) {
                body1 = reqBody.substring(0, 4000);
                body2 = reqBody.substring(4001, 8000);
                body3 = reqBody.substring(8001, reqBody.length());
            }
            record.setReqBody(body1);
            record.setReqBody2(body2);
            record.setReqBody3(body3);
            record.setCreateTime(sysdate);
            record.setRemark(recordVO.getRemark());
            recordList.add(record);
            /* 2.2 组织订阅者处理记录 */
            List<NoticeSubscribeRecord> list = this.buildSubscribeRecords(recordId,
                    record.getSubjectId(), sysdate, recordVO.getTenantId());
            subscribeRecordList.addAll(list);

            recordIds.add(recordId);
        }
        /* 3.批量保存 */
        ServiceFactory.getINoticeRecordAtomSV().saveBatch(recordList);
        ServiceFactory.getINoticeSubscribeRecordAtomSV().saveBatch(subscribeRecordList);

        return recordIds;
    }

    /**
     * 构建初始化订阅者处理记录列表
     */
    private List<NoticeSubscribeRecord> buildSubscribeRecords(String recordId, String subjectId,
            Timestamp sysdate, String tenantId) {
        List<NoticeSubscribeRecord> list = new ArrayList<NoticeSubscribeRecord>();
        /* 1.获取当前主题下的所有系统的订阅记录 */
        List<NoticeSubscribe> subscribes = this.getSubscribes(subjectId);
        /* 2.针对每条发布记录初始化一条订阅者处理记录 */
        if (!CollectionUtil.isEmpty(subscribes)) {
            for (NoticeSubscribe subscribeBean : subscribes) {
                NoticeSubscribeRecord bean = new NoticeSubscribeRecord();
                String seqId = CcpSeqUtil.createNoticeSubscribeRecordSeqId();
                bean.setSeqId(seqId);
                bean.setSubscribeId(subscribeBean.getSubscribeId());
                bean.setRecordId(recordId);
                bean.setState(CcpConstants.NoticeSubscribeRecord.State.WAIT);
                bean.setStateChgTime(sysdate);
                bean.setCreateTime(sysdate);
                bean.setSendTimes(0);
                bean.setTenantId(tenantId);
                list.add(bean);
            }
        }
        return list;
    }

    /**
     * 获取主题所有订阅者
     */
    private List<NoticeSubscribe> getSubscribes(String subjectId) {
        NoticeSubscribeCriteria criteria = new NoticeSubscribeCriteria();
        criteria.createCriteria().andSubjectIdEqualTo(subjectId)
                .andStateEqualTo(CcpConstants.NoticeSubscribe.State.VALID);
        return MapperFactory.getNoticeSubscribeMapper().selectByExample(criteria);
    }

    /**
     * 获取订阅者信息
     */
    private NoticeSubscribe getSubscribe(String subscribeId) throws Exception {
        NoticeSubscribeCriteria criteria = new NoticeSubscribeCriteria();
        criteria.createCriteria().andSubscribeIdEqualTo(subscribeId)
                .andStateEqualTo(CcpConstants.NoticeSubscribe.State.VALID);
        List<NoticeSubscribe> noticeSubscribes = MapperFactory.getNoticeSubscribeMapper()
                .selectByExample(criteria);
        return CollectionUtil.isEmpty(noticeSubscribes) ? null : noticeSubscribes.get(0);
    }

    /**
     * 获取主题配置
     */
    private NoticeSubject getSubject(String tenantId, String subjectId) {
        NoticeSubjectCriteria criteria = new NoticeSubjectCriteria();
        criteria.createCriteria().andTenantIdEqualTo(tenantId).andSubjectIdEqualTo(subjectId);
        List<NoticeSubject> noticeSubjects = MapperFactory.getNoticeSubjectMapper()
                .selectByExample(criteria);
        return CollectionUtil.isEmpty(noticeSubjects) ? null : noticeSubjects.get(0);
    }

    /**
     * 获取单个订阅者记录流水
     */
    private NoticeSubscribeRecord getSubscribeRecord(String seqId) {
        NoticeSubscribeRecordCriteria criteria = new NoticeSubscribeRecordCriteria();
        criteria.createCriteria().andSeqIdEqualTo(seqId)
                .andStateEqualTo(CcpConstants.NoticeSubscribeRecord.State.WAIT);
        List<NoticeSubscribeRecord> noticeSubscribeRecords = MapperFactory
                .getNoticeSubscribeRecordMapper().selectByExample(criteria);
        if (CollectionUtil.isEmpty(noticeSubscribeRecords)) {
            return null;
        }
        return noticeSubscribeRecords.get(0);
    }

    /**
     * 获取单条发布记录
     */
    private NoticeRecord getRecord(String record) throws Exception {
        return MapperFactory.getNoticeRecordMapper().selectByPrimaryKey(record);
    }

    /**
     * 校验数据
     */
    private void validate(List<NoticeRecordVO> records) {
        for (NoticeRecordVO record : records) {
            int index = records.indexOf(record);
            if (StringUtil.isBlank(record.getSubjectId())) {
                throw new BusinessException(ExceptCodeConstants.Special.PARAM_IS_NULL,
                        "保存发布记录信息失败：第[" + index + "]条数据没有指定主题");
            }
            if (StringUtil.isBlank(record.getReqBody())) {
                throw new BusinessException(ExceptCodeConstants.Special.PARAM_IS_NULL,
                        "保存发布记录信息失败：第[" + index + "]条数据没有指定请求报文");
            }
            if (record.getReqBody().length() > 12000) {
                throw new BusinessException(ExceptCodeConstants.Special.SYSTEM_ERROR,
                        "保存发布记录信息失败：第[" + index + "]条数据请求报文长度过长");
            }
            NoticeSubject subject = this.getSubject(record.getTenantId(), record.getSubjectId());
            if (subject == null) {
                throw new BusinessException(ExceptCodeConstants.Special.PARAM_IS_NULL,
                        "保存发布记录信息失败：第[" + index + "]条数据指定主题[" + record.getSubjectId() + "]配置信息不存在");
            }
        }
    }

    /**
     * 组织请求报文信息
     */
    private String concateReqBody(NoticeRecord record) {
        StringBuffer sb = new StringBuffer();
        if (!StringUtil.isBlank(record.getReqBody()))
            sb.append(record.getReqBody());
        if (!StringUtil.isBlank(record.getReqBody2()))
            sb.append(record.getReqBody2());
        if (!StringUtil.isBlank(record.getReqBody3()))
            sb.append(record.getReqBody3());
        return sb.toString();
    }

    @Override
    public void updateSyncSubscribeRecordState(long seqId, String state, String stateDesc)
            throws Exception {
    }

    @Override
    public NoticeSubscribeRecord[] fetchSubscribeRecords(int processNo, int processTotalNum,
            int total, int retryTimes) throws Exception {
        return null;
    }

    @Override
    public void createNoticeRecord(AbstractBaseNoticeBusiVO busiVO) {
        String tenantId = busiVO.getTenantId();
        String subjectId = busiVO.getSubjectId();
        /* 获取订阅者信息 */
        List<NoticeSubscribe> subscribes = this.getSubscribes(subjectId);
        if (CollectionUtil.isEmpty(subscribes)) {
            return;
        }
        NoticeSubscribe subscribe = subscribes.get(0);
        /* 根据订阅者的配置获取其处理类 */
        String processClass = subscribe.getProcessClass();
        if (StringUtil.isBlank(processClass)) {
            throw new BusinessException("订阅者[ID:{}]处理类没有配置", subscribe.getSubscribeId());
        }
        // 获取具体的业务处理实例
        AbstractGroupNotice base = AbstractGroupNotice.getInstance(processClass);
        // 生成特定的消息报文
        String reqBody = base.assembleReqBody();
        NoticeRecordVO noticeRecordVO = new NoticeRecordVO();
        noticeRecordVO.setReqBody(reqBody);
        noticeRecordVO.setSubjectId(subjectId);
        noticeRecordVO.setTenantId(tenantId);
        List<NoticeRecordVO> records = new ArrayList<NoticeRecordVO>();
        records.add(noticeRecordVO);
        List<String> recordIds = this.saveBatchRecord(records);
        // 发送报文
        ServiceFactory.getINoticeBusiSV().sendMsg(recordIds);
    }

}
