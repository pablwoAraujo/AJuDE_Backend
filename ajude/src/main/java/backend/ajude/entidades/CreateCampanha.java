package backend.ajude.entidades;

public class CreateCampanha{
    private String nome;
    private String descricao;
    private double meta;

    public CreateCampanha() {
        super();
    }

    public CreateCampanha(String nome, String descricao, double meta){
        super();
        this.nome = nome;
        this.descricao = descricao;
        this.meta = meta;
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

}