package javafx.modelo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TaskController{

    @FXML
    private Button bt_addsubtask_controls;

    @FXML
    private Button bt_completed_controls;

    @FXML
    private Button bt_edit_controls;

    @FXML
    private Button bt_expand_controls;

    @FXML
    private Button bt_remove_controls;

    @FXML
    private HBox hbox_controls;

    @FXML
    private HBox hbox_sections;

    @FXML
    private Label label_created_at;

    @FXML
    private Label label_status;

    @FXML
    private TextField txtField_PlannedFinish;

    @FXML
    private TextField txtField_Priority;

    @FXML
    private TextField txtField_title;

    @FXML
    private TextField txtField_type;

    @FXML
    private VBox vbox_task_fxml;
    @FXML
    private ImageView imgView_edit;


    private Task task;

    private boolean isEditing = true;

    private MainController mainController;


    public void setTaskData(Task task){
        txtField_type.setText(task.getType_task());
        txtField_title.setText(task.getTitle_task());
        label_status.setText(task.getStatus_task());
        txtField_Priority.setText(task.getPriority_task());
        label_created_at.setText(task.getTask_created_at());
        txtField_PlannedFinish.setText(task.getPlanned_finish());

        this.task = task;
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
    public void btExpandTaskAction(ActionEvent event){
        mainController.toFrontExpand(task);
        callRefreshGridSubtasks();
    }

    @FXML
    public void btEditTaskAction(ActionEvent event){
        if(isEditing){
            bt_edit_controls.setStyle("-fx-background-color: #FFBE0B;");
            Image image = new Image("C:\\Java Projects\\Task_Manager\\src\\main\\resources\\javafx\\modelo\\images\\ok_96px.png");
            imgView_edit.setImage(image);

            txtField_type.setEditable(true);
            txtField_title.setEditable(true);
            txtField_Priority.setEditable(true);
            txtField_PlannedFinish.setEditable(true);
            txtField_title.requestFocus();
            isEditing = false;
        } else {
            bt_edit_controls.setStyle("-fx-background-color: transparent;");
            Image image = new Image("C:\\Java Projects\\Task_Manager\\src\\main\\resources\\javafx\\modelo\\images\\edit_104px.png");
            imgView_edit.setImage(image);

            txtField_type.setEditable(false);
            txtField_title.setEditable(false);
            txtField_Priority.setEditable(false);
            txtField_PlannedFinish.setEditable(false);
            saveTask();
        }
    }
    private void saveTask(){
        task.setType_task(txtField_type.getText());
        task.setTitle_task(txtField_title.getText());
        task.setPriority_task(txtField_Priority.getText());
        task.setPlanned_finish(txtField_PlannedFinish.getText());

        DataBase.editTask(task);
        callRefreshGridTasks();
    }
}
