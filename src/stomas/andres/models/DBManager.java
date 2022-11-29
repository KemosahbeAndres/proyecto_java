package stomas.andres.models;

import java.sql.*;

public class DBManager {
    private static DBManager manager;
    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String db = "jdbc:mysql://localhost:3306/ordenes";
    private static final String usuario = "root";
    private static final String clave = "";
    private DBManager(){}

    public Connection getConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(db, usuario, clave);
        }catch(Exception e){
            System.out.println("Error de conexion.");
        }
        return conn;
    }

    public static DBManager getManager(){
        if(manager == null) manager = new DBManager();
        return manager;
    }

    private void insert(String table){
        try {
            Connection conn = getConnection();
            conn.setAutoCommit(false);
            String sql = "INSERT INTO "
                    +table.trim()
                    +" VALUES "
                    +"()";
            PreparedStatement ps = conn.prepareStatement(sql);


        }catch(Exception e){
            System.out.println("Error al insertar.");
        }
    }
}
