package pcws.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pcws.service.memberService.MemberServiceInf;

public class Start_Controller {

	private Stage primaryStage;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField txtID;

	@FXML
	private PasswordField txtPW;

	@FXML
	private Button btnLogin;

	@FXML
	void find_ID(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Find_ID.fxml"));
			Parent changeFintId = loader.load();

			Find_Id_Controller find_id = loader.getController();

			Stage findIdStage = new Stage(StageStyle.UTILITY);
			findIdStage.initModality(Modality.WINDOW_MODAL);
			findIdStage.initOwner(primaryStage);

			Scene findIdScene = new Scene(changeFintId);
			findIdStage.setTitle("아이디 찾기");
			findIdStage.setScene(findIdScene);
			findIdStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void find_PW(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Find_PW.fxml"));
			Parent changeFindPw = loader.load();

			Find_Pw_Controller find_pw = loader.getController();

			Stage findPwStage = new Stage(StageStyle.UTILITY);
			findPwStage.initModality(Modality.WINDOW_MODAL);
			findPwStage.initOwner(primaryStage);

			Scene findIdScene = new Scene(changeFindPw);
			findPwStage.setTitle("아이디 찾기");
			findPwStage.setScene(findIdScene);
			findPwStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void join(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Start_Join.fxml"));
			Parent changeJoin = loader.load();
			// Parent changeAlert =
			// FXMLLoader.load(getClass().getResource("../view/ProListSearch.fxml"));

			Scene joinScene = new Scene(changeJoin);
			primaryStage.setScene(joinScene);

			Start_Join_Controller start_join = loader.getController();
			start_join.setPrimaryStage(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void loginClick(ActionEvent event) {
		try {
			// MemberInfo.mvo : 로그인한 사용자 정보
			MemberInfo.mvo = memService.logIn(txtID.getText(), txtPW.getText());
			
			if (MemberInfo.mvo != null) {
				if(MemberInfo.mvo.getMem_admin().equals("true")) {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Friend.fxml"));
					Parent changeAlert = loader.load();
					// Parent changeAlert =
					// FXMLLoader.load(getClass().getResource("../view/ProListSearch.fxml"));

					Scene alertScene = new Scene(changeAlert);
					primaryStage.setScene(alertScene);

					Friend_Controller friendController = loader.getController();
					friendController.setPrimaryStage(primaryStage);
				}else {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/WorkAlarm.fxml"));
					Parent changeAlert = loader.load();
					// Parent changeAlert =
					// FXMLLoader.load(getClass().getResource("../view/ProListSearch.fxml"));
		
					Scene alertScene = new Scene(changeAlert);
					primaryStage.setScene(alertScene);
		
					WorkAlarm_Controller proListSearchController = loader.getController();
					proListSearchController.setPrimaryStage(primaryStage);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	MemberServiceInf memService;

	@FXML
	void initialize() throws RemoteException, NotBoundException {
		assert txtID != null : "fx:id=\"txtID\" was not injected: check your FXML file 'Start.fxml'.";
		assert txtPW != null : "fx:id=\"txtPW\" was not injected: check your FXML file 'Start.fxml'.";
		assert btnLogin != null : "fx:id=\"btnLogin\" was not injected: check your FXML file 'Start.fxml'.";

		// 등록된 서버를 찾기 위해 Registry객체를 생성한다.
		Registry reg = LocateRegistry.getRegistry("localhost", 5959);
//		Registry reg = LocateRegistry.getRegistry("192.168.0.60", 5959);

		// 서버에서 공유한 객체의 Alias를 이용하여 사용할 객체를 구한다.
		memService = (MemberServiceInf)reg.lookup("mem_server");
	}
}
