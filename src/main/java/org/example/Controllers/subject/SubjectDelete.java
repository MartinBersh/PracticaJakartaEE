package org.example.Controllers.subject;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.services.SubjectService;
import org.example.services.servicesImpl.SubjectServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet("/subjectdelete")

public class SubjectDelete extends HttpServlet {

    @Inject
    private SubjectService service;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        String idString = req.getParameter("id_subject2");
        try {
            Long id = Long.parseLong(idString);
            service.delete(id);
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Eliminar por ID</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Asignatura eliminada</h1>");
                out.println(" <h3>La asignatura con id " + id + " fue eliminada :  " + service.list() + "</h3>");
                out.println(" </body>");
                out.println("</html>");
            }
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No existe una asignatura con este id");
        }
    }
}
