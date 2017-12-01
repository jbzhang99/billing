package com.ai.baas.mt.web.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/role-func")
public class RoleFuncManageController {
    @RequestMapping("/toRoleFunc")
    public ModelAndView toFuncRole(HttpServletRequest request) {
        return new ModelAndView("jsp/system/role-func");
    }
}
