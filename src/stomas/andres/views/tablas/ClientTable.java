package stomas.andres.views.tablas;

import java.util.Arrays;
import java.util.Vector;

public class ClientTable extends DefaultTable{
    public ClientTable(){
        super(new String[]{"Nombre", "Run", "Direccion", "Correo"});
    }

    @Override
    protected Class<?> getRealColumnClass(int columnIndex) {
        return super.getRealColumnClass(columnIndex);
    }
}
