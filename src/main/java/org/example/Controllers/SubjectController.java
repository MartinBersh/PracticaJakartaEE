package org.example.Controllers;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.domain.Teacher;
import org.example.mapping.dto.SubjectDto;
import org.example.mapping.dto.TeacherDto;
import repository.repositoryImpl.SubjectRepositoryLogicImpl;
import repository.repositoryImpl.TeacherRepositoryLogicImpl;
import services.SubjectService;
import services.TeacherService;
import services.servicesImpl.SubjectServiceImpl;
import services.servicesImpl.TeacherServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "studentController", value = "/student-form")

public class SubjectController extends HttpServlet {

    public SubjectRepositoryLogicImpl subjectRepository;
    public SubjectService service;

    public SubjectController() {
        subjectRepository = new SubjectRepositoryLogicImpl();
        service = new SubjectServiceImpl(subjectRepository);
    }

    private String message;

    public void init() {
        message = "Subjects";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Subjects</h1>");
        out.println(service.list());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String name = req.getParameter("name");

        String nameTeacher = req.getParameter("nameTeacher");
        String email = req.getParameter("email");
        Teacher teacher = new Teacher(1L, nameTeacher, email);

        SubjectDto subject = new SubjectDto(1L, name, teacher);
        service.update(subject);
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
            out.println("            <li>Teacher: " + teacher + "</li>");
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }

    public void destroy() {
    }


}
