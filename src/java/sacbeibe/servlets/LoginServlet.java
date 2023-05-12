package sacbeibe.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sacbeibe.beans.Usuario;
import sacbeibe.facade.UsuarioFacade;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        Usuario user = UsuarioFacade.BuscaUsuarioPorLogin(login);

        if (user != null) {
            boolean autentica = false;
            try {
                autentica = UsuarioFacade.autenticaLogin(login, senha, user);
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Erro na criptografia");
                e.printStackTrace();
            }

            String menu = "";

            if (autentica) {
                String destino;
                session.setAttribute("autenticado", user);
                switch (user.getTipo()) {
                    case 1:
                        menu = "<li class=\"nav-link text-white\"><a href=\"ClienteServlet?action=meusAtendimentos\" class=\"nav-link text-white\" aria-current=\"page\">Meus Atendimentos</a></li>\n"
                                + "<li class=\"nav-link text-white\"><a href=\"ClienteServlet?action=alterarCadastro\" class=\"nav-link text-white\" aria-current=\"page\">Alterar Cadastro</a></li>\n"
                                + "<li class=\"nav-link text-white\"><a href=\"ClienteServlet?action=novoAtendimento\" class=\"nav-link text-white\" aria-current=\"page\">Novo Atendimento</a></li>";
                        session.setAttribute("menu", menu);
                        destino = "/listaatendimentos.jsp";
                        break;
                    case 2:
                        menu = "<li class=\"nav-link text-white\"><a href=\"FuncionarioServlet?action=listarAtendimentosAbertos\" class=\"nav-link text-white\" aria-current=\"page\">Atendimentos Abertos</a></li>\n"
                                + "<li class=\"nav-link text-white\"><a href=\"FuncionarioServlet?action=listarTodosAtendimentos\" class=\"nav-link text-white\" aria-current=\"page\">Todos os Atendimentos</a></li>\n"
                                + "<li class=\"nav-link text-white\"><a href=\"FuncionarioServlet?action=cadastroCategoria\" class=\"nav-link text-white\" aria-current=\"page\">Cadastro de Categorias</a></li>\n"
                                + "<li class=\"nav-link text-white\"><a href=\"FuncionarioServlet?action=cadastroProduto\" class=\"nav-link text-white\" aria-current=\"page\">Cadastro de Produtos</a></li>";
                        session.setAttribute("menu", menu);
                        destino = "/listaatendimentosabertos.jsp";
                        break;
                    case 3:
                        menu = "<li class=\"nav-link text-white\"><a href=\"GerenteServlet?action=dashboard\" class=\"nav-link text-white\" aria-current=\"page\">Dashboard</a></li>\n"
                                + "<li class=\"nav-link text-white\"><a href=\"GerenteServlet?action=listarAtendimentosAbertos\" class=\"nav-link text-white\" aria-current=\"page\">Atendimentos Abertos</a></li>\n"
                                + "<li class=\"nav-link text-white\"><a href=\"GerenteServlet?action=listarTodosAtendimentos\" class=\"nav-link text-white\" aria-current=\"page\">Todos os Atendimentos</a></li>\n"
                                + "<li class=\"nav-link text-white\"><a href=\"GerenteServlet?action=cadastroColaboradores\" class=\"nav-link text-white\" aria-current=\"page\">Cadastro de Colaboradores</a></li>\n"
                                + "<li class=\"nav-link text-white\"><a href=\"GerenteServlet?action=relatorios\" class=\"nav-link text-white\" aria-current=\"page\">Relatórios</a></li>";
                        session.setAttribute("menu", menu);
                        destino = "/portalgerente.jsp";
                        break;
                    case 4:
                        menu = "<li class=\"nav-link text-white\"><a href=\"FuncionarioServlet?action=listarAtendimentosAbertos\" class=\"nav-link text-white\" aria-current=\"page\">Atendimentos Abertos</a></li>\n"
                                + "<li class=\"nav-link text-white\"><a href=\"FuncionarioServlet?action=listarTodosAtendimentos\" class=\"nav-link text-white\" aria-current=\"page\">Todos os Atendimentos</a></li>\n"
                                + "<li class=\"nav-link text-white\"><a href=\"FuncionarioServlet?action=cadastroCategoria\" class=\"nav-link text-white\" aria-current=\"page\">Cadastro de Categorias</a></li>\n"
                                + "<li class=\"nav-link text-white\"><a href=\"FuncionarioServlet?action=cadastroProduto\" class=\"nav-link text-white\" aria-current=\"page\">Cadastro de Produtos</a></li>\n"
                                + "<li class=\"nav-link text-white\"><a href=\"GerenteServlet?action=dashboard\" class=\"nav-link text-white\" aria-current=\"page\">Dashboard</a></li>\n"
                                + "<li class=\"nav-link text-white\"><a href=\"GerenteServlet?action=listarAtendimentosAbertos\" class=\"nav-link text-white\" aria-current=\"page\">Atendimentos Abertos</a></li>\n"
                                + "<li class=\"nav-link text-white\"><a href=\"GerenteServlet?action=listarTodosAtendimentos\" class=\"nav-link text-white\" aria-current=\"page\">Todos os Atendimentos</a></li>\n"
                                + "<li class=\"nav-link text-white\"><a href=\"GerenteServlet?action=cadastroColaboradores\" class=\"nav-link text-white\" aria-current=\"page\">Cadastro de Colaboradores</a></li>\n"
                                + "<li class=\"nav-link text-white\"><a href=\"GerenteServlet?action=relatorios\" class=\"nav-link text-white\" aria-current=\"page\">Relatórios</a></li>\n"
                                + "<li class=\"nav-link text-white\"><a href=\"ClienteServlet?action=meusAtendimentos\" class=\"nav-link text-white\" aria-current=\"page\">Meus Atendimentos</a></li>\n"
                                + "<li class=\"nav-link text-white\"><a href=\"ClienteServlet?action=alterarCadastro\" class=\"nav-link text-white\" aria-current=\"page\">Alterar Cadastro</a></li>\n"
                                + "<li class=\"nav-link text-white\"><a href=\"ClienteServlet?action=novoAtendimento\" class=\"nav-link text-white\" aria-current=\"page\">Novo Atendimento</a></li>";
                        session.setAttribute("menu", menu);
                        destino = "/FuncionarioServlet";
                        break;
                    default:
                        destino = "";

                }
                System.out.println("destino: " + destino);
                if (destino.isEmpty()) {
                    request.setAttribute("msg", "Tipo de usuário invalido.");
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                    return;
                } else {
                    RequestDispatcher rd = getServletContext().getRequestDispatcher(destino);
                    rd.forward(request, response);
                }
            } else {
                request.setAttribute("msg", "Usuário e/ou Senha inválidos");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
                return;
            }
        } else {
            request.setAttribute("msg", "Usuário e/ou Senha inválidos");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
            return;
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
