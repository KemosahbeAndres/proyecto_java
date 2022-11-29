package stomas.andres.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            cliente.add(result.getInt("fecha_cliente"));

            clientes.add(cliente);
        }
        result.close();
        connection.close();
        return clientes;
    }
}
