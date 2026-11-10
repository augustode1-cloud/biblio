package dominio;

import entidad.ePrestamo;
import persistencia.pPrestamo;
import java.sql.SQLException;
import java.util.ArrayList;

public class dPrestamo
{
    private pPrestamo unP;

    public dPrestamo()
    {
        unP = new pPrestamo();
    }

    public void alta(ePrestamo p)
    {
        validar(p);
        unP.alta(p);
    }

    public void modificar(ePrestamo p)
    {
        validar(p);
        unP.modificar(p);
    }

    public void eliminar(int id)
    {
        if (id <= 0) {
            throw new IllegalArgumentException("id obligatorio");
        }
        unP.eliminar(id);
    }


    public ArrayList<String> listarSociosPrestamoVenc(String strFechaLimite)
		                                        throws SQLException
    {
        return unP.listarSociosPrestamoVenc(strFechaLimite);
    }


    public ArrayList<ePrestamo> listar() throws SQLException
    {
        return unP.listar();
    }

    private void validar(ePrestamo p)
    {
        if (p.getCiSocio() == null || p.getCiSocio().trim().isEmpty()) {
            throw new IllegalArgumentException("socio obligatorio");
        }
    }
}
