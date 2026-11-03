package presentacion;

import dominio.Fachada;
import entidad.FormatoCopia;
import entidad.eAutor;
import entidad.eCopia;
import entidad.eLibro;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LibroFormulario
{
    @FXML private VBox contenedorFilas;

    private Fachada fachada;
    private ArrayList<eLibro> snapshot;

    @FXML
    private void initialize()
    {
        fachada = new Fachada();
        snapshot = new ArrayList<>();
        cargar();
    }

    private void cargar()
    {
        try {
            ArrayList<eLibro> lista = fachada.listarLibros();
            snapshot.clear();
            snapshot.addAll(lista);
            mostrar(lista);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void mostrar(ArrayList<eLibro> lista)
    {
        contenedorFilas.getChildren().clear();
        for (eLibro l : lista) {
            contenedorFilas.getChildren().add(crearFila(l));
        }
    }

    private HBox crearFila(eLibro l)
    {
        HBox fila = new HBox(8);
        Button borrar = new Button("X");
        borrar.setOnAction(e -> {
            contenedorFilas.getChildren().remove(fila);
        });

        String nomAutor = "";
        String nac = "";
        try {
            eAutor a = fachada.listarAutores().stream()
                    .filter(x -> x.getId() == l.getIdAutor())
                    .findFirst().orElse(null);
            if (a != null) {
                nomAutor = a.getNombre();
                nac = a.getNacionalidad();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        int cantFis = 0;
        int cantEle = 0;
        try {
            ArrayList<eCopia> copias = fachada.listarCopias();
            for (eCopia c : copias) {
                if (c.getIsbn().equals(l.getIsbn())) {
                    if (c.getFormato() == FormatoCopia.FISICO) {
                        cantFis++;
                    } else {
                        cantEle++;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        TextField isbnField = campo(l.getIsbn(), 140, false);
        isbnField.setStyle("-fx-background-color: #e0e0e0;");

        fila.getChildren().addAll(
                isbnField,
                campo(l.getTitulo(), 180, true),
                campo(nomAutor, 140, true),
                campo(nac, 100, true),
                campo(String.valueOf(cantFis), 60, true),
                campo(String.valueOf(cantEle), 80, true),
                borrar
        );
        return fila;
    }

    private TextField campo(String texto, double ancho, boolean edit)
    {
        TextField f = new TextField(texto);
        f.setPrefWidth(ancho);
        f.setEditable(edit);
        return f;
    }

    private String texto(HBox fila, int pos)
    {
        return ((TextField) fila.getChildren().get(pos)).getText().trim();
    }

    @FXML
    private void onAgregar()
    {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/nuevo-libro.fxml"));
            Parent root = loader.load();
            Stage dialog = new Stage();
            dialog.initOwner(
                    contenedorFilas.getScene().getWindow());
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setScene(new Scene(root));
            dialog.showAndWait();

            NuevoLibroFormulario ctrl = loader.getController();
            if (ctrl.getResultado()) {
                guardarNuevo(ctrl);
                cargar();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void guardarNuevo(NuevoLibroFormulario ctrl)
    {
        try {
            String nom = ctrl.getAutor();
            String nac = ctrl.getNacionalidad();
            eAutor autor = fachada.buscarAutorPorNombre(nom);
            if (autor == null) {
                autor = new eAutor();
                autor.setNombre(nom);
                autor.setNacionalidad(nac);
                fachada.altaAutor(autor);
                autor = fachada.buscarAutorPorNombre(nom);
            }

            eLibro lib = new eLibro();
            lib.setIsbn(ctrl.getIsbn());
            lib.setTitulo(ctrl.getTitulo());
            lib.setIdAutor(autor.getId());
            fachada.altaLibro(lib);

            FormatoCopia fmt = FormatoCopia.valueOf(ctrl.getFormato());
            int cant = Integer.parseInt(ctrl.getCantidad());
            fachada.crearVariasCopias(lib.getIsbn(), fmt, cant);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onGuardar()
    {
        try {
            ArrayList<String> isbnsError = new ArrayList<>();
            Set<String> isbnsActuales = new HashSet<>();

            for (var nodo : contenedorFilas.getChildren()) {
                HBox fila = (HBox) nodo;
                String isbn = texto(fila, 0);
                isbnsActuales.add(isbn);

                int cantFisNueva = Integer.parseInt(texto(fila, 4));
                int cantEleNueva = Integer.parseInt(texto(fila, 5));

                int cantFisActual = 0;
                int cantEleActual = 0;
                ArrayList<eCopia> copias = fachada.listarCopias();
                for (eCopia c : copias) {
                    if (c.getIsbn().equals(isbn)) {
                        if (c.getFormato() == FormatoCopia.FISICO) {
                            cantFisActual++;
                        } else {
                            cantEleActual++;
                        }
                    }
                }

                if (cantFisNueva < cantFisActual ||
                        cantEleNueva < cantEleActual) {
                    isbnsError.add(isbn);
                }
            }

            if (!isbnsError.isEmpty()) {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Cantidades incorrectas");
                alerta.setHeaderText(null);
                StringBuilder msg = new StringBuilder();
                msg.append("Los siguientes ISBN tienen ");
                msg.append("cantidades menores a las actuales:\n");
                for (String isbn : isbnsError) {
                    msg.append(isbn);
                    msg.append("\n");
                }
                alerta.setContentText(msg.toString());
                alerta.showAndWait();
                return;
            }

            for (var nodo : contenedorFilas.getChildren()) {
                HBox fila = (HBox) nodo;
                String isbn = texto(fila, 0);
                String titulo = texto(fila, 1);
                String nomAutor = texto(fila, 2);
                String nac = texto(fila, 3);
                int cantFisNueva = Integer.parseInt(texto(fila, 4));
                int cantEleNueva = Integer.parseInt(texto(fila, 5));

                eAutor autor = fachada.buscarAutorPorNombre(nomAutor);
                if (autor == null) {
                    autor = new eAutor();
                    autor.setNombre(nomAutor);
                    autor.setNacionalidad(nac);
                    fachada.altaAutor(autor);
                    autor = fachada.buscarAutorPorNombre(nomAutor);
                } else if (!autor.getNacionalidad().equals(nac)) {
                    autor.setNacionalidad(nac);
                    fachada.modificarAutor(autor);
                }

                eLibro lib = new eLibro();
                lib.setIsbn(isbn);
                lib.setTitulo(titulo);
                lib.setIdAutor(autor.getId());
                fachada.modificarLibro(lib);

                int cantFisActual = 0;
                int cantEleActual = 0;
                ArrayList<eCopia> copias = fachada.listarCopias();
                for (eCopia c : copias) {
                    if (c.getIsbn().equals(isbn)) {
                        if (c.getFormato() == FormatoCopia.FISICO) {
                            cantFisActual++;
                        } else {
                            cantEleActual++;
                        }
                    }
                }

                if (cantFisNueva > cantFisActual) {
                    fachada.crearVariasCopias(isbn, FormatoCopia.FISICO,
                            cantFisNueva - cantFisActual);
                }
                if (cantEleNueva > cantEleActual) {
                    fachada.crearVariasCopias(isbn, FormatoCopia.ELECTRONICO,
                            cantEleNueva - cantEleActual);
                }
            }

            for (eLibro orig : snapshot) {
                if (!isbnsActuales.contains(orig.getIsbn())) {
                    ArrayList<eCopia> copias = fachada.listarCopias();
                    for (eCopia c : copias) {
                        if (c.getIsbn().equals(orig.getIsbn())) {
                            fachada.eliminarCopia(c.getIdCopia());
                        }
                    }
                    fachada.eliminarLibro(orig.getIsbn());
                }
            }

            cargar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onCancelar()
    {
        mostrar(snapshot);
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
