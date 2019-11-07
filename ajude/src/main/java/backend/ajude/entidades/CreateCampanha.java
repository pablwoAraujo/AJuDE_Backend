package backend.ajude.entidades;

public class CreateCampanha{
    private String nome;
    private String descricao;
    private double meta;
    private String data;
    private String url;

    public CreateCampanha() {
        super();
    }

    public CreateCampanha(String nome, String descricao, double meta, String data, String url){
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}