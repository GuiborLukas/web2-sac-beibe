package sacbeibe.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sacbeibe.beans.Atendimento;
import sacbeibe.beans.Categoria;
import sacbeibe.beans.TipoAtendimento;
import sacbeibe.beans.Usuario;
import sacbeibe.facade.AtendimentoFacade;
import sacbeibe.facade.CategoriaFacade;
import sacbeibe.facade.PessoaFacade;
import sacbeibe.facade.UsuarioFacade;

@WebServlet(name = "ClienteServlet", urlPatterns = {"/ClienteServlet"})
public class ClienteServlet extends HttpServlet {

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
            case "alterarCadastro": {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/alterardados.jsp");
                rd.forward(request, response);
                break;
            }
            case "atualizarCadastro": {

                user.getDados().setNome(request.getParameter("nome"));
                if (!request.getParameter("InputPassword1").isEmpty()) {
                    user.setSenha(request.getParameter("InputPassword1"));
                }
                user.getDados().setRua(request.getParameter("logradouro"));
                user.getDados().setComplemento(request.getParameter("complemento"));
                user.getDados().setBairro(request.getParameter("bairro"));
                user.getDados().setCep(request.getParameter("cep"));
                user.getDados().setTelefone(request.getParameter("telefone"));
                user.getDados().setNumeroPredial(Integer.parseInt(request.getParameter("numero")));
                int idCidade = Integer.parseInt(request.getParameter("cidade"));
                user.getDados().setCidade(UsuarioFacade.buscaCidadePorId(idCidade));
                UsuarioFacade.Alterar(user);
                PessoaFacade.alterar(user.getDados());
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/alterardados.jsp");
                rd.forward(request, response);
                break;
            }
            case "novoAtendimento": {
                List<TipoAtendimento> listTipoAtendimento = AtendimentoFacade.BuscarTodosTiposAtendimento();
                request.setAttribute("listTipoAtendimento", listTipoAtendimento);
                List<Categoria> listCategoria = CategoriaFacade.BuscarTodos();
                request.setAttribute("listCategoria", listCategoria);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/criaratendimento.jsp");
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
                Atendimento atd = new Atendimento();

                atd.setDataAtendimento(new java.util.Date());
                atd.setIdCliente(user.getId());
                atd.setCliente(user);
                atd.setStatus("Aberto");
                int idTipo = Integer.parseInt(request.getParameter("tipoAtendimento"));
                atd.setTipoAtendimento(AtendimentoFacade.BuscarTipoPorId(idTipo));
                atd.setIdProduto(Integer.parseInt(request.getParameter("produto")));
                atd.setDescricao(request.getParameter("descricaoAtendimento"));
                AtendimentoFacade.Inserir(atd);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/listaatendimentos.jsp");
                rd.forward(request, response);
                break;
            }
            case "":
            case "meusAtendimentos": {
                List<Atendimento> listAtendimento = AtendimentoFacade.BuscarTodos();
                request.setAttribute("listAtendimento", listAtendimento);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/listaatendimentos.jsp");
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
