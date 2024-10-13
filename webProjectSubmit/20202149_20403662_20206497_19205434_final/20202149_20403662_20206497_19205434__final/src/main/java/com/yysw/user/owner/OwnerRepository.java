package com.yysw.user.owner;

import com.yysw.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @Query("from Owner")
    User findByUsernameAndPasswd(String username, String passwd);
}
