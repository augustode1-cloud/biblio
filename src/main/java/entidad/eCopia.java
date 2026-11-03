package entidad;

public class eCopia
{
    private int idCopia;
    private String isbn;
    private FormatoCopia formato;

    public eCopia()
    {
    }

    public eCopia(int idCopia, String isbn, FormatoCopia formato)
    {
        this.idCopia = idCopia;
        this.isbn = isbn;
        this.formato = formato;
    }

    public int getIdCopia()
    {
        return idCopia;
    }

    public void setIdCopia(int idCopia)
    {
        this.idCopia = idCopia;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public FormatoCopia getFormato()
    {
        return formato;
    }

    public void setFormato(FormatoCopia formato)
    {
        this.formato = formato;
    }

    @Override
    public String toString()
    {
        return "Copia " + idCopia;
    }
}
