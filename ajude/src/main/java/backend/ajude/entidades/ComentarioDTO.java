package backend.ajude.entidades;

public class ComentarioDTO {
    private int idCampanha;
    private String comentario;
    private String email;

    public ComentarioDTO(){
        super();
    }

    public ComentarioDTO(int idCampanha,String comentario, String email){
        super();
        this.idCampanha= idCampanha;
        this.comentario = comentario;
        this.email = email;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdCampanha() {
        return idCampanha;
    }

    public void setIdCampanha(int idCampanha) {
        this.idCampanha = idCampanha;
    }

}