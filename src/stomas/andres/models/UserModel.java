package stomas.andres.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class UserModel {
    private static final String tabla = "usuarios";
    private DBManager manager;
    private Connection connection;
    public UserModel(){
        manager = DBManager.getManager();
    }
    public Vector<Vector<Object>> login() throws SQLException {
        connection = manager.getConnection();
        Statement st = connection.createStatement();
        ResultSet result = st.executeQuery("SELECT * FROM "+tabla+" LIMIT 1;");
        Vector<Vector<Object>> usuarios = new Vector<>();
        while(result.next()){
            Vector<Object> usuario = new Vector<>();
            usuario.add(result.getInt("id"));
            usuario.add(result.getString("usuario"));
            usuario.add(result.getString("password"));
            usuario.add(result.getTimestamp("fecha"));
            usuarios.add(usuario);
        }
        result.close();
        connection.close();
        return usuarios;
    }

}
