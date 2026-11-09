package persistencia;

import entidad.eCopia;
import entidad.FormatoCopia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class pCopia
{
    private Conexion conexion = Conexion.getInstance();
    public void alta(eCopia c)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO copias (ISBN, formato) ");
        sb.append("VALUES ('");
        sb.append(c.getIsbn());
        sb.append("','");
        sb.append(c.getFormato().name());
        sb.append("');");
        conexion.ejecutarSQL(sb.toString());
    }

    public void modificar(eCopia c)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE copias SET formato = '");
        sb.append(c.getFormato().name());
        sb.append("' WHERE idCopia = ");
        sb.append(c.getIdCopia());
        sb.append(";");
        conexion.ejecutarSQL(sb.toString());
    }

    public void eliminar(int id)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM copias WHERE idCopia = ");
        sb.append(id);
        sb.append(";");
        conexion.ejecutarSQL(sb.toString());
    }

    public ArrayList<eCopia> listar() throws SQLException
    {
        ArrayList<eCopia> lista = new ArrayList<>();
        String sql = "SELECT ISBN, idCopia, formato FROM copias";
        ResultSet rs = conexion.ejecutarConsulta(sql);
        while (rs != null && rs.next()) {
            eCopia c = new eCopia();
            c.setIsbn(rs.getString("ISBN"));
            c.setIdCopia(rs.getInt("idCopia"));
            c.setFormato(FormatoCopia.valueOf(rs.getString("formato")));
            lista.add(c);
        }
        conexion.cerrar();
        return lista;
    }


    public ArrayList<eCopia> listarPorISBN(String isbn) throws SQLException
    {
        ArrayList<eCopia> lista = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ISBN, idCopia, formato FROM copias WHERE ISBN = '");
        sb.append(isbn);
        sb.append("'");
	String sql = sb.toString();
        ResultSet rs = conexion.ejecutarConsulta(sql);
        while (rs != null && rs.next()) {
            eCopia c = new eCopia();
            c.setIsbn(rs.getString("ISBN"));
            c.setIdCopia(rs.getInt("idCopia"));
            c.setFormato(FormatoCopia.valueOf(rs.getString("formato")));
            lista.add(c);
        }
        conexion.cerrar();
        return lista;
    }


    public int contarPorIsbn(String isbn) throws SQLException
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(*) AS total FROM copias WHERE ISBN = '");
        sb.append(isbn);
        sb.append("'");
        ResultSet rs = conexion.ejecutarConsulta(sb.toString());
        int total = 0;
        if (rs != null && rs.next()) {
            total = rs.getInt("total");
        }
        conexion.cerrar();
        return total;
    }
}
