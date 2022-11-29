package stomas.andres.controllers;

import stomas.andres.entitys.Cliente;
import stomas.andres.models.ClientModel;

import java.sql.SQLException;

public class NewClientController {
    private ClientModel model;
    public NewClientController(){
        model = new ClientModel();
    }
    public void execute(Cliente cliente) throws SQLException {
        model.insert(cliente);
    }
}
