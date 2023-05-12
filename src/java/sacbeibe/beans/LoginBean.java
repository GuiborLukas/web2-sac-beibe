package sacbeibe.beans;

import java.io.Serializable;

public class LoginBean implements Serializable{
    
    int id;
    String nome;

    public LoginBean() {
    }
    
    public LoginBean(int id, String nome) {
        this.id = id;
        this.nome = nome;
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
    
}
