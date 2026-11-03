package entidad;

import java.time.LocalDate;

public class ePrestamo
{
    private int idPrestamo;
    private String ciSocio;
    private String ciEmpleado;
    private String isbn;
    private int idCopia;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public ePrestamo()
    {
    }

    public ePrestamo(int idPrestamo, String ciSocio,
            String ciEmpleado, String isbn, int idCopia,
            LocalDate fechaPrestamo, LocalDate fechaDevolucion)
    {
        this.idPrestamo = idPrestamo;
        this.ciSocio = ciSocio;
        this.ciEmpleado = ciEmpleado;
        this.isbn = isbn;
        this.idCopia = idCopia;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getIdPrestamo()
    {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo)
    {
        this.idPrestamo = idPrestamo;
    }

    public String getCiSocio()
    {
        return ciSocio;
    }

    public void setCiSocio(String ciSocio)
    {
        this.ciSocio = ciSocio;
    }

    public String getCiEmpleado()
    {
        return ciEmpleado;
    }

    public void setCiEmpleado(String ciEmpleado)
    {
        this.ciEmpleado = ciEmpleado;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public int getIdCopia()
    {
        return idCopia;
    }

    public void setIdCopia(int idCopia)
    {
        this.idCopia = idCopia;
    }

    public LocalDate getFechaPrestamo()
    {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo)
    {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion()
    {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion)
    {
        this.fechaDevolucion = fechaDevolucion;
    }

    public void registrarPrestamo(LocalDate fecha)
    {
        this.fechaPrestamo = fecha;
    }

    public void registrarDevolucion(LocalDate fecha)
    {
        this.fechaDevolucion = fecha;
    }

    @Override
    public String toString()
    {
        return "Prestamo " + idPrestamo;
    }
}
