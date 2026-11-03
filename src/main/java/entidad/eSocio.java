package entidad;

public class eSocio extends ePersona
{
    private boolean sancionado;

    public eSocio()
    {
        super();
    }

    public eSocio(String cedula, String nombre, String email,
            boolean sancionado)
    {
        super(cedula, nombre, email);
        this.sancionado = sancionado;
    }

    public boolean isSancionado()
    {
        return sancionado;
    }

    public void setSancionado(boolean sancionado)
    {
        this.sancionado = sancionado;
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}
