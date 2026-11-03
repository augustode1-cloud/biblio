package presentacion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application
{
    @Override
    public void start(Stage stage)
    {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/fxml/prestamos.fxml"));
            Scene scene = new Scene(root, 1100, 600);
            stage.setTitle("Biblioteca - Prestamos");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
