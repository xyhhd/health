package com.bianyiit.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bianyiit.dao.PermissionDao;
import com.bianyiit.dao.RoleDao;
import com.bianyiit.dao.UserDao;
import com.bianyiit.pojo.Permission;
import com.bianyiit.pojo.Role;
import com.bianyiit.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    PermissionDao permissionDao;


    @Override
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);
        Set<Role> roles = roleDao.findRoleById(user.getId());
        if(roles.size()>0||roles!=null){
            for (Role role : roles) {
                Set<Permission> permission = permissionDao.findPermissionById(role.getId());
                role.setPermissions(permission);
            }
            user.setRoles(roles);
        }
        return user;
    }
}
