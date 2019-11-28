package backend.ajude.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import backend.ajude.Enum.StatusCampanha;

/**
 * Entidade Campanha
 */
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
    private List<Doacao> doacoes;
    @OneToMany
    private List<Comentario> hashcomentarios;
    @ManyToOne
    private Usuario dono;
    @OneToMany
    private List<Like> likes;

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
        this.likes =  new ArrayList<>();
        this.hashcomentarios = new ArrayList<>();
        this.doacoes = new ArrayList<>();
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
        this.likes =  new ArrayList<>();
        this.hashcomentarios = new ArrayList<>();
        this.doacoes = new ArrayList<>();
    }

    /**
     * Metodo resposavel por adicionar comentarios na campanha
     * 
     * @param comentario o comentario a ser adicionado
     * @return List<Comentario> a lista dos comentarios da campanha
     */
    public List<Comentario> adcionaComentario(Comentario comentario){
        hashcomentarios.add(comentario);
        return this.hashcomentarios;
    }

    /**
     * Metodo resposavel por adicionar doacoes na campanha
     * 
     * @param doacaoo a doacao a ser adicionado
     * @return List<Doacao> a lista de doacoes da campanha
     */
    public List<Doacao> adcionaDoacao(Doacao doacao){
        this.doacoes.add(doacao);
        return this.doacoes;
    }

    /**
     * Metodo resposavel por adicionar likes/curtidas na campanha
     * 
     * @param like o like/curtida a ser adicionado
     */
	public void adcionaLike(Like like) {
        this.likes.add(like);
	}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public List<Doacao> getDoacoes() {
        return doacoes;
    }

    public void setDoacoes(List<Doacao> doacoes) {
        this.doacoes = doacoes;
    }

    public List<Comentario> getHashcomentarios() {
        return hashcomentarios;
    }

    public void setHashcomentarios(List<Comentario> hashcomentarios) {
        this.hashcomentarios = hashcomentarios;
    }

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

 
}