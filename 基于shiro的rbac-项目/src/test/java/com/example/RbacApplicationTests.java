package com.example;

import com.example.mapper.EmployeeMapper;
import com.example.service.IDepartmentService;
import com.example.service.IEmployeeService;
import com.example.service.IPermissionService;
import com.example.until.StringNull;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sun.security.provider.MD5;

import javax.sql.DataSource;
import java.util.UUID;

@SpringBootTest
class RbacApplicationTests {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private StringNull stringNull;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IPermissionService permissionService;



    @Test
    void contextLoads() {
        for (int i = 0; i <3 ; i++) {
            String salt = UUID.randomUUID().toString().substring(0, 4);

            Md5Hash pwd=new Md5Hash("1",salt,3);
            System.out.println("salt::"+salt);
            System.out.println("pwd::"+pwd);
        }
    }

}
