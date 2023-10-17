package org.example.Controllers.subject;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mapping.dto.SubjectDto;
import org.example.services.SubjectService;
import org.example.services.servicesImpl.SubjectServiceImpl;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet("/subjectbyid")
public class SubjectById extends HttpServlet {

    @Inject
    private SubjectService service;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String idString = req.getParameter("id_subject");
        try {
            Long id = Long.parseLong(idString);
            SubjectDto subject = service.byId(id);
            if (subject != null) {
                try (PrintWriter out = resp.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println(" <head>");
                    out.println(" <meta charset=\"UTF-8\">");
                    out.println(" <title>Consulta por ID</title>");
                    out.println(" </head>");
                    out.println(" <body>");
                    out.println(" <h1>Asignatura encontrada!</h1>");
                    out.println(" <h3>Este es la asignatura con id "+id+" :  "+ subject + "</h3>");
                    out.println(" </body>");
                    out.println("</html>");
                }
            } else {
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No existe una asignatura con este id");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "El 'id' ingresado no es un número válido.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
    }
}
