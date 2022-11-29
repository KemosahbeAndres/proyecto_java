package stomas.andres.views.tablas;

import stomas.andres.entitys.Vectorizable;

import javax.swing.*;
import java.util.Date;
import java.util.Vector;

public class OrderTable extends DefaultTable{
    public OrderTable(){
        super(new String[]{"ID", "N°","AÑO","CLIENTE","MONTO","FECHA"});
    }

    @Override
    protected Class<?> getRealColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
            case 1:
            case 2:
                return Integer.class;
            case 4:
                return Double.class;
            case 5:
                return String.class;
            default:
                return String.class;
        }
    }

}
