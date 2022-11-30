package stomas.andres.controllers;

import stomas.andres.entitys.Usuario;
import stomas.andres.models.UserModel;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Vector;

public class RegisterController {
    private UserModel model;
    public RegisterController(UserModel model){
        this.model = model;
    }
    public void execute(String usuario, String contraseña) throws SQLException, Exception {
        UserModel model = new UserModel();
        Vector<Vector<Object>> usuarios = model.selectAll();
        for(Vector<Object> v: usuarios){
            if(((String)v.get(2)).equals(contraseña)){
                throw new Exception("Contraseña repetida");
            }
        }
        model.insert(new Usuario(
                0,
                usuario,
                contraseña,
                Timestamp.from(Instant.now())
        ));
    }

}
