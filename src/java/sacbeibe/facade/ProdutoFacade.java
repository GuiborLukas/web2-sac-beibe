package sacbeibe.facade;

import java.util.ArrayList;
import java.util.List;
import sacbeibe.beans.Categoria;
import sacbeibe.beans.Produto;
import sacbeibe.dao.*;
import sacbeibe.exception.DAOException;

public class ProdutoFacade {

    
    public static void Inserir(Produto c) {

        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Inserir Produto Id: " + c.getId());
            ProdutoDAO dao = new ProdutoDAO(factory.getConnection());
            dao.inserir(c);

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();

        }
    }

    public static void Alterar(Produto c) {

        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Atualizar Produto Id" + c.getId());
            ProdutoDAO dao = new ProdutoDAO(factory.getConnection());
            dao.atualizar(c);

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<Produto> BuscarTodos() {
        List<Produto> listProduto = new ArrayList<Produto>();
        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Buscar todos");
            ProdutoDAO dao = new ProdutoDAO(factory.getConnection());
            listProduto = dao.buscarTodos();

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        }
        return listProduto;

    }

    public static void Remover(Produto c) {

        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Remover Produto Id" + c.getId());
            ProdutoDAO dao = new ProdutoDAO(factory.getConnection());
            dao.remover(c);

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static List<Produto> BuscarTodosPorAtributo(String atributo, String valor) {
        List<Produto> listProduto = new ArrayList<Produto>();
        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Buscar todos itens com " + atributo + " = " + valor);
            ProdutoDAO dao = new ProdutoDAO(factory.getConnection());
            listProduto = dao.buscarPorAtributo(atributo, valor);

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        }
        return listProduto;

    }

    public static List<Produto> BuscarTodosPorAtributo(String atributo, int valor) {
        List<Produto> listProduto = new ArrayList<Produto>();
        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Buscar todos itens com " + atributo + " = " + valor);
            ProdutoDAO dao = new ProdutoDAO(factory.getConnection());
            listProduto = dao.buscarPorAtributo(atributo, valor);

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        }
        return listProduto;

    }

    public static Produto BuscarPorIdDetalhado(int id) {
        Produto p = new Produto();
        Categoria cat;
        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Buscar " + id);
            ProdutoDAO dao = new ProdutoDAO(factory.getConnection());
            p = dao.buscarPorId(id);
            cat = CategoriaFacade.BuscaCategoriaPorId(p.getIdCategoria());
            p.setCategoria(cat);
        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        }
        return p;
    }
}
