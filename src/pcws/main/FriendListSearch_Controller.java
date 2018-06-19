package pcws.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class FriendListSearch_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView log;

    @FXML
    private ComboBox<?> category;

    @FXML
    private Button search;

    @FXML
    private ComboBox<?> loginAndLogout;

    @FXML
    private Button myImage;

    @FXML
    private TableView<?> friTableView;

    @FXML
    private TableColumn<?, ?> firId;

    @FXML
    private TableColumn<?, ?> friName;

    @FXML
    private TableColumn<?, ?> friTel;

    @FXML
    private Button Delete;

    @FXML
    void alarnClick(ActionEvent event) {

    }

    @FXML
    void deleteAction(ActionEvent event) {

    }

    @FXML
    void friendDelete(MouseEvent event) {

    }

    @FXML
    void logClick(MouseEvent event) {

    }

    @FXML
    void memberClick(ActionEvent event) {

    }

    @FXML
    void myImageClick(ActionEvent event) {

    }

    @FXML
    void mypageClick(ActionEvent event) {

    }

    @FXML
    void projectClick(ActionEvent event) {

    }

    @FXML
    void searchClick(ActionEvent event) {

    }

    @FXML
    void taskClick(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'FriendListSearch.fxml'.";
        assert category != null : "fx:id=\"category\" was not injected: check your FXML file 'FriendListSearch.fxml'.";
        assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'FriendListSearch.fxml'.";
        assert loginAndLogout != null : "fx:id=\"loginAndLogout\" was not injected: check your FXML file 'FriendListSearch.fxml'.";
        assert myImage != null : "fx:id=\"myImage\" was not injected: check your FXML file 'FriendListSearch.fxml'.";
        assert friTableView != null : "fx:id=\"friTableView\" was not injected: check your FXML file 'FriendListSearch.fxml'.";
        assert firId != null : "fx:id=\"firId\" was not injected: check your FXML file 'FriendListSearch.fxml'.";
        assert friName != null : "fx:id=\"friName\" was not injected: check your FXML file 'FriendListSearch.fxml'.";
        assert friTel != null : "fx:id=\"friTel\" was not injected: check your FXML file 'FriendListSearch.fxml'.";
        assert Delete != null : "fx:id=\"Delete\" was not injected: check your FXML file 'FriendListSearch.fxml'.";

    }
}
