package pcws.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Admin_Search_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField searchText;

    @FXML
    private Button search;

    @FXML
    private ListView<?> listView;

    @FXML
    private Button out;

    @FXML
    private Button concle;

    @FXML
    void concleAction(ActionEvent event) {

    }

    @FXML
    void onMouseClick(MouseEvent event) {

    }

    @FXML
    void outAction(ActionEvent event) {

    }

    @FXML
    void searchAction(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert searchText != null : "fx:id=\"searchText\" was not injected: check your FXML file 'Admin_Search.fxml'.";
        assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'Admin_Search.fxml'.";
        assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'Admin_Search.fxml'.";
        assert out != null : "fx:id=\"out\" was not injected: check your FXML file 'Admin_Search.fxml'.";
        assert concle != null : "fx:id=\"concle\" was not injected: check your FXML file 'Admin_Search.fxml'.";

    }
}
