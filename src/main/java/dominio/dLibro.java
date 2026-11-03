package dominio;

import entidad.eLibro;
import persistencia.pLibro;
import java.sql.SQLException;
import java.util.ArrayList;

public class dLibro
{
    private pLibro unP;

    public dLibro()
    {
        unP = new pLibro();
    }

    public void alta(eLibro l)
    {
        validar(l);
        unP.alta(l);
    }

    public void modificar(eLibro l)
    {
        validar(l);
        unP.modificar(l);
    }

    public void eliminar(String isbn)
    {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("isbn obligatorio");
        }
        unP.eliminar(isbn);
    }

    public ArrayList<eLibro> listar() throws SQLException
    {
        return unP.listar();
    }

    private void validar(eLibro l)
    {
        if (l.getTitulo() == null || l.getTitulo().trim().isEmpty()) {
            throw new IllegalArgumentException("titulo vacio");
        }
        if (l.getIsbn() == null || l.getIsbn().trim().isEmpty()) {
            throw new IllegalArgumentException("isbn vacio");
        }
    }
}
