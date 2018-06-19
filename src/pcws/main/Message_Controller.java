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
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import pcws.service.messageService.MessageServiceInf;
import pcws.vo.MessageVO;

public class Message_Controller {

	private static Stage primaryStage;
	
	public static void setPrimaryStage(Stage primaryStage) {
		Message_Controller.primaryStage = primaryStage;
	}

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView log;

    @FXML
    private ComboBox<String> comboCate;

    @FXML
    private TextField txtSearch;

    @FXML
    private ComboBox<String> comboLog;

    @FXML
    private TableView<MessageVO> msgTable;

    @FXML
    private TableColumn<?, ?> noCol;

    @FXML
    private TableColumn<?, ?> writeCol;

    @FXML
    private TableColumn<?, ?> contCol;

    @FXML
    private TableColumn<?, ?> fileCol;

    @FXML
    private TableColumn<?, ?> dateCol;

    @FXML
    void alertClick(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/WorkAlarm.fxml"));
    		Parent change = loader.load();
    		
    		Scene scene = new Scene(change);
    		primaryStage.setScene(scene);
    		
    		WorkAlarm_Controller controller = loader.getController();
    		controller.setPrimaryStage(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void memberClick(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Friend.fxml"));
    		Parent change = loader.load();
    		
    		Scene scene = new Scene(change);
    		primaryStage.setScene(scene);
    		
    		Friend_Controller controller = loader.getController();
    		controller.setPrimaryStage(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void mypageClick(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Mypage.fxml"));
    		Parent change = loader.load();
    		
    		Scene scene = new Scene(change);
    		primaryStage.setScene(scene);
    		
    		Mypage_Controller controller = loader.getController();
    		controller.setPrimaryStage(primaryStage);
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
    		Parent change = loader.load();
    		
    		Scene scene = new Scene(change);
    		primaryStage.setScene(scene);
    		
    		ProjectTap_Controller controller = loader.getController();
    		controller.setPrimaryStage(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void searchClick(MouseEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ProListSearch.fxml"));
    		Parent change = loader.load();
    		
    		Scene scene = new Scene(change);
    		primaryStage.setScene(scene);
    		
    		ProListSearch_Controller controller = loader.getController();
    		controller.setPrimaryStage(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    // 쪽지함
    @FXML
    void taskClick(ActionEvent event) {
    }
    
    @FXML
    void logout(ActionEvent event) throws IOException {
    	if(comboLog.getValue().equals("LogOut")) {
    		MemberInfo.mvo = null;
    		ClientMain main = new ClientMain();
    		main.start(primaryStage);
    	}

    }
    
    @FXML
    void logOutClick(MouseEvent event) {
    }

//    Stage checkStage = new Stage();
//    Button btnOk = new Button("확 인");
    @FXML
    void msgTableClick(MouseEvent event) {
    	BorderPane root = new BorderPane();
		root.setPrefSize(400, 300);
		
		TextArea txtArea = new TextArea();
		
		BorderPane bottom = new BorderPane();
		TextField txtField = new TextField();
		Button btnOk = new Button("확 인");
		
		bottom.setCenter(txtField);
		bottom.setRight(btnOk);
		
		root.setCenter(txtArea);
		root.setBottom(bottom);
		
		Scene scene = new Scene(root);
		
		Stage checkStage = new Stage();
		checkStage.initModality(Modality.WINDOW_MODAL);
		checkStage.initOwner(primaryStage);
		
		MessageVO msgvo = msgTable.getSelectionModel().getSelectedItem();
		txtArea.setText(msgvo.getMsg_cont());
		txtField.setText(msgvo.getMsg_filenm());
		
		checkStage.setTitle("메세지 확인 창");
		checkStage.setScene(scene);
		checkStage.show();
		
		btnOk.setOnAction(e->{
			try {
				MessageVO msgfile = new MessageVO();
				msgfile.setMsg_filenm(msgService.getFile(msgvo.getMsg_filenm()).getMsg_filenm());
				msgfile.setFileData(msgService.getFile(msgvo.getMsg_filenm()).getFileData());
				File file = new File("C:\\Temp\\" + msgvo.getMsg_filenm());
				FileOutputStream fos = new FileOutputStream(file);
				int len = (int)msgfile.getFileData().length;
				byte[] data = new byte[len];
				data = msgfile.getFileData();
				fos.write(data);
				fos.close();
				showAlertInfo("파일이 저장되었습니다.");
//				
//				MessageVO msgfile = new MessageVO();
//				msgfile.setMsg_filenm(file.getName());
//				msgfile.setFileData(data);
//				
//				msgService.setFiles(msgfile);
				
//				fis = new FileInputStream(msgTable.getSelectionModel().getSelectedItem().getMsg_filepath() + "\\" +
//						msgTable.getSelectionModel().getSelectedItem().getMsg_filenm());
//				BufferedInputStream bis = new BufferedInputStream(fis);
//				FileOutputStream fos = new FileOutputStream("C:\\Temp\\" + msgvo.getMsg_filenm());
//				BufferedOutputStream bos = new BufferedOutputStream(fos);
//				int c;
//				while((c=bis.read()) != -1) {
//					bos.write(c);
//				}
//				bos.flush();
//				
//				bis.close();
//				bos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			checkStage.close();
		});
    }
    
    @FXML
    void writeClick(ActionEvent event) {
    	try {
    		Stage stage = new Stage();
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(primaryStage);
			
//    		Parent changeMake = FXMLLoader.load(getClass().getResource("../view/Project_Make.fxml"));
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Friend_Message.fxml"));
    		Parent change = loader.load();
    		
    		write_Controller controller = loader.getController();
    		controller.setController(this);
    		
			Scene scene = new Scene(change);
			stage.setTitle("write");
			stage.setScene(scene);
			controller.setStage(stage);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    ObservableList<MessageVO> msgList = FXCollections.observableArrayList();
    MessageServiceInf msgService;
    
    @FXML
    void initialize() throws RemoteException, NotBoundException {
    	
    	comboCate.getItems().addAll("회원명", "프로젝트명");
    	comboLog.getItems().addAll("LogIn", "LogOut");
    	
        assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'Message.fxml'.";
        assert comboCate != null : "fx:id=\"comboCate\" was not injected: check your FXML file 'Message.fxml'.";
        assert txtSearch != null : "fx:id=\"txtSearch\" was not injected: check your FXML file 'Message.fxml'.";
        assert comboLog != null : "fx:id=\"comboLog\" was not injected: check your FXML file 'Message.fxml'.";
        assert msgTable != null : "fx:id=\"msgTable\" was not injected: check your FXML file 'Message.fxml'.";
        assert noCol != null : "fx:id=\"noCol\" was not injected: check your FXML file 'Message.fxml'.";
        assert writeCol != null : "fx:id=\"writeCol\" was not injected: check your FXML file 'Message.fxml'.";
        assert contCol != null : "fx:id=\"contCol\" was not injected: check your FXML file 'Message.fxml'.";
        assert fileCol != null : "fx:id=\"fileCol\" was not injected: check your FXML file 'Message.fxml'.";
        assert dateCol != null : "fx:id=\"dateCol\" was not injected: check your FXML file 'Message.fxml'.";
        
		// 등록된 서버를 찾기 위해 Registry객체를 생성한다.
     	Registry reg = LocateRegistry.getRegistry("localhost", 5959);
//        Registry reg = LocateRegistry.getRegistry("192.168.0.60", 5959);
        
        // 서버에서 공유한 객체의 Alias를 이용하여 사용할 객체를 구한다.
     	msgService = (MessageServiceInf)reg.lookup("mes_server");
     	
        noCol.setCellValueFactory(new PropertyValueFactory<>("num"));
        writeCol.setCellValueFactory(new PropertyValueFactory<>("msg_wt"));
        contCol.setCellValueFactory(new PropertyValueFactory<>("msg_cont"));
        fileCol.setCellValueFactory(new PropertyValueFactory<>("msg_filenm"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("msg_date"));
        
        msgList.addAll(msgService.getAllmsg(MemberInfo.mvo.getMem_id()));
        msgTable.setItems(msgList);

    }
    
    private void showAlertInfo(String string) {
		Alert alertInfomation = new Alert(AlertType.INFORMATION);
		alertInfomation.setTitle("");
		alertInfomation.setHeaderText("알림");
		alertInfomation.setContentText(string);
		alertInfomation.showAndWait(); // Alert창 보여주기 (창을 띄우고 기다려줌) -> 꼭 해줘야야 뜸.
    }
}
