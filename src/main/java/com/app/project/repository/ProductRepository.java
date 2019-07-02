package com.app.project.repository;

import com.app.project.model.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

  @Query(value = "select p from Product as p where p.customer.emailAddress = :customerEmail")
  List<Product> findAllProductsByCustomerEmail(@Param("customerEmail") String email);

  @Query(value = "select p.category, sum(p.price) from Product as p group by p.category")
  List<Object[]> findProductsByCategory();

  @Query(value = "select product.category, product.price, product.customer_id, customer.first_name, customer.last_name, customer.email_address from product join customer on product.customer_id = customer.id", nativeQuery = true)
  List<Object[]> joinTablesResult();

  @Query(value = "select p.category, sum(p.price) from product as p join customer as c on p.customer_id = c.id where c.email_address = :emailAddress group by p.category", nativeQuery = true)
  List<Object[]> findProductsByCategoryByClientEmail(@Param("emailAddress") String email);
}
