package pcws.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pcws.service.memberService.MemberServiceInf;
import pcws.vo.MemberVO;

public class Mypage_Controller {

	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView log;

    @FXML
    private ComboBox<?> comboCate;

    @FXML
    private TextField txtSearch;

    @FXML
    private ComboBox<?> comboLog;

    @FXML
    private TextField tfId;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfBirth;

    @FXML
    private TextField tfGender;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfTel;

    @FXML
    void alertClick(ActionEvent event) {
       	try {
    		Parent changeAlert = FXMLLoader.load(getClass().getResource("../view/WorkAlarm.fxml"));
			Scene alertScene = new Scene(changeAlert);
			primaryStage.setScene(alertScene);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void memberClick(ActionEvent event) {

    }

    @FXML
    void mypageClick(ActionEvent event) {

    }

    Mypage_Out_Controller mypageOut;
    
    @FXML
    void outBtn(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Mypage_Out.fxml"));
			Parent change = loader.load();

			mypageOut = loader.getController();
			mypageOut.setMc(this);
		
			Stage stage = new Stage(StageStyle.UTILITY);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(primaryStage);

			Scene scene = new Scene(change);
			stage.setTitle("탈퇴 하기");
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void pcwsClick(MouseEvent event) {

    }

    @FXML
    void projectClick(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ProjectTap.fxml"));
    		Parent changePro = loader.load();
    		
			Scene proScene = new Scene(changePro);
			primaryStage.setScene(proScene);
			
			ProjectTap_Controller pjTap = loader.getController();
			pjTap.setPrimaryStage(primaryStage);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void searchClick(MouseEvent event) {

    }

    @FXML
    void taskClick(ActionEvent event) {

    }

    @FXML
    void updateBtn(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Mypage_Update.fxml"));
    		Parent changeMypage = loader.load();
    		
			Scene mypageUpdateScene = new Scene(changeMypage);
			primaryStage.setScene(mypageUpdateScene);
			
			MypageUpdate_Controller myPageUpdate = loader.getController();
			myPageUpdate.setPrimaryStage(primaryStage);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private MemberServiceInf memService;
    
    @FXML
    void initialize() throws RemoteException, NotBoundException {
        assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'Mypage.fxml'.";
        assert comboCate != null : "fx:id=\"comboCate\" was not injected: check your FXML file 'Mypage.fxml'.";
        assert txtSearch != null : "fx:id=\"txtSearch\" was not injected: check your FXML file 'Mypage.fxml'.";
        assert comboLog != null : "fx:id=\"comboLog\" was not injected: check your FXML file 'Mypage.fxml'.";
        assert tfId != null : "fx:id=\"tfId\" was not injected: check your FXML file 'Mypage.fxml'.";
        assert tfName != null : "fx:id=\"tfName\" was not injected: check your FXML file 'Mypage.fxml'.";
        assert tfBirth != null : "fx:id=\"tfBirth\" was not injected: check your FXML file 'Mypage.fxml'.";
        assert tfGender != null : "fx:id=\"tfGender\" was not injected: check your FXML file 'Mypage.fxml'.";
        assert tfEmail != null : "fx:id=\"tfEmail\" was not injected: check your FXML file 'Mypage.fxml'.";
        assert tfTel != null : "fx:id=\"tfTel\" was not injected: check your FXML file 'Mypage.fxml'.";
        
        // 등록된 서버를 찾기 위해 Registry객체를 생성한다.
     	Registry reg = LocateRegistry.getRegistry("localhost", 5959);

     	// 서버에서 공유한 객체의 Alias를 이용하여 사용할 객체를 구한다.
     	memService = (MemberServiceInf) reg.lookup("mem_server");
        
     	MemberVO memberVo = new MemberVO();
     	memberVo = MemberInfo.mvo;
     	
     	//필드창에 해당 정보 넣어 주기
        tfId.setText(memberVo.getMem_id());
        tfName.setText(memberVo.getMem_nm());
        tfBirth.setText(memberVo.getMem_birth());
        tfGender.setText(memberVo.getMem_gen());
        tfEmail.setText(memberVo.getMem_email());
        tfTel.setText(memberVo.getMem_tel());
        
        //입력창 비활성화
        tfId.setDisable(true);
        tfName.setDisable(true);
        tfBirth.setDisable(true);
        tfGender.setDisable(true);
        tfEmail.setDisable(true);
        tfTel.setDisable(true);
        
    }
    
    public void reset() {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Start.fxml"));
    		Parent change = loader.load();
    		
			Scene scene = new Scene(change);
			primaryStage.setScene(scene);
			
			Start_Controller start_controller = loader.getController();
			start_controller.setPrimaryStage(primaryStage);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
