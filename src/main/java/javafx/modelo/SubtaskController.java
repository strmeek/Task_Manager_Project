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

public class SubtaskController {

    @FXML
    private Button bt_completed_subtask;

    @FXML
    private Button bt_edit_subtask;

    @FXML
    private Button bt_remove_subtask;

    @FXML
    private ImageView imgView_Done;

    @FXML
    private ImageView imgView_edit;

    @FXML
    private ImageView imgView_remove;

    @FXML
    private ImageView imgView_task_Pfinish;

    @FXML
    private ImageView imgView_task_Pstart;

    @FXML
    private HBox buttons_hbox;

    @FXML
    private Label labelPlannedEnd;

    @FXML
    private Label labelPlannedStart;

    @FXML
    private Label labelPrioritySubtask;

    @FXML
    private Label labelTypeSubtask;

    @FXML
    private Label labelStatusSubtask;

    @FXML
    private TextField txtFieldDescription;

    @FXML
    private TextField txtFieldTitle;

    @FXML
    private VBox vbox_subtask;

    /*Stores information*/
    private Subtask subtask;
    private int foreign_idTask;
    private MainController mainController;

    /*Animation Variables*/
    private TranslateTransition animation;
    private PauseTransition pause;
    /*Variable necessary to make the toggle edit button work*/
    private boolean isEditing = true;

    /*Sets all subtask data inside the block*/
    public void setSubtaskData(Subtask subtask){
        txtFieldTitle.setText(subtask.getTitle_subtask());
        txtFieldDescription.setText(subtask.getDescription_subtask());
        labelPrioritySubtask.setText(subtask.getPriority_subtask());
        labelTypeSubtask.setText(subtask.getType_subtask());
        labelStatusSubtask.setText(subtask.getStatus_subtask());
        labelPlannedStart.setText(subtask.getSubtask_plannedStart());
        labelPlannedEnd.setText(subtask.getSubtask_plannedFinish());

        this.subtask = subtask;
        this.foreign_idTask = subtask.getForeign_idTask();

        /*Buttons appear when you enter the task*/
        firstStep();

        vbox_subtask.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                secondStep();
                animation(mouseEvent);
            }
        });

        vbox_subtask.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                thirdStep();
                animation(mouseEvent);
            }
        });
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

    /*Edit in toggle style*/
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
    /*Talks to the database and sucessfully edit the subtask*/
    private void saveSubtask(){
        subtask.setTitle_subtask(txtFieldTitle.getText());
        subtask.setDescription_subtask(txtFieldDescription.getText());

        DataBase.editSubtask(subtask);
        callRefreshGridSubtasks();
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
            animation = new TranslateTransition(Duration.millis(300), vbox_subtask);
            animation.setFromY(0);
            animation.setToY(-16);
            animation.play();

            vbox_subtask.setStyle("-fx-effect: dropshadow(three-pass-box, #000, 10, 0, 10px, 10px)");
        } else {
            // Cancel any existing pause
            if (pause != null) {
                pause.stop();
            }

            // Create and play a new pause
            pause = new PauseTransition(Duration.millis(100));
            pause.setOnFinished(event -> {
                // Create and play a new animation
                animation = new TranslateTransition(Duration.millis(150), vbox_subtask);
                animation.setToY(0);
                animation.play();

                vbox_subtask.setStyle("-fx-effect: dropshadow(three-pass-box, #000, 0, 0, 0, 0)");
            });
            pause.play();
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
        imgView_remove.setStyle("-fx-scale-y: 0;");
        imgView_edit.setStyle("-fx-scale-y: 0;");
    }

    public void secondStep(){
        /*All appear when you hover the task*/
        buttons_hbox.setStyle("-fx-scale-x: 1;");
        buttons_hbox.setStyle("-fx-scale-y: 1;");
        buttons_hbox.setStyle("-fx-pref-height: 60;");
        imgView_Done.setStyle("-fx-scale-y: 1;");
        imgView_remove.setStyle("-fx-scale-y: 1;");
        imgView_edit.setStyle("-fx-scale-y: 1;");
        bt_edit_subtask.setStyle("-fx-scale-y: 1;");
        txtFieldTitle.setStyle("-fx-text-fill: -BLACK;");
        labelStatusSubtask.setStyle("-fx-text-fill: -GRAY;");
        labelTypeSubtask.setStyle("-fx-text-fill: -GRAY;");
        labelPrioritySubtask.setStyle("-fx-text-fill: -GRAY;");
        labelPlannedStart.setStyle("-fx-text-fill: -GRAY;");
        labelPlannedEnd.setStyle("-fx-text-fill: -GRAY;");
        imgView_task_Pstart.setStyle("-fx-effect: innershadow( gaussian, #000, 10, 0, 255px, 255px)");
        imgView_task_Pfinish.setStyle("-fx-effect: innershadow( gaussian, #000, 10, 0, 255px, 255px)");
    }
    public void thirdStep(){
        buttons_hbox.setStyle("-fx-scale-x: 0;");
        buttons_hbox.setStyle("-fx-scale-y: 0;");
        buttons_hbox.setStyle("-fx-pref-height: 0;");
        imgView_Done.setStyle("-fx-scale-y: 0;");
        imgView_remove.setStyle("-fx-scale-y: 0;");
        imgView_edit.setStyle("-fx-scale-y: 0;");
        bt_edit_subtask.setStyle("-fx-scale-y: 0;");
        txtFieldTitle.setStyle("-fx-text-fill: -WHITE;");
        labelStatusSubtask.setStyle("-fx-text-fill: -LIGHT-GRAY;");
        labelTypeSubtask.setStyle("-fx-text-fill: -LIGHT-GRAY;");
        labelPrioritySubtask.setStyle("-fx-text-fill: -LIGHT-GRAY;");
        labelPlannedStart.setStyle("-fx-text-fill: -LIGHT-GRAY;");
        labelPlannedEnd.setStyle("-fx-text-fill: -LIGHT-GRAY;");
        imgView_task_Pstart.setStyle("-fx-effect: innershadow( gaussian, #000, 0, 0, 0px, 0px)");
        imgView_task_Pfinish.setStyle("-fx-effect: innershadow( gaussian, #000, 0, 0, 0px, 0px)");
    }
}
