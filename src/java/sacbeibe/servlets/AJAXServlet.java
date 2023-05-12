/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package sacbeibe.servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sacbeibe.beans.Cidade;
import sacbeibe.beans.Produto;
import sacbeibe.facade.ProdutoFacade;
import sacbeibe.facade.UsuarioFacade;

@WebServlet(name = "AJAXServlet", urlPatterns = {"/AJAXServlet"})
public class AJAXServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        List<Cidade> listaCidades;
        List<Produto> listaProdutos;

        if (action.equals("getCidade")) {
            String estado = request.getParameter("estadoId");
            int idEstado;

            //Converte o parametro recebido para int
            // e o utiliza como id do Estado para busca das cidades
            try {
                idEstado = Integer.parseInt(estado);
                System.out.println("estado: " + idEstado);
                listaCidades = UsuarioFacade.listarCidades(idEstado);
                // transforma o MAP em JSON
                String json = new Gson().toJson(listaCidades);
                // retorna o JSON
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            } catch (NumberFormatException ex) {
                request.setAttribute("javax.servlet.jsp.jspException", ex);
                request.setAttribute("javax.servlet.error.status_code", 400);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
                return;
            }
        }

        if (action.equals("getProduto")) {
            String categoria = request.getParameter("categoriaId");
            int idCategoria;

            //Converte o parametro recebido para int
            // e o utiliza como id da Categoria para busca dos produtos
            try {
                idCategoria = Integer.parseInt(categoria);
                System.out.println("categoria: " + idCategoria);
                listaProdutos = ProdutoFacade.BuscarTodosPorAtributo("idCategoria", idCategoria);
                // transforma o MAP em JSON
                String json = new Gson().toJson(listaProdutos);
                // retorna o JSON
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(json);
            } catch (NumberFormatException ex) {
                request.setAttribute("javax.servlet.jsp.jspException", ex);
                request.setAttribute("javax.servlet.error.status_code", 400);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                rd.forward(request, response);
                return;
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
