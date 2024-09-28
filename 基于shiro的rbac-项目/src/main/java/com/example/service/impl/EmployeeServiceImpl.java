package com.example.service.impl;

import com.example.entity.Employee;
import com.example.mapper.EmployeeMapper;
import com.example.qo.SearchQuery;
import com.example.service.IEmployeeService;
import com.example.until.StringNull;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Transactional
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public PageInfo<Employee> selForList(SearchQuery qo) {
        PageHelper.startPage(qo.getCurrentPage(), qo.getPageSize());

        return new PageInfo<Employee>(employeeMapper.selectForList(qo));
    }

    @Override
    @Transactional
    public void edit(Employee employee, Long[] roleIds) {
        //维护中间表-先删
        employeeMapper.deleteRelationById(employee.getId());
        //后加
        if (roleIds != null && roleIds.length > 0)//合理化校验
        {
            employeeMapper.insertRelationBatch(employee.getId(), roleIds);
        }
        //维护员工本身数据
        employeeMapper.updateByPrimaryKey(employee);
    }

    @Override
    public void add(Employee employee, Long[] roleIds) {
        //前台验证过的数据，后台必须再次验证，此处省略校验

        String salt = UUID.randomUUID().toString().substring(0, 4);
        Md5Hash pwd=new Md5Hash(employee.getPassword(), salt,3);
        employee.setPassword(pwd.toString());
        employee.setSalt(salt);
        employeeMapper.insert(employee);
        //向关联表批量插入
        if (roleIds != null && roleIds.length > 0 && !employee.isAdmin())//合理化校验
        {
            employeeMapper.insertRelationBatch(employee.getId(), roleIds);
        }
    }

    @Override
    @Transactional
    public void delById(Long id) {
        //删中间表
        employeeMapper.deleteRelationById(id);
        //删员工
        employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Employee selectByPrimaryKey(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean checkUserName(String userName) {
        int conut = employeeMapper.checkUserName(userName);
        return conut == 0;
    }

    @Override
    public Employee selUserByUsername(String userName) {
        return employeeMapper.selUserByUsername(userName);
    }

    @Override
    public Employee checkUser(String username, String password) {
        //进行非空判断，否则不查询数据库
        if (StringNull.isNotNull(username) && StringNull.isNotNull(password)) {
            Employee e = employeeMapper.checkUser(username, password);
            if (e != null)
                return e;

            return null;
        } else {
            return null;
        }
    }

}
