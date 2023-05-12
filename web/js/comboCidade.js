/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


$(document).ready(function () {
    $("#estado").change(function () {
        getCidades();
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
function getCidades() {
    var estadoId = $("#estado").val();
    var url = "AJAXServlet?action=getCidade";
    $.ajax({
        url: url, // URL da sua Servlet
        data: {
            estadoId: estadoId
        },
        dataType: 'json',
        success: function (data) {
            // Se sucesso, limpa e preenche a combo de cidade
            //alert(JSON.stringify(data));
            $("#cidade").empty();
            $.each(data, function (i, obj) {
                $("#cidade").append('<option value=' + obj.id + '>' + obj.nome +
                        '</option>');
            });
        },
        error: function (request, textStatus, errorThrown) {
            alert(request.status + ', Error: ' + request.statusText);
        }
    });
}

function getEstados() {
    var url = "AJAXServlet?action=getEstado";
    $.get(url, function (responseJson) {
        $("#estado").empty();
        $.each(responseJson, function (i, obj) {
            $("#estado").append('<option value=' + obj.id + '>' + obj.nome +
                    '</option>');
        });
    });
}