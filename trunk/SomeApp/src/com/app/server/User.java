package com.app.server;

import java.io.Serializable;

import javax.persistence.Id;

public class User implements Serializable{
    
    private static final long serialVersionUID = 1L;
    @Id
    private Long userId;
    private String userName;
    private String password;
    private String email;
    private String userType;
    private boolean isActive;
    
    public User(){}

    public User(String userName, String password, String email, String userType, boolean isActive) {
        super();
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userType = userType;
        this.isActive = isActive;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    } 
}