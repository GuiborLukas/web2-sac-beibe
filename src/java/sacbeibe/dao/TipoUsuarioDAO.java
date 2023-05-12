package sacbeibe.dao;

import sacbeibe.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sacbeibe.beans.TipoUsuario;

public class TipoUsuarioDAO implements DAO<TipoUsuario> {

    private static final String QUERY_INSERIR
            = "INSERT INTO tb_tipo_usuario (descricao_tipo) VALUES (?)";
    private static final String QUERY_BUSCAR_TODOS
            = "SELECT id_tipo, descricao_tipo FROM tb_tipo_usuario";
    private static final String QUERY_BUSCAR
            = "SELECT id_tipo, descricao_tipo FROM tb_tipo_usuario WHERE descricao_tipo = ?";
    private static final String QUERY_BUSCAR_POR_ID
            = "SELECT id_tipo, descricao_tipo FROM tb_tipo_usuario WHERE id_tipo = ?";
    private static final String QUERY_ATUALIZAR
            = "UPDATE tb_tipo_usuario SET descricao_tipo = ? WHERE id_tipo = ?";
    private static final String QUERY_REMOVER
            = "DELETE FROM tb_tipo_usuario WHERE id_tipo = ? AND descricao_tipo = ?";
    private Connection con = null;

    public TipoUsuarioDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar TipoUsuarioDAO.");
        }
        this.con = con;
    }

    @Override
    public void inserir(TipoUsuario p) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_INSERIR)) {
            st.setString(1, p.getDescricao());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro inserindo tipo de usuário: "
                    + QUERY_INSERIR
                    + "/ " + p.toString(), e);
        }
    }

    @Override
    public List<TipoUsuario> buscarTodos() throws DAOException {
        List<TipoUsuario> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                TipoUsuario p = new TipoUsuario();
                p.setId(rs.getInt("id_tipo"));
                p.setDescricao(rs.getString("descricao_tipo"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando todos os tipos de usuários: "
                    + QUERY_BUSCAR_TODOS, e);
        }
    }

    @Override
    public TipoUsuario buscar(String descricao) throws DAOException {
            TipoUsuario p = new TipoUsuario();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setString(1, descricao);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id_tipo"));
                p.setDescricao(rs.getString("descricao_tipo"));
            }
            return p;
        }  catch (SQLException e) {
            throw new DAOException("Erro buscando tipo de usuário pela descrição: "
                    + QUERY_BUSCAR + descricao, e);
        }
    }

    @Override
    public TipoUsuario buscarPorId(int id) throws DAOException {
            TipoUsuario p = new TipoUsuario();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_POR_ID)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id_tipo"));
                p.setDescricao(rs.getString("descricao_tipo"));
            }
            return p;
        }  catch (SQLException e) {
            throw new DAOException("Erro buscando tipo de usuário pelo id: "
                    + QUERY_BUSCAR + id, e);
        }
    }

    @Override
    public void atualizar(TipoUsuario p) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_ATUALIZAR)) {
            st.setString(1, p.getDescricao());
            st.setInt(2, p.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro atualizando tipo de usuário: "
                    + QUERY_ATUALIZAR
                    + "/ " + p.toString(), e);
        }
    }

    @Override
    public void remover(TipoUsuario p) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_REMOVER)) {
            st.setInt(1, p.getId());
            st.setString(2, p.getDescricao());
            st.executeUpdate();
            System.out.println(p.getDescricao()+ " removido com sucesso!");
        } catch (SQLException e) {
            throw new DAOException("Erro deletando tipo de usuário: "
                    + QUERY_REMOVER
                    + "/ " + p.toString(), e);
        }
    }
}