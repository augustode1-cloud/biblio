package persistencia;

import entidad.eSocio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class pSocio
{
    private Conexion conexion = Conexion.getInstance();
    public void alta(eSocio s)
    {
        StringBuilder sb1 = new StringBuilder();
        sb1.append("INSERT INTO persona (cedula, nombre, email) ");
        sb1.append("VALUES ('");
        sb1.append(s.getCedula());
        sb1.append("','");
        sb1.append(s.getNombre());
        sb1.append("','");
        sb1.append(s.getEmail());
        sb1.append("');");
        conexion.ejecutarSQL(sb1.toString());

        StringBuilder sb2 = new StringBuilder();
        sb2.append("INSERT INTO socios (cedula, sancionado) ");
        sb2.append("VALUES ('");
        sb2.append(s.getCedula());
        sb2.append("',");
        sb2.append(s.isSancionado() ? 1 : 0);
        sb2.append(");");
        conexion.ejecutarSQL(sb2.toString());
    }

    public void modificar(eSocio s)
    {
        StringBuilder sb1 = new StringBuilder();
        sb1.append("UPDATE persona SET nombre = '");
        sb1.append(s.getNombre());
        sb1.append("', email = '");
        sb1.append(s.getEmail());
        sb1.append("' WHERE cedula = '");
        sb1.append(s.getCedula());
        sb1.append("';");
        conexion.ejecutarSQL(sb1.toString());

        StringBuilder sb2 = new StringBuilder();
        sb2.append("UPDATE socios SET sancionado = ");
        sb2.append(s.isSancionado() ? 1 : 0);
        sb2.append(" WHERE cedula = '");
        sb2.append(s.getCedula());
        sb2.append("';");
        conexion.ejecutarSQL(sb2.toString());
    }

    public void eliminar(String cedula)
    {
        StringBuilder sb1 = new StringBuilder();
        sb1.append("DELETE FROM socios WHERE cedula = '");
        sb1.append(cedula);
        sb1.append("';");
        conexion.ejecutarSQL(sb1.toString());

        StringBuilder sb2 = new StringBuilder();
        sb2.append("DELETE FROM persona WHERE cedula = '");
        sb2.append(cedula);
        sb2.append("';");
        conexion.ejecutarSQL(sb2.toString());
    }

    public ArrayList<eSocio> listar() throws SQLException
    {
        ArrayList<eSocio> lista = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT p.cedula, p.nombre, p.email, s.sancionado ");
        sb.append("FROM persona p INNER JOIN socios s ");
        sb.append("ON p.cedula = s.cedula");
        ResultSet rs = conexion.ejecutarConsulta(sb.toString());
        while (rs != null && rs.next()) {
            eSocio s = new eSocio();
            s.setCedula(rs.getString("cedula"));
            s.setNombre(rs.getString("nombre"));
            s.setEmail(rs.getString("email"));
            s.setSancionado(rs.getInt("sancionado") == 1);
            lista.add(s);
        }
        conexion.cerrar();
        return lista;
    }
}
