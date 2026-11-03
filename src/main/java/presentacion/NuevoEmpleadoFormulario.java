package presentacion;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NuevoEmpleadoFormulario
{
    @FXML private TextField cedulaField;
    @FXML private TextField nombreField;
    @FXML private TextField emailField;
    @FXML private TextField sueldoField;
    @FXML private TextField claveField;

    private boolean ok;

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
        ((Stage) cedulaField.getScene().getWindow()).close();
    }

    public boolean getResultado()
    {
        return ok;
    }

    public String getCedula()
    {
        return cedulaField.getText().trim();
    }

    public String getNombre()
    {
        return nombreField.getText().trim();
    }

    public String getEmail()
    {
        return emailField.getText().trim();
    }

    public double getSueldo()
    {
        try {
            return Double.parseDouble(sueldoField.getText().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getClave()
    {
        return claveField.getText().trim();
    }
}
