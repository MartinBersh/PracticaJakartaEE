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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "gradeController", value = "/grades-form")
public class GradeController extends HttpServlet {

    @Inject
    public GradesService service;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("h1>Students</h1>");
        out.println(service.list());
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");

        String corte = req.getParameter("corte");
        List<String> errores = getErrors( corte);
        Map<String, String> errorsmap = getErrors2(corte);

        if (errorsmap.isEmpty()) {
            service.update(GradesDto.builder()
                    .corte(corte)
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
                out.println("            <li>Corte: " + corte + "</li>");
                out.println("        </ul>");
                out.println("    </body>");
                out.println("</html>");
            }
        } else {

            req.setAttribute("errors", errores);
            req.setAttribute("errorsmap", errorsmap);
            getServletContext().getRequestDispatcher("/grades.jsp").forward(req, resp);
        }
    }

    private Map<String, String> getErrors2( String corte) {
        Map<String, String> errors = new HashMap<>();
        if (corte == null || corte.isBlank()) {
            errors.put("corte", "El corte es requerido");
        }
        return errors;
    }

    private List<String> getErrors(String corte) {
        List<String> errors = new ArrayList<String>();
        if (corte == null || corte.isBlank()) {
            errors.add("El corte es requerido");
        }
        return errors;
    }
}
