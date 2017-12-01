package com.ai.baas.ccp.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OmcScoutLogCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public OmcScoutLogCriteria() {
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

        public Criteria andLogidIsNull() {
            addCriterion("logid is null");
            return (Criteria) this;
        }

        public Criteria andLogidIsNotNull() {
            addCriterion("logid is not null");
            return (Criteria) this;
        }

        public Criteria andLogidEqualTo(Long value) {
            addCriterion("logid =", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidNotEqualTo(Long value) {
            addCriterion("logid <>", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidGreaterThan(Long value) {
            addCriterion("logid >", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidGreaterThanOrEqualTo(Long value) {
            addCriterion("logid >=", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidLessThan(Long value) {
            addCriterion("logid <", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidLessThanOrEqualTo(Long value) {
            addCriterion("logid <=", value, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidIn(List<Long> values) {
            addCriterion("logid in", values, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidNotIn(List<Long> values) {
            addCriterion("logid not in", values, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidBetween(Long value1, Long value2) {
            addCriterion("logid between", value1, value2, "logid");
            return (Criteria) this;
        }

        public Criteria andLogidNotBetween(Long value1, Long value2) {
            addCriterion("logid not between", value1, value2, "logid");
            return (Criteria) this;
        }

        public Criteria andSourcetypeIsNull() {
            addCriterion("SourceType is null");
            return (Criteria) this;
        }

        public Criteria andSourcetypeIsNotNull() {
            addCriterion("SourceType is not null");
            return (Criteria) this;
        }

        public Criteria andSourcetypeEqualTo(String value) {
            addCriterion("SourceType =", value, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andSourcetypeNotEqualTo(String value) {
            addCriterion("SourceType <>", value, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andSourcetypeGreaterThan(String value) {
            addCriterion("SourceType >", value, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andSourcetypeGreaterThanOrEqualTo(String value) {
            addCriterion("SourceType >=", value, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andSourcetypeLessThan(String value) {
            addCriterion("SourceType <", value, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andSourcetypeLessThanOrEqualTo(String value) {
            addCriterion("SourceType <=", value, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andSourcetypeLike(String value) {
            addCriterion("SourceType like", value, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andSourcetypeNotLike(String value) {
            addCriterion("SourceType not like", value, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andSourcetypeIn(List<String> values) {
            addCriterion("SourceType in", values, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andSourcetypeNotIn(List<String> values) {
            addCriterion("SourceType not in", values, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andSourcetypeBetween(String value1, String value2) {
            addCriterion("SourceType between", value1, value2, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andSourcetypeNotBetween(String value1, String value2) {
            addCriterion("SourceType not between", value1, value2, "sourcetype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeIsNull() {
            addCriterion("ownertype is null");
            return (Criteria) this;
        }

        public Criteria andOwnertypeIsNotNull() {
            addCriterion("ownertype is not null");
            return (Criteria) this;
        }

        public Criteria andOwnertypeEqualTo(String value) {
            addCriterion("ownertype =", value, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeNotEqualTo(String value) {
            addCriterion("ownertype <>", value, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeGreaterThan(String value) {
            addCriterion("ownertype >", value, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeGreaterThanOrEqualTo(String value) {
            addCriterion("ownertype >=", value, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeLessThan(String value) {
            addCriterion("ownertype <", value, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeLessThanOrEqualTo(String value) {
            addCriterion("ownertype <=", value, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeLike(String value) {
            addCriterion("ownertype like", value, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeNotLike(String value) {
            addCriterion("ownertype not like", value, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeIn(List<String> values) {
            addCriterion("ownertype in", values, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeNotIn(List<String> values) {
            addCriterion("ownertype not in", values, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeBetween(String value1, String value2) {
            addCriterion("ownertype between", value1, value2, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnertypeNotBetween(String value1, String value2) {
            addCriterion("ownertype not between", value1, value2, "ownertype");
            return (Criteria) this;
        }

        public Criteria andOwnerIdIsNull() {
            addCriterion("owner_id is null");
            return (Criteria) this;
        }

        public Criteria andOwnerIdIsNotNull() {
            addCriterion("owner_id is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerIdEqualTo(String value) {
            addCriterion("owner_id =", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdNotEqualTo(String value) {
            addCriterion("owner_id <>", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdGreaterThan(String value) {
            addCriterion("owner_id >", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdGreaterThanOrEqualTo(String value) {
            addCriterion("owner_id >=", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdLessThan(String value) {
            addCriterion("owner_id <", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdLessThanOrEqualTo(String value) {
            addCriterion("owner_id <=", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdLike(String value) {
            addCriterion("owner_id like", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdNotLike(String value) {
            addCriterion("owner_id not like", value, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdIn(List<String> values) {
            addCriterion("owner_id in", values, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdNotIn(List<String> values) {
            addCriterion("owner_id not in", values, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdBetween(String value1, String value2) {
            addCriterion("owner_id between", value1, value2, "ownerId");
            return (Criteria) this;
        }

        public Criteria andOwnerIdNotBetween(String value1, String value2) {
            addCriterion("owner_id not between", value1, value2, "ownerId");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeIsNull() {
            addCriterion("business_code is null");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeIsNotNull() {
            addCriterion("business_code is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeEqualTo(String value) {
            addCriterion("business_code =", value, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeNotEqualTo(String value) {
            addCriterion("business_code <>", value, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeGreaterThan(String value) {
            addCriterion("business_code >", value, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeGreaterThanOrEqualTo(String value) {
            addCriterion("business_code >=", value, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeLessThan(String value) {
            addCriterion("business_code <", value, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeLessThanOrEqualTo(String value) {
            addCriterion("business_code <=", value, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeLike(String value) {
            addCriterion("business_code like", value, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeNotLike(String value) {
            addCriterion("business_code not like", value, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeIn(List<String> values) {
            addCriterion("business_code in", values, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeNotIn(List<String> values) {
            addCriterion("business_code not in", values, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeBetween(String value1, String value2) {
            addCriterion("business_code between", value1, value2, "businessCode");
            return (Criteria) this;
        }

        public Criteria andBusinessCodeNotBetween(String value1, String value2) {
            addCriterion("business_code not between", value1, value2, "businessCode");
            return (Criteria) this;
        }

        public Criteria andScoutTypeIsNull() {
            addCriterion("scout_type is null");
            return (Criteria) this;
        }

        public Criteria andScoutTypeIsNotNull() {
            addCriterion("scout_type is not null");
            return (Criteria) this;
        }

        public Criteria andScoutTypeEqualTo(String value) {
            addCriterion("scout_type =", value, "scoutType");
            return (Criteria) this;
        }

        public Criteria andScoutTypeNotEqualTo(String value) {
            addCriterion("scout_type <>", value, "scoutType");
            return (Criteria) this;
        }

        public Criteria andScoutTypeGreaterThan(String value) {
            addCriterion("scout_type >", value, "scoutType");
            return (Criteria) this;
        }

        public Criteria andScoutTypeGreaterThanOrEqualTo(String value) {
            addCriterion("scout_type >=", value, "scoutType");
            return (Criteria) this;
        }

        public Criteria andScoutTypeLessThan(String value) {
            addCriterion("scout_type <", value, "scoutType");
            return (Criteria) this;
        }

        public Criteria andScoutTypeLessThanOrEqualTo(String value) {
            addCriterion("scout_type <=", value, "scoutType");
            return (Criteria) this;
        }

        public Criteria andScoutTypeLike(String value) {
            addCriterion("scout_type like", value, "scoutType");
            return (Criteria) this;
        }

        public Criteria andScoutTypeNotLike(String value) {
            addCriterion("scout_type not like", value, "scoutType");
            return (Criteria) this;
        }

        public Criteria andScoutTypeIn(List<String> values) {
            addCriterion("scout_type in", values, "scoutType");
            return (Criteria) this;
        }

        public Criteria andScoutTypeNotIn(List<String> values) {
            addCriterion("scout_type not in", values, "scoutType");
            return (Criteria) this;
        }

        public Criteria andScoutTypeBetween(String value1, String value2) {
            addCriterion("scout_type between", value1, value2, "scoutType");
            return (Criteria) this;
        }

        public Criteria andScoutTypeNotBetween(String value1, String value2) {
            addCriterion("scout_type not between", value1, value2, "scoutType");
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

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andInsettimeIsNull() {
            addCriterion("insettime is null");
            return (Criteria) this;
        }

        public Criteria andInsettimeIsNotNull() {
            addCriterion("insettime is not null");
            return (Criteria) this;
        }

        public Criteria andInsettimeEqualTo(Timestamp value) {
            addCriterion("insettime =", value, "insettime");
            return (Criteria) this;
        }

        public Criteria andInsettimeNotEqualTo(Timestamp value) {
            addCriterion("insettime <>", value, "insettime");
            return (Criteria) this;
        }

        public Criteria andInsettimeGreaterThan(Timestamp value) {
            addCriterion("insettime >", value, "insettime");
            return (Criteria) this;
        }

        public Criteria andInsettimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("insettime >=", value, "insettime");
            return (Criteria) this;
        }

        public Criteria andInsettimeLessThan(Timestamp value) {
            addCriterion("insettime <", value, "insettime");
            return (Criteria) this;
        }

        public Criteria andInsettimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("insettime <=", value, "insettime");
            return (Criteria) this;
        }

        public Criteria andInsettimeIn(List<Timestamp> values) {
            addCriterion("insettime in", values, "insettime");
            return (Criteria) this;
        }

        public Criteria andInsettimeNotIn(List<Timestamp> values) {
            addCriterion("insettime not in", values, "insettime");
            return (Criteria) this;
        }

        public Criteria andInsettimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("insettime between", value1, value2, "insettime");
            return (Criteria) this;
        }

        public Criteria andInsettimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("insettime not between", value1, value2, "insettime");
            return (Criteria) this;
        }

        public Criteria andScoutRuleIsNull() {
            addCriterion("scout_rule is null");
            return (Criteria) this;
        }

        public Criteria andScoutRuleIsNotNull() {
            addCriterion("scout_rule is not null");
            return (Criteria) this;
        }

        public Criteria andScoutRuleEqualTo(String value) {
            addCriterion("scout_rule =", value, "scoutRule");
            return (Criteria) this;
        }

        public Criteria andScoutRuleNotEqualTo(String value) {
            addCriterion("scout_rule <>", value, "scoutRule");
            return (Criteria) this;
        }

        public Criteria andScoutRuleGreaterThan(String value) {
            addCriterion("scout_rule >", value, "scoutRule");
            return (Criteria) this;
        }

        public Criteria andScoutRuleGreaterThanOrEqualTo(String value) {
            addCriterion("scout_rule >=", value, "scoutRule");
            return (Criteria) this;
        }

        public Criteria andScoutRuleLessThan(String value) {
            addCriterion("scout_rule <", value, "scoutRule");
            return (Criteria) this;
        }

        public Criteria andScoutRuleLessThanOrEqualTo(String value) {
            addCriterion("scout_rule <=", value, "scoutRule");
            return (Criteria) this;
        }

        public Criteria andScoutRuleLike(String value) {
            addCriterion("scout_rule like", value, "scoutRule");
            return (Criteria) this;
        }

        public Criteria andScoutRuleNotLike(String value) {
            addCriterion("scout_rule not like", value, "scoutRule");
            return (Criteria) this;
        }

        public Criteria andScoutRuleIn(List<String> values) {
            addCriterion("scout_rule in", values, "scoutRule");
            return (Criteria) this;
        }

        public Criteria andScoutRuleNotIn(List<String> values) {
            addCriterion("scout_rule not in", values, "scoutRule");
            return (Criteria) this;
        }

        public Criteria andScoutRuleBetween(String value1, String value2) {
            addCriterion("scout_rule between", value1, value2, "scoutRule");
            return (Criteria) this;
        }

        public Criteria andScoutRuleNotBetween(String value1, String value2) {
            addCriterion("scout_rule not between", value1, value2, "scoutRule");
            return (Criteria) this;
        }

        public Criteria andBalanceinfoIsNull() {
            addCriterion("balanceinfo is null");
            return (Criteria) this;
        }

        public Criteria andBalanceinfoIsNotNull() {
            addCriterion("balanceinfo is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceinfoEqualTo(String value) {
            addCriterion("balanceinfo =", value, "balanceinfo");
            return (Criteria) this;
        }

        public Criteria andBalanceinfoNotEqualTo(String value) {
            addCriterion("balanceinfo <>", value, "balanceinfo");
            return (Criteria) this;
        }

        public Criteria andBalanceinfoGreaterThan(String value) {
            addCriterion("balanceinfo >", value, "balanceinfo");
            return (Criteria) this;
        }

        public Criteria andBalanceinfoGreaterThanOrEqualTo(String value) {
            addCriterion("balanceinfo >=", value, "balanceinfo");
            return (Criteria) this;
        }

        public Criteria andBalanceinfoLessThan(String value) {
            addCriterion("balanceinfo <", value, "balanceinfo");
            return (Criteria) this;
        }

        public Criteria andBalanceinfoLessThanOrEqualTo(String value) {
            addCriterion("balanceinfo <=", value, "balanceinfo");
            return (Criteria) this;
        }

        public Criteria andBalanceinfoLike(String value) {
            addCriterion("balanceinfo like", value, "balanceinfo");
            return (Criteria) this;
        }

        public Criteria andBalanceinfoNotLike(String value) {
            addCriterion("balanceinfo not like", value, "balanceinfo");
            return (Criteria) this;
        }

        public Criteria andBalanceinfoIn(List<String> values) {
            addCriterion("balanceinfo in", values, "balanceinfo");
            return (Criteria) this;
        }

        public Criteria andBalanceinfoNotIn(List<String> values) {
            addCriterion("balanceinfo not in", values, "balanceinfo");
            return (Criteria) this;
        }

        public Criteria andBalanceinfoBetween(String value1, String value2) {
            addCriterion("balanceinfo between", value1, value2, "balanceinfo");
            return (Criteria) this;
        }

        public Criteria andBalanceinfoNotBetween(String value1, String value2) {
            addCriterion("balanceinfo not between", value1, value2, "balanceinfo");
            return (Criteria) this;
        }

        public Criteria andParainfoIsNull() {
            addCriterion("parainfo is null");
            return (Criteria) this;
        }

        public Criteria andParainfoIsNotNull() {
            addCriterion("parainfo is not null");
            return (Criteria) this;
        }

        public Criteria andParainfoEqualTo(String value) {
            addCriterion("parainfo =", value, "parainfo");
            return (Criteria) this;
        }

        public Criteria andParainfoNotEqualTo(String value) {
            addCriterion("parainfo <>", value, "parainfo");
            return (Criteria) this;
        }

        public Criteria andParainfoGreaterThan(String value) {
            addCriterion("parainfo >", value, "parainfo");
            return (Criteria) this;
        }

        public Criteria andParainfoGreaterThanOrEqualTo(String value) {
            addCriterion("parainfo >=", value, "parainfo");
            return (Criteria) this;
        }

        public Criteria andParainfoLessThan(String value) {
            addCriterion("parainfo <", value, "parainfo");
            return (Criteria) this;
        }

        public Criteria andParainfoLessThanOrEqualTo(String value) {
            addCriterion("parainfo <=", value, "parainfo");
            return (Criteria) this;
        }

        public Criteria andParainfoLike(String value) {
            addCriterion("parainfo like", value, "parainfo");
            return (Criteria) this;
        }

        public Criteria andParainfoNotLike(String value) {
            addCriterion("parainfo not like", value, "parainfo");
            return (Criteria) this;
        }

        public Criteria andParainfoIn(List<String> values) {
            addCriterion("parainfo in", values, "parainfo");
            return (Criteria) this;
        }

        public Criteria andParainfoNotIn(List<String> values) {
            addCriterion("parainfo not in", values, "parainfo");
            return (Criteria) this;
        }

        public Criteria andParainfoBetween(String value1, String value2) {
            addCriterion("parainfo between", value1, value2, "parainfo");
            return (Criteria) this;
        }

        public Criteria andParainfoNotBetween(String value1, String value2) {
            addCriterion("parainfo not between", value1, value2, "parainfo");
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

        public Criteria andSystemIdIsNull() {
            addCriterion("system_id is null");
            return (Criteria) this;
        }

        public Criteria andSystemIdIsNotNull() {
            addCriterion("system_id is not null");
            return (Criteria) this;
        }

        public Criteria andSystemIdEqualTo(String value) {
            addCriterion("system_id =", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotEqualTo(String value) {
            addCriterion("system_id <>", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdGreaterThan(String value) {
            addCriterion("system_id >", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdGreaterThanOrEqualTo(String value) {
            addCriterion("system_id >=", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLessThan(String value) {
            addCriterion("system_id <", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLessThanOrEqualTo(String value) {
            addCriterion("system_id <=", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdLike(String value) {
            addCriterion("system_id like", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotLike(String value) {
            addCriterion("system_id not like", value, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdIn(List<String> values) {
            addCriterion("system_id in", values, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotIn(List<String> values) {
            addCriterion("system_id not in", values, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdBetween(String value1, String value2) {
            addCriterion("system_id between", value1, value2, "systemId");
            return (Criteria) this;
        }

        public Criteria andSystemIdNotBetween(String value1, String value2) {
            addCriterion("system_id not between", value1, value2, "systemId");
            return (Criteria) this;
        }

        public Criteria andBakTimeIsNull() {
            addCriterion("bak_time is null");
            return (Criteria) this;
        }

        public Criteria andBakTimeIsNotNull() {
            addCriterion("bak_time is not null");
            return (Criteria) this;
        }

        public Criteria andBakTimeEqualTo(Timestamp value) {
            addCriterion("bak_time =", value, "bakTime");
            return (Criteria) this;
        }

        public Criteria andBakTimeNotEqualTo(Timestamp value) {
            addCriterion("bak_time <>", value, "bakTime");
            return (Criteria) this;
        }

        public Criteria andBakTimeGreaterThan(Timestamp value) {
            addCriterion("bak_time >", value, "bakTime");
            return (Criteria) this;
        }

        public Criteria andBakTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("bak_time >=", value, "bakTime");
            return (Criteria) this;
        }

        public Criteria andBakTimeLessThan(Timestamp value) {
            addCriterion("bak_time <", value, "bakTime");
            return (Criteria) this;
        }

        public Criteria andBakTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("bak_time <=", value, "bakTime");
            return (Criteria) this;
        }

        public Criteria andBakTimeIn(List<Timestamp> values) {
            addCriterion("bak_time in", values, "bakTime");
            return (Criteria) this;
        }

        public Criteria andBakTimeNotIn(List<Timestamp> values) {
            addCriterion("bak_time not in", values, "bakTime");
            return (Criteria) this;
        }

        public Criteria andBakTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("bak_time between", value1, value2, "bakTime");
            return (Criteria) this;
        }

        public Criteria andBakTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("bak_time not between", value1, value2, "bakTime");
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