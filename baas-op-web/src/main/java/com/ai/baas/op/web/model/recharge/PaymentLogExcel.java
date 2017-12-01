package com.ai.baas.op.web.model.recharge;

import java.sql.Timestamp;

public class PaymentLogExcel {
    private String peerSerialCode;
    private String custName;
    private String custGrade;
    private double totalAmount;
    private Timestamp payTime;
    
    public String getPeerSerialCode() {
        return peerSerialCode;
    }
    public void setPeerSerialCode(String peerSerialCode) {
        this.peerSerialCode = peerSerialCode;
    }
    public String getCustName() {
        return custName;
    }
    public void setCustName(String custName) {
        this.custName = custName;
    }
    public String getCustGrade() {
        return custGrade;
    }
    public void setCustGrade(String custGrade) {
        this.custGrade = custGrade;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public Timestamp getPayTime() {
        return new Timestamp(payTime.getTime());
    }
    public void setPayTime(Timestamp payTime) {
        this.payTime = new Timestamp(payTime.getTime());
    }
    
}
