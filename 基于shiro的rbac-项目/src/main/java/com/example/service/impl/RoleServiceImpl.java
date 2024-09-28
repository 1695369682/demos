package com.example.service.impl;


import com.example.entity.Role;
import com.example.mapper.RoleMapper;
import com.example.qo.QueryObject;
import com.example.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public PageInfo<Role> selForList(QueryObject qo) {
        //将用户传递的当前页，每页展示条数传递给PageHelpr,PageHelpr会将这些数据绑定到线程中
        PageHelper.startPage(qo.getCurrentPage(),qo.getPageSize());
            return new PageInfo<Role>(roleMapper.selectForList(qo));
    }

    @Override
    public void edit(Role role, Long[] permissionIds) {
        //保存角色信息
        roleMapper.updateByPrimaryKey(role);
        //先删除角色权限信息
        roleMapper.delRelation(role.getId());
        //再添加角色权限信息
/*      不能直接调用add，会导致插入新的role
*       this.add(role, permissionIds);
* */
        if (permissionIds != null && permissionIds.length>0)//合理化校验
        {
            roleMapper.insertRelationBatch(role.getId(),permissionIds);
        }
    }

    @Override
    @Transactional
    public void add(Role role, Long[] permissionIds) {
        //向角色表插入
        roleMapper.insert(role);
        //向关联表批量插入
        if (permissionIds != null && permissionIds.length>0)//合理化校验
        {
            roleMapper.insertRelationBatch(role.getId(),permissionIds);
        }
    }

    @Override
    @Transactional
    public void delById(Long id) {
        //处理中间表
        roleMapper.delRelation(id);
        //删人
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> selAll() {
        return roleMapper.selectAll();
    }

    @Override
    public List<Role> selByEmpId(Long id) {
        return roleMapper.selectByEmpId(id);
    }

}
