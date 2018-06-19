package pcws.main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import pcws.service.friendService.FriendServiceInf;
import pcws.service.messageService.MessageServiceInf;
import pcws.vo.FriendVO;
import pcws.vo.MessageVO;
import pcws.vo.YouAndMeVO;

public class write_Controller {

	private Message_Controller controller;
	private static Stage stage;

	public void setController(Message_Controller controller) {
		this.controller = controller;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> txtName;

    @FXML
    private TextArea txtCont;

    @FXML
    private TextField txtFile;

    @FXML
    void cancelClick(ActionEvent event) {
    	stage.close();
    }


    @FXML
    void sendClick(ActionEvent event) throws RemoteException {
    	String receiver = txtName.getValue();
    	receiver = receiver.split("/")[1];
    	
    	String msg = txtCont.getText();
    	String writer = MemberInfo.mvo.getMem_id();
    	
    	HashMap<String, String> msgMap = new HashMap<>();
    	msgMap.put("receiver", receiver);
    	msgMap.put("msg", msg);
    	msgMap.put("writer", writer);
//    	msgMap.put("path", "C:\\Kimdohyoung\\A_TeachingMaterial\\5.MiddleProject\\files");
    	msgMap.put("path", "C:\\Temp");
    	
    	msgMap.put("fileName", txtFile.getText());
    	
    	if(msgService.sendMsg(msgMap)) {
    		
    		String msgID = msgService.getMsgId(msgMap);
    		System.out.println("쪽지 아이디 : " + msgID);
    		msgService.insert_notice(msgID);
    		showAlertInfo("성공적으로 보냈습니다.");
    	}else {
    		showAlertInfo("쪽지 보내기에 실패했습니다.");
    	}
    	stage.close();
    	
    	
    }

    @FXML
    void upClick(ActionEvent event) throws RemoteException {
    	FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(
				new ExtensionFilter("All Files", "*.*")
		);
		File selectedFile = fileChooser.showOpenDialog(stage);
		if(selectedFile != null) {
			txtFile.setText(selectedFile.getName());
			System.out.println(selectedFile.getPath());
//			String fileName = txtFile.getText();
//			File file = new File("C:\\Kimdohyoung\\A_TeachingMaterial\\5.MiddleProject\\files\\"+selectedFile.getName());
			File file = new File("C:\\Temp\\"+selectedFile.getName());
			try {
				FileInputStream fis = new FileInputStream(selectedFile);
				BufferedInputStream bis = new BufferedInputStream(fis);
				
				FileOutputStream fos = new FileOutputStream(file);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				int len = (int)selectedFile.length();
				byte[] data = new byte[len];
//				System.out.println("len : " + len);
				bis.read(data);
				
				MessageVO msgfile = new MessageVO();
				msgfile.setMsg_filenm(file.getName());
				msgfile.setFileData(data);
				
				msgService.setFiles(msgfile);
				
//				while((len=bis.read()) != -1) {
//					bos.write(len);
//				}
//				bos.flush();
//				
//				bis.close();
//				bos.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
//			System.out.println(selectedFile.getPath());
		}
    }

    private void showAlertInfo(String string) {
		Alert alertInfomation = new Alert(AlertType.INFORMATION);
		alertInfomation.setTitle("");
		alertInfomation.setHeaderText("알림");
		alertInfomation.setContentText(string);
		alertInfomation.showAndWait();	 // Alert창 보여주기 (창을 띄우고 기다려줌) -> 꼭 해줘야야 뜸.
	}
    
    FriendServiceInf friService;
    MessageServiceInf msgService;
    
    
    @FXML
    void initialize() throws RemoteException, NotBoundException {
        assert txtName != null : "fx:id=\"txtName\" was not injected: check your FXML file 'Friend_Message.fxml'.";
        assert txtCont != null : "fx:id=\"txtCont\" was not injected: check your FXML file 'Friend_Message.fxml'.";
        assert txtFile != null : "fx:id=\"txtFile\" was not injected: check your FXML file 'Friend_Message.fxml'.";

        // 등록된 서버를 찾기 위해 Registry객체를 생성한다.
     	Registry reg = LocateRegistry.getRegistry("localhost", 5959);
//        Registry reg = LocateRegistry.getRegistry("192.168.0.60", 5959);
             	
        // 서버에서 공유한 객체의 Alias를 이용하여 사용할 객체를 구한다.
     	friService = (FriendServiceInf)reg.lookup("fri_server");
     	msgService = (MessageServiceInf)reg.lookup("mes_server");
     	
     	// 친구 목록 가져오기
     	List<YouAndMeVO> list = friService.getAllMember(MemberInfo.mvo.getMem_id());
     	ObservableList<String> friList = null;
     	for(int i=0; i<list.size(); i++) {
     		friList = FXCollections.observableArrayList(list.get(i).getName()+ "/" + list.get(i).getFrd_id());
     		txtName.getItems().addAll(friList);
     	}
     	
    }

}
