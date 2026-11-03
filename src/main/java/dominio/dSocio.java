package dominio;

import entidad.eSocio;
import persistencia.pSocio;
import java.sql.SQLException;
import java.util.ArrayList;

public class dSocio
{
    private pSocio unP;

    public dSocio()
    {
        unP = new pSocio();
    }

    public void alta(eSocio s)
    {
        validar(s);
        unP.alta(s);
    }

    public void modificar(eSocio s)
    {
        validar(s);
        unP.modificar(s);
    }

    public void eliminar(String cedula)
    {
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new IllegalArgumentException("cedula obligatoria");
        }
        unP.eliminar(cedula);
    }

    public ArrayList<eSocio> listar() throws SQLException
    {
        return unP.listar();
    }

    private void validar(eSocio s)
    {
        if (s.getCedula() == null || s.getCedula().trim().isEmpty()) {
            throw new IllegalArgumentException("cedula vacia");
        }
    }
}
