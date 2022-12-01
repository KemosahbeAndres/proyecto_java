package stomas.andres.models;

import stomas.andres.entitys.Vectorizable;

import javax.swing.*;
import java.sql.*;
import java.time.Instant;
import java.util.Vector;

public class OrderModel {
    private static final String tabla = "compras";
    private DBManager manager;
    private Connection connection;
    public OrderModel(){
        manager = DBManager.getManager();
    }
    public Vector<Vector<Object>> selectAll() throws SQLException {
        Vector<Vector<Object>> ordenes = new Vector<>();
        connection = manager.getConnection();
        Statement st = connection.createStatement();
        ResultSet result = st.executeQuery("SELECT * FROM "+tabla+";");
        while(result.next()){
            Vector<Object> orden = new Vector<>();
            orden.add(result.getInt("id"));
            orden.add(result.getInt("numero"));
            orden.add(result.getInt("year"));
            orden.add(result.getInt("id_clientes"));
            orden.add(result.getDouble("monto"));
            orden.add(result.getTimestamp("fecha_compra"));

            ordenes.add(orden);
        }
        result.close();
        connection.close();
        return ordenes;
    }
    public void insert(Vectorizable object) throws SQLException {
        Vector<Object> vector = object.toVector();
        connection = manager.getConnection();
        String query = "INSERT INTO "+tabla+"(numero, year, id_clientes, monto, fecha_compra) VALUES (?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setInt(1, (int) vector.get(1));
        statement.setInt(2, (int) vector.get(2));
        statement.setInt(3, (int) vector.get(3));
        statement.setDouble(4, (double) vector.get(4));
        statement.setTimestamp(5, (Timestamp) Timestamp.from(Instant.now()));

        statement.execute();

        connection.close();
    }
    public Vector<Object> searchByNumber(int number) throws SQLException {
        Vector<Object> vector = new Vector<>();
        connection = manager.getConnection();
        Statement st = connection.createStatement();
        ResultSet result = st.executeQuery("SELECT * FROM "+tabla+" WHERE numero = "+number+" LIMIT 1;");
        while(result.next()){
            vector.add(result.getInt("id"));
            vector.add(result.getInt("numero"));
            vector.add(result.getInt("year"));
            vector.add(result.getInt("id_clientes"));
            vector.add(result.getDouble("monto"));
            vector.add(result.getTimestamp("fecha_compra"));
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
}
