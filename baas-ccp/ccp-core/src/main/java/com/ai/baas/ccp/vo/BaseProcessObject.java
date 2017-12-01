package com.ai.baas.ccp.vo;

import com.ai.baas.ccp.topoligy.core.exception.OmcException;
import com.ai.baas.ccp.topoligy.core.pojo.OmcObj;
import com.google.gson.JsonObject;

public class BaseProcessObject {

    private ConfigContainerObject config;

    private OmcObj omcobj;

    private JsonObject input;

    private JsonObject output;

    public BaseProcessObject() {
    }

    public BaseProcessObject(ConfigContainerObject cfg, OmcObj obj, JsonObject data)
            throws OmcException {
        this.config = cfg;
        this.omcobj = obj;
        this.input = data;
    }

    public void setDefault(ConfigContainerObject configContainerObject, OmcObj obj, JsonObject data) {
        this.config = configContainerObject;
        this.omcobj = obj;
        this.input = data;
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

    public ConfigContainerObject getConfig() {
        return config;
    }

    public void setConfig(ConfigContainerObject config) {
        this.config = config;
    }

    public OmcObj getOmcobj() {
        return omcobj;
    }

    public void setOmcobj(OmcObj omcobj) {
        this.omcobj = omcobj;
    }

}
