package backend.ajude.entidades;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ComentarioDTO {
    private int idCampanha;
    private String comentario;
    @Temporal(TemporalType.DATE)
    private Date data;

    public ComentarioDTO(){
        super();
    }

    public ComentarioDTO(int idCampanha,String comentario, Date data){
        super();
        this.idCampanha= idCampanha;
        this.comentario = comentario;
        this.data = data;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}