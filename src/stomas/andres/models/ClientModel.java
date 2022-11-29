package stomas.andres.models;

import stomas.andres.entitys.Vectorizable;

import java.sql.*;
import java.time.Instant;
import java.util.Vector;

public class ClientModel {
    private static final String tabla = "clientes";
    private DBManager manager;
    private Connection connection;
    public ClientModel(){
        manager = DBManager.getManager();
    }
    public Vector<Vector<Object>> selectAll() throws SQLException {
        Vector<Vector<Object>> clientes = new Vector<>();
        connection = manager.getConnection();
        Statement st = connection.createStatement();
        ResultSet result = st.executeQuery("SELECT * FROM "+tabla+";");
        while(result.next()){
            Vector<Object> cliente = new Vector<>();
            cliente.add(result.getInt("id"));
            cliente.add(result.getString("nombre"));
            cliente.add(result.getInt("telefono"));
            cliente.add(result.getString("direccion"));
            cliente.add(result.getString("run"));
            cliente.add(result.getTimestamp("fecha_cliente"));

            clientes.add(cliente);
        }
        result.close();
        connection.close();
        return clientes;
    }

    public void insert(Vectorizable object) throws SQLException {
        Vector<Object> vector = object.toVector();
        connection = manager.getConnection();
        String query = "INSERT INTO "+tabla+"(nombre, telefono, direccion, run, fecha_cliente) VALUES (?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, (String) vector.get(1));
        statement.setInt(2, (int) vector.get(2));
        statement.setString(3, (String) vector.get(3));
        statement.setString(4, (String) vector.get(4));
        statement.setTimestamp(5, (Timestamp) Timestamp.from(Instant.now()));

        statement.execute();

        connection.close();
    }
}
