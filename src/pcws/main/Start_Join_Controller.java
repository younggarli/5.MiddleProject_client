package pcws.main;

import java.io.IOException;
import java.net.URL;
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

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pcws.service.memberService.MemberServiceInf;
import pcws.vo.MemberVO;

public class Start_Join_Controller {
	StringBuffer CertNum = new StringBuffer();
	String idchk = null;
	boolean idchk2 = false;
	
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
    private TextField id;

    @FXML
    private Button idOk;

    @FXML
    private PasswordField pw;

    @FXML
    private PasswordField pwOk;

    @FXML
    private TextField name;

    @FXML
    private DatePicker birth;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private TextField email;

    @FXML
    private Button emailCerti;

    @FXML
    private TextField emailOk;

    @FXML
    private Button emailOkCerti;

    @FXML
    private TextField tel;

    @FXML
    private Button join;

    @FXML
    private Button cancel;

    @FXML
    void cancelButton(MouseEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Start.fxml"));
		Parent changeJoin = loader.load();
//		Parent changeAlert = FXMLLoader.load(getClass().getResource("../view/ProListSearch.fxml"));
		
		Scene joinScene = new Scene(changeJoin);
		primaryStage.setScene(joinScene);
		
		Start_Controller start = loader.getController();
		start.setPrimaryStage(primaryStage);
    }

    @FXML
    void emailCertiButton(MouseEvent event) {
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

		String to = email.getText();

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
			email.setEditable(false);	// text 비활성화
			emailCerti.setDisable(true);	// 버튼 비활성화
		} catch (MessagingException e) {
			e.printStackTrace();
			showAlertInfo("전송 실패 했습니다.");
		}
		email.setEditable(true);
	}


    @FXML
    void emailOkCertiButton(MouseEvent event) {
    	// 이메일 인증 코드 확인
    	if(emailOk.getText().equals(CertNum.toString())) {
    		showAlertInfo("인증확인되었습니다.");
    		emailOk.setEditable(false);	//  text 비활성화
    		emailOkCerti.setDisable(true);	// 버튼 비활성화
    	}else {
    		showAlertInfo("인증 코드가 올바르지 않습니다.");
    		emailOk.requestFocus();
    	}
    }

    @FXML
    void idOkButton(MouseEvent event) throws RemoteException {
    	// id 중복확인
    	if(id.getText().isEmpty()) {	// 사용자가 입력한 값이 없으면
    		showAlertInfo("id를 확인해주세요.");
    		return;
    	}
    	
    	if(memberService.checkMember(id.getText())) {
    		showAlertInfo("id가 중복됩니다. 다시 입력해주세요!");
    		id.requestFocus();
    	}else {
    		idchk2 = true;
    		idchk = id.getText();
    		idOk.setDisable(true); 	// 비활성화
    		showAlertInfo("사용 가능한 id입니다.");
    	}
    }

    @FXML
    void joinButton(MouseEvent event) throws IOException {
    	int stack = 0;
    	MemberVO mvo = new MemberVO();
    	
    	if(idchk2==true) {
    		mvo.setMem_id(id.getText());
    		stack++;
    	}
    	
    	if(pw.getText().equals(pwOk.getText())) {
    		mvo.setMem_pw(pw.getText());
    		stack++;
    	}
    	
    	if(!name.getText().isEmpty()) {
    		mvo.setMem_nm(name.getText());
    		stack++;
    	}
    	
    	if(gender.getValue()==null) {
    		mvo.setMem_gen("남자");
    		stack++;
    	}else {
    		mvo.setMem_gen(gender.getValue());
    		stack++;
    	}
    	
    	if(birth.getValue()!=null) {
    		mvo.setMem_birth(birth.getValue()+"");
    		stack++;
    	}
    	
    	if(email.getText()!=null) {
    		mvo.setMem_email(email.getText());
    		stack++;
    	}
    	
    	if(tel.getText()!=null) {
    		mvo.setMem_tel(tel.getText());
    		stack++;
    	}
    	
    	
    	if(stack==7) {
    		mvo.setMem_admin("false");
    		mvo.setMem_wdw("false");
    		memberService.insertMember(mvo);
    		
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Start.fxml"));
    		Parent changeJoin = loader.load();
//    		Parent changeAlert = FXMLLoader.load(getClass().getResource("../view/ProListSearch.fxml"));
    		
    		Scene joinScene = new Scene(changeJoin);
    		primaryStage.setScene(joinScene);
    		
    		Start_Controller start = loader.getController();
    		start.setPrimaryStage(primaryStage);
    		showAlertInfo("가입이 완료 되었습니다!!");
    	}else {
    		showAlertInfo("입력하신 정보를 다시 확인해주세요.");
    	}
    }
    
    @FXML
    void resetButton(MouseEvent event) {
    	idchk = null;
    	CertNum.setLength(0); // 초기화
    	idOk.setDisable(false);	// 버튼 활성화
    	join.setDisable(false); // 버튼 활성화
    	cancel.setDisable(false); // 버튼 활성화
    	emailCerti.setDisable(false); // 버튼 활성화
    	email.setEditable(true);	// Text 활성화
    	emailOk.setEditable(true);	// Text 활성화
    	
    	id.clear();
    	pw.clear();
    	pwOk.clear();
    	name.clear();
    	birth.getEditor().clear();
    	gender.getItems().clear();
    	email.clear();
    	emailOk.clear();
    	tel.clear();
    	
    	gender.getItems().addAll("남자", "여자");
    	
    	id.requestFocus();
    	
    }
    // service 연동
    private MemberServiceInf memberService;
    
    
    @FXML
    void initialize() throws RemoteException, NotBoundException {
        assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'Start_Join.fxml'.";
        assert id != null : "fx:id=\"id\" was not injected: check your FXML file 'Start_Join.fxml'.";
        assert idOk != null : "fx:id=\"idOk\" was not injected: check your FXML file 'Start_Join.fxml'.";
        assert pw != null : "fx:id=\"pw\" was not injected: check your FXML file 'Start_Join.fxml'.";
        assert pwOk != null : "fx:id=\"pwOk\" was not injected: check your FXML file 'Start_Join.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'Start_Join.fxml'.";
        assert birth != null : "fx:id=\"birth\" was not injected: check your FXML file 'Start_Join.fxml'.";
        assert gender != null : "fx:id=\"gender\" was not injected: check your FXML file 'Start_Join.fxml'.";
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'Start_Join.fxml'.";
        assert emailCerti != null : "fx:id=\"emailCerti\" was not injected: check your FXML file 'Start_Join.fxml'.";
        assert emailOk != null : "fx:id=\"emailOk\" was not injected: check your FXML file 'Start_Join.fxml'.";
        assert emailOkCerti != null : "fx:id=\"emailOkCerti\" was not injected: check your FXML file 'Start_Join.fxml'.";
        assert tel != null : "fx:id=\"tel\" was not injected: check your FXML file 'Start_Join.fxml'.";
        assert join != null : "fx:id=\"join\" was not injected: check your FXML file 'Start_Join.fxml'.";
        assert cancel != null : "fx:id=\"cancel\" was not injected: check your FXML file 'Start_Join.fxml'.";

    // 등록된 서버를 찾기 위해 Registry 객체를 생성한다.
        Registry reg = null;
        try {
			reg = LocateRegistry.getRegistry("localhost",5959);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
        
    // 서버에서 공유한 객체의 Alias를 이용하여 사용할 객체를 구한다.
        memberService = (MemberServiceInf) reg.lookup("mem_server");
        
        gender.getItems().addAll("남자", "여자");

    }
    

    private void showAlertInfo(String string) {
		Alert alertInfomation = new Alert(AlertType.INFORMATION);
		alertInfomation.setTitle("");
		alertInfomation.setHeaderText("알림");
		alertInfomation.setContentText(string);
		alertInfomation.showAndWait();	 // Alert창 보여주기 (창을 띄우고 기다려줌) -> 꼭 해줘야야 뜸.
	}

}
