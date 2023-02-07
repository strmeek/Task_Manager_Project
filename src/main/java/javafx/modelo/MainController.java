package javafx.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private AnchorPane anchorpane;

    @FXML
    private Button bt_add_home;

    @FXML
    private Button bt_add_menu;

    @FXML
    private Button bt_add_projects;

    @FXML
    private Button bt_add_tasks;

    @FXML
    private Button bt_all_tasks;

    @FXML
    private Button bt_close_add;

    @FXML
    private Button bt_close_newBlock;

    @FXML
    private Button bt_close_newProject;

    @FXML
    private Button bt_close_newTask;

    @FXML
    private Button bt_config_menu;

    @FXML
    private Button bt_create_add;

    @FXML
    private Button bt_filter_close;

    @FXML
    private Button bt_filters_tasks;

    @FXML
    private Button bt_home_menu;

    @FXML
    private Button bt_leftarrow_tasks;

    @FXML
    private Button bt_newblock_add;

    @FXML
    private Button bt_newproject_add;

    @FXML
    private Button bt_newtask_add;

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
    private ComboBox<String> comboBox_priority_add;

    @FXML
    private ComboBox<String> comboBox_type_add;

    @FXML
    private DatePicker datepicker_PFinish;

    @FXML
    private DatePicker datepicker_PStart;

    @FXML
    private GridPane grid_home;

    @FXML
    private GridPane grid_projects;

    @FXML
    private GridPane grid_tasks;

    @FXML
    private GridPane grid_vbox_tasks;

    @FXML
    private Label label_andrade;

    @FXML
    private TextArea txtarea_description_add;

    @FXML
    private TextField txtfield_search_config;

    @FXML
    private TextField txtfield_search_home;

    @FXML
    private TextField txtfield_search_projects;

    @FXML
    private TextField txtfield_search_tasks;

    @FXML
    private TextField txtfield_title_add;

    @FXML
    private VBox vbox_New_Block;

    @FXML
    private VBox vbox_New_Project;

    @FXML
    private VBox vbox_New_Task;

    @FXML
    private VBox vbox_add;

    @FXML
    private VBox vbox_config;

    @FXML
    private VBox vbox_filters;

    @FXML
    private VBox vbox_home;

    @FXML
    private VBox vbox_menu;

    @FXML
    private VBox vbox_projects;

    @FXML
    private VBox vbox_task;

    ObservableList<String> list_type = FXCollections.observableArrayList();
    ObservableList<String> list_priority = FXCollections.observableArrayList();

    private List<Task> tasks;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /* Home always the first screen */
        vbox_home.toFront();

        /*ComboBoxes items*/
        list_type.addAll("Work", "House", "Routine", "Special");
        comboBox_type_add.setItems(list_type);
        list_priority.addAll("High", "Medium", "Low");
        comboBox_priority_add.setItems(list_priority);

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
                grid_vbox_tasks.getChildren().clear();

                /*Task Grid*/
                tasks = new ArrayList<>(DataBase.grid_List_tasks());

                int columns = 0;
                int row = 1;

                try{
                    for(int i=0; i<tasks.size(); i++){
                        FXMLLoader fxmlLoader = new FXMLLoader();
                        fxmlLoader.setLocation(getClass().getResource("task.fxml"));
                        VBox block = fxmlLoader.load();
                        TaskController taskController = fxmlLoader.getController();
                        taskController.setTaskData(tasks.get(i));

                        if(columns == 1){
                            columns = 0;
                            ++row;
                        }
                        grid_vbox_tasks.add(block, columns++,row);
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
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
        /* Expand the filters in task screen */
        bt_filters_tasks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                vbox_filters.toFront();
            }
        });
        /* Collapse the filters in task screen */
        bt_filter_close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                vbox_filters.toBack();
            }
        });
        /*show add options*/
        bt_add_tasks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                vbox_add.toFront();
            }
        });
        /*show add options*/
        bt_add_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                vbox_add.toFront();
            }
        });
        /*show add options*/
        bt_add_home.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                vbox_add.toFront();
            }
        });
        /*show add options*/
        bt_add_projects.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                vbox_add.toFront();
            }
        });
        /*closes add options*/
        bt_close_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                vbox_add.toBack();
            }
        });
        /*Shows New Tasks options*/
        bt_newtask_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                vbox_New_Task.toFront();
            }
        });
        /*Shows New Projects options*/
        bt_newproject_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                vbox_New_Project.toFront();
            }
        });
        /*Shows New Blocks options*/
        bt_newblock_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                vbox_New_Block.toFront();
            }
        });
        /*Collapse add tabs*/
        bt_close_newTask.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                vbox_New_Task.toBack();
            }
        });
        /*Collapse add tabs*/
        bt_close_newProject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                vbox_New_Project.toBack();
            }
        });
        /*Collapse add tabs*/
        bt_close_newBlock.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                vbox_New_Block.toBack();
            }
        });

        bt_create_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String title = txtfield_title_add.getText();
                String description = txtarea_description_add.getText();
                String type = comboBox_type_add.getValue();
                String priority = comboBox_priority_add.getValue();
                String status = "To-Do";
                //transforming planning start and finish to date
                LocalDateTime planned_start_dt = datepicker_PStart.getValue().atStartOfDay();
                Timestamp planned_start_tstamp = Timestamp.valueOf(planned_start_dt);
                Date planned_Start = new Date(planned_start_tstamp.getTime());//converted
                LocalDateTime planned_finish_dt = datepicker_PFinish.getValue().atStartOfDay();
                Timestamp planned_finish_tstamp = Timestamp.valueOf(planned_finish_dt);
                Date planned_Finish = new Date(planned_finish_tstamp.getTime());//converted

                /*actually add to the database*/
                if (!title.isEmpty() &&
                    !description.isEmpty() &&
                    !type.isEmpty() &&
                    !status.isEmpty() && !priority.isEmpty()){
                    try {
                        DataBase.addTask(type, title, description, priority, status, planned_Start, planned_Finish);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}