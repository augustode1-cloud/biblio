package persistencia;

import entidad.eAutor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class pAutor
{
    private Conexion conexion = Conexion.getInstance();

    public void alta(eAutor a)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO autores (nombre, nacionalidad) ");
        sb.append("VALUES ('");
        sb.append(a.getNombre());
        sb.append("','");
        sb.append(a.getNacionalidad());
        sb.append("');");
        conexion.ejecutarSQL(sb.toString());
    }

    public void modificar(eAutor a)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE autores SET nombre = '");
        sb.append(a.getNombre());
        sb.append("', nacionalidad = '");
        sb.append(a.getNacionalidad());
        sb.append("' WHERE idAutor = ");
        sb.append(a.getId());
        sb.append(";");
        conexion.ejecutarSQL(sb.toString());
    }

    public void eliminar(int id)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM autores WHERE idAutor = ");
        sb.append(id);
        sb.append(";");
        conexion.ejecutarSQL(sb.toString());
    }

    public eAutor reconstruirAutor(ResultSet rs) throws SQLException
    {
        eAutor a = new eAutor();
        a.setId(rs.getInt("idAutor"));
        a.setNombre(rs.getString("nombre"));
        a.setNacionalidad(rs.getString("nacionalidad"));
	return a;
    }

    public ArrayList<eAutor> listar() throws SQLException
    {
        ArrayList<eAutor> lista = new ArrayList<>();
        String sql = "SELECT idAutor, nombre, nacionalidad FROM autores";
        ResultSet rs = conexion.ejecutarConsulta(sql);
        while (rs != null && rs.next()) {
            eAutor a = reconstruirAutor(rs);
            lista.add(a);
        }
        conexion.cerrar();
        return lista;
    }

    public eAutor buscarPorNombre(String nombre) throws SQLException
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT idAutor, nombre, nacionalidad FROM autores ");
        sb.append("WHERE nombre = '");
        sb.append(nombre);
        sb.append("'");
        ResultSet rs = conexion.ejecutarConsulta(sb.toString());
        eAutor a = null;
        if (rs != null && rs.next()) {
            a = reconstruirAutor(rs);
        }
        conexion.cerrar();
        return a;
    }
}
