package backend.ajude.entidades;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class DoacaoDTO {
    private float valor;
    private int idCampanha;
    @Temporal(TemporalType.DATE)
    private Date data;

    // public DoacaoDTO(){
    //     super();
    // }

    public DoacaoDTO(float valor,int idCampanha,Date data){
        // super();
        this.valor = valor;
        this.idCampanha = idCampanha;
        this.data = data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
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