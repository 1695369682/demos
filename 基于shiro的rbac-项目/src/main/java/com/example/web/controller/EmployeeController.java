package com.example.web.controller;

import com.example.entity.Department;
import com.example.entity.Employee;
import com.example.entity.Role;
import com.example.qo.SearchQuery;
import com.example.until.JsonResult;
import com.example.service.IDepartmentService;
import com.example.service.IEmployeeService;
import com.example.service.IRoleService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IRoleService roleService;

    // 处理部门查询请求    /employee/list    currentPage  pageSizer keyword deptId
    @RequestMapping("/list")
//    @RequiresPermissions("employee:list")
    public String list(Model model, @ModelAttribute("qo") SearchQuery qo){
        Subject subject= SecurityUtils.getSubject();
        PageInfo<Employee> PageInfo = null;

        if (subject.isPermitted("employee:list"))
        {//有权限，用service调方法给pageinfo
            PageInfo= employeeService.selForList(qo);
            PageInfo.getList().forEach(System.out::println);
        }else {//没有查询权限--显示空页面
            PageInfo=new PageInfo<>(Collections.emptyList());
        }

        model.addAttribute("pageInfo",PageInfo);
        // 查询所有部门并共享
        List<Department> departmentList = departmentService.selAll();
        model.addAttribute("departmentList",departmentList);
        return "employee/list";   // /WEB-INF/views/ employee/list .jsp
    }

    // 处理部门删除请求  /employee/delete
    @RequestMapping("/delete")
    @RequiresPermissions("employee:del")
    public String delete(Long id){
        if (id != null) {
            employeeService.delById(id);
        }
        return "redirect:/employee/list";      // 找上面的处理器方法
    }

    // 进入 新增||编辑 页面   /employee/input
    @RequestMapping("/input")
    public String input(Long id,Model model){
        // 查询所有部门并共享
        List<Department> departmentList = departmentService.selAll();
        model.addAttribute("departmentList",departmentList);
        //角色信息
        model.addAttribute("allRoles",roleService.selAll());
        if (id != null) {
            // 编辑
            model.addAttribute("empTitle","员工编辑");
            Employee employee = employeeService.selectByPrimaryKey(id);
            model.addAttribute("employee",employee);
            //查询员工的角色数据
            List<Role> roles = roleService.selByEmpId(id);
            model.addAttribute("selfRoles",roles);
        }
        return "employee/input";  // /WEB-INF/views/ employee/input .jsp
    }

    // 处理新增请求   /employee/saveOrUpdate
    @RequestMapping("/saveOrUpdate")
    @RequiresPermissions("employee:saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Employee employee, Long[] roleIds){
            if(employee.getId() != null){
                // 编辑
                employeeService.edit(employee,roleIds);
            } else {
                // 新增
                employeeService.add(employee,roleIds);
            }
        return new JsonResult(true);
    }
    //校验用户名是否存在
    @RequestMapping("/checkUserName")
    @ResponseBody
    public Map<String,Boolean> checkUserName(String username,Integer id)
    {
        Map<String,Boolean> map=new HashMap<String,Boolean>();
        if (id != null)
        map.put("valid",true);
        else
        map.put("valid",employeeService.checkUserName(username));
        return map;
    }
}
