<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<!-- TODO list
criar duas colunas na area do formulario de cadastro e colocar algum textinho
corrigir layout do form pra ficar mais facil de preencher  -->


<html>
    <head>
        <title>Home</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <link rel="stylesheet" href="css/style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    </head>
    <body>
        <nav class="navbar container-fluid">
            <a class="navbar-brand text-decoration-none" id="logo">
                <img class="d-inline-block navbar-image" src="img/BEIBE-logos.jpeg" alt="Logo Beibe"/>
                Central de Atendimento BEIBE
            </a>
            <a href="login.jsp" class="btn btn-primary" role="button">Login</a>
        </nav>
    <c:if test="${!empty requestScope.msg}" >
        <div class="alert alert-danger m-5 text-center" role="alert">
            <h1> ${requestScope.msg}</h1>
        </div>
    </c:if>
    <div class="carousel slide" data-ride="carousel">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img class="d-block w-100" src="img/indexImage.jpg" alt="First slide">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Novo(a) por aqui?</h5>
                    <p>Então faça seu cadastro abaixo!</p>
                    <form action="autocadastro.jsp" method="post">
                        <button type="submit" class="btn btn-dark">Cadastrar</button>
                    </form>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
