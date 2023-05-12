<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="sacbeibe.beans.Estado"%>
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
            <title>Cadastrar Colaborador</title>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
            <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
            <link href="css/style.css" rel="stylesheet" />
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
            <script src="js/comboCidade.js" type="text/javascript"></script>
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
                    <h1>Alterar cadastro</h1>
                    <form action="ClienteServlet?action=atualizarCadastro" method="post">
                        <div class="form-label">
                            <label for="login">Login</label>
                            <input type="text" class="form-control" id="login" name="login" readonly value="<c:out value="${autenticado.login}"/>">
                        </div>
                        <div class="form-label">
                            <label for="nome">Nome Completo</label>
                            <input type="text" class="form-control" id="nome" name="nome" value="<c:out value="${autenticado.dados.nome}"/>">
                        </div>
                        <div class="form-label">
                            <label for="cpf">CPF</label>
                            <input type="text" class="form-control" id="cpf" name="cpf" readonly value="<c:out value="${autenticado.dados.cpf}"/>">
                        </div>
                        <div class="form-label">
                            <label for="email">E-mail</label>
                            <input type="email" class="form-control" id="email" name="email" readonly value="<c:out value="${autenticado.dados.email}"/>">
                        </div>
                        <div class="form-label">
                            <label for="InputPassword1">Nova Senha</label>
                            <input type="password" class="form-control" id="InputPassword1" name="InputPassword1">
                        </div>
                        <div class="form-label">
                            <label for="InputPassword1">Confirme a Nova Senha</label>
                            <input type="password" class="form-control" id="InputPassword2" name="InputPassword2" >
                        </div>
                        <div class="form-label">
                            <label for="logradouro">Endereço</label>
                            <input type="text" class="form-control" id="logradouro" name="logradouro" value="<c:out value="${autenticado.dados.rua}"/>">
                        </div>
                        <div class="form-label">
                            <label for="cep">CEP</label>
                            <input type="text" class="form-control" id="cep" name="cep" value="<c:out value="${autenticado.dados.cep}"/>">
                        </div>
                        <div class="form-label">
                            <label for="numero">Número</label>
                            <input type="text" class="form-control" id="numero" name="numero" value="<c:out value="${autenticado.dados.numeroPredial}"/>">
                        </div>
                        <div class="form-label">
                            <label for="complemento">Complemento</label>
                            <input type="text" class="form-control" id="complemento" name="complemento" value="<c:out value="${autenticado.dados.complemento}"/>">
                        </div>
                        <div class="form-label">
                            <label for="bairro">Bairro</label>
                            <input type="text" class="form-control" id="bairro" name="bairro" value="<c:out value="${autenticado.dados.bairro}"/>">
                        </div>
                        <div class="form-label">
                            <label for="estado">Estado:</label>
                            <select name="estado" id="estado">
                                <option value="" disabled selected>Selecione o Estado</option>
                                <c:forEach items="${listaEstados}" var="estado">
                                    <option value="${estado.id}">${estado.nome}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-label">
                            <label for="cidade">Cidade</label>
                            <select name="cidade" id="cidade"></select>
                        </div>
                        <button type="submit" class="btn btn-primary">Cadastrar</button>
                      
                        <a href="ClienteServlet" class="btn btn-primary" role="button">Voltar</a>                      
                    </form>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
