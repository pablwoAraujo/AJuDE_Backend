package backend.ajude.entidades;

/**
 * Entidade Criada para autenticar o usuario por meio de seu email e senha
 */
public class AuthUsuario{

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