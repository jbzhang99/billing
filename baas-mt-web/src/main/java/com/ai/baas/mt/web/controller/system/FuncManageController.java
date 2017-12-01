package com.ai.baas.mt.web.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/func")
public class FuncManageController {
    @RequestMapping("/toManage")
    public ModelAndView toFuncManage(HttpServletRequest request) {
        return new ModelAndView("jsp/system/func-manage");
    }

    @RequestMapping("/toFuncAdd")
    public ModelAndView toFuncAdd(HttpServletRequest request) {
        return new ModelAndView("jsp/system/func-manage-add");
    }

    @RequestMapping("/toFuncUpdate")
    public ModelAndView toFuncUpdate(HttpServletRequest request) {
        return new ModelAndView("jsp/system/func-manage-update");
    }
    @RequestMapping("/toFuncDetail")
    public ModelAndView toFuncDetail(HttpServletRequest request) {
        return new ModelAndView("jsp/system/func-manage-detail");
    }
}
