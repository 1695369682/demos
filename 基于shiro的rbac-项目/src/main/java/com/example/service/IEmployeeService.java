package com.example.service;

import com.example.entity.Employee;
import com.example.qo.SearchQuery;
import com.github.pagehelper.PageInfo;

public interface IEmployeeService {
    PageInfo<Employee> selForList(SearchQuery qo);

    void edit(Employee employee, Long[] roleIds);

    void add(Employee employee, Long[] roleIds);

    void delById(Long id);

    Employee selectByPrimaryKey(Long id);

    Boolean checkUserName(String userName);

    Employee selUserByUsername(String userName);

    Employee checkUser(String username, String password);
}
