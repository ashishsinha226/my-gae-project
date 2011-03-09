package com.app.client;

import java.util.List;

import com.app.shared.UserBean;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("/")
public interface Service extends RemoteService {
    
    String addUser(UserBean userBean) throws IllegalArgumentException;
    List<UserBean> searchUser(String searchType, String searchInput) throws IllegalArgumentException;
    String deleteUser(String input) throws IllegalArgumentException;
    String updateUser(String input) throws IllegalArgumentException;
}