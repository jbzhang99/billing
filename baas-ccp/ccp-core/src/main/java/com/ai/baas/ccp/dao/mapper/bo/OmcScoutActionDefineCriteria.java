package com.ai.baas.ccp.dao.mapper.bo;

import java.util.ArrayList;
import java.util.List;

public class OmcScoutActionDefineCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public OmcScoutActionDefineCriteria() {
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

        public Criteria andInfCommondIsNull() {
            addCriterion("inf_commond is null");
            return (Criteria) this;
        }

        public Criteria andInfCommondIsNotNull() {
            addCriterion("inf_commond is not null");
            return (Criteria) this;
        }

        public Criteria andInfCommondEqualTo(String value) {
            addCriterion("inf_commond =", value, "infCommond");
            return (Criteria) this;
        }

        public Criteria andInfCommondNotEqualTo(String value) {
            addCriterion("inf_commond <>", value, "infCommond");
            return (Criteria) this;
        }

        public Criteria andInfCommondGreaterThan(String value) {
            addCriterion("inf_commond >", value, "infCommond");
            return (Criteria) this;
        }

        public Criteria andInfCommondGreaterThanOrEqualTo(String value) {
            addCriterion("inf_commond >=", value, "infCommond");
            return (Criteria) this;
        }

        public Criteria andInfCommondLessThan(String value) {
            addCriterion("inf_commond <", value, "infCommond");
            return (Criteria) this;
        }

        public Criteria andInfCommondLessThanOrEqualTo(String value) {
            addCriterion("inf_commond <=", value, "infCommond");
            return (Criteria) this;
        }

        public Criteria andInfCommondLike(String value) {
            addCriterion("inf_commond like", value, "infCommond");
            return (Criteria) this;
        }

        public Criteria andInfCommondNotLike(String value) {
            addCriterion("inf_commond not like", value, "infCommond");
            return (Criteria) this;
        }

        public Criteria andInfCommondIn(List<String> values) {
            addCriterion("inf_commond in", values, "infCommond");
            return (Criteria) this;
        }

        public Criteria andInfCommondNotIn(List<String> values) {
            addCriterion("inf_commond not in", values, "infCommond");
            return (Criteria) this;
        }

        public Criteria andInfCommondBetween(String value1, String value2) {
            addCriterion("inf_commond between", value1, value2, "infCommond");
            return (Criteria) this;
        }

        public Criteria andInfCommondNotBetween(String value1, String value2) {
            addCriterion("inf_commond not between", value1, value2, "infCommond");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateIsNull() {
            addCriterion("sms_template is null");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateIsNotNull() {
            addCriterion("sms_template is not null");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateEqualTo(Integer value) {
            addCriterion("sms_template =", value, "smsTemplate");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateNotEqualTo(Integer value) {
            addCriterion("sms_template <>", value, "smsTemplate");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateGreaterThan(Integer value) {
            addCriterion("sms_template >", value, "smsTemplate");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateGreaterThanOrEqualTo(Integer value) {
            addCriterion("sms_template >=", value, "smsTemplate");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateLessThan(Integer value) {
            addCriterion("sms_template <", value, "smsTemplate");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateLessThanOrEqualTo(Integer value) {
            addCriterion("sms_template <=", value, "smsTemplate");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateIn(List<Integer> values) {
            addCriterion("sms_template in", values, "smsTemplate");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateNotIn(List<Integer> values) {
            addCriterion("sms_template not in", values, "smsTemplate");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateBetween(Integer value1, Integer value2) {
            addCriterion("sms_template between", value1, value2, "smsTemplate");
            return (Criteria) this;
        }

        public Criteria andSmsTemplateNotBetween(Integer value1, Integer value2) {
            addCriterion("sms_template not between", value1, value2, "smsTemplate");
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