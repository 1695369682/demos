//package com.example.web.interceptor;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class MvcJavaConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private LoginInterceptor loginInterceptor;
//
//    @Autowired
//    private PermissionInterceptor permissionInterceptor;
//
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 注册登录拦截器
//        registry.addInterceptor(loginInterceptor)
//                // 对哪些资源起过滤作用
//                .addPathPatterns("/**")
//                // 对哪些资源起排除作用
//                .excludePathPatterns("/login", "/static/**");
//        // 注册登录拦截器
//        registry.addInterceptor(permissionInterceptor)
//                // 对哪些资源起过滤作用
//                .addPathPatterns("/**")
//                // 对哪些资源起排除作用
//                .excludePathPatterns("/login", "/static/**","/favicon.ico");
//    }
//}