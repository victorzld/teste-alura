<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Gerenciamento de Alunos</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="/assets/external-libs/bootstrap/css/bootstrap.min.css">
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h1>Alunos</h1>
                </div>
                <table class="panel-body table table-hover">
                    <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Email</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${students}" var="student">
                        <tr>
                            <td>${student.name}</td>
                            <td>${student.email}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="col-md-4">
            <section class="panel panel-primary">
                <div class="panel-heading">
                    <h1>Cadastrar Novo Aluno</h1>
                </div>
                <div class="panel-body">
                    <form:form modelAttribute="newStudentUserDTO" action="/admin/students/new" method="post">
                        <div class="form-group">
                            <label for="name">Nome:</label>
                            <form:input path="name" id="name" cssClass="form-control" required="required"/>
                            <form:errors path="name" cssClass="text-danger"/>
                        </div>

                        <div class="form-group">
                            <label for="email">Email:</label>
                            <form:input path="email" id="email" cssClass="form-control" required="required"/>
                            <form:errors path="email" cssClass="text-danger"/>
                        </div>

                        <div class="form-group">
                            <label for="password">Senha:</label>
                            <form:password path="password" id="password" cssClass="form-control" required="required"/>
                            <form:errors path="password" cssClass="text-danger"/>
                        </div>

                        <input class="btn btn-success" type="submit" value="Salvar"/>
                    </form:form>
                </div>
            </section>
        </div>
    </div>
</div>
</body>
</html>