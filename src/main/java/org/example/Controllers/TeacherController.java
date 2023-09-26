package org.example.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.mapping.dto.StudentDto;
import org.example.mapping.dto.TeacherDto;
import repository.repositoryImpl.StudentRespositoryLogicImpl;
import repository.repositoryImpl.SubjectRepositoryImpl;
import repository.repositoryImpl.TeacherRepositoryImpl;
import repository.repositoryImpl.TeacherRepositoryLogicImpl;
import services.StudentService;
import services.TeacherService;
import services.servicesImpl.SubjectServiceImpl;
import services.servicesImpl.TeacherServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "studentController", value = "/student-form")

public class TeacherController extends HttpServlet {

    public TeacherRepositoryImpl teacherRepository;
    public TeacherService service;


    private String message;

    public void init() {
        message = "Teachers";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Connection conn = (Connection) request.getAttribute("conn");
        teacherRepository = new TeacherRepositoryImpl(conn);
        service = new TeacherServiceImpl(conn);

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Teachers</h1>");
        out.println(service.list());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");
        teacherRepository = new TeacherRepositoryImpl(conn);
        service = new TeacherServiceImpl(conn);

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        TeacherDto teacher = new TeacherDto(1L, name, email);
        service.update(teacher);
        System.out.println(service.list());

        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Resultado form</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <h1>Resultado form!</h1>");

            out.println("        <ul>");
            out.println("            <li>Name: " + name + "</li>");
            out.println("            <li>Email: " + email + "</li>");
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }

    public void destroy() {
    }

}
