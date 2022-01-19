package com.api.spring_restapi.Security;

import com.api.spring_restapi.Entity.Account;
import com.api.spring_restapi.Entity.Role;
import com.api.spring_restapi.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //find account user in database
        Account accountExist = accountRepository.findAccountByUsername(username);
        //throw error if exist
        if(accountExist == null) throw new UsernameNotFoundException("User Account Is Not Found!");
        //get list role
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role ro : accountExist.getRoles()){
            authorities.add(new SimpleGrantedAuthority(ro.getName()));
        }
        org.springframework.security.core.userdetails.User userSpring = new org.springframework.security.core.userdetails.User(accountExist.getUsername(),accountExist.getPassword(),authorities);
        return userSpring;
    }
}
