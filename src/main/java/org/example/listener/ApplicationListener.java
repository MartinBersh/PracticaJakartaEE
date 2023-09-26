package org.example.listener;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionListener;

public class ApplicationListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("inicia la aplicación!");
        servletContext = sce.getServletContext();
        servletContext.setAttribute("mensaje", "Hola a todos desde la application!");
    }
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("inicializando el request!");
        sre.getServletRequest().setAttribute("mensaje", "guardando algún valor para el request");
    }
}


