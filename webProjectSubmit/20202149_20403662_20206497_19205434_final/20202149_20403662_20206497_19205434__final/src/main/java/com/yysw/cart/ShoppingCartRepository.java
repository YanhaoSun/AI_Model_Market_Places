package com.yysw.cart;

import com.yysw.user.customer.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartItem, Long> {
    void deleteByIdAndCustomer(Long id, Customer customer);

    void deleteAllByCustomer(Customer customer);

}
