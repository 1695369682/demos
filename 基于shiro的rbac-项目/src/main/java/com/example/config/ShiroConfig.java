package com.example.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.example.realm.EmployeeRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {
    //会话管理器
    @Bean
    public DefaultWebSessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }
    //realm对象
    @Bean
    public EmployeeRealm employeeRealm() {
        EmployeeRealm realm=new EmployeeRealm();
        return realm;
    }

    //安全管理器对象
    @Bean
    public DefaultWebSecurityManager securityManager(EmployeeRealm employeeRealm,DefaultWebSessionManager sessionManager){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //添加realm对象
        securityManager.setRealm(employeeRealm);
//        改后的数据库被我删了，md5的密码找不到了，所以把登录认证注掉了
        //添加session管理器对象
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

     // Shiro过滤器配置
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // Shiro的核心安全接口,这个属性是必须的
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 身份认证失败，则跳转到登录页面的配置
        shiroFilterFactoryBean.setLoginUrl("/static/login.html");
        // 权限认证失败，则跳转到指定页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/nopermissioin");
        // Shiro连接约束配置，即过滤链的定义
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 对静态资源设置匿名访问
        // 不需要拦截的访问
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/favicon.ico**", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");

        // 退出 logout地址，shiro去清除session
        filterChainDefinitionMap.put("/logout", "logout");
        // 所有请求需要认证
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 开启Shiro注解通知器
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
            DefaultWebSecurityManager securityManager)
    {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    /**
     * 设置支持CGlib代理
     * 详情看DefaultAopProxyFactory#createAopProxy
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    /**
     * thymeleaf模板引擎和shiro框架的整合
     */
    @Bean
    public ShiroDialect shiroDialect()
    {
        return new ShiroDialect();
    }
    //缓存
    @Bean
    public EhCacheManager getEhCacheManager()
    {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:ehcache/ehcache-shiro.xml");
        return em;
    }
    /**
     * 自定义Realm
     */
    @Bean
    public EmployeeRealm userRealm(EhCacheManager cacheManager,
                                   HashedCredentialsMatcher hashedCredentialsMatcher)
    {
        EmployeeRealm employeeRealm = new EmployeeRealm();
        employeeRealm.setCacheManager(cacheManager);
        employeeRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return employeeRealm;
    }
    /**
     * 凭证匹配器
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher matcher=new HashedCredentialsMatcher("md5");
        matcher.setHashIterations(3);
        return matcher;
    }
}
