<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Cadastrar Novo Curso</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="/assets/external-libs/bootstrap/css/bootstrap.min.css">
</head>

<body>
<div class="container">
    <section class="panel panel-primary vertical-space">
        <div class="panel-heading">
            <h1>Cadastrar Novo Curso</h1>
        </div>

        <form:form modelAttribute="newCourseForm" cssClass="form-horizontal panel-body" action="/admin/course/new" method="post">
            <div class="form-group">
                <label for="name" class="col-md-2 control-label">Nome:</label>
                <div class="col-md-10">
                    <form:input path="name" id="name" cssClass="form-control" required="required"/>
                    <form:errors path="name" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label for="code" class="col-md-2 control-label">Código:</label>
                <div class="col-md-10">
                    <form:input path="code" id="code" cssClass="form-control" required="required"/>
                    <form:errors path="code" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label for="description" class="col-md-2 control-label">Descrição:</label>
                <div class="col-md-10">
                    <form:textarea path="description" id="description" cssClass="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <label for="instructorId" class="col-md-2 control-label">Instrutor:</label>
                <div class="col-md-10">
                    <form:select path="instructorId" id="instructorId" cssClass="form-control" required="required">
                        <option value="">Selecione o instrutor</option>
                        <c:forEach items="${instructors}" var="instructor">
                            <form:option value="${instructor.id}">${instructor.name}</form:option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="instructorId" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label for="categoryId" class="col-md-2 control-label">Categoria:</label>
                <div class="col-md-10">
                    <form:select path="categoryId" id="categoryId" cssClass="form-control" required="required">
                        <option value="">Selecione a categoria</option>
                        <c:forEach items="${categories}" var="category">
                            <form:option value="${category.id}">${category.name}</form:option>
                        </c:forEach>
                    </form:select>
                    <form:errors path="categoryId" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <input class="btn btn-success" type="submit" value="Salvar"/>
                </div>
            </div>
        </form:form>
    </section>
</div>
</body>
</html>