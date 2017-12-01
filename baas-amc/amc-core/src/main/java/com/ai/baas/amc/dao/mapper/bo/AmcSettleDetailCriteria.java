package com.ai.baas.amc.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AmcSettleDetailCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    protected String billMonth;
    
    public String getBillMonth() {
        return billMonth;
    }

    public void setBillMonth(String billMonth) {
        this.billMonth = billMonth;
    }
    public AmcSettleDetailCriteria() {
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

        public Criteria andSerialCodeIsNull() {
            addCriterion("serial_code is null");
            return (Criteria) this;
        }

        public Criteria andSerialCodeIsNotNull() {
            addCriterion("serial_code is not null");
            return (Criteria) this;
        }

        public Criteria andSerialCodeEqualTo(String value) {
            addCriterion("serial_code =", value, "serialCode");
            return (Criteria) this;
        }

        public Criteria andSerialCodeNotEqualTo(String value) {
            addCriterion("serial_code <>", value, "serialCode");
            return (Criteria) this;
        }

        public Criteria andSerialCodeGreaterThan(String value) {
            addCriterion("serial_code >", value, "serialCode");
            return (Criteria) this;
        }

        public Criteria andSerialCodeGreaterThanOrEqualTo(String value) {
            addCriterion("serial_code >=", value, "serialCode");
            return (Criteria) this;
        }

        public Criteria andSerialCodeLessThan(String value) {
            addCriterion("serial_code <", value, "serialCode");
            return (Criteria) this;
        }

        public Criteria andSerialCodeLessThanOrEqualTo(String value) {
            addCriterion("serial_code <=", value, "serialCode");
            return (Criteria) this;
        }

        public Criteria andSerialCodeLike(String value) {
            addCriterion("serial_code like", value, "serialCode");
            return (Criteria) this;
        }

        public Criteria andSerialCodeNotLike(String value) {
            addCriterion("serial_code not like", value, "serialCode");
            return (Criteria) this;
        }

        public Criteria andSerialCodeIn(List<String> values) {
            addCriterion("serial_code in", values, "serialCode");
            return (Criteria) this;
        }

        public Criteria andSerialCodeNotIn(List<String> values) {
            addCriterion("serial_code not in", values, "serialCode");
            return (Criteria) this;
        }

        public Criteria andSerialCodeBetween(String value1, String value2) {
            addCriterion("serial_code between", value1, value2, "serialCode");
            return (Criteria) this;
        }

        public Criteria andSerialCodeNotBetween(String value1, String value2) {
            addCriterion("serial_code not between", value1, value2, "serialCode");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNull() {
            addCriterion("tenant_id is null");
            return (Criteria) this;
        }

        public Criteria andTenantIdIsNotNull() {
            addCriterion("tenant_id is not null");
            return (Criteria) this;
        }

        public Criteria andTenantIdEqualTo(String value) {
            addCriterion("tenant_id =", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotEqualTo(String value) {
            addCriterion("tenant_id <>", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThan(String value) {
            addCriterion("tenant_id >", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdGreaterThanOrEqualTo(String value) {
            addCriterion("tenant_id >=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThan(String value) {
            addCriterion("tenant_id <", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLessThanOrEqualTo(String value) {
            addCriterion("tenant_id <=", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdLike(String value) {
            addCriterion("tenant_id like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotLike(String value) {
            addCriterion("tenant_id not like", value, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdIn(List<String> values) {
            addCriterion("tenant_id in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotIn(List<String> values) {
            addCriterion("tenant_id not in", values, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdBetween(String value1, String value2) {
            addCriterion("tenant_id between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andTenantIdNotBetween(String value1, String value2) {
            addCriterion("tenant_id not between", value1, value2, "tenantId");
            return (Criteria) this;
        }

        public Criteria andBusiOperCodeIsNull() {
            addCriterion("busi_oper_code is null");
            return (Criteria) this;
        }

        public Criteria andBusiOperCodeIsNotNull() {
            addCriterion("busi_oper_code is not null");
            return (Criteria) this;
        }

        public Criteria andBusiOperCodeEqualTo(String value) {
            addCriterion("busi_oper_code =", value, "busiOperCode");
            return (Criteria) this;
        }

        public Criteria andBusiOperCodeNotEqualTo(String value) {
            addCriterion("busi_oper_code <>", value, "busiOperCode");
            return (Criteria) this;
        }

        public Criteria andBusiOperCodeGreaterThan(String value) {
            addCriterion("busi_oper_code >", value, "busiOperCode");
            return (Criteria) this;
        }

        public Criteria andBusiOperCodeGreaterThanOrEqualTo(String value) {
            addCriterion("busi_oper_code >=", value, "busiOperCode");
            return (Criteria) this;
        }

        public Criteria andBusiOperCodeLessThan(String value) {
            addCriterion("busi_oper_code <", value, "busiOperCode");
            return (Criteria) this;
        }

        public Criteria andBusiOperCodeLessThanOrEqualTo(String value) {
            addCriterion("busi_oper_code <=", value, "busiOperCode");
            return (Criteria) this;
        }

        public Criteria andBusiOperCodeLike(String value) {
            addCriterion("busi_oper_code like", value, "busiOperCode");
            return (Criteria) this;
        }

        public Criteria andBusiOperCodeNotLike(String value) {
            addCriterion("busi_oper_code not like", value, "busiOperCode");
            return (Criteria) this;
        }

        public Criteria andBusiOperCodeIn(List<String> values) {
            addCriterion("busi_oper_code in", values, "busiOperCode");
            return (Criteria) this;
        }

        public Criteria andBusiOperCodeNotIn(List<String> values) {
            addCriterion("busi_oper_code not in", values, "busiOperCode");
            return (Criteria) this;
        }

        public Criteria andBusiOperCodeBetween(String value1, String value2) {
            addCriterion("busi_oper_code between", value1, value2, "busiOperCode");
            return (Criteria) this;
        }

        public Criteria andBusiOperCodeNotBetween(String value1, String value2) {
            addCriterion("busi_oper_code not between", value1, value2, "busiOperCode");
            return (Criteria) this;
        }

        public Criteria andAcctIdIsNull() {
            addCriterion("acct_id is null");
            return (Criteria) this;
        }

        public Criteria andAcctIdIsNotNull() {
            addCriterion("acct_id is not null");
            return (Criteria) this;
        }

        public Criteria andAcctIdEqualTo(Long value) {
            addCriterion("acct_id =", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdNotEqualTo(Long value) {
            addCriterion("acct_id <>", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdGreaterThan(Long value) {
            addCriterion("acct_id >", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdGreaterThanOrEqualTo(Long value) {
            addCriterion("acct_id >=", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdLessThan(Long value) {
            addCriterion("acct_id <", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdLessThanOrEqualTo(Long value) {
            addCriterion("acct_id <=", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdIn(List<Long> values) {
            addCriterion("acct_id in", values, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdNotIn(List<Long> values) {
            addCriterion("acct_id not in", values, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdBetween(Long value1, Long value2) {
            addCriterion("acct_id between", value1, value2, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdNotBetween(Long value1, Long value2) {
            addCriterion("acct_id not between", value1, value2, "acctId");
            return (Criteria) this;
        }

        public Criteria andSettleModeIsNull() {
            addCriterion("settle_mode is null");
            return (Criteria) this;
        }

        public Criteria andSettleModeIsNotNull() {
            addCriterion("settle_mode is not null");
            return (Criteria) this;
        }

        public Criteria andSettleModeEqualTo(Integer value) {
            addCriterion("settle_mode =", value, "settleMode");
            return (Criteria) this;
        }

        public Criteria andSettleModeNotEqualTo(Integer value) {
            addCriterion("settle_mode <>", value, "settleMode");
            return (Criteria) this;
        }

        public Criteria andSettleModeGreaterThan(Integer value) {
            addCriterion("settle_mode >", value, "settleMode");
            return (Criteria) this;
        }

        public Criteria andSettleModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("settle_mode >=", value, "settleMode");
            return (Criteria) this;
        }

        public Criteria andSettleModeLessThan(Integer value) {
            addCriterion("settle_mode <", value, "settleMode");
            return (Criteria) this;
        }

        public Criteria andSettleModeLessThanOrEqualTo(Integer value) {
            addCriterion("settle_mode <=", value, "settleMode");
            return (Criteria) this;
        }

        public Criteria andSettleModeIn(List<Integer> values) {
            addCriterion("settle_mode in", values, "settleMode");
            return (Criteria) this;
        }

        public Criteria andSettleModeNotIn(List<Integer> values) {
            addCriterion("settle_mode not in", values, "settleMode");
            return (Criteria) this;
        }

        public Criteria andSettleModeBetween(Integer value1, Integer value2) {
            addCriterion("settle_mode between", value1, value2, "settleMode");
            return (Criteria) this;
        }

        public Criteria andSettleModeNotBetween(Integer value1, Integer value2) {
            addCriterion("settle_mode not between", value1, value2, "settleMode");
            return (Criteria) this;
        }

        public Criteria andSettleTypeIsNull() {
            addCriterion("settle_type is null");
            return (Criteria) this;
        }

        public Criteria andSettleTypeIsNotNull() {
            addCriterion("settle_type is not null");
            return (Criteria) this;
        }

        public Criteria andSettleTypeEqualTo(Integer value) {
            addCriterion("settle_type =", value, "settleType");
            return (Criteria) this;
        }

        public Criteria andSettleTypeNotEqualTo(Integer value) {
            addCriterion("settle_type <>", value, "settleType");
            return (Criteria) this;
        }

        public Criteria andSettleTypeGreaterThan(Integer value) {
            addCriterion("settle_type >", value, "settleType");
            return (Criteria) this;
        }

        public Criteria andSettleTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("settle_type >=", value, "settleType");
            return (Criteria) this;
        }

        public Criteria andSettleTypeLessThan(Integer value) {
            addCriterion("settle_type <", value, "settleType");
            return (Criteria) this;
        }

        public Criteria andSettleTypeLessThanOrEqualTo(Integer value) {
            addCriterion("settle_type <=", value, "settleType");
            return (Criteria) this;
        }

        public Criteria andSettleTypeIn(List<Integer> values) {
            addCriterion("settle_type in", values, "settleType");
            return (Criteria) this;
        }

        public Criteria andSettleTypeNotIn(List<Integer> values) {
            addCriterion("settle_type not in", values, "settleType");
            return (Criteria) this;
        }

        public Criteria andSettleTypeBetween(Integer value1, Integer value2) {
            addCriterion("settle_type between", value1, value2, "settleType");
            return (Criteria) this;
        }

        public Criteria andSettleTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("settle_type not between", value1, value2, "settleType");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNull() {
            addCriterion("book_id is null");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNotNull() {
            addCriterion("book_id is not null");
            return (Criteria) this;
        }

        public Criteria andBookIdEqualTo(Long value) {
            addCriterion("book_id =", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotEqualTo(Long value) {
            addCriterion("book_id <>", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThan(Long value) {
            addCriterion("book_id >", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThanOrEqualTo(Long value) {
            addCriterion("book_id >=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThan(Long value) {
            addCriterion("book_id <", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThanOrEqualTo(Long value) {
            addCriterion("book_id <=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdIn(List<Long> values) {
            addCriterion("book_id in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotIn(List<Long> values) {
            addCriterion("book_id not in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdBetween(Long value1, Long value2) {
            addCriterion("book_id between", value1, value2, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotBetween(Long value1, Long value2) {
            addCriterion("book_id not between", value1, value2, "bookId");
            return (Criteria) this;
        }

        public Criteria andSubsIdIsNull() {
            addCriterion("subs_id is null");
            return (Criteria) this;
        }

        public Criteria andSubsIdIsNotNull() {
            addCriterion("subs_id is not null");
            return (Criteria) this;
        }

        public Criteria andSubsIdEqualTo(Long value) {
            addCriterion("subs_id =", value, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdNotEqualTo(Long value) {
            addCriterion("subs_id <>", value, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdGreaterThan(Long value) {
            addCriterion("subs_id >", value, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("subs_id >=", value, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdLessThan(Long value) {
            addCriterion("subs_id <", value, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdLessThanOrEqualTo(Long value) {
            addCriterion("subs_id <=", value, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdIn(List<Long> values) {
            addCriterion("subs_id in", values, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdNotIn(List<Long> values) {
            addCriterion("subs_id not in", values, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdBetween(Long value1, Long value2) {
            addCriterion("subs_id between", value1, value2, "subsId");
            return (Criteria) this;
        }

        public Criteria andSubsIdNotBetween(Long value1, Long value2) {
            addCriterion("subs_id not between", value1, value2, "subsId");
            return (Criteria) this;
        }

        public Criteria andSvcTypeIsNull() {
            addCriterion("svc_type is null");
            return (Criteria) this;
        }

        public Criteria andSvcTypeIsNotNull() {
            addCriterion("svc_type is not null");
            return (Criteria) this;
        }

        public Criteria andSvcTypeEqualTo(Integer value) {
            addCriterion("svc_type =", value, "svcType");
            return (Criteria) this;
        }

        public Criteria andSvcTypeNotEqualTo(Integer value) {
            addCriterion("svc_type <>", value, "svcType");
            return (Criteria) this;
        }

        public Criteria andSvcTypeGreaterThan(Integer value) {
            addCriterion("svc_type >", value, "svcType");
            return (Criteria) this;
        }

        public Criteria andSvcTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("svc_type >=", value, "svcType");
            return (Criteria) this;
        }

        public Criteria andSvcTypeLessThan(Integer value) {
            addCriterion("svc_type <", value, "svcType");
            return (Criteria) this;
        }

        public Criteria andSvcTypeLessThanOrEqualTo(Integer value) {
            addCriterion("svc_type <=", value, "svcType");
            return (Criteria) this;
        }

        public Criteria andSvcTypeIn(List<Integer> values) {
            addCriterion("svc_type in", values, "svcType");
            return (Criteria) this;
        }

        public Criteria andSvcTypeNotIn(List<Integer> values) {
            addCriterion("svc_type not in", values, "svcType");
            return (Criteria) this;
        }

        public Criteria andSvcTypeBetween(Integer value1, Integer value2) {
            addCriterion("svc_type between", value1, value2, "svcType");
            return (Criteria) this;
        }

        public Criteria andSvcTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("svc_type not between", value1, value2, "svcType");
            return (Criteria) this;
        }

        public Criteria andFundSubjectIdIsNull() {
            addCriterion("fund_subject_id is null");
            return (Criteria) this;
        }

        public Criteria andFundSubjectIdIsNotNull() {
            addCriterion("fund_subject_id is not null");
            return (Criteria) this;
        }

        public Criteria andFundSubjectIdEqualTo(Long value) {
            addCriterion("fund_subject_id =", value, "fundSubjectId");
            return (Criteria) this;
        }

        public Criteria andFundSubjectIdNotEqualTo(Long value) {
            addCriterion("fund_subject_id <>", value, "fundSubjectId");
            return (Criteria) this;
        }

        public Criteria andFundSubjectIdGreaterThan(Long value) {
            addCriterion("fund_subject_id >", value, "fundSubjectId");
            return (Criteria) this;
        }

        public Criteria andFundSubjectIdGreaterThanOrEqualTo(Long value) {
            addCriterion("fund_subject_id >=", value, "fundSubjectId");
            return (Criteria) this;
        }

        public Criteria andFundSubjectIdLessThan(Long value) {
            addCriterion("fund_subject_id <", value, "fundSubjectId");
            return (Criteria) this;
        }

        public Criteria andFundSubjectIdLessThanOrEqualTo(Long value) {
            addCriterion("fund_subject_id <=", value, "fundSubjectId");
            return (Criteria) this;
        }

        public Criteria andFundSubjectIdIn(List<Long> values) {
            addCriterion("fund_subject_id in", values, "fundSubjectId");
            return (Criteria) this;
        }

        public Criteria andFundSubjectIdNotIn(List<Long> values) {
            addCriterion("fund_subject_id not in", values, "fundSubjectId");
            return (Criteria) this;
        }

        public Criteria andFundSubjectIdBetween(Long value1, Long value2) {
            addCriterion("fund_subject_id between", value1, value2, "fundSubjectId");
            return (Criteria) this;
        }

        public Criteria andFundSubjectIdNotBetween(Long value1, Long value2) {
            addCriterion("fund_subject_id not between", value1, value2, "fundSubjectId");
            return (Criteria) this;
        }

        public Criteria andCycleMonthIsNull() {
            addCriterion("cycle_month is null");
            return (Criteria) this;
        }

        public Criteria andCycleMonthIsNotNull() {
            addCriterion("cycle_month is not null");
            return (Criteria) this;
        }

        public Criteria andCycleMonthEqualTo(String value) {
            addCriterion("cycle_month =", value, "cycleMonth");
            return (Criteria) this;
        }

        public Criteria andCycleMonthNotEqualTo(String value) {
            addCriterion("cycle_month <>", value, "cycleMonth");
            return (Criteria) this;
        }

        public Criteria andCycleMonthGreaterThan(String value) {
            addCriterion("cycle_month >", value, "cycleMonth");
            return (Criteria) this;
        }

        public Criteria andCycleMonthGreaterThanOrEqualTo(String value) {
            addCriterion("cycle_month >=", value, "cycleMonth");
            return (Criteria) this;
        }

        public Criteria andCycleMonthLessThan(String value) {
            addCriterion("cycle_month <", value, "cycleMonth");
            return (Criteria) this;
        }

        public Criteria andCycleMonthLessThanOrEqualTo(String value) {
            addCriterion("cycle_month <=", value, "cycleMonth");
            return (Criteria) this;
        }

        public Criteria andCycleMonthLike(String value) {
            addCriterion("cycle_month like", value, "cycleMonth");
            return (Criteria) this;
        }

        public Criteria andCycleMonthNotLike(String value) {
            addCriterion("cycle_month not like", value, "cycleMonth");
            return (Criteria) this;
        }

        public Criteria andCycleMonthIn(List<String> values) {
            addCriterion("cycle_month in", values, "cycleMonth");
            return (Criteria) this;
        }

        public Criteria andCycleMonthNotIn(List<String> values) {
            addCriterion("cycle_month not in", values, "cycleMonth");
            return (Criteria) this;
        }

        public Criteria andCycleMonthBetween(String value1, String value2) {
            addCriterion("cycle_month between", value1, value2, "cycleMonth");
            return (Criteria) this;
        }

        public Criteria andCycleMonthNotBetween(String value1, String value2) {
            addCriterion("cycle_month not between", value1, value2, "cycleMonth");
            return (Criteria) this;
        }

        public Criteria andInvoiceSeqIsNull() {
            addCriterion("invoice_seq is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceSeqIsNotNull() {
            addCriterion("invoice_seq is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceSeqEqualTo(Long value) {
            addCriterion("invoice_seq =", value, "invoiceSeq");
            return (Criteria) this;
        }

        public Criteria andInvoiceSeqNotEqualTo(Long value) {
            addCriterion("invoice_seq <>", value, "invoiceSeq");
            return (Criteria) this;
        }

        public Criteria andInvoiceSeqGreaterThan(Long value) {
            addCriterion("invoice_seq >", value, "invoiceSeq");
            return (Criteria) this;
        }

        public Criteria andInvoiceSeqGreaterThanOrEqualTo(Long value) {
            addCriterion("invoice_seq >=", value, "invoiceSeq");
            return (Criteria) this;
        }

        public Criteria andInvoiceSeqLessThan(Long value) {
            addCriterion("invoice_seq <", value, "invoiceSeq");
            return (Criteria) this;
        }

        public Criteria andInvoiceSeqLessThanOrEqualTo(Long value) {
            addCriterion("invoice_seq <=", value, "invoiceSeq");
            return (Criteria) this;
        }

        public Criteria andInvoiceSeqIn(List<Long> values) {
            addCriterion("invoice_seq in", values, "invoiceSeq");
            return (Criteria) this;
        }

        public Criteria andInvoiceSeqNotIn(List<Long> values) {
            addCriterion("invoice_seq not in", values, "invoiceSeq");
            return (Criteria) this;
        }

        public Criteria andInvoiceSeqBetween(Long value1, Long value2) {
            addCriterion("invoice_seq between", value1, value2, "invoiceSeq");
            return (Criteria) this;
        }

        public Criteria andInvoiceSeqNotBetween(Long value1, Long value2) {
            addCriterion("invoice_seq not between", value1, value2, "invoiceSeq");
            return (Criteria) this;
        }

        public Criteria andChargeSeqIsNull() {
            addCriterion("charge_seq is null");
            return (Criteria) this;
        }

        public Criteria andChargeSeqIsNotNull() {
            addCriterion("charge_seq is not null");
            return (Criteria) this;
        }

        public Criteria andChargeSeqEqualTo(Long value) {
            addCriterion("charge_seq =", value, "chargeSeq");
            return (Criteria) this;
        }

        public Criteria andChargeSeqNotEqualTo(Long value) {
            addCriterion("charge_seq <>", value, "chargeSeq");
            return (Criteria) this;
        }

        public Criteria andChargeSeqGreaterThan(Long value) {
            addCriterion("charge_seq >", value, "chargeSeq");
            return (Criteria) this;
        }

        public Criteria andChargeSeqGreaterThanOrEqualTo(Long value) {
            addCriterion("charge_seq >=", value, "chargeSeq");
            return (Criteria) this;
        }

        public Criteria andChargeSeqLessThan(Long value) {
            addCriterion("charge_seq <", value, "chargeSeq");
            return (Criteria) this;
        }

        public Criteria andChargeSeqLessThanOrEqualTo(Long value) {
            addCriterion("charge_seq <=", value, "chargeSeq");
            return (Criteria) this;
        }

        public Criteria andChargeSeqIn(List<Long> values) {
            addCriterion("charge_seq in", values, "chargeSeq");
            return (Criteria) this;
        }

        public Criteria andChargeSeqNotIn(List<Long> values) {
            addCriterion("charge_seq not in", values, "chargeSeq");
            return (Criteria) this;
        }

        public Criteria andChargeSeqBetween(Long value1, Long value2) {
            addCriterion("charge_seq between", value1, value2, "chargeSeq");
            return (Criteria) this;
        }

        public Criteria andChargeSeqNotBetween(Long value1, Long value2) {
            addCriterion("charge_seq not between", value1, value2, "chargeSeq");
            return (Criteria) this;
        }

        public Criteria andFeeSubjectIdIsNull() {
            addCriterion("fee_subject_id is null");
            return (Criteria) this;
        }

        public Criteria andFeeSubjectIdIsNotNull() {
            addCriterion("fee_subject_id is not null");
            return (Criteria) this;
        }

        public Criteria andFeeSubjectIdEqualTo(Long value) {
            addCriterion("fee_subject_id =", value, "feeSubjectId");
            return (Criteria) this;
        }

        public Criteria andFeeSubjectIdNotEqualTo(Long value) {
            addCriterion("fee_subject_id <>", value, "feeSubjectId");
            return (Criteria) this;
        }

        public Criteria andFeeSubjectIdGreaterThan(Long value) {
            addCriterion("fee_subject_id >", value, "feeSubjectId");
            return (Criteria) this;
        }

        public Criteria andFeeSubjectIdGreaterThanOrEqualTo(Long value) {
            addCriterion("fee_subject_id >=", value, "feeSubjectId");
            return (Criteria) this;
        }

        public Criteria andFeeSubjectIdLessThan(Long value) {
            addCriterion("fee_subject_id <", value, "feeSubjectId");
            return (Criteria) this;
        }

        public Criteria andFeeSubjectIdLessThanOrEqualTo(Long value) {
            addCriterion("fee_subject_id <=", value, "feeSubjectId");
            return (Criteria) this;
        }

        public Criteria andFeeSubjectIdIn(List<Long> values) {
            addCriterion("fee_subject_id in", values, "feeSubjectId");
            return (Criteria) this;
        }

        public Criteria andFeeSubjectIdNotIn(List<Long> values) {
            addCriterion("fee_subject_id not in", values, "feeSubjectId");
            return (Criteria) this;
        }

        public Criteria andFeeSubjectIdBetween(Long value1, Long value2) {
            addCriterion("fee_subject_id between", value1, value2, "feeSubjectId");
            return (Criteria) this;
        }

        public Criteria andFeeSubjectIdNotBetween(Long value1, Long value2) {
            addCriterion("fee_subject_id not between", value1, value2, "feeSubjectId");
            return (Criteria) this;
        }

        public Criteria andTotalIsNull() {
            addCriterion("total is null");
            return (Criteria) this;
        }

        public Criteria andTotalIsNotNull() {
            addCriterion("total is not null");
            return (Criteria) this;
        }

        public Criteria andTotalEqualTo(Long value) {
            addCriterion("total =", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotEqualTo(Long value) {
            addCriterion("total <>", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThan(Long value) {
            addCriterion("total >", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThanOrEqualTo(Long value) {
            addCriterion("total >=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThan(Long value) {
            addCriterion("total <", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThanOrEqualTo(Long value) {
            addCriterion("total <=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalIn(List<Long> values) {
            addCriterion("total in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotIn(List<Long> values) {
            addCriterion("total not in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalBetween(Long value1, Long value2) {
            addCriterion("total between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotBetween(Long value1, Long value2) {
            addCriterion("total not between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Timestamp value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Timestamp value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Timestamp value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Timestamp value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Timestamp> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Timestamp> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andLastStatusDateIsNull() {
            addCriterion("last_status_date is null");
            return (Criteria) this;
        }

        public Criteria andLastStatusDateIsNotNull() {
            addCriterion("last_status_date is not null");
            return (Criteria) this;
        }

        public Criteria andLastStatusDateEqualTo(Timestamp value) {
            addCriterion("last_status_date =", value, "lastStatusDate");
            return (Criteria) this;
        }

        public Criteria andLastStatusDateNotEqualTo(Timestamp value) {
            addCriterion("last_status_date <>", value, "lastStatusDate");
            return (Criteria) this;
        }

        public Criteria andLastStatusDateGreaterThan(Timestamp value) {
            addCriterion("last_status_date >", value, "lastStatusDate");
            return (Criteria) this;
        }

        public Criteria andLastStatusDateGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("last_status_date >=", value, "lastStatusDate");
            return (Criteria) this;
        }

        public Criteria andLastStatusDateLessThan(Timestamp value) {
            addCriterion("last_status_date <", value, "lastStatusDate");
            return (Criteria) this;
        }

        public Criteria andLastStatusDateLessThanOrEqualTo(Timestamp value) {
            addCriterion("last_status_date <=", value, "lastStatusDate");
            return (Criteria) this;
        }

        public Criteria andLastStatusDateIn(List<Timestamp> values) {
            addCriterion("last_status_date in", values, "lastStatusDate");
            return (Criteria) this;
        }

        public Criteria andLastStatusDateNotIn(List<Timestamp> values) {
            addCriterion("last_status_date not in", values, "lastStatusDate");
            return (Criteria) this;
        }

        public Criteria andLastStatusDateBetween(Timestamp value1, Timestamp value2) {
            addCriterion("last_status_date between", value1, value2, "lastStatusDate");
            return (Criteria) this;
        }

        public Criteria andLastStatusDateNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("last_status_date not between", value1, value2, "lastStatusDate");
            return (Criteria) this;
        }

        public Criteria andSettleOrderIsNull() {
            addCriterion("settle_order is null");
            return (Criteria) this;
        }

        public Criteria andSettleOrderIsNotNull() {
            addCriterion("settle_order is not null");
            return (Criteria) this;
        }

        public Criteria andSettleOrderEqualTo(Long value) {
            addCriterion("settle_order =", value, "settleOrder");
            return (Criteria) this;
        }

        public Criteria andSettleOrderNotEqualTo(Long value) {
            addCriterion("settle_order <>", value, "settleOrder");
            return (Criteria) this;
        }

        public Criteria andSettleOrderGreaterThan(Long value) {
            addCriterion("settle_order >", value, "settleOrder");
            return (Criteria) this;
        }

        public Criteria andSettleOrderGreaterThanOrEqualTo(Long value) {
            addCriterion("settle_order >=", value, "settleOrder");
            return (Criteria) this;
        }

        public Criteria andSettleOrderLessThan(Long value) {
            addCriterion("settle_order <", value, "settleOrder");
            return (Criteria) this;
        }

        public Criteria andSettleOrderLessThanOrEqualTo(Long value) {
            addCriterion("settle_order <=", value, "settleOrder");
            return (Criteria) this;
        }

        public Criteria andSettleOrderIn(List<Long> values) {
            addCriterion("settle_order in", values, "settleOrder");
            return (Criteria) this;
        }

        public Criteria andSettleOrderNotIn(List<Long> values) {
            addCriterion("settle_order not in", values, "settleOrder");
            return (Criteria) this;
        }

        public Criteria andSettleOrderBetween(Long value1, Long value2) {
            addCriterion("settle_order between", value1, value2, "settleOrder");
            return (Criteria) this;
        }

        public Criteria andSettleOrderNotBetween(Long value1, Long value2) {
            addCriterion("settle_order not between", value1, value2, "settleOrder");
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