package pcws.main;

import java.net.URL;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import pcws.service.memberService.MemberServiceInf;
import pcws.vo.MemberVO;

public class Find_Pw_Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField findNm;

    @FXML
    private TextField findEmail;

    @FXML
    private TextField findTel;

    @FXML
    private Button okBtn;

    @FXML
    private Button cancleBtn;

    @FXML
    void cancleBtnAction(ActionEvent event) {
    	st = (Stage) cancleBtn.getScene().getWindow();
    	st.close();
    }

    @FXML
    void okBtnAction(ActionEvent event) throws RemoteException {
    	if(findNm.getText().isEmpty() || findEmail.getText().isEmpty() || findTel.getText().isEmpty() ) {
    		showAlertInfo("입력하신 정보를 다시 확인해주세요.");
    	}else {
    		MemberVO mvo = new MemberVO();
    		mvo.setMem_id(findNm.getText());
    		mvo.setMem_email(findEmail.getText());
    		mvo.setMem_tel(findTel.getText());
    		
    		
    		StringBuffer CertNum = new StringBuffer();
    		
    		String findPw;
    		if(memberService.checkPwSearch(mvo)) {
    			findPw = memberService.getPwSearchMember(mvo);
    			if(findPw != null || !findPw.equals("")) {
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
    		    	String sec=String.valueOf(CertNum);
    		    	mvo.setMem_pw(sec);
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
    		    	
    				try {
    					MimeMessage message = new MimeMessage(session);
    					message.setFrom(new InternetAddress(user));
    					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
    					
    					// 제목
    					message.setSubject("비밀번호 찾기 전송 메일입니다.");
    					
    					// 내용
    					message.setText("당신의 임시 비밀번호는 "+ sec +"입니다.");
    					
    					// 전송 완료
    					Transport.send(message);
    					st = (Stage) cancleBtn.getScene().getWindow();
    					st.close();
    					showAlertInfo("전송되었습니다.");
    					memberService.updateMemberPw(mvo);
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
    
	private Stage primaryStage;
	private Stage st;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
    private void showAlertInfo(String string) {
		Alert alertInfomation = new Alert(AlertType.INFORMATION);
		alertInfomation.setTitle("");
		alertInfomation.setHeaderText("알림");
		alertInfomation.setContentText(string);
		alertInfomation.showAndWait();	 // Alert창 보여주기 (창을 띄우고 기다려줌) -> 꼭 해줘야야 뜸.
	}
    
    @FXML
    void initialize() {
        assert findNm != null : "fx:id=\"findNm\" was not injected: check your FXML file 'Find_PW.fxml'.";
        assert findEmail != null : "fx:id=\"findEmail\" was not injected: check your FXML file 'Find_PW.fxml'.";
        assert findTel != null : "fx:id=\"findTel\" was not injected: check your FXML file 'Find_PW.fxml'.";
        assert okBtn != null : "fx:id=\"okBtn\" was not injected: check your FXML file 'Find_PW.fxml'.";
        assert cancleBtn != null : "fx:id=\"cancleBtn\" was not injected: check your FXML file 'Find_PW.fxml'.";

	        try {
	  			// 등록된 서버를 찾기 위해 Registry객체를 생성한다.
	  	     	Registry reg = LocateRegistry.getRegistry("localhost", 5959);
	  	     	
	  	     	// 서버에서 공유한 객체의 Alias를 이용하여 사용할 객체를 구한다.
	  	     	memberService = (MemberServiceInf)reg.lookup("mem_server"); 
	  		} catch (Exception e) {
  		}
        
    }
}

