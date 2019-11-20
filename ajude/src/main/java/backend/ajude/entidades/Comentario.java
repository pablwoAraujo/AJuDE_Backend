package backend.ajude.entidades;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class Comentario {
    @Id
    @GeneratedValue
    private long id;
    private String comentario;
    private String email;
    @OneToMany
    @JoinColumn(name="Respostas")
    private Set<Comentario> hashrespostas;

    public Comentario(){
        super();
    }
    public Comentario(long id, String comentario, String email){
        super();
        this.id = id;
        this.comentario = comentario;
        this.email = email;
        this.hashrespostas = new HashSet<>();
    }

    public Comentario(String comentario, String email){
        super();
        this.comentario = comentario;
        this.email = email;
        this.hashrespostas = new HashSet<>();
    }

    public void adcionaResposta(String comentario, String email){
        Comentario iniciar = new Comentario(comentario, email);
        hashrespostas.add(iniciar);
    }

    public long getId() {
        return id;
    }

    public String getComentario() {
        return comentario;
    }

    public String getEmail() {
        return email;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Set<Comentario> getHashrespostas() {
        return hashrespostas;
    }

    public void setHashrespostas(Set<Comentario> hashrespostas) {
        this.hashrespostas = hashrespostas;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
