package stomas.andres.models;

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
}
