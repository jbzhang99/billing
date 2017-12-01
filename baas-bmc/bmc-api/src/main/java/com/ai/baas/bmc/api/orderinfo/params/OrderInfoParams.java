package com.ai.baas.bmc.api.orderinfo.params;

import java.util.List;

import com.ai.opt.base.vo.BaseInfo;

public class OrderInfoParams extends BaseInfo {
    private static final long serialVersionUID = -6666115297878537537L;

    /**
     * 消息流水, 组成：租户ID + YYMMDDHH24MISS + SSS(毫秒) + 9位序列号, 必填
     */
    private String tradeSeq;

    /**
     * 外部客户ID, 必填
     */
    private String extCustId;

    /**
     * 业务类型
     */
    private String ServType;

    /**
     * 订购类型 取值范围：Test:测试；Normal：正式 必填
     */
    private String usetype;

    /**
     * 订购状态 取值范围：Normal：正常；Stop：停机；Cancel：销户
     */
    private String state;

    /**
     * 服务标识 必填 VARCHAR(64)
     */
    private String serviceId;

    /**
     * 订购时间 格式：YYYYMMDDHH24MISS VARCHAR(14)
     */
    private String orderTime;

    /**
     * 归属省 参考省份定义表 VARCHAR(6)
     */
    private String provinceCode;

    /**
     * 归属地区 以0开头的地区号 VARCHAR(6)
     */
    private String cityCode;

    /**
     * 发展渠道 VARCHAR(32)
     */
    private String chlId;

    /**
     * 发展人 VARCHAR(32)
     */
    private String devId;

    /**
     * 信控方案标识 VARCHAR(32)
     */
    private String scoutPolocyID;

    /**
     * 生效时间 格式：YYYYMMDDHH24MISS 必填 VARCHAR(14)
     */
    private String activeTime;

    /**
     * 失效时间 格式：YYYYMMDDHH24MISS 必填 VARCHAR(14)
     */
    private String inactiveTime;

    /**
     * 订购扩展信息列表 list
     */
    private List<OrderExt> orderExtInfo;

    /**
     * 备注 varchar(1024)
     */
    private String remark;

    /**
     * 产品列表 list
     */
    private List<Product> productList;

    /**
     * 扩展信息列表
     */
    private List<ExtInfo> sublist;

    public String getTradeSeq() {
        return tradeSeq;
    }

    public void setTradeSeq(String tradeSeq) {
        this.tradeSeq = tradeSeq;
    }

    public String getExtCustId() {
        return extCustId;
    }

    public void setExtCustId(String extCustId) {
        this.extCustId = extCustId;
    }

    public String getUsetype() {
        return usetype;
    }

    public void setUsetype(String usetype) {
        this.usetype = usetype;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getChlId() {
        return chlId;
    }

    public void setChlId(String chlId) {
        this.chlId = chlId;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(String activeTime) {
        this.activeTime = activeTime;
    }

    public String getInactiveTime() {
        return inactiveTime;
    }

    public void setInactiveTime(String inactiveTime) {
        this.inactiveTime = inactiveTime;
    }

    public List<OrderExt> getOrderExtInfo() {
        return orderExtInfo;
    }

    public void setOrderExtInfo(List<OrderExt> orderExtInfo) {
        this.orderExtInfo = orderExtInfo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getServType() {
        return ServType;
    }

    public void setServType(String servType) {
        ServType = servType;
    }

    public String getScoutPolocyID() {
        return scoutPolocyID;
    }

    public void setScoutPolocyID(String scoutPolocyID) {
        this.scoutPolocyID = scoutPolocyID;
    }

    public List<ExtInfo> getSublist() {
        return sublist;
    }

    public void setSublist(List<ExtInfo> sublist) {
        this.sublist = sublist;
    }

}
