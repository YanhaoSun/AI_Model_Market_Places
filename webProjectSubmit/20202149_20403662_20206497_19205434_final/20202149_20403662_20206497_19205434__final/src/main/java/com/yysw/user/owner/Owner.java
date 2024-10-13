package com.yysw.user.owner;

import com.yysw.user.User;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Owner")
public class Owner extends User implements Serializable {
    public Owner() {}

    public Owner(String username, String passwd) {
        super(username, passwd);
    }
}