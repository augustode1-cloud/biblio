package presentacion;

import dominio.Fachada;
import entidad.eEmpleado;
import entidad.eLibro;
import entidad.ePrestamo;
import entidad.eSocio;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrestamoFormulario
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
            ArrayList<ePrestamo> lista = fachada.listarPrestamos();
            mostrar(lista);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void mostrar(ArrayList<ePrestamo> lista)
    {
        contenedorFilas.getChildren().clear();
        for (ePrestamo p : lista) {
            contenedorFilas.getChildren().add(crearFila(p));
        }
    }

    private HBox crearFila(ePrestamo p)
    {
        HBox fila = new HBox(8);

        String socioTxt = p.getCiSocio();
        String empTxt = p.getCiEmpleado();
        String libTxt = p.getIsbn();

        try {
            ArrayList<eSocio> socios = fachada.listarSocios();
            for (eSocio s : socios) {
                if (s.getCedula().equals(p.getCiSocio())) {
                    socioTxt = s.getCedula() + " - " + s.getNombre();
                    break;
                }
            }
            ArrayList<eEmpleado> emps = fachada.listarEmpleados();
            for (eEmpleado e : emps) {
                if (e.getCedula().equals(p.getCiEmpleado())) {
                    empTxt = e.getCedula() + " - " + e.getNombre();
                    break;
                }
            }
            ArrayList<eLibro> libs = fachada.listarLibros();
            for (eLibro l : libs) {
                if (l.getIsbn().equals(p.getIsbn())) {
                    libTxt = l.getIsbn() + " - " + l.getTitulo();
                    break;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        String fDev = p.getFechaDevolucion() == null ?
                "" : p.getFechaDevolucion().toString();
        String estado = p.getFechaDevolucion() == null ?
                "Activo" : "Devuelto";

        Button devolver = new Button("Devolver");
        devolver.setDisable(p.getFechaDevolucion() != null);
        devolver.setOnAction(e -> {
            p.setFechaDevolucion(LocalDate.now());
            try {
                fachada.modificarPrestamo(p);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            cargar();
        });

        fila.getChildren().addAll(
                label(String.valueOf(p.getIdPrestamo()), 50),
                label(socioTxt, 140),
                label(empTxt, 140),
                label(libTxt, 180),
                label(String.valueOf(p.getIdCopia()), 60),
                label(p.getFechaPrestamo().toString(), 100),
                label(fDev, 100),
                label(estado, 80),
                devolver
        );
        return fila;
    }

    private Label label(String texto, double ancho)
    {
        Label l = new Label(texto);
        l.setPrefWidth(ancho);
        return l;
    }

    @FXML
    private void onIrLibros()
    {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/libros.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) contenedorFilas.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Biblioteca - Libros");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onIrSocios()
    {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/socios.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) contenedorFilas.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Biblioteca - Socios");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onIrEmpleados()
    {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/empleados.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) contenedorFilas.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Biblioteca - Empleados");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onAgregar()
    {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/nuevo-prestamo.fxml"));
            Parent root = loader.load();
            Stage dialog = new Stage();
            dialog.initOwner(
                    contenedorFilas.getScene().getWindow());
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setScene(new Scene(root));
            dialog.showAndWait();

            NuevoPrestamoFormulario ctrl = loader.getController();
            if (ctrl.getResultado()) {
                ePrestamo p = new ePrestamo();
                p.setCiSocio(ctrl.getSocio());
                p.setCiEmpleado(ctrl.getEmpleado());
                p.setIsbn(ctrl.getIsbn());
                p.setIdCopia(ctrl.getIdCopia());
                p.setFechaPrestamo(ctrl.getFechaPrestamo());
                p.setFechaDevolucion(null);
                fachada.altaPrestamo(p);
                cargar();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
