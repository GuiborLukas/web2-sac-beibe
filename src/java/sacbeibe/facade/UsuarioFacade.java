package sacbeibe.facade;

import java.util.List;
import sacbeibe.beans.Cidade;
import sacbeibe.beans.Estado;
import sacbeibe.beans.Pessoa;
import sacbeibe.beans.Usuario;
import sacbeibe.dao.CidadeDAO;
import sacbeibe.dao.ConnectionFactory;
import sacbeibe.dao.EstadoDAO;
import sacbeibe.dao.UsuarioDAO;
import sacbeibe.exception.DAOException;
import java.security.*;
import java.math.*;
import sacbeibe.beans.TipoUsuario;
import sacbeibe.dao.TipoUsuarioDAO;

public class UsuarioFacade {

    public static Usuario instanciaUsuario(int id, String login, String senha, int tipo, Pessoa p) {
        Usuario u = new Usuario(id, login, tipo, senha, p);
        return u;
    }

    public static void insereUsuario(Usuario u) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            UsuarioDAO daoU = new UsuarioDAO(factory.getConnection());
            daoU.inserir(u);
        } catch (DAOException e) {
            System.err.println("Erro criar usuario :: " + e.getMessage());
        }

    }

    public static List<Estado> listaEstado() {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            EstadoDAO e = new EstadoDAO(factory.getConnection());
            List<Estado> lista;
            lista = e.buscarTodos();
            return lista;

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
        }
        return null;
    }

    public static int buscaEstadoPorID(String nomeEstado) {
        int id = 0;
        try (ConnectionFactory factory = new ConnectionFactory()) {
            EstadoDAO dao = new EstadoDAO(factory.getConnection());
            Estado e = dao.buscar(nomeEstado);
            id = e.getId();
            return id;
        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
        }
        return id;
    }

    public static List<Cidade> listarCidades(int idEstado) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            CidadeDAO c = new CidadeDAO(factory.getConnection());
            List<Cidade> lista;
            lista = c.buscarPorEstado(idEstado);
            return lista;

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
        }
        return null;
    }

    public static Cidade buscaCidadePorId(int idCidade) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            CidadeDAO c = new CidadeDAO(factory.getConnection());
            Cidade cidade = c.buscarPorId(idCidade);
            return cidade;

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
        }
        return null;
    }

    public static Usuario validaUsuario(String login) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            UsuarioDAO u = new UsuarioDAO(factory.getConnection());
            Usuario user = u.buscar(login);
            return user;

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
        }
        return null;
    }

    public static Usuario BuscaUsuario(int id) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            UsuarioDAO u = new UsuarioDAO(factory.getConnection());
            Usuario user = u.buscarPorId(id);
            return user;

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
        }
        return null;
    }

    public static Usuario BuscaUsuarioPorLogin(String login) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            UsuarioDAO u = new UsuarioDAO(factory.getConnection());
            Usuario user = u.buscar(login);
            return user;
        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
        }
        return null;
    }

    public static void Alterar(Usuario c) {

        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Atualizar Produto Id" + c.getId());
            UsuarioDAO dao = new UsuarioDAO(factory.getConnection());
            dao.atualizar(c);

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void Remover(Usuario c) {

        try (ConnectionFactory factory = new ConnectionFactory()) {
            System.out.println("###FACADE-----------> Remover Produto Id" + c.getId());
            UsuarioDAO dao = new UsuarioDAO(factory.getConnection());
            dao.remover(c);

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public static List<Usuario> BuscarTodosUsuarios() {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            UsuarioDAO c = new UsuarioDAO(factory.getConnection());
            List<Usuario> lista;
            lista = c.buscarTodos();
            return lista;

        } catch (DAOException e) {
            System.out.println("#### ERRO DE DAO: " + e.getMessage());
        }
        return null;
    }

    public static boolean autenticaLogin(String login, String senha, Usuario p) throws NoSuchAlgorithmException {
        senha = Criptografia(senha);
        boolean autenticado = false;
        if (login.equals(p.getLogin()) && senha.equals(p.getSenha())) {
            autenticado = true;
        }
        return autenticado;
    }

    public static String Criptografia(String senha) throws NoSuchAlgorithmException {

        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(senha.getBytes(), 0, senha.length());
        String hash = new BigInteger(1, m.digest()).toString(100);
        return hash;
    }

    public static int buscaTipoUsuario(String tipo) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            TipoUsuarioDAO daoU = new TipoUsuarioDAO(factory.getConnection());
            TipoUsuario tpUsuario = daoU.buscar(tipo);
            if (tpUsuario != null) {
                return tpUsuario.getId();
            }
        } catch (DAOException e) {
            System.err.println("Erro buscar tipo de usuario :: " + e.getMessage());
        }
        return 0;
    }
}
