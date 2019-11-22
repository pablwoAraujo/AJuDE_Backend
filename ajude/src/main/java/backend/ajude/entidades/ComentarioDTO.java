package backend.ajude.entidades;

public class ComentarioDTO {
    private int idCampanha;
    private String comentario;

    public ComentarioDTO(){
        super();
    }

    public ComentarioDTO(int idCampanha,String comentario){
        super();
        this.idCampanha= idCampanha;
        this.comentario = comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getIdCampanha() {
        return idCampanha;
    }

    public void setIdCampanha(int idCampanha) {
        this.idCampanha = idCampanha;
    }

}