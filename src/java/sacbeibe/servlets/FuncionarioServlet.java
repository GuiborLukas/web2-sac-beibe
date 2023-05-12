package sacbeibe.servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sacbeibe.facade.*;
import sacbeibe.beans.Atendimento;
import sacbeibe.beans.Categoria;
import sacbeibe.beans.Produto;
import sacbeibe.beans.Usuario;

@WebServlet(name = "FuncionarioServlet", urlPatterns = {"/FuncionarioServlet"})
public class FuncionarioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        if (session.getAttribute("autenticado") == null) {
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema!");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        }
        Usuario user = (Usuario) session.getAttribute("autenticado");
        String action = "";
        if (request.getParameter("action") != null) {
            action = request.getParameter("action");
        }

        switch (action) {
            case "cadastroProduto": {
                List<Produto> listProduto = ProdutoFacade.BuscarTodos();
                request.setAttribute("listProduto", listProduto);
                List<Categoria> listCategoria = CategoriaFacade.BuscarTodos();
                request.setAttribute("listCategoria", listCategoria);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastrarproduto.jsp");
                rd.forward(request, response);
                break;
            }
            case "cadastrarProduto": {
                Produto produto = new Produto();

                int idCtg = Integer.parseInt(request.getParameter("categoria"));
                int peso = Integer.parseInt(request.getParameter("peso"));
                String nome = (String) request.getParameter("nome");
                String descricao = (String) request.getParameter("descricao");
                produto.setIdCategoria(idCtg);
                produto.setPeso(peso);
                produto.setNome(nome);
                produto.setDescricao(descricao);
                ProdutoFacade.Inserir(produto);
                List<Produto> listProduto = ProdutoFacade.BuscarTodos();
                request.setAttribute("listProduto", listProduto);
                List<Categoria> listCategoria = CategoriaFacade.BuscarTodos();
                request.setAttribute("listCategoria", listCategoria);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastrarproduto.jsp");
                rd.forward(request, response);
                break;
            }
            case "cadastroCategoria": {
                List<Categoria> listCategoria = CategoriaFacade.BuscarTodos();
                request.setAttribute("listCategoria", listCategoria);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastrarcategoria.jsp");
                rd.forward(request, response);
                break;
            }
            case "cadastrarCategoria": {
                Categoria categoria = new Categoria();
                String nome = (String) request.getParameter("nome");
                categoria.setDescricao(nome);
                CategoriaFacade.Inserir(categoria);
                List<Categoria> listCategoria = CategoriaFacade.BuscarTodos();
                request.setAttribute("listCategoria", listCategoria);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastrarcategoria.jsp");
                rd.forward(request, response);
                break;
            }
            case "listarTodosAtendimentos": {
                List<Atendimento> listAtendimento = AtendimentoFacade.BuscarTodos();
                Collections.sort(listAtendimento);
                request.setAttribute("listAtendimento", listAtendimento);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/listaatendimentos.jsp");
                rd.forward(request, response);
                break;
            }
            case "resolverAtendimento": {
                int idAtd = Integer.parseInt(request.getParameter("id"));
                Atendimento atd = AtendimentoFacade.BuscarPorId(idAtd);
                request.setAttribute("atd", atd);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/resolucaoatendimento.jsp");
                rd.forward(request, response);
                break;
            }
            case "visualizarAtendimento": {
                int idAtd = Integer.parseInt(request.getParameter("id"));
                Atendimento atd = AtendimentoFacade.BuscarPorId(idAtd);
                request.setAttribute("atd", atd);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/visualizaratendimento.jsp");
                rd.forward(request, response);
                break;
            }
            case "gravarAtendimento": {
                int idAtd = Integer.parseInt(request.getParameter("id"));
                Atendimento atd = AtendimentoFacade.BuscarPorId(idAtd);
                atd.setSolucao((String) request.getParameter("solucaoAtendimento"));
                atd.setStatus("Fechado");
                atd.setIdFuncionario(user.getId());
                atd.setFuncionario(user);
                AtendimentoFacade.Alterar(atd);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/listaatendimentosabertos.jsp");
                rd.forward(request, response);
                break;
            }
            case "":
            case "listarAtendimentosAbertos": {
                List<Atendimento> listAtendimento = AtendimentoFacade.BuscarTodosPorAtributo("status", "Aberto");
                Collections.sort(listAtendimento);
                request.setAttribute("listAtendimento", listAtendimento);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/listaatendimentosabertos.jsp");
                rd.forward(request, response);
                break;
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
