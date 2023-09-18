<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Clases</title>
</head>
<body>
<h3><%= "Formulario Clases" %>
</h3>

<form action="subject-form" method="post">
  <div class="row mb-3">
    <label for="name" class="col-form-label col-sm-2">Name</label>
    <div class="col-sm-4"><input type="text" name="name" id="name" class="form-control"></div>
  </div>
  <div class="row mb-3">
    <label for="teacherName" class="col-form-label col-sm-2">Teacher</label>
    <div class="col-sm-4"><input type="text" name="teacherName" id="teacherName" class="form-control"></div>
  </div>
  <div class="row mb-3">
    <label for="teacherEmail" class="col-form-label col-sm-2">Teacher Email</label>
    <div class="col-sm-4"><input type="text" name="teacherEmail" id="teacherEmail" class="form-control"></div>
  </div>
  </div>
</form>
<br/>
<a href="subject-form">Vamos a SubjectController</a>
</body>
</html>
