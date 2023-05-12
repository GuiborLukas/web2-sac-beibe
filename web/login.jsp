<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
TODO LIST

- corrigir o alinhamento e a cor do texto
- aumentar a distancia entre os itens do formulario
- alinhar o botÃ£o a direita

-->
<html>
    <title>Login</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <link href="css/style.css" rel="stylesheet" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>
    <div class="container" id="loginPage">
        <div class="row">
            <div class="col firstCol">
                <div class=" row loginContent">
                    <div class="col-sm-6">
                        <h1 >Beauty Embuste Indústria de Beleza e Estética</h1>
                        <h5 class="text-right">Rua do Embuste, 1313</h5>
                        <h5 class="text-right">Curitiba - PR 13131-313</h5>
                        <h5 class="text-right">(41) 91313-1313</h5>
                    </div>
                    <div class="col-sm-6">
                        <img src="img/BEIBE-logos_white.png" class="loginIMG" />
                    </div>
                </div>

            </div>
            <div class="col">
                <form action="LoginServlet" method="post" class="loginContent">
                    <div class="form-label">
                        <label for="login">Login</label>
                        <input type="text" class="form-control" id="login" name="login" placeholder="Login">
                    </div>
                    <div class="form-label">
                        <label for="senha">Senha</label>
                        <input type="password" class="form-control" id="senha" name="senha" placeholder="Senha">
                    </div>
                    <button type="submit" class="btn btn-primary">Entrar</button>
                </form>
            </div>
        </div>

    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="js/scripts.js"></script>
</body>
</html>
