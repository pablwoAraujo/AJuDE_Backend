package backend.ajude.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario{

    @Id
    private String email;
    private String primeiroNome;
    private String ultimoNome;
    private int cartaoCredito;
    private String senha;

    public Usuario(){
        super();
    }
    public Usuario(String email, String primeiroNome, String ultimoNome, int cartaoCredito, String senha){
        super();
        this.email = email;
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.cartaoCredito = cartaoCredito;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public int getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(int cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}