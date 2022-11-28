package stomas.andres.views.tablas;

import java.util.Arrays;
import java.util.Vector;

public class ProductTable extends DefaultTable{

    public ProductTable(){
        super(new String[]{"Nombre", "Run", "Direccion", "Correo"});
    }

    @Override
    protected Class<?> getRealColumnClass(int columnIndex) {
        return super.getRealColumnClass(columnIndex);
    }

    @Override
    public void insertData(Vector<Vector<Object>> filas) {

    }
}
