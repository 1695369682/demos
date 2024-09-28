package com.example.service.impl;

import com.example.entity.Permission;
import com.example.mapper.PermissionMapper;
import com.example.qo.QueryObject;
import com.example.qo.TablePageInfo;
import com.example.service.IPermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Transactional
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    ApplicationContext ac;

    @Override
    public PageInfo<Permission> selForList(QueryObject qo) {
        //将用户传递的当前页，每页展示条数传递给PageHelpr,PageHelpr会将这些数据绑定到线程中
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
            return new PageInfo<Permission>(permissionMapper.selForList(qo));
    }

    @Override
    public void edit(Permission Permission) {
        permissionMapper.updateByPrimaryKey(Permission);
    }

    @Override
    public void add(Permission Permission) {
        permissionMapper.insert(Permission);
    }

    @Override
    public int delById(Long id) {
        int i = permissionMapper.deleteByPrimaryKey(id);
        System.err.println(i);
        return i;
    }

    @Override
    public Permission selectByPrimaryKey(Long id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Permission> selAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public List<Permission> queryByRoleId(Long roleId) {
        return permissionMapper.queryByRoleId(roleId);
    }

    @Override
    public List<String> selPermissionsByEmpId(Long id) {
        List<String> selfPermissionList=permissionMapper.selPermissionsByEmpId(id);
        return selfPermissionList;
    }

    @Override
    public Workbook export() {
        List<Permission> permissions = permissionMapper.selectAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("权限列表");
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("权限名称");
        row.createCell(2).setCellValue("权限表达式");
        Permission permission  = null;
        for(int i=0;i<permissions.size();i++){
            row = sheet.createRow(i+1);
            permission = permissions.get(i);
            row.createCell(0).setCellValue(permission.getId());
            row.createCell(1).setCellValue(permission.getName());
            row.createCell(2).setCellValue(permission.getExpression());
        }
        return workbook;
    }

    @Override
    public int importExcel(InputStream is) throws IOException {
        //新建表
        Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        int rowNum=sheet.getLastRowNum()+1;
        System.out.println(rowNum);
        Row row =null;
        int insertCount=0;
        for (int i=1; i<rowNum; i++) {
            row=sheet.getRow(i);
            String name = row.getCell(0).toString();
            String expression = row.getCell(1).toString();
            //判断不为空
            if (StringUtil.isNotEmpty(expression)&&StringUtil.isNotEmpty(name)) {
                int count=permissionMapper.getCountByPermission(expression);
                if (count==0)
                {
                    Permission permission = new Permission(name,expression);
                    permissionMapper.insert(permission);
                    insertCount++;
                }
            }
        }
        workbook.close();
        return insertCount;
    }

    @Override
    public TablePageInfo query(QueryObject qo) {
        PageHelper.offsetPage(qo.getOffset(), qo.getLimit());

        List<Permission> permissionList = permissionMapper.selForList(qo);

        PageInfo<Permission> pageInfo = new PageInfo<>(permissionList);

        return new TablePageInfo(pageInfo.getTotal(),pageInfo.getList());
    }
}
