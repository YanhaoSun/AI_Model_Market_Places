package com.yysw.login;

import javax.validation.constraints.NotBlank;

public class RegisterInformation {
    @NotBlank
    private String username;

    @NotBlank
    private String passwd;

    private String adminKey;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getAdminKey() {
        return adminKey;
    }

    public void setAdminKey(String adminKey) {
        this.adminKey = adminKey;
    }
}
