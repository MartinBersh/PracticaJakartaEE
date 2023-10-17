package org.example.Controllers.student;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.services.StudentService;
import org.example.services.servicesImpl.StudentServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet("/studentdelete")
public class StudentDelete extends HttpServlet {

    @Inject
    private StudentService service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String idString = req.getParameter("id_student2");
        try {
            Long id = Long.parseLong(idString);
            service.delete(id);
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Consulta por ID</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Estudiante encontrado!</h1>");
                out.println(" <h3>El estudiante con id "+id+" fue eliminado :  "+ service.list() + "</h3>");
                out.println(" </body>");
                out.println("</html>");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No existe un estudiante con el id ingresado");
        }
    }
}
