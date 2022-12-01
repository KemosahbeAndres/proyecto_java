package stomas.andres.controllers;

import stomas.andres.entitys.Producto;
import stomas.andres.entitys.ProductoCompra;
import stomas.andres.entitys.Vectorizable;
import stomas.andres.models.ClientModel;
import stomas.andres.models.OrderModel;
import stomas.andres.models.ProductItemModel;
import stomas.andres.models.ProductModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ListProductsController {
    private ProductItemModel itemModel;
    public ListProductsController(ProductItemModel itemsModel) {
        itemModel = itemsModel;
    }

    public Vector<Vectorizable> execute() throws SQLException {
        Vector<Vectorizable> productos = new Vector<>();
        for(Vector<Object> o: itemModel.selectAll()){
            productos.add(new ProductoCompra(o));
        }
        return productos;
    }
}
