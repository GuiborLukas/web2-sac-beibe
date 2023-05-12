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
import sacbeibe.beans.Usuario;

@WebServlet(name = "GerenteServlet", urlPatterns = {"/GerenteServlet"})
public class GerenteServlet extends HttpServlet {

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
            case "visualizarColaborador": {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/");
                rd.forward(request, response);
                break;
            }
            case "inserirColaborador": {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastrarcategoria.jsp");
                rd.forward(request, response);
                break;
            }
            case "alterarColaborador": {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastrarproduto.jsp");
                rd.forward(request, response);
                break;
            }
            case "removerColaborador": {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastrarcategoria.jsp");
                rd.forward(request, response);
                break;
            }
            case "cadastroColaboradores": {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/cadastrarfuncionario.jsp");
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
            case "relatorios": {
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/relatorios.jsp");
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
            case "listarAtendimentosAbertos": {
                List<Atendimento> listAtendimento = AtendimentoFacade.BuscarTodosPorAtributo("status", "Aberto");
                Collections.sort(listAtendimento);
                request.setAttribute("listAtendimento", listAtendimento);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/listaatendimentosabertos.jsp");
                rd.forward(request, response);
                break;
            }
            case "":
            case "dashboard": {
                int totalAtd = GerenteFacade.atendimentosTotais();
                int totalAtdAb = GerenteFacade.atendimentosAbertos();
                int totalAtdRec = GerenteFacade.reclamacaoTotal();
                int totalAtdAbRec = GerenteFacade.reclamacaoAberto();
                int totalAtdSug = GerenteFacade.sugestaoTotal();
                int totalAtdAbSug = GerenteFacade.sugestaoAberto();
                int totalAtdCrit = GerenteFacade.criticaTotal();
                int totalAtdAbCrit = GerenteFacade.criticaAberto();
                int totalAtdProb = GerenteFacade.problemaTotal();
                int totalAtdAbProb = GerenteFacade.problemaAberto();

                request.setAttribute("totalAtd", totalAtd);
                request.setAttribute("totalAtdAb", totalAtdAb);
                request.setAttribute("totalAtdRec", totalAtdRec);
                request.setAttribute("totalAtdAbRec", totalAtdAbRec);
                request.setAttribute("totalAtdSug", totalAtdSug);
                request.setAttribute("totalAtdAbSug", totalAtdAbSug);
                request.setAttribute("totalAtdCrit", totalAtdCrit);
                request.setAttribute("totalAtdAbCrit", totalAtdAbCrit);
                request.setAttribute("totalAtdProb", totalAtdProb);
                request.setAttribute("totalAtdAbProb", totalAtdAbProb);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/portalgerente.jsp");
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
