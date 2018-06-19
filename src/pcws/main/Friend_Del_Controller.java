package pcws.main;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import pcws.service.friendService.FriendServiceInf;

public class Friend_Del_Controller {
	
	private Stage primaryStage;
	private Stage st;
	private Friend_Controller fc;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void setFc(Friend_Controller fc) {
		this.fc = fc;
	}
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button confirm;

    @FXML
    private Button cancel;

    @FXML
    void confirmAction(ActionEvent event) {
    	try {
    		fc.del();
    		fc.reset();
    		fc.initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	st = (Stage) confirm.getScene().getWindow();
    	st.close();
    }

    @FXML
    void cancelAction(ActionEvent event) {
    	st = (Stage) cancel.getScene().getWindow();
    	st.close();
    }
    
    @FXML
    void initialize() {
        assert confirm != null : "fx:id=\"confirm\" was not injected: check your FXML file 'Friend_Del.fxml'.";
        assert cancel != null : "fx:id=\"cancel\" was not injected: check your FXML file 'Friend_Del.fxml'.";

    }
    
	private void showAlertInfo(String string) {
		Alert alertInfomation = new Alert(AlertType.INFORMATION);
		alertInfomation.setTitle("");
		alertInfomation.setHeaderText("알림");
		alertInfomation.setContentText(string);
		alertInfomation.showAndWait(); // Alert창 보여주기 (창을 띄우고 기다려줌) -> 꼭 해줘야야 뜸.
	}

}
