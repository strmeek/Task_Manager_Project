package javafx.modelo;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class ProjectController {
    @FXML
    private Button bt_Pfinish_task;

    @FXML
    private Button bt_Pstart_task;

    @FXML
    private Button bt_addtask_project;

    @FXML
    private Button bt_completed_project;

    @FXML
    private Button bt_edit_Project;

    @FXML
    private Button bt_priority_project;

    @FXML
    private Button bt_remaining_project;

    @FXML
    private Button bt_remove_Project;

    @FXML
    private Button bt_status_project;

    @FXML
    private Button bt_type_project;

    @FXML
    private HBox buttons_hbox;

    @FXML
    private ImageView imgView_Done;

    @FXML
    private ImageView imgView_addSubtask;

    @FXML
    private ImageView imgView_edit;

    @FXML
    private ImageView imgView_remove;

    @FXML
    private ImageView imgView_task_Pfinish;

    @FXML
    private ImageView imgView_task_Pstart;

    @FXML
    private ImageView imgView_tasksCount;

    @FXML
    private ImageView imgView_updated;

    @FXML
    private Label label_Pfinish;

    @FXML
    private Label label_Pstart;

    @FXML
    private Label label_priority;

    @FXML
    private Label label_remaining;

    @FXML
    private Label label_status;

    @FXML
    private Label label_type;

    @FXML
    private Label label_updatedAt;

    @FXML
    private TextField txtField_description_project;

    @FXML
    private TextField txtField_title_project;

    @FXML
    private VBox vbox_project_fxml;

    /*Animation variables*/
    private TranslateTransition animation;
    private PauseTransition pause;

    /*Saves Information*/
    private MainController mainController;
    private Project project;

    private int countTasks = 9; //FAZER O MÉTODO PARA CONTAR AS TASKS DE CADA PROJETO DEPOIS

    /*All info from the project is set here*/
    public void setProjectData(Project project){
        label_status.setText(project.getStatus_Project());
        label_type.setText(project.getType_Project());
        label_priority.setText(project.getPriority_Project());

        txtField_title_project.setText(project.getTitle_Project());
        txtField_description_project.setText(project.getDescription_Project());
        txtField_title_project.setEditable(false);
        txtField_description_project.setEditable(false);

        label_Pstart.setText(project.getPlannedStart_Project());
        label_Pfinish.setText(project.getPlannedFinish_Project());

        label_remaining.setText(String.valueOf(countTasks));
        label_updatedAt.setText(project.getUpdatedAt_Project());

        this.project = project;

        /*Buttons appear when you enter the task*/
        firstStep();
        vbox_project_fxml.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                secondStep();
                animation(mouseEvent);
            }
        });

        vbox_project_fxml.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                thirdStep();
                animation(mouseEvent);
            }
        });
    }

    /* ChatGPT solution to a big error i was having */
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }
    public void callRefreshGridTasks(){
        mainController.refreshGridTasks();
    }

    public void callRefreshGridProjects(){
        mainController.refreshGridProjects();
        mainController.refreshHomeGridProjects();
    }
    /* ------------------------------------ */
    @FXML
    void btAddTaskAction(ActionEvent event) {
        mainController.toFrontNewTask(project);
        callRefreshGridTasks();
    }

    @FXML
    void btCompleteProjectAction(ActionEvent event) {
        DataBase.completeProject(project);
        callRefreshGridProjects();
        DataBase.completeAllTask(project);
    }

    @FXML
    void btDeleteProjectAction(ActionEvent event) {
        DataBase.deleteProject(project);
        callRefreshGridProjects();
        DataBase.deleteAllTask(project);
    }

    @FXML
    void btEditProjectAction(ActionEvent event) {

    }

    @FXML
    void btExpandProjectAction(MouseEvent event) {

    }

     /*The following methods were created to do the buttons appear
     when you hover a task in the grid, and make readability better*/
    public void firstStep(){
        /*Initial position of the fxml*/
        buttons_hbox.setStyle("-fx-scale-x: 0;");
        buttons_hbox.setStyle("-fx-scale-y: 0;");
        buttons_hbox.setStyle("-fx-pref-height: 0;");
        imgView_Done.setStyle("-fx-scale-y: 0;");
        imgView_addSubtask.setStyle("-fx-scale-y: 0;");
        imgView_remove.setStyle("-fx-scale-y: 0;");
        imgView_edit.setStyle("-fx-scale-y: 0;");
    }

    public void secondStep(){
        /*All appear when you hover the task*/
        buttons_hbox.setStyle("-fx-scale-x: 1;");
        buttons_hbox.setStyle("-fx-scale-y: 1;");
        buttons_hbox.setStyle("-fx-pref-height: 80;");
        imgView_Done.setStyle("-fx-scale-y: 1;");
        imgView_addSubtask.setStyle("-fx-scale-y: 1;");
        imgView_remove.setStyle("-fx-scale-y: 1;");
        imgView_edit.setStyle("-fx-scale-y: 1;");
        bt_edit_Project.setStyle("-fx-scale-y: 1;");
        txtField_title_project.setStyle("-fx-text-fill: -BLACK;");
        txtField_description_project.setStyle("-fx-text-fill: -GRAY;");
        label_status.setStyle("-fx-text-fill: -GRAY;");
        label_type.setStyle("-fx-text-fill: -GRAY;");
        label_priority.setStyle("-fx-text-fill: -GRAY;");
        label_Pstart.setStyle("-fx-text-fill: -GRAY;");
        label_Pfinish.setStyle("-fx-text-fill: -GRAY;");
        label_remaining.setStyle("-fx-text-fill: -GRAY;");
        label_updatedAt.setStyle("-fx-text-fill: -GRAY;");
        imgView_task_Pstart.setStyle("-fx-effect: innershadow( gaussian, #000, 10, 0, 255px, 255px);");
        imgView_task_Pfinish.setStyle("-fx-effect: innershadow( gaussian, #000, 10, 0, 255px, 255px);");
        imgView_tasksCount.setStyle("-fx-effect: innershadow( gaussian, #000, 10, 0, 255px, 255px);");
        imgView_updated.setStyle("-fx-effect: innershadow( gaussian, #000, 10, 0, 255px, 255px);");
    }
    public void thirdStep(){
        buttons_hbox.setStyle("-fx-scale-x: 0;");
        buttons_hbox.setStyle("-fx-scale-y: 0;");
        buttons_hbox.setStyle("-fx-pref-height: 0;");
        imgView_Done.setStyle("-fx-scale-y: 0;");
        imgView_addSubtask.setStyle("-fx-scale-y: 0;");
        imgView_remove.setStyle("-fx-scale-y: 0;");
        imgView_edit.setStyle("-fx-scale-y: 0;");
        bt_edit_Project.setStyle("-fx-scale-y: 0;");
        txtField_title_project.setStyle("-fx-text-fill: -WHITE;");
        txtField_description_project.setStyle("-fx-text-fill: -LIGHTER-GRAY;");
        label_status.setStyle("-fx-text-fill: -LIGHTER-GRAY;");
        label_type.setStyle("-fx-text-fill: -LIGHTER-GRAY;");
        label_priority.setStyle("-fx-text-fill: -LIGHTER-GRAY;");
        label_Pstart.setStyle("-fx-text-fill: -LIGHTER-GRAY;");
        label_Pfinish.setStyle("-fx-text-fill: -LIGHTER-GRAY;");
        label_remaining.setStyle("-fx-text-fill: -LIGHTER-GRAY;");
        label_updatedAt.setStyle("-fx-text-fill: -LIGHTER-GRAY;");
        imgView_task_Pstart.setStyle("-fx-effect: innershadow( gaussian, #000, 0, 0, 0px, 0px);");
        imgView_task_Pfinish.setStyle("-fx-effect: innershadow( gaussian, #000, 0, 0, 0px, 0px);");
        imgView_tasksCount.setStyle("-fx-effect: innershadow( gaussian, #000, 0, 0, 0px, 0px);");
        imgView_updated.setStyle("-fx-effect: innershadow( gaussian, #000, 0, 0, 0px, 0px);");
    }
    public void animation(MouseEvent mouseEvent) {
        if (mouseEvent.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
            // Cancel any existing pause
            if (pause != null) {
                pause.stop();
            }
            // Cancel any existing animation
            if (animation != null) {
                animation.stop();
            }
            // Create and play a new animation
            animation = new TranslateTransition(Duration.millis(300), vbox_project_fxml);
            animation.setFromY(0);
            animation.setToY(-16);
            animation.play();

            vbox_project_fxml.setStyle("-fx-effect: dropshadow(three-pass-box, #000, 10, 0, 10px, 10px)");
        } else {
            // Cancel any existing pause
            if (pause != null) {
                pause.stop();
            }

            // Create and play a new pause
            pause = new PauseTransition(Duration.millis(100));
            pause.setOnFinished(event -> {
                // Create and play a new animation
                animation = new TranslateTransition(Duration.millis(150), vbox_project_fxml);
                animation.setToY(0);
                animation.play();

                vbox_project_fxml.setStyle("-fx-effect: dropshadow(three-pass-box, #000, 0, 0, 0, 0)");
            });
            pause.play();
        }
    }
}
