package stomas.andres.controllers;

import stomas.andres.entitys.Producto;
import stomas.andres.entitys.Vectorizable;
import stomas.andres.models.ProductModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ListProductsController {
    private ProductModel model;
    public ListProductsController(ProductModel productModel) {
        model = productModel;
    }

    public Vector<Vectorizable> execute() throws SQLException {
        Vector<Vectorizable> productos = new Vector<>();
        for(Vector<Object> o: model.selectAll()){
            productos.add(new Producto(o));
        }
        return productos;
    }
}
