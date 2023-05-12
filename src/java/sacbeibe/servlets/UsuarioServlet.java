package sacbeibe.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sacbeibe.beans.Pessoa;
import sacbeibe.beans.Usuario;
import sacbeibe.facade.UsuarioFacade;
import sacbeibe.beans.Cidade;
import sacbeibe.facade.PessoaFacade;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        int idTipoUsuario;
        switch (action) {
            case "cliente":
            case "autocadastro":
                idTipoUsuario = UsuarioFacade.buscaTipoUsuario("Cliente");
                break;
            case "colaborador":
                if (!"gerente".equals(request.getParameter("tipoCadastro"))) {
                    idTipoUsuario = UsuarioFacade.buscaTipoUsuario("Funcionário");
                } else {
                    idTipoUsuario = UsuarioFacade.buscaTipoUsuario("Gerente");
                }
                break;
            default:
                idTipoUsuario = 0;
        }

        if (idTipoUsuario == 0) {
            request.setAttribute("msg", "Tipo de Usuário inválido.");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
            return;
        }

        int idCidade = 0;
        int numero = 0;
        String nome = request.getParameter("nome");
        String login = request.getParameter("login");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String senha = request.getParameter("InputPassword1");
        String rua = request.getParameter("logradouro");
        String complemento = request.getParameter("complemento");
        String bairro = request.getParameter("bairro");
        String cep = request.getParameter("cep");
        String telefone = request.getParameter("telefone");

        String paramNumero = request.getParameter("numero");
        String paramIdCidade = request.getParameter("cidade");
        try {
            idCidade = Integer.parseInt(paramIdCidade);
            numero = Integer.parseInt(paramNumero);

        } catch (NumberFormatException e) {
            System.err.println("Erro conversao " + e);
            //Redirecionar para Servlet erro
        }
        //busca a cidade
        try {
            Cidade c = UsuarioFacade.buscaCidadePorId(idCidade);

            Pessoa p = new Pessoa(nome, cpf, email, rua, numero, complemento, bairro, cep, telefone, c);

            //Cria a pessoa
            PessoaFacade.inserePessoa(p);
            //Depois de inserida, busca pela pessoa recem 
            p = PessoaFacade.buscarPorCpf(cpf);

            if (p == null) {
                request.setAttribute("msg", "Erro ao criar usuário (pessoa).");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
                return;
            }

            //Cria um usuario associado a pessoa
            Usuario u = new Usuario();
            u.setLogin(login);
            //Criptografa a senha
            senha = UsuarioFacade.Criptografia(senha);
            u.setSenha(senha);
            u.setDados(p);
            u.setIdDados(p.getId());
            u.setTipo(idTipoUsuario);
            UsuarioFacade.insereUsuario(u);
        } catch (IOException | ServletException | NoSuchAlgorithmException e) {
            request.setAttribute("msg", "Erro ao criar usuário. - " + e.getMessage());
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
            return;
        }
        request.setAttribute("msg", "Usuário criado com sucesso!");
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
        if ("colaborador".equals(action)) {
            rd = getServletContext().getRequestDispatcher("/portalgerente.jsp");
        }
        rd.forward(request, response);
        return;
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
