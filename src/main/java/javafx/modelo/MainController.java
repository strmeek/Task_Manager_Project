package javafx.modelo;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
    private Button bt_add_menu;

    @FXML
    private Button bt_close_menu;

    @FXML
    private Button bt_config_menu;

    @FXML
    private Button bt_home_menu;

    @FXML
    private Button bt_newproject_add;

    @FXML
    private Button bt_newtask_add;

    @FXML
    private Button bt_projects_menu;

    @FXML
    private Button bt_search_menu;

    @FXML
    private Button bt_tasks_menu;

    @FXML
    private GridPane grid_projects;

    @FXML
    private GridPane grid_vbox_tasks;

    @FXML
    private Label menu_Clock;

    @FXML
    private Label menu_Date;

    @FXML
    private Label menu_Day;

    @FXML
    private TextField txtField_search_menu;

    @FXML
    private AnchorPane vbox_add;

    @FXML
    private VBox vbox_config;

    @FXML
    private VBox vbox_expand;

    @FXML
    private VBox vbox_home;

    @FXML
    private VBox vbox_projects;

    @FXML
    private VBox vbox_task;

    ObservableList<String> list_type = FXCollections.observableArrayList();
    ObservableList<String> list_priority = FXCollections.observableArrayList();

    private List<Task> tasks;
    private List<Subtask> subtasks;

    private int saveTask;
    private boolean isOnAddTab = true;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /* Home always the first screen */
        vbox_home.toFront();

        /*Clock*/
        menuClock();

        /*ComboBoxes items
        list_type.addAll("Work", "House", "Routine", "Special");
        comboBox_type_add.setItems(list_type);
        comboBoxType_expand.setItems(list_type);

        list_priority.addAll("High", "Medium", "Low");
        comboBox_priority_add.setItems(list_priority);
        comboBox_priority_addSubtask.setItems(list_priority);
        comboBoxPriority_expand.setItems(list_priority);*/

        /* CLOSE BUTTON */
        bt_close_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
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
                refreshGridTasks();
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
        //1o click do botão é dado aqui
        bt_add_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vbox_add.toFront();
                closeAddTabAction();
            }
        });
    }

    public void refreshGridTasks() {
        grid_vbox_tasks.getChildren().clear();

        /*Task Grid*/
        tasks = new ArrayList<>(DataBase.grid_List_tasks());

        int columns = 0;
        int row = 1;

        try {
            for (int i = 0; i < tasks.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("task.fxml"));
                VBox taskBlock = fxmlLoader.load();
                TaskController taskController = fxmlLoader.getController();
                taskController.setTaskData(tasks.get(i));
                taskController.setMainController(this);

                if (columns == 5) {
                    columns = 0;
                    ++row;
                }
                grid_vbox_tasks.add(taskBlock, columns++, row);
                GridPane.setMargin(taskBlock, new Insets(12));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void menuClock() {
        Thread clock = new Thread() {
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        Calendar now = Calendar.getInstance();
                        int hour = now.get(Calendar.HOUR_OF_DAY);
                        int minute = now.get(Calendar.MINUTE);
                        menu_Clock.setText(String.format("%02d:%02d", hour, minute));
                        menu_Date.setText(java.time.LocalDate.now().toString());
                        Calendar calendar = Calendar.getInstance();
                        SimpleDateFormat dayFormat = new SimpleDateFormat("E");
                        String abbreviatedDayOfWeek = dayFormat.format(calendar.getTime());
                        menu_Day.setText(abbreviatedDayOfWeek);
                    });
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        };
        clock.start();
    }
    /*abre a aba e devolve pra closeAddTabaction*/
    public void openAddTabAction(){
        bt_add_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vbox_add.toFront();
                closeAddTabAction();
            }
        });
    }
    /*2o click do botão vem pra a cá, agora joga ping pong com openAddTabAction*/
    public void closeAddTabAction(){
        bt_add_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vbox_add.toBack();
                openAddTabAction();
            }
        });
    }

    public void vboxExpandToFront(){
        vbox_expand.toFront();
    }
}