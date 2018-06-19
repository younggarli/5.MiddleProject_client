package pcws.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ClientMain extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Start.fxml"));
		Font.loadFont(getClass().getResourceAsStream("Roboto-Regular.ttf"), 14);
		Parent root = loader.load();
		
		Start_Controller startController = loader.getController();
		startController.setPrimaryStage(primaryStage);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Client Main");
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
