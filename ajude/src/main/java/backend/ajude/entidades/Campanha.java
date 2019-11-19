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

import com.fasterxml.jackson.annotation.JsonIgnore;

import backend.ajude.Enum.StatusCampanha;

@Entity
public class Campanha {
    @Id
    @GeneratedValue
    private long id;
    private String nome;
    private String url;
    private String descricao;
    @Temporal(TemporalType.DATE)
    private Date data;
    private StatusCampanha status;
    private double meta;
    private double doacao;
    @JsonIgnore
    @OneToMany
    @JoinColumn(name="Comentariossss")
    private Set<Comentario> hashcomentarios;
    @ManyToOne
    private Usuario dono;
    private int likes;

    public Campanha() {
        super();
    }

    public Campanha(long id, String nome, String url, String descricao, Date data, StatusCampanha status, double meta,
            double doacao, Usuario dono, int likes) {
        super();
        this.id = id;
        this.nome = nome;
        this.url = url;
        this.descricao = descricao;
        this.data = data;
        this.status = status;
        this.meta = meta;
        this.doacao = doacao;
        this.dono = dono;
        this.likes = likes;
        this.hashcomentarios = new HashSet<>();
    }

    public Campanha(String nome, String url, String descricao, Date data, StatusCampanha status, double meta,
            double doacao, Usuario dono, int likes) {
        super();
        this.nome = nome;
        this.url = url;
        this.descricao = descricao;
        this.data = data;
        this.status = status;
        this.meta = meta;
        this.doacao = doacao;
        this.dono = dono;
        this.likes = likes;
        this.hashcomentarios = new HashSet<>();

    }
    /** aaaaaaaaaaaaaaaaquii **/
    public void adcionaComentario(String comentario, String email){
        Comentario iniciar = new Comentario(comentario, email);
        hashcomentarios.add(iniciar);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String geturl() {
        return url;
    }

    public void seturl(String url) {
        this.url = url;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public StatusCampanha getStatus() {
        return status;
    }

    public void setStatus(StatusCampanha status) {
        this.status = status;
    }

    public double getMeta() {
        return meta;
    }

    public void setMeta(double meta) {
        this.meta = meta;
    }

    public double getDoacao() {
        return doacao;
    }

    public void setDoacao(double doacao) {
        this.doacao = doacao;
    }

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
