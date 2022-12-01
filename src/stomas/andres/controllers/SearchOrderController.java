package stomas.andres.controllers;

import stomas.andres.entitys.Cliente;
import stomas.andres.entitys.Orden;
import stomas.andres.entitys.OrderData;
import stomas.andres.models.ClientModel;
import stomas.andres.models.OrderModel;

import java.sql.SQLException;

public class SearchOrderController {
    private OrderModel orderModel;
    private ClientModel clientModel;
    public SearchOrderController(OrderModel oModel, ClientModel cModel){
        orderModel = oModel;
        clientModel = cModel;
    }

    public OrderData execute(int idOrden) throws SQLException {
        Orden o = new Orden(orderModel.searchByID(idOrden));
        Cliente c = new Cliente(clientModel.searchByID(o.getId_cliente()));
        return new OrderData(o, c);
    }
}
