//package com.example.web.interceptor;
//
//import com.alibaba.fastjson.JSON;
//import com.example.entity.Employee;
//import com.example.service.IPermissionService;
//import com.example.until.JsonResult;
//import com.github.pagehelper.util.StringUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//
//@Component
//public class PermissionInterceptor implements HandlerInterceptor {
//    @Autowired
//    private IPermissionService permissionService;
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        /**
//         Object handler：
//         情况1：访问静态资源的处理         -->   ResourceHttpRequestHandler
//         情况2：访问控制器方法的处理       -->   HandlerMethod
//         */
//        // 1. 获取当前登录用户
//        Employee currentEmp = (Employee) request.getSession().getAttribute("user_in_session");
//        // 2. 判断是否为超级管理员
//        if (currentEmp.isAdmin()){
//            // 若为超级管理员则直接放行。
//            return true;
//        }
//        // 3. 若不是超级管理员则代码继续向下执行
//        if (handler instanceof HandlerMethod)
//        {
//            HandlerMethod handlerMethod = (HandlerMethod)handler;
//            // 4. 判断当前访问的控制器方法是否贴有权限注解，(@RequirePermission注解)
//            RequirePermission rpAnnotation = handlerMethod.getMethodAnnotation(RequirePermission.class);
//            if (rpAnnotation == null)
//            {
//                //  5.没有注解说明为公共方法，不需要权限拦截，直接放行,若存在注解方法继续向下执行
//                return true;
//            }
//            // 6. 获取注解上的 expression。
//            String expression=rpAnnotation.name();
//            // 7. 拿到当前用户的权限集合，判断注解中的 expression 在用户的权限集合中是否存在。
//            List<String> expressionList= (List<String>) request.getSession().getAttribute("permission_in_session");
//            // 安全(非空)校验
//            if (StringUtil.isNotEmpty(expression)&&expressionList!=null &&expressionList.size()>0){
//                // 若不存在则说明没有权限，拦截请求，跳转到没有权限的页面。
//                if (!expressionList.contains(expression))
//                {
//                    //页面请求和ajax请求，通过@ResponseBody区别
//                    if(handlerMethod.hasMethodAnnotation(ResponseBody.class))
//                    {
//                        //贴了，说明是ajax请求
//                        System.err.println("ajax");
//                        response.setContentType("application/json;charset=UTF-8");
//                        response.getWriter().write(JSON.toJSONString(new JsonResult(false,"没有权限操作")));
//                    }else
//                    //没贴，页面请求，跳转到没有权限的页面
//                    response.sendRedirect("/permission/noPermission");
//                    return false;
//                }
//                // 若存在则说明该用户有权限访问，放行。
//            }
//        }
//        return true;
//    }
//}
