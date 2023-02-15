package javafx.modelo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class SubtaskController {

    @FXML
    private Button bt_completed_subtask;

    @FXML
    private Button bt_edit_subtask;

    @FXML
    private Button bt_remove_subtask;

    @FXML
    private ImageView imgView_edit;

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

    private boolean isEditing = true;

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
        mainController.refreshGridSubtasks();
    }
    /* ------------------------------------ */

    @FXML
    private void btDeleteSubtaskAction(ActionEvent event) {
        DataBase.deleteSubtask(subtask);
        callRefreshGridSubtasks();
    }

    @FXML
    private void btCompleteSubtaskAction(ActionEvent event) {
        DataBase.completeSubtask(subtask);
        callRefreshGridSubtasks();
    }

    @FXML
    private void btEditSubtaskAction(ActionEvent event) {
        if(isEditing){
            bt_edit_subtask.setStyle("-fx-background-color: #FFBE0B;");
            Image image = new Image("C:\\Java Projects\\Task_Manager\\src\\main\\resources\\javafx\\modelo\\images\\ok_96px.png");
            imgView_edit.setImage(image);

            txtFieldTitle.setEditable(true);
            txtFieldDescription.setEditable(true);
            txtFieldTitle.requestFocus();
            isEditing = false;
        } else {
            bt_edit_subtask.setStyle("-fx-background-color: transparent;");
            Image image = new Image("C:\\Java Projects\\Task_Manager\\src\\main\\resources\\javafx\\modelo\\images\\edit_104px.png");
            imgView_edit.setImage(image);

            txtFieldTitle.setEditable(false);
            txtFieldDescription.setEditable(false);
            saveSubtask();
        }
    }
    private void saveSubtask(){
        subtask.setTitle_subtask(txtFieldTitle.getText());
        subtask.setDescription_subtask(txtFieldDescription.getText());

        DataBase.editSubtask(subtask);
        callRefreshGridSubtasks();
    }
}
