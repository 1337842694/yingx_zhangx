package com.zhangxin.conf;

import com.zhangxin.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ShiroFilter {

    @Bean
    public ShiroFilterFactoryBean getShiroFilter(DefaultWebSecurityManager securityManager) {
        /*过滤器工厂*/
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        /*将安全管理器交给过滤器工厂*/
        factoryBean.setSecurityManager(securityManager);

        HashMap<String, String> map = new HashMap<>();


        map.put("/**", "authc");
        map.put("/user/login", "anon");
        map.put("/main/main.jsp", "anon");
        map.put("/logout", "logout");
        map.put("/**", "user");

        /*设置过滤器链*/
        factoryBean.setFilterChainDefinitionMap(map);

        /*设置登陆页面位置*/
        factoryBean.setLoginUrl("/user/login.jsp");
        return factoryBean;
    }

    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(CacheManager cacheManager, MyRealm realm, CookieRememberMeManager cookieRememberMeManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(realm);

        securityManager.setRememberMeManager(cookieRememberMeManager);
        securityManager.setCacheManager(cacheManager);
        return securityManager;
    }

    @Bean
    public MyRealm getRealm(HashedCredentialsMatcher credentialsMatcher) {
        MyRealm realm = new MyRealm();

        realm.setCredentialsMatcher(credentialsMatcher);
        return realm;
    }

    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();

        credentialsMatcher.setHashAlgorithmName("md5");
        credentialsMatcher.setHashIterations(1024);
        return credentialsMatcher;
    }

    @Bean
    public CookieRememberMeManager getCookieRememberMeManager(SimpleCookie cookie) {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();

        cookieRememberMeManager.setCookie(cookie);

        return cookieRememberMeManager;
    }

    @Bean
    public SimpleCookie getSimpleCookie() {
        SimpleCookie cookie = new SimpleCookie();
        //cookie的名称，对应的是前端checkbox中name属性的值  name="rememberme"
        cookie.setName("rememberme");
        //设置记住我cookie的失效时间   参数：秒
        cookie.setMaxAge(60 * 2);
        return cookie;
    }

    //将Shiro的缓存交给Spring工厂管理
    @Bean
    public CacheManager getCacheManager() {
        //创建缓存管理
        CacheManager cacheManager = new EhCacheManager();
        return cacheManager;
    }
}
