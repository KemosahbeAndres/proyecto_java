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
            cliente.add(result.getString("run"));
            cliente.add(result.getString("direccion"));
            cliente.add(result.getInt("telefono"));
            cliente.add(result.getTimestamp("fecha_cliente"));

            clientes.add(cliente);
        }
        result.close();
        connection.close();
        return clientes;
    }
    public Vector<Object> searchByName(String name) throws SQLException {
        Vector<Object> vector = new Vector<>();
        connection = manager.getConnection();
        Statement st = connection.createStatement();
        ResultSet result = st.executeQuery("SELECT * FROM "+tabla+" WHERE nombre LIKE '%"+name.trim()+"%' LIMIT 1;");
        while(result.next()){
            vector.add(result.getInt("id"));
            vector.add(result.getString("nombre"));
            vector.add(result.getString("run"));
            vector.add(result.getString("direccion"));
            vector.add(result.getInt("telefono"));
            vector.add(result.getTimestamp("fecha_cliente"));
        }
        result.close();
        connection.close();
        return vector;
    }
    public Vector<Object> searchByID(int id) throws SQLException {
        Vector<Object> vector = new Vector<>();
        connection = manager.getConnection();
        Statement st = connection.createStatement();
        ResultSet result = st.executeQuery("SELECT * FROM "+tabla+" WHERE id = "+id+" LIMIT 1;");
        while(result.next()){
            vector.add(result.getInt("id"));
            vector.add(result.getString("nombre"));
            vector.add(result.getString("run"));
            vector.add(result.getString("direccion"));
            vector.add(result.getInt("telefono"));
            vector.add(result.getTimestamp("fecha_cliente"));
        }
        result.close();
        connection.close();
        return vector;
    }
    public int count(String name) throws SQLException{
        int cantidad = 0;
        connection = manager.getConnection();
        Statement st = connection.createStatement();
        ResultSet result = st.executeQuery("SELECT COUNT(*) FROM "+tabla+" WHERE nombre LIKE '%"+name.trim()+"%';");
        while(result.next()){
            cantidad = (int) result.getInt(1);
        }
        result.close();
        connection.close();
        return cantidad;
    }
    public int countAll() throws SQLException{
        int cantidad = 0;
        connection = manager.getConnection();
        Statement st = connection.createStatement();
        ResultSet result = st.executeQuery("SELECT COUNT(*) FROM "+tabla+";");
        while(result.next()){
            cantidad = (int) result.getInt(1);
        }
        result.close();
        connection.close();
        return cantidad;
    }

    public void insert(Vectorizable object) throws SQLException {
        Vector<Object> vector = object.toVector();
        connection = manager.getConnection();
        String query = "INSERT INTO "+tabla+"(nombre, run, direccion, telefono, fecha_cliente) VALUES (?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, (String) vector.get(1));
        statement.setString(2, (String) vector.get(2));
        statement.setString(3, (String) vector.get(3));
        statement.setInt(4, (int) vector.get(4));
        statement.setTimestamp(5, (Timestamp) Timestamp.from(Instant.now()));

        statement.execute();

        connection.close();
    }
}
