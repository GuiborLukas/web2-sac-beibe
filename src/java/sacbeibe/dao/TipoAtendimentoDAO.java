package sacbeibe.dao;

import sacbeibe.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sacbeibe.beans.TipoAtendimento;

public class TipoAtendimentoDAO implements DAO<TipoAtendimento> {

    private static final String QUERY_INSERIR
            = "INSERT INTO tb_tipo_atendimento (descricao_tipo) VALUES (?)";
    private static final String QUERY_BUSCAR_TODOS
            = "SELECT id_tipo_atendimento, descricao_tipo FROM tb_tipo_atendimento";
    private static final String QUERY_BUSCAR
            = "SELECT id_tipo_atendimento, descricao_tipo FROM tb_tipo_atendimento WHERE descricao_tipo = ?";
    private static final String QUERY_BUSCAR_POR_ID
            = "SELECT id_tipo_atendimento, descricao_tipo FROM tb_tipo_atendimento WHERE id_tipo_atendimento = ?";
    private static final String QUERY_ATUALIZAR
            = "UPDATE tb_tipo_atendimento SET descricao_tipo = ? WHERE id_tipo_atendimento = ?";
    private static final String QUERY_REMOVER
            = "DELETE FROM tb_tipo_atendimento WHERE id_tipo_atendimento = ? AND descricao_tipo = ?";
    private Connection con = null;

    public TipoAtendimentoDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar TipoAtendimentoDAO.");
        }
        this.con = con;
    }

    @Override
    public void inserir(TipoAtendimento p) throws DAOException {
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
    public List<TipoAtendimento> buscarTodos() throws DAOException {
        List<TipoAtendimento> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                TipoAtendimento p = new TipoAtendimento();
                p.setId(rs.getInt("id_tipo_atendimento"));
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
    public TipoAtendimento buscar(String descricao) throws DAOException {
            TipoAtendimento p = new TipoAtendimento();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setString(1, descricao);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id_tipo_atendimento"));
                p.setDescricao(rs.getString("descricao_tipo"));
            }
            return p;
        }  catch (SQLException e) {
            throw new DAOException("Erro buscando tipo de usuário pela descrição: "
                    + QUERY_BUSCAR + descricao, e);
        }
    }

    @Override
    public TipoAtendimento buscarPorId(int id) throws DAOException {
            TipoAtendimento p = new TipoAtendimento();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_POR_ID)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id_tipo_atendimento"));
                p.setDescricao(rs.getString("descricao_tipo"));
            }
            return p;
        }  catch (SQLException e) {
            throw new DAOException("Erro buscando tipo de usuário pelo id: "
                    + QUERY_BUSCAR_POR_ID + id, e);
        }
    }

    @Override
    public void atualizar(TipoAtendimento p) throws DAOException {
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
    public void remover(TipoAtendimento p) throws DAOException {
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