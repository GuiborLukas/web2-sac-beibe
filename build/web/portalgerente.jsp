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
                <h1>Dashboard</h1>
      <div class="container-fluid">
          <h1 class="welcome-message">Seja bem-vindo, ${autenticado.dados.nome} </h1>
      <div class="row align-items-center">
          <div class="col">

            <div class=" col card mb-4">
              <div class="row">
                <div class="col-md-6 align-middle">
                  <h3 class="card-number">${totalAtd}</h3>
                </div>
                <div class="col-md-6">
                  <div class="card-body">
                    <h5 class="card-title align-middle">Total de Atendimentos</h5>
                  </div>
                </div>
              </div>
            </div>

          </div>
          <div class="col">

            <div class=" col card mb-4">
              <div class="row">
                <div class="col-md-6 align-middle">
                    <h3 class="card-number">${totalAtd}</h3>
                </div>
                <div class="col-md-6">
                  <div class="card-body">
                    <h5 class="card-title align-middle">Total de Atendimentos Abertos</h5>
                  </div>
                </div>
              </div>
            </div>

          </div>
          <div class="col">

            <div class=" col card mb-4">
              <div class="row">
                <div class="col-sm-6 align-middle">
                  <h3 class="card-number-sm">${totalAtdAbRec}/${totalAtdRec}(${totalAtdRec / totalAtdAbRec * 100}%)</h3>
                </div>
                <div class="col-md-6">
                  <div class="card-body">
                    <h5 class="card-title align-middle">Reclamações</h5>
                  </div>
                </div>
              </div>
            </div>

          </div>
        </div>
               <div class="row align-items-center">
          <div class="col">

            <div class=" col card mb-4">
              <div class="row">
                <div class="col-sm-6 align-middle">
                    <h3 class="card-number-sm">${totalAtdAbSug}/${totalAtdSug}(${totalAtdSug / totalAtdAbSug * 100}%)</h3>
                </div>
                <div class="col-md-6">
                  <div class="card-body">
                    <h5 class="card-title align-middle">Sugestões</h5>
                  </div>
                </div>
              </div>
            </div>

          </div>
          <div class="col">

            <div class=" col card mb-4">
              <div class="row">
                <div class="col-sm-6 align-middle">
                    <h3 class="card-number-sm">${totalAtdAbCrit}/${totalAtdCrit}(${totalAtdCrit / totalAtdAbCrit * 100}%)</h3>
                </div>
                <div class="col-md-6">
                  <div class="card-body">
                    <h5 class="card-title align-middle">Críticas</h5>
                  </div>
                </div>
              </div>
            </div>

          </div>
          <div class="col">

            <div class=" col card mb-4">
              <div class="row">
                <div class="col-sm-6 align-middle">
                    <h3 class="card-number-sm">${totalAtdAbProb}/${totalAtdProb}(${totalAtdProb / totalAtdAbProb * 100}%)</h3>
                </div>
                <div class="col-md-6">
                  <div class="card-body">
                    <h5 class="card-title align-middle">Problemas</h5>
                  </div>
                </div>
              </div>
            </div>

          </div>
        </div>
      </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
