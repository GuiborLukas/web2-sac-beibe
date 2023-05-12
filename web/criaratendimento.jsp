<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="now" class="java.util.Date" />
<%@page import="sacbeibe.beans.Categoria"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<c:if test="${empty sessionScope.autenticado}" >
    <c:set var="msg" value="Precisa fazer o login" scope="request" />
    <jsp:forward page="login.jsp" />
</c:if>
<html>
    <!DOCTYPE html>
    <html>
        <head>
            <title>Novo Atendimento</title>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
            <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
            <link href="css/style.css" rel="stylesheet" />
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
            <script src="js/comboProduto.js" type="text/javascript"></script>
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
                    <h1 class="mt-4">Abrir Atendimento</h1>
                    <br>
                    <form action="ClienteServlet?action=gravarAtendimento" method="post">
                        <div class="form-label">
                        <label for="tipoAtendimento">Tipo do Atendimento</label>
                        <select class="custom-select" name="tipoAtendimento" id="tipoAtendimento">
                            <option value="" selected>Selecione o Tipo do Atendimento</option>
                            <c:forEach items="${listTipoAtendimento}" var="tipoAtendimento">
                                <option value="${tipoAtendimento.id}">${tipoAtendimento.descricao}</option>
                            </c:forEach>
                        </select>
                </div>
                        <div class="form-label">
                            <label for="categoria">Categoria do Produto</label>
                            <select class="custom-select" name="categoria" id="categoria">
                                <option value="" selected>Selecione a Categoria</option>
                                <c:forEach items="${listCategoria}" var="categoria">
                                    <option value="${categoria.id}">${categoria.descricao}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-label">
                            <label for="produto">Produto</label>
                            <select name="produto" id="produto">
                            </select>
                        </div>
                        <div class="form-group">
                            <textarea class="form-control" id="textAreaExample2" rows="10"  maxlength="2000" name="descricaoAtendimento" placeholder="Escreva os detalhes para o atendimento."></textarea>
                        </div>
                        <br>
                        <button type="submit" class="btn btn-primary">Salvar</button>
                    </form>
                        <br>
                    <div>
                        <c:choose>
                            <c:when test="${autenticado.tipo eq 1}">
                                <a href="<%=request.getContextPath()%>/ClienteServlet?action=listarTodosAtendimentos"><button class="btn btn-primary">Voltar</button></a>
                            </c:when>
                            <c:when test="${autenticado.tipo eq 2}">
                                <a href="<%=request.getContextPath()%>/FuncionarioServlet?action=listarTodosAtendimentos"><button class="btn btn-primary">Voltar</button></a>
                            </c:when>
                            <c:when test="${autenticado.tipo eq 3}">
                                <a href="<%=request.getContextPath()%>/GerenteServlet?action=listarTodosAtendimentos"><button class="btn btn-primary">Voltar</button></a>
                            </c:when>
                            <c:otherwise>
                                <a href="<%=request.getContextPath()%>/FuncionarioServlet?action=listarTodosAtendimentos"><button class="btn btn-primary">Voltar</button></a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
