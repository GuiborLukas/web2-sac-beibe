package sacbeibe.beans;

import java.io.Serializable;

public class Cidade implements Serializable {

    private int id;
    private String nome;
    private int idEstado;
    private Estado estado;

    public Cidade() {
    }

    public Cidade(int id, String nome, int idEstado) {
        this.id = id;
        this.nome = nome;
        this.idEstado = idEstado;
    }

    public Cidade(int id, String nome, int idEstado, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.idEstado = idEstado;
        this.estado = estado;
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

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
