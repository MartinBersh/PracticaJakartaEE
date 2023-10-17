package org.example.Controllers.subject;


import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.domain.Teacher;
import org.example.mapping.dto.SubjectDto;

import org.example.mapping.dto.TeacherDto;
import org.example.mapping.mappers.TeacherMapper;
import org.example.services.SubjectService;
import org.example.services.servicesImpl.SubjectServiceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "subjectController", value = "/subject-form")

public class SubjectController extends HttpServlet {

    @Inject
    private SubjectService service;
    private String message;

    public void init(){
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");


        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("h1>Teachers</h1>");
        out.println(service.list());
        out.println("</body></html>");
    }

    protected  void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");

        String name = req.getParameter("name");
        List<String> errores = getErrors(name);
        Map<String,String> errorsmap = getErrors2(name);

        TeacherDto teacherDto = getTeacherByName(req.getParameter("teacher"));

        Teacher teach = TeacherMapper.mapFrom(teacherDto);

        if(errorsmap.isEmpty()) {

            service.update(SubjectDto.builder()
                    .name(name)
                    .teacher(teach)
                    .build());
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
                out.println("        </ul>");
                out.println("    </body>");
                out.println("</html>");
            }
        }else{
            req.setAttribute("errors", errores);
            req.setAttribute("errorsmap", errorsmap);
            getServletContext().getRequestDispatcher("/subject.jsp").forward(req, resp);
        }
    }

    private TeacherDto getTeacherByName(String teacher) {
        List<TeacherDto> teachers = (List<TeacherDto>)getServletContext().getAttribute("teachers");
        return teachers.stream()
                .filter(e->e.name().equalsIgnoreCase(teacher))
                .findFirst()
                .orElseGet(null);
    }


    private Map<String,String> getErrors2(String name) {
        Map<String,String> errors = new HashMap<>();
        if(name==null || name.isBlank()){
            errors.put("name","El nombre es requerido");
        }
        return errors;
    }
    private List<String> getErrors(String name)
    {
        List<String> errors = new ArrayList<String>();
        if(name==null ||name.isBlank()){
            errors.add("El nombre es requerido");
        }
        return errors;
    }
    public void destroy() {

    }
}
