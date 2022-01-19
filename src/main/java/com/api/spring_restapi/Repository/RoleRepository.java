package com.api.spring_restapi.Repository;

import com.api.spring_restapi.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    //Create query check role name exist
    @Query(value ="Select Count(r) from Role r where r.name = ?1")
    Integer selectExistRoleName(String name);
}
