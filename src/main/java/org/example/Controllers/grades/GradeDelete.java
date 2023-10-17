package org.example.Controllers.grades;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.services.GradesService;
import org.example.services.servicesImpl.GradesServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "gradeController", value = "/deletegrade")
public class GradeDelete extends HttpServlet {
    @Inject
    public GradesService service;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("h1>Nota eliminada</h1>");
        out.println(service.list());
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        String idString = req.getParameter("id_grades2");
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
                out.println(" <h1>Registro de notas eliminado</h1>");
                out.println(" <h3>El registro de notas con id "+id+" fue eliminado , aqui esta la lista :  "+ service.list() + "</h3>");
                out.println(" </body>");
                out.println("</html>");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No existe un estudiante con el id ingresado");
        }
    }
}
