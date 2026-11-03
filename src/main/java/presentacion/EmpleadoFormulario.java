package presentacion;

import dominio.Fachada;
import entidad.eEmpleado;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EmpleadoFormulario
{
    @FXML private VBox contenedorFilas;

    private Fachada fachada;

    @FXML
    private void initialize()
    {
        fachada = new Fachada();
        cargar();
    }

    private void cargar()
    {
        try {
            ArrayList<eEmpleado> lista = fachada.listarEmpleados();
            mostrar(lista);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void mostrar(ArrayList<eEmpleado> lista)
    {
        contenedorFilas.getChildren().clear();
        for (eEmpleado emp : lista) {
            contenedorFilas.getChildren().add(crearFila(emp));
        }
    }

    private HBox crearFila(eEmpleado emp)
    {
        HBox fila = new HBox(8);
        fila.getChildren().addAll(
                label(emp.getCedula(), 120),
                label(emp.getNombre(), 160),
                label(emp.getEmail(), 160),
                label(String.valueOf(emp.getSueldo()), 80),
                label(emp.getClave(), 100)
        );
        return fila;
    }

    private Label label(String texto, double ancho)
    {
        Label l = new Label(texto == null ? "" : texto);
        l.setPrefWidth(ancho);
        return l;
    }

    @FXML
    private void onAgregar()
    {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/nuevo-empleado.fxml"));
            Parent root = loader.load();
            Stage dialog = new Stage();
            dialog.initOwner(
                    contenedorFilas.getScene().getWindow());
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setScene(new Scene(root));
            dialog.showAndWait();

            NuevoEmpleadoFormulario ctrl = loader.getController();
            if (ctrl.getResultado()) {
                eEmpleado emp = new eEmpleado();
                emp.setCedula(ctrl.getCedula());
                emp.setNombre(ctrl.getNombre());
                emp.setEmail(ctrl.getEmail());
                emp.setSueldo(ctrl.getSueldo());
                emp.setClave(ctrl.getClave());
                fachada.altaEmpleado(emp);
                cargar();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onVolver()
    {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/prestamos.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) contenedorFilas.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Biblioteca - Prestamos");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
