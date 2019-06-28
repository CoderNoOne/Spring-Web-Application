package com.app.project.repository;

import com.app.project.model.Customer;
import com.app.project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Set;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

  @Query(value = "select * from product where customer_id = :customerId", nativeQuery = true)
  List<Product> findAllProductsById(@Param("customerId") Integer customerId);


  @Query(value = "select p.customer from Product p where p.customer.emailAddress = :email")
  Customer getCustomerByCustomerEmail(@Param("email") String email);

  @Query(value = "select p.customer.id from Product p where p.customer.id = :customerId")
  Integer getCustomerByCustomerId(@Param("customerId") Integer id);

  @Query(value = "select p from Product as p where p.customer.emailAddress = :customerEmail")
  List<Product> findAllProductsByCustomerEmail(@Param("customerEmail") String email);

  List<Product> findAllProductsByCategory(String category);

  //dla wszystkich userow
  @Query(value = "select p.category, sum(p.price) from Product as p group by p.category")
  List<Object[]> findProductsByCategory();

  //dla zalogowanego
  @Query(value = "select category, sum(price) from product where customer_id =:customerId group by category ", nativeQuery = true)
  List<Object[]> findProductsByCategoryByClient(@Param("customerId") Integer customerId);


  @Query(value = "select product.category, product.price, product.customer_id, customer.first_name, customer.last_name, customer.email_address from product join customer on product.customer_id = customer.id", nativeQuery = true)
  List<Object[]> joinTablesResult();

  @Query(value = "select p.category, sum(p.price) from product as p join customer as c on p.customer_id = c.id where c.email_address = :emailAddress group by p.category", nativeQuery = true)
  List<Object[]> findProductsByCategoryByClientEmail(@Param("emailAddress") String email);
}
