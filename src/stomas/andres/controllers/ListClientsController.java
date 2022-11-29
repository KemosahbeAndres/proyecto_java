package stomas.andres.controllers;

import stomas.andres.entitys.Cliente;
import stomas.andres.entitys.Producto;
import stomas.andres.entitys.Vectorizable;
import stomas.andres.models.ClientModel;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class ListClientsController {
    private ClientModel model;
    public ListClientsController(ClientModel model){
        this.model = model;
    }
    public Vector<Vectorizable> execute() throws SQLException {
        Vector<Vectorizable> clientes = new Vector<>();
        for(Vector<Object> vector: model.selectAll()){
            clientes.add(new Cliente(vector));
        }
        return clientes;
    }
}
