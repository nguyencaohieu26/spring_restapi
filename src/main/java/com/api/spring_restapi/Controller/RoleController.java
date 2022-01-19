package com.api.spring_restapi.Controller;

import com.api.spring_restapi.Entity.Role;
import com.api.spring_restapi.Mapper.RoleMapper;
import com.api.spring_restapi.Response.RestResponse;
import com.api.spring_restapi.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/roles")
@RestController
@CrossOrigin("*")
public class RoleController {
    //Create constructor injection
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    //get list roles
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getRoles(){
        List<Role> roles = roleService.findAll();
        return new ResponseEntity<>(
                new RestResponse.Success()
//                        .addDatas(roles)
                        .addDatas(roles.stream().map(RoleMapper.INSTANCE::roleToRoleDTO).collect(Collectors.toList()))
                        .build()
                , HttpStatus.OK);
    }
    //create role
    @RequestMapping(method = RequestMethod.POST,value = "/add")
    public ResponseEntity<?> createRole(@RequestBody @Valid Role Role){
        return new ResponseEntity<>(
                new RestResponse.Success()
                        .addData(roleService.create(Role))
                        .build()
                ,HttpStatus.OK);
    }
    //delete role
    @RequestMapping(method = RequestMethod.DELETE,value = "/delete/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(
                new RestResponse.Success()
                        .addData(roleService.delete(id))
                        .build()
                ,HttpStatus.OK);
    }
    //update name role
    @RequestMapping(method = RequestMethod.PUT,value = "/edit/{id}")
    public ResponseEntity<?> updateNameRole(
            @PathVariable Integer id,
            @RequestBody String name)
    {
        return new ResponseEntity<>(
                new RestResponse.Success()
                        .addData(roleService.editName(id,name))
                        .build()
                ,HttpStatus.OK);
    }
}
