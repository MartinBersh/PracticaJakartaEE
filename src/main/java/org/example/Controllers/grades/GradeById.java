package org.example.Controllers.grades;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mapping.dto.GradesDto;
import org.example.services.GradesService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet("/gradesbyid")
public class GradeById extends HttpServlet{

    @Inject
    private GradesService service;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String idString = req.getParameter("id_grades");
        try {
            Long id = Long.parseLong(idString);
            GradesDto grades = service.byId(id);
            if (grades != null) {
                try (PrintWriter out = resp.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println(" <head>");
                    out.println(" <meta charset=\"UTF-8\">");
                    out.println(" <title>Consulta por ID</title>");
                    out.println(" </head>");
                    out.println(" <body>");
                    out.println(" <h1>Estudiante encontrado!</h1>");
                    out.println(" <h3>Este es el estudiante con id "+id+" :  "+ grades + "</h3>");
                    out.println(" </body>");
                    out.println("</html>");
                }
            } else {
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No existen notas con este id");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "El 'id' ingresado no es un número válido.");
        }
    }
}
