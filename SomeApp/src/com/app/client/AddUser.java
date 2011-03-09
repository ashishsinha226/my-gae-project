package com.app.client;

import com.app.shared.UserBean;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Grid;

public class AddUser implements EntryPoint {
    
    private final ServiceAsync addUserService = GWT.create(Service.class);
    
    @Override
    public void onModuleLoad() {
        VerticalPanel mainPanel = new VerticalPanel();
        mainPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        
        Grid fillerGrid = new Grid(1, 1);
        mainPanel.add(fillerGrid);
        
        Grid formGrid = new Grid(6,2);
        
        Label userNameLabel = new Label("User Name");
        formGrid.setWidget(1, 0, userNameLabel);
        final TextBox userName = new TextBox();
        formGrid.setWidget(1, 1, userName);
        
        Label passwordLabel = new Label("Password");
        formGrid.setWidget(2, 0, passwordLabel);
        final PasswordTextBox password = new PasswordTextBox();
        formGrid.setWidget(2, 1, password);
        
        Label emailLabel = new Label("Email");
        formGrid.setWidget(3, 0, emailLabel);
        final TextBox email = new TextBox();
        formGrid.setWidget(3, 1, email);
        
        Label userTypeLabel = new Label("User Type");
        formGrid.setWidget(4, 0, userTypeLabel);
        final ListBox userType = new ListBox();
        userType.addItem("Admin", "Admin");
        userType.addItem("User", "User");
        userType.addItem("Other", "Other");
        formGrid.setWidget(4, 1, userType);
        
        mainPanel.add(formGrid);
        Button addUserButton = new Button("New Button");
        addUserButton.setText("Add new user");
        ClickHandler handler = new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                AsyncCallback<String> callback = new AsyncCallback<String>() {
                    
                    @Override
                    public void onSuccess(String result) {
                        Window.alert("Server says :: " + result);
                    }
                    
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert("Error message :: " + caught.getMessage());
                    }
                };;;
                UserBean userBean = new UserBean();
                userBean.setUserName(userName.getValue());
                userBean.setPassword(password.getValue());
                userBean.setActive(true);
                userBean.setEmail(email.getValue());
                userBean.setUserType(userType.getItemText(userType.getSelectedIndex()));
                addUserService.addUser(userBean, callback);
            }
        };;;
        addUserButton.addClickHandler(handler);
        mainPanel.add(addUserButton);
        
        Button homeButton = new Button("New Button");
        homeButton.setText("Home");
        ClickHandler homeHandler = new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                Window.Location.assign("SomeApp.html");
            }
        };;;
        homeButton.addClickHandler(homeHandler);
        mainPanel.add(homeButton);
        
        RootPanel.get("content-add-user-page").add(mainPanel);
    }   
}