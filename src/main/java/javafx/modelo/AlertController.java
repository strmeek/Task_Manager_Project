package javafx.modelo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AlertController implements Initializable{

    @FXML
    private VBox alert_vbox;

    @FXML
    private Button bt_alert;

    @FXML
    private Label label_alert;

    public VBox getAlert_vbox() {
        return alert_vbox;
    }

    public void setAlert_vbox(VBox alert_vbox) {
        this.alert_vbox = alert_vbox;
    }

    public Button getBt_alert() {
        return bt_alert;
    }

    public void setBt_alert(Button bt_alert) {
        this.bt_alert = bt_alert;
    }

    public Label getLabel_alert() {
        return label_alert;
    }

    public void setLabel_alert(Label label_alert) {
        this.label_alert = label_alert;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bt_alert.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                alert_vbox.setLayoutX(-10000);
            }
        });
    }
}
