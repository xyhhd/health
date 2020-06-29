package com.bianyiit.dao;



import com.bianyiit.pojo.Permission;

import java.util.Set;

public interface PermissionDao {
    Set<Permission> findPermissionById(Integer id);
}
