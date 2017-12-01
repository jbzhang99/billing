package com.ai.opt.sys.dao.mapper.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class GnFuncCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public GnFuncCriteria() {
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

        public Criteria andFuncIdIsNull() {
            addCriterion("func_id is null");
            return (Criteria) this;
        }

        public Criteria andFuncIdIsNotNull() {
            addCriterion("func_id is not null");
            return (Criteria) this;
        }

        public Criteria andFuncIdEqualTo(long value) {
            addCriterion("func_id =", value, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdNotEqualTo(long value) {
            addCriterion("func_id <>", value, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdGreaterThan(long value) {
            addCriterion("func_id >", value, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdGreaterThanOrEqualTo(long value) {
            addCriterion("func_id >=", value, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdLessThan(long value) {
            addCriterion("func_id <", value, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdLessThanOrEqualTo(long value) {
            addCriterion("func_id <=", value, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdIn(List<Long> values) {
            addCriterion("func_id in", values, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdNotIn(List<Long> values) {
            addCriterion("func_id not in", values, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdBetween(long value1, long value2) {
            addCriterion("func_id between", value1, value2, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncIdNotBetween(long value1, long value2) {
            addCriterion("func_id not between", value1, value2, "funcId");
            return (Criteria) this;
        }

        public Criteria andFuncNameIsNull() {
            addCriterion("func_name is null");
            return (Criteria) this;
        }

        public Criteria andFuncNameIsNotNull() {
            addCriterion("func_name is not null");
            return (Criteria) this;
        }

        public Criteria andFuncNameEqualTo(String value) {
            addCriterion("func_name =", value, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncNameNotEqualTo(String value) {
            addCriterion("func_name <>", value, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncNameGreaterThan(String value) {
            addCriterion("func_name >", value, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncNameGreaterThanOrEqualTo(String value) {
            addCriterion("func_name >=", value, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncNameLessThan(String value) {
            addCriterion("func_name <", value, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncNameLessThanOrEqualTo(String value) {
            addCriterion("func_name <=", value, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncNameLike(String value) {
            addCriterion("func_name like", value, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncNameNotLike(String value) {
            addCriterion("func_name not like", value, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncNameIn(List<String> values) {
            addCriterion("func_name in", values, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncNameNotIn(List<String> values) {
            addCriterion("func_name not in", values, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncNameBetween(String value1, String value2) {
            addCriterion("func_name between", value1, value2, "funcName");
            return (Criteria) this;
        }

        public Criteria andFuncNameNotBetween(String value1, String value2) {
            addCriterion("func_name not between", value1, value2, "funcName");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("state like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("state not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andFuncUrlIsNull() {
            addCriterion("func_url is null");
            return (Criteria) this;
        }

        public Criteria andFuncUrlIsNotNull() {
            addCriterion("func_url is not null");
            return (Criteria) this;
        }

        public Criteria andFuncUrlEqualTo(String value) {
            addCriterion("func_url =", value, "funcUrl");
            return (Criteria) this;
        }

        public Criteria andFuncUrlNotEqualTo(String value) {
            addCriterion("func_url <>", value, "funcUrl");
            return (Criteria) this;
        }

        public Criteria andFuncUrlGreaterThan(String value) {
            addCriterion("func_url >", value, "funcUrl");
            return (Criteria) this;
        }

        public Criteria andFuncUrlGreaterThanOrEqualTo(String value) {
            addCriterion("func_url >=", value, "funcUrl");
            return (Criteria) this;
        }

        public Criteria andFuncUrlLessThan(String value) {
            addCriterion("func_url <", value, "funcUrl");
            return (Criteria) this;
        }

        public Criteria andFuncUrlLessThanOrEqualTo(String value) {
            addCriterion("func_url <=", value, "funcUrl");
            return (Criteria) this;
        }

        public Criteria andFuncUrlLike(String value) {
            addCriterion("func_url like", value, "funcUrl");
            return (Criteria) this;
        }

        public Criteria andFuncUrlNotLike(String value) {
            addCriterion("func_url not like", value, "funcUrl");
            return (Criteria) this;
        }

        public Criteria andFuncUrlIn(List<String> values) {
            addCriterion("func_url in", values, "funcUrl");
            return (Criteria) this;
        }

        public Criteria andFuncUrlNotIn(List<String> values) {
            addCriterion("func_url not in", values, "funcUrl");
            return (Criteria) this;
        }

        public Criteria andFuncUrlBetween(String value1, String value2) {
            addCriterion("func_url between", value1, value2, "funcUrl");
            return (Criteria) this;
        }

        public Criteria andFuncUrlNotBetween(String value1, String value2) {
            addCriterion("func_url not between", value1, value2, "funcUrl");
            return (Criteria) this;
        }

        public Criteria andFuncTypeIsNull() {
            addCriterion("func_type is null");
            return (Criteria) this;
        }

        public Criteria andFuncTypeIsNotNull() {
            addCriterion("func_type is not null");
            return (Criteria) this;
        }

        public Criteria andFuncTypeEqualTo(String value) {
            addCriterion("func_type =", value, "funcType");
            return (Criteria) this;
        }

        public Criteria andFuncTypeNotEqualTo(String value) {
            addCriterion("func_type <>", value, "funcType");
            return (Criteria) this;
        }

        public Criteria andFuncTypeGreaterThan(String value) {
            addCriterion("func_type >", value, "funcType");
            return (Criteria) this;
        }

        public Criteria andFuncTypeGreaterThanOrEqualTo(String value) {
            addCriterion("func_type >=", value, "funcType");
            return (Criteria) this;
        }

        public Criteria andFuncTypeLessThan(String value) {
            addCriterion("func_type <", value, "funcType");
            return (Criteria) this;
        }

        public Criteria andFuncTypeLessThanOrEqualTo(String value) {
            addCriterion("func_type <=", value, "funcType");
            return (Criteria) this;
        }

        public Criteria andFuncTypeLike(String value) {
            addCriterion("func_type like", value, "funcType");
            return (Criteria) this;
        }

        public Criteria andFuncTypeNotLike(String value) {
            addCriterion("func_type not like", value, "funcType");
            return (Criteria) this;
        }

        public Criteria andFuncTypeIn(List<String> values) {
            addCriterion("func_type in", values, "funcType");
            return (Criteria) this;
        }

        public Criteria andFuncTypeNotIn(List<String> values) {
            addCriterion("func_type not in", values, "funcType");
            return (Criteria) this;
        }

        public Criteria andFuncTypeBetween(String value1, String value2) {
            addCriterion("func_type between", value1, value2, "funcType");
            return (Criteria) this;
        }

        public Criteria andFuncTypeNotBetween(String value1, String value2) {
            addCriterion("func_type not between", value1, value2, "funcType");
            return (Criteria) this;
        }

        public Criteria andFuncSortIsNull() {
            addCriterion("func_sort is null");
            return (Criteria) this;
        }

        public Criteria andFuncSortIsNotNull() {
            addCriterion("func_sort is not null");
            return (Criteria) this;
        }

        public Criteria andFuncSortEqualTo(long value) {
            addCriterion("func_sort =", value, "funcSort");
            return (Criteria) this;
        }

        public Criteria andFuncSortNotEqualTo(long value) {
            addCriterion("func_sort <>", value, "funcSort");
            return (Criteria) this;
        }

        public Criteria andFuncSortGreaterThan(long value) {
            addCriterion("func_sort >", value, "funcSort");
            return (Criteria) this;
        }

        public Criteria andFuncSortGreaterThanOrEqualTo(long value) {
            addCriterion("func_sort >=", value, "funcSort");
            return (Criteria) this;
        }

        public Criteria andFuncSortLessThan(long value) {
            addCriterion("func_sort <", value, "funcSort");
            return (Criteria) this;
        }

        public Criteria andFuncSortLessThanOrEqualTo(long value) {
            addCriterion("func_sort <=", value, "funcSort");
            return (Criteria) this;
        }

        public Criteria andFuncSortIn(List<Long> values) {
            addCriterion("func_sort in", values, "funcSort");
            return (Criteria) this;
        }

        public Criteria andFuncSortNotIn(List<Long> values) {
            addCriterion("func_sort not in", values, "funcSort");
            return (Criteria) this;
        }

        public Criteria andFuncSortBetween(long value1, long value2) {
            addCriterion("func_sort between", value1, value2, "funcSort");
            return (Criteria) this;
        }

        public Criteria andFuncSortNotBetween(long value1, long value2) {
            addCriterion("func_sort not between", value1, value2, "funcSort");
            return (Criteria) this;
        }

        public Criteria andParentFuncIdIsNull() {
            addCriterion("parent_func_id is null");
            return (Criteria) this;
        }

        public Criteria andParentFuncIdIsNotNull() {
            addCriterion("parent_func_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentFuncIdEqualTo(long value) {
            addCriterion("parent_func_id =", value, "parentFuncId");
            return (Criteria) this;
        }

        public Criteria andParentFuncIdNotEqualTo(long value) {
            addCriterion("parent_func_id <>", value, "parentFuncId");
            return (Criteria) this;
        }

        public Criteria andParentFuncIdGreaterThan(long value) {
            addCriterion("parent_func_id >", value, "parentFuncId");
            return (Criteria) this;
        }

        public Criteria andParentFuncIdGreaterThanOrEqualTo(long value) {
            addCriterion("parent_func_id >=", value, "parentFuncId");
            return (Criteria) this;
        }

        public Criteria andParentFuncIdLessThan(long value) {
            addCriterion("parent_func_id <", value, "parentFuncId");
            return (Criteria) this;
        }

        public Criteria andParentFuncIdLessThanOrEqualTo(long value) {
            addCriterion("parent_func_id <=", value, "parentFuncId");
            return (Criteria) this;
        }

        public Criteria andParentFuncIdIn(List<Long> values) {
            addCriterion("parent_func_id in", values, "parentFuncId");
            return (Criteria) this;
        }

        public Criteria andParentFuncIdNotIn(List<Long> values) {
            addCriterion("parent_func_id not in", values, "parentFuncId");
            return (Criteria) this;
        }

        public Criteria andParentFuncIdBetween(long value1, long value2) {
            addCriterion("parent_func_id between", value1, value2, "parentFuncId");
            return (Criteria) this;
        }

        public Criteria andParentFuncIdNotBetween(long value1, long value2) {
            addCriterion("parent_func_id not between", value1, value2, "parentFuncId");
            return (Criteria) this;
        }

        public Criteria andFuncCssClassIsNull() {
            addCriterion("func_css_class is null");
            return (Criteria) this;
        }

        public Criteria andFuncCssClassIsNotNull() {
            addCriterion("func_css_class is not null");
            return (Criteria) this;
        }

        public Criteria andFuncCssClassEqualTo(String value) {
            addCriterion("func_css_class =", value, "funcCssClass");
            return (Criteria) this;
        }

        public Criteria andFuncCssClassNotEqualTo(String value) {
            addCriterion("func_css_class <>", value, "funcCssClass");
            return (Criteria) this;
        }

        public Criteria andFuncCssClassGreaterThan(String value) {
            addCriterion("func_css_class >", value, "funcCssClass");
            return (Criteria) this;
        }

        public Criteria andFuncCssClassGreaterThanOrEqualTo(String value) {
            addCriterion("func_css_class >=", value, "funcCssClass");
            return (Criteria) this;
        }

        public Criteria andFuncCssClassLessThan(String value) {
            addCriterion("func_css_class <", value, "funcCssClass");
            return (Criteria) this;
        }

        public Criteria andFuncCssClassLessThanOrEqualTo(String value) {
            addCriterion("func_css_class <=", value, "funcCssClass");
            return (Criteria) this;
        }

        public Criteria andFuncCssClassLike(String value) {
            addCriterion("func_css_class like", value, "funcCssClass");
            return (Criteria) this;
        }

        public Criteria andFuncCssClassNotLike(String value) {
            addCriterion("func_css_class not like", value, "funcCssClass");
            return (Criteria) this;
        }

        public Criteria andFuncCssClassIn(List<String> values) {
            addCriterion("func_css_class in", values, "funcCssClass");
            return (Criteria) this;
        }

        public Criteria andFuncCssClassNotIn(List<String> values) {
            addCriterion("func_css_class not in", values, "funcCssClass");
            return (Criteria) this;
        }

        public Criteria andFuncCssClassBetween(String value1, String value2) {
            addCriterion("func_css_class between", value1, value2, "funcCssClass");
            return (Criteria) this;
        }

        public Criteria andFuncCssClassNotBetween(String value1, String value2) {
            addCriterion("func_css_class not between", value1, value2, "funcCssClass");
            return (Criteria) this;
        }

        public Criteria andFuncPicIsNull() {
            addCriterion("func_pic is null");
            return (Criteria) this;
        }

        public Criteria andFuncPicIsNotNull() {
            addCriterion("func_pic is not null");
            return (Criteria) this;
        }

        public Criteria andFuncPicEqualTo(String value) {
            addCriterion("func_pic =", value, "funcPic");
            return (Criteria) this;
        }

        public Criteria andFuncPicNotEqualTo(String value) {
            addCriterion("func_pic <>", value, "funcPic");
            return (Criteria) this;
        }

        public Criteria andFuncPicGreaterThan(String value) {
            addCriterion("func_pic >", value, "funcPic");
            return (Criteria) this;
        }

        public Criteria andFuncPicGreaterThanOrEqualTo(String value) {
            addCriterion("func_pic >=", value, "funcPic");
            return (Criteria) this;
        }

        public Criteria andFuncPicLessThan(String value) {
            addCriterion("func_pic <", value, "funcPic");
            return (Criteria) this;
        }

        public Criteria andFuncPicLessThanOrEqualTo(String value) {
            addCriterion("func_pic <=", value, "funcPic");
            return (Criteria) this;
        }

        public Criteria andFuncPicLike(String value) {
            addCriterion("func_pic like", value, "funcPic");
            return (Criteria) this;
        }

        public Criteria andFuncPicNotLike(String value) {
            addCriterion("func_pic not like", value, "funcPic");
            return (Criteria) this;
        }

        public Criteria andFuncPicIn(List<String> values) {
            addCriterion("func_pic in", values, "funcPic");
            return (Criteria) this;
        }

        public Criteria andFuncPicNotIn(List<String> values) {
            addCriterion("func_pic not in", values, "funcPic");
            return (Criteria) this;
        }

        public Criteria andFuncPicBetween(String value1, String value2) {
            addCriterion("func_pic between", value1, value2, "funcPic");
            return (Criteria) this;
        }

        public Criteria andFuncPicNotBetween(String value1, String value2) {
            addCriterion("func_pic not between", value1, value2, "funcPic");
            return (Criteria) this;
        }

        public Criteria andActiveTimeIsNull() {
            addCriterion("active_time is null");
            return (Criteria) this;
        }

        public Criteria andActiveTimeIsNotNull() {
            addCriterion("active_time is not null");
            return (Criteria) this;
        }

        public Criteria andActiveTimeEqualTo(Timestamp value) {
            addCriterion("active_time =", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeNotEqualTo(Timestamp value) {
            addCriterion("active_time <>", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeGreaterThan(Timestamp value) {
            addCriterion("active_time >", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("active_time >=", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeLessThan(Timestamp value) {
            addCriterion("active_time <", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("active_time <=", value, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeIn(List<Timestamp> values) {
            addCriterion("active_time in", values, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeNotIn(List<Timestamp> values) {
            addCriterion("active_time not in", values, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("active_time between", value1, value2, "activeTime");
            return (Criteria) this;
        }

        public Criteria andActiveTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("active_time not between", value1, value2, "activeTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeIsNull() {
            addCriterion("inactive_time is null");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeIsNotNull() {
            addCriterion("inactive_time is not null");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeEqualTo(Timestamp value) {
            addCriterion("inactive_time =", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeNotEqualTo(Timestamp value) {
            addCriterion("inactive_time <>", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeGreaterThan(Timestamp value) {
            addCriterion("inactive_time >", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("inactive_time >=", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeLessThan(Timestamp value) {
            addCriterion("inactive_time <", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("inactive_time <=", value, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeIn(List<Timestamp> values) {
            addCriterion("inactive_time in", values, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeNotIn(List<Timestamp> values) {
            addCriterion("inactive_time not in", values, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("inactive_time between", value1, value2, "inactiveTime");
            return (Criteria) this;
        }

        public Criteria andInactiveTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("inactive_time not between", value1, value2, "inactiveTime");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Timestamp value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Timestamp value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Timestamp value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Timestamp value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Timestamp value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Timestamp value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Timestamp> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Timestamp> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Timestamp value1, Timestamp value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Timestamp value1, Timestamp value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdIsNull() {
            addCriterion("create_account_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdIsNotNull() {
            addCriterion("create_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdEqualTo(long value) {
            addCriterion("create_account_id =", value, "createAccountId");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdNotEqualTo(long value) {
            addCriterion("create_account_id <>", value, "createAccountId");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdGreaterThan(long value) {
            addCriterion("create_account_id >", value, "createAccountId");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdGreaterThanOrEqualTo(long value) {
            addCriterion("create_account_id >=", value, "createAccountId");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdLessThan(long value) {
            addCriterion("create_account_id <", value, "createAccountId");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdLessThanOrEqualTo(long value) {
            addCriterion("create_account_id <=", value, "createAccountId");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdIn(List<Long> values) {
            addCriterion("create_account_id in", values, "createAccountId");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdNotIn(List<Long> values) {
            addCriterion("create_account_id not in", values, "createAccountId");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdBetween(long value1, long value2) {
            addCriterion("create_account_id between", value1, value2, "createAccountId");
            return (Criteria) this;
        }

        public Criteria andCreateAccountIdNotBetween(long value1, long value2) {
            addCriterion("create_account_id not between", value1, value2, "createAccountId");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdIsNull() {
            addCriterion("update_account_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdIsNotNull() {
            addCriterion("update_account_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdEqualTo(long value) {
            addCriterion("update_account_id =", value, "updateAccountId");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdNotEqualTo(long value) {
            addCriterion("update_account_id <>", value, "updateAccountId");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdGreaterThan(long value) {
            addCriterion("update_account_id >", value, "updateAccountId");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdGreaterThanOrEqualTo(long value) {
            addCriterion("update_account_id >=", value, "updateAccountId");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdLessThan(long value) {
            addCriterion("update_account_id <", value, "updateAccountId");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdLessThanOrEqualTo(long value) {
            addCriterion("update_account_id <=", value, "updateAccountId");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdIn(List<Long> values) {
            addCriterion("update_account_id in", values, "updateAccountId");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdNotIn(List<Long> values) {
            addCriterion("update_account_id not in", values, "updateAccountId");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdBetween(long value1, long value2) {
            addCriterion("update_account_id between", value1, value2, "updateAccountId");
            return (Criteria) this;
        }

        public Criteria andUpdateAccountIdNotBetween(long value1, long value2) {
            addCriterion("update_account_id not between", value1, value2, "updateAccountId");
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