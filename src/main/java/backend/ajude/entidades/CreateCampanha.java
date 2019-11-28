package backend.ajude.entidades;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Objeto criado para facilitar a criacao da entidade Campanha, contendo as informacoes basicas para 
 * criar uma Campanha
 */
public class CreateCampanha{
    private String nome;
    private String descricao;
    private double meta;
    @Temporal(TemporalType.DATE)
    private Date data;
    private String url;
    private String email;

    public CreateCampanha() {
        super();
    }

    public CreateCampanha(String nome, String descricao, double meta, Date data, String url, String email){
        super();
        this.nome = nome;
        this.descricao = descricao;
        this.meta = meta;
        this.data = data;
        this.url = url;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getMeta() {
        return meta;
    }

    public void setMeta(double meta) {
        this.meta = meta;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}