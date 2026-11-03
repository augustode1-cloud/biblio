package persistencia;

import entidad.eEmpleado;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class pEmpleado
{
    private Conexion conexion = Conexion.getInstance();
    public void alta(eEmpleado e)
    {
        StringBuilder sb1 = new StringBuilder();
        sb1.append("INSERT INTO persona (cedula, nombre, email) ");
        sb1.append("VALUES ('");
        sb1.append(e.getCedula());
        sb1.append("','");
        sb1.append(e.getNombre());
        sb1.append("','");
        sb1.append(e.getEmail());
        sb1.append("');");
        conexion.ejecutarSQL(sb1.toString());

        StringBuilder sb2 = new StringBuilder();
        sb2.append("INSERT INTO empleados (cedula, sueldo, clave) ");
        sb2.append("VALUES ('");
        sb2.append(e.getCedula());
        sb2.append("',");
        sb2.append(e.getSueldo());
        sb2.append(",'");
        sb2.append(e.getClave());
        sb2.append("');");
        conexion.ejecutarSQL(sb2.toString());
    }

    public void modificar(eEmpleado e)
    {
        StringBuilder sb1 = new StringBuilder();
        sb1.append("UPDATE persona SET nombre = '");
        sb1.append(e.getNombre());
        sb1.append("', email = '");
        sb1.append(e.getEmail());
        sb1.append("' WHERE cedula = '");
        sb1.append(e.getCedula());
        sb1.append("';");
        conexion.ejecutarSQL(sb1.toString());

        StringBuilder sb2 = new StringBuilder();
        sb2.append("UPDATE empleados SET sueldo = ");
        sb2.append(e.getSueldo());
        sb2.append(", clave = '");
        sb2.append(e.getClave());
        sb2.append("' WHERE cedula = '");
        sb2.append(e.getCedula());
        sb2.append("';");
        conexion.ejecutarSQL(sb2.toString());
    }

    public void eliminar(String cedula)
    {
        StringBuilder sb1 = new StringBuilder();
        sb1.append("DELETE FROM empleados WHERE cedula = '");
        sb1.append(cedula);
        sb1.append("';");
        conexion.ejecutarSQL(sb1.toString());

        StringBuilder sb2 = new StringBuilder();
        sb2.append("DELETE FROM persona WHERE cedula = '");
        sb2.append(cedula);
        sb2.append("';");
        conexion.ejecutarSQL(sb2.toString());
    }

    public ArrayList<eEmpleado> listar() throws SQLException
    {
        ArrayList<eEmpleado> lista = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT p.cedula, p.nombre, p.email, ");
        sb.append("e.sueldo, e.clave ");
        sb.append("FROM persona p INNER JOIN empleados e ");
        sb.append("ON p.cedula = e.cedula");
        ResultSet rs = conexion.ejecutarConsulta(sb.toString());
        while (rs != null && rs.next()) {
            eEmpleado emp = new eEmpleado();
            emp.setCedula(rs.getString("cedula"));
            emp.setNombre(rs.getString("nombre"));
            emp.setEmail(rs.getString("email"));
            emp.setSueldo(rs.getDouble("sueldo"));
            emp.setClave(rs.getString("clave"));
            lista.add(emp);
        }
        conexion.cerrar();
        return lista;
    }
}
