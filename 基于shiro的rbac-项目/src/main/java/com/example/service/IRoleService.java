package com.example.service;

import com.example.entity.Role;
import com.example.qo.QueryObject;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IRoleService {
    PageInfo<Role> selForList(QueryObject qo);

    void edit(Role role, Long[] permissionIds);

    void add(Role role, Long[] permissionIds);

    void delById(Long id);

    Role selectByPrimaryKey(Long id);

    List<Role> selAll();

    List<Role> selByEmpId(Long id);
}
