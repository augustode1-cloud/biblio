package presentacion;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NuevoLibroFormulario
{
    @FXML private TextField tituloField;
    @FXML private TextField isbnField;
    @FXML private ComboBox<String> formatoCombo;
    @FXML private TextField autorField;
    @FXML private TextField nacionalidadField;
    @FXML private TextField cantidadField;

    private boolean ok;

    @FXML
    private void initialize()
    {
        formatoCombo.getItems().addAll("FISICO", "ELECTRONICO");
        formatoCombo.getSelectionModel().selectFirst();
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
        ((Stage) tituloField.getScene().getWindow()).close();
    }

    public boolean getResultado()
    {
        return ok;
    }

    public String getTitulo()
    {
        return tituloField.getText().trim();
    }

    public String getIsbn()
    {
        return isbnField.getText().trim();
    }

    public String getFormato()
    {
        return formatoCombo.getValue();
    }

    public String getAutor()
    {
        return autorField.getText().trim();
    }

    public String getNacionalidad()
    {
        return nacionalidadField.getText().trim();
    }

    public String getCantidad()
    {
        return cantidadField.getText().trim();
    }
}
