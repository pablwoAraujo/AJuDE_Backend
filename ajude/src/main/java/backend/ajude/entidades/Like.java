package backend.ajude.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "CURTIDA")
public class Like{
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private Usuario user;

    public Like(){
        super();
    }

    public Like(Usuario user){
        super();
        this.user= user;
    }

    public Like(int id, Usuario user){
        super();
        this.id = id;
        this.user= user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
}