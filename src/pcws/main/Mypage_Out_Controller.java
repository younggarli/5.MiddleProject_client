package pcws.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pcws.service.memberService.MemberServiceInf;
import pcws.vo.MemberVO;

public class Mypage_Out_Controller {

	private Stage primaryStage;
	private Stage st;
	private Mypage_Controller mc;

	public void setMc(Mypage_Controller mc) {
		this.mc = mc;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button confirm;

	@FXML
	private Button cancle;

	@FXML
	void cancleAction(ActionEvent event) {
		st = (Stage) cancle.getScene().getWindow();
		st.close();
	}

	
	
	@FXML
	void confirmAction(ActionEvent event) throws IOException {
		memberService.memberOut(null);
		
		st = (Stage) confirm.getScene().getWindow();
		st.close();
		
		mc.reset();
	}

	private MemberServiceInf memberService;

	@FXML
	void initialize() {
		assert confirm != null : "fx:id=\"confirm\" was not injected: check your FXML file 'Mypage_Out.fxml'.";
		assert cancle != null : "fx:id=\"cancle\" was not injected: check your FXML file 'Mypage_Out.fxml'.";

		try {
			// 등록된 서버를 찾기 위해 Registry객체를 생성한다.
			Registry reg = LocateRegistry.getRegistry("localhost", 5959);

			// 서버에서 공유한 객체의 Alias를 이용하여 사용할 객체를 구한다.
			memberService = (MemberServiceInf) reg.lookup("mem_server");
		} catch (Exception e) {
		}
	}
}
