package pcws.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pcws.service.checkListService.CheckListServiceInf;
import pcws.service.projectService.ProjectServiceInf;
import pcws.service.teamService.TeamServiceInf;
import pcws.vo.MemberVO;
import pcws.vo.MessageVO;
import pcws.vo.ProjectVO;
import pcws.vo.TeamVO;

public class ProjectTaskList_Controller {

	private ProjectVO saveProject;
	private Stage primaryStage;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	private ProjectTap_Controller pjController;
	
	public void setPjController(ProjectTap_Controller pjController) {
		this.pjController = pjController;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtEndDate;

    @FXML
    private Button memberDel;

    @FXML
    private Button updateBtn;

    @FXML
    private TableColumn<?, ?> teamCol;

    @FXML
    private TextField txtProName;

    @FXML
    private Button memberAdd;

    @FXML
    private TextField txtStartDate;

    @FXML
    private Pane colorLabel;

    @FXML
    private Label tagLabel;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableView<TeamVO> memberView;
    
    @FXML
    void addMember(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MemberAdd.fxml"));
		Parent root = loader.load();
		
		MemberAdd_Controller mem_controll = loader.getController();
		mem_controll.setPjController(pjController);
		MemberInfo.pvo = saveProject;
		
		Stage stage = new Stage(StageStyle.UTILITY);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(primaryStage);
		
		Scene scene = new Scene(root);
		stage.setTitle("addMember");
		stage.setScene(scene);
		stage.show();
		
    }
    
    @FXML
    void delMember(ActionEvent event) throws IOException {
    	int selectedIndex = memberView.getSelectionModel().getSelectedIndex();
    	TeamVO team = memberView.getItems().get(selectedIndex);
    	
    	teamService.Team_Delete(team);
    	pjController.settingList();
    	
    }

    
    @FXML
    void updateAction(ActionEvent event) {
    	try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Project_Update.fxml"));
			Parent pjupdate = loader.load();

			Project_Update_Controller pj_update = loader.getController();
			pj_update.setData(saveProject);
			pj_update.setPjController(pjController);
			
			Stage findIdStage = new Stage(StageStyle.UTILITY);
			findIdStage.initModality(Modality.WINDOW_MODAL);
			findIdStage.initOwner(primaryStage);
			
			Scene pjUpdateScene = new Scene(pjupdate);
			findIdStage.setTitle("프로젝트 수정");
			findIdStage.setScene(pjUpdateScene);
			findIdStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void deleteAction(ActionEvent event) throws Exception {
    	projectService.project_delete(saveProject);
    	pjController.settingList();
    }

    @FXML
    void taskListAction(ActionEvent event) throws IOException {
    	System.out.println("세이브 프로젝트는 "+saveProject);
    	MemberInfo.pvo = saveProject;
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CheckListView.fxml"));
		Parent chkListLoader = loader.load();
		
		Stage chklistStage = new Stage(StageStyle.UTILITY);
		chklistStage.initModality(Modality.WINDOW_MODAL);
		chklistStage.initOwner(primaryStage);
		
		Scene chklisteScene = new Scene(chkListLoader);
		chklistStage.setTitle("프로젝트 수정");
		chklistStage.setScene(chklisteScene);
		chklistStage.show();
    }
    
    private ProjectServiceInf projectService;
    private CheckListServiceInf checkService;
    private TeamServiceInf teamService;

    ObservableList<TeamVO> list = FXCollections.observableArrayList();

    
    @FXML
    void initialize() {
    	assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Project_Task_List.fxml'.";
        assert memberDel != null : "fx:id=\"memberDel\" was not injected: check your FXML file 'Project_Task_List.fxml'.";
        assert updateBtn != null : "fx:id=\"updateBtn\" was not injected: check your FXML file 'Project_Task_List.fxml'.";
        assert teamCol != null : "fx:id=\"teamCol\" was not injected: check your FXML file 'Project_Task_List.fxml'.";
        assert txtProName != null : "fx:id=\"txtProName\" was not injected: check your FXML file 'Project_Task_List.fxml'.";
        assert memberAdd != null : "fx:id=\"memberAdd\" was not injected: check your FXML file 'Project_Task_List.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Project_Task_List.fxml'.";
        assert colorLabel != null : "fx:id=\"colorLabel\" was not injected: check your FXML file 'Project_Task_List.fxml'.";
        assert tagLabel != null : "fx:id=\"tagLabel\" was not injected: check your FXML file 'Project_Task_List.fxml'.";
        assert deleteBtn != null : "fx:id=\"deleteBtn\" was not injected: check your FXML file 'Project_Task_List.fxml'.";
        assert memberView != null : "fx:id=\"memberView\" was not injected: check your FXML file 'Project_Task_List.fxml'.";

        teamCol.setCellValueFactory(new PropertyValueFactory<>("mem_nm"));
        
        try {
			// 등록된 서버를 찾기 위해 Registry객체를 생성한다.
	     	Registry reg = LocateRegistry.getRegistry("localhost", 5959);
//        	Registry reg = LocateRegistry.getRegistry("192.168.0.60", 5959);
	     	
	     	// 서버에서 공유한 객체의 Alias를 이용하여 사용할 객체를 구한다.
	     	projectService = (ProjectServiceInf)reg.lookup("pro_server");
	     	checkService = (CheckListServiceInf)reg.lookup("che_server");
	     	teamService = (TeamServiceInf)reg.lookup("tea_server");
		} catch (Exception e) {	}
        
    }
    
    public void setData(ProjectVO proVo) throws RemoteException {
    	saveProject = proVo;
    	txtProName.setText(proVo.getPj_nm());
    	txtStartDate.setText(proVo.getPj_str_date().substring(0, 10));
    	txtEndDate.setText(proVo.getPj_end_date().substring(0, 10));
    	if(!(proVo.getPj_tag().equals("")||proVo.getPj_tag()==null)) {
    		System.out.println(proVo.getPj_tag());
    		tagLabel.setText(proVo.getPj_tag());
    		System.out.println(proVo.getPj_color());
    		colorLabel.setBackground(new Background(new BackgroundFill(Color.web(proVo.getPj_color()), CornerRadii.EMPTY, Insets.EMPTY)));
    	}
    	List<TeamVO> allTeam = teamService.getAllTeam(saveProject);
    	if(allTeam != null && allTeam.size() != 0){
    		list.addAll(allTeam);
    		memberView.setItems(list);
    	}
    }
}
