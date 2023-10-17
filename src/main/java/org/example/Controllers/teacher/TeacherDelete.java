package org.example.Controllers.teacher;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.services.TeacherService;
import org.example.services.servicesImpl.TeacherServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;


@WebServlet("/teacherdelete")
public class TeacherDelete extends HttpServlet {
    @Inject
    private TeacherService service;
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String idString = req.getParameter("id_teacher2");
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
                out.println(" <h1>Docente encontrado!</h1>");
                out.println(" <h3>El docente con id " + id + " fue eliminado :  " + service.list() + "</h3>");
                out.println(" </body>");
                out.println("</html>");
            }
        } catch(Exception e){
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No existe un docente con este id");

        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
    }
}
