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
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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

    /* FXML VARIABLES MADE BY SCENEBUILDER:*/
    @FXML
    private Button bt_add_menu;

    @FXML
    private Button bt_close_menu;

    @FXML
    private Button bt_close_newSubtask;

    @FXML
    private Button bt_close_newProject;

    @FXML
    private Button bt_close_newTask;

    @FXML
    private Button bt_config_menu;

    @FXML
    private Button bt_create_add;

    @FXML
    private Button bt_create_addProject;

    @FXML
    private Button bt_create_addSubtask;

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
    private ComboBox<String> comboBox_priority_add;

    @FXML
    private ComboBox<String> comboBox_priority_addProject;

    @FXML
    private ComboBox<String> comboBox_priority_addSubtask;

    @FXML
    private ComboBox<String> comboBox_project_add;

    @FXML
    private ComboBox<String> comboBox_type_add;

    @FXML
    private ComboBox<String> comboBox_type_addProject;

    @FXML
    private ComboBox<String> comboBox_type_addSubtask;

    @FXML
    private DatePicker datepicker_PFinish;

    @FXML
    private DatePicker datepicker_PFinishProject;

    @FXML
    private DatePicker datepicker_PFinishSubtask;

    @FXML
    private DatePicker datepicker_PStart;

    @FXML
    private DatePicker datepicker_PStartProject;

    @FXML
    private DatePicker datepicker_PStartSubtask;

    @FXML
    private GridPane grid_projects;

    @FXML
    private GridPane grid_projects_home;

    @FXML
    private GridPane grid_subtasks;

    @FXML
    private GridPane grid_today_home;

    @FXML
    private GridPane grid_urgent_home;

    @FXML
    private GridPane grid_vbox_tasks;

    @FXML
    private ImageView imgView_plus_createProject;

    @FXML
    private ImageView imgView_plus_createTask;

    @FXML
    private ImageView imgView_plus_createTask1;

    @FXML
    private Label menu_Clock;

    @FXML
    private Label menu_Date;

    @FXML
    private Label menu_Day;

    @FXML
    private ScrollPane scrollpane_projects_home;

    @FXML
    private TextField txtField_search_menu;

    @FXML
    private TextArea txtarea_description_add;

    @FXML
    private TextArea txtarea_description_addProject;

    @FXML
    private TextArea txtarea_description_addSubtask;

    @FXML
    private TextField txtfield_title_add;

    @FXML
    private TextField txtfield_title_addProject;

    @FXML
    private TextField txtfield_title_addSubtask;

    @FXML
    private AnchorPane vbox_New_Project;

    @FXML
    private AnchorPane vbox_New_Subtask;

    @FXML
    private AnchorPane vbox_New_Task;

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

    /*These lists go inside the ComboBoxes for the user to select
    * the Type/Priority/Project on Task/Subtask/Project creation and help
    * me on linking information and sorting*/
    ObservableList<String> list_type = FXCollections.observableArrayList();
    ObservableList<String> list_type_subtasks = FXCollections.observableArrayList();
    ObservableList<String> list_priority = FXCollections.observableArrayList();
    ObservableList<String> list_projects = FXCollections.observableArrayList();

    /*these lists save information for the grids*/
    private List<Project> projects;
    private List<Task> tasks;
    private List<Subtask> subtasks;

    /*Saves the task from TaskController so the system knows what task
    * the user clicked*/
    private int saveTask;
    private int saveProject;
    private boolean isOnAddTab = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /* Home always the first screen */
        refreshGridProjects();
        refreshGridTasks();
        refreshGridSubtasks();
        refreshHomeGridProjects();
        vbox_home.toFront();

        /*Clock*/
        menuClock();

        /*ComboBoxes items*/
        list_type.addAll("Work", "House", "Routine", "Special");
        comboBox_type_add.setItems(list_type);
        comboBox_type_addProject.setItems(list_type);
        //comboBoxType_expand.setItems(list_type);

        list_type_subtasks.addAll("Documentation", "Coding");
        comboBox_type_addSubtask.setItems(list_type_subtasks);
        comboBox_priority_addProject.setItems(list_type_subtasks);

        list_priority.addAll("High", "Medium", "Low");
        comboBox_priority_add.setItems(list_priority);
        comboBox_priority_addSubtask.setItems(list_priority);
        comboBox_priority_addProject.setItems(list_priority);
        //comboBoxPriority_expand.setItems(list_priority);

        /*carousel effect on home projects*/
        scrollpane_projects_home.setContent(grid_projects_home);
        scrollpane_projects_home.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent scrollEvent) {
                double hValue = scrollpane_projects_home.getHvalue();
                double delta = scrollEvent.getDeltaY();
                double width = scrollpane_projects_home.getContent().getBoundsInLocal().getWidth();
                double viewportWidth = scrollpane_projects_home.getViewportBounds().getWidth();
                double maxH = width - viewportWidth;

                //makes the scrolling more confortable
                double faster = 0;
                if(delta < 0){
                    faster = -0.06;
                } else if( delta > 0){
                    faster = 0.06;
                }

                hValue = Math.min(maxH, Math.max(0, hValue - delta / width));
                scrollpane_projects_home.setHvalue(hValue + faster);
            }
        });

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
                refreshHomeGridProjects();
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
                refreshGridProjects();
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
        //1o click do bot??o ?? dado aqui
        bt_add_menu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vbox_add.toFront();
                closeAddTabAction();
                isOnAddTab = true;
            }
        });
        /*Collapse add tabs*/
        bt_close_newTask.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                vbox_New_Task.toBack();
                vbox_add.toBack();
                isOnAddTab = false;
            }
        });

        bt_close_newSubtask.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vbox_New_Subtask.toBack();
            }
        });
        /*Collapse add tabs*/
        bt_close_newProject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                vbox_New_Project.toBack();
                vbox_add.toBack();
            }
        });

        bt_create_addProject.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String title = txtfield_title_addProject.getText();
                String description = txtarea_description_addProject.getText();
                String type = comboBox_type_addProject.getValue();
                String priority = comboBox_priority_addProject.getValue();
                String status = "To-Do";

                //transforming planning start and finish to date
                LocalDateTime planned_start_dt = datepicker_PStartProject.getValue().atStartOfDay();
                Timestamp planned_start_tstamp = Timestamp.valueOf(planned_start_dt);
                Date planned_Start = new Date(planned_start_tstamp.getTime());//converted
                LocalDateTime planned_finish_dt = datepicker_PFinishProject.getValue().atStartOfDay();
                Timestamp planned_finish_tstamp = Timestamp.valueOf(planned_finish_dt);
                Date planned_Finish = new Date(planned_finish_tstamp.getTime());//converted

                /*actually add to the database*/
                if (!title.isEmpty() &&
                        !description.isEmpty() &&
                        !type.isEmpty() &&
                        !status.isEmpty() && !priority.isEmpty()){
                    try {
                        Project project = new Project();
                        project.setTitle_Project(title);
                        project.setDescription_Project(description);
                        project.setType_Project(type);
                        project.setPriority_Project(priority);
                        project.setStatus_Project(status);
                        if(planned_Start == null){
                            planned_Start = new Date(System.currentTimeMillis());
                            project.setPlannedStart_Project(String.valueOf(planned_Start));
                        }else{
                            project.setPlannedStart_Project(String.valueOf(planned_Start));
                        }
                        if(planned_Finish == null){
                            planned_Finish = new Date(System.currentTimeMillis());
                            project.setPlannedFinish_Project(String.valueOf(planned_Finish));
                        }else{
                            project.setPlannedFinish_Project(String.valueOf(planned_Finish));
                        }

                        DataBase.addProject(project);
                        refreshGridProjects();
                        refreshHomeGridProjects();
                        vbox_New_Project.toBack();
                        vbox_add.toBack();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

        /*Get the info from the screen and makes the adition to the database*/
        bt_create_add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                    String title = txtfield_title_add.getText();
                    String description = txtarea_description_add.getText();
                    String type = comboBox_type_add.getValue();
                    String priority = comboBox_priority_add.getValue();
                    String status = "To-Do";
                    String project;

                    if(comboBox_project_add.getValue() == null){
                        project = "No Project Related";
                    } else {
                        project = comboBox_project_add.getValue();
                    }

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
                    !status.isEmpty() &&
                        !priority.isEmpty() &&
                        !project.isEmpty()){
                    try {
                        Task task = new Task();
                        task.setTitle_task(title);
                        task.setDescription_task(description);
                        task.setType_task(type);
                        task.setPriority_task(priority);
                        task.setStatus_task(status);
                        task.setForeign_idProject(saveProject);

                        if(planned_Start == null){
                            planned_Start = new Date(System.currentTimeMillis());
                            task.setPlanned_start(String.valueOf(planned_Start));
                        }else{
                            task.setPlanned_start(String.valueOf(planned_Start));
                        }
                        if(planned_Finish == null){
                            planned_Finish = new Date(System.currentTimeMillis());
                            task.setPlanned_finish(String.valueOf(planned_Finish));
                        }else{
                            task.setPlanned_finish(String.valueOf(planned_Finish));
                        }
                        DataBase.addTask(task);
                        refreshGridTasks();

                        vbox_New_Task.toBack();
                        vbox_add.toBack();
                        isOnAddTab = false;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
        /*Get the info from the screen and makes the adition to the database*/
        bt_create_addSubtask.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String title = txtfield_title_addSubtask.getText();
                String description = txtarea_description_addSubtask.getText();
                String priority = comboBox_priority_addSubtask.getValue();
                String type = comboBox_type_addSubtask.getValue();
                String status = "To-Do";
                //transforming planning start and finish to date

                LocalDateTime planned_startDate = datepicker_PStartSubtask.getValue().atStartOfDay();
                Timestamp plannedStartTimestamp = Timestamp.valueOf(planned_startDate);
                Date plannedStart = new Date(plannedStartTimestamp.getTime());
                LocalDateTime planned_finishDate = datepicker_PFinishSubtask.getValue().atStartOfDay();
                Timestamp planned_finishTimestamp = Timestamp.valueOf(planned_finishDate);
                Date planned_Finish = new Date(planned_finishTimestamp.getTime());//converted

                //actually add to the database
                if(!title.isEmpty() &&
                        !description.isEmpty() &&
                        !status.isEmpty() &&
                        !priority.isEmpty() &&
                        !type.isEmpty()){
                    try{
                        Subtask subtask = new Subtask();
                        subtask.setStatus_subtask(status);
                        subtask.setPriority_subtask(priority);
                        subtask.setTitle_subtask(title);
                        subtask.setDescription_subtask(description);
                        subtask.setType_subtask(type);
                        subtask.setForeign_idTask(saveTask);

                        if(plannedStart == null){
                            plannedStart = new Date(System.currentTimeMillis());
                            subtask.setSubtask_plannedStart(String.valueOf(plannedStart));
                        }else{
                            subtask.setSubtask_plannedStart(String.valueOf(plannedStart));
                        }
                        if(planned_Finish == null){
                            planned_Finish = new Date(System.currentTimeMillis());
                            subtask.setSubtask_plannedFinish(String.valueOf(planned_Finish));
                        }else{
                            subtask.setSubtask_plannedFinish(String.valueOf(planned_Finish));
                        }

                        DataBase.addSubTask(subtask);
                        refreshGridSubtasks();
                        vbox_New_Subtask.toBack();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    /*Generates the grid with the Projects information*/
    public void refreshGridProjects(){
        grid_projects.getChildren().clear();
        list_projects.clear();

        //Task Grid
        projects = new ArrayList<>(DataBase.gridListProjects());

        int columns = 0;
        int row = 1;

        try {
            for (int i = 0; i < projects.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("project.fxml"));
                VBox projectBlock = fxmlLoader.load();
                ProjectController projectController = fxmlLoader.getController();
                projectController.setProjectData(projects.get(i));
                projectController.setMainController(this);

                if (columns == 3) {
                    columns = 0;
                    ++row;
                }
                grid_projects.add(projectBlock, columns++, row);
                GridPane.setMargin(projectBlock, new Insets(12));
                /*Recognize all existing projects*/
                list_projects.add(projects.get(i).getTitle_Project());
            }
            /*User can link task to project in the create task screen*/
            comboBox_project_add.setItems(list_projects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*Generates the grid with the Projects information for the home page*/
    public void refreshHomeGridProjects(){
        grid_projects_home.getChildren().clear();

        //Task Grid
        projects = new ArrayList<>(DataBase.gridListProjects());

        int columns = projects.size();
        int row = 1;

        VBox emptyBoxStart = new VBox();
        emptyBoxStart.setStyle("-fx-background-color: rgba(0 ,0 ,0 ,0);");
        emptyBoxStart.setMinWidth(350);
        emptyBoxStart.setMinHeight(250);
        grid_projects_home.add(emptyBoxStart, 0, row);

        try {
            for (int i = 0; i < projects.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("project.fxml"));
                VBox projectBlock = fxmlLoader.load();
                ProjectController projectController = fxmlLoader.getController();
                projectController.setProjectData(projects.get(i));
                projectController.setMainController(this);

                grid_projects_home.add(projectBlock, columns++, row);
                GridPane.setMargin(projectBlock, new Insets(16));
            }
            VBox emptyBoxEnd = new VBox();
            emptyBoxEnd.setStyle("-fx-background-color: rgba(0 ,0 ,0 ,0);");
            emptyBoxEnd.setMinWidth(350);
            emptyBoxEnd.setMinHeight(250);
            grid_projects_home.add(emptyBoxEnd, columns, row);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*Generates the grid with the tasks information*/
        public void refreshGridTasks(){
            grid_vbox_tasks.getChildren().clear();

            //Task Grid
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

                    if (columns == 4) {
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
    /*Generates the grid with the Subtasks information inside the tasks*/
    public void refreshGridSubtasks(){
        grid_subtasks.getChildren().clear();
        int idTask = saveTask;

        //Subtask Grid
        subtasks = new ArrayList<>(DataBase.gridListSubtask(idTask));

        int columns = 0;
        int row = 1;

        try{
            for(int i=0; i<subtasks.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("subtask.fxml"));
                VBox subtaskBlock = fxmlLoader.load();
                SubtaskController subtaskController = fxmlLoader.getController();
                subtaskController.setSubtaskData(subtasks.get(i));
                subtaskController.setMainController(this);

                if(columns == 4){
                    columns = 0;
                    ++row;
                }
                grid_subtasks.add(subtaskBlock, columns++,row);
                GridPane.setMargin(subtaskBlock, new Insets(12));
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

        public void toFrontExpand(Task task) {
            vbox_expand.toFront();
            saveTask = task.getId_task();
        }

        public void toFrontNewTask(Project project){
            vbox_New_Task.toFront();
            saveProject = project.getId_Project();
        }

        public void toFrontNewSubtask(Task task) {
            vbox_New_Subtask.toFront();
            saveTask = task.getId_task();
        }

        /*Clock that is shown in the top left*/
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
        public void openAddTabAction() {
            bt_add_menu.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    vbox_add.toFront();
                    closeAddTabAction();
                    isOnAddTab = true;
                }
            });
        }

        /*2o click do bot??o vem pra a c??, agora joga ping pong com openAddTabAction*/
        public void closeAddTabAction() {
            bt_add_menu.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    vbox_add.toBack();
                    openAddTabAction();
                    isOnAddTab = false;
                }
            });
        }
}