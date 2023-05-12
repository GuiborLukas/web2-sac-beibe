package sacbeibe.dao;

import sacbeibe.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sacbeibe.beans.Estado;

public class EstadoDAO implements DAO<Estado> {

    private static final String QUERY_BUSCAR_TODOS
            = "SELECT id_estado, nome_estado, sigla_estado FROM tb_estados";
    private static final String QUERY_BUSCAR
            = "SELECT id_estado, nome_estado, sigla_estado FROM tb_estados WHERE nome_estado = ?";
    private static final String QUERY_BUSCAR_POR_ID
            = "SELECT id_estado, nome_estado, sigla_estado FROM tb_estados WHERE id_estado = ?";
    
    private Connection con = null;

    public EstadoDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar EstadoDAO.");
        }
        this.con = con;
    }

    @Override
    public List<Estado> buscarTodos() throws DAOException {
        List<Estado> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Estado p = new Estado();
                p.setId(rs.getInt("id_estado"));
                p.setNome(rs.getString("nome_estado"));
                p.setUf(rs.getString("sigla_estado"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando todas os estados: "
                    + QUERY_BUSCAR_TODOS, e);
        }
    }

    @Override
    public Estado buscar(String nomeEstado) throws DAOException {
        Estado p = new Estado();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setString(1, nomeEstado);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id_estado"));
                p.setNome(rs.getString("nome_estado"));
                p.setUf(rs.getString("sigla_estado"));
            }
            return p;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando estado pelo nome: "
                    + QUERY_BUSCAR + nomeEstado, e);
        }
    }

    @Override
    public Estado buscarPorId(int id) throws DAOException {
        Estado p = new Estado();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_POR_ID)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id_estado"));
                p.setNome(rs.getString("nome_estado"));
                p.setUf(rs.getString("sigla_estado"));
            }
            return p;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando estado pelo id: "
                    + QUERY_BUSCAR + id, e);
        }
    }

    @Override
    public void inserir(Estado p) throws DAOException {
        throw new DAOException("Não é possível inserir um Estado.");
    }

    @Override
    public void atualizar(Estado p) throws DAOException {
        throw new DAOException("Não é possível atualizar um Estado.");
    }

    @Override
    public void remover(Estado p) throws DAOException {
        throw new DAOException("Não é possível deletar um Estado.");
    }
    
}
