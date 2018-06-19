package pcws.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TaskAdd_Controller {

	private Stage primaryStage;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	private ProjectTap_Controller pjController;
	
	public void setPjController(ProjectTap_Controller pjController) {
		this.pjController = pjController;
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void plusClick(MouseEvent event) {
    	try {
    		Stage makeStage = new Stage();
			makeStage.initModality(Modality.WINDOW_MODAL);
			makeStage.initOwner(primaryStage);
			
//    		Parent changeMake = FXMLLoader.load(getClass().getResource("../view/Project_Make.fxml"));
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Project_Make.fxml"));
    		Parent changeMake = loader.load();
    		
    		Project_Make_Controller controller = loader.getController();
    		
    		controller.setPjController(pjController);
    		
			Scene makeScene = new Scene(changeMake);
			makeStage.setTitle("project create");
			makeStage.setScene(makeScene);
			makeStage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {

    }
}

