<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Editar Categoria</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="/assets/external-libs/bootstrap/css/bootstrap.min.css">
</head>

<body>
<div class="container">
    <section class="panel panel-primary vertical-space">
        <div class="panel-heading">
            <h1>Editar categoria</h1>
        </div>

        <form:form modelAttribute="newCategoryForm" cssClass="form-horizontal panel-body" action="/admin/category/edit/${categoryId}" method="post">
            <div class="form-group">
                <label for="newCategory-name" class="col-md-2 control-label">Nome:</label>
                <div class="col-md-10">
                    <form:input path="name" id="newCategory-name" cssClass="form-control" required="required"/>
                    <form:errors path="name" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label for="newCategory-code" class="col-md-2 control-label">Código:</label>
                <div class="col-md-10">
                    <form:input path="code" id="newCategory-code" cssClass="form-control" required="required"/>
                    <form:errors path="code" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label for="newCategory-order" class="col-md-2 control-label">Ordem:</label>
                <div class="col-md-10">
                    <form:input path="order" type="number" min="1" id="newCategory-order" cssClass="form-control" required="required"/>
                    <form:errors path="order" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label for="newCategory-color" class="col-md-2 control-label">Cor:</label>
                <div class="col-md-10">
                    <form:input path="color" id="newCategory-color" cssClass="form-control" required="required"/>
                    <form:errors path="color" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label for="description" class="col-md-2 control-label">Descrição:</label>
                <div class="col-md-10">
                    <form:textarea path="description" id="description" cssClass="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <label for="iconPath" class="col-md-2 control-label">Caminho do Ícone:</label>
                <div class="col-md-10">
                    <form:input path="iconPath" id="iconPath" cssClass="form-control"/>
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