package javafx.modelo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class SubtaskController {

    @FXML
    private Button bt_completed_subtask;

    @FXML
    private Button bt_edit_subtask;

    @FXML
    private Button bt_remove_subtask;

    @FXML
    private HBox hbox_controls;

    @FXML
    private Label labelPlannedEnd;

    @FXML
    private Label labelPlannedStart;

    @FXML
    private Label labelPrioritySubtask;

    @FXML
    private Label labelStatusSubtask;

    @FXML
    private TextField txtFieldDescription;

    @FXML
    private TextField txtFieldTitle;

    private Subtask subtask;
    private int foreign_idTask;
    private MainController mainController;

    public void setSubtaskData(Subtask subtask){
        txtFieldTitle.setText(subtask.getTitle_subtask());
        txtFieldDescription.setText(subtask.getDescription_subtask());
        labelPrioritySubtask.setText(subtask.getPriority_subtask());
        labelStatusSubtask.setText(subtask.getStatus_subtask());
        labelPlannedStart.setText(subtask.getSubtask_plannedStart());
        labelPlannedEnd.setText(subtask.getSubtask_plannedFinish());

        this.subtask = subtask;
        this.foreign_idTask = subtask.getForeign_idTask();
    }
    /*ChatGPT solution to a big error i was having*/
    public void setMainController(MainController mainController){
        this.mainController = mainController;
    }
    public void callRefreshGridSubtasks(){
        mainController.refreshGridSubtasks(foreign_idTask);
    }
    /* ------------------------------------ */

    @FXML
    private void btDeleteTaskAction(ActionEvent event) {
        DataBase.deleteSubtask(subtask);
        callRefreshGridSubtasks();
    }

    @FXML
    private void btCompleteTaskAction(ActionEvent event) {
        DataBase.completeSubtask(subtask);
        callRefreshGridSubtasks();
    }

}
