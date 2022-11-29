package stomas.andres.controllers;

import stomas.andres.models.UserModel;

import java.sql.SQLException;
import java.util.Vector;

final public class LoginController {

    public LoginController(){

    }
    public boolean execute(String usuario, String contraseña) throws SQLException {
        UserModel model = new UserModel();
        Vector<Vector<Object>> usuarios = model.login();
        for(Vector<Object> v: usuarios){
            if(((String)v.get(1)).equals(usuario)  && ((String)v.get(2)).equals(contraseña)){
                return true;
            }
        }
        return false;
    }
}
