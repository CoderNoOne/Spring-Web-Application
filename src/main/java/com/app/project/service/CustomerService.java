package com.app.project.service;

import com.app.project.dto.CustomerDto;
import com.app.project.mapper.CustomerMapper;
import com.app.project.model.entity.Customer;
import com.app.project.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomerService {

  private CustomerRepository customerRepository;
  private CustomerMapper customerMapper;

  @Autowired
  public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
    this.customerRepository = customerRepository;
    this.customerMapper = customerMapper;
  }

  public void saveCustomerIntoDb(Customer customer) {

    customerRepository.save(customer);
  }

  public boolean checkIfCustomerIsInDb(Customer customer) {
    return Objects.nonNull(customerRepository.findCustomerByEmailAddress(customer.getEmailAddress()));
  }

  public CustomerDto getUserByEmail(String email){
    return customerMapper.mapCustomerToCustomerDto(customerRepository.findCustomerByEmailAddress(email));
  }

  public Integer getTotalNumberOfRegisteredCustomers(){
    return customerRepository.findAll().size();
  }

  public void deleteCustomerById(Integer id) {

    customerRepository.deleteById(id);
  }
}
