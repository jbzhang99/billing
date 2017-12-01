package com.ai.baas.amc.dao.mapper.bo;

import java.util.ArrayList;
import java.util.List;

public class AmcInvoiceInfoCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitStart;

    protected Integer limitEnd;

    public AmcInvoiceInfoCriteria() {
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

        public Criteria andInvoiceInfoIdIsNull() {
            addCriterion("INVOICE_INFO_ID is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceInfoIdIsNotNull() {
            addCriterion("INVOICE_INFO_ID is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceInfoIdEqualTo(String value) {
            addCriterion("INVOICE_INFO_ID =", value, "invoiceInfoId");
            return (Criteria) this;
        }

        public Criteria andInvoiceInfoIdNotEqualTo(String value) {
            addCriterion("INVOICE_INFO_ID <>", value, "invoiceInfoId");
            return (Criteria) this;
        }

        public Criteria andInvoiceInfoIdGreaterThan(String value) {
            addCriterion("INVOICE_INFO_ID >", value, "invoiceInfoId");
            return (Criteria) this;
        }

        public Criteria andInvoiceInfoIdGreaterThanOrEqualTo(String value) {
            addCriterion("INVOICE_INFO_ID >=", value, "invoiceInfoId");
            return (Criteria) this;
        }

        public Criteria andInvoiceInfoIdLessThan(String value) {
            addCriterion("INVOICE_INFO_ID <", value, "invoiceInfoId");
            return (Criteria) this;
        }

        public Criteria andInvoiceInfoIdLessThanOrEqualTo(String value) {
            addCriterion("INVOICE_INFO_ID <=", value, "invoiceInfoId");
            return (Criteria) this;
        }

        public Criteria andInvoiceInfoIdLike(String value) {
            addCriterion("INVOICE_INFO_ID like", value, "invoiceInfoId");
            return (Criteria) this;
        }

        public Criteria andInvoiceInfoIdNotLike(String value) {
            addCriterion("INVOICE_INFO_ID not like", value, "invoiceInfoId");
            return (Criteria) this;
        }

        public Criteria andInvoiceInfoIdIn(List<String> values) {
            addCriterion("INVOICE_INFO_ID in", values, "invoiceInfoId");
            return (Criteria) this;
        }

        public Criteria andInvoiceInfoIdNotIn(List<String> values) {
            addCriterion("INVOICE_INFO_ID not in", values, "invoiceInfoId");
            return (Criteria) this;
        }

        public Criteria andInvoiceInfoIdBetween(String value1, String value2) {
            addCriterion("INVOICE_INFO_ID between", value1, value2, "invoiceInfoId");
            return (Criteria) this;
        }

        public Criteria andInvoiceInfoIdNotBetween(String value1, String value2) {
            addCriterion("INVOICE_INFO_ID not between", value1, value2, "invoiceInfoId");
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

        public Criteria andAcctIdIsNull() {
            addCriterion("ACCT_ID is null");
            return (Criteria) this;
        }

        public Criteria andAcctIdIsNotNull() {
            addCriterion("ACCT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andAcctIdEqualTo(String value) {
            addCriterion("ACCT_ID =", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdNotEqualTo(String value) {
            addCriterion("ACCT_ID <>", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdGreaterThan(String value) {
            addCriterion("ACCT_ID >", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdGreaterThanOrEqualTo(String value) {
            addCriterion("ACCT_ID >=", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdLessThan(String value) {
            addCriterion("ACCT_ID <", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdLessThanOrEqualTo(String value) {
            addCriterion("ACCT_ID <=", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdLike(String value) {
            addCriterion("ACCT_ID like", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdNotLike(String value) {
            addCriterion("ACCT_ID not like", value, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdIn(List<String> values) {
            addCriterion("ACCT_ID in", values, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdNotIn(List<String> values) {
            addCriterion("ACCT_ID not in", values, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdBetween(String value1, String value2) {
            addCriterion("ACCT_ID between", value1, value2, "acctId");
            return (Criteria) this;
        }

        public Criteria andAcctIdNotBetween(String value1, String value2) {
            addCriterion("ACCT_ID not between", value1, value2, "acctId");
            return (Criteria) this;
        }

        public Criteria andCustIdIsNull() {
            addCriterion("CUST_ID is null");
            return (Criteria) this;
        }

        public Criteria andCustIdIsNotNull() {
            addCriterion("CUST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCustIdEqualTo(String value) {
            addCriterion("CUST_ID =", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotEqualTo(String value) {
            addCriterion("CUST_ID <>", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdGreaterThan(String value) {
            addCriterion("CUST_ID >", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_ID >=", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLessThan(String value) {
            addCriterion("CUST_ID <", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLessThanOrEqualTo(String value) {
            addCriterion("CUST_ID <=", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdLike(String value) {
            addCriterion("CUST_ID like", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotLike(String value) {
            addCriterion("CUST_ID not like", value, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdIn(List<String> values) {
            addCriterion("CUST_ID in", values, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotIn(List<String> values) {
            addCriterion("CUST_ID not in", values, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdBetween(String value1, String value2) {
            addCriterion("CUST_ID between", value1, value2, "custId");
            return (Criteria) this;
        }

        public Criteria andCustIdNotBetween(String value1, String value2) {
            addCriterion("CUST_ID not between", value1, value2, "custId");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("TITLE is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("TITLE =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("TITLE <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("TITLE >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("TITLE <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("TITLE <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("TITLE like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("TITLE not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("TITLE in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("TITLE not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("TITLE between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("TITLE not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andIssueTypeIsNull() {
            addCriterion("ISSUE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andIssueTypeIsNotNull() {
            addCriterion("ISSUE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andIssueTypeEqualTo(Integer value) {
            addCriterion("ISSUE_TYPE =", value, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeNotEqualTo(Integer value) {
            addCriterion("ISSUE_TYPE <>", value, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeGreaterThan(Integer value) {
            addCriterion("ISSUE_TYPE >", value, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("ISSUE_TYPE >=", value, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeLessThan(Integer value) {
            addCriterion("ISSUE_TYPE <", value, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeLessThanOrEqualTo(Integer value) {
            addCriterion("ISSUE_TYPE <=", value, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeIn(List<Integer> values) {
            addCriterion("ISSUE_TYPE in", values, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeNotIn(List<Integer> values) {
            addCriterion("ISSUE_TYPE not in", values, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeBetween(Integer value1, Integer value2) {
            addCriterion("ISSUE_TYPE between", value1, value2, "issueType");
            return (Criteria) this;
        }

        public Criteria andIssueTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("ISSUE_TYPE not between", value1, value2, "issueType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIsNull() {
            addCriterion("INVOICE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIsNotNull() {
            addCriterion("INVOICE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeEqualTo(Integer value) {
            addCriterion("INVOICE_TYPE =", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotEqualTo(Integer value) {
            addCriterion("INVOICE_TYPE <>", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeGreaterThan(Integer value) {
            addCriterion("INVOICE_TYPE >", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("INVOICE_TYPE >=", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLessThan(Integer value) {
            addCriterion("INVOICE_TYPE <", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeLessThanOrEqualTo(Integer value) {
            addCriterion("INVOICE_TYPE <=", value, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeIn(List<Integer> values) {
            addCriterion("INVOICE_TYPE in", values, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotIn(List<Integer> values) {
            addCriterion("INVOICE_TYPE not in", values, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeBetween(Integer value1, Integer value2) {
            addCriterion("INVOICE_TYPE between", value1, value2, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andInvoiceTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("INVOICE_TYPE not between", value1, value2, "invoiceType");
            return (Criteria) this;
        }

        public Criteria andLinkNameIsNull() {
            addCriterion("LINK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLinkNameIsNotNull() {
            addCriterion("LINK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLinkNameEqualTo(String value) {
            addCriterion("LINK_NAME =", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameNotEqualTo(String value) {
            addCriterion("LINK_NAME <>", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameGreaterThan(String value) {
            addCriterion("LINK_NAME >", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameGreaterThanOrEqualTo(String value) {
            addCriterion("LINK_NAME >=", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameLessThan(String value) {
            addCriterion("LINK_NAME <", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameLessThanOrEqualTo(String value) {
            addCriterion("LINK_NAME <=", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameLike(String value) {
            addCriterion("LINK_NAME like", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameNotLike(String value) {
            addCriterion("LINK_NAME not like", value, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameIn(List<String> values) {
            addCriterion("LINK_NAME in", values, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameNotIn(List<String> values) {
            addCriterion("LINK_NAME not in", values, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameBetween(String value1, String value2) {
            addCriterion("LINK_NAME between", value1, value2, "linkName");
            return (Criteria) this;
        }

        public Criteria andLinkNameNotBetween(String value1, String value2) {
            addCriterion("LINK_NAME not between", value1, value2, "linkName");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("ADDRESS =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("ADDRESS <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("ADDRESS >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ADDRESS >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("ADDRESS <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("ADDRESS <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("ADDRESS like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("ADDRESS not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("ADDRESS in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("ADDRESS not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("ADDRESS between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("ADDRESS not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andPostCodeIsNull() {
            addCriterion("POST_CODE is null");
            return (Criteria) this;
        }

        public Criteria andPostCodeIsNotNull() {
            addCriterion("POST_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andPostCodeEqualTo(String value) {
            addCriterion("POST_CODE =", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotEqualTo(String value) {
            addCriterion("POST_CODE <>", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeGreaterThan(String value) {
            addCriterion("POST_CODE >", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeGreaterThanOrEqualTo(String value) {
            addCriterion("POST_CODE >=", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLessThan(String value) {
            addCriterion("POST_CODE <", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLessThanOrEqualTo(String value) {
            addCriterion("POST_CODE <=", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeLike(String value) {
            addCriterion("POST_CODE like", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotLike(String value) {
            addCriterion("POST_CODE not like", value, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeIn(List<String> values) {
            addCriterion("POST_CODE in", values, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotIn(List<String> values) {
            addCriterion("POST_CODE not in", values, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeBetween(String value1, String value2) {
            addCriterion("POST_CODE between", value1, value2, "postCode");
            return (Criteria) this;
        }

        public Criteria andPostCodeNotBetween(String value1, String value2) {
            addCriterion("POST_CODE not between", value1, value2, "postCode");
            return (Criteria) this;
        }

        public Criteria andMobileNoIsNull() {
            addCriterion("MOBILE_NO is null");
            return (Criteria) this;
        }

        public Criteria andMobileNoIsNotNull() {
            addCriterion("MOBILE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andMobileNoEqualTo(String value) {
            addCriterion("MOBILE_NO =", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoNotEqualTo(String value) {
            addCriterion("MOBILE_NO <>", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoGreaterThan(String value) {
            addCriterion("MOBILE_NO >", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoGreaterThanOrEqualTo(String value) {
            addCriterion("MOBILE_NO >=", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoLessThan(String value) {
            addCriterion("MOBILE_NO <", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoLessThanOrEqualTo(String value) {
            addCriterion("MOBILE_NO <=", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoLike(String value) {
            addCriterion("MOBILE_NO like", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoNotLike(String value) {
            addCriterion("MOBILE_NO not like", value, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoIn(List<String> values) {
            addCriterion("MOBILE_NO in", values, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoNotIn(List<String> values) {
            addCriterion("MOBILE_NO not in", values, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoBetween(String value1, String value2) {
            addCriterion("MOBILE_NO between", value1, value2, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andMobileNoNotBetween(String value1, String value2) {
            addCriterion("MOBILE_NO not between", value1, value2, "mobileNo");
            return (Criteria) this;
        }

        public Criteria andPhoneNoIsNull() {
            addCriterion("PHONE_NO is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNoIsNotNull() {
            addCriterion("PHONE_NO is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNoEqualTo(String value) {
            addCriterion("PHONE_NO =", value, "phoneNo");
            return (Criteria) this;
        }

        public Criteria andPhoneNoNotEqualTo(String value) {
            addCriterion("PHONE_NO <>", value, "phoneNo");
            return (Criteria) this;
        }

        public Criteria andPhoneNoGreaterThan(String value) {
            addCriterion("PHONE_NO >", value, "phoneNo");
            return (Criteria) this;
        }

        public Criteria andPhoneNoGreaterThanOrEqualTo(String value) {
            addCriterion("PHONE_NO >=", value, "phoneNo");
            return (Criteria) this;
        }

        public Criteria andPhoneNoLessThan(String value) {
            addCriterion("PHONE_NO <", value, "phoneNo");
            return (Criteria) this;
        }

        public Criteria andPhoneNoLessThanOrEqualTo(String value) {
            addCriterion("PHONE_NO <=", value, "phoneNo");
            return (Criteria) this;
        }

        public Criteria andPhoneNoLike(String value) {
            addCriterion("PHONE_NO like", value, "phoneNo");
            return (Criteria) this;
        }

        public Criteria andPhoneNoNotLike(String value) {
            addCriterion("PHONE_NO not like", value, "phoneNo");
            return (Criteria) this;
        }

        public Criteria andPhoneNoIn(List<String> values) {
            addCriterion("PHONE_NO in", values, "phoneNo");
            return (Criteria) this;
        }

        public Criteria andPhoneNoNotIn(List<String> values) {
            addCriterion("PHONE_NO not in", values, "phoneNo");
            return (Criteria) this;
        }

        public Criteria andPhoneNoBetween(String value1, String value2) {
            addCriterion("PHONE_NO between", value1, value2, "phoneNo");
            return (Criteria) this;
        }

        public Criteria andPhoneNoNotBetween(String value1, String value2) {
            addCriterion("PHONE_NO not between", value1, value2, "phoneNo");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("EMAIL =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("EMAIL <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("EMAIL >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("EMAIL <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("EMAIL <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("EMAIL like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("EMAIL not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("EMAIL in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("EMAIL not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("EMAIL between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("EMAIL not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andTaxRegNoIsNull() {
            addCriterion("TAX_REG_NO is null");
            return (Criteria) this;
        }

        public Criteria andTaxRegNoIsNotNull() {
            addCriterion("TAX_REG_NO is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRegNoEqualTo(String value) {
            addCriterion("TAX_REG_NO =", value, "taxRegNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegNoNotEqualTo(String value) {
            addCriterion("TAX_REG_NO <>", value, "taxRegNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegNoGreaterThan(String value) {
            addCriterion("TAX_REG_NO >", value, "taxRegNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegNoGreaterThanOrEqualTo(String value) {
            addCriterion("TAX_REG_NO >=", value, "taxRegNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegNoLessThan(String value) {
            addCriterion("TAX_REG_NO <", value, "taxRegNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegNoLessThanOrEqualTo(String value) {
            addCriterion("TAX_REG_NO <=", value, "taxRegNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegNoLike(String value) {
            addCriterion("TAX_REG_NO like", value, "taxRegNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegNoNotLike(String value) {
            addCriterion("TAX_REG_NO not like", value, "taxRegNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegNoIn(List<String> values) {
            addCriterion("TAX_REG_NO in", values, "taxRegNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegNoNotIn(List<String> values) {
            addCriterion("TAX_REG_NO not in", values, "taxRegNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegNoBetween(String value1, String value2) {
            addCriterion("TAX_REG_NO between", value1, value2, "taxRegNo");
            return (Criteria) this;
        }

        public Criteria andTaxRegNoNotBetween(String value1, String value2) {
            addCriterion("TAX_REG_NO not between", value1, value2, "taxRegNo");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("BANK_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("BANK_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("BANK_NAME =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("BANK_NAME <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("BANK_NAME >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_NAME >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("BANK_NAME <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("BANK_NAME <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("BANK_NAME like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("BANK_NAME not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("BANK_NAME in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("BANK_NAME not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("BANK_NAME between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("BANK_NAME not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankAcctNoIsNull() {
            addCriterion("BANK_ACCT_NO is null");
            return (Criteria) this;
        }

        public Criteria andBankAcctNoIsNotNull() {
            addCriterion("BANK_ACCT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andBankAcctNoEqualTo(String value) {
            addCriterion("BANK_ACCT_NO =", value, "bankAcctNo");
            return (Criteria) this;
        }

        public Criteria andBankAcctNoNotEqualTo(String value) {
            addCriterion("BANK_ACCT_NO <>", value, "bankAcctNo");
            return (Criteria) this;
        }

        public Criteria andBankAcctNoGreaterThan(String value) {
            addCriterion("BANK_ACCT_NO >", value, "bankAcctNo");
            return (Criteria) this;
        }

        public Criteria andBankAcctNoGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_ACCT_NO >=", value, "bankAcctNo");
            return (Criteria) this;
        }

        public Criteria andBankAcctNoLessThan(String value) {
            addCriterion("BANK_ACCT_NO <", value, "bankAcctNo");
            return (Criteria) this;
        }

        public Criteria andBankAcctNoLessThanOrEqualTo(String value) {
            addCriterion("BANK_ACCT_NO <=", value, "bankAcctNo");
            return (Criteria) this;
        }

        public Criteria andBankAcctNoLike(String value) {
            addCriterion("BANK_ACCT_NO like", value, "bankAcctNo");
            return (Criteria) this;
        }

        public Criteria andBankAcctNoNotLike(String value) {
            addCriterion("BANK_ACCT_NO not like", value, "bankAcctNo");
            return (Criteria) this;
        }

        public Criteria andBankAcctNoIn(List<String> values) {
            addCriterion("BANK_ACCT_NO in", values, "bankAcctNo");
            return (Criteria) this;
        }

        public Criteria andBankAcctNoNotIn(List<String> values) {
            addCriterion("BANK_ACCT_NO not in", values, "bankAcctNo");
            return (Criteria) this;
        }

        public Criteria andBankAcctNoBetween(String value1, String value2) {
            addCriterion("BANK_ACCT_NO between", value1, value2, "bankAcctNo");
            return (Criteria) this;
        }

        public Criteria andBankAcctNoNotBetween(String value1, String value2) {
            addCriterion("BANK_ACCT_NO not between", value1, value2, "bankAcctNo");
            return (Criteria) this;
        }

        public Criteria andRegAddressIsNull() {
            addCriterion("REG_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andRegAddressIsNotNull() {
            addCriterion("REG_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andRegAddressEqualTo(String value) {
            addCriterion("REG_ADDRESS =", value, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegAddressNotEqualTo(String value) {
            addCriterion("REG_ADDRESS <>", value, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegAddressGreaterThan(String value) {
            addCriterion("REG_ADDRESS >", value, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegAddressGreaterThanOrEqualTo(String value) {
            addCriterion("REG_ADDRESS >=", value, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegAddressLessThan(String value) {
            addCriterion("REG_ADDRESS <", value, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegAddressLessThanOrEqualTo(String value) {
            addCriterion("REG_ADDRESS <=", value, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegAddressLike(String value) {
            addCriterion("REG_ADDRESS like", value, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegAddressNotLike(String value) {
            addCriterion("REG_ADDRESS not like", value, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegAddressIn(List<String> values) {
            addCriterion("REG_ADDRESS in", values, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegAddressNotIn(List<String> values) {
            addCriterion("REG_ADDRESS not in", values, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegAddressBetween(String value1, String value2) {
            addCriterion("REG_ADDRESS between", value1, value2, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegAddressNotBetween(String value1, String value2) {
            addCriterion("REG_ADDRESS not between", value1, value2, "regAddress");
            return (Criteria) this;
        }

        public Criteria andRegPhoneIsNull() {
            addCriterion("REG_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andRegPhoneIsNotNull() {
            addCriterion("REG_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andRegPhoneEqualTo(String value) {
            addCriterion("REG_PHONE =", value, "regPhone");
            return (Criteria) this;
        }

        public Criteria andRegPhoneNotEqualTo(String value) {
            addCriterion("REG_PHONE <>", value, "regPhone");
            return (Criteria) this;
        }

        public Criteria andRegPhoneGreaterThan(String value) {
            addCriterion("REG_PHONE >", value, "regPhone");
            return (Criteria) this;
        }

        public Criteria andRegPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("REG_PHONE >=", value, "regPhone");
            return (Criteria) this;
        }

        public Criteria andRegPhoneLessThan(String value) {
            addCriterion("REG_PHONE <", value, "regPhone");
            return (Criteria) this;
        }

        public Criteria andRegPhoneLessThanOrEqualTo(String value) {
            addCriterion("REG_PHONE <=", value, "regPhone");
            return (Criteria) this;
        }

        public Criteria andRegPhoneLike(String value) {
            addCriterion("REG_PHONE like", value, "regPhone");
            return (Criteria) this;
        }

        public Criteria andRegPhoneNotLike(String value) {
            addCriterion("REG_PHONE not like", value, "regPhone");
            return (Criteria) this;
        }

        public Criteria andRegPhoneIn(List<String> values) {
            addCriterion("REG_PHONE in", values, "regPhone");
            return (Criteria) this;
        }

        public Criteria andRegPhoneNotIn(List<String> values) {
            addCriterion("REG_PHONE not in", values, "regPhone");
            return (Criteria) this;
        }

        public Criteria andRegPhoneBetween(String value1, String value2) {
            addCriterion("REG_PHONE between", value1, value2, "regPhone");
            return (Criteria) this;
        }

        public Criteria andRegPhoneNotBetween(String value1, String value2) {
            addCriterion("REG_PHONE not between", value1, value2, "regPhone");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachIdIsNull() {
            addCriterion("LICENSE_ATTACH_ID is null");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachIdIsNotNull() {
            addCriterion("LICENSE_ATTACH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachIdEqualTo(String value) {
            addCriterion("LICENSE_ATTACH_ID =", value, "licenseAttachId");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachIdNotEqualTo(String value) {
            addCriterion("LICENSE_ATTACH_ID <>", value, "licenseAttachId");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachIdGreaterThan(String value) {
            addCriterion("LICENSE_ATTACH_ID >", value, "licenseAttachId");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachIdGreaterThanOrEqualTo(String value) {
            addCriterion("LICENSE_ATTACH_ID >=", value, "licenseAttachId");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachIdLessThan(String value) {
            addCriterion("LICENSE_ATTACH_ID <", value, "licenseAttachId");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachIdLessThanOrEqualTo(String value) {
            addCriterion("LICENSE_ATTACH_ID <=", value, "licenseAttachId");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachIdLike(String value) {
            addCriterion("LICENSE_ATTACH_ID like", value, "licenseAttachId");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachIdNotLike(String value) {
            addCriterion("LICENSE_ATTACH_ID not like", value, "licenseAttachId");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachIdIn(List<String> values) {
            addCriterion("LICENSE_ATTACH_ID in", values, "licenseAttachId");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachIdNotIn(List<String> values) {
            addCriterion("LICENSE_ATTACH_ID not in", values, "licenseAttachId");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachIdBetween(String value1, String value2) {
            addCriterion("LICENSE_ATTACH_ID between", value1, value2, "licenseAttachId");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachIdNotBetween(String value1, String value2) {
            addCriterion("LICENSE_ATTACH_ID not between", value1, value2, "licenseAttachId");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachTypeIsNull() {
            addCriterion("LICENSE_ATTACH_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachTypeIsNotNull() {
            addCriterion("LICENSE_ATTACH_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachTypeEqualTo(String value) {
            addCriterion("LICENSE_ATTACH_TYPE =", value, "licenseAttachType");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachTypeNotEqualTo(String value) {
            addCriterion("LICENSE_ATTACH_TYPE <>", value, "licenseAttachType");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachTypeGreaterThan(String value) {
            addCriterion("LICENSE_ATTACH_TYPE >", value, "licenseAttachType");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachTypeGreaterThanOrEqualTo(String value) {
            addCriterion("LICENSE_ATTACH_TYPE >=", value, "licenseAttachType");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachTypeLessThan(String value) {
            addCriterion("LICENSE_ATTACH_TYPE <", value, "licenseAttachType");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachTypeLessThanOrEqualTo(String value) {
            addCriterion("LICENSE_ATTACH_TYPE <=", value, "licenseAttachType");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachTypeLike(String value) {
            addCriterion("LICENSE_ATTACH_TYPE like", value, "licenseAttachType");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachTypeNotLike(String value) {
            addCriterion("LICENSE_ATTACH_TYPE not like", value, "licenseAttachType");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachTypeIn(List<String> values) {
            addCriterion("LICENSE_ATTACH_TYPE in", values, "licenseAttachType");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachTypeNotIn(List<String> values) {
            addCriterion("LICENSE_ATTACH_TYPE not in", values, "licenseAttachType");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachTypeBetween(String value1, String value2) {
            addCriterion("LICENSE_ATTACH_TYPE between", value1, value2, "licenseAttachType");
            return (Criteria) this;
        }

        public Criteria andLicenseAttachTypeNotBetween(String value1, String value2) {
            addCriterion("LICENSE_ATTACH_TYPE not between", value1, value2, "licenseAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachIdIsNull() {
            addCriterion("TAX_REG_ATTACH_ID is null");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachIdIsNotNull() {
            addCriterion("TAX_REG_ATTACH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachIdEqualTo(String value) {
            addCriterion("TAX_REG_ATTACH_ID =", value, "taxRegAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachIdNotEqualTo(String value) {
            addCriterion("TAX_REG_ATTACH_ID <>", value, "taxRegAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachIdGreaterThan(String value) {
            addCriterion("TAX_REG_ATTACH_ID >", value, "taxRegAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachIdGreaterThanOrEqualTo(String value) {
            addCriterion("TAX_REG_ATTACH_ID >=", value, "taxRegAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachIdLessThan(String value) {
            addCriterion("TAX_REG_ATTACH_ID <", value, "taxRegAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachIdLessThanOrEqualTo(String value) {
            addCriterion("TAX_REG_ATTACH_ID <=", value, "taxRegAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachIdLike(String value) {
            addCriterion("TAX_REG_ATTACH_ID like", value, "taxRegAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachIdNotLike(String value) {
            addCriterion("TAX_REG_ATTACH_ID not like", value, "taxRegAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachIdIn(List<String> values) {
            addCriterion("TAX_REG_ATTACH_ID in", values, "taxRegAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachIdNotIn(List<String> values) {
            addCriterion("TAX_REG_ATTACH_ID not in", values, "taxRegAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachIdBetween(String value1, String value2) {
            addCriterion("TAX_REG_ATTACH_ID between", value1, value2, "taxRegAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachIdNotBetween(String value1, String value2) {
            addCriterion("TAX_REG_ATTACH_ID not between", value1, value2, "taxRegAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachTypeIsNull() {
            addCriterion("TAX_REG_ATTACH_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachTypeIsNotNull() {
            addCriterion("TAX_REG_ATTACH_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachTypeEqualTo(String value) {
            addCriterion("TAX_REG_ATTACH_TYPE =", value, "taxRegAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachTypeNotEqualTo(String value) {
            addCriterion("TAX_REG_ATTACH_TYPE <>", value, "taxRegAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachTypeGreaterThan(String value) {
            addCriterion("TAX_REG_ATTACH_TYPE >", value, "taxRegAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TAX_REG_ATTACH_TYPE >=", value, "taxRegAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachTypeLessThan(String value) {
            addCriterion("TAX_REG_ATTACH_TYPE <", value, "taxRegAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachTypeLessThanOrEqualTo(String value) {
            addCriterion("TAX_REG_ATTACH_TYPE <=", value, "taxRegAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachTypeLike(String value) {
            addCriterion("TAX_REG_ATTACH_TYPE like", value, "taxRegAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachTypeNotLike(String value) {
            addCriterion("TAX_REG_ATTACH_TYPE not like", value, "taxRegAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachTypeIn(List<String> values) {
            addCriterion("TAX_REG_ATTACH_TYPE in", values, "taxRegAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachTypeNotIn(List<String> values) {
            addCriterion("TAX_REG_ATTACH_TYPE not in", values, "taxRegAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachTypeBetween(String value1, String value2) {
            addCriterion("TAX_REG_ATTACH_TYPE between", value1, value2, "taxRegAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxRegAttachTypeNotBetween(String value1, String value2) {
            addCriterion("TAX_REG_ATTACH_TYPE not between", value1, value2, "taxRegAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachIdIsNull() {
            addCriterion("TAXPAYER_ATTACH_ID is null");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachIdIsNotNull() {
            addCriterion("TAXPAYER_ATTACH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachIdEqualTo(String value) {
            addCriterion("TAXPAYER_ATTACH_ID =", value, "taxpayerAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachIdNotEqualTo(String value) {
            addCriterion("TAXPAYER_ATTACH_ID <>", value, "taxpayerAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachIdGreaterThan(String value) {
            addCriterion("TAXPAYER_ATTACH_ID >", value, "taxpayerAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachIdGreaterThanOrEqualTo(String value) {
            addCriterion("TAXPAYER_ATTACH_ID >=", value, "taxpayerAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachIdLessThan(String value) {
            addCriterion("TAXPAYER_ATTACH_ID <", value, "taxpayerAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachIdLessThanOrEqualTo(String value) {
            addCriterion("TAXPAYER_ATTACH_ID <=", value, "taxpayerAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachIdLike(String value) {
            addCriterion("TAXPAYER_ATTACH_ID like", value, "taxpayerAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachIdNotLike(String value) {
            addCriterion("TAXPAYER_ATTACH_ID not like", value, "taxpayerAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachIdIn(List<String> values) {
            addCriterion("TAXPAYER_ATTACH_ID in", values, "taxpayerAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachIdNotIn(List<String> values) {
            addCriterion("TAXPAYER_ATTACH_ID not in", values, "taxpayerAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachIdBetween(String value1, String value2) {
            addCriterion("TAXPAYER_ATTACH_ID between", value1, value2, "taxpayerAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachIdNotBetween(String value1, String value2) {
            addCriterion("TAXPAYER_ATTACH_ID not between", value1, value2, "taxpayerAttachId");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachTypeIsNull() {
            addCriterion("TAXPAYER_ATTACH_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachTypeIsNotNull() {
            addCriterion("TAXPAYER_ATTACH_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachTypeEqualTo(String value) {
            addCriterion("TAXPAYER_ATTACH_TYPE =", value, "taxpayerAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachTypeNotEqualTo(String value) {
            addCriterion("TAXPAYER_ATTACH_TYPE <>", value, "taxpayerAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachTypeGreaterThan(String value) {
            addCriterion("TAXPAYER_ATTACH_TYPE >", value, "taxpayerAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TAXPAYER_ATTACH_TYPE >=", value, "taxpayerAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachTypeLessThan(String value) {
            addCriterion("TAXPAYER_ATTACH_TYPE <", value, "taxpayerAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachTypeLessThanOrEqualTo(String value) {
            addCriterion("TAXPAYER_ATTACH_TYPE <=", value, "taxpayerAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachTypeLike(String value) {
            addCriterion("TAXPAYER_ATTACH_TYPE like", value, "taxpayerAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachTypeNotLike(String value) {
            addCriterion("TAXPAYER_ATTACH_TYPE not like", value, "taxpayerAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachTypeIn(List<String> values) {
            addCriterion("TAXPAYER_ATTACH_TYPE in", values, "taxpayerAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachTypeNotIn(List<String> values) {
            addCriterion("TAXPAYER_ATTACH_TYPE not in", values, "taxpayerAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachTypeBetween(String value1, String value2) {
            addCriterion("TAXPAYER_ATTACH_TYPE between", value1, value2, "taxpayerAttachType");
            return (Criteria) this;
        }

        public Criteria andTaxpayerAttachTypeNotBetween(String value1, String value2) {
            addCriterion("TAXPAYER_ATTACH_TYPE not between", value1, value2, "taxpayerAttachType");
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