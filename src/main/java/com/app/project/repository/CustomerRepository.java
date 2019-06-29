package com.app.project.repository;

import com.app.project.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

  Customer findCustomerByEmailAddress(String email);

  Customer findCustomerByEmailAddressAndPassword(String email, String password);

  Customer findCustomerById(Integer id);
}
