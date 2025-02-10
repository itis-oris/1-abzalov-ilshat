package com.heartoftatarstan.listeners;

import com.heartoftatarstan.utils.DatabaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    private static final Logger logger = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DatabaseConnection.getInstance();
        logger.info("Database connection pool initialized.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DatabaseConnection.getInstance().destroy();
        logger.info("Database connection pool destroyed.");
    }
}