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
    @OneToMany
    private Set<Comentario> hashcomentarios;
    @ManyToOne
    private Usuario dono;
    @OneToMany
    private Set<Like> likes;

    public Campanha() {
        super();
    }

    public Campanha(long id, String nome, String url, String descricao, Date data, StatusCampanha status, double meta,
            double doacao, Usuario dono) {
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
        this.likes =  new HashSet<>();
        this.hashcomentarios = new HashSet<>();
    }

    public Campanha(String nome, String url, String descricao, Date data, StatusCampanha status, double meta,
            double doacao, Usuario dono) {
        super();
        this.nome = nome;
        this.url = url;
        this.descricao = descricao;
        this.data = data;
        this.status = status;
        this.meta = meta;
        this.doacao = doacao;
        this.dono = dono;
        this.likes = new HashSet<>();
        this.hashcomentarios = new HashSet<>();

    }
    /** aaaaaaaaaaaaaaaaquii **/
    public Set<Comentario> adcionaComentario(Comentario comentario){
        hashcomentarios.add(comentario);
        return this.hashcomentarios;
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

    public Set<Like> getLikes() {
        return likes;
    }

    public void setLikes(Set<Like> likes) {
        this.likes = likes;
    }

    public Set<Comentario> getHashcomentarios() {
        return hashcomentarios;
    }

    public void setHashcomentarios(Set<Comentario> hashcomentarios) {
        this.hashcomentarios = hashcomentarios;
    }

	public void adcionaLike(Like like) {
        this.likes.add(like);
	}
}
