package com.example.service;

import com.example.entity.Department;
import com.example.qo.DepartmentQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IDepartmentService {
    PageInfo<Department> selForList(DepartmentQuery qo);

    void edit(Department department);

    void add(Department department);

    void delById(Long id);

    Department selectByPrimaryKey(Long id);

    List<Department> selAll();
}
