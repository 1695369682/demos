//package com.example.web.interceptor;
//
//import com.example.entity.Employee;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class LoginInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //获取session中当前用户
//        Employee currentEmp=(Employee)request.getSession().getAttribute("user_in_session");
//        if(currentEmp==null)
//        {
//            response.sendRedirect("/static/login.html");
//        }
//        return true;
//    }
//}
