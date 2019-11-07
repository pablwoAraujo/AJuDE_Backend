package backend.ajude.entidades;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class CreateCampanha{
    private String nome;
    private String descricao;
    private double meta;
    @Temporal(TemporalType.DATE)
    private Date data;
    private String url;

    public CreateCampanha() {
        super();
    }

    public CreateCampanha(String nome, String descricao, double meta, Date data, String url){
        super();
        this.nome = nome;
        this.descricao = descricao;
        this.meta = meta;
        this.data = data;
        this.url = url;
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

}