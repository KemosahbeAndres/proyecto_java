package stomas.andres.controllers;

import stomas.andres.entitys.Vectorizable;
import stomas.andres.models.ProductItemModel;

import java.util.Vector;

public class AddProductController {
    private ProductItemModel model;
    public AddProductController(ProductItemModel model){
        this.model = model;
    }

    public Vector<Vectorizable> execute(){
        Vector<Vectorizable> productos = new Vector<>();
        return productos;
    }
}
