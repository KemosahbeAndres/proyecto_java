package stomas.andres.models;

import stomas.andres.entitys.Vectorizable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ProductModel {
    private static final String tabla = "productos_base";
    private DBManager manager;
    private Connection connection;
    public ProductModel(){
        manager = DBManager.getManager();
    }
    public Vector<Vector<Object>> selectAll() throws SQLException {
        Vector<Vector<Object>> productos = new Vector<>();
        connection = manager.getConnection();
        Statement st = connection.createStatement();
        ResultSet result = st.executeQuery("SELECT * FROM "+tabla+";");
        while(result.next()){
            Vector<Object> producto = new Vector<>();
            producto.add(result.getInt("id"));
            producto.add(result.getString("nombre"));
            producto.add(result.getInt("precio"));
            producto.add(result.getInt("stock"));

            productos.add(producto);
        }
        result.close();
        connection.close();
        return productos;
    }
    public Vector<Object> searchByName(String name) throws SQLException {
        Vector<Object> vector = new Vector<>();
        connection = manager.getConnection();
        Statement st = connection.createStatement();
        ResultSet result = st.executeQuery("SELECT * FROM "+tabla+" WHERE nombre LIKE '%"+name.trim()+"%' LIMIT 1;");
        while(result.next()){
            vector.add(result.getInt("id"));
            vector.add(result.getString("nombre"));
            vector.add(result.getInt("precio"));
            vector.add(result.getInt("stock"));
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
}
