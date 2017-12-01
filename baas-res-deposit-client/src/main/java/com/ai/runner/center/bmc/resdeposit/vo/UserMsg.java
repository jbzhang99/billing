package com.ai.runner.center.bmc.resdeposit.vo;

public class UserMsg {
    private String MSG_TYPE;

    private String SUBS_ID;

    private String TENANT_ID;

    private String SERVICE_STATUS;

    public String getMSG_TYPE() {
        return MSG_TYPE;
    }

    public void setMSG_TYPE(String mSG_TYPE) {
        MSG_TYPE = mSG_TYPE;
    }

    public String getSUBS_ID() {
        return SUBS_ID;
    }

    public void setSUBS_ID(String sUBS_ID) {
        SUBS_ID = sUBS_ID;
    }

    public String getTENANT_ID() {
        return TENANT_ID;
    }

    public void setTENANT_ID(String tENANT_ID) {
        TENANT_ID = tENANT_ID;
    }

    public String getSERVICE_STATUS() {
        return SERVICE_STATUS;
    }

    public void setSERVICE_STATUS(String sERVICE_STATUS) {
        SERVICE_STATUS = sERVICE_STATUS;
    }

    @Override
    public String toString() {
        return "UserMsg:[MSG_TYPE:" + MSG_TYPE + ",SUBS_ID:" + SUBS_ID + ",TENANT_ID:" + TENANT_ID
                + ",SERVICE_STATUS:" + SERVICE_STATUS + "]";
    }

}
