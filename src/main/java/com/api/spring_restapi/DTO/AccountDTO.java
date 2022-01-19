package com.api.spring_restapi.DTO;

import com.api.spring_restapi.Entity.Role;
import com.api.spring_restapi.Enum.Gender;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
public class AccountDTO {
    private Integer id;
    private String name;
    private Date birthday;
    private String phone;
    private String email;
    private Gender gender;
    private Set<Role> roles;
}
