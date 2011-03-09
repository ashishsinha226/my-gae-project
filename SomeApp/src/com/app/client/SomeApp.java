package com.app.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SomeApp implements EntryPoint {
    
    public void onModuleLoad() {

        VerticalPanel mainPanel = new VerticalPanel();
        Grid fillerGrid = new Grid(4, 1);
        CaptionPanel panel = new CaptionPanel();
        panel.setCaptionText("User management system");
        
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        panel.setContentWidget(verticalPanel);
        
        Grid fillerCaptionGrid = new Grid(1,1);
        verticalPanel.add(fillerCaptionGrid);
        
        Label lblNewLabel = new Label("Welcome to the user management system");
        verticalPanel.add(lblNewLabel);
        
        Grid fillerCaptionGridOther = new Grid(2,1);
        verticalPanel.add(fillerCaptionGridOther);
        
        HorizontalPanel horizontalPanel = new HorizontalPanel();
        verticalPanel.add(horizontalPanel);
        
        Button addUserButton = new Button("New button");
        addUserButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Window.Location.assign("AddUser.html");
            }
        });
        addUserButton.setText("Add user");
        horizontalPanel.add(addUserButton);
        
        Button showUserButton = new Button("New button");
        showUserButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Window.Location.assign("SearchUser.html");
            }
        });
        showUserButton.setText("Find user");
        horizontalPanel.add(showUserButton);
        
        Button updateUserButton = new Button("New button");
        updateUserButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Window.Location.assign("UpdateUser.html");
            }
        });
        updateUserButton.setText("Update user");
        horizontalPanel.add(updateUserButton);
        
        Button deleteUserButton = new Button("New button");
        deleteUserButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Window.Location.assign("DeleteUser.html");
            }
        });
        deleteUserButton.setText("Delete User");
        horizontalPanel.add(deleteUserButton);
        
        Grid fillerPadGrid = new Grid(1,1);
        verticalPanel.add(fillerPadGrid);
        
        mainPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        mainPanel.add(fillerGrid);
        mainPanel.add(panel);
        RootPanel.get("content-main-page").add(mainPanel);
    }
}