package com.ai.baas.ccp.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class NoticeSubscribeRecordCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public NoticeSubscribeRecordCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(Integer limitStart) {
        this.limitStart=limitStart;
    }

    public Integer getLimitStart() {
        return limitStart;
    }

    public void setLimitEnd(Integer limitEnd) {
        this.limitEnd=limitEnd;
    }

    public Integer getLimitEnd() {
        return limitEnd;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSeqIdIsNull() {
            addCriterion("SEQ_ID is null");
            return (Criteria) this;
        }

        public Criteria andSeqIdIsNotNull() {
            addCriterion("SEQ_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSeqIdEqualTo(String value) {
            addCriterion("SEQ_ID =", value, "seqId");
            return (Criteria) this;
        }

        public Criteria andSeqIdNotEqualTo(String value) {
            addCriterion("SEQ_ID <>", value, "seqId");
            return (Criteria) this;
        }

        public Criteria andSeqIdGreaterThan(String value) {
            addCriterion("SEQ_ID >", value, "seqId");
            return (Criteria) this;
        }

        public Criteria andSeqIdGreaterThanOrEqualTo(String value) {
            addCriterion("SEQ_ID >=", value, "seqId");
            return (Criteria) this;
        }

        public Criteria andSeqIdLessThan(String value) {
            addCriterion("SEQ_ID <", value, "seqId");
            return (Criteria) this;
        }

        public Criteria andSeqIdLessThanOrEqualTo(String value) {
            addCriterion("SEQ_ID <=", value, "seqId");
            return (Criteria) this;
        }

        public Criteria andSeqIdLike(String value) {
            addCriterion("SEQ_ID like", value, "seqId");
            return (Criteria) this;
        }

        public Criteria andSeqIdNotLike(String value) {
            addCriterion("SEQ_ID not like", value, "seqId");
            return (Criteria) this;
        }

        public Criteria andSeqIdIn(List<String> values) {
            addCriterion("SEQ_ID in", values, "seqId");
            return (Criteria) this;
        }

        public Criteria andSeqIdNotIn(List<String> values) {
            addCriterion("SEQ_ID not in", values, "seqId");
            return (Criteria) this;
        }

        public Criteria andSeqIdBetween(String value1, String value2) {
            addCriterion("SEQ_ID between", value1, value2, "seqId");
            return (Criteria) this;
        }

        public Criteria andSeqIdNotBetween(String value1, String value2) {
            addCriterion("SEQ_ID not between", value1, value2, "seqId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNull() {
            addCriterion("TENANT_ID is null");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNotNull() {
            addCriterion("TENANT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTenantIdEqualTo(String value) {
            addCriterion("TENANT_ID =", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotEqualTo(String value) {
            addCriterion("TENANT_ID <>", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThan(String value) {
            addCriterion("TENANT_ID >", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThanOrEqualTo(String value) {
            addCriterion("TENANT_ID >=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThan(String value) {
            addCriterion("TENANT_ID <", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThanOrEqualTo(String value) {
            addCriterion("TENANT_ID <=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLike(String value) {
            addCriterion("TENANT_ID like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotLike(String value) {
            addCriterion("TENANT_ID not like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIn(List<String> values) {
            addCriterion("TENANT_ID in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotIn(List<String> values) {
            addCriterion("TENANT_ID not in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdBetween(String value1, String value2) {
            addCriterion("TENANT_ID between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotBetween(String value1, String value2) {
            addCriterion("TENANT_ID not between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdIsNull() {
            addCriterion("SUBSCRIBE_ID is null");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdIsNotNull() {
            addCriterion("SUBSCRIBE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdEqualTo(String value) {
            addCriterion("SUBSCRIBE_ID =", value, "subscribeId");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdNotEqualTo(String value) {
            addCriterion("SUBSCRIBE_ID <>", value, "subscribeId");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdGreaterThan(String value) {
            addCriterion("SUBSCRIBE_ID >", value, "subscribeId");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdGreaterThanOrEqualTo(String value) {
            addCriterion("SUBSCRIBE_ID >=", value, "subscribeId");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdLessThan(String value) {
            addCriterion("SUBSCRIBE_ID <", value, "subscribeId");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdLessThanOrEqualTo(String value) {
            addCriterion("SUBSCRIBE_ID <=", value, "subscribeId");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdLike(String value) {
            addCriterion("SUBSCRIBE_ID like", value, "subscribeId");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdNotLike(String value) {
            addCriterion("SUBSCRIBE_ID not like", value, "subscribeId");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdIn(List<String> values) {
            addCriterion("SUBSCRIBE_ID in", values, "subscribeId");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdNotIn(List<String> values) {
            addCriterion("SUBSCRIBE_ID not in", values, "subscribeId");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdBetween(String value1, String value2) {
            addCriterion("SUBSCRIBE_ID between", value1, value2, "subscribeId");
            return (Criteria) this;
        }

        public Criteria andSubscribeIdNotBetween(String value1, String value2) {
            addCriterion("SUBSCRIBE_ID not between", value1, value2, "subscribeId");
            return (Criteria) this;
        }

        public Criteria andRecordIdIsNull() {
            addCriterion("RECORD_ID is null");
            return (Criteria) this;
        }

        public Criteria andRecordIdIsNotNull() {
            addCriterion("RECORD_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRecordIdEqualTo(String value) {
            addCriterion("RECORD_ID =", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotEqualTo(String value) {
            addCriterion("RECORD_ID <>", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThan(String value) {
            addCriterion("RECORD_ID >", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdGreaterThanOrEqualTo(String value) {
            addCriterion("RECORD_ID >=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThan(String value) {
            addCriterion("RECORD_ID <", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLessThanOrEqualTo(String value) {
            addCriterion("RECORD_ID <=", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdLike(String value) {
            addCriterion("RECORD_ID like", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotLike(String value) {
            addCriterion("RECORD_ID not like", value, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdIn(List<String> values) {
            addCriterion("RECORD_ID in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotIn(List<String> values) {
            addCriterion("RECORD_ID not in", values, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdBetween(String value1, String value2) {
            addCriterion("RECORD_ID between", value1, value2, "recordId");
            return (Criteria) this;
        }

        public Criteria andRecordIdNotBetween(String value1, String value2) {
            addCriterion("RECORD_ID not between", value1, value2, "recordId");
            return (Criteria) this;
        }

        public Criteria andReqTimeIsNull() {
            addCriterion("REQ_TIME is null");
            return (Criteria) this;
        }

        public Criteria andReqTimeIsNotNull() {
            addCriterion("REQ_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andReqTimeEqualTo(Timestamp value) {
            addCriterion("REQ_TIME =", value, "reqTime");
            return (Criteria) this;
        }

        public Criteria andReqTimeNotEqualTo(Timestamp value) {
            addCriterion("REQ_TIME <>", value, "reqTime");
            return (Criteria) this;
        }

        public Criteria andReqTimeGreaterThan(Timestamp value) {
            addCriterion("REQ_TIME >", value, "reqTime");
            return (Criteria) this;
        }

        public Criteria andReqTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("REQ_TIME >=", value, "reqTime");
            return (Criteria) this;
        }

        public Criteria andReqTimeLessThan(Timestamp value) {
            addCriterion("REQ_TIME <", value, "reqTime");
            return (Criteria) this;
        }

        public Criteria andReqTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("REQ_TIME <=", value, "reqTime");
            return (Criteria) this;
        }

        public Criteria andReqTimeIn(List<Timestamp> values) {
            addCriterion("REQ_TIME in", values, "reqTime");
            return (Criteria) this;
        }

        public Criteria andReqTimeNotIn(List<Timestamp> values) {
            addCriterion("REQ_TIME not in", values, "reqTime");
            return (Criteria) this;
        }

        public Criteria andReqTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("REQ_TIME between", value1, value2, "reqTime");
            return (Criteria) this;
        }

        public Criteria andReqTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("REQ_TIME not between", value1, value2, "reqTime");
            return (Criteria) this;
        }

        public Criteria andReqAckTimeIsNull() {
            addCriterion("REQ_ACK_TIME is null");
            return (Criteria) this;
        }

        public Criteria andReqAckTimeIsNotNull() {
            addCriterion("REQ_ACK_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andReqAckTimeEqualTo(Timestamp value) {
            addCriterion("REQ_ACK_TIME =", value, "reqAckTime");
            return (Criteria) this;
        }

        public Criteria andReqAckTimeNotEqualTo(Timestamp value) {
            addCriterion("REQ_ACK_TIME <>", value, "reqAckTime");
            return (Criteria) this;
        }

        public Criteria andReqAckTimeGreaterThan(Timestamp value) {
            addCriterion("REQ_ACK_TIME >", value, "reqAckTime");
            return (Criteria) this;
        }

        public Criteria andReqAckTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("REQ_ACK_TIME >=", value, "reqAckTime");
            return (Criteria) this;
        }

        public Criteria andReqAckTimeLessThan(Timestamp value) {
            addCriterion("REQ_ACK_TIME <", value, "reqAckTime");
            return (Criteria) this;
        }

        public Criteria andReqAckTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("REQ_ACK_TIME <=", value, "reqAckTime");
            return (Criteria) this;
        }

        public Criteria andReqAckTimeIn(List<Timestamp> values) {
            addCriterion("REQ_ACK_TIME in", values, "reqAckTime");
            return (Criteria) this;
        }

        public Criteria andReqAckTimeNotIn(List<Timestamp> values) {
            addCriterion("REQ_ACK_TIME not in", values, "reqAckTime");
            return (Criteria) this;
        }

        public Criteria andReqAckTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("REQ_ACK_TIME between", value1, value2, "reqAckTime");
            return (Criteria) this;
        }

        public Criteria andReqAckTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("REQ_ACK_TIME not between", value1, value2, "reqAckTime");
            return (Criteria) this;
        }

        public Criteria andReqAckBodyIsNull() {
            addCriterion("REQ_ACK_BODY is null");
            return (Criteria) this;
        }

        public Criteria andReqAckBodyIsNotNull() {
            addCriterion("REQ_ACK_BODY is not null");
            return (Criteria) this;
        }

        public Criteria andReqAckBodyEqualTo(String value) {
            addCriterion("REQ_ACK_BODY =", value, "reqAckBody");
            return (Criteria) this;
        }

        public Criteria andReqAckBodyNotEqualTo(String value) {
            addCriterion("REQ_ACK_BODY <>", value, "reqAckBody");
            return (Criteria) this;
        }

        public Criteria andReqAckBodyGreaterThan(String value) {
            addCriterion("REQ_ACK_BODY >", value, "reqAckBody");
            return (Criteria) this;
        }

        public Criteria andReqAckBodyGreaterThanOrEqualTo(String value) {
            addCriterion("REQ_ACK_BODY >=", value, "reqAckBody");
            return (Criteria) this;
        }

        public Criteria andReqAckBodyLessThan(String value) {
            addCriterion("REQ_ACK_BODY <", value, "reqAckBody");
            return (Criteria) this;
        }

        public Criteria andReqAckBodyLessThanOrEqualTo(String value) {
            addCriterion("REQ_ACK_BODY <=", value, "reqAckBody");
            return (Criteria) this;
        }

        public Criteria andReqAckBodyLike(String value) {
            addCriterion("REQ_ACK_BODY like", value, "reqAckBody");
            return (Criteria) this;
        }

        public Criteria andReqAckBodyNotLike(String value) {
            addCriterion("REQ_ACK_BODY not like", value, "reqAckBody");
            return (Criteria) this;
        }

        public Criteria andReqAckBodyIn(List<String> values) {
            addCriterion("REQ_ACK_BODY in", values, "reqAckBody");
            return (Criteria) this;
        }

        public Criteria andReqAckBodyNotIn(List<String> values) {
            addCriterion("REQ_ACK_BODY not in", values, "reqAckBody");
            return (Criteria) this;
        }

        public Criteria andReqAckBodyBetween(String value1, String value2) {
            addCriterion("REQ_ACK_BODY between", value1, value2, "reqAckBody");
            return (Criteria) this;
        }

        public Criteria andReqAckBodyNotBetween(String value1, String value2) {
            addCriterion("REQ_ACK_BODY not between", value1, value2, "reqAckBody");
            return (Criteria) this;
        }

        public Criteria andReqAckStatusIsNull() {
            addCriterion("REQ_ACK_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andReqAckStatusIsNotNull() {
            addCriterion("REQ_ACK_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andReqAckStatusEqualTo(String value) {
            addCriterion("REQ_ACK_STATUS =", value, "reqAckStatus");
            return (Criteria) this;
        }

        public Criteria andReqAckStatusNotEqualTo(String value) {
            addCriterion("REQ_ACK_STATUS <>", value, "reqAckStatus");
            return (Criteria) this;
        }

        public Criteria andReqAckStatusGreaterThan(String value) {
            addCriterion("REQ_ACK_STATUS >", value, "reqAckStatus");
            return (Criteria) this;
        }

        public Criteria andReqAckStatusGreaterThanOrEqualTo(String value) {
            addCriterion("REQ_ACK_STATUS >=", value, "reqAckStatus");
            return (Criteria) this;
        }

        public Criteria andReqAckStatusLessThan(String value) {
            addCriterion("REQ_ACK_STATUS <", value, "reqAckStatus");
            return (Criteria) this;
        }

        public Criteria andReqAckStatusLessThanOrEqualTo(String value) {
            addCriterion("REQ_ACK_STATUS <=", value, "reqAckStatus");
            return (Criteria) this;
        }

        public Criteria andReqAckStatusLike(String value) {
            addCriterion("REQ_ACK_STATUS like", value, "reqAckStatus");
            return (Criteria) this;
        }

        public Criteria andReqAckStatusNotLike(String value) {
            addCriterion("REQ_ACK_STATUS not like", value, "reqAckStatus");
            return (Criteria) this;
        }

        public Criteria andReqAckStatusIn(List<String> values) {
            addCriterion("REQ_ACK_STATUS in", values, "reqAckStatus");
            return (Criteria) this;
        }

        public Criteria andReqAckStatusNotIn(List<String> values) {
            addCriterion("REQ_ACK_STATUS not in", values, "reqAckStatus");
            return (Criteria) this;
        }

        public Criteria andReqAckStatusBetween(String value1, String value2) {
            addCriterion("REQ_ACK_STATUS between", value1, value2, "reqAckStatus");
            return (Criteria) this;
        }

        public Criteria andReqAckStatusNotBetween(String value1, String value2) {
            addCriterion("REQ_ACK_STATUS not between", value1, value2, "reqAckStatus");
            return (Criteria) this;
        }

        public Criteria andReqAckMessageIsNull() {
            addCriterion("REQ_ACK_MESSAGE is null");
            return (Criteria) this;
        }

        public Criteria andReqAckMessageIsNotNull() {
            addCriterion("REQ_ACK_MESSAGE is not null");
            return (Criteria) this;
        }

        public Criteria andReqAckMessageEqualTo(String value) {
            addCriterion("REQ_ACK_MESSAGE =", value, "reqAckMessage");
            return (Criteria) this;
        }

        public Criteria andReqAckMessageNotEqualTo(String value) {
            addCriterion("REQ_ACK_MESSAGE <>", value, "reqAckMessage");
            return (Criteria) this;
        }

        public Criteria andReqAckMessageGreaterThan(String value) {
            addCriterion("REQ_ACK_MESSAGE >", value, "reqAckMessage");
            return (Criteria) this;
        }

        public Criteria andReqAckMessageGreaterThanOrEqualTo(String value) {
            addCriterion("REQ_ACK_MESSAGE >=", value, "reqAckMessage");
            return (Criteria) this;
        }

        public Criteria andReqAckMessageLessThan(String value) {
            addCriterion("REQ_ACK_MESSAGE <", value, "reqAckMessage");
            return (Criteria) this;
        }

        public Criteria andReqAckMessageLessThanOrEqualTo(String value) {
            addCriterion("REQ_ACK_MESSAGE <=", value, "reqAckMessage");
            return (Criteria) this;
        }

        public Criteria andReqAckMessageLike(String value) {
            addCriterion("REQ_ACK_MESSAGE like", value, "reqAckMessage");
            return (Criteria) this;
        }

        public Criteria andReqAckMessageNotLike(String value) {
            addCriterion("REQ_ACK_MESSAGE not like", value, "reqAckMessage");
            return (Criteria) this;
        }

        public Criteria andReqAckMessageIn(List<String> values) {
            addCriterion("REQ_ACK_MESSAGE in", values, "reqAckMessage");
            return (Criteria) this;
        }

        public Criteria andReqAckMessageNotIn(List<String> values) {
            addCriterion("REQ_ACK_MESSAGE not in", values, "reqAckMessage");
            return (Criteria) this;
        }

        public Criteria andReqAckMessageBetween(String value1, String value2) {
            addCriterion("REQ_ACK_MESSAGE between", value1, value2, "reqAckMessage");
            return (Criteria) this;
        }

        public Criteria andReqAckMessageNotBetween(String value1, String value2) {
            addCriterion("REQ_ACK_MESSAGE not between", value1, value2, "reqAckMessage");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("STATE is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("STATE is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("STATE =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("STATE <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("STATE >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("STATE >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("STATE <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("STATE <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("STATE like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("STATE not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("STATE in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("STATE not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("STATE between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("STATE not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateDescIsNull() {
            addCriterion("STATE_DESC is null");
            return (Criteria) this;
        }

        public Criteria andStateDescIsNotNull() {
            addCriterion("STATE_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andStateDescEqualTo(String value) {
            addCriterion("STATE_DESC =", value, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateDescNotEqualTo(String value) {
            addCriterion("STATE_DESC <>", value, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateDescGreaterThan(String value) {
            addCriterion("STATE_DESC >", value, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateDescGreaterThanOrEqualTo(String value) {
            addCriterion("STATE_DESC >=", value, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateDescLessThan(String value) {
            addCriterion("STATE_DESC <", value, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateDescLessThanOrEqualTo(String value) {
            addCriterion("STATE_DESC <=", value, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateDescLike(String value) {
            addCriterion("STATE_DESC like", value, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateDescNotLike(String value) {
            addCriterion("STATE_DESC not like", value, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateDescIn(List<String> values) {
            addCriterion("STATE_DESC in", values, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateDescNotIn(List<String> values) {
            addCriterion("STATE_DESC not in", values, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateDescBetween(String value1, String value2) {
            addCriterion("STATE_DESC between", value1, value2, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateDescNotBetween(String value1, String value2) {
            addCriterion("STATE_DESC not between", value1, value2, "stateDesc");
            return (Criteria) this;
        }

        public Criteria andStateChgTimeIsNull() {
            addCriterion("STATE_CHG_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStateChgTimeIsNotNull() {
            addCriterion("STATE_CHG_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStateChgTimeEqualTo(Timestamp value) {
            addCriterion("STATE_CHG_TIME =", value, "stateChgTime");
            return (Criteria) this;
        }

        public Criteria andStateChgTimeNotEqualTo(Timestamp value) {
            addCriterion("STATE_CHG_TIME <>", value, "stateChgTime");
            return (Criteria) this;
        }

        public Criteria andStateChgTimeGreaterThan(Timestamp value) {
            addCriterion("STATE_CHG_TIME >", value, "stateChgTime");
            return (Criteria) this;
        }

        public Criteria andStateChgTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("STATE_CHG_TIME >=", value, "stateChgTime");
            return (Criteria) this;
        }

        public Criteria andStateChgTimeLessThan(Timestamp value) {
            addCriterion("STATE_CHG_TIME <", value, "stateChgTime");
            return (Criteria) this;
        }

        public Criteria andStateChgTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("STATE_CHG_TIME <=", value, "stateChgTime");
            return (Criteria) this;
        }

        public Criteria andStateChgTimeIn(List<Timestamp> values) {
            addCriterion("STATE_CHG_TIME in", values, "stateChgTime");
            return (Criteria) this;
        }

        public Criteria andStateChgTimeNotIn(List<Timestamp> values) {
            addCriterion("STATE_CHG_TIME not in", values, "stateChgTime");
            return (Criteria) this;
        }

        public Criteria andStateChgTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("STATE_CHG_TIME between", value1, value2, "stateChgTime");
            return (Criteria) this;
        }

        public Criteria andStateChgTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("STATE_CHG_TIME not between", value1, value2, "stateChgTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Timestamp value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Timestamp value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Timestamp> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Timestamp> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andSendTimesIsNull() {
            addCriterion("SEND_TIMES is null");
            return (Criteria) this;
        }

        public Criteria andSendTimesIsNotNull() {
            addCriterion("SEND_TIMES is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimesEqualTo(Integer value) {
            addCriterion("SEND_TIMES =", value, "sendTimes");
            return (Criteria) this;
        }

        public Criteria andSendTimesNotEqualTo(Integer value) {
            addCriterion("SEND_TIMES <>", value, "sendTimes");
            return (Criteria) this;
        }

        public Criteria andSendTimesGreaterThan(Integer value) {
            addCriterion("SEND_TIMES >", value, "sendTimes");
            return (Criteria) this;
        }

        public Criteria andSendTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("SEND_TIMES >=", value, "sendTimes");
            return (Criteria) this;
        }

        public Criteria andSendTimesLessThan(Integer value) {
            addCriterion("SEND_TIMES <", value, "sendTimes");
            return (Criteria) this;
        }

        public Criteria andSendTimesLessThanOrEqualTo(Integer value) {
            addCriterion("SEND_TIMES <=", value, "sendTimes");
            return (Criteria) this;
        }

        public Criteria andSendTimesIn(List<Integer> values) {
            addCriterion("SEND_TIMES in", values, "sendTimes");
            return (Criteria) this;
        }

        public Criteria andSendTimesNotIn(List<Integer> values) {
            addCriterion("SEND_TIMES not in", values, "sendTimes");
            return (Criteria) this;
        }

        public Criteria andSendTimesBetween(Integer value1, Integer value2) {
            addCriterion("SEND_TIMES between", value1, value2, "sendTimes");
            return (Criteria) this;
        }

        public Criteria andSendTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("SEND_TIMES not between", value1, value2, "sendTimes");
            return (Criteria) this;
        }

        public Criteria andRelaSeqIsNull() {
            addCriterion("RELA_SEQ is null");
            return (Criteria) this;
        }

        public Criteria andRelaSeqIsNotNull() {
            addCriterion("RELA_SEQ is not null");
            return (Criteria) this;
        }

        public Criteria andRelaSeqEqualTo(String value) {
            addCriterion("RELA_SEQ =", value, "relaSeq");
            return (Criteria) this;
        }

        public Criteria andRelaSeqNotEqualTo(String value) {
            addCriterion("RELA_SEQ <>", value, "relaSeq");
            return (Criteria) this;
        }

        public Criteria andRelaSeqGreaterThan(String value) {
            addCriterion("RELA_SEQ >", value, "relaSeq");
            return (Criteria) this;
        }

        public Criteria andRelaSeqGreaterThanOrEqualTo(String value) {
            addCriterion("RELA_SEQ >=", value, "relaSeq");
            return (Criteria) this;
        }

        public Criteria andRelaSeqLessThan(String value) {
            addCriterion("RELA_SEQ <", value, "relaSeq");
            return (Criteria) this;
        }

        public Criteria andRelaSeqLessThanOrEqualTo(String value) {
            addCriterion("RELA_SEQ <=", value, "relaSeq");
            return (Criteria) this;
        }

        public Criteria andRelaSeqLike(String value) {
            addCriterion("RELA_SEQ like", value, "relaSeq");
            return (Criteria) this;
        }

        public Criteria andRelaSeqNotLike(String value) {
            addCriterion("RELA_SEQ not like", value, "relaSeq");
            return (Criteria) this;
        }

        public Criteria andRelaSeqIn(List<String> values) {
            addCriterion("RELA_SEQ in", values, "relaSeq");
            return (Criteria) this;
        }

        public Criteria andRelaSeqNotIn(List<String> values) {
            addCriterion("RELA_SEQ not in", values, "relaSeq");
            return (Criteria) this;
        }

        public Criteria andRelaSeqBetween(String value1, String value2) {
            addCriterion("RELA_SEQ between", value1, value2, "relaSeq");
            return (Criteria) this;
        }

        public Criteria andRelaSeqNotBetween(String value1, String value2) {
            addCriterion("RELA_SEQ not between", value1, value2, "relaSeq");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}