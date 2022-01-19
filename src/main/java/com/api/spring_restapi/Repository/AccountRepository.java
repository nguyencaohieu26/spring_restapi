package com.api.spring_restapi.Repository;

import com.api.spring_restapi.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findAccountByUsername(String username);

}
