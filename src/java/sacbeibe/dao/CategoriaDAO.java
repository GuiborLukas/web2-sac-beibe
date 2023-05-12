package sacbeibe.dao;

import sacbeibe.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sacbeibe.beans.Categoria;

public class CategoriaDAO implements DAO<Categoria> {

    private static final String QUERY_INSERIR
            = "INSERT INTO tb_categorias_produtos (nome_categoria) VALUES (?)";
    private static final String QUERY_BUSCAR_TODOS
            = "SELECT id_categoria, nome_categoria FROM tb_categorias_produtos";
    private static final String QUERY_BUSCAR
            = "SELECT id_categoria, nome_categoria FROM tb_categorias_produtos WHERE nome_categoria = ?";
    private static final String QUERY_BUSCAR_POR_ID
            = "SELECT id_categoria, nome_categoria FROM tb_categorias_produtos WHERE id_categoria = ?";
    private static final String QUERY_ATUALIZAR
            = "UPDATE tb_categorias_produtos SET nome_categoria = ? WHERE id_categoria = ?";
    private static final String QUERY_REMOVER
            = "DELETE FROM tb_categorias_produtos WHERE id_categoria = ? AND nome_categoria = ?";
    private Connection con = null;

    public CategoriaDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar CategoriaDAO.");
        }
        this.con = con;
    }

    @Override
    public void inserir(Categoria p) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_INSERIR)) {
            st.setString(1, p.getDescricao());
System.out.println(p.getDescricao());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro inserindo categoria: "
                    + QUERY_INSERIR
                    + "/ " + p.toString(), e);
        }
    }

    @Override
    public List<Categoria> buscarTodos() throws DAOException {
        List<Categoria> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Categoria p = new Categoria();
                p.setId(rs.getInt("id_categoria"));
                p.setDescricao(rs.getString("nome_categoria"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando todos as categorias: "
                    + QUERY_BUSCAR_TODOS, e);
        }
    }

    @Override
    public Categoria buscar(String descricao) throws DAOException {
            Categoria p = new Categoria();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setString(1, descricao);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id_categoria"));
                p.setDescricao(rs.getString("nome_categoria"));
            }
            return p;
        }  catch (SQLException e) {
            throw new DAOException("Erro buscando categoria pela descrição: "
                    + QUERY_BUSCAR + descricao, e);
        }
    }

    @Override
    public Categoria buscarPorId(int id) throws DAOException {
            Categoria p = new Categoria();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_POR_ID)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id_categoria"));
                p.setDescricao(rs.getString("nome_categoria"));
            }
            return p;
        }  catch (SQLException e) {
            throw new DAOException("Erro buscando categoria pelo id: "
                    + QUERY_BUSCAR + id, e);
        }
    }

    @Override
    public void atualizar(Categoria p) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_ATUALIZAR)) {
            st.setString(1, p.getDescricao());
            st.setInt(2, p.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro atualizando categoria: "
                    + QUERY_ATUALIZAR
                    + "/ " + p.toString(), e);
        }
    }

    @Override
    public void remover(Categoria p) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_REMOVER)) {
            st.setInt(1, p.getId());
            st.setString(2, p.getDescricao());
            st.executeUpdate();
            System.out.println(p.getDescricao()+ " removido com sucesso!");
        } catch (SQLException e) {
            throw new DAOException("Erro deletando categoria: "
                    + QUERY_REMOVER
                    + "/ " + p.toString(), e);
        }
    }
}