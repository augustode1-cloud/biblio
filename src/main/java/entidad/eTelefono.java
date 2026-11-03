package entidad;

public class eTelefono
{
    private String cedulaPersona;
    private String telefono;

    public eTelefono()
    {
    }

    public eTelefono(String cedulaPersona, String telefono)
    {
        this.cedulaPersona = cedulaPersona;
        this.telefono = telefono;
    }

    public String getCedulaPersona()
    {
        return cedulaPersona;
    }

    public void setCedulaPersona(String cedulaPersona)
    {
        this.cedulaPersona = cedulaPersona;
    }

    public String getTelefono()
    {
        return telefono;
    }

    public void setTelefono(String telefono)
    {
        this.telefono = telefono;
    }

    @Override
    public String toString()
    {
        return telefono;
    }
}
