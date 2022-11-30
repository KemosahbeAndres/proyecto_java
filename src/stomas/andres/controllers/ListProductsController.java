package stomas.andres.controllers;

import stomas.andres.entitys.Producto;
import stomas.andres.entitys.ProductoCompra;
import stomas.andres.entitys.Vectorizable;
import stomas.andres.models.ProductItemModel;
import stomas.andres.models.ProductModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ListProductsController {
    private ProductModel productModel;
    private ProductItemModel itemModel;
    private Vector<ProductoCompra> glosa;
    private Vector<Producto> productos;
    public ListProductsController(ProductModel productsModel, ProductItemModel itemsModel) {
        productModel = productsModel;
        itemModel = itemsModel;
    }

    public Vector<Vectorizable> execute() throws SQLException {
        Vector<Vectorizable> productos = new Vector<>();
        for(Vector<Object> o: itemModel.selectAll()){
            productos.add(new Producto(o));
        }
        return productos;
    }
}
