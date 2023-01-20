package javafx.modelo;

import com.guigarage.responsive.ResponsiveHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /* Calls the FXML file and the css file to create the interactive interface(frontend) */
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml")); //gets the fxml file.
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720); // create the scene with the fxml info.
        String css = this.getClass().getResource("style.css").toExternalForm(); // get the css file.
        scene.getStylesheets().add(css); // add the css file to the scene.
        stage.setTitle("Task Manager"); // title.
        stage.setScene(scene); // display the scene.
        stage.show(); // make visible.
        ResponsiveHandler.addResponsiveToWindow(scene.getWindow());
    }

    public static void main(String[] args) {
        launch(); //launch the application.
    }
}