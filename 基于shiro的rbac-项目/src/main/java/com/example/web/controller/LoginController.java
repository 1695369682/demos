package com.example.web.controller;

import com.example.until.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LoginController {

    @RequestMapping("/login")
    @ResponseBody
    public JsonResult login(String username, String password)
    {
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token= new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            subject.getSession().setAttribute("user_in_session",subject.getPrincipal());
        }catch (UnknownAccountException e){
            return new JsonResult(false,"账号错误");
        }catch (IncorrectCredentialsException e)
        {
            return new JsonResult(false,"密码错误");
        }catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,"error");
        }
        return new JsonResult(true,"登录成功");
    }

}
