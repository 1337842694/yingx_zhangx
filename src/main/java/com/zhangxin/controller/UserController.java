package com.zhangxin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    @RequestMapping("login")
    public String login(String username, String password, Integer rememberme) {
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        if (rememberme != null && rememberme == 1) {
            token.setRememberMe(true);
        }


        try {
            subject.login(token);
            boolean authenticated = subject.isAuthenticated();
            System.out.println("认证结果：" + authenticated);
            return "redirect:/main/main.jsp";
        } catch (UnknownAccountException e) {
            System.out.println("未知账户异常，用户名不存在");
            return "redirect:/user/login.jsp";
        } catch (IncorrectCredentialsException e) {
            System.out.println("不正确的凭证异常   密码错误");
            return "redirect:/user/login.jsp";
        }
    }

    /*@RequestMapping("logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "user/login";
    }*/
}
