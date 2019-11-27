package backend.ajude.ordenacao;

import backend.ajude.entidades.Campanha;

public class OrdenacaoPorLikes implements Comparadores{

    @Override
    public int compare(Object arg0, Object arg1) {
        Campanha campanha0= (Campanha) arg0;
        Campanha campanha1= (Campanha) arg1;

        int quantidadeLike0 = campanha0.getLikes().size();
        int quantidadeLike1 = campanha1.getLikes().size();

        if(quantidadeLike0>quantidadeLike1){
            return -1;
        }else if(quantidadeLike0<quantidadeLike1){
            return 1;
        }else{
            return 0;
        }
    }

}