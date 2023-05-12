package sacbeibe.dao;

import sacbeibe.exception.DAOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sacbeibe.beans.Atendimento;

public class AtendimentoDAO implements DAO<Atendimento> {

    private static final String QUERY_INSERIR
            = "INSERT INTO tb_atendimento (data_atendimento, id_cliente, id_status, id_produto, tipo_atendimento, descricao_atendimento, solucao_atendimento) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String QUERY_BUSCAR_TODOS
            = "SELECT id_atendimento, data_atendimento, id_cliente, id_status, id_produto, tipo_atendimento, descricao_atendimento, solucao_atendimento, id_funcionario FROM tb_atendimento";
    private static final String QUERY_BUSCAR_POR_ID
            = "SELECT id_atendimento, data_atendimento, id_cliente, id_status, id_produto, tipo_atendimento, descricao_atendimento, solucao_atendimento, id_funcionario FROM tb_atendimento WHERE id_atendimento = ?";
    private static final String QUERY_BUSCAR_POR_ATRIBUTO
            = "SELECT id_atendimento, data_atendimento, id_cliente, id_status, id_produto, tipo_atendimento, descricao_atendimento, solucao_atendimento, id_funcionario FROM tb_atendimento WHERE #coluna# = ?";
    private static final String QUERY_BUSCAR_POR_INTERVALO
            = "SELECT id_atendimento, data_atendimento, id_cliente, id_status, id_produto, tipo_atendimento, descricao_atendimento, solucao_atendimento, id_funcionario FROM tb_atendimento WHERE data_atendimento BETWEEN ? AND ?";
    private static final String QUERY_ATUALIZAR
            = "UPDATE tb_atendimento SET data_atendimento = ?, id_cliente = ?, id_status = ?, id_produto = ?, tipo_atendimento = ?, descricao_atendimento = ?, solucao_atendimento = ?, id_funcionario = ? WHERE id_atendimento = ?";
    private static final String QUERY_REMOVER
            = "DELETE FROM tb_atendimento WHERE id_atendimento = ? AND data_atendimento = ? AND id_cliente = ? AND id_status = ? AND id_produto = ? AND tipo_atendimento = ? AND descricao_atendimento = ? AND solucao_atendimento = ? AND id_funcionario = ?";
    private Connection con = null;

    public AtendimentoDAO(Connection con) throws DAOException {
        if (con == null) {
            throw new DAOException("Conexão nula ao criar AtendimentoDAO.");
        }
        this.con = con;
    }

    @Override
    public void inserir(Atendimento p) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_INSERIR)) {
            java.sql.Timestamp dataSql = new java.sql.Timestamp(p.getDataAtendimento().getTime());
            st.setTimestamp(1, dataSql);
            st.setInt(2, p.getIdCliente());
            st.setString(3, p.getStatus());
            st.setInt(4, p.getIdProduto());
            st.setInt(5, p.getTipoAtendimento().getId());
            st.setString(6, p.getDescricao());
            st.setString(7, p.getSolucao());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro inserindo usuário: "
                    + QUERY_INSERIR
                    + "/ " + p.toString(), e);
        }
    }

    @Override
    public List<Atendimento> buscarTodos() throws DAOException {
        List<Atendimento> lista = new ArrayList<>();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_TODOS);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Atendimento p = new Atendimento();
                p.setId(rs.getInt("id_atendimento"));
                p.setDataAtendimento(new java.util.Date(rs.getTimestamp("data_atendimento").getTime()));
                p.setIdCliente(rs.getInt("id_cliente"));
                UsuarioDAO daoUsuario = new UsuarioDAO(this.con);
                p.setCliente(daoUsuario.buscarPorId(p.getIdCliente()));
                p.setStatus(rs.getString("id_status"));
                p.setIdProduto(rs.getInt("id_produto"));
                ProdutoDAO daoProduto = new ProdutoDAO(this.con);
                p.setProduto(daoProduto.buscarPorId(p.getIdProduto()));
                int tp = rs.getInt("tipo_atendimento");
                TipoAtendimentoDAO daoTp = new TipoAtendimentoDAO(this.con);
                p.setTipoAtendimento(daoTp.buscarPorId(tp));
                p.setDescricao(rs.getString("descricao_atendimento"));
                p.setSolucao(rs.getString("solucao_atendimento"));
                p.setIdFuncionario(rs.getInt("id_funcionario"));
                p.setCliente(daoUsuario.buscarPorId(p.getIdCliente()));
                lista.add(p);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando todos os usuários: "
                    + QUERY_BUSCAR_TODOS, e);
        }
    }

    @Override
    public Atendimento buscar(String cpf) throws DAOException {
            throw new DAOException("Não implementado.");
    }

    @Override
    public Atendimento buscarPorId(int id) throws DAOException {
            Atendimento p = new Atendimento();
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_POR_ID)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt("id_atendimento"));
                p.setDataAtendimento(new java.util.Date(rs.getTimestamp("data_atendimento").getTime()));
                p.setIdCliente(rs.getInt("id_cliente"));
                UsuarioDAO daoUsuario = new UsuarioDAO(this.con);
                p.setCliente(daoUsuario.buscarPorId(p.getIdCliente()));
                p.setStatus(rs.getString("id_status"));
                p.setIdProduto(rs.getInt("id_produto"));
                ProdutoDAO daoProduto = new ProdutoDAO(this.con);
                p.setProduto(daoProduto.buscarPorId(p.getIdProduto()));
                int tp = rs.getInt("tipo_atendimento");
                TipoAtendimentoDAO daoTp = new TipoAtendimentoDAO(this.con);
                p.setTipoAtendimento(daoTp.buscarPorId(tp));
                p.setDescricao(rs.getString("descricao_atendimento"));
                p.setSolucao(rs.getString("solucao_atendimento"));
                p.setIdFuncionario(rs.getInt("id_funcionario"));
                p.setCliente(daoUsuario.buscarPorId(p.getIdCliente()));
            }
            return p;
        }  catch (SQLException e) {
            throw new DAOException("Erro buscando usuário pelo login: "
                    + QUERY_BUSCAR_POR_ID + id, e);
        }
    }

    @Override
    public void atualizar(Atendimento p) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_ATUALIZAR)) {
            java.sql.Timestamp dataSql = new java.sql.Timestamp(p.getDataAtendimento().getTime());
            st.setTimestamp(1, dataSql);
            st.setInt(2, p.getIdCliente());
            st.setString(3, p.getStatus());
            st.setInt(4, p.getIdProduto());
            st.setInt(5, p.getTipoAtendimento().getId());
            st.setString(6, p.getDescricao());
            st.setString(7, p.getSolucao());
            st.setInt(8, p.getIdFuncionario());
            st.setInt(9, p.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Erro atualizando usuário: "
                    + QUERY_ATUALIZAR
                    + "/ " + p.toString(), e);
        }
    }

    @Override
    public void remover(Atendimento p) throws DAOException {
        try (PreparedStatement st = con.prepareStatement(QUERY_REMOVER)) {
            st.setInt(1, p.getId());
            java.sql.Timestamp dataSql = new java.sql.Timestamp(p.getDataAtendimento().getTime());
            st.setTimestamp(1, dataSql);
            st.setInt(3, p.getIdCliente());
            st.setString(4, p.getStatus());
            st.setInt(5, p.getIdProduto());
            st.setInt(6, p.getTipoAtendimento().getId());
            st.setString(7, p.getDescricao());
            st.setString(8, p.getSolucao());
            st.setInt(9, p.getIdFuncionario());
            st.executeUpdate();
            System.out.println(p.getId() + " removido com sucesso!");
        } catch (SQLException e) {
            throw new DAOException("Erro deletando usuário: "
                    + QUERY_REMOVER
                    + "/ " + p.toString(), e);
        }
    }

    public List<Atendimento> buscarPorAtributo(String atributo, String valor) throws DAOException {
        List<Atendimento> lista = new ArrayList<>();
        String coluna = "";
        if ("status".equalsIgnoreCase(atributo)) {
            coluna = "id_status";
        } else if ("descricao".equalsIgnoreCase(atributo)) {
            coluna = "descricao_atendimento";
        } else if ("solucao".equalsIgnoreCase(atributo)) {
            coluna = "solucao_atendimento";
        }
        String query = QUERY_BUSCAR_POR_ATRIBUTO.replace("#coluna#", coluna);
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, valor);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Atendimento p = new Atendimento();
                p.setId(rs.getInt("id_atendimento"));
                p.setDataAtendimento(new java.util.Date(rs.getTimestamp("data_atendimento").getTime()));
                p.setIdCliente(rs.getInt("id_cliente"));
                UsuarioDAO daoUsuario = new UsuarioDAO(this.con);
                p.setCliente(daoUsuario.buscarPorId(p.getIdCliente()));
                p.setStatus(rs.getString("id_status"));
                p.setIdProduto(rs.getInt("id_produto"));
                ProdutoDAO daoProduto = new ProdutoDAO(this.con);
                p.setProduto(daoProduto.buscarPorId(p.getIdProduto()));
                int tp = rs.getInt("tipo_atendimento");
                TipoAtendimentoDAO daoTp = new TipoAtendimentoDAO(this.con);
                p.setTipoAtendimento(daoTp.buscarPorId(tp));
                p.setDescricao(rs.getString("descricao_atendimento"));
                p.setSolucao(rs.getString("solucao_atendimento"));
                p.setIdFuncionario(rs.getInt("id_funcionario"));
                p.setCliente(daoUsuario.buscarPorId(p.getIdCliente()));
                lista.add(p);
            }
for(Atendimento a: lista){
System.out.println("Valor na DAO: " + a.getId());
}
            return lista;

        } catch (SQLException e) {
            throw new DAOException("Erro buscando todos os endereços com o atributo" + atributo + " de valor " + valor + "\n"
                    + query, e);
        }
    }

    public List<Atendimento> buscarPorAtributo(String atributo, int valor) throws DAOException {
        List<Atendimento> lista = new ArrayList<>();
        String coluna = "";
        if ("id".equalsIgnoreCase(atributo)) {
            coluna = "id_atendimento";
        } else if ("idCliente".equalsIgnoreCase(atributo)) {
            coluna = "id_cliente";
        } else if ("tipoAtendimento".equalsIgnoreCase(atributo)) {
            coluna = "tipo_atendimento";
        } else if ("idFuncionario".equalsIgnoreCase(atributo)) {
            coluna = "id_funcionario";
        } 
        String query = QUERY_BUSCAR_POR_ATRIBUTO.replace("#coluna#", coluna);
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setInt(1, valor);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Atendimento p = new Atendimento();
                p.setId(rs.getInt("id_atendimento"));
                p.setDataAtendimento(new java.util.Date(rs.getTimestamp("data_atendimento").getTime()));
                p.setIdCliente(rs.getInt("id_cliente"));
                UsuarioDAO daoUsuario = new UsuarioDAO(this.con);
                p.setCliente(daoUsuario.buscarPorId(p.getIdCliente()));
                p.setStatus(rs.getString("id_status"));
                p.setIdProduto(rs.getInt("id_produto"));
                ProdutoDAO daoProduto = new ProdutoDAO(this.con);
                p.setProduto(daoProduto.buscarPorId(p.getIdProduto()));
                int tp = rs.getInt("tipo_atendimento");
                TipoAtendimentoDAO daoTp = new TipoAtendimentoDAO(this.con);
                p.setTipoAtendimento(daoTp.buscarPorId(tp));
                p.setDescricao(rs.getString("descricao_atendimento"));
                p.setSolucao(rs.getString("solucao_atendimento"));
                p.setIdFuncionario(rs.getInt("id_funcionario"));
                p.setCliente(daoUsuario.buscarPorId(p.getIdCliente()));
                lista.add(p);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando todos os atendimentos com o atributo" + atributo + " de valor " + valor + "\n"
                    + query, e);
        }
    }

    public List<Atendimento> buscarPorAtributo(String atributo, Date valor) throws DAOException {
        List<Atendimento> lista = new ArrayList<>();
        String coluna = "";
        if ("dataAtendimento".equalsIgnoreCase(atributo)) {
            coluna = "data_atendimento";
        } 
        String query = QUERY_BUSCAR_POR_ATRIBUTO.replace("#coluna#", coluna);
        try (PreparedStatement st = con.prepareStatement(query)) {
            java.sql.Timestamp dataSql = new java.sql.Timestamp(valor.getTime());
            st.setTimestamp(1, dataSql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Atendimento p = new Atendimento();
                p.setId(rs.getInt("id_atendimento"));
                p.setDataAtendimento(new java.util.Date(rs.getTimestamp("data_atendimento").getTime()));
                p.setIdCliente(rs.getInt("id_cliente"));
                UsuarioDAO daoUsuario = new UsuarioDAO(this.con);
                p.setCliente(daoUsuario.buscarPorId(p.getIdCliente()));
                p.setStatus(rs.getString("id_status"));
                p.setIdProduto(rs.getInt("id_produto"));
                ProdutoDAO daoProduto = new ProdutoDAO(this.con);
                p.setProduto(daoProduto.buscarPorId(p.getIdProduto()));
                int tp = rs.getInt("tipo_atendimento");
                TipoAtendimentoDAO daoTp = new TipoAtendimentoDAO(this.con);
                p.setTipoAtendimento(daoTp.buscarPorId(tp));
                p.setDescricao(rs.getString("descricao_atendimento"));
                p.setSolucao(rs.getString("solucao_atendimento"));
                p.setIdFuncionario(rs.getInt("id_funcionario"));
                p.setCliente(daoUsuario.buscarPorId(p.getIdCliente()));
                lista.add(p);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando todos os atendimentos com o atributo" + atributo + " de valor " + valor + "\n"
                    + query, e);
        }
    }

    public List<Atendimento> buscarPorIntervaloDatas(Date dataInicial, Date dataFinal) throws DAOException {
        List<Atendimento> lista = new ArrayList<>();
            java.sql.Timestamp dataInicialSql = new java.sql.Timestamp(dataInicial.getTime());
            java.sql.Timestamp dataFinalSql = new java.sql.Timestamp(dataFinal.getTime());
        try (PreparedStatement st = con.prepareStatement(QUERY_BUSCAR_POR_INTERVALO)) {
            st.setTimestamp(1, dataInicialSql);
            st.setTimestamp(2, dataFinalSql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Atendimento p = new Atendimento();
                p.setId(rs.getInt("id_atendimento"));
                p.setDataAtendimento(new java.util.Date(rs.getTimestamp("data_atendimento").getTime()));
                p.setIdCliente(rs.getInt("id_cliente"));
                UsuarioDAO daoUsuario = new UsuarioDAO(this.con);
                p.setCliente(daoUsuario.buscarPorId(p.getIdCliente()));
                p.setStatus(rs.getString("id_status"));
                p.setIdProduto(rs.getInt("id_produto"));
                ProdutoDAO daoProduto = new ProdutoDAO(this.con);
                p.setProduto(daoProduto.buscarPorId(p.getIdProduto()));
                int tp = rs.getInt("tipo_atendimento");
                TipoAtendimentoDAO daoTp = new TipoAtendimentoDAO(this.con);
                p.setTipoAtendimento(daoTp.buscarPorId(tp));
                p.setDescricao(rs.getString("descricao_atendimento"));
                p.setSolucao(rs.getString("solucao_atendimento"));
                p.setIdFuncionario(rs.getInt("id_funcionario"));
                p.setCliente(daoUsuario.buscarPorId(p.getIdCliente()));
                lista.add(p);
            }
            return lista;
        } catch (SQLException e) {
            throw new DAOException("Erro buscando por intervalo de tempo: "
                    + QUERY_BUSCAR_POR_INTERVALO, e);
        }
    }

}