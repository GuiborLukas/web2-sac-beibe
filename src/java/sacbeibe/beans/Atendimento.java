package sacbeibe.beans;

import java.io.Serializable;
import java.util.Date;

public class Atendimento implements Serializable, Comparable<Atendimento> {

    private int id;
    private Date dataAtendimento;
    private int idCliente;
    private String status;
    private int idProduto;
    private TipoAtendimento tipoAtendimento;
    private String descricao;
    private String solucao;
    private int idFuncionario;
    private Produto produto;
    private Usuario cliente;
    private Usuario funcionario;
    

    public Atendimento() {
    }

    public Atendimento(int id, Date dataAtendimento, int idCliente, String status, int idProduto, TipoAtendimento tipoAtendimento, String descricao, String solucao, int idFuncionario) {
        this.id = id;
        this.dataAtendimento = dataAtendimento;
        this.idCliente = idCliente;
        this.status = status;
        this.idProduto = idProduto;
        this.tipoAtendimento = tipoAtendimento;
        this.descricao = descricao;
        this.solucao = solucao;
        this.idFuncionario = idFuncionario;
    }

    public Atendimento(int id, Date dataAtendimento, int idCliente, String status, int idProduto, TipoAtendimento tipoAtendimento, String descricao, String solucao, int idFuncionario, Produto produto) {
        this.id = id;
        this.dataAtendimento = dataAtendimento;
        this.idCliente = idCliente;
        this.status = status;
        this.idProduto = idProduto;
        this.tipoAtendimento = tipoAtendimento;
        this.descricao = descricao;
        this.solucao = solucao;
        this.idFuncionario = idFuncionario;
        this.produto = produto;
    }

    public Atendimento(int id, Date dataAtendimento, int idCliente, String status, int idProduto, TipoAtendimento tipoAtendimento, String descricao, String solucao, int idFuncionario, Produto produto, Usuario cliente) {
        this.id = id;
        this.dataAtendimento = dataAtendimento;
        this.idCliente = idCliente;
        this.status = status;
        this.idProduto = idProduto;
        this.tipoAtendimento = tipoAtendimento;
        this.descricao = descricao;
        this.solucao = solucao;
        this.idFuncionario = idFuncionario;
        this.produto = produto;
        this.cliente = cliente;
    }

    public Atendimento(int id, Date dataAtendimento, int idCliente, String status, int idProduto, TipoAtendimento tipoAtendimento, String descricao, String solucao, int idFuncionario, Produto produto, Usuario cliente, Usuario funcionario) {
        this.id = id;
        this.dataAtendimento = dataAtendimento;
        this.idCliente = idCliente;
        this.status = status;
        this.idProduto = idProduto;
        this.tipoAtendimento = tipoAtendimento;
        this.descricao = descricao;
        this.solucao = solucao;
        this.idFuncionario = idFuncionario;
        this.produto = produto;
        this.cliente = cliente;
        this.funcionario = funcionario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(Date dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public TipoAtendimento getTipoAtendimento() {
        return tipoAtendimento;
    }

    public void setTipoAtendimento(TipoAtendimento tipoAtendimento) {
        this.tipoAtendimento = tipoAtendimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSolucao() {
        return solucao;
    }

    public void setSolucao(String solucao) {
        this.solucao = solucao;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Usuario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Usuario funcionario) {
        this.funcionario = funcionario;
    }

@Override
public int compareTo(Atendimento outro) { 
if (this.dataAtendimento.getTime() > outro.dataAtendimento.getTime()) { 
  return -1; 
  } if (this.dataAtendimento.getTime() < outro.dataAtendimento.getTime()) { 
  return 1; 
  } 
  return 0; 
 }

}
