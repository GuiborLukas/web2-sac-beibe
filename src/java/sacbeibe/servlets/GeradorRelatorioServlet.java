package sacbeibe.servlets;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sacbeibe.dao.ConnectionFactory;
import sacbeibe.exception.DAOException;

@WebServlet(name = "GeradorRelatorioServlet", urlPatterns = {"/GeradorRelatorioServlet"})
public class GeradorRelatorioServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String report = (String) request.getAttribute("tipoRelatorio");

        switch (report) {
            case "atendimentosAbertosPorData": {
                try (ConnectionFactory factory = new ConnectionFactory()) {
                    String host = "http://" + request.getServerName() + ":"
                            + request.getServerPort();
                    String jasper = request.getContextPath() + "/AtendimentosAbertosPorData.jasper";
                    URL jasperURL = new URL(host + jasper);
                    Date dataInicial = (Date) request.getAttribute("dataInicial");
                    Date dataFinal = (Date) request.getAttribute("dataFinal");
                    HashMap params = new HashMap();
                    params.put("DATA_INICIAL", new java.sql.Date(dataInicial.getTime()));
                    params.put("DATA_FINAL", new java.sql.Date(dataFinal.getTime()));
                    byte[] bytes = JasperRunManager.runReportToPdf(
                            jasperURL.openStream(),
                            params,
                            factory.getConnection());
                    if (bytes != null) {
                        response.setContentType("application/pdf");
                        OutputStream ops = response.getOutputStream();
                        ops.write(bytes);
                    }
                } catch (DAOException e) {
                    request.setAttribute("mensagem", "Erro de DAO : "
                            + e.getMessage());
                    request.getRequestDispatcher("erro.jsp").
                            forward(request, response);
                } catch (JRException e) {
                    request.setAttribute("mensagem", "Erro no Jasper : "
                            + e.getMessage());
                    request.getRequestDispatcher("erro.jsp").
                            forward(request, response);
                }
                break;
            }
            case "relatorioFuncionarios": {
                try (ConnectionFactory factory = new ConnectionFactory()) {
                    String host = "http://" + request.getServerName() + ":"
                            + request.getServerPort();
                    String jasper = request.getContextPath() + "/FuncionariosReport.jasper";
                    URL jasperURL = new URL(host + jasper);
                    HashMap params = new HashMap();
                    byte[] bytes = JasperRunManager.runReportToPdf(
                            jasperURL.openStream(),
                            params,
                            factory.getConnection());
                    if (bytes != null) {
                        response.setContentType("application/pdf");
                        OutputStream ops = response.getOutputStream();
                        ops.write(bytes);
                    }
                } catch (DAOException e) {
                    request.setAttribute("mensagem", "Erro de DAO : "
                            + e.getMessage());
                    request.getRequestDispatcher("erro.jsp").
                            forward(request, response);
                } catch (JRException e) {
                    request.setAttribute("mensagem", "Erro no Jasper : "
                            + e.getMessage());
                    request.getRequestDispatcher("erro.jsp").
                            forward(request, response);
                }
                break;
            }
            case "produtosMaisReclamados": {
                try (ConnectionFactory factory = new ConnectionFactory()) {
                    String host = "http://" + request.getServerName() + ":"
                            + request.getServerPort();
                    String jasper = request.getContextPath() + "/ProdutosMaisReclamados.jasper";
                    URL jasperURL = new URL(host + jasper);
                    HashMap params = new HashMap();
                    byte[] bytes = JasperRunManager.runReportToPdf(
                            jasperURL.openStream(),
                            params,
                            factory.getConnection());
                    if (bytes != null) {
                        response.setContentType("application/pdf");
                        OutputStream ops = response.getOutputStream();
                        ops.write(bytes);
                    }
                } catch (DAOException e) {
                    request.setAttribute("mensagem", "Erro de DAO : "
                            + e.getMessage());
                    request.getRequestDispatcher("erro.jsp").
                            forward(request, response);
                } catch (JRException e) {
                    request.setAttribute("mensagem", "Erro no Jasper : "
                            + e.getMessage());
                    request.getRequestDispatcher("erro.jsp").
                            forward(request, response);
                }
                break;
            }
            case "reclamacoes": {
                try (ConnectionFactory factory = new ConnectionFactory()) {
                    String host = "http://" + request.getServerName() + ":"
                            + request.getServerPort();
                    String jasper = request.getContextPath() + "/Reclamacoes.jasper";
                    URL jasperURL = new URL(host + jasper);
                    HashMap params = new HashMap();
                    byte[] bytes = JasperRunManager.runReportToPdf(
                            jasperURL.openStream(),
                            params,
                            factory.getConnection());
                    if (bytes != null) {
                        response.setContentType("application/pdf");
                        OutputStream ops = response.getOutputStream();
                        ops.write(bytes);
                    }
                } catch (DAOException e) {
                    request.setAttribute("mensagem", "Erro de DAO : "
                            + e.getMessage());
                    request.getRequestDispatcher("erro.jsp").
                            forward(request, response);
                } catch (JRException e) {
                    request.setAttribute("mensagem", "Erro no Jasper : "
                            + e.getMessage());
                    request.getRequestDispatcher("erro.jsp").
                            forward(request, response);
                }
                break;
            }
            case "reclamacoesAbertas": {
                try (ConnectionFactory factory = new ConnectionFactory()) {
                    String host = "http://" + request.getServerName() + ":"
                            + request.getServerPort();
                    String jasper = request.getContextPath() + "/ReclamacoesFechadas.jasper";
                    URL jasperURL = new URL(host + jasper);
                    HashMap params = new HashMap();
                    byte[] bytes = JasperRunManager.runReportToPdf(
                            jasperURL.openStream(),
                            params,
                            factory.getConnection());
                    if (bytes != null) {
                        response.setContentType("application/pdf");
                        OutputStream ops = response.getOutputStream();
                        ops.write(bytes);
                    }
                } catch (DAOException e) {
                    request.setAttribute("mensagem", "Erro de DAO : "
                            + e.getMessage());
                    request.getRequestDispatcher("erro.jsp").
                            forward(request, response);
                } catch (JRException e) {
                    request.setAttribute("mensagem", "Erro no Jasper : "
                            + e.getMessage());
                    request.getRequestDispatcher("erro.jsp").
                            forward(request, response);
                }
                break;
            }
            case "reclamacoesFechadas": {
                try (ConnectionFactory factory = new ConnectionFactory()) {
                    String host = "http://" + request.getServerName() + ":"
                            + request.getServerPort();
                    String jasper = request.getContextPath() + "/ReclamacoesFechadas.jasper";
                    URL jasperURL = new URL(host + jasper);
                    HashMap params = new HashMap();
                    byte[] bytes = JasperRunManager.runReportToPdf(
                            jasperURL.openStream(),
                            params,
                            factory.getConnection());
                    if (bytes != null) {
                        response.setContentType("application/pdf");
                        OutputStream ops = response.getOutputStream();
                        ops.write(bytes);
                    }
                } catch (DAOException e) {
                    request.setAttribute("mensagem", "Erro de DAO : "
                            + e.getMessage());
                    request.getRequestDispatcher("erro.jsp").
                            forward(request, response);
                } catch (JRException e) {
                    request.setAttribute("mensagem", "Erro no Jasper : "
                            + e.getMessage());
                    request.getRequestDispatcher("erro.jsp").
                            forward(request, response);
                }
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
