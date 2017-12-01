package com.ai.baas.batch.client.prepareflow.params;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Instances
{
    private String instance_id;
    
    private List<Map<String, String>> ratioList;

    private String use;

    private String app_base_line;

    private String is_deleted;

	public String getInstance_id() {
		return instance_id;
	}

	public void setInstance_id(String instance_id) {
		this.instance_id = instance_id;
	}

	public List<Map<String, String>> getRatioList() {
		return ratioList;
	}

	public void setRatioList(List<Map<String, String>> ratioList) {
		this.ratioList = ratioList;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public String getApp_base_line() {
		return app_base_line;
	}

	public void setApp_base_line(String app_base_line) {
		this.app_base_line = app_base_line;
	}

	public String getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
	}


}