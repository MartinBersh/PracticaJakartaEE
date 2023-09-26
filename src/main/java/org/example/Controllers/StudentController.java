package org.example.Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.domain.Student;
import org.example.mapping.dto.StudentDto;
import org.example.mapping.mappers.StudentMapper;
import repository.repositoryImpl.StudentRepositoryImp;
import repository.repositoryImpl.StudentRespositoryLogicImpl;
import services.StudentService;
import services.servicesImpl.StudentServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "studentController", value = "/student-form")

public class StudentController extends HttpServlet {

    public StudentRepositoryImp studentRepository;
    public StudentService service;

    private String message;

    public void init() {
        message = "Students";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Connection conn = (Connection) request.getAttribute("conn");
        studentRepository = new StudentRepositoryImp(conn);
        service = new StudentServiceImpl(conn);

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Students</h1>");
        out.println(service.list());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");
        studentRepository = new StudentRepositoryImp(conn);
        service = new StudentServiceImpl(conn);
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String semester = req.getParameter("semester");
        String career = req.getParameter("career");
        Student student = Student.builder()
                .name(name)
                .email(email)
                .semester(semester)
                .career(career).build();
        StudentDto studentDto = StudentMapper.mapFrom(student);
        service.update(studentDto);
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
            out.println("            <li>Semester: " + semester + "</li>");
            out.println("            <li>Career: " + career + "</li>");
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }
}