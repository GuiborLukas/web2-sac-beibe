package sacbeibe.dao;

import sacbeibe.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sacbeibe.beans.Pessoa;
import sacbeibe.beans.Usuario;

public class UsuarioDAO implements DAO<Usuario> {

    private static final String QUERY_INSERIR
            = "INSERT INTO tb_usuario (login_usuario, tipo_usuario, senha_usuario, id_dados) VALUES (?, ?, ?, ?)";
    private static final String QUERY_BUSCAR_TODOS
            = "SELECT id_usuario, login_usuario, tipo_usuario, senha_usuario, id_dados FROM tb_usuario";
    private static final String QUERY_BUSCAR
            = "SELECT id_usuario, login_usuario, tipo_usuario, senha_usuario, id_dados FROM tb_usuario WHERE login_usuario = ?";
    private static final String QUERY_BUSCAR_POR_ID
            = "SELECT id_usuario, login_usuario, tipo_usuario, senha_usuario, id_dados FROM tb_usuario WHERE id_usuario = ?";
    private static final String QUERY_ATUALIZAR
            = "UPDATE tb_usuario SET login_usuario = ?, tipo_usuario = ?, senha_usuario = ?, id_dados = ? WHERE id_usuario = ?";
    private static final String QUERY_REMOVER
            = "DELETE FROM tb_usuario WHERE id_usuario = ?";
    private Connection con = null;

    public UsuarioDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar UsuarioDAO.");
        }
        this.con = con;
    }

    @Override
    public void inserir(Usuario p) throws DAOException {
        System.out.println(p.getLogin() + "::" + p.getSenha() + "::" + p.getIdDados());
        try (PreparedStatement st = con.prepareStatement(QUERY_INSERIR)) {
            st.setString(1, p.getLogin());
            st.setInt(2, p.getTipo());
            st.setString(3, p.getSenha());
            st.setInt(4, p.getIdDados());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro inserindo usuário: "
                    + e.getSQLState()
                    + "/ " + p.toString(), e);
        }
    }

    @Override
    public List<Usuario> buscarTodos() throws DAOException {
        List<Usuario> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Usuario p = new Usuario();
                p.setId(rs.getInt("id_usuario"));
                p.setLogin(rs.getString("login_usuario"));
                p.setTipo(rs.getInt("tipo_usuario"));
                p.setSenha(rs.getString("senha_usuario"));
                p.setIdDados(rs.getInt("id_dados"));
                PessoaDAO pessoaDao = new PessoaDAO(this.con);
                p.setDados(pessoaDao.buscarPorId(p.getIdDados()));
                lista.add(p);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando todos os usuários: "
                    + QUERY_BUSCAR_TODOS, e);
        }
    }

    @Override
    public Usuario buscar(String login) throws DAOException {
        Usuario p = new Usuario();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id_usuario"));
                p.setLogin(rs.getString("login_usuario"));
                p.setTipo(rs.getInt("tipo_usuario"));
                p.setSenha(rs.getString("senha_usuario"));
                p.setIdDados(rs.getInt("id_dados"));
                PessoaDAO pessoaDao = new PessoaDAO(this.con);
                Pessoa ps = pessoaDao.buscarPorId(p.getIdDados());
                p.setDados(ps);
            }
            return p;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando usuário pelo login: "
                    + QUERY_BUSCAR + login, e);
        }
    }

    @Override
    public Usuario buscarPorId(int id) throws DAOException {
        Usuario p = new Usuario();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_POR_ID)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id_usuario"));
                p.setLogin(rs.getString("login_usuario"));
                p.setTipo(rs.getInt("tipo_usuario"));
                p.setSenha(rs.getString("senha_usuario"));
                p.setIdDados(rs.getInt("id_dados"));
                PessoaDAO pessoaDao = new PessoaDAO(this.con);
                Pessoa ps = pessoaDao.buscarPorId(p.getIdDados());
                p.setDados(ps);
            }
            return p;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando usuário pelo login: "
                    + QUERY_BUSCAR + id, e);
        }
    }

    @Override
    public void atualizar(Usuario p) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_ATUALIZAR)) {
            st.setString(1, p.getLogin());
            st.setInt(2, p.getTipo());
            st.setString(3, p.getSenha());
            st.setInt(4, p.getIdDados());
            st.setInt(5, p.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro atualizando usuário: "
                    + QUERY_ATUALIZAR
                    + "/ " + p.toString(), e);
        }
    }

    @Override
    public void remover(Usuario p) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_REMOVER)) {
            st.setInt(1, p.getId());
            st.setString(2, p.getLogin());
            st.setInt(3, p.getTipo());
            st.setString(4, p.getSenha());
            st.setInt(5, p.getIdDados());
            st.executeUpdate();
            System.out.println(p.getLogin() + " removido com sucesso!");
        } catch (SQLException e) {
            throw new DAOException("Erro deletando usuário: "
                    + QUERY_REMOVER
                    + "/ " + p.toString(), e);
        }
    }
}
