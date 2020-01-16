package com.junyi.mall.controller.admin;

import com.junyi.mall.common.ServiceResultEnum;
import com.junyi.mall.entity.AdminUser;
import com.junyi.mall.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
    @Resource
    private AdminUserService adminUserService;

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }
    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession session) {
        if (StringUtils.isEmpty(verifyCode)) {
            session.setAttribute("errorMsg", "验证码不能为空");
            return "admin/login";
        }
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            session.setAttribute("errorMsg", "用户名或密码不能为空");
            return "admin/login";
        }
        String kaptchaCode = session.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            session.setAttribute("errorMsg", "验证码错误");
            return "admin/login";
        }
        AdminUser user = adminUserService.login(userName, password);
        if (user != null) {
            session.setAttribute("loginUser", user.getNickName());
            session.setAttribute("loginUserId", user.getAdminUserId());
            return "redirect:/admin/index";
        } else {
            session.setAttribute("errorMsg", "登陆失败，请联系作者获得测试账号");
            return "admin/login";
        }
    }



    @GetMapping("/test")
    public String test() {
        return "admin/test";
    }

    @GetMapping({"", "/", "/index", "index.html"})
    public String index(HttpServletRequest request) {
        request.setAttribute("path", "index");
        request.setAttribute("categoryCount", 0);
        request.setAttribute("blogCount", 0);
        request.setAttribute("linkCount", 0);
        request.setAttribute("tagCount", 0);
        request.setAttribute("commentCount", 0);
        return "admin/index";
    }

    @GetMapping("/profile")
    public String profile(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("loginUserId");
        AdminUser user = adminUserService.getUserDetailById(userId);
        if (user  == null)
            return "admin/login";
        request.setAttribute("path", "profile");
        request.setAttribute("loginUserName", user.getLoginUserName());
        request.setAttribute("nickName", user.getNickName());
        return "admin/profile";
    }
    @PostMapping("/profile/password")
    @ResponseBody
    public String passwordUpdate(HttpServletRequest request, @RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword) {
        if (StringUtils.isEmpty(originalPassword) || StringUtils.isEmpty(newPassword)) {
            return "参数不能为空";
        }
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        if (adminUserService.updatePassword(loginUserId, originalPassword, newPassword)) {
            //修改成功后清空session中的数据，前端控制跳转至登录页
            request.getSession().removeAttribute("loginUserId");
            request.getSession().removeAttribute("loginUser");
            request.getSession().removeAttribute("errorMsg");
            return ServiceResultEnum.SUCCESS.getResult();
        } else {
            return "修改失败";
        }
    }

    @PostMapping("/profile/name")
    @ResponseBody
    public String nameUpdate(HttpServletRequest request, @RequestParam("loginUserName") String loginUserName,
                             @RequestParam("nickName") String nickName) {
        if (StringUtils.isEmpty(loginUserName) || StringUtils.isEmpty(nickName)) {
            return "参数不能为空";
        }
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        if (adminUserService.updateName(loginUserId, loginUserName, nickName)) {
            return ServiceResultEnum.SUCCESS.getResult();
        } else {
            return "修改失败";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("errorMsg");
        return "admin/login";
    }
}
