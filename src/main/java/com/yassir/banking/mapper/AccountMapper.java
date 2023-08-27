package com.yassir.banking.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.mapstruct.MappingConstants;

import com.yassir.banking.dto.AccountDto;
import com.yassir.banking.entity.Account;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(source = "id", target = "accountId")
    @Mapping(source = "customer.id", target = "customerId")
    AccountDto fromEntityToDto(Account account);

    @InheritInverseConfiguration
    Account fromDtoToEntity(AccountDto accountDto);

}
