package backend.ajude.ordenacao;

import java.util.Date;

import backend.ajude.entidades.Campanha;

public class OrdenacaoPorData implements Comparadores{

    @Override
    public int compare(Object arg0, Object arg1) {
        Campanha campanha0= (Campanha) arg0;
        Campanha campanha1= (Campanha) arg1;

        Date data0 = campanha0.getData();
        Date data1 = campanha1.getData();

        if(data0.compareTo(data1)>0){
            return 1;
        }else if(data0.compareTo(data1)<0){
            return -1;
        }else{
            return 0;
        }
    }

}