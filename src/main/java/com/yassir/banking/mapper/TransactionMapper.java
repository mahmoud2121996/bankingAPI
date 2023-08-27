package com.yassir.banking.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import com.yassir.banking.dto.TransactionDto;
import com.yassir.banking.entity.Transaction;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransactionMapper {

	TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

	@Mapping(source = "id", target = "transactionId")
	@Mapping(source = "sender.id", target = "senderId")
	@Mapping(source = "reciver.id", target = "reciverId")
	TransactionDto fromEntityToDto(Transaction transaction);

	@InheritInverseConfiguration
	Transaction fromDtoToEntity(TransactionDto transactionDto);

	List<TransactionDto> fromEntityListToDtoList(List<Transaction> listOfTransactions);
}
