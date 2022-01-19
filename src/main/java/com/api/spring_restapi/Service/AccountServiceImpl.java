package com.api.spring_restapi.Service;

import com.api.spring_restapi.DTO.AccountDTO;
import com.api.spring_restapi.Entity.Account;
import com.api.spring_restapi.Entity.Role;
import com.api.spring_restapi.Exceptions.NotFoundException;
import com.api.spring_restapi.Mapper.AccountMapper;
import com.api.spring_restapi.Repository.AccountRepository;
import com.api.spring_restapi.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService{

    //Create constructor injection
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository1){
        this.accountRepository = accountRepository1;
    }

    @Autowired
    RoleRepository roleRepository;

   @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<AccountDTO> findAll() {
        List<Account> list = accountRepository.findAll();
        //change role name
        return  list.stream().map(
                AccountMapper.INSTANCE::accountToAccountDTO
        ).collect(Collectors.toList());
    }

    @Override
    public AccountDTO findById(Integer id) {
        Optional<?> account = accountRepository.findById(id);
        if(!account.isPresent()){
            throw new NotFoundException("Account Is Not Found!");
        }else{
            Account accountExist = accountRepository.findById(id).get();
            return AccountMapper.INSTANCE.accountToAccountDTO(accountExist);
        }
    }

    @Override
    public Account edit(Integer id, Account account) {
        return null;
    }

    @Override
    public void delete(Integer id) {
        Optional<Account> accountExist = accountRepository.findById(id);
        if(!accountExist.isPresent()){
            throw new NotFoundException("Account Is Not Found!");
        }
        accountRepository.deleteById(id);
    }

    @Override
    public Account save(Account account) {
        //format password
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        //save default user role
        account.addRole(roleRepository.findById(8).get());
        return accountRepository.save(account);
    }

    @Override
    public Account addRole(Integer accountID, Integer roleID) {
        Optional<Role> roleExist        = roleRepository.findById(roleID);
        Optional<Account> accountExist  = accountRepository.findById(accountID);
        if(!roleExist.isPresent()){
            throw new NotFoundException("Role Is Not Found");
        }
        if(!accountExist.isPresent()){
            throw new NotFoundException("Account Is Not Found");
        }
        //get role and account
        Role role       = roleExist.get();
        Account account = accountExist.get();
        //add role
        account.addRole(role);
        return accountRepository.save(account);
    }


}
