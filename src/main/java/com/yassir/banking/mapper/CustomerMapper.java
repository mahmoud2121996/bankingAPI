package com.yassir.banking.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import com.yassir.banking.dto.CustomerDto;
import com.yassir.banking.entity.Customer;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {

	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

	CustomerDto fromEntityToDto(Customer customer);

	@InheritInverseConfiguration
	Customer fromDtoToEntity(CustomerDto customerDto);
}
