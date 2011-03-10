package com.app.client;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.app.shared.UserBean;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
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
        
        final Grid formGrid = new Grid(10, 2);
        
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
        
        final Button searchUser = new Button("New Button");
        searchUser.setText("Search user");
        
        final Button homeButton = new Button("New Button");
        
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
                        
                        Collections.sort(result, new NameComparator());
                        CellTable<UserBean> table = new CellTable<UserBean>();
                        formGrid.setVisible(false);
                        searchUser.setVisible(false);
                        homeButton.setVisible(false);
                        
                        
                        TextColumn<UserBean> nameColumn = new TextColumn<UserBean>() {
                            @Override
                            public String getValue(UserBean user) {
                                return user.getUserName();
                            }
                        };
                        
                        TextColumn<UserBean> idColumn = new TextColumn<UserBean>() {
                            @Override
                            public String getValue(UserBean user) {
                                return Long.toString(user.getUserId());
                            }
                        };
                        
                        TextColumn<UserBean> userTypeColumn = new TextColumn<UserBean>() {
                            @Override
                            public String getValue(UserBean user) {
                                return user.getUserType();
                            }
                        };
                        
                        TextColumn<UserBean> emailColumn = new TextColumn<UserBean>() {
                            @Override
                            public String getValue(UserBean user) {
                                return user.getEmail();
                            }
                        };
                        
                        table.addColumn(idColumn, "User Id");
                        table.addColumn(nameColumn, "Name");
                        table.addColumn(userTypeColumn, "User type");
                        table.addColumn(emailColumn, "Email Id");
                        table.setRowCount(result.size(), true);
                        table.setRowData(0, result);
                        mainPanel.add(table);
                        
                        
                        
                        final Button homeButtonNew = new Button("New Button");
                        homeButtonNew.setText("Home");
                        
                        ClickHandler homeNewHandler = new ClickHandler() {
                            
                            @Override
                            public void onClick(ClickEvent event) {
                                Window.Location.assign("SomeApp.html");
                            }
                        };
                        ;
                        ;
                        homeButtonNew.addClickHandler(homeNewHandler);
                        mainPanel.add(homeButtonNew);
                    }
                };
                ;
                ;
                searchUserService.searchUser(searchType.getValue(searchType.getSelectedIndex()),
                        searchValue.getValue(), callback);
            }
        };
        ;
        ;
        searchUser.addClickHandler(searchHandler);
        
        mainPanel.add(searchUser);
        
        
        homeButton.setText("Home");
        
        ClickHandler homeHandler = new ClickHandler() {
            
            @Override
            public void onClick(ClickEvent event) {
                Window.Location.assign("SomeApp.html");
            }
        };
        ;
        ;
        homeButton.addClickHandler(homeHandler);
        
        mainPanel.add(homeButton);
        
        RootPanel.get("content-search-user-page").add(mainPanel);
    }
    
    class NameComparator implements Comparator{
        
        public int compare(Object emp1, Object emp2){    
     
            //parameter are of type Object, so we have to downcast it to Employee objects
           
            String emp1Name = ((UserBean)emp1).getUserName();        
            String emp2Name = ((UserBean)emp2).getUserName();
           
            //uses compareTo method of String class to compare names of the employee
            return emp1Name.compareTo(emp2Name);
       
        }
     
    }
}