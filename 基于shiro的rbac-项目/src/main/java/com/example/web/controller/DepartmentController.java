package com.example.web.controller;

import com.example.entity.Department;
import com.example.qo.DepartmentQuery;
import com.example.service.IDepartmentService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("/list")
//    @RequiresPermissions("department:list")
    public String getList(Model model, @ModelAttribute("qo") DepartmentQuery qo)
    {
        PageInfo<Department> pageInfo=departmentService.selForList(qo);
        model.addAttribute("pageInfo", pageInfo);
        return "department/list";
    }
    @RequestMapping("/input")
    public String saveOrUpdate(Long id,Model model)
    {
        if (id!=null)
        {
            Department department=departmentService.selectByPrimaryKey(id);
            model.addAttribute("department",department);
        }
        return "department/input";
    }

    @RequestMapping("/saveOrUpdate")
    @RequiresPermissions("department:saveOrUpdate")
    public String save(Department department)
    {
        System.out.println(department.toString());
        if (department.getId()==null) {
           departmentService.add(department);
        }
        else {
            departmentService.edit(department);
        }
        return "redirect:/department/list";
    }
    @RequestMapping("/del")
    @RequiresPermissions("department:del")
    public String del(Long id)
    {
        if(id!=null)
        departmentService.delById(id);
        return "redirect:/department/list";
    }
}
