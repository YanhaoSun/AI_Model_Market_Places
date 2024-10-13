package com.yysw.user.customer;

import com.yysw.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("from Customer ")
    User findByUsernameAndPasswd(String username, String passwd);

    Customer findCustomerById(Long id);

}