package javafx.modelo;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class TaskController{

    @FXML
    private Button bt_Pfinish_task;

    @FXML
    private Button bt_Pstart_task;

    @FXML
    private Button bt_addsubtask_task;

    @FXML
    private Button bt_completed_task;

    @FXML
    private Button bt_edit_task;

    @FXML
    private Button bt_priority_tasks;

    @FXML
    private Button bt_project_task;

    @FXML
    private Button bt_remove_task;

    @FXML
    private Button bt_status_task;

    @FXML
    private Button bt_type_task;
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
    private ImageView imgView_task_project;

    @FXML
    private Label label_Pfinish;

    @FXML
    private Label label_Pstart;

    @FXML
    private Label label_priority;

    @FXML
    private Label label_project;

    @FXML
    private Label label_status;

    @FXML
    private Label label_type;

    @FXML
    private TextField txtField_title;

    @FXML
    private HBox buttons_hbox;

    @FXML
    private VBox vbox_task_fxml;

    @FXML
    private VBox vbox_task_over;

    private Task task;

    private boolean isEditing = false;
    private TranslateTransition animation;
    private PauseTransition pause;

    private MainController mainController;

    public void setTaskData(Task task){
        label_status.setText(task.getStatus_task());
        label_type.setText(task.getType_task());
        label_priority.setText(task.getPriority_task());

        txtField_title.setText(task.getTitle_task());
        txtField_title.setEditable(false);
        label_project.setText(task.getProject_task());

        label_Pstart.setText(task.getPlanned_start());
        label_Pfinish.setText(task.getPlanned_finish());

        this.task = task;

        /*Buttons appear when you enter the task*/
        firstStep();

        vbox_task_fxml.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                secondStep();
                animation(mouseEvent);
            }
        });

        vbox_task_fxml.setOnMouseExited(new EventHandler<MouseEvent>() {
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
    public void callRefreshGridSubtasks(){
        mainController.refreshGridSubtasks();
    }
    /* ------------------------------------ */
    @FXML
    private void btDeleteTaskAction(ActionEvent event) {
        DataBase.deleteTask(task);
        callRefreshGridTasks();
        DataBase.deleteAllSubtask(task);
    }

    @FXML
    private void btCompleteTaskAction(ActionEvent event) {
        DataBase.completeTask(task);
        callRefreshGridTasks();
        DataBase.completeAllSubtask(task);
    }
    @FXML
    private void btAddSubtaskAction(ActionEvent event){
        mainController.toFrontNewSubtask(task);
        callRefreshGridSubtasks();
    }

    @FXML
    public void btEditTaskAction(ActionEvent event) {
        if (!isEditing) {
            bt_edit_task.setStyle("-fx-background-color: -EDIT-BT;");
            Image image = new Image("C:\\Java Projects\\Task_Manager\\src\\main\\resources\\javafx\\modelo\\images\\ok_96px.png");
            imgView_edit.setImage(image);

            txtField_title.setEditable(true);
            txtField_title.requestFocus();
            isEditing = true;
        } else {
            bt_edit_task.setStyle("-fx-background-color: transparent;");
            Image image = new Image("C:\\Java Projects\\Task_Manager\\src\\main\\resources\\javafx\\modelo\\images\\edit_104px.png");
            imgView_edit.setImage(image);

            txtField_title.setEditable(false);
            saveTask();
        }
    }
    private void saveTask(){
        task.setTitle_task(txtField_title.getText());

        DataBase.editTask(task);
        callRefreshGridTasks();
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
            animation = new TranslateTransition(Duration.millis(300), vbox_task_fxml);
            animation.setFromY(0);
            animation.setToY(-16);
            animation.play();

            vbox_task_fxml.setStyle("-fx-effect: dropshadow(three-pass-box, #000, 10, 0, 10px, 10px)");
        } else {
            // Cancel any existing pause
            if (pause != null) {
                pause.stop();
            }

            // Create and play a new pause
            pause = new PauseTransition(Duration.millis(100));
            pause.setOnFinished(event -> {
                // Create and play a new animation
                animation = new TranslateTransition(Duration.millis(150), vbox_task_fxml);
                animation.setToY(0);
                animation.play();

                vbox_task_fxml.setStyle("-fx-effect: dropshadow(three-pass-box, #000, 0, 0, 0, 0)");
            });
            pause.play();
        }
    }
    public void btExpandTaskAction() {
        if (!isEditing) {
            mainController.toFrontExpand(task);
            callRefreshGridSubtasks();
        } else{
            txtField_title.setEditable(true);
            isEditing = true;
        }
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
        buttons_hbox.setStyle("-fx-pref-height: 30;");
        imgView_Done.setStyle("-fx-scale-y: 1;");
        imgView_addSubtask.setStyle("-fx-scale-y: 1;");
        imgView_remove.setStyle("-fx-scale-y: 1;");
        imgView_edit.setStyle("-fx-scale-y: 1;");
        bt_edit_task.setStyle("-fx-scale-y: 1;");
        txtField_title.setStyle("-fx-text-fill: -BLACK;");
        label_status.setStyle("-fx-text-fill: -GRAY;");
        label_type.setStyle("-fx-text-fill: -GRAY;");
        label_priority.setStyle("-fx-text-fill: -GRAY;");
        label_Pstart.setStyle("-fx-text-fill: -GRAY;");
        label_Pfinish.setStyle("-fx-text-fill: -GRAY;");
        label_project.setStyle("-fx-text-fill: -GRAY;");
        imgView_task_project.setStyle("-fx-effect: innershadow( gaussian, #000, 10, 0, 255px, 255px)");
        imgView_task_Pstart.setStyle("-fx-effect: innershadow( gaussian, #000, 10, 0, 255px, 255px)");
        imgView_task_Pfinish.setStyle("-fx-effect: innershadow( gaussian, #000, 10, 0, 255px, 255px)");
    }
    public void thirdStep(){
        buttons_hbox.setStyle("-fx-scale-x: 0;");
        buttons_hbox.setStyle("-fx-scale-y: 0;");
        buttons_hbox.setStyle("-fx-pref-height: 0;");
        imgView_Done.setStyle("-fx-scale-y: 0;");
        imgView_addSubtask.setStyle("-fx-scale-y: 0;");
        imgView_remove.setStyle("-fx-scale-y: 0;");
        imgView_edit.setStyle("-fx-scale-y: 0;");
        bt_edit_task.setStyle("-fx-scale-y: 0;");
        txtField_title.setStyle("-fx-text-fill: -WHITE;");
        label_status.setStyle("-fx-text-fill: -LIGHT-GRAY;");
        label_type.setStyle("-fx-text-fill: -LIGHT-GRAY;");
        label_priority.setStyle("-fx-text-fill: -LIGHT-GRAY;");
        label_Pstart.setStyle("-fx-text-fill: -LIGHT-GRAY;");
        label_Pfinish.setStyle("-fx-text-fill: -LIGHT-GRAY;");
        label_project.setStyle("-fx-text-fill: -LIGHT-GRAY;");
        imgView_task_project.setStyle("-fx-effect: innershadow( gaussian, #000, 0, 0, 0px, 0px)");
        imgView_task_Pstart.setStyle("-fx-effect: innershadow( gaussian, #000, 0, 0, 0px, 0px)");
        imgView_task_Pfinish.setStyle("-fx-effect: innershadow( gaussian, #000, 0, 0, 0px, 0px)");
    }
}
