package com.ai.baas.op.web.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/index")
public class IndexController {

	@RequestMapping("/toIndex")
	public ModelAndView index(HttpServletRequest request) {

		return new ModelAndView("jsp/index");
    }
}
