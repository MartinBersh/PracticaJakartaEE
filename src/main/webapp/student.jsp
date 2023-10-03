
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.Map"%>
<%@ page import="java.util.List" %>
<%
    List<String> errores = (List<String>)request.getAttribute("errores");
%>
<%
    Map<String,String> errorsmap =
            (Map<String,String>)request.getAttribute("errorsmap");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Student CRUD</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<h3><%= "Formulario estudiantes" %>
</h3>
<form action="student-form" method="post">
    <div class="row mb-3">
        <label for="name" class="col-form-label col-sm-2">Name</label>
        <div class="col-sm-4"><input type="text" name="name" id="name" class="form-control"
                                     value="${param.name}"></div>
        <%
            if(errorsmap != null && errorsmap.containsKey("name")){
                out.println("<div class='row mb-3 alert alert-danger col-sm-4'" +
                        "style='color: red;'>"+ errorsmap.get("name") + "</div>");
            }
        %>
    </div>
    <div class="row mb-3">
        <label for="email" class="col-form-label col-sm-2">Email</label>
        <div class="col-sm-4"><input type="text" name="email" id="email" class="form-control"></div>
        <%
            if(errorsmap != null && errorsmap.containsKey("email")){
                out.println("<div class='row mb-3 alert alert-danger col-sm-4' " +
                        "style='color: red;'>"+ errorsmap.get("email") + "</div>");
            }
        %>
    </div>
    <div class="row mb-3">
        <label for="career" class="col-form-label col-sm-2">Career</label>
        <div class="col-sm-4">
            <select name="career" id="career" class="form-select">
                <option value="">-- Seleccionar --</option>
                <option value="Ingenieria de software" ${param.pais.equals("Ingenieria de software")? "selected":
                        ""}>Ingenieria de software</option>
                <option value="Ingenieria Industrial" ${param.pais.equals("Ingenieria Industrial")? "selected":
                        ""}>Ingenieria Industrial</option>
                <option value="Enfermeria" ${param.pais.equals("Enfermeria")? "selected":
                        ""}>Enfermeria</option>
                <option value="Medicina" ${param.pais.equals("Medicina")? "selected":
                        ""}>Medicina</option>
                <option value="Veterinaria" ${param.pais.equals("Veterinaria")? "selected":
                        ""}>Veterinaria</option>
                <option value="Derecho" ${param.pais.equals("Derecho")? "selected":
                        ""}>Derecho</option>
                <option value="Psicologia" ${param.pais.equals("Psicologia")? "selected":
                        ""}>Psicologia</option>
                <option value="Ingenieria Civil" ${param.pais.equals("Ingenieria Civil")? "selected":
                        ""}>Ingenieria Civil</option>
                <option value="Marketing Digital" ${param.pais.equals("Marketing Digital")? "selected":
                        ""}>Marketing Digital</option>
            </select>
        </div>
        <%
            if(errorsmap != null && errorsmap.containsKey("career")){
                out.println("<small class='alert alert-danger col-sm-4'" +
                        "style='color: red;'>"+ errorsmap.get("career") + "</small>");
            }
        %>
    </div>
    <div class="row mb-3">
        <label for="semester" class="col-form-label col-sm-2">Semester</label>
        <div class="col-sm-4">
            <select name="semester" id="semester" class="form-select">
                <option value="">-- Seleccionar --</option>
                <option value="1" ${param.pais.equals("1")? "selected":
                        ""}>1</option>
                <option value="2" ${param.pais.equals("2")? "selected":
                        ""}>2</option>
                <option value="3" ${param.pais.equals("3")? "selected":
                        ""}>3</option>
                <option value="4" ${param.pais.equals("4")? "selected":
                        ""}>4</option>
                <option value="5" ${param.pais.equals("5")? "selected":
                        ""}>5</option>
                <option value="6" ${param.pais.equals("6")? "selected":
                        ""}>6</option>
                <option value="7" ${param.pais.equals("7")? "selected":
                        ""}>7</option>
                <option value="8" ${param.pais.equals("8")? "selected":
                        ""}>8</option>
                <option value="9" ${param.pais.equals("9")? "selected":
                        ""}>9</option>
                <option value="10" ${param.pais.equals("10")? "selected":
                        ""}>10</option>
            </select>
            <%
                if(errorsmap != null && errorsmap.containsKey("semester")){
                    out.println("<small class='alert alert-danger col-sm-4'" +
                            "style='color: red;'>"+ errorsmap.get("semester") + "</small>");
                }
            %>
        </div>

        <div class="row mb-3">
            <div>
                <input type="submit" value="Actualizar" class="btn btn-primary">
            </div>
        </div>
</form>
<br/>
<h3><%= "Consultar por ID" %>
</h3>
<form action="byid" method="post">
    <div class="row mb-3">
        <label for="id_student" class="col-form-label col-sm-2">Id</label>
        <div class="col-sm-4"><input type="text" name="id_student" id="id_student" class="form-control"></div>
        <div class="row mb-3">
            <div>
                <input type="submit" value="Buscar" class="btn btn-primary">
            </div>
        </div>
    </div>
</form>
<br/>
<h3><%= "Eliminar por ID" %>
</h3>
<form action="studentdelete" method="post">
    <div class="row mb-3">
        <label for="id_student2" class="col-form-label col-sm-2">Id para eliminar</label>
        <div class="col-sm-4"><input type="text" name="id_student2" id="id_student2" class="form-control"></div>
        <div class="row mb-3">
            <div>
                <input type="submit" value="Eliminar" class="btn btn-primary">
            </div>
        </div>
    </div>
</form>
<br/>
<div>
    <h3><%= "Lista de estudiantes" %>
    </h3>
    <a href="student-form">Vamos a listar estudiantes</a>
</div>
</body>
</html>
