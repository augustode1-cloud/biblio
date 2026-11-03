package dominio;

import entidad.eAutor;
import persistencia.pAutor;
import java.sql.SQLException;
import java.util.ArrayList;

public class dAutor
{
    private pAutor unP;

    public dAutor()
    {
        unP = new pAutor();
    }

    public void alta(eAutor a)
    {
        validar(a);
        unP.alta(a);
    }

    public void modificar(eAutor a)
    {
        validar(a);
        if (a.getId() <= 0) {
            throw new IllegalArgumentException("id obligatorio");
        }
        unP.modificar(a);
    }

    public void eliminar(int id)
    {
        if (id <= 0) {
            throw new IllegalArgumentException("id obligatorio");
        }
        unP.eliminar(id);
    }

    public ArrayList<eAutor> listar() throws SQLException
    {
        return unP.listar();
    }

    public eAutor buscarPorNombre(String nombre) throws SQLException
    {
        return unP.buscarPorNombre(nombre);
    }

    private void validar(eAutor a)
    {
        if (a.getNombre() == null || a.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("nombre vacio");
        }
    }
}
