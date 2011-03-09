package com.app.server;

import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;

import com.googlecode.objectify.ObjectifyService;

@SuppressWarnings("serial")
public class EnvSetupServlet extends HttpServlet {
    
    private static final Logger log = Logger.getLogger(EnvSetupServlet.class.getName());
    
    public void init() {
        log.info("***************************************************************************************");
        log.info("                              EnvSetupServlet started.");
        log.info("***************************************************************************************");
        registerAllEntities();
        log.info("***************************************************************************************");
        log.info("                              EnvSetupServlet ended.");
        log.info("***************************************************************************************");
    }
    
    private void registerAllEntities() {
        ObjectifyService.register(User.class);
        log.info("User entity class registered.");
    }
}