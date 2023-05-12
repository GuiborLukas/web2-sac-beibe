package sacbeibe.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sacbeibe.beans.LoginBean;
import sacbeibe.beans.Usuario;
import sacbeibe.facade.UsuarioFacade;

@WebServlet(name = "AlterarCadastro", urlPatterns = {"/AlterarCadastro"})
public class AlterarCadastro extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        if (session.getAttribute("autenticado") == null) {
            request.setAttribute("msg", "Usuário deve se autenticar para acessar o sistema!");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
        }

        LoginBean autenticado = (LoginBean) session.getAttribute("autenticado");
        Usuario user = UsuarioFacade.BuscaUsuario(autenticado.getId());

if (user.getTipo() == 1){

}


//
//        int id = Integer.parseInt(request.getParameter("id"));
//               
//            request.setAttribute("cliente", cliente);
//            RequestDispatcher rd = getServletContext().getRequestDispatcher("/clientesVisualizar.jsp");
//            rd.forward(request, response);
        
            
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
