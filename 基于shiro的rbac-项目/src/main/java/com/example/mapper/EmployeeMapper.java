package com.example.mapper;

import com.example.entity.Employee;
import com.example.qo.SearchQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    Integer count(SearchQuery qo);

    List<Employee> selectForList(SearchQuery qo);

    void insertRelationBatch(@Param("role_id") Long id, @Param("empIds") Long[] empIds);

    void deleteRelationById(Long id);

    int checkUserName(String userName);

    Employee selUserByUsername(String userName);

    Employee checkUser(@Param("username") String username, @Param("password") String password);
}