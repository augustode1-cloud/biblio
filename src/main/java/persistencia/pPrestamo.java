package persistencia;

import entidad.ePrestamo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class pPrestamo
{
    private Conexion conexion = Conexion.getInstance();
    public void alta(ePrestamo p)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO prestamos ");
        sb.append("(ciSocio, ciEmpleado, ISBN, idCopia, ");
        sb.append("fechaPrestamo, fechaDevolucion) VALUES ('");
        sb.append(p.getCiSocio());
        sb.append("','");
        sb.append(p.getCiEmpleado());
        sb.append("','");
        sb.append(p.getIsbn());
        sb.append("',");
        sb.append(p.getIdCopia());
        sb.append(",'");
        sb.append(p.getFechaPrestamo());
        sb.append("',");
        if (p.getFechaDevolucion() == null) {
            sb.append("NULL");
        } else {
            sb.append("'");
            sb.append(p.getFechaDevolucion());
            sb.append("'");
        }
        sb.append(");");
        conexion.ejecutarSQL(sb.toString());
    }

    public void modificar(ePrestamo p)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE prestamos SET fechaDevolucion = '");
        sb.append(p.getFechaDevolucion());
        sb.append("' WHERE idPrestamo = ");
        sb.append(p.getIdPrestamo());
        sb.append(";");
        conexion.ejecutarSQL(sb.toString());
    }

    public void eliminar(int id)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM prestamos WHERE idPrestamo = ");
        sb.append(id);
        sb.append(";");
        conexion.ejecutarSQL(sb.toString());
    }

    public ArrayList<ePrestamo> listar() throws SQLException
    {
        ArrayList<ePrestamo> lista = new ArrayList<>();
        String sql = "SELECT * FROM prestamos";
        ResultSet rs = conexion.ejecutarConsulta(sql);
        while (rs != null && rs.next()) {
            ePrestamo p = new ePrestamo();
            p.setIdPrestamo(rs.getInt("idPrestamo"));
            p.setCiSocio(rs.getString("ciSocio"));
            p.setCiEmpleado(rs.getString("ciEmpleado"));
            p.setIsbn(rs.getString("ISBN"));
            p.setIdCopia(rs.getInt("idCopia"));
            if (rs.getDate("fechaPrestamo") != null) {
                p.setFechaPrestamo(
                        rs.getDate("fechaPrestamo").toLocalDate());
            } if (rs.getDate("fechaDevolucion") != null) {
                p.setFechaDevolucion(
                        rs.getDate("fechaDevolucion").toLocalDate());
            }
            lista.add(p);
        }
        conexion.cerrar();
        return lista;
    }
}
