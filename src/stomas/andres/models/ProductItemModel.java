package stomas.andres.models;

import stomas.andres.entitys.Vectorizable;

import java.sql.*;
import java.time.Instant;
import java.util.Vector;

public class ProductItemModel {
    private static final String tabla = "productos_compras";
    private DBManager manager;
    private Connection connection;
    public ProductItemModel(){
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
            producto.add(result.getDouble("precio"));
            producto.add(result.getDouble("cantidad"));
            producto.add(result.getDouble("total"));
            producto.add(result.getInt("id_compras"));

            productos.add(producto);
        }
        result.close();
        connection.close();
        return productos;
    }
    public void insert(Vectorizable object, int id) throws SQLException {
        Vector<Object> vector = object.toVector();
        connection = manager.getConnection();
        String query = "INSERT INTO "+tabla+"(nombre, precio, cantidad, total, id_compras) VALUES (?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, (String) vector.get(1));
        statement.setDouble(2, (double) vector.get(2));
        statement.setDouble(3, (double) vector.get(3));
        statement.setDouble(4, (double) vector.get(4));
        statement.setInt(5, id);

        statement.execute();

        connection.close();
    }

}
