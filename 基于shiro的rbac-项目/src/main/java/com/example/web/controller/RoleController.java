package com.example.web.controller;

import com.example.entity.Permission;
import com.example.entity.Role;
import com.example.qo.QueryObject;
import com.example.service.IPermissionService;
import com.example.service.IRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/list")
    public String getList(Model model, @ModelAttribute("qo") QueryObject qo)
    {
        PageInfo<Role> pageInfo=roleService.selForList(qo);
        model.addAttribute("pageInfo", pageInfo);
        return "role/list";
    }
    @RequestMapping("/input")
    public String saveOrUpdate(Long roleId,Model model)
    {
        model.addAttribute("allPermissions",permissionService.selAll());
        if (roleId!=null)
        {
            model.addAttribute("title","角色编辑");
            //当前角色数据
            Role role=roleService.selectByPrimaryKey(roleId);
            model.addAttribute("role",role);
            //当前角色拥有权限数据
            List<Permission> selfPermissions=permissionService.queryByRoleId(roleId);
            model.addAttribute("selfPermissions",selfPermissions);
        }
        return "role/input";
    }

    @RequestMapping("/saveOrUpdate")
    public String save(Role role,Long[] permissionIds)
    {
        System.out.println(role.toString());
        if (role.getId()==null) {
           roleService.add(role,permissionIds);
        }
        else {
            roleService.edit(role,permissionIds);
        }
        return "redirect:/role/list";
    }
    @RequestMapping("/delete")
    public String del(Long roleId)
    {
        if(roleId!=null)
        roleService.delById(roleId);
        return "redirect:/role/list";
    }
}
