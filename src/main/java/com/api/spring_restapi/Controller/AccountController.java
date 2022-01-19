package com.api.spring_restapi.Controller;

import com.api.spring_restapi.DTO.AccountDTO;
import com.api.spring_restapi.Entity.Account;
import com.api.spring_restapi.Response.RestResponse;
import com.api.spring_restapi.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@CrossOrigin("*")
public class AccountController {
    //Create constructor injection
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    //get list account
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAccounts(){
        List<AccountDTO> list = accountService.findAll();
        if(list.size() == 0){
            return new ResponseEntity<>(
                    new RestResponse.SimpleError()
                            .setMessage("List Account Is Empty")
                            .build()
                    , HttpStatus.OK);
        }
        return new ResponseEntity<>(
                new RestResponse.Success()
                        .addDatas(list)
                        .build()
                ,HttpStatus.OK);

    }
    //get account by id
    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable(name = "id") Integer id){
        return new ResponseEntity<>(
                new RestResponse.Success()
                        .addData(accountService.findById(id))
                        .build()
                ,HttpStatus.OK);
    }

    //create account
    @RequestMapping(method = RequestMethod.POST,value = "/add")
    public ResponseEntity<?> createAccount(@Valid @RequestBody Account account){
        return new ResponseEntity<>(
                new RestResponse.Success()
                        .addData(accountService.save(account))
                        .build()
                ,HttpStatus.OK);
    }
    //update account
    @RequestMapping(method = RequestMethod.PUT,value = "/edit/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable(name="id") Integer id ,@Valid @RequestBody Account account){
        return new ResponseEntity<>(
                new RestResponse.Success()
                        .addData(accountService.edit(id,account))
                        .build()
                ,HttpStatus.OK);
    }
    //delete account
    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable(name="id") Integer id){
        return new ResponseEntity<>(
                new RestResponse.Success()
                        .addData("Delete Successfully")
                        .build()
                ,HttpStatus.OK);
    }
    //add role to account
    @RequestMapping(method = RequestMethod.PUT,value = "/{accountId}/addRole/{roleId}")
    public ResponseEntity<?> addRole(
            @PathVariable(name = "roleId") Integer roleId,
            @PathVariable(name = "accountId") Integer accountId
    ){
        return new ResponseEntity<>(
                new RestResponse.Success()
                        .addData(accountService.addRole(accountId,roleId))
                        .build()
                ,HttpStatus.OK);
    }
}
