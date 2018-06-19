package pcws.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pcws.service.memberService.MemberServiceInf;
import pcws.vo.MemberVO;

public class MypageUpdate_Controller {

	StringBuffer CertNum = new StringBuffer();
	
	
	
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
    private PasswordField tfChangePW;

    @FXML
    private PasswordField tfCheckPW;


    @FXML
    private TextField tfName;

    @FXML
    private TextField tfEmail;

    @FXML
    private Button checkBtn;

    @FXML
    private TextField tfCheck;

    @FXML
    private Button okBtn;

    @FXML
    private TextField tfTel;

    @FXML
    private Button updateBtn;

    @FXML
    private Button cancleBtn;

    @FXML
    void alertClick(ActionEvent event) {

    }

    @FXML
    void cancleClick(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Mypage.fxml"));
		Parent changeJoin = loader.load();
		// Parent changeAlert = FXMLLoader.load(getClass().getResource("../view/ProListSearch.fxml"));

		Scene joinScene = new Scene(changeJoin);

		primaryStage.setScene(joinScene);

		Mypage_Controller start = loader.getController();
		start.setPrimaryStage(primaryStage);
    }

    @FXML
    void confirmClick(ActionEvent event) throws IOException {
    	int stack = 0;
    	MemberVO mvo = MemberInfo.mvo;
    	
    	if(tfChangePW.getText().equals(tfCheckPW.getText())) {
    		mvo.setMem_pw(tfChangePW.getText());
    		stack++;
    	}
    	
       	if(!tfName.getText().isEmpty()) {
    		mvo.setMem_nm(tfName.getText());
    		stack++;
    	}
       	
    	if(tfEmail.getText()!=null) {
    		mvo.setMem_email(tfEmail.getText());
    		stack++;
    	}
    	
    	if(tfTel.getText()!=null) {
    		mvo.setMem_tel(tfTel.getText());
    		stack++;
    	}
    	
    	if(stack==4) {
    		memberService.updateMember(mvo);
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Mypage.fxml"));
    		Parent changeJoin = loader.load();
    		
    		Scene updateScene = new Scene(changeJoin);
    		primaryStage.setScene(updateScene);
    		
    		Mypage_Controller mapage_Controller = loader.getController();
    		mapage_Controller.setPrimaryStage(primaryStage);
    		showAlertInfo("정보가 수정 되었습니다!!");
    	}else {
    		showAlertInfo("입력하신 정보를 다시 확인해주세요.");
    	}
    	
    	
    }

    @FXML
    void emailCheckClick(ActionEvent event) {
    	// 랜덤 인증번호 생성
    	CertNum.setLength(0);
    	Random rnd = new Random();
    	for (int i = 0; i < 6; i++) {
    	    int rIndex = rnd.nextInt(3);
    	    switch (rIndex) {
    	    case 0:
    	        // a-z
    	    	CertNum.append((char) ((int) (rnd.nextInt(26)) + 97));
    	        break;
    	    case 1:
    	        // A-Z
    	    	CertNum.append((char) ((int) (rnd.nextInt(26)) + 65));
    	        break;
    	    case 2:
    	        // 0-9
    	    	CertNum.append((rnd.nextInt(10)));
    	        break;
    	    }
    	}

		// 메일 보내기 위한 셋팅
		String host = "smtp.naver.com";
		final String user = "ujisun93";
		final String password = "GKDKFRqNDLD1";

		String to = tfEmail.getText();

		// 객체 가져오기
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// 메시지 작성
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// 제목
			message.setSubject("인증 메일입니다.");

			// 내용
			message.setText("인증번호는 " + CertNum.toString() +"입니다.");

			// 전송 완료
			Transport.send(message);
			showAlertInfo("전송되었습니다.");
			tfEmail.setEditable(false);	// text 비활성화
			checkBtn.setDisable(true);	// 버튼 비활성화
		} catch (MessagingException e) {
			e.printStackTrace();
			showAlertInfo("전송 실패 했습니다.");
		}
		tfEmail.setEditable(true);
    }

    @FXML
    void emailConfirmClick(ActionEvent event) {
    	// 이메일 인증 코드 확인
    	if(tfCheck.getText().equals(CertNum.toString())) {
    		showAlertInfo("인증확인되었습니다.");
    		tfCheck.setEditable(false);	//  text 비활성화
    		okBtn.setDisable(true);	// 버튼 비활성화
    	}else {
    		showAlertInfo("인증 코드가 올바르지 않습니다.");
    		tfCheck.requestFocus();
    	}
    }

    @FXML
    void memberClick(ActionEvent event) {

    }

    @FXML
    void mypageClick(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Mypage.fxml"));
    		Parent changeMypage = loader.load();
//    		Parent changePro = FXMLLoader.load(getClass().getResource("../view/ProjectTap.fxml"));
    		
    		Scene Scene = new Scene(changeMypage);
    		primaryStage.setScene(Scene);
    		
    		Mypage_Controller MypageCon = loader.getController();
    		MypageCon.setPrimaryStage(primaryStage);
    		
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
//    		Parent changePro = FXMLLoader.load(getClass().getResource("../view/ProjectTap.fxml"));
    		
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


    // service 연동
    private MemberServiceInf memberService;
    
 	
    @FXML
    void initialize() throws AccessException, RemoteException, NotBoundException {
        assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'Mypage_Update.fxml'.";
        assert comboCate != null : "fx:id=\"comboCate\" was not injected: check your FXML file 'Mypage_Update.fxml'.";
        assert txtSearch != null : "fx:id=\"txtSearch\" was not injected: check your FXML file 'Mypage_Update.fxml'.";
        assert comboLog != null : "fx:id=\"comboLog\" was not injected: check your FXML file 'Mypage_Update.fxml'.";
        assert tfChangePW != null : "fx:id=\"tfChangePW\" was not injected: check your FXML file 'Mypage_Update.fxml'.";
        assert tfCheckPW != null : "fx:id=\"tfCheckPW\" was not injected: check your FXML file 'Mypage_Update.fxml'.";
        assert tfName != null : "fx:id=\"tfName\" was not injected: check your FXML file 'Mypage_Update.fxml'.";
        assert tfEmail != null : "fx:id=\"tfEmail\" was not injected: check your FXML file 'Mypage_Update.fxml'.";
        assert checkBtn != null : "fx:id=\"checkBtn\" was not injected: check your FXML file 'Mypage_Update.fxml'.";
        assert tfCheck != null : "fx:id=\"tfCheck\" was not injected: check your FXML file 'Mypage_Update.fxml'.";
        assert okBtn != null : "fx:id=\"okBtn\" was not injected: check your FXML file 'Mypage_Update.fxml'.";
        assert tfTel != null : "fx:id=\"tfTel\" was not injected: check your FXML file 'Mypage_Update.fxml'.";
        assert updateBtn != null : "fx:id=\"updateBtn\" was not injected: check your FXML file 'Mypage_Update.fxml'.";
        assert cancleBtn != null : "fx:id=\"cancleBtn\" was not injected: check your FXML file 'Mypage_Update.fxml'.";

     // 등록된 서버를 찾기 위해 Registry 객체를 생성한다.
        Registry reg = null;
        try {
			reg = LocateRegistry.getRegistry("localhost",5959);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
        // 서버에서 공유한 객체의 Alias를 이용하여 사용할 객체를 구한다.
        memberService = (MemberServiceInf) reg.lookup("mem_server");
        
    }
    private void showAlertInfo(String string) {
		Alert alertInfomation = new Alert(AlertType.INFORMATION);
		alertInfomation.setTitle("");
		alertInfomation.setHeaderText("알림");
		alertInfomation.setContentText(string);
		alertInfomation.showAndWait();	 // Alert창 보여주기 (창을 띄우고 기다려줌) -> 꼭 해줘야야 뜸.
	}
}
