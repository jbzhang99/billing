package com.ai.runner.center.bmc.resdeposit.vo;

public class MsgControl {
    private String event_id;

    private String system_id;

    private String tenant_id;

    private String source_type;

    private String owner_type;

    private String owner_id;

    private String event_type;

    private String amount;

    private String amount_mark;

    private String amount_type;

    private String expanded_info;

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getSystem_id() {
        return system_id;
    }

    public void setSystem_id(String system_id) {
        this.system_id = system_id;
    }

    public String getTenant_id() {
        return tenant_id;
    }

    public void setTenant_id(String tenant_id) {
        this.tenant_id = tenant_id;
    }

    public String getSource_type() {
        return source_type;
    }

    public void setSource_type(String source_type) {
        this.source_type = source_type;
    }

    public String getOwner_type() {
        return owner_type;
    }

    public void setOwner_type(String owner_type) {
        this.owner_type = owner_type;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount_mark() {
        return amount_mark;
    }

    public void setAmount_mark(String amount_mark) {
        this.amount_mark = amount_mark;
    }

    public String getAmount_type() {
        return amount_type;
    }

    public void setAmount_type(String amount_type) {
        this.amount_type = amount_type;
    }

    public String getExpanded_info() {
        return expanded_info;
    }

    public void setExpanded_info(String expanded_info) {
        this.expanded_info = expanded_info;
    }

}
