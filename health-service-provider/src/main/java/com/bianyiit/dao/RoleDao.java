package com.bianyiit.dao;

import com.bianyiit.pojo.Role;

import java.util.Set;

public interface RoleDao {
    //根据用户查询角色信息
    Set<Role> findRoleById(Integer id);
}
