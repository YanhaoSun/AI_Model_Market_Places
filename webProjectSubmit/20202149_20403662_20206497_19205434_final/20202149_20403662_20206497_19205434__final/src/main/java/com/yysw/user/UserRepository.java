package com.yysw.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
//    @Query("from User ")
    User findByUsernameAndPasswd(String username, String passwd);
    User findUserById(Long id);

    boolean existsUserByUsername(String username);

    boolean existsUserByUsernameAndPasswd(String username, String passwd);
}