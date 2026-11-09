package dominio;

import entidad.eAutor;
import entidad.eCopia;
import entidad.eEmpleado;
import entidad.eLibro;
import entidad.ePrestamo;
import entidad.eSocio;
import entidad.FormatoCopia;
import java.sql.SQLException;
import java.util.ArrayList;

public class Fachada
{
    private dAutor dAut;
    private dLibro dLib;
    private dCopia dCop;
    private dPrestamo dPre;
    private dSocio dSoc;
    private dEmpleado dEmp;

    public Fachada()
    {
        dAut = new dAutor();
        dLib = new dLibro();
        dCop = new dCopia();
        dPre = new dPrestamo();
        dSoc = new dSocio();
        dEmp = new dEmpleado();
    }

    public ArrayList<eLibro> listarLibros() throws SQLException
    {
        return dLib.listar();
    }

    public ArrayList<eAutor> listarAutores() throws SQLException
    {
        return dAut.listar();
    }

    public eAutor buscarAutorPorNombre(String nombre) throws SQLException
    {
        return dAut.buscarPorNombre(nombre);
    }

    public void altaAutor(eAutor autor) throws SQLException
    {
        dAut.alta(autor);
    }

    public void modificarAutor(eAutor autor) throws SQLException
    {
        dAut.modificar(autor);
    }

    public void altaLibro(eLibro libro) throws SQLException
    {
        dLib.alta(libro);
    }

    public void modificarLibro(eLibro libro) throws SQLException
    {
        dLib.modificar(libro);
    }

    public void eliminarLibro(String isbn) throws SQLException
    {
        dLib.eliminar(isbn);
    }


    public ArrayList<eCopia> listarCopiasPorISBN(String isbn)
		    				throws SQLException
    {
        return dCop.listarPorISBN(isbn);
    }

    public ArrayList<eCopia> listarCopias() throws SQLException
    {
        return dCop.listar();
    }

    public void crearVariasCopias(String isbn, FormatoCopia formato,
            int cantidad) throws SQLException
    {
        dCop.crearVarias(isbn, formato, cantidad);
    }

    public void eliminarCopia(int idCopia) throws SQLException
    {
        dCop.eliminar(idCopia);
    }

    public ArrayList<ePrestamo> listarPrestamos() throws SQLException
    {
        return dPre.listar();
    }

    public void altaPrestamo(ePrestamo prestamo) throws SQLException
    {
        dPre.alta(prestamo);
    }

    public void modificarPrestamo(ePrestamo prestamo) throws SQLException
    {
        dPre.modificar(prestamo);
    }

    public ArrayList<eSocio> listarSocios() throws SQLException
    {
        return dSoc.listar();
    }

    public void altaSocio(eSocio socio) throws SQLException
    {
        dSoc.alta(socio);
    }

    public ArrayList<eEmpleado> listarEmpleados() throws SQLException
    {
        return dEmp.listar();
    }

    public void altaEmpleado(eEmpleado empleado) throws SQLException
    {
        dEmp.alta(empleado);
    }
}
