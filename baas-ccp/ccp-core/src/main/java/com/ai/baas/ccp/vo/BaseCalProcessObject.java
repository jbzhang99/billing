package com.ai.baas.ccp.vo;

import com.google.gson.JsonObject;

public class BaseCalProcessObject {

    private ConfigContainerObject config;

    private InformationProcessorObject information;

    private JsonObject input;

    private JsonObject output;

    public BaseCalProcessObject(ConfigContainerObject cfg, InformationProcessorObject info,
            JsonObject data) {
        this.setConfig(cfg);
        this.setInformation(info);
        this.setInput(data);
    }

    protected void setdefault(ConfigContainerObject cfg, InformationProcessorObject info,
            JsonObject data) {
        this.setConfig(cfg);
        this.setInformation(info);
        this.setInput(data);
    }

    public JsonObject getInput() {
        return input;
    }

    public void setInput(JsonObject input) {
        this.input = input;
    }

    public JsonObject getOutput() {
        return output;
    }

    public void setOutput(JsonObject output) {
        this.output = output;
    }

    public void setConfig(ConfigContainerObject config) {
        this.config = config;
    }

    public void setInformation(InformationProcessorObject information) {
        this.information = information;
    }

    public ConfigContainerObject getConfig() {
        return config;
    }

    public InformationProcessorObject getInformation() {
        return information;
    }

}
