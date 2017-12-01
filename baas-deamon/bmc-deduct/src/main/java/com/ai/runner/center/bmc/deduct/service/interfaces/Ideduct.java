package com.ai.runner.center.bmc.deduct.service.interfaces;

import net.sf.json.JSONObject;

public interface Ideduct {
	public void resDeduct(JSONObject jsonobject,String msgContent ) throws Exception;
	public void funDeduct(JSONObject jsonobject,String msgContent ) throws Exception;
}
