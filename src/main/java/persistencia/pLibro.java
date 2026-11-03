package persistencia;

import entidad.eLibro;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class pLibro
{
    private Conexion conexion = Conexion.getInstance();
    public void alta(eLibro l)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO libros (ISBN, titulo, idAutor) ");
        sb.append("VALUES ('");
        sb.append(l.getIsbn());
        sb.append("','");
        sb.append(l.getTitulo());
        sb.append("',");
        sb.append(l.getIdAutor());
        sb.append(");");
        conexion.ejecutarSQL(sb.toString());
    }

    public void modificar(eLibro l)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE libros SET titulo = '");
        sb.append(l.getTitulo());
        sb.append("', idAutor = ");
        sb.append(l.getIdAutor());
        sb.append(" WHERE ISBN = '");
        sb.append(l.getIsbn());
        sb.append("';");
        conexion.ejecutarSQL(sb.toString());
    }

    public void eliminar(String isbn)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM libros WHERE ISBN = '");
        sb.append(isbn);
        sb.append("';");
        conexion.ejecutarSQL(sb.toString());
    }

    public ArrayList<eLibro> listar() throws SQLException
    {
        ArrayList<eLibro> lista = new ArrayList<>();
        String sql = "SELECT ISBN, titulo, idAutor FROM libros";
        ResultSet rs = conexion.ejecutarConsulta(sql);
        while (rs != null && rs.next()) {
            eLibro l = new eLibro();
            l.setIsbn(rs.getString("ISBN"));
            l.setTitulo(rs.getString("titulo"));
            l.setIdAutor(rs.getInt("idAutor"));
            lista.add(l);
        }
        conexion.cerrar();
        return lista;
    }
}
