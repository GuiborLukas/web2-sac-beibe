package sacbeibe.beans;

import java.io.Serializable;

public class Produto implements Serializable {

    private int id;
    private String nome;
    private int idCategoria;
    private String descricao;
    private int peso;
    private Categoria categoria;

    public Produto() {
    }

    public Produto(int id, String nome, int idCategoria, String descricao, int peso) {
        this.id = id;
        this.nome = nome;
        this.idCategoria = idCategoria;
        this.descricao = descricao;
        this.peso = peso;
    }

    public Produto(int id, String nome, int idCategoria, String descricao, int peso, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.idCategoria = idCategoria;
        this.descricao = descricao;
        this.peso = peso;
        this.categoria = categoria;
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

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
