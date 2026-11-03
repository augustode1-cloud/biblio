package entidad;

public class eEmpleado extends ePersona
{
    private double sueldo;
    private String clave;

    public eEmpleado()
    {
        super();
    }

    public eEmpleado(String cedula, String nombre, String email,
            double sueldo, String clave)
    {
        super(cedula, nombre, email);
        this.sueldo = sueldo;
        this.clave = clave;
    }

    public double getSueldo()
    {
        return sueldo;
    }

    public void setSueldo(double sueldo)
    {
        this.sueldo = sueldo;
    }

    public String getClave()
    {
        return clave;
    }

    public void setClave(String clave)
    {
        this.clave = clave;
    }

    public boolean validarAcceso(String clave)
    {
        return this.clave != null && this.clave.equals(clave);
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}
