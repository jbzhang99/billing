package com.ai.baas.omc.dao.mapper.bo;

import java.util.ArrayList;
import java.util.List;

public class OmcScoutRuleCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public OmcScoutRuleCriteria() {
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

        public Criteria andRuleIdIsNull() {
            addCriterion("rule_id is null");
            return (Criteria) this;
        }

        public Criteria andRuleIdIsNotNull() {
            addCriterion("rule_id is not null");
            return (Criteria) this;
        }

        public Criteria andRuleIdEqualTo(Long value) {
            addCriterion("rule_id =", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdNotEqualTo(Long value) {
            addCriterion("rule_id <>", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdGreaterThan(Long value) {
            addCriterion("rule_id >", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("rule_id >=", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdLessThan(Long value) {
            addCriterion("rule_id <", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdLessThanOrEqualTo(Long value) {
            addCriterion("rule_id <=", value, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdIn(List<Long> values) {
            addCriterion("rule_id in", values, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdNotIn(List<Long> values) {
            addCriterion("rule_id not in", values, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdBetween(Long value1, Long value2) {
            addCriterion("rule_id between", value1, value2, "ruleId");
            return (Criteria) this;
        }

        public Criteria andRuleIdNotBetween(Long value1, Long value2) {
            addCriterion("rule_id not between", value1, value2, "ruleId");
            return (Criteria) this;
        }

        public Criteria andPolicyidIsNull() {
            addCriterion("policyId is null");
            return (Criteria) this;
        }

        public Criteria andPolicyidIsNotNull() {
            addCriterion("policyId is not null");
            return (Criteria) this;
        }

        public Criteria andPolicyidEqualTo(Long value) {
            addCriterion("policyId =", value, "policyid");
            return (Criteria) this;
        }

        public Criteria andPolicyidNotEqualTo(Long value) {
            addCriterion("policyId <>", value, "policyid");
            return (Criteria) this;
        }

        public Criteria andPolicyidGreaterThan(Long value) {
            addCriterion("policyId >", value, "policyid");
            return (Criteria) this;
        }

        public Criteria andPolicyidGreaterThanOrEqualTo(Long value) {
            addCriterion("policyId >=", value, "policyid");
            return (Criteria) this;
        }

        public Criteria andPolicyidLessThan(Long value) {
            addCriterion("policyId <", value, "policyid");
            return (Criteria) this;
        }

        public Criteria andPolicyidLessThanOrEqualTo(Long value) {
            addCriterion("policyId <=", value, "policyid");
            return (Criteria) this;
        }

        public Criteria andPolicyidIn(List<Long> values) {
            addCriterion("policyId in", values, "policyid");
            return (Criteria) this;
        }

        public Criteria andPolicyidNotIn(List<Long> values) {
            addCriterion("policyId not in", values, "policyid");
            return (Criteria) this;
        }

        public Criteria andPolicyidBetween(Long value1, Long value2) {
            addCriterion("policyId between", value1, value2, "policyid");
            return (Criteria) this;
        }

        public Criteria andPolicyidNotBetween(Long value1, Long value2) {
            addCriterion("policyId not between", value1, value2, "policyid");
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

        public Criteria andBalanceFloorIsNull() {
            addCriterion("balance_floor is null");
            return (Criteria) this;
        }

        public Criteria andBalanceFloorIsNotNull() {
            addCriterion("balance_floor is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceFloorEqualTo(Long value) {
            addCriterion("balance_floor =", value, "balanceFloor");
            return (Criteria) this;
        }

        public Criteria andBalanceFloorNotEqualTo(Long value) {
            addCriterion("balance_floor <>", value, "balanceFloor");
            return (Criteria) this;
        }

        public Criteria andBalanceFloorGreaterThan(Long value) {
            addCriterion("balance_floor >", value, "balanceFloor");
            return (Criteria) this;
        }

        public Criteria andBalanceFloorGreaterThanOrEqualTo(Long value) {
            addCriterion("balance_floor >=", value, "balanceFloor");
            return (Criteria) this;
        }

        public Criteria andBalanceFloorLessThan(Long value) {
            addCriterion("balance_floor <", value, "balanceFloor");
            return (Criteria) this;
        }

        public Criteria andBalanceFloorLessThanOrEqualTo(Long value) {
            addCriterion("balance_floor <=", value, "balanceFloor");
            return (Criteria) this;
        }

        public Criteria andBalanceFloorIn(List<Long> values) {
            addCriterion("balance_floor in", values, "balanceFloor");
            return (Criteria) this;
        }

        public Criteria andBalanceFloorNotIn(List<Long> values) {
            addCriterion("balance_floor not in", values, "balanceFloor");
            return (Criteria) this;
        }

        public Criteria andBalanceFloorBetween(Long value1, Long value2) {
            addCriterion("balance_floor between", value1, value2, "balanceFloor");
            return (Criteria) this;
        }

        public Criteria andBalanceFloorNotBetween(Long value1, Long value2) {
            addCriterion("balance_floor not between", value1, value2, "balanceFloor");
            return (Criteria) this;
        }

        public Criteria andBalanceCeilIsNull() {
            addCriterion("balance_ceil is null");
            return (Criteria) this;
        }

        public Criteria andBalanceCeilIsNotNull() {
            addCriterion("balance_ceil is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceCeilEqualTo(Long value) {
            addCriterion("balance_ceil =", value, "balanceCeil");
            return (Criteria) this;
        }

        public Criteria andBalanceCeilNotEqualTo(Long value) {
            addCriterion("balance_ceil <>", value, "balanceCeil");
            return (Criteria) this;
        }

        public Criteria andBalanceCeilGreaterThan(Long value) {
            addCriterion("balance_ceil >", value, "balanceCeil");
            return (Criteria) this;
        }

        public Criteria andBalanceCeilGreaterThanOrEqualTo(Long value) {
            addCriterion("balance_ceil >=", value, "balanceCeil");
            return (Criteria) this;
        }

        public Criteria andBalanceCeilLessThan(Long value) {
            addCriterion("balance_ceil <", value, "balanceCeil");
            return (Criteria) this;
        }

        public Criteria andBalanceCeilLessThanOrEqualTo(Long value) {
            addCriterion("balance_ceil <=", value, "balanceCeil");
            return (Criteria) this;
        }

        public Criteria andBalanceCeilIn(List<Long> values) {
            addCriterion("balance_ceil in", values, "balanceCeil");
            return (Criteria) this;
        }

        public Criteria andBalanceCeilNotIn(List<Long> values) {
            addCriterion("balance_ceil not in", values, "balanceCeil");
            return (Criteria) this;
        }

        public Criteria andBalanceCeilBetween(Long value1, Long value2) {
            addCriterion("balance_ceil between", value1, value2, "balanceCeil");
            return (Criteria) this;
        }

        public Criteria andBalanceCeilNotBetween(Long value1, Long value2) {
            addCriterion("balance_ceil not between", value1, value2, "balanceCeil");
            return (Criteria) this;
        }

        public Criteria andOweMaxdaysIsNull() {
            addCriterion("owe_maxdays is null");
            return (Criteria) this;
        }

        public Criteria andOweMaxdaysIsNotNull() {
            addCriterion("owe_maxdays is not null");
            return (Criteria) this;
        }

        public Criteria andOweMaxdaysEqualTo(Integer value) {
            addCriterion("owe_maxdays =", value, "oweMaxdays");
            return (Criteria) this;
        }

        public Criteria andOweMaxdaysNotEqualTo(Integer value) {
            addCriterion("owe_maxdays <>", value, "oweMaxdays");
            return (Criteria) this;
        }

        public Criteria andOweMaxdaysGreaterThan(Integer value) {
            addCriterion("owe_maxdays >", value, "oweMaxdays");
            return (Criteria) this;
        }

        public Criteria andOweMaxdaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("owe_maxdays >=", value, "oweMaxdays");
            return (Criteria) this;
        }

        public Criteria andOweMaxdaysLessThan(Integer value) {
            addCriterion("owe_maxdays <", value, "oweMaxdays");
            return (Criteria) this;
        }

        public Criteria andOweMaxdaysLessThanOrEqualTo(Integer value) {
            addCriterion("owe_maxdays <=", value, "oweMaxdays");
            return (Criteria) this;
        }

        public Criteria andOweMaxdaysIn(List<Integer> values) {
            addCriterion("owe_maxdays in", values, "oweMaxdays");
            return (Criteria) this;
        }

        public Criteria andOweMaxdaysNotIn(List<Integer> values) {
            addCriterion("owe_maxdays not in", values, "oweMaxdays");
            return (Criteria) this;
        }

        public Criteria andOweMaxdaysBetween(Integer value1, Integer value2) {
            addCriterion("owe_maxdays between", value1, value2, "oweMaxdays");
            return (Criteria) this;
        }

        public Criteria andOweMaxdaysNotBetween(Integer value1, Integer value2) {
            addCriterion("owe_maxdays not between", value1, value2, "oweMaxdays");
            return (Criteria) this;
        }

        public Criteria andOweMindaysIsNull() {
            addCriterion("owe_mindays is null");
            return (Criteria) this;
        }

        public Criteria andOweMindaysIsNotNull() {
            addCriterion("owe_mindays is not null");
            return (Criteria) this;
        }

        public Criteria andOweMindaysEqualTo(Integer value) {
            addCriterion("owe_mindays =", value, "oweMindays");
            return (Criteria) this;
        }

        public Criteria andOweMindaysNotEqualTo(Integer value) {
            addCriterion("owe_mindays <>", value, "oweMindays");
            return (Criteria) this;
        }

        public Criteria andOweMindaysGreaterThan(Integer value) {
            addCriterion("owe_mindays >", value, "oweMindays");
            return (Criteria) this;
        }

        public Criteria andOweMindaysGreaterThanOrEqualTo(Integer value) {
            addCriterion("owe_mindays >=", value, "oweMindays");
            return (Criteria) this;
        }

        public Criteria andOweMindaysLessThan(Integer value) {
            addCriterion("owe_mindays <", value, "oweMindays");
            return (Criteria) this;
        }

        public Criteria andOweMindaysLessThanOrEqualTo(Integer value) {
            addCriterion("owe_mindays <=", value, "oweMindays");
            return (Criteria) this;
        }

        public Criteria andOweMindaysIn(List<Integer> values) {
            addCriterion("owe_mindays in", values, "oweMindays");
            return (Criteria) this;
        }

        public Criteria andOweMindaysNotIn(List<Integer> values) {
            addCriterion("owe_mindays not in", values, "oweMindays");
            return (Criteria) this;
        }

        public Criteria andOweMindaysBetween(Integer value1, Integer value2) {
            addCriterion("owe_mindays between", value1, value2, "oweMindays");
            return (Criteria) this;
        }

        public Criteria andOweMindaysNotBetween(Integer value1, Integer value2) {
            addCriterion("owe_mindays not between", value1, value2, "oweMindays");
            return (Criteria) this;
        }

        public Criteria andChargeCeilIsNull() {
            addCriterion("charge_ceil is null");
            return (Criteria) this;
        }

        public Criteria andChargeCeilIsNotNull() {
            addCriterion("charge_ceil is not null");
            return (Criteria) this;
        }

        public Criteria andChargeCeilEqualTo(Long value) {
            addCriterion("charge_ceil =", value, "chargeCeil");
            return (Criteria) this;
        }

        public Criteria andChargeCeilNotEqualTo(Long value) {
            addCriterion("charge_ceil <>", value, "chargeCeil");
            return (Criteria) this;
        }

        public Criteria andChargeCeilGreaterThan(Long value) {
            addCriterion("charge_ceil >", value, "chargeCeil");
            return (Criteria) this;
        }

        public Criteria andChargeCeilGreaterThanOrEqualTo(Long value) {
            addCriterion("charge_ceil >=", value, "chargeCeil");
            return (Criteria) this;
        }

        public Criteria andChargeCeilLessThan(Long value) {
            addCriterion("charge_ceil <", value, "chargeCeil");
            return (Criteria) this;
        }

        public Criteria andChargeCeilLessThanOrEqualTo(Long value) {
            addCriterion("charge_ceil <=", value, "chargeCeil");
            return (Criteria) this;
        }

        public Criteria andChargeCeilIn(List<Long> values) {
            addCriterion("charge_ceil in", values, "chargeCeil");
            return (Criteria) this;
        }

        public Criteria andChargeCeilNotIn(List<Long> values) {
            addCriterion("charge_ceil not in", values, "chargeCeil");
            return (Criteria) this;
        }

        public Criteria andChargeCeilBetween(Long value1, Long value2) {
            addCriterion("charge_ceil between", value1, value2, "chargeCeil");
            return (Criteria) this;
        }

        public Criteria andChargeCeilNotBetween(Long value1, Long value2) {
            addCriterion("charge_ceil not between", value1, value2, "chargeCeil");
            return (Criteria) this;
        }

        public Criteria andChargeFloorIsNull() {
            addCriterion("charge_floor is null");
            return (Criteria) this;
        }

        public Criteria andChargeFloorIsNotNull() {
            addCriterion("charge_floor is not null");
            return (Criteria) this;
        }

        public Criteria andChargeFloorEqualTo(Long value) {
            addCriterion("charge_floor =", value, "chargeFloor");
            return (Criteria) this;
        }

        public Criteria andChargeFloorNotEqualTo(Long value) {
            addCriterion("charge_floor <>", value, "chargeFloor");
            return (Criteria) this;
        }

        public Criteria andChargeFloorGreaterThan(Long value) {
            addCriterion("charge_floor >", value, "chargeFloor");
            return (Criteria) this;
        }

        public Criteria andChargeFloorGreaterThanOrEqualTo(Long value) {
            addCriterion("charge_floor >=", value, "chargeFloor");
            return (Criteria) this;
        }

        public Criteria andChargeFloorLessThan(Long value) {
            addCriterion("charge_floor <", value, "chargeFloor");
            return (Criteria) this;
        }

        public Criteria andChargeFloorLessThanOrEqualTo(Long value) {
            addCriterion("charge_floor <=", value, "chargeFloor");
            return (Criteria) this;
        }

        public Criteria andChargeFloorIn(List<Long> values) {
            addCriterion("charge_floor in", values, "chargeFloor");
            return (Criteria) this;
        }

        public Criteria andChargeFloorNotIn(List<Long> values) {
            addCriterion("charge_floor not in", values, "chargeFloor");
            return (Criteria) this;
        }

        public Criteria andChargeFloorBetween(Long value1, Long value2) {
            addCriterion("charge_floor between", value1, value2, "chargeFloor");
            return (Criteria) this;
        }

        public Criteria andChargeFloorNotBetween(Long value1, Long value2) {
            addCriterion("charge_floor not between", value1, value2, "chargeFloor");
            return (Criteria) this;
        }

        public Criteria andCustTypeIsNull() {
            addCriterion("cust_type is null");
            return (Criteria) this;
        }

        public Criteria andCustTypeIsNotNull() {
            addCriterion("cust_type is not null");
            return (Criteria) this;
        }

        public Criteria andCustTypeEqualTo(String value) {
            addCriterion("cust_type =", value, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeNotEqualTo(String value) {
            addCriterion("cust_type <>", value, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeGreaterThan(String value) {
            addCriterion("cust_type >", value, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeGreaterThanOrEqualTo(String value) {
            addCriterion("cust_type >=", value, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeLessThan(String value) {
            addCriterion("cust_type <", value, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeLessThanOrEqualTo(String value) {
            addCriterion("cust_type <=", value, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeLike(String value) {
            addCriterion("cust_type like", value, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeNotLike(String value) {
            addCriterion("cust_type not like", value, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeIn(List<String> values) {
            addCriterion("cust_type in", values, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeNotIn(List<String> values) {
            addCriterion("cust_type not in", values, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeBetween(String value1, String value2) {
            addCriterion("cust_type between", value1, value2, "custType");
            return (Criteria) this;
        }

        public Criteria andCustTypeNotBetween(String value1, String value2) {
            addCriterion("cust_type not between", value1, value2, "custType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeIsNull() {
            addCriterion("acct_type is null");
            return (Criteria) this;
        }

        public Criteria andAcctTypeIsNotNull() {
            addCriterion("acct_type is not null");
            return (Criteria) this;
        }

        public Criteria andAcctTypeEqualTo(String value) {
            addCriterion("acct_type =", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeNotEqualTo(String value) {
            addCriterion("acct_type <>", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeGreaterThan(String value) {
            addCriterion("acct_type >", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeGreaterThanOrEqualTo(String value) {
            addCriterion("acct_type >=", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeLessThan(String value) {
            addCriterion("acct_type <", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeLessThanOrEqualTo(String value) {
            addCriterion("acct_type <=", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeLike(String value) {
            addCriterion("acct_type like", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeNotLike(String value) {
            addCriterion("acct_type not like", value, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeIn(List<String> values) {
            addCriterion("acct_type in", values, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeNotIn(List<String> values) {
            addCriterion("acct_type not in", values, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeBetween(String value1, String value2) {
            addCriterion("acct_type between", value1, value2, "acctType");
            return (Criteria) this;
        }

        public Criteria andAcctTypeNotBetween(String value1, String value2) {
            addCriterion("acct_type not between", value1, value2, "acctType");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNull() {
            addCriterion("user_type is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNotNull() {
            addCriterion("user_type is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeEqualTo(String value) {
            addCriterion("user_type =", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotEqualTo(String value) {
            addCriterion("user_type <>", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThan(String value) {
            addCriterion("user_type >", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThanOrEqualTo(String value) {
            addCriterion("user_type >=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThan(String value) {
            addCriterion("user_type <", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThanOrEqualTo(String value) {
            addCriterion("user_type <=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLike(String value) {
            addCriterion("user_type like", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotLike(String value) {
            addCriterion("user_type not like", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeIn(List<String> values) {
            addCriterion("user_type in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotIn(List<String> values) {
            addCriterion("user_type not in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeBetween(String value1, String value2) {
            addCriterion("user_type between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotBetween(String value1, String value2) {
            addCriterion("user_type not between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andCustLevelIsNull() {
            addCriterion("cust_level is null");
            return (Criteria) this;
        }

        public Criteria andCustLevelIsNotNull() {
            addCriterion("cust_level is not null");
            return (Criteria) this;
        }

        public Criteria andCustLevelEqualTo(String value) {
            addCriterion("cust_level =", value, "custLevel");
            return (Criteria) this;
        }

        public Criteria andCustLevelNotEqualTo(String value) {
            addCriterion("cust_level <>", value, "custLevel");
            return (Criteria) this;
        }

        public Criteria andCustLevelGreaterThan(String value) {
            addCriterion("cust_level >", value, "custLevel");
            return (Criteria) this;
        }

        public Criteria andCustLevelGreaterThanOrEqualTo(String value) {
            addCriterion("cust_level >=", value, "custLevel");
            return (Criteria) this;
        }

        public Criteria andCustLevelLessThan(String value) {
            addCriterion("cust_level <", value, "custLevel");
            return (Criteria) this;
        }

        public Criteria andCustLevelLessThanOrEqualTo(String value) {
            addCriterion("cust_level <=", value, "custLevel");
            return (Criteria) this;
        }

        public Criteria andCustLevelLike(String value) {
            addCriterion("cust_level like", value, "custLevel");
            return (Criteria) this;
        }

        public Criteria andCustLevelNotLike(String value) {
            addCriterion("cust_level not like", value, "custLevel");
            return (Criteria) this;
        }

        public Criteria andCustLevelIn(List<String> values) {
            addCriterion("cust_level in", values, "custLevel");
            return (Criteria) this;
        }

        public Criteria andCustLevelNotIn(List<String> values) {
            addCriterion("cust_level not in", values, "custLevel");
            return (Criteria) this;
        }

        public Criteria andCustLevelBetween(String value1, String value2) {
            addCriterion("cust_level between", value1, value2, "custLevel");
            return (Criteria) this;
        }

        public Criteria andCustLevelNotBetween(String value1, String value2) {
            addCriterion("cust_level not between", value1, value2, "custLevel");
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

        public Criteria andSectionTypeIsNull() {
            addCriterion("section_type is null");
            return (Criteria) this;
        }

        public Criteria andSectionTypeIsNotNull() {
            addCriterion("section_type is not null");
            return (Criteria) this;
        }

        public Criteria andSectionTypeEqualTo(String value) {
            addCriterion("section_type =", value, "sectionType");
            return (Criteria) this;
        }

        public Criteria andSectionTypeNotEqualTo(String value) {
            addCriterion("section_type <>", value, "sectionType");
            return (Criteria) this;
        }

        public Criteria andSectionTypeGreaterThan(String value) {
            addCriterion("section_type >", value, "sectionType");
            return (Criteria) this;
        }

        public Criteria andSectionTypeGreaterThanOrEqualTo(String value) {
            addCriterion("section_type >=", value, "sectionType");
            return (Criteria) this;
        }

        public Criteria andSectionTypeLessThan(String value) {
            addCriterion("section_type <", value, "sectionType");
            return (Criteria) this;
        }

        public Criteria andSectionTypeLessThanOrEqualTo(String value) {
            addCriterion("section_type <=", value, "sectionType");
            return (Criteria) this;
        }

        public Criteria andSectionTypeLike(String value) {
            addCriterion("section_type like", value, "sectionType");
            return (Criteria) this;
        }

        public Criteria andSectionTypeNotLike(String value) {
            addCriterion("section_type not like", value, "sectionType");
            return (Criteria) this;
        }

        public Criteria andSectionTypeIn(List<String> values) {
            addCriterion("section_type in", values, "sectionType");
            return (Criteria) this;
        }

        public Criteria andSectionTypeNotIn(List<String> values) {
            addCriterion("section_type not in", values, "sectionType");
            return (Criteria) this;
        }

        public Criteria andSectionTypeBetween(String value1, String value2) {
            addCriterion("section_type between", value1, value2, "sectionType");
            return (Criteria) this;
        }

        public Criteria andSectionTypeNotBetween(String value1, String value2) {
            addCriterion("section_type not between", value1, value2, "sectionType");
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