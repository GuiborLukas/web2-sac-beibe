package sacbeibe.dao;

import sacbeibe.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sacbeibe.beans.Pessoa;

public class PessoaDAO implements DAO<Pessoa> {

    private static final String QUERY_INSERIR
            = "INSERT INTO tb_pessoa (nome, cpf, email, rua, numero_predial, complemento, cep, bairro, telefone, id_cidade) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String QUERY_BUSCAR_TODOS
            = "SELECT id_pessoa, nome, cpf, email, rua, numero_predial, complemento, cep, bairro, telefone, id_cidade FROM tb_pessoa";
    private static final String QUERY_BUSCAR
            = "SELECT id_pessoa, nome, cpf, email, rua, numero_predial, complemento, cep, bairro, telefone, id_cidade FROM tb_pessoa WHERE cpf = ?";
    private static final String QUERY_BUSCAR_POR_ID
            = "SELECT id_pessoa, nome, cpf, email, rua, numero_predial, complemento, cep, bairro, telefone, id_cidade FROM tb_pessoa WHERE id_pessoa = ?";
    private static final String QUERY_ATUALIZAR
            = "UPDATE tb_pessoa SET nome = ?, cpf = ?, email = ?, rua = ?, numero_predial = ?, complemento = ?, cep = ?, bairro = ?, telefone = ?, id_cidade = ? WHERE id_pessoa = ?";
    private static final String QUERY_REMOVER
            = "DELETE FROM tb_pessoa WHERE id_pessoa = ?";
    private Connection con = null;

    public PessoaDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar PessoaDAO.");
        }
        this.con = con;
    }

    @Override
    public void inserir(Pessoa p) throws DAOException {
        System.out.println(p.getNome() + "::" + p.getCpf() + "::" + p.getEmail());

        try (PreparedStatement st = con.prepareStatement(QUERY_INSERIR)) {
            st.setString(1, p.getNome());
            st.setString(2, p.getCpf());
            st.setString(3, p.getEmail());
            st.setString(4, p.getRua());
            st.setInt(5, p.getNumeroPredial());
            st.setString(6, p.getComplemento());
            st.setString(7, p.getCep());
            st.setString(8, p.getBairro());
            st.setString(9, p.getTelefone());
            st.setInt(10, p.getCidade().getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro inserindo pessoa: "
                    + e.getSQLState()
                    + "/ " + p.toString(), e);
        }
    }

    @Override
    public List<Pessoa> buscarTodos() throws DAOException {
        List<Pessoa> lista = new ArrayList<>();
        CidadeDAO daoCidade = new CidadeDAO(con);
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Pessoa p = new Pessoa();
                p.setId(rs.getInt("id_pessoa"));
                p.setNome(rs.getString("nome"));
                p.setCpf(rs.getString("cpf"));
                p.setEmail(rs.getString("email"));
                p.setRua(rs.getString("rua"));
                p.setNumeroPredial(rs.getInt("numero_predial"));
                p.setComplemento(rs.getString("complemento"));
                p.setBairro(rs.getString("bairro"));
                p.setCep(rs.getString("cep"));
                p.setTelefone(rs.getString("telefone"));
                p.setCidade(daoCidade.buscarPorId(rs.getInt("id_cidade")));
                lista.add(p);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando todas as pessoas: "
                    + QUERY_BUSCAR_TODOS, e);
        }
    }

    @Override
    public Pessoa buscar(String cpf) throws DAOException {
        Pessoa p = null;
        CidadeDAO daoCidade = new CidadeDAO(con);
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR)) {
            st.setString(1, cpf);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p = new Pessoa();
                p.setId(rs.getInt("id_pessoa"));
                p.setNome(rs.getString("nome"));
                p.setCpf(rs.getString("cpf"));
                p.setEmail(rs.getString("email"));
                p.setRua(rs.getString("rua"));
                p.setNumeroPredial(rs.getInt("numero_predial"));
                p.setComplemento(rs.getString("complemento"));
                p.setBairro(rs.getString("bairro"));
                p.setCep(rs.getString("cep"));
                p.setTelefone(rs.getString("telefone"));
                p.setCidade(daoCidade.buscarPorId(rs.getInt("id_cidade")));
            }
            return p;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando pessoa pelo CPF: "
                    + QUERY_BUSCAR + " - " + cpf, e);
        }
    }

    @Override
    public Pessoa buscarPorId(int id) throws DAOException {
        Pessoa p = new Pessoa();
        CidadeDAO daoCidade = new CidadeDAO(con);
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_POR_ID)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id_pessoa"));
                p.setNome(rs.getString("nome"));
                p.setCpf(rs.getString("cpf"));
                p.setEmail(rs.getString("email"));
                p.setRua(rs.getString("rua"));
                p.setNumeroPredial(rs.getInt("numero_predial"));
                p.setComplemento(rs.getString("complemento"));
                p.setBairro(rs.getString("bairro"));
                p.setCep(rs.getString("cep"));
                p.setTelefone(rs.getString("telefone"));
                p.setCidade(daoCidade.buscarPorId(rs.getInt("id_cidade")));
            }
            return null;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando pessoa pelo id: "
                    + QUERY_BUSCAR_POR_ID + id, e);
        }
    }

    @Override
    public void atualizar(Pessoa p) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_ATUALIZAR)) {
            st.setString(1, p.getNome());
            st.setString(2, p.getCpf());
            st.setString(3, p.getEmail());
            st.setString(4, p.getRua());
            st.setInt(5, p.getNumeroPredial());
            st.setString(6, p.getComplemento());
            st.setString(7, p.getCep());
            st.setString(8, p.getBairro());
            st.setString(9, p.getTelefone());
            st.setInt(10, p.getCidade().getId());

            st.setInt(11, p.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro atualizando pessoa: "
                    + QUERY_ATUALIZAR
                    + "/ " + p.toString(), e);
        }
    }

    @Override
    public void remover(Pessoa p) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_REMOVER)) {
            st.setInt(1, p.getId());
            st.executeUpdate();
            System.out.println(p.getNome() + " removido com sucesso!");
        } catch (SQLException e) {
            throw new DAOException("Erro deletando usuário: "
                    + QUERY_REMOVER
                    + "/ " + p.toString(), e);
        }
    }
}
