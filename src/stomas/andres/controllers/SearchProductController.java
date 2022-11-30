package stomas.andres.controllers;

import stomas.andres.entitys.Producto;
import stomas.andres.models.ProductModel;

import java.sql.SQLException;

public class SearchProductController {
    private ProductModel model;
    public SearchProductController(ProductModel model){
        this.model = model;
    }

    public Producto search(String nombre) throws SQLException {
        return new Producto(model.searchByName(nombre.trim()));
    }
    public int existencias(String nombre) throws SQLException {
        return model.count(nombre.trim());
    }
}
