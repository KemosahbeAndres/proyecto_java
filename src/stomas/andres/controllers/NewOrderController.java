package stomas.andres.controllers;

import stomas.andres.entitys.Orden;
import stomas.andres.models.OrderModel;

import java.sql.SQLException;

public class NewOrderController {
    private OrderModel model;
    public NewOrderController(OrderModel model){
        this.model = model;
    }
    public void execute(Orden orden) throws SQLException {
        model.insert(orden);
    }
}
