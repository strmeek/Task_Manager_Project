package javafx.modelo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private Button bt_add_home;

    @FXML
    private Button bt_add_projects;

    @FXML
    private Button bt_add_tasks;

    @FXML
    private Button bt_all_tasks;

    @FXML
    private Button bt_config_menu;

    @FXML
    private Button bt_filters_tasks;

    @FXML
    private Button bt_home_menu;

    @FXML
    private Button bt_leftarrow_tasks;

    @FXML
    private Button bt_projects_menu;

    @FXML
    private Button bt_rightarrow_tasks;

    @FXML
    private Button bt_search_config;

    @FXML
    private Button bt_search_home;

    @FXML
    private Button bt_search_projects;

    @FXML
    private Button bt_search_tasks;

    @FXML
    private Button bt_tasks_menu;

    @FXML
    private Button bt_today_tasks;

    @FXML
    private Button bt_tomorrow_tasks;

    @FXML
    private GridPane grid_home;

    @FXML
    private GridPane grid_projects;

    @FXML
    private GridPane grid_tasks;

    @FXML
    private Label label_andrade;

    @FXML
    private TextField txtfield_search_config;

    @FXML
    private TextField txtfield_search_home;

    @FXML
    private TextField txtfield_search_projects;

    @FXML
    private TextField txtfield_search_tasks;

    @FXML
    private VBox vbox_config;

    @FXML
    private VBox vbox_home;

    @FXML
    private VBox vbox_menu;

    @FXML
    private VBox vbox_projects;

    @FXML
    private VBox vbox_task;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /* HOME BUTTON */
        bt_home_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                vbox_home.toFront();
            }
        });
        /* TASKS BUTTON */
        bt_tasks_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                vbox_task.toFront();
            }
        });
        /* PROJECTS BUTTON */
        bt_projects_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                vbox_projects.toFront();
            }
        });
        /* CONFIG BUTTON */
        bt_config_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                vbox_config.toFront();
            }
        });


    }
}