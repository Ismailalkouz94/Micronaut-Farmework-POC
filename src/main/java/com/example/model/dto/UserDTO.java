package com.example.model.dto;

import com.example.entity.User;
import com.example.model.UserAuthority;

import java.util.List;

public class UserDTO {
    private String userName;
    private String password;
    private String email;
    private List<UserAuthority> authority;

    public User toUser() {
        return new User(this.userName, this.password, this.email);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserAuthority> getAuthority() {
        return authority;
    }

    public void setAuthority(List<UserAuthority> authority) {
        this.authority = authority;
    }
}
