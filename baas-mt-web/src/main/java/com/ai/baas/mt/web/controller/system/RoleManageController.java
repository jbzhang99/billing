package com.ai.baas.mt.web.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/role")
public class RoleManageController {
    @RequestMapping("/toManage")
    public ModelAndView toRoleManage(HttpServletRequest request) {
        return new ModelAndView("jsp/system/role-manage");
    }

    @RequestMapping("/toRoleAdd")
    public ModelAndView toRoleAdd(HttpServletRequest request) {
        return new ModelAndView("jsp/system/role-manage-add");
    }

    @RequestMapping("/toRoleUpdate")
    public ModelAndView toRoleUpdate(HttpServletRequest request) {
        return new ModelAndView("jsp/system/role-manage-update");
    }
    @RequestMapping("/toRoleDetail")
    public ModelAndView toFuncDetail(HttpServletRequest request) {
        return new ModelAndView("jsp/system/role-manage-detail");
    }

}
