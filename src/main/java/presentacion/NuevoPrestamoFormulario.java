package presentacion;

import dominio.Fachada;
import entidad.eCopia;
import entidad.eEmpleado;
import entidad.eLibro;
import entidad.eSocio;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

public class NuevoPrestamoFormulario
{
    @FXML private ComboBox<String> socioCombo;
    @FXML private ComboBox<String> empleadoCombo;
    @FXML private ComboBox<String> libroCombo;
    @FXML private ComboBox<String> copiaCombo;
    @FXML private DatePicker fechaPrestamoPicker;
    @FXML private DatePicker fechaDevolucionPicker;

    private boolean ok;
    private Fachada fachada;

    @FXML
    private void initialize()
    {
        fachada = new Fachada();
        fechaPrestamoPicker.setValue(LocalDate.now());
        cargarCombos();
        libroCombo.setOnAction(e -> cargarCopias());
    }

    private void cargarCombos()
    {
        try {
            ArrayList<eSocio> socios = fachada.listarSocios();
            for (eSocio s : socios) {
                socioCombo.getItems().add(
                        s.getCedula() + " - " + s.getNombre());
            }
            ArrayList<eEmpleado> emps = fachada.listarEmpleados();
            for (eEmpleado emp : emps) {
                empleadoCombo.getItems().add(
                        emp.getCedula() + " - " + emp.getNombre());
            }
            ArrayList<eLibro> libs = fachada.listarLibros();
            for (eLibro l : libs) {
                libroCombo.getItems().add(
                        l.getIsbn() + " - " + l.getTitulo());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cargarCopias()
    {
        copiaCombo.getItems().clear();
        String sel = libroCombo.getValue();
        if (sel == null) {
            return;
        }
        String isbn = sel.split(" - ")[0];
        try {
            ArrayList<eCopia> copias = fachada.listarCopias();
            for (eCopia c : copias) {
                if (c.getIsbn().equals(isbn)) {
                    copiaCombo.getItems().add(
                            String.valueOf(c.getIdCopia()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onAceptar()
    {
        ok = true;
        cerrar();
    }

    @FXML
    private void onCancelar()
    {
        ok = false;
        cerrar();
    }

    private void cerrar()
    {
        ((Stage) socioCombo.getScene().getWindow()).close();
    }

    public boolean getResultado()
    {
        return ok;
    }

    public String getSocio()
    {
        String v = socioCombo.getValue();
        return v == null ? "" : v.split(" - ")[0];
    }

    public String getEmpleado()
    {
        String v = empleadoCombo.getValue();
        return v == null ? "" : v.split(" - ")[0];
    }

    public String getIsbn()
    {
        String v = libroCombo.getValue();
        return v == null ? "" : v.split(" - ")[0];
    }

    public int getIdCopia()
    {
        String v = copiaCombo.getValue();
        return v == null ? 0 : Integer.parseInt(v);
    }

    public LocalDate getFechaPrestamo()
    {
        return fechaPrestamoPicker.getValue();
    }

    public LocalDate getFechaDevolucion()
    {
        return fechaDevolucionPicker.getValue();
    }
}
