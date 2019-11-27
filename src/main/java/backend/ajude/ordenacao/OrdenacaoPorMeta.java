package backend.ajude.ordenacao;

import backend.ajude.entidades.Campanha;

public class OrdenacaoPorMeta implements Comparadores{

    @Override
    public int compare(Object arg0, Object arg1) {
        Campanha campanha0= (Campanha) arg0;
        Campanha campanha1= (Campanha) arg1;

        double quantidadeQueFalta0= campanha0.getMeta() - campanha0.getDoacao();
        double quantidadeQueFalta1= campanha1.getMeta() - campanha1.getDoacao();

        if(quantidadeQueFalta0>quantidadeQueFalta1){
            return 1;
        }else if(quantidadeQueFalta0<quantidadeQueFalta1){
            return -1;
        }else{
            return 0;
        }
    }

}