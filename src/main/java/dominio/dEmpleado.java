package dominio;

import entidad.eEmpleado;
import persistencia.pEmpleado;
import java.sql.SQLException;
import java.util.ArrayList;

public class dEmpleado
{
    private pEmpleado unP;

    public dEmpleado()
    {
        unP = new pEmpleado();
    }

    public void alta(eEmpleado e)
    {
        validar(e);
        unP.alta(e);
    }

    public void modificar(eEmpleado e)
    {
        validar(e);
        unP.modificar(e);
    }

    public void eliminar(String cedula)
    {
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new IllegalArgumentException("cedula obligatoria");
        }
        unP.eliminar(cedula);
    }

    public ArrayList<eEmpleado> listar() throws SQLException
    {
        return unP.listar();
    }

    private void validar(eEmpleado e)
    {
        if (e.getCedula() == null || e.getCedula().trim().isEmpty()) {
            throw new IllegalArgumentException("cedula vacia");
        }
    }
}
