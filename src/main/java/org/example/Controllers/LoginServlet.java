package org.example.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.LoginService;
import services.servicesImpl.LoginServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Optional;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    final static String USERNAME = "admin";
    final static String PASSWORD = "12345";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("pass");
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            resp.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Login correcto</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Login correcto!</h1>");
                out.println(" <h3>Hola " + username + " has iniciado sesión con éxito!</h3>");
                out.println(" </body>");
                out.println("</html>");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado" +
                    "para ingresar a esta página!");
        }
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            Cookie usernameCookie = new Cookie("username", username);
            resp.addCookie(usernameCookie);
            resp.sendRedirect(req.getContextPath() + "/login.html");
        } else {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Lo sentimos no esta autorizado para ingresar a esta página!");
        }
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        String languagePreference = "Inglés";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("language")) {
                    languagePreference = cookie.getValue();
                    break;
                }
            }
        }

        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println(" <head>");
            out.println(" <meta charset=\"UTF-8\">");
            out.println(" <title>Bienvenido</title>");
            out.println(" </head>");
            out.println(" <body>");
            out.println(" <h1>Bienvenido</h1>");
            out.println(" <h3>Idioma preferido: " + languagePreference + "</h3>");
            out.println(" </body>");
            out.println("</html>");
        }
    }
}


