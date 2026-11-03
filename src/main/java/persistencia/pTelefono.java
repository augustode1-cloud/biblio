package persistencia;

import entidad.eTelefono;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class pTelefono
{
    private Conexion conexion = Conexion.getInstance();
    public void alta(eTelefono t)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO telefonos (cedulaPersona, telefono) ");
        sb.append("VALUES ('");
        sb.append(t.getCedulaPersona());
        sb.append("','");
        sb.append(t.getTelefono());
        sb.append("');");
        conexion.ejecutarSQL(sb.toString());
    }

    public void modificar(eTelefono t)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE telefonos SET telefono = '");
        sb.append(t.getTelefono());
        sb.append("' WHERE cedulaPersona = '");
        sb.append(t.getCedulaPersona());
        sb.append("' AND telefono = '");
        sb.append(t.getTelefono());
        sb.append("';");
        conexion.ejecutarSQL(sb.toString());
    }

    public void eliminar(String cedula, String tel)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM telefonos WHERE cedulaPersona = '");
        sb.append(cedula);
        sb.append("' AND telefono = '");
        sb.append(tel);
        sb.append("';");
        conexion.ejecutarSQL(sb.toString());
    }

    public ArrayList<eTelefono> listar() throws SQLException
    {
        ArrayList<eTelefono> lista = new ArrayList<>();
        String sql = "SELECT cedulaPersona, telefono FROM telefonos";
        ResultSet rs = conexion.ejecutarConsulta(sql);
        while (rs != null && rs.next()) {
            eTelefono t = new eTelefono();
            t.setCedulaPersona(rs.getString("cedulaPersona"));
            t.setTelefono(rs.getString("telefono"));
            lista.add(t);
        }
        conexion.cerrar();
        return lista;
    }
}
