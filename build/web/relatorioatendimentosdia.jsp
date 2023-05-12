<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>RelatÃ³rio de Atendimentos em Aberto Por Data</title>
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
                    <a href="portalgerente.html" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto text-white text-decoration-none">
                      <img src="img/BEIBE-logos.jpeg" class="sidebarImage" />
                      <span class="fs-4 navbar-brand">BEIBE</span>
                    </a>
                    <hr>
                    <ul class="nav nav-pills flex-column mb-auto">
                      <li class="nav-item">
                        <a href="portalgerente.html" class="nav-link text-white" aria-current="page">
                          Dashboard
                        </a>
                      </li>
                      <li>
                        <a href="listaatendimentosgerente.html" class="nav-link text-white">
                          Atendimentos
                        </a>
                      </li>
                      <li>
                        <a href="listaatendimentosabertosgerente.html" class="nav-link text-white">
                          Atendimentos Abertos
                        </a>
                      </li>
                      <li>
                        <a href="cadastrarfuncionario.html" class="nav-link text-white">
                          Cadastro de FuncionÃ¡rio
                        </a>
                      </li>
                      <li>
                        <a href="relatorios.html" class="nav-link active">
                          RelatÃ³rios
                        </a>
                      </li>
                    </ul>
                    <hr>
                    <a href="index.html" class="nav-link text-white text-right">Logout</a>
                  </div>
          <!-- sidebar ends -->

          <!-- Page content-->
                <div class="container-fluid">
                    <h1 class="mt-4">RelatÃ³rio de Atendimentos em Aberto Por Data</h1>
                    <p><a href="docs/PDF-teste.pdf" download="PDF-teste.pdf">Download</a></p>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
