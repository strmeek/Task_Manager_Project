package javafx.modelo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TaskController {

    @FXML
    private VBox vbox_task_fxml;

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
    private Label label_planned_finish;

    @FXML
    private Label label_priority;

    @FXML
    private Label label_status;

    @FXML
    private Label label_title;

    @FXML
    private Label label_type;


    public void setTaskData(Task task){
        label_type.setText(task.getType_task());
        label_title.setText(task.getTitle_task());
        label_status.setText(task.getStatus_task());
        label_priority.setText(task.getPriority_task());
        label_created_at.setText(task.getTask_created_at());
        label_planned_finish.setText(task.getPlanned_finish());
    }
}
