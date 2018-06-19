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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pcws.service.memberService.MemberServiceInf;
import pcws.service.projectService.ProjectServiceInf;
import pcws.vo.ProjectVO;

public class ProListSearch_Controller {

	MemberServiceInf memService;

	private Stage primaryStage;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
//	private String txt;
//	
//	public void setTxt(String txt) {
//		this.txt = txt;
//	}

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
    private TableView<ProjectVO> proTable;

    @FXML
    private TableColumn<?, ?> proTableNO;

    @FXML
    private TableColumn<?, ?> proTableNM;

    @FXML
    private TableColumn<?, ?> proTablePL;

    @FXML
    private TableColumn<?, ?> proTableSD;

    @FXML
    private TableColumn<?, ?> proTableED;
    
    @FXML
    void logOutClick(ActionEvent event) throws IOException {
    	if(comboLog.getValue().equals("LogOut")) {
    		MemberInfo.mvo = null;
    		ClientMain main = new ClientMain();
    		main.start(primaryStage);
    	}
    }
    
    @FXML
    void searchClick(ActionEvent event) throws RemoteException {
    	proTableList.clear();
    	if(comboCate.getValue().equals("Project")) {
    		proTableList.addAll(proservice.selectPro(txtSearch.getText()));
    		proTable.setItems(proTableList);
    	}
    }

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

    // 쪽지함
    @FXML
    void taskClick(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Message.fxml"));
    		Parent change = loader.load();
    		
    		Scene scene = new Scene(change);
    		primaryStage.setScene(scene);
    		
    		Message_Controller controller = loader.getController();
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
    
    ProjectServiceInf proservice;
    ObservableList<ProjectVO> proTableList = FXCollections.observableArrayList();
    
    @FXML
    void initialize() throws RemoteException, NotBoundException {
    	 assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'ProListSearch.fxml'.";
         assert comboCate != null : "fx:id=\"comboCate\" was not injected: check your FXML file 'ProListSearch.fxml'.";
         assert txtSearch != null : "fx:id=\"txtSearch\" was not injected: check your FXML file 'ProListSearch.fxml'.";
         assert comboLog != null : "fx:id=\"comboLog\" was not injected: check your FXML file 'ProListSearch.fxml'.";
         assert proTable != null : "fx:id=\"proTable\" was not injected: check your FXML file 'ProListSearch.fxml'.";
         assert proTableNO != null : "fx:id=\"proTableNO\" was not injected: check your FXML file 'ProListSearch.fxml'.";
         assert proTableNM != null : "fx:id=\"proTableNM\" was not injected: check your FXML file 'ProListSearch.fxml'.";
         assert proTablePL != null : "fx:id=\"proTablePL\" was not injected: check your FXML file 'ProListSearch.fxml'.";
         assert proTableSD != null : "fx:id=\"proTableSD\" was not injected: check your FXML file 'ProListSearch.fxml'.";
         assert proTableED != null : "fx:id=\"proTableED\" was not injected: check your FXML file 'ProListSearch.fxml'.";
        
        System.out.println(MemberInfo.txtSearch + "<<<<<<<<<<<<<<<<<<<<");
        
        comboCate.getItems().addAll("Member","Project");
        comboLog.getItems().addAll("LogIn", "LogOut");
    	
        // 등록된 서버를 찾기 위해 Registry객체를 생성한다.
     	Registry reg = LocateRegistry.getRegistry("localhost", 5959);
     		
     	// 서버에서 공유한 객체의 Alias를 이용하여 사용할 객체를 구한다.
     	proservice = (ProjectServiceInf) reg.lookup("pro_server");

     	proTableNO.setCellValueFactory(new PropertyValueFactory<>("num"));
     	proTableNM.setCellValueFactory(new PropertyValueFactory<>("pj_nm"));
     	proTablePL.setCellValueFactory(new PropertyValueFactory<>("pj_id".split("M")[0]));
     	proTableSD.setCellValueFactory(new PropertyValueFactory<>("pj_str_date"));
     	proTableED.setCellValueFactory(new PropertyValueFactory<>("pj_end_date"));
     	
     	proTableList.addAll(proservice.selectPro(MemberInfo.txtSearch));
		proTable.setItems(proTableList);
    }
}
