package com.api.spring_restapi.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@SQLDelete(sql = "UPDATE roles SET deleted = true WHERE id = ?")
@Where(clause = "deleted=false")
public class Role extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @NotBlank(message = "Role Name is required")
    private String name;

    private boolean deleted = Boolean.FALSE;

    //Relationship
    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    Set<Account> accounts = new HashSet<>();
}
