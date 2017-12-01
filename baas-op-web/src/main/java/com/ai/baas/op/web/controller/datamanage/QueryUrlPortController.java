package com.ai.baas.op.web.controller.datamanage;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 端口和地址查询
 * @author wangluyang
 *
 */
@RestController
@RequestMapping("/queryUrlPort")
public class QueryUrlPortController {

	private static final Logger LOG = Logger.getLogger(QueryUrlPortController.class);
	
	@RequestMapping("/list")
    public ModelAndView toQuery(HttpServletRequest request) {

		return new ModelAndView("jsp/datamanage/queryUrlPort");
    }
}
