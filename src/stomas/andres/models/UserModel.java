package stomas.andres.models;

import stomas.andres.entitys.Vectorizable;

import java.sql.*;
import java.time.Instant;
import java.util.Vector;

public class UserModel {
    private static final String tabla = "usuarios";
    private DBManager manager;
    private Connection connection;
    public UserModel(){
        manager = DBManager.getManager();
    }
    public Vector<Vector<Object>> selectAll() throws SQLException {
        connection = manager.getConnection();
        Statement st = connection.createStatement();
        ResultSet result = st.executeQuery("SELECT * FROM "+tabla+";");
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

    public void insert(Vectorizable object) throws SQLException{
        Vector<Object> vector = object.toVector();
        connection = manager.getConnection();
        String query = "INSERT INTO "+tabla+"(usuario, password, fecha) VALUES (?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, (String) vector.get(1));
        statement.setString(2, (String) vector.get(2));
        statement.setTimestamp(3, (Timestamp) vector.get(3));

        statement.execute();

        connection.close();
    }

}
