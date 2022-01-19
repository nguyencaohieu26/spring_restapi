package com.api.spring_restapi.Service;

import com.api.spring_restapi.Entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role delete (Integer id);
    Role editName(Integer id,String name);
    Role create(Role role);
}
