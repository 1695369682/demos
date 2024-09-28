package com.example.mapper;


import com.example.entity.Department;
import com.example.entity.Role;

import com.example.qo.QueryObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    List<Role> selectForList(QueryObject qo);

    void insertRelationBatch(@Param("role_id") Long id, @Param("permissionIds") Long[] permissionIds);

    void delRelation(Long roleId);

    List<Role> selectByEmpId(@Param("emp_id") Long id);
}