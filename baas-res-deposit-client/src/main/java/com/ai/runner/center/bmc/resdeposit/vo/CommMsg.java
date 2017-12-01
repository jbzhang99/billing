package com.ai.runner.center.bmc.resdeposit.vo;

public class CommMsg {
    private String MSG_TYPE;

    private String SUBS_ID;

    private String TENANT_ID;

    private String PRODUCT_ID;

    private String SUBS_PRODUCT_ID;

    private String ACTIVE_TIME;

    private String INACTIVE_TIME;

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

    public String getPRODUCT_ID() {
        return PRODUCT_ID;
    }

    public void setPRODUCT_ID(String pRODUCT_ID) {
        PRODUCT_ID = pRODUCT_ID;
    }

    public String getSUBS_PRODUCT_ID() {
        return SUBS_PRODUCT_ID;
    }

    public void setSUBS_PRODUCT_ID(String sUBS_PRODUCT_ID) {
        SUBS_PRODUCT_ID = sUBS_PRODUCT_ID;
    }

    public String getACTIVE_TIME() {
        return ACTIVE_TIME;
    }

    public void setACTIVE_TIME(String aCTIVE_TIME) {
        ACTIVE_TIME = aCTIVE_TIME;
    }

    public String getINACTIVE_TIME() {
        return INACTIVE_TIME;
    }

    public void setINACTIVE_TIME(String iNACTIVE_TIME) {
        INACTIVE_TIME = iNACTIVE_TIME;
    }

    @Override
    public String toString() {
        return "CommMsg:[MSG_TYPE:" + MSG_TYPE + ",SUBS_ID:" + SUBS_ID + ",TENANT_ID:" + TENANT_ID
                + ",PRODUCT_ID:" + PRODUCT_ID + ",SUBS_PRODUCT_ID:" + SUBS_PRODUCT_ID
                + ",ACTIVE_TIME:" + ACTIVE_TIME + ",INACTIVE_TIME:" + INACTIVE_TIME + "]";
    }

}
