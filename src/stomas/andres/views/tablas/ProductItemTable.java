package stomas.andres.views.tablas;

public class ProductItemTable extends DefaultTable{
    public ProductItemTable(){
        super(new String[]{"ID", "Nombre", "Precio", "Cantidad", "Subtotal"});
    }
    public ProductItemTable(boolean f){
        super(new String[]{"ID", "Nombre", "Precio", "Cantidad", "Subtotal", "ID Compra"});
    }


}
