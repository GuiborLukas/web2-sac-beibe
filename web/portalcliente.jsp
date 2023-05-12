<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>Portal do Cliente</title>
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
                <a href="portalcliente.html" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                    <img src="img/BEIBE-logos.jpeg" class="sidebarImage" />
                    <span class="fs-4 navbar-brand">BEIBE</span>
                </a>
                <hr>
                <ul class="nav nav-pills flex-column mb-auto">
                    <!--                      <li class="nav-item">
                                            <a href="portalgerente.html" class="nav-link active" aria-current="page">
                                              Dashboard
                                            </a>
                                          </li>
                                          <li>
                                            <a href="listaatendimentoscliente.html" class="nav-link text-white">
                                              Meus Atendimentos
                                            </a>
                                          </li>
                                          <li>
                                            <a href="criaratendimento.html" class="nav-link text-white">
                                              Solicitar Atendimento
                                            </a>
                                          </li>
                                          <li>
                                            <a href="alterardados.html" class="nav-link text-white">
                                              Meus Dados
                                            </a>
                                          </li>-->
                    ${sessionScope.menu}
                </ul>
                <hr>
                <a href="LogoutServlet" class="nav-link text-white text-right">Logout</a>
            </div>
            <!-- sidebar ends -->
            <!-- Page content-->
            <div class="container-fluid">
                <h1 class="welcome-message">Seja bem-vindo, ${autenticado.dados.nome}</h1>
                <div class="row align-items-center">
                    <div class="col">

                        <div class=" col card mb-4">
                            <div class="row">
                                <div class="col-md-6 align-middle">
                                    <h3 class="card-number">6</h3>
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title align-middle">Compras Realizadas</h5>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="col">

                        <div class=" col card mb-4">
                            <div class="row">
                                <div class="col-md-6 align-middle">
                                    <h3 class="card-number">2</h3>
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title align-middle">ReclamaÃ§Ãµes</h5>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="col">

                        <div class=" col card mb-4">
                            <div class="row">
                                <div class="col-md-6 align-middle">
                                    <h3 class="card-number">1</h3>
                                </div>
                                <div class="col-md-6">
                                    <div class="card-body">
                                        <h5 class="card-title align-middle">ReclamaÃ§Ã£o Aberta</h5>
                                    </div>
                                </div>
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
