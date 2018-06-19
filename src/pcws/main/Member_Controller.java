package pcws.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class Member_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField idTf;

    @FXML
    private TextField pwTf;

    @FXML
    private TextField pwOkTf;

    @FXML
    private TextField nmTf;

    @FXML
    private DatePicker birthDp;

    @FXML
    private ComboBox<?> genCb;

    @FXML
    private TextField emailTf;

    @FXML
    private TextField emainOkTf;

    @FXML
    private TextField telTf;

    @FXML
    void cancleBt(ActionEvent event) {

    }

    @FXML
    void certifiedBt(ActionEvent event) {

    }

    @FXML
    void joinBt(ActionEvent event) {

    }

    @FXML
    void okBt(ActionEvent event) {
    	
    }

    @FXML
    void overlapBt(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert idTf != null : "fx:id=\"idTf\" was not injected: check your FXML file 'Start_Join.fxml'.";
        assert pwTf != null : "fx:id=\"pwTf\" was not injected: check your FXML file 'Start_Join.fxml'.";
        assert pwOkTf != null : "fx:id=\"pwOkTf\" was not injected: check your FXML file 'Start_Join.fxml'.";
        assert nmTf != null : "fx:id=\"nmTf\" was not injected: check your FXML file 'Start_Join.fxml'.";
        assert birthDp != null : "fx:id=\"birthDp\" was not injected: check your FXML file 'Start_Join.fxml'.";
        assert genCb != null : "fx:id=\"genCb\" was not injected: check your FXML file 'Start_Join.fxml'.";
        assert emailTf != null : "fx:id=\"emailTf\" was not injected: check your FXML file 'Start_Join.fxml'.";
        assert emainOkTf != null : "fx:id=\"emainOkTf\" was not injected: check your FXML file 'Start_Join.fxml'.";
        assert telTf != null : "fx:id=\"telTf\" was not injected: check your FXML file 'Start_Join.fxml'.";

    }
}
