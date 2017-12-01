package com.ai.runner.center.mmp.service;

import com.ai.runner.center.mmp.vo.SMSInputVo;

public interface SMSSv {

	String dataInput(String param);

	String dataInput(SMSInputVo sMSInputVo);

}
