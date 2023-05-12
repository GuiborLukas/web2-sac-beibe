/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


$(document).ready(function () {
    $("#categoria").change(function () {
        getProdutos();
    });

//    $("#cpf").mask("999.999.999-99");
//    $("#cep").mask("99999-999");
//
//
//    $("#formCliente").submit(function () {
//        $("#cpf").unmask();
//        $("#cep").unmask();
//    });
});
function getProdutos() {
    var categoriaId = $("#categoria").val();
    var url = "AJAXServlet?action=getProduto";
    $.ajax({
        url: url, // URL da sua Servlet
        data: {
            categoriaId: categoriaId
        },
        dataType: 'json',
        success: function (data) {
            // Se sucesso, limpa e preenche a combo de produto
            //alert(JSON.stringify(data));
            $("#produto").empty();
            $.each(data, function (i, obj) {
                $("#produto").append('<option value=' + obj.id + '>' + obj.nome +
                        '</option>');
            });
        },
        error: function (request, textStatus, errorThrown) {
            alert(request.status + ', Error: ' + request.statusText);
        }
    });
}

function getCategorias() {
    var url = "AJAXServlet?action=getCategoria";
    $.get(url, function (responseJson) {
        $("#categoria").empty();
        $.each(responseJson, function (i, obj) {
            $("#categoria").append('<option value=' + obj.id + '>' + obj.nome +
                    '</option>');
        });
    });
}