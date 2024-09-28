package com.example.service;

import com.example.entity.Permission;
import com.example.qo.QueryObject;
import com.example.qo.TablePageInfo;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface IPermissionService {
    //分页
    PageInfo<Permission> selForList(QueryObject qo);

    void edit(Permission Permission);

    void add(Permission Permission);

    int delById(Long id);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selAll();

//    void reLoad();

//    根据角色id查询角色权限信息
    List<Permission> queryByRoleId(Long roleId);

    List<String> selPermissionsByEmpId(Long id);

    Workbook export();

    int importExcel(InputStream is) throws IOException;

    TablePageInfo query(QueryObject qo);
}
