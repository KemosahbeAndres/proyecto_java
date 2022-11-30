package stomas.andres.controllers;

import stomas.andres.entitys.Vectorizable;
import stomas.andres.models.BuyProductModel;

import java.util.Vector;

public class AddProductController {
    private BuyProductModel model;
    public AddProductController(BuyProductModel model){
        this.model = model;
    }

    public Vector<Vectorizable> selectAll(){
        return null;
    }
}
