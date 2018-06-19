package pcws.main;

import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pcws.service.memberService.MemberServiceInf;
import pcws.vo.MemberVO;

public class Find_Id_Controller {

	private Stage primaryStage;
	private Stage st;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField name;

    @FXML
    private DatePicker birth;

    @FXML
    private TextField email;

    @FXML
    private Button confirm;

    @FXML
    private Button cancel;

    @FXML
    void cancelAction(ActionEvent event) {
    	st = (Stage) cancel.getScene().getWindow();
    	st.close();
    }

    @FXML
    void confirmAction(ActionEvent event) throws RemoteException {
    	if(name.getText().isEmpty() || birth.getValue() == null || email.getText().isEmpty()) {
    		showAlertInfo("입력하신 정보를 다시 확인해주세요.");
    	}else {
    		MemberVO mvo = new MemberVO();
    		mvo.setMem_nm(name.getText());
    		mvo.setMem_birth(birth.getValue()+"");
    		mvo.setMem_email(email.getText());
    		
    		String searchId;
    		if(memberService.checkIdSearch(mvo)) {
    			searchId = memberService.getIdSearchMember(mvo);
    			if(searchId != null || !(searchId.equals(""))) {
    				String host = "smtp.naver.com";
    				final String user = "ujisun93";
    				final String password = "GKDKFRqNDLD1";
    				
    				String to = mvo.getMem_email();
    				
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
    					message.setSubject("아이디 찾기 전송 메일입니다.");
    					
    					// 내용
    					message.setText("당신의 아이디는 "+ searchId +"입니다.");
    					
    					// 전송 완료
    					Transport.send(message);
    					st = (Stage) cancel.getScene().getWindow();
    					st.close();
    					showAlertInfo("전송되었습니다.");
    				} catch (MessagingException e) {
    					e.printStackTrace();
    					showAlertInfo("전송 실패 했습니다.");
    				}
    			}else {
    				showAlertInfo("입력한 정보를 다시 확인해주세요.");
    			}
    		}else {
    			showAlertInfo("입력하신 정보가 일치하지 않습니다.");
    		}
    	}
    }

    private MemberServiceInf memberService;
    
    @FXML
    void initialize() {
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'Find_ID.fxml'.";
        assert birth != null : "fx:id=\"birth\" was not injected: check your FXML file 'Find_ID.fxml'.";
        assert email != null : "fx:id=\"email\" was not injected: check your FXML file 'Find_ID.fxml'.";
        assert confirm != null : "fx:id=\"confirm\" was not injected: check your FXML file 'Find_ID.fxml'.";
        assert cancel != null : "fx:id=\"cancel\" was not injected: check your FXML file 'Find_ID.fxml'.";
        
        try {
			// 등록된 서버를 찾기 위해 Registry객체를 생성한다.
	     	Registry reg = LocateRegistry.getRegistry("localhost", 5959);
	     	
	     	// 서버에서 공유한 객체의 Alias를 이용하여 사용할 객체를 구한다.
	     	memberService = (MemberServiceInf)reg.lookup("mem_server");
		} catch (Exception e) {	}
        
    }
    
    private void showAlertInfo(String string) {
		Alert alertInfomation = new Alert(AlertType.INFORMATION);
		alertInfomation.setTitle("");
		alertInfomation.setHeaderText("알림");
		alertInfomation.setContentText(string);
		alertInfomation.showAndWait();	 // Alert창 보여주기 (창을 띄우고 기다려줌) -> 꼭 해줘야야 뜸.
	}
    
}
