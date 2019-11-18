package backend.ajude.entidades;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

public class Comentario {
    @Id
    @GeneratedValue
    private long id;
    private String comentario;
    private String email;
    private Comentario resposta;
    private Set<Comentario> hashcomentarios;

    public Comentario(){
        super();
    }
    public Comentario(long Id, String comentario, String email){
        super();
        this.id = id;
        this.comentario = comentario;
        this.email = email;
        this.resposta = null;
        this.hashcomentarios = new HashSet<>();
    }
    public Comentario(String comentario, String email){
        super();
        this.id = id;
        this.comentario = comentario;
        this.email = email;
        this.resposta = null;
        this.hashcomentarios = new HashSet<>();
    }

    public void adcionaResposta(String comentario, String email){
        Comentario iniciar = new Comentario(comentario, email);
        hashcomentarios.add(iniciar);
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

    public Comentario getResposta() {
        return resposta;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setResposta(Comentario resposta) {
        this.resposta = resposta;
    }
}
