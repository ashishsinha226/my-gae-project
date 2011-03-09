package com.app.server;

import java.util.List;
import java.util.logging.Logger;

import com.app.client.Service;
import com.app.shared.UserBean;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;

@SuppressWarnings("serial")
public class ServiceImpl extends RemoteServiceServlet implements Service {
    
    private static final UserDao DAO = new UserDao();
    private static final Logger log = Logger.getLogger(ServiceImpl.class.getName());

    @Override
    public String deleteUser(String input) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String updateUser(String input) throws IllegalArgumentException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String addUser(UserBean userBean) throws IllegalArgumentException {
        Key<User> createUser = DAO.createUser(userBean);
        return Long.toString(createUser.getId());
    }

    @Override
    public List<UserBean> searchUser(String searchType, String searchInput) throws IllegalArgumentException {
        return DAO.searchUser(searchType, searchInput);
    }
}