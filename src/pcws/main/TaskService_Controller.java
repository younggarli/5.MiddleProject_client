package pcws.main;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class TaskService_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane colorAppear;

    @FXML
    private Button updateBtn;

    @FXML
    private ChoiceBox<?> tagChoice;

    @FXML
    private DatePicker endDate;

    @FXML
    private ColorPicker colorChoice;

    @FXML
    private Button contentBtn;

    @FXML
    private TextField taskName;

    @FXML
    private Button chkListBtn;

    @FXML
    private TextField importace;

    @FXML
    private DatePicker startDate;

    @FXML
    private TextField taskTime;

    @FXML
    private Button deleteBtn;

    @FXML
    void contentBtnAct(ActionEvent event) {

    }

    @FXML
    void chkListAct(ActionEvent event) {

    }

    @FXML
    void updateBtnAct(ActionEvent event) {

    }

    @FXML
    void deleteBtnAct(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert colorAppear != null : "fx:id=\"colorAppear\" was not injected: check your FXML file 'TaskList.fxml'.";
        assert updateBtn != null : "fx:id=\"updateBtn\" was not injected: check your FXML file 'TaskList.fxml'.";
        assert tagChoice != null : "fx:id=\"tagChoice\" was not injected: check your FXML file 'TaskList.fxml'.";
        assert endDate != null : "fx:id=\"endDate\" was not injected: check your FXML file 'TaskList.fxml'.";
        assert colorChoice != null : "fx:id=\"colorChoice\" was not injected: check your FXML file 'TaskList.fxml'.";
        assert contentBtn != null : "fx:id=\"contentBtn\" was not injected: check your FXML file 'TaskList.fxml'.";
        assert taskName != null : "fx:id=\"taskName\" was not injected: check your FXML file 'TaskList.fxml'.";
        assert chkListBtn != null : "fx:id=\"chkListBtn\" was not injected: check your FXML file 'TaskList.fxml'.";
        assert importace != null : "fx:id=\"importace\" was not injected: check your FXML file 'TaskList.fxml'.";
        assert startDate != null : "fx:id=\"startDate\" was not injected: check your FXML file 'TaskList.fxml'.";
        assert taskTime != null : "fx:id=\"taskTime\" was not injected: check your FXML file 'TaskList.fxml'.";
        assert deleteBtn != null : "fx:id=\"deleteBtn\" was not injected: check your FXML file 'TaskList.fxml'.";

    }
}
