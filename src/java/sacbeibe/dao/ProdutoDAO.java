package sacbeibe.dao;

import sacbeibe.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sacbeibe.beans.Produto;

public class ProdutoDAO implements DAO<Produto> {

    private static final String QUERY_INSERIR
            = "INSERT INTO tb_produtos (nome_produto, id_categoria, descricao_produto, peso_produto) VALUES (?, ?, ?, ?)";
    private static final String QUERY_BUSCAR_TODOS
            = "SELECT id_produto, nome_produto, id_categoria, descricao_produto, peso_produto FROM tb_produtos";
    private static final String QUERY_BUSCAR_POR_ID
            = "SELECT id_produto, nome_produto, id_categoria, descricao_produto, peso_produto FROM tb_produtos WHERE id_produto = ?";
    private static final String QUERY_BUSCAR_POR_ATRIBUTO
            = "SELECT id_produto, nome_produto, id_categoria, descricao_produto, peso_produto FROM tb_produtos WHERE #coluna# = ?";
    private static final String QUERY_ATUALIZAR
            = "UPDATE tb_produtos SET nome_produto = ?, id_categoria = ?, descricao_produto = ?, peso_produto = ? WHERE id_produto = ?";
    private static final String QUERY_REMOVER
            = "DELETE FROM tb_produtos WHERE id_produto = ? AND nome_produto = ? AND id_categoria = ? AND descricao_produto = ? AND peso_produto = ?";
    private Connection con = null;

    public ProdutoDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar ProdutoDAO.");
        }
        this.con = con;
    }

    @Override
    public void inserir(Produto p) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_INSERIR)) {
            st.setString(1, p.getNome());
            st.setInt(2, p.getIdCategoria());
            st.setString(3, p.getDescricao());
            st.setInt(4, p.getPeso());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro inserindo usuário: "
                    + QUERY_INSERIR
                    + "/ " + p.getNome() + "," + p.getIdCategoria() + "," + p.getDescricao() + "," + p.getPeso(), e);
        }
    }

    @Override
    public List<Produto> buscarTodos() throws DAOException {
        List<Produto> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome_produto"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setDescricao(rs.getString("descricao_produto"));
                p.setPeso(rs.getInt("peso_produto"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando todos os usuários: "
                    + QUERY_BUSCAR_TODOS, e);
        }
    }

    @Override
    public Produto buscar(String s) throws DAOException {
        throw new DAOException("Não é possível buscar pelo nome");
    }

    @Override
    public Produto buscarPorId(int id) throws DAOException {
            Produto p = new Produto();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_POR_ID)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome_produto"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setDescricao(rs.getString("descricao_produto"));
                p.setPeso(rs.getInt("peso_produto"));
            }
            return p;
        }  catch (SQLException e) {
            throw new DAOException("Erro buscando usuário pelo login: "
                    + QUERY_BUSCAR_POR_ID + id, e);
        }
    }

    @Override
    public void atualizar(Produto p) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_ATUALIZAR)) {
            st.setString(1, p.getNome());
            st.setInt(2, p.getIdCategoria());
            st.setString(3, p.getDescricao());
            st.setInt(4, p.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro atualizando usuário: "
                    + QUERY_ATUALIZAR
                    + "/ " + p.toString(), e);
        }
    }

    @Override
    public void remover(Produto p) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_REMOVER)) {
            st.setInt(1, p.getId());
            st.setString(2, p.getNome());
            st.setInt(3, p.getIdCategoria());
            st.setString(4, p.getDescricao());
            st.executeUpdate();
            System.out.println(p.getNome() + " removido com sucesso!");
        } catch (SQLException e) {
            throw new DAOException("Erro deletando usuário: "
                    + QUERY_REMOVER
                    + "/ " + p.toString(), e);
        }
    }

    public List<Produto> buscarPorAtributo(String atributo, String valor) throws DAOException {
        List<Produto> lista = new ArrayList<>();
        String coluna = "";
        if ("nome".equalsIgnoreCase(atributo)) {
            coluna = "nome_produto";
        } else if ("descricao".equalsIgnoreCase(atributo)) {
            coluna = "descricao_produto";
        }
        String query = QUERY_BUSCAR_POR_ATRIBUTO.replace("#coluna#", coluna);
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, valor);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome_produto"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setDescricao(rs.getString("descricao_produto"));
                p.setPeso(rs.getInt("peso_produto"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando todos os endereços com o atributo" + atributo + " de valor " + valor + "\n"
                    + query, e);
        }
    }

    public List<Produto> buscarPorAtributo(String atributo, int valor) throws DAOException {
        List<Produto> lista = new ArrayList<>();
        String coluna = "";
        if ("id".equalsIgnoreCase(atributo)) {
            coluna = "id_produto";
        } else if ("idCategoria".equalsIgnoreCase(atributo)) {
            coluna = "id_categoria";
        } else if ("peso".equalsIgnoreCase(atributo)) {
            coluna = "peso_produto";
        } 
        String query = QUERY_BUSCAR_POR_ATRIBUTO.replace("#coluna#", coluna);
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, valor);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id_produto"));
                p.setNome(rs.getString("nome_produto"));
                p.setIdCategoria(rs.getInt("id_categoria"));
                p.setDescricao(rs.getString("descricao_produto"));
                p.setPeso(rs.getInt("peso_produto"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando todos os endereços com o atributo" + atributo + " de valor " + valor + "\n"
                    + query, e);
        }
    }
}