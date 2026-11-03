package dominio;

import entidad.eTelefono;
import persistencia.pTelefono;
import java.sql.SQLException;
import java.util.ArrayList;

public class dTelefono
{
    private pTelefono unP;

    public dTelefono()
    {
        unP = new pTelefono();
    }

    public void alta(eTelefono t)
    {
        validar(t);
        unP.alta(t);
    }

    public void modificar(eTelefono t)
    {
        validar(t);
        unP.modificar(t);
    }

    public void eliminar(String cedula, String tel)
    {
        unP.eliminar(cedula, tel);
    }

    public ArrayList<eTelefono> listar() throws SQLException
    {
        return unP.listar();
    }

    private void validar(eTelefono t)
    {
        if (t.getTelefono() == null || t.getTelefono().trim().isEmpty()) {
            throw new IllegalArgumentException("telefono vacio");
        }
    }
}
