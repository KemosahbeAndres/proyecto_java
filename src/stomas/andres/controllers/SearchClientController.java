package stomas.andres.controllers;

import stomas.andres.entitys.Cliente;
import stomas.andres.entitys.Producto;
import stomas.andres.models.ClientModel;

import java.sql.SQLException;
import java.util.List;

final public class SearchClientController {
    private ClientModel model;
    public SearchClientController(ClientModel model){
        this.model = model;
    }
    public Cliente search(String nombre) throws SQLException {
        return new Cliente(model.searchByName(nombre.trim()));
    }
    public int existencias() throws SQLException {
        return model.countAll();
    }
}
