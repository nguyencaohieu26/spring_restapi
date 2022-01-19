package com.api.spring_restapi.Service;

import com.api.spring_restapi.DTO.AccountDTO;
import com.api.spring_restapi.Entity.Account;

import java.util.List;

public interface AccountService {
    List<AccountDTO> findAll();
    AccountDTO findById(Integer id);
    Account edit(Integer id,Account account);
    void delete(Integer id);
    Account save(Account account);
    Account addRole(Integer accountID, Integer roleID);
}
