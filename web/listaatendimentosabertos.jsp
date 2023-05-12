<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="now" class="java.util.Date" />
<%@page import="sacbeibe.beans.Atendimento"%>
<%@page import="sacbeibe.beans.Usuario"%>
<%@page import="sacbeibe.beans.TipoAtendimento"%>
<c:if test="${empty sessionScope.autenticado}" >
    <c:set var="msg" value="Precisa fazer o login" scope="request" />
    <jsp:forward page="login.jsp" />
</c:if>
<!DOCTYPE html>
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
                    <a href="#" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
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
                    <h1 class="mt-4">Atendimentos em Aberto</h1>
                    ${atd.dataAtendimento.time}
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th>Tipo Atendimento</th>
                                <th>Cliente</th>
                                <th>Produto</th>
                                <th>Data</th>
                                <th>Status</th>
                                <th>Resolução</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.listAtendimento}" var="atd">

                                <c:choose>
                                    <c:when test="${(now.time - 604800000) lt atd.dataAtendimento.time}">
                                        <tr>
                                            <td><c:out value="${atd.tipoAtendimento.descricao}"/></td>
                                            <td><c:out value="${atd.cliente.dados.nome}"/></td>
                                            <td><c:out value="${atd.produto.nome}"/></td>
                                            <td><fmt:formatDate value="${atd.dataAtendimento}" pattern="dd/MM/yyyy"/></td>
                                            <td><c:out value="${atd.status}"/></td>
                                            <td><a href="FuncionarioServlet?action=resolverAtendimento&id=${atd.id}">Resolver</a></td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <tr class="table-danger">
                                            <td><c:out value="${atd.tipoAtendimento.descricao}"/></td>
                                            <td><c:out value="${atd.cliente.dados.nome}"/></td>
                                            <td><c:out value="${atd.produto.nome}"/></td>
                                            <td><fmt:formatDate value="${atd.dataAtendimento}" pattern="dd/MM/yyyy"/></td>
                                            <td><c:out value="${atd.status}"/></td>
                                            <td><a href="FuncionarioServlet?action=resolverAtendimento&id=${atd.id}}">Resolver</a></td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
