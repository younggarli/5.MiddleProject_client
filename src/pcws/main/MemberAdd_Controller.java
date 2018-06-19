package pcws.main;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pcws.service.projectService.ProjectServiceInf;
import pcws.service.teamService.TeamServiceInf;
import pcws.vo.MemberVO;
import pcws.vo.TeamVO;

public class MemberAdd_Controller {
	private Stage st;
	private	static Stage primaryStage;
	
	public static void setPrimaryStage(Stage primaryStage) {
		MemberAdd_Controller.primaryStage = primaryStage;
	}
	
	private ProjectTap_Controller ProjectTap_Controller;
	
	public void setPjController(ProjectTap_Controller pjTaskList_Controller) {
		this.ProjectTap_Controller = pjTaskList_Controller;
	}
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addMemBtn;

    @FXML
    private TableColumn<?, ?> IDCol;

    @FXML
    private TextField txtInput;

    @FXML
    private TableView<MemberVO> memTable;

    @FXML
    private TableColumn<?, ?> TelCol;

    @FXML
    private TableColumn<?, ?> NameCol;
	

    @FXML
    void SearchClick(ActionEvent event) throws RemoteException {
    	memTableList.clear();
    	memTableList.addAll(proService.searchMem(txtInput.getText()));
    	memTable.setItems(memTableList);
    }

    @FXML
    void addClick(ActionEvent event) throws Exception {
    	int selectedIndex = memTable.getSelectionModel().getSelectedIndex();
    	MemberVO memAdd = memTable.getItems().get(selectedIndex);
    	
    	TeamVO newTeam = new TeamVO();
    	newTeam.setTm_pj_id(MemberInfo.pvo.getPj_id());
    	newTeam.setTm_mem_id(memAdd.getMem_id());
    	newTeam.setTm_id(MemberInfo.pvo.getPj_id()+memAdd.getMem_id());
    	teamService.Team_Create(newTeam);
    	
    	ProjectTap_Controller.settingList();
    	
    	st = (Stage) addMemBtn.getScene().getWindow();
    	st.close();
    }

    @FXML
    void allClick(ActionEvent event) throws RemoteException {
    	memTableList.clear();
    	memTableList.addAll(proService.getAllMember());
        memTable.setItems(memTableList);
    }

    ObservableList<MemberVO> memTableList = FXCollections.observableArrayList();
    ProjectServiceInf proService;
    TeamServiceInf teamService;
    
    
    @FXML
    void initialize() throws RemoteException, NotBoundException {
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'MemberAdd.fxml'.";
        assert memTable != null : "fx:id=\"memTable\" was not injected: check your FXML file 'MemberAdd.fxml'.";
        assert IDCol != null : "fx:id=\"IDCol\" was not injected: check your FXML file 'MemberAdd.fxml'.";
        assert NameCol != null : "fx:id=\"NameCol\" was not injected: check your FXML file 'MemberAdd.fxml'.";
        assert TelCol != null : "fx:id=\"TelCol\" was not injected: check your FXML file 'MemberAdd.fxml'.";

        // 등록된 서버를 찾기 위해 Registry객체를 생성한다.
     	Registry reg = LocateRegistry.getRegistry("localhost", 5959);
     	
     	// 서버에서 공유한 객체의 Alias를 이용하여 사용할 객체를 구한다.
     	proService = (ProjectServiceInf)reg.lookup("pro_server");
     	teamService = (TeamServiceInf)reg.lookup("tea_server");
     	
        IDCol.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("mem_nm"));
        TelCol.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
        
        memTableList.addAll(proService.getAllMember());
        memTable.setItems(memTableList);
    }
}
