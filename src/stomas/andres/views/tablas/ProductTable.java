package stomas.andres.views.tablas;

import stomas.andres.entitys.Vectorizable;

import java.util.Vector;

public class ProductTable extends DefaultTable{

    public ProductTable(){
        super(new String[]{"ID", "Nombre", "Precio", "Stock"});
    }


    @Override
    protected Class<?> getRealColumnClass(int columnIndex) {
        return super.getRealColumnClass(columnIndex);
    }

}
