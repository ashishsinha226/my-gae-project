package com.app.client;

import java.util.List;

import com.app.shared.UserBean;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ServiceAsync {
    void addUser(UserBean userBean, AsyncCallback<String> callback) throws IllegalArgumentException;
    void searchUser(String searchType, String searchInput, AsyncCallback<List<UserBean>> callback) throws IllegalArgumentException;
    void deleteUser(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
    void updateUser(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
}
