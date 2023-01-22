package javafx.modelo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    /* you don't NEED(you can, as shown in the
     * library site: https://guigarage.com/2014/11/responsive-design-javafx/)
     * to create the javafx object on the java application,
     * you can create on the fxml(scenebuilder) and once you declared the
     * object on the controller he'll work with the responsive fx
     * that means you will do more coding on the css file(example on style.css) using
     * responsivefx library, which is really great and keep all
     * organized in the controller */

    /* FXML VARIABLES MADE BY SCENEBUILDER for example:*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}