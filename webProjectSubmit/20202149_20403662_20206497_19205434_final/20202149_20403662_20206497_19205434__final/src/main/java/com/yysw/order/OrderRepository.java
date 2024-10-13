package com.yysw.order;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yysw.user.customer.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderById(Long id);
    List<Order> findOrderByCustomerOrderByOrderDateDesc(Customer customer);
    List<Order> findAllByOrderByOrderDateDescStateAsc();
}