package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion
{
    private static Conexion instancia;
    private final String url =
            "jdbc:mysql://localhost:3306/biblioteca1";
    private final String usuario = "admin";
    private final String password = "";
    private Connection con;
    private Statement statement;

    private Conexion()
    {
    }

    public static Conexion getInstance()
    {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    public void conectar()
    {
        try {
            con = DriverManager.getConnection(url, usuario, password);
            statement = con.createStatement();
            System.out.println("Conexion establecida.");
        } catch (SQLException e) {
            System.out.println("Error al conectar.");
            e.printStackTrace();
        }
    }

    public int ejecutarSQL(String sql)
    {
        try {
            conectar();
            int cantidad = statement.executeUpdate(sql);
            return cantidad;
        } catch (SQLException e) {
            System.out.println("Error al ejecutar SQL.");
            e.printStackTrace();
            return 0;
        }
    }

    public ResultSet ejecutarConsulta(String sql)
    {
        try {
            conectar();
            ResultSet resultado = statement.executeQuery(sql);
            return resultado;
        } catch (SQLException e) {
            System.out.println("Error al ejecutar consulta.");
            e.printStackTrace();
            return null;
        }
    }

    public void cerrar()
    {
        try {
            if (con != null) {
                con.close();
            }
            con = null;
            statement = null;
            System.out.println("Conexion cerrada.");
        } catch (SQLException e) {
            System.out.println("Error al cerrar.");
            e.printStackTrace();
        }
    }
}
