package sacbeibe.facade;

import java.util.ArrayList;
import java.util.List;
import sacbeibe.beans.Atendimento;
import sacbeibe.beans.Categoria;
import sacbeibe.dao.*;

import sacbeibe.exception.DAOException;

public class GerenteFacade {

    public static int atendimentosTotais() {

        // a ideia foi pegar a lista todas com o buscar lista e ai apenas informar o tamanho para uma variavel int
        int atendimentos = 0;
        List<Atendimento> listAtendimento = new ArrayList<Atendimento>();

        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Buscar todos");
            AtendimentoDAO dao = new AtendimentoDAO(factory.getConnection());
            listAtendimento = dao.buscarTodos();
            atendimentos = listAtendimento.size();

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        }
        return atendimentos;
    }

    public static int atendimentosAbertos() {

        List<Atendimento> listAtendimento = new ArrayList<Atendimento>();
        int atendimentos = 0;
        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Buscar todos");
            AtendimentoDAO dao = new AtendimentoDAO(factory.getConnection());
            listAtendimento = dao.buscarPorAtributo("status", "Aberto");
            atendimentos = listAtendimento.size();

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        }

        return atendimentos;
    }

    public static int antendimentosTipo() {

        return 0;
    }

    public static int reclamacaoAberto() {

        List<Atendimento> listAtendimento = AtendimentoFacade.BuscarTodosPorAtributo("status", "Aberto");
        int contador = 0;
        for (Atendimento a : listAtendimento) {
            if (a.getTipoAtendimento().getId() == 1) {
                contador++;
            }
        }
        return contador;

    }

    public static int sugestaoAberto() {

        List<Atendimento> listAtendimento = AtendimentoFacade.BuscarTodosPorAtributo("status", "Aberto");
        int contador = 0;
        for (Atendimento a : listAtendimento) {
            if (a.getTipoAtendimento().getId() == 2) {
                contador++;
            }
        }
        return contador;

    }

    public static int criticaAberto() {

        List<Atendimento> listAtendimento = AtendimentoFacade.BuscarTodosPorAtributo("status", "Aberto");
        int contador = 0;
        for (Atendimento a : listAtendimento) {
            if (a.getTipoAtendimento().getId() == 3) {
                contador++;
            }
        }
        return contador;

    }

    public static int problemaAberto() {

        List<Atendimento> listAtendimento = AtendimentoFacade.BuscarTodosPorAtributo("status", "Aberto");
        int contador = 0;
        for (Atendimento a : listAtendimento) {
            if (a.getTipoAtendimento().getId() == 4) {
                contador++;
            }
        }
        return contador;

    }

public static int reclamacaoTotal() {

        List<Atendimento> listAtendimento = AtendimentoFacade.BuscarTodos();
        int contador = 0;
        for (Atendimento a : listAtendimento) {
            if (a.getTipoAtendimento().getId() == 1) {
                contador++;
            }
        }
        return contador;

    }

    public static int sugestaoTotal() {

        List<Atendimento> listAtendimento = AtendimentoFacade.BuscarTodos();
        int contador = 0;
        for (Atendimento a : listAtendimento) {
            if (a.getTipoAtendimento().getId() == 2) {
                contador++;
            }
        }
        return contador;

    }

    public static int criticaTotal() {

        List<Atendimento> listAtendimento = AtendimentoFacade.BuscarTodos();
        int contador = 0;
        for (Atendimento a : listAtendimento) {
            if (a.getTipoAtendimento().getId() == 3) {
                contador++;
            }
        }
        return contador;

    }

    public static int problemaTotal() {

        List<Atendimento> listAtendimento = AtendimentoFacade.BuscarTodos();
        int contador = 0;
        for (Atendimento a : listAtendimento) {
            if (a.getTipoAtendimento().getId() == 4) {
                contador++;
            }
        }
        return contador;

    }


}
