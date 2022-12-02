package stomas.andres.views.tablas;

import stomas.andres.entitys.Vectorizable;

import java.util.Arrays;
import java.util.Vector;

public class ClientTable extends DefaultTable{
    public ClientTable(){
        super(new String[]{"ID","Nombre", "Run", "Direccion", "Telefono"});
    }



    @Override
    protected Class<?> getRealColumnClass(int columnIndex) {
        return super.getRealColumnClass(columnIndex);
    }
}
