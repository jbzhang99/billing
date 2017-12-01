package com.ai.citic.billing.web.controller.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * iframe demo
 * @author wangluyang
 *
 */
@RestController
@RequestMapping("/iframe")
public class DemoController {

	public static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);
	
	@RequestMapping("/toIframeParent")
    public ModelAndView toPolicyList(HttpServletRequest request, HttpServletResponse response){
        ModelAndView view = new ModelAndView("demo/parent");
        return view;
    }
	
	@RequestMapping("/toPriceElementAdd")
    public ModelAndView toPriceElementAdd(HttpServletRequest request, Model model){
        ModelAndView view = new ModelAndView("jsp/element/priceElementAdd");
        return view;
    }
	
	@RequestMapping("/toCustomPriceElementAdd")
    public ModelAndView toCustomPriceElementAdd(HttpServletRequest request, Model model){
		String mainProductId = request.getParameter("mainProductId");
		String mainProductName = request.getParameter("mainProductName");
		String supplierId = request.getParameter("supplierId");
		String supplierName = request.getParameter("supplierName");
		model.addAttribute("mainProductId",mainProductId);
		model.addAttribute("mainProductName",mainProductName);
		model.addAttribute("supplierId",supplierId);
		model.addAttribute("supplierName",supplierName);
        ModelAndView view = new ModelAndView("jsp/element/customPriceElementAdd");
        return view;
    }
	
}
