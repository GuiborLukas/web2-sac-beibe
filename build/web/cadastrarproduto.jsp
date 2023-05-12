<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE html>
<c:if test="${empty sessionScope.autenticado}" >
    <c:set var="msg" value="Precisa fazer o login" scope="request" />
    <jsp:forward page="login.jsp" />
</c:if>
<html>
    <!DOCTYPE html>
    <html>
        <head>
            <title>Atendimentos em Aberto</title>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
            <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
            <link href="css/style.css" rel="stylesheet" />

        </head>
        <body>
            <div class="d-flex" id="wrapper">

                <!-- sidebar begin -->
                <div class="d-flex flex-column flex-shrink-0 p-3 text-whites sidebar" style="width: 350px;">
                    <a href="portalfuncionario.html" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                        <img src="img/BEIBE-logos.jpeg" class="sidebarImage" />
                        <span class="fs-4 navbar-brand">BEIBE</span>
                    </a>
                    <hr>
                    <ul class="nav nav-pills flex-column mb-auto">
                        ${sessionScope.menu}
                    </ul>
                    <hr>
                    <!-- ADD LOGOUT PAGE -->
                    <a href="LogoutServlet" class="nav-link text-white text-right">Logout</a>
                </div>
                <!-- sidebar ends -->
                <!-- Page content-->
                <div class="container-fluid">
                    <h1 class="mt-4">Cadastrar Produto</h1>
                    <form action="FuncionarioServlet?action=cadastrarProduto" method="post">
                        <div class="form-label">
                            <label for="categoria">Categoria do Produto</label>
                            <select class="custom-select" name="categoria" id="categoria">
                                <option value="" selected>Selecione a Categoria</option>
                                <c:forEach items="${listCategoria}" var="categoria">
                                    <option value="${categoria.id}">${categoria.descricao}</option>
                                </c:forEach>
                            </select>
                            <br>
                            <label for="nome">Nome do Produto</label>
                            <input type="text" class="form-control" id="nome" name="nome">
                            <label for="descricao">Descrição do Produto</label>
                            <input type="text" class="form-control" id="descricao" name="descricao">
                            <label for="peso">Peso do Produto (em gramas)</label>
                            <input type="text" class="form-control" id="peso" name="peso">
                        </div>
                        <button type="submit" class="btn btn-primary">Cadastrar</button>
                    </form>
                    <br>
                    <h1 class="mt-4">Produtos registrados</h1>
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th>Id Produto</th>
                                <th>Nome Produto</th>
                                <th>Categoria</th>
                                <th>Descrição</th>
                                <th>Peso</th>
                            </tr>
                        </thead>
                        <c:forEach var="produto" items="${requestScope.listProduto}">
                            <tr>
                                <td>${produto.id}</td>
                                <td>${produto.nome}</td>
                                <td>${produto.categoria.descricao}</td>
                                <td>${produto.descricao}</td>
                                <td>${produto.peso}</td>
                            </tr>
                        </c:forEach>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
