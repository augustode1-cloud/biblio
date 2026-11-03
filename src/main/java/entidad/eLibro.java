package entidad;

public class eLibro
{
    private String isbn;
    private String titulo;
    private int idAutor;

    public eLibro()
    {
    }

    public eLibro(String isbn, String titulo, int idAutor)
    {
        this.isbn = isbn;
        this.titulo = titulo;
        this.idAutor = idAutor;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public String getTitulo()
    {
        return titulo;
    }

    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    public int getIdAutor()
    {
        return idAutor;
    }

    public void setIdAutor(int idAutor)
    {
        this.idAutor = idAutor;
    }

    @Override
    public String toString()
    {
        return titulo;
    }
}
