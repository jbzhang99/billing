package com.ai.baas.batch.client.api.usage.params;

import java.io.Serializable;

public class UsageRecord implements Serializable{

    private String serviceId;
    private String usageJson;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getUsageJson() {
        return usageJson;
    }

    public void setUsageJson(String usageJson) {
        this.usageJson = usageJson;
    }
}
