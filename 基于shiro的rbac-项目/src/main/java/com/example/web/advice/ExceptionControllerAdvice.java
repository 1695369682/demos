package com.example.web.advice;

import com.alibaba.fastjson.JSON;
import com.example.until.JsonResult;
import com.example.web.exception.BusinessExcetion;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ExceptionControllerAdvice {
    // 捕获自定义异常
    @ExceptionHandler(BusinessExcetion.class)
    public String handlerException(HandlerMethod method, HttpServletResponse response, BusinessExcetion e, Model model){
        // 异常也分为两种
        if(method.hasMethodAnnotation(ResponseBody.class)){
            // AJAX 异常
            response.setContentType("application/json;charset=utf-8");
            try {
                response.getWriter().write(JSON.toJSONString(new JsonResult(false,e.getMessage())));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;        //  直接将数据返回
        } else {
            // 页面异常
            model.addAttribute("errorMsg",e.getMessage());
            // 存在视图解析器，存在前后缀。可以直接访问 templates
            return "common/error"; //  返回页面
        }
    }

    // 捕获系统异常
    @ExceptionHandler(Exception.class)
    public String handlerException(HandlerMethod method, HttpServletResponse response, Exception e, Model model){
        // 异常也分为两种
        if(method.hasMethodAnnotation(ResponseBody.class)){
            // AJAX 异常
            response.setContentType("application/json;charset=utf-8");
            try {
                response.getWriter().write(JSON.toJSONString(new JsonResult(false,"服务器崩溃啦~~~，请联系管理员")));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;        //  直接将数据返回
        } else {
            // 页面异常
            model.addAttribute("errorMsg","服务器崩溃啦~~~，请联系管理员");
            // 存在视图解析器，存在前后缀。可以直接访问 templates
            return "common/error"; //  返回页面
        }
    }

    // 捕获没有权限异常
    @ExceptionHandler(UnauthorizedException.class)
    public String handlerException(HandlerMethod method, HttpServletResponse response, UnauthorizedException e, Model model){
        // 异常也分为两种
        if(method.hasMethodAnnotation(ResponseBody.class)){
            // AJAX 异常
            response.setContentType("application/json;charset=utf-8");
            try {
                response.getWriter().write(JSON.toJSONString(new JsonResult(false,"您还没有权限")));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return null;        //  直接将数据返回
        } else {
            // 页面异常
            model.addAttribute("errorMsg","您还没有权限");
            // 存在视图解析器，存在前后缀。可以直接访问 templates
            return "common/error"; //  返回页面
        }
    }
}
