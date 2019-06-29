package com.app.project.mapper;

import com.app.project.dto.CustomerDto;
import com.app.project.model.entity.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

  CustomerDto mapCustomerToCustomerDto(Customer customer);

  Customer mapCustomerDtoToCustomer(CustomerDto customerDto);
}
