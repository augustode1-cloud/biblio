package dominio;

import entidad.eCopia;
import entidad.FormatoCopia;
import persistencia.pCopia;
import java.sql.SQLException;
import java.util.ArrayList;

public class dCopia
{
    private pCopia unP;

    public dCopia()
    {
        unP = new pCopia();
    }

    public void alta(eCopia c)
    {
        validar(c);
        unP.alta(c);
    }

    public void crearVarias(String isbn, FormatoCopia formato, int cant)
    {
        if (cant <= 0) {
            return;
        }
        for (int i = 0; i < cant; i++) {
            eCopia c = new eCopia();
            c.setIsbn(isbn);
            c.setFormato(formato);
            alta(c);
        }
    }

    public void modificar(eCopia c)
    {
        validar(c);
        unP.modificar(c);
    }

    public void eliminar(int id)
    {
        if (id <= 0) {
            throw new IllegalArgumentException("id obligatorio");
        }
        unP.eliminar(id);
    }

    public ArrayList<eCopia> listar() throws SQLException
    {
        return unP.listar();
    }

    public int contarPorIsbn(String isbn) throws SQLException
    {
        return unP.contarPorIsbn(isbn);
    }

    private void validar(eCopia c)
    {
        if (c.getIsbn() == null || c.getIsbn().trim().isEmpty()) {
            throw new IllegalArgumentException("isbn vacio");
        }
    }
}
