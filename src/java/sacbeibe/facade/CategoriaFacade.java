package sacbeibe.facade;

import java.util.ArrayList;
import java.util.List;
import sacbeibe.beans.Categoria;
import sacbeibe.dao.*;

import sacbeibe.exception.DAOException;

public class CategoriaFacade {

    
    public static void Inserir(Categoria c) {

        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Inserir Categoria Id: " + c.getId());
            CategoriaDAO dao = new CategoriaDAO(factory.getConnection());
            dao.inserir(c);

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();

        }
    }

    public static void Alterar(Categoria c) {

        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Atualizar Categoria Id" + c.getId());
            CategoriaDAO dao = new CategoriaDAO(factory.getConnection());
            dao.atualizar(c);

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<Categoria> BuscarTodos() {
        List<Categoria> listCategoria = new ArrayList<Categoria>();
        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Buscar todas as Categorias");
            CategoriaDAO dao = new CategoriaDAO(factory.getConnection());
            listCategoria = dao.buscarTodos();

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        }
        return listCategoria;

    }

    public static Categoria BuscaCategoriaPorId(int id) {
        Categoria cat = new Categoria();
        try (ConnectionFactory factory = new ConnectionFactory()) {
            CategoriaDAO u = new CategoriaDAO(factory.getConnection());
            cat = u.buscarPorId(id);
        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
        }
        return cat;
    }

    public static void Remover(Categoria c) {

        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Remover Categoria Id" + c.getId());
            CategoriaDAO dao = new CategoriaDAO(factory.getConnection());
            dao.remover(c);

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        }

    }

}
