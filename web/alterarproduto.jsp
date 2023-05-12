<%-- 
    Document   : alterarproduto
    Created on : 01/05/2022, 19:32:31
    Author     : Guilherme Janke
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alterar Produto</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" />
    </head>
    
    <body>
        <div class="container">
            
            <h1 class="mb-4 h-3 fw-normal ">Alterar Produto:</h1>
            
            <div class="form-floating">
                  <input type="text" class="form-control" id="id" placeholder="ID" name="id" value="<c:out value="${produto.id}"/>" readonly>
                <label for="id">ID</label>
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" id="nome" placeholder="Nome" name="nome" value="<c:out value="${produto.nome}"/>" required>
                <label for="descricao">Nome</label>
            </div>
            <div class="form-textarea">
                <textarea class="form-control" id="descricao" placeholder="Descrição" name="descricao" rows="5" required><c:out value="${produto.descricao}"/>  </textarea>
            </div>
            <div class="form-floating">
                <input type="text" class="form-control" id="peso" placeholder="Peso" name="peso" value="<c:out value="${produto.peso}"/>" required>
                <label for="descricao">Peso</label>
            </div>
            <div class="form-floating">
                <select class="form-select" id="categoria" placeholder="Categoria" name="categoria" required>
                    <c:forEach var="cat" items="${categoria}">
                        <option value="${cat.id}" 
                        <c:if test="${cat.id == produto.produtoCategoria.id}">
                                        selected
                        </c:if>
                        ><c:out value="${cat.descricao}"/>  </option>
                    </c:forEach>
                </select>
                <label for="categoria">Categoria</label>
            </div>
            
            <!Adicionar btn salvar e voltar> 
            
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
