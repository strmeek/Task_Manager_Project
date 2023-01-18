package javafx.modelo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button bt_add_menu;

    @FXML
    private Button bt_config_menu;

    @FXML
    private Button bt_home_menu;

    @FXML
    private Button bt_tasks_menu;

    @FXML
    private Pane pane_add;

    @FXML
    private Pane pane_config;

    @FXML
    private Pane pane_home;

    @FXML
    private Pane pane_tasks;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bt_add_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane_add.toFront();
            }
        });
        bt_config_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane_config.toFront();
            }
        });
        bt_tasks_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane_tasks.toFront();
            }
        });
        bt_home_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane_home.toFront();
            }
        });
    }

}