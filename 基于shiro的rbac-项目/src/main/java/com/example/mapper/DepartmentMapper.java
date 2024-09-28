package com.example.mapper;

import com.example.entity.Department;
import com.example.qo.DepartmentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);

    List<Department> selectForList(DepartmentQuery qo);
}