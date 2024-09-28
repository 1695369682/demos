package com.example.service.impl;

import com.example.entity.Department;
import com.example.mapper.DepartmentMapper;
import com.example.qo.DepartmentQuery;
import com.example.qo.PageResult;
import com.example.service.IDepartmentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Override
    public PageInfo<Department> selForList(DepartmentQuery qo) {
        //将用户传递的当前页，每页展示条数传递给PageHelpr,PageHelpr会将这些数据绑定到线程中
        Page<Object> objects = PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());
        System.out.println(objects);
            return new PageInfo<Department>(departmentMapper.selectForList(qo));
    }

    @Override
    public void edit(Department department) {
        departmentMapper.updateByPrimaryKey(department);
    }

    @Override
    public void add(Department department) {
        departmentMapper.insert(department);
    }

    @Override
    public void delById(Long id) {
        departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Department selectByPrimaryKey(Long id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> selAll() {
        return departmentMapper.selectAll();
    }

}
