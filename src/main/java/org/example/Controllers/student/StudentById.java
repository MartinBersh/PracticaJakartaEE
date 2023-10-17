package org.example.Controllers.student;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mapping.dto.StudentDto;
import org.example.services.StudentService;


import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/byid")
public class StudentById extends HttpServlet {

    @Inject
    private StudentService service;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String idString = req.getParameter("id_student");
        try {
            Long id = Long.parseLong(idString);
            StudentDto student = service.byId(id);
            if (student != null) {
                try (PrintWriter out = resp.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println(" <head>");
                    out.println(" <meta charset=\"UTF-8\">");
                    out.println(" <title>Consulta por ID</title>");
                    out.println(" </head>");
                    out.println(" <body>");
                    out.println(" <h1>Estudiante encontrado!</h1>");
                    out.println(" <h3>Este es el estudiante con id "+id+" :  "+ student + "</h3>");
                    out.println(" </body>");
                    out.println("</html>");
                }
            } else {
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No existe un estudiante con este id");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "El 'id' ingresado no es un número válido.");
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("h1></h1>");
        out.println(service.list());
        out.println("</body></html>");
    }
}