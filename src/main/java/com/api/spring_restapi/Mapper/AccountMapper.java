package com.api.spring_restapi.Mapper;

import com.api.spring_restapi.DTO.AccountDTO;
import com.api.spring_restapi.Entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    AccountDTO accountToAccountDTO(Account account);
}
