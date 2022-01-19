package com.api.spring_restapi.Service;

import com.api.spring_restapi.Entity.QRole;
import com.api.spring_restapi.Entity.Role;
import com.api.spring_restapi.Exceptions.NotFoundException;
import com.api.spring_restapi.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role delete(Integer id) {
        Optional<Role> role = roleRepository.findById(id);
        if(!role.isPresent()){
            throw new NotFoundException("Role Is Not Found!");
        }
        roleRepository.deleteById(id);
        return role.get();
    }

    @Override
    public Role editName(Integer id, String name) {
        if(name.equals("")){
            throw new NotFoundException("Name is empty");
        }
        int isNameExist = roleRepository.selectExistRoleName(name);
        if(isNameExist > 0){
            throw new NotFoundException("Role Name Is Already Exist");
        }else{
            Role roleExist = roleRepository.findById(id).get();
            roleExist.setName(name);
            return roleRepository.save(roleExist);
        }

    }

    @Override
    public Role create(Role role) {
        int isNameExist = roleRepository.selectExistRoleName(role.getName());
        if(isNameExist > 0){
            throw new NotFoundException("Role Name Is Already Exist");
        }else{
            return roleRepository.save(role);
        }
    }
}
