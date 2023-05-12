package sacbeibe.beans;

import java.io.Serializable;

public class Pessoa implements Serializable {

    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String rua;
    private int numeroPredial;
    private String complemento;
    private String bairro;
    private String cep;
    private String telefone;
    private Cidade cidade;

    public Pessoa() {
    }


    public Pessoa(int id, String nome, String cpf, String email, String rua, int numeroPredial, String complemento, String bairro, String cep, String telefone, Cidade cidade) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.rua = rua;
        this.numeroPredial = numeroPredial;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.telefone = telefone;
        this.cidade = cidade;
    }

    public Pessoa(String nome, String cpf, String email, String rua, int numeroPredial, String complemento, String bairro, String cep, String telefone, Cidade cidade) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.rua = rua;
        this.numeroPredial = numeroPredial;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.telefone = telefone;
        this.cidade = cidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumeroPredial() {
        return numeroPredial;
    }

    public void setNumeroPredial(int numeroPredial) {
        this.numeroPredial = numeroPredial;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

}
