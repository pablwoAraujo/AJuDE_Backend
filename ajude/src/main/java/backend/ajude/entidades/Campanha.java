package backend.ajude.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Campanha {
    @Id
    @GeneratedValue
    private long id;
    private String nome;
    private String URL;
    private String descricao;
    private String data;
    private String status;
    private double meta;
    private double doacao;
    private Usuario dono;
    private String comentarios;
    private int likes;

    public Campanha() {
        super();
    }

    public Campanha(long id, String nome, String URL, String descricao, String data, String status, double meta, double doacao, Usuario dono, String comentarios, int likes) {
        super();
        this.id = id;
        this.nome = nome;
        this.URL = URL;
        this.descricao = descricao;
        this.data = data;
        this.status = status;
        this.meta = meta;
        this.doacao = doacao;
        this.dono = dono;
        this.comentarios = comentarios;
        this.likes = likes;
    }

    public Campanha(String nome, String URL, String descricao, String data, String status, double meta, double doacao, Usuario dono, String comentarios, int likes) {
        super();
        this.nome = nome;
        this.URL = URL;
        this.descricao = descricao;
        this.data = data;
        this.status = status;
        this.meta = meta;
        this.doacao = doacao;
        this.dono = dono;
        this.comentarios = comentarios;
        this.likes = likes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
