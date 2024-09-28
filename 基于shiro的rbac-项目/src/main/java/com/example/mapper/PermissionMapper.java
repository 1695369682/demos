package com.example.mapper;

import com.example.entity.Permission;
import com.example.qo.QueryObject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission record);

    List<Permission> selForList(QueryObject qo);

    List<Permission> queryByRoleId(Long roleId);

    List<String> selPermissionsByEmpId(Long id);

    int getCountByPermission(String expression);
}