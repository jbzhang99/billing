package com.ai.baas.pt.web.controller.business;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 关于我们页
 * @author gucl
 */
@RestController
public class LinkUsController {
	
	private static final Logger LOG = LoggerFactory.getLogger(LinkUsController.class);
	
	@RequestMapping("/linkus")
    public ModelAndView toList(HttpServletRequest request) {

        ModelAndView view = new ModelAndView("jsp/linkus");
        return view;
    }
	
}
