<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="/assets/css/login.css">
</head>
<body class="login-page">
<div class="container">
    <div class="login-box">
        <h2>Já estuda com a gente?</h2>
        <p>Faça seu login e boa aula!</p>
        <a href="/admin/categories" class="btn-login">ENTRAR</a>
    </div>

    <div class="courses">
        <h2>Ainda não estuda com a gente?</h2>
        <p>São mais de mil cursos nas seguintes áreas:</p>

        <div class="grid">
            <c:forEach items="${categoriesWithCourses}" var="categoryData">
                <div class="card">
                    <img src="${categoryData.iconPath()}" alt="" class="card-icon">
                    <h3>Escola_ ${categoryData.name().toUpperCase()}</h3>

                    <ul class="course-list">
                        <c:forEach items="${categoryData.courses()}" var="course">
                            <li>${course.name()}</li>
                        </c:forEach>

                        <c:if test="${empty categoryData.courses()}">
                            <li class="no-courses">Nenhum curso ativo no momento.</li>
                        </c:if>
                    </ul>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>