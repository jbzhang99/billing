package com.ai.citic.billing.web.util;

import com.ai.citic.billing.web.constants.CiticWebConstants;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.components.ccs.CCSClientFactory;
import com.ai.opt.sdk.components.mcs.MCSClientFactory;
import com.ai.opt.sdk.util.RandomUtil;
import com.ai.opt.sdk.web.model.ResponseData;
import com.ai.paas.ipaas.ccs.IConfigClient;
import com.ai.paas.ipaas.mcs.interfaces.ICacheClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class VerifyUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(VerifyUtil.class);

	public static BufferedImage getImageVerifyCode(String namespace, String cacheKey, int width, int height) {
		// int width = 100, height = 38;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		// 获取图形上下文
		Graphics g = image.getGraphics();

		// 设定背景色
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, width, height);

		// 画边框
		g.setColor(Color.lightGray);
		g.drawRect(0, 0, width - 1, height - 1);

		// 取随机产生的认证码
		String verifyCode = RandomUtil.randomString(CiticWebConstants.PictureVerifyConstants.VERIFY_SIZE);
		// 将认证码存入缓存
		try{
		    ICacheClient cacheClient = MCSClientFactory.getCacheClient(namespace);
	        IConfigClient defaultConfigClient = CCSClientFactory.getDefaultConfigClient();
	        String overTimeStr = defaultConfigClient.get(CiticWebConstants.PictureVerifyConstants.VERIFY_OVERTIME_KEY);
	        cacheClient.setex(cacheKey, Integer.valueOf(overTimeStr), verifyCode);
	        LOGGER.debug("cacheKey=" + cacheKey + ",verifyCode=" + verifyCode);
	        // 将认证码显示到图象中
	        g.setColor(new Color(0x10a2fb));

	        g.setFont(new Font("Atlantic Inline", Font.PLAIN, 20));
	        String Str = verifyCode.substring(0, 1);
	        g.drawString(Str, 8, 22);

	        Str = verifyCode.substring(1, 2);
	        g.drawString(Str, 23, 19);
	        Str = verifyCode.substring(2, 3);
	        g.drawString(Str, 38, 24);

	        Str = verifyCode.substring(3, 4);
	        g.drawString(Str, 53, 20);
	        // 随机产生88个干扰点，使图象中的认证码不易被其它程序探测到
	        Random random = new Random();
	        for (int i = 0; i < 88; i++) {
	            int x = random.nextInt(width);
	            int y = random.nextInt(height);
	            g.drawOval(x, y, 0, 0);
	        }

	        // 图象生效
	        g.dispose();

		}catch(Exception e){
		    e.printStackTrace();
		}
		 return image;
	}

	/**
	 * 检查图片验证码
	 * 
	 * @param verifyCode
	 * @param cacheVerifyCode
	 * @return
	 */
	public static ResponseData<String> checkPictureVerifyCode(String verifyCode, String cacheVerifyCode) {
		ResponseData<String> responseData = null;
		ResponseHeader responseHeader = null;
		if (cacheVerifyCode == null) {
			responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, "图形验证码已失效", "0");
			responseHeader = new ResponseHeader(false, CiticWebConstants.ResultCodeConstants.REGISTER_PICTURE_ERROR, "图形验证码已失效");
		} else if (cacheVerifyCode.compareToIgnoreCase(verifyCode) != 0) {
			responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, "图形验证码错误", "0");
			responseHeader = new ResponseHeader(false, CiticWebConstants.ResultCodeConstants.REGISTER_PICTURE_ERROR, "图形验证码错误");
		} else {
			responseData = new ResponseData<String>(ResponseData.AJAX_STATUS_SUCCESS, "图形验证码正确", "1");
			responseHeader = new ResponseHeader(true, CiticWebConstants.ResultCodeConstants.SUCCESS_CODE, "图形验证码正确");
		}
		responseData.setResponseHeader(responseHeader);
		return responseData;
	}

}
