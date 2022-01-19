package com.api.spring_restapi.Mapper;

import com.api.spring_restapi.DTO.RoleDTO;
import com.api.spring_restapi.Entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
    RoleDTO roleToRoleDTO(Role role);
}
