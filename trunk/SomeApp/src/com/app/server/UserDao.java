package com.app.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.app.shared.UserBean;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Query;

public class UserDao {
    
    private static final Logger log       = Logger.getLogger(UserDao.class.getName());
    private Objectify           objectify = ObjectifyService.begin();
    
    public Key<User> createUser(UserBean userBean) {
        User newUser = new User(userBean.getUserName(), userBean.getPassword(), userBean.getEmail(), userBean.getUserType(), userBean.isActive());
        Key<User> put = objectify.put(newUser);
        log.info("UserId is " + put.getId());
        return put;
    }
    
    public List<User> getUserByName(String userName) {
        Query<User> query = objectify.query(User.class).filter("userName", userName);
        return query.list();
    }
    
    public List<User> getUserByEmail(String email) {
        Query<User> query = objectify.query(User.class).filter("email", email);
        return query.list();
    }
    
    public List<User> getUserById(Long id) {
        Query<User> query = objectify.query(User.class).filter("userId", id);
        return query.list();
    }
    
    public void updateUser(User user) {
        objectify.put(user);
    }
    
    public void deleteUser(User user) {
        objectify.delete(user);
    }

    public List<UserBean> searchUser(String searchType, String searchInput) {
        List<UserBean> userBeanList = new ArrayList<UserBean>();
        if(searchType.equals("all")){
            List<User> userList = objectify.query(User.class).list(); 
            setUserBeanList(userBeanList, userList);
        }else if(searchType.equals("name")){
            Query<User> userList = objectify.query(User.class).filter("userName", searchInput);
            setUserBeanList(userBeanList, userList.list());
        }else if(searchType.equals("email")){
            Query<User> userList = objectify.query(User.class).filter("email", searchInput);
            setUserBeanList(userBeanList, userList.list());
        }else if(searchType.equals("type")){
            Query<User> userList = objectify.query(User.class).filter("userType", searchInput);
            setUserBeanList(userBeanList, userList.list());
        }else if(searchType.equals("id")){
            Query<User> userList = objectify.query(User.class).filter("userId", Long.parseLong(searchInput));
            setUserBeanList(userBeanList, userList.list());
        }
        return userBeanList;
    }

    private void setUserBeanList(List<UserBean> userBeanList, List<User> userList) {
        log.info("Size is " + userList.size());
        for (User user : userList) {
            UserBean userBean = new UserBean();
            userBean.setActive(user.isActive());
            userBean.setEmail(user.getEmail());
            userBean.setPassword(user.getPassword());
            userBean.setUserId(user.getUserId());
            userBean.setUserName(user.getUserName());
            userBean.setUserType(user.getUserType());
            userBeanList.add(userBean);
        }
    }
}