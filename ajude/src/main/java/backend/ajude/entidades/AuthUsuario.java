package backend.ajude.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AuthUsuario{

    @Id
    private String email;
    private String senha;

    public AuthUsuario(){
        super();
    }
    public AuthUsuario(String email, String senha){
        super();
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}