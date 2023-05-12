package sacbeibe.facade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sacbeibe.beans.Atendimento;
import sacbeibe.beans.TipoAtendimento;
import sacbeibe.dao.*;
import sacbeibe.exception.DAOException;

public class AtendimentoFacade {

    public static void Inserir(Atendimento c) {

        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Inserir Atendimento Id: " + c.getId());
            AtendimentoDAO dao = new AtendimentoDAO(factory.getConnection());
            dao.inserir(c);

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();

        }
    }

    public static void Alterar(Atendimento c) {

        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Atualizar Atendimento Id" + c.getId());
            AtendimentoDAO dao = new AtendimentoDAO(factory.getConnection());
            dao.atualizar(c);

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<Atendimento> BuscarTodos() {
        List<Atendimento> listAtendimento = new ArrayList<Atendimento>();
        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Buscar todos");
            AtendimentoDAO dao = new AtendimentoDAO(factory.getConnection());
            listAtendimento = dao.buscarTodos();

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        }
        return listAtendimento;

    }

    public static void Remover(Atendimento c) {

        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Remover Atendimento Id" + c.getId());
            AtendimentoDAO dao = new AtendimentoDAO(factory.getConnection());
            dao.remover(c);

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static List<Atendimento> BuscarTodosPorAtributo(String atributo, String valor) {
        List<Atendimento> listAtendimento = new ArrayList<Atendimento>();
        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Buscar todos itens com " + atributo + " = " + valor);
            AtendimentoDAO dao = new AtendimentoDAO(factory.getConnection());
            listAtendimento = dao.buscarPorAtributo(atributo, valor);

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        }
        return listAtendimento;

    }

    public static List<Atendimento> BuscarTodosPorAtributo(String atributo, int valor) {
        List<Atendimento> listAtendimento = new ArrayList<Atendimento>();
        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Buscar todos itens com " + atributo + " = " + valor);
            AtendimentoDAO dao = new AtendimentoDAO(factory.getConnection());
            listAtendimento = dao.buscarPorAtributo(atributo, valor);

            for (Atendimento a : listAtendimento) {
                System.out.println("Valor na FACADE: " + a.getId());
            }
        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        }
        return listAtendimento;

    }

    public static List<Atendimento> BuscarTodosPorIntervalo(Date dataInicial, Date dataFinal) {
        List<Atendimento> listAtendimento = new ArrayList<Atendimento>();
        try (ConnectionFactory factory = new ConnectionFactory()) {
            SimpleDateFormat dateFormatBr = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("###FACADE-----------> Buscar todos os atendimentos entre " + dateFormatBr.format(dataInicial) + " e " + dateFormatBr.format(dataFinal));
            AtendimentoDAO dao = new AtendimentoDAO(factory.getConnection());
            listAtendimento = dao.buscarPorIntervaloDatas(dataInicial, dataFinal);

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        }
        return listAtendimento;

    }

    public static Atendimento BuscarPorId(int id) {
        Atendimento c = new Atendimento();
        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Buscar " + id);
            AtendimentoDAO dao = new AtendimentoDAO(factory.getConnection());
            c = dao.buscarPorId(id);
        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        }
        return c;
    }

    public static List<TipoAtendimento> BuscarTodosTiposAtendimento() {
        List<TipoAtendimento> listTipoAtendimento = new ArrayList<TipoAtendimento>();
        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Buscar todos");
            TipoAtendimentoDAO dao = new TipoAtendimentoDAO(factory.getConnection());
            listTipoAtendimento = dao.buscarTodos();

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        }
        return listTipoAtendimento;

    }
    public static TipoAtendimento BuscarTipoPorId(int id) {
        TipoAtendimento c = new TipoAtendimento();
        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Buscar " + id);
            TipoAtendimentoDAO dao = new TipoAtendimentoDAO(factory.getConnection());
            c = dao.buscarPorId(id);
        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        }
        return c;
    }
}
