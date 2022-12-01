package stomas.andres.controllers;

import stomas.andres.entitys.Cliente;
import stomas.andres.entitys.Orden;
import stomas.andres.entitys.ProductoCompra;
import stomas.andres.entitys.Vectorizable;
import stomas.andres.models.ClientModel;
import stomas.andres.models.OrderModel;
import stomas.andres.models.ProductItemModel;
import stomas.andres.models.ProductModel;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;
import java.util.Vector;

public class NewOrderController {
    private OrderModel oModel;
    private ProductModel pbModel;
    private ProductItemModel pModel;
    public NewOrderController(OrderModel oModel, ProductModel pbModel, ProductItemModel pModel){
        this.oModel = oModel;
        this.pbModel = pbModel;
        this.pModel = pModel;
    }
    public void execute(Vector<Vectorizable> vector, Cliente cliente) throws SQLException {
        Vector<ProductoCompra> productos = new Vector<>();
        int total = 0;
        for(Vectorizable o: vector){
            ProductoCompra producto = (ProductoCompra) o;
            productos.add(producto);
            total += producto.getTotal();
        }
        Instant ahora = Instant.now();
        Timestamp fecha = Timestamp.from(ahora);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        System.out.println(year);
        int id = cliente.getId();
        int numero = oModel.countAll() +1;

        Orden orden = new Orden(
                0,
                numero,
                year,
                id,
                total,
                fecha
        );
        oModel.insert(orden);
        orden = new Orden(oModel.searchByNumber(numero));
        for(ProductoCompra p: productos){
            pModel.insert(p, orden.getId());
            pbModel.updateStock(p);
        }
    }
}
