package com.ai.citic.billing.web.controller.captcha;

import com.ai.citic.billing.web.constants.CiticWebConstants;
import com.ai.citic.billing.web.util.VerifyUtil;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.opt.sdk.util.StringUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RequestMapping("/captcha")
@RestController
public class CaptchaController {
	private static final Logger LOG = LoggerFactory.getLogger(CaptchaController.class);


	/**
	 * 获取验证码
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/getImageVerifyCode")
	public void getImageVerifyCode(HttpServletRequest request, HttpServletResponse response) {
		String cacheKey = CiticWebConstants.CACHE_KEY_VERIFY_PICTURE + request.getSession().getId();
		BufferedImage image = VerifyUtil.getImageVerifyCode(CiticWebConstants.CACHE_NAMESPACE, cacheKey, 79, 30);
		try {
			ImageIO.write(image, "PNG", response.getOutputStream());
		} catch (IOException e) {
			LOG.error("生成图片验证码错误：" + e);
			e.printStackTrace();
		}
	}

    /**
     * 验证码校验
     * @param request
     * @param captcha
     * @return
     */
	@RequestMapping("/verifyCaptcha")
	public ResponseData<String> verifyCaptcha(HttpServletRequest request,String captcha){
		ResponseData<String> responseData;
		if(!StringUtil.isBlank(captcha)){
			ICacheClient iCacheClient = MCSClientFactory.getCacheClient(CiticWebConstants.CACHE_NAMESPACE);
            String code = iCacheClient.get(CiticWebConstants.CACHE_KEY_VERIFY_PICTURE + request.getSession().getId());
            responseData = VerifyUtil.checkPictureVerifyCode(captcha,code);
        }else{
            responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS,"请输入验证码","0");
        }
		return responseData;
	}


}
