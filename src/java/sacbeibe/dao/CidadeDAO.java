package sacbeibe.dao;

import sacbeibe.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sacbeibe.beans.Cidade;

public class CidadeDAO implements DAO<Cidade> {

    private static final String QUERY_BUSCAR_TODOS
            = "SELECT id_cidade, nome_cidade, id_estado FROM tb_cidades";
    private static final String QUERY_BUSCAR
            = "SELECT id_cidade, nome_cidade, id_estado FROM tb_cidades WHERE nome_cidade = ?";
    private static final String QUERY_BUSCAR_POR_ID
            = "SELECT id_cidade, nome_cidade, id_estado FROM tb_cidades WHERE id_cidade = ?";
    private static final String QUERY_BUSCAR_POR_ESTADO
            = "SELECT id_cidade, nome_cidade, id_estado FROM tb_cidades WHERE id_estado = ?";

    private Connection con = null;

    public CidadeDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar CidadeDAO.");
        }
        this.con = con;
    }

    @Override
    public List<Cidade> buscarTodos() throws DAOException {
        List<Cidade> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Cidade p = new Cidade();
                p.setId(rs.getInt("id_cidade"));
                p.setNome(rs.getString("nome_cidade"));
                p.setId(rs.getInt("id_estado"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando todas as cidades: "
                    + QUERY_BUSCAR_TODOS, e);
        }
    }

    @Override
    public Cidade buscar(String nomeCidade) throws DAOException {
        Cidade p = new Cidade();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setString(1, nomeCidade);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p.setId(Integer.parseInt(rs.getString("id_cidade")));
                p.setNome(rs.getString("nome_cidade"));
                p.setId(Integer.parseInt(rs.getString("id_estado")));
            }
            return p;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando cidade pelo nome: "
                    + QUERY_BUSCAR + nomeCidade, e);
        }
    }

    @Override
    public Cidade buscarPorId(int id) throws DAOException {
        Cidade p = new Cidade();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_POR_ID)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id_cidade"));
                p.setNome(rs.getString("nome_cidade"));
                p.setId(rs.getInt("id_estado"));
            }
            return p;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando cidade pelo id: "
                    + QUERY_BUSCAR + id, e);
        }
    }

    public List<Cidade> buscarPorEstado(int idEstado) throws DAOException {
        List<Cidade> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_POR_ESTADO)) {
            st.setInt(1, idEstado);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cidade p = new Cidade();
                p.setId(rs.getInt("id_cidade"));
                p.setNome(rs.getString("nome_cidade"));
                p.setId(rs.getInt("id_estado"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando cidades por estado: "
                    + QUERY_BUSCAR_POR_ESTADO, e);
        }
    }

    @Override
    public void inserir(Cidade p) throws DAOException {
        throw new DAOException("Não é possível inserir uma Cidade.");
    }

    @Override
    public void atualizar(Cidade p) throws DAOException {
        throw new DAOException("Não é possível atualizar uma Cidade.");
    }

    @Override
    public void remover(Cidade p) throws DAOException {
        throw new DAOException("Não é possível deletar uma Cidade.");
    }

}
