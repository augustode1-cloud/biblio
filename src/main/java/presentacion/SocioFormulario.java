package presentacion;

import dominio.Fachada;
import entidad.eSocio;
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

public class SocioFormulario
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
            ArrayList<eSocio> lista = fachada.listarSocios();
            mostrar(lista);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void mostrar(ArrayList<eSocio> lista)
    {
        contenedorFilas.getChildren().clear();
        for (eSocio s : lista) {
            contenedorFilas.getChildren().add(crearFila(s));
        }
    }

    private HBox crearFila(eSocio s)
    {
        HBox fila = new HBox(8);
        fila.getChildren().addAll(
                label(s.getCedula(), 120),
                label(s.getNombre(), 180),
                label(s.getEmail(), 180),
                label(s.isSancionado() ? "Si" : "No", 100)
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
                    getClass().getResource("/fxml/nuevo-socio.fxml"));
            Parent root = loader.load();
            Stage dialog = new Stage();
            dialog.initOwner(
                    contenedorFilas.getScene().getWindow());
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setScene(new Scene(root));
            dialog.showAndWait();

            NuevoSocioFormulario ctrl = loader.getController();
            if (ctrl.getResultado()) {
                eSocio s = new eSocio();
                s.setCedula(ctrl.getCedula());
                s.setNombre(ctrl.getNombre());
                s.setEmail(ctrl.getEmail());
                s.setSancionado(ctrl.isSancionado());
                fachada.altaSocio(s);
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
