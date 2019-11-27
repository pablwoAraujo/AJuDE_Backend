package backend.ajude.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Doacao{
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Usuario usuario;
    private float valor;
    @JsonIgnore
    @ManyToOne
    private Campanha campanha;
    @Temporal(TemporalType.DATE)
    private Date data;

    public Doacao(){
        super();
    }

    public Doacao(Usuario user,float valor,Campanha campanha,Date data){
        super();
        this.usuario= user;
        this.valor = valor;
        this.campanha = campanha;
        this.data = data;
    }

    public Doacao(long id, Usuario user,float valor,Campanha campanha,Date data){
        super();
        this.id = id;
        this.usuario= user;
        this.valor = valor;
        this.campanha = campanha;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    
}