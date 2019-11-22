package backend.ajude.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comentario {
    @Id
    @GeneratedValue
    private long id;
    private String comentario;//
    @ManyToOne
    private Usuario usuario;//
    //@OneToMany
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comentario> resposta;
    @JsonIgnore
    @ManyToOne
    private Campanha campanha;//
    private Boolean status;

    public Comentario(){
        super();
    }
    public Comentario(long id, String comentario, Usuario usuario, Campanha campanha, Boolean status){
        super();
        this.id = id;
        this.comentario = comentario;
        this.usuario = usuario;
        this.resposta = new HashSet<>();
        this.campanha = campanha;
        this.status = status;
    }

    public Comentario(String comentario, Usuario usuario, Campanha campanha, Boolean status){
        super();
        this.comentario = comentario;
        this.usuario = usuario;
        this.resposta = new HashSet<>();
        this.campanha = campanha;
        this.status = status;
    }

    public void adcionaResposta(Comentario comentario){
        resposta.add(comentario);
    }

    public long getId() {
        return id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Set<Comentario> getresposta() {
        return resposta;
    }

    public void setresposta(Set<Comentario> resposta) {
        this.resposta = resposta;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
}
