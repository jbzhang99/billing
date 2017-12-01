package com.ai.baas.mt.web.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/account")
public class AccountManageController {
    @RequestMapping("/toManage")
    public ModelAndView toAccountManage(HttpServletRequest request) {
        return new ModelAndView("jsp/system/account-manage");
    }

    @RequestMapping("/toAccountAdd")
    public ModelAndView toAccountAdd(HttpServletRequest request) {
        return new ModelAndView("jsp/system/account-manage-add");
    }

    @RequestMapping("/toAccountUpdate")
    public ModelAndView toAccountUpdate(HttpServletRequest request) {
        return new ModelAndView("jsp/system/account-manage-update");
    }

    @RequestMapping("/toAccountDetail")
    public ModelAndView toAccountDetail(HttpServletRequest request) {
        return new ModelAndView("jsp/system/account-manage-detail");
    }

}
