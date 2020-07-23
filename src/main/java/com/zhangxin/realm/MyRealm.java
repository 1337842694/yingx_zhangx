package com.zhangxin.realm;

import com.zhangxin.dao.AdminDao;
import com.zhangxin.entity.Admin;
import com.zhangxin.entity.Authority;
import com.zhangxin.entity.Role;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    AdminDao adminDao;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        Admin admin = adminDao.queryByUserName(username);
        AuthenticationInfo info = info = new SimpleAuthenticationInfo(
                admin.getUsername(),
                admin.getPassword(),
                ByteSource.Util.bytes(admin.getSalt()),
                this.getName());
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //System.out.println("授权");
        String username = (String) principals.getPrimaryPrincipal();

        Admin admin = adminDao.queryAllMessage(username);

        ArrayList<String> roleNames = new ArrayList<>();
        ArrayList<String> permissions = new ArrayList<>();

        List<Role> roles = admin.getRoles();

        for (Role role : roles) {
            roleNames.add(role.getRoleName());
            for (Authority authority : role.getAuthorities()) {
                permissions.add(authority.getName());
            }
        }


        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //给当前主体授予多个角色
        info.addRoles(roleNames);
        //给当前主体授予多个权限
        info.addStringPermissions(permissions);

        return info;
    }


}
