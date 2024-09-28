package com.example.realm;

import com.example.entity.Employee;
import com.example.entity.Role;
import com.example.service.IEmployeeService;
import com.example.service.IPermissionService;
import com.example.service.IRoleService;
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

import java.util.List;

public class EmployeeRealm extends AuthorizingRealm {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private IRoleService roleService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Employee employee = (Employee) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (employee.isAdmin()) {//是管理员--权限可以使用通配符
            info.addStringPermission("*:*");
            //角色
            List<Role> roles = roleService.selAll();
            for (Role role : roles) {
                info.addRole(role.getSn());
            }
        } else {//不是
            List<String> permissionList = permissionService.selPermissionsByEmpId(employee.getId());
            info.addStringPermissions(permissionList);
            //角色
            List<Role> roles = roleService.selByEmpId(employee.getId());
            for (Role role : roles) {
                info.addRole(role.getSn());
            }
        }

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        Employee employee = employeeService.selUserByUsername(username);
        if (employee == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(employee, employee.getPassword(),
                ByteSource.Util.bytes(employee.getSalt()), getName());
    }

}
