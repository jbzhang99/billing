package com.ai.baas.mt.web.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/tenant")
public class TenantManageController {
    @RequestMapping("/toManage")
    public ModelAndView toTenantManage(HttpServletRequest request) {
        return new ModelAndView("jsp/system/tenant-manage");
    }

    @RequestMapping("/toTenantUpdate")
    public ModelAndView toTenantUpdte(HttpServletRequest request) {
        return new ModelAndView("jsp/system/tenant-manage-update");
    }
}
