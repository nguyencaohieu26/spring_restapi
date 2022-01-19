package com.api.spring_restapi.Mapper;


import com.api.spring_restapi.DTO.RoleDTO;
import com.api.spring_restapi.Entity.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class RoleMapperTest {

    @Test
    @DisplayName("Is Role Mapper Running!")
    void isRoleMapperRun(){
        //given
        Role role = new Role(1,"USER_ROLE",false,null);
        //when
        RoleDTO roleDTO = RoleMapper.INSTANCE.roleToRoleDTO(role);
        //then
        assertThat(roleDTO.getId()).isEqualTo(role.getId());
        assertThat(roleDTO.getName()).isEqualTo(role.getName());
    }
}