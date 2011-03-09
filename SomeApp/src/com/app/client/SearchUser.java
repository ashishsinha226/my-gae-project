package com.app.client;

import java.util.List;

import com.app.shared.UserBean;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SearchUser implements EntryPoint {
    
    private final ServiceAsync searchUserService = GWT.create(Service.class);
    
    @Override
    public void onModuleLoad() {
        final VerticalPanel mainPanel = new VerticalPanel();
        
        Grid formGrid = new Grid(10,2);

        final TextBox searchValue = new TextBox();
        Label searchTypeLabel = new Label("Select the search type");
        formGrid.setWidget(7, 0, searchTypeLabel);
        final ListBox searchType = new ListBox();
        searchType.addItem("All", "all");
        searchType.addItem("By name", "name");
        searchType.addItem("By email id", "email");
        searchType.addItem("By user type", "type");
        searchType.addItem("By user id", "id");
        formGrid.setWidget(7, 1, searchType);
        
        Label searchInputLabel = new Label("Enter the value");
        formGrid.setWidget(8, 0, searchInputLabel);
        formGrid.setWidget(8, 1, searchValue);

        
        mainPanel.add(formGrid);
        
        Button searchUser = new Button("New Button");
        searchUser.setText("Search user");
        
        ClickHandler searchHandler = new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                AsyncCallback<List<UserBean>> callback = new AsyncCallback<List<UserBean>>() {
                    
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert("Error message :: " + caught.getMessage());
                    }

                    @Override
                    public void onSuccess(List<UserBean> result) {
                       for (UserBean userBean : result) {   
                       Grid resultGrid = new Grid(8,2);
                       Label resUserNameLabel = new Label("User Name");
                       resultGrid.setWidget(1, 0, resUserNameLabel);
                       Label resUserNameValue = new Label(userBean.getUserName());
                       resultGrid.setWidget(1, 1, resUserNameValue);                    
                       mainPanel.add(resultGrid);
                       }
                    }
                };;;
                searchUserService.searchUser(searchType.getValue(searchType.getSelectedIndex()), searchValue.getValue(), callback);
            }
        };;;
        searchUser.addClickHandler(searchHandler);
        
        mainPanel.add(searchUser);
        
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
        
        
        RootPanel.get("content-search-user-page").add(mainPanel);
    }   
}