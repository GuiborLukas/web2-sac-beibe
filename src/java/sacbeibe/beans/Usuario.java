package sacbeibe.beans;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int id;
    private String login;
    private int tipo;
    private String senha;
    private int idDados;
    private Pessoa dados;

    public Usuario() {
    }

    public Usuario(String login, int tipo, String senha, int idDados) {
        this.login = login;
        this.tipo = tipo;
        this.senha = senha;
        this.idDados = idDados;
    }

    public Usuario(int id, String login, int tipo, String senha, int idDados) {
        this.id = id;
        this.login = login;
        this.tipo = tipo;
        this.senha = senha;
        this.idDados = idDados;
    }

    public Usuario(int id, String login, int tipo, String senha, Pessoa dados) {
        this.id = id;
        this.login = login;
        this.tipo = tipo;
        this.senha = senha;
        this.dados = dados;
        this.idDados = dados.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdDados() {
        return idDados;
    }

    public void setIdDados(int idDados) {
        this.idDados = idDados;
    }

    public Pessoa getDados() {
        return dados;
    }

    public void setDados(Pessoa dados) {
        this.dados = dados;
    }

    
}
