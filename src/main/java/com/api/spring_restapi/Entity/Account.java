package com.api.spring_restapi.Entity;

import com.api.spring_restapi.Enum.Gender;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
@SQLDelete(sql = "UPDATE accounts SET deleted = true WHERE id=?") @Where(clause = "deleted = false")
public class Account extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Info account login
    @NotNull(message = "Username is required")
    @Column(name = "user_name",columnDefinition = "TEXT")
    private String username;

    @NotNull(message = "Password is required")
    @Column(name ="pass_word")
    private String password;

    //Info account
    @NotBlank(message = "Fullname is required")
    @Column(name = "name",columnDefinition = "TEXT",nullable = false)
    private String name;

    @NotNull(message = "Birthday is required")
    private Date birthday;

    @NotNull(message = "Phone is required")
    private String phone;

    @Email
    @NotNull(message = "Email is required")
    @Column(nullable = false,unique = true)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    private boolean deleted = Boolean.FALSE;

    //Relationship many to many with role
    @ManyToMany(fetch = FetchType.EAGER)
            @JoinTable(
                    name = "account_roles",
                    joinColumns = @JoinColumn(name = "account_id"),
                    inverseJoinColumns = @JoinColumn(name = "role_id")
            )
    Set<Role> roles = new HashSet<>();

    //method add role to account
    public void addRole(Role role){
        this.roles.add(role);
    }
    //method remove role
}
