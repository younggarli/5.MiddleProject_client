package pcws.main;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import pcws.service.checkListService.CheckListServiceInf;
import pcws.service.projectService.ProjectServiceInf;
import pcws.service.teamService.TeamServiceInf;
import pcws.vo.CheckListVO;
import pcws.vo.MemberVO;
import pcws.vo.NoTaPrMeVO;
import pcws.vo.TeamVO;

public class CheckListView_Controller {

	CheckListVO newCheck = new CheckListVO();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> selectMem;

    @FXML
    private TableColumn<CheckListVO, String> contentCol;

    @FXML
    private TableColumn<CheckListVO, String> chkBoxCol;

    @FXML
    private TableView<CheckListVO> listView;

    @FXML
    private TableColumn<CheckListVO, String> memberCol;

    @FXML
    private Button delBtn;

    @FXML
    private TextArea txtContent;

    @FXML
    private Label pjName;

    @FXML
    private Button addBtn;

    @FXML
    private Button saveBtn;

    @FXML
    void saveAction(ActionEvent event) throws RemoteException {
    	int selectedIndex = listView.getSelectionModel().getSelectedIndex();
    	CheckListVO chk = listView.getItems().get(selectedIndex);
    	
    	if(chk.getChk_wtr().equals("true")) {
    		chk.setChk_wtr("false");
    		CheckListService.checkList_update(chk);
    	}else {
    		chk.setChk_wtr("true");
    		CheckListService.checkList_update(chk);
    	}
    	listSet();
    	
    }

    @FXML
    void addAction(ActionEvent event) throws RemoteException {
    	if(selectMem.getValue()!=null&&(txtContent.getText()!=null||txtContent.getText().equals(""))) {
    		newCheck.setChk_task_mem(selectMem.getValue());
    		newCheck.setChk_task_cont(txtContent.getText());
    		newCheck.setChk_wtr("false");
    		newCheck.setChk_list_id(MemberInfo.pvo.getPj_id()+"N");
    		
    		CheckListService.checkList_Insert(newCheck);
    		listSet();
    		
    		txtContent.clear();
    	}else {
    		showAlertInfo("정보를 확인해주세요.");
    	}
//    	ProjectTap_Controller controller = new ProjectTap_Controller();
//    	controller.printPie();
    }

    @FXML
    void deleteAction(ActionEvent event) throws RemoteException {
    	int selectedIndex = listView.getSelectionModel().getSelectedIndex();
    	CheckListVO chk = listView.getItems().get(selectedIndex);
    	
    	CheckListService.checkList_Delete(chk);
    	
    	listSet();
    }
    
    ObservableList<CheckListVO> list = FXCollections.observableArrayList();
    ProjectServiceInf proService;
    TeamServiceInf teamService;
    CheckListServiceInf CheckListService;
    
    @FXML
    void initialize() throws RemoteException, NotBoundException {
    	assert selectMem != null : "fx:id=\"selectMem\" was not injected: check your FXML file 'CheckListView.fxml'.";
        assert contentCol != null : "fx:id=\"contentCol\" was not injected: check your FXML file 'CheckListView.fxml'.";
        assert chkBoxCol != null : "fx:id=\"chkBoxCol\" was not injected: check your FXML file 'CheckListView.fxml'.";
        assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'CheckListView.fxml'.";
        assert memberCol != null : "fx:id=\"memberCol\" was not injected: check your FXML file 'CheckListView.fxml'.";
        assert delBtn != null : "fx:id=\"delBtn\" was not injected: check your FXML file 'CheckListView.fxml'.";
        assert txtContent != null : "fx:id=\"txtContent\" was not injected: check your FXML file 'CheckListView.fxml'.";
        assert pjName != null : "fx:id=\"pjName\" was not injected: check your FXML file 'CheckListView.fxml'.";
        assert addBtn != null : "fx:id=\"addBtn\" was not injected: check your FXML file 'CheckListView.fxml'.";
        assert saveBtn != null : "fx:id=\"saveBtn\" was not injected: check your FXML file 'CheckListView.fxml'.";

        // 등록된 서버를 찾기 위해 Registry객체를 생성한다.
     	Registry reg = LocateRegistry.getRegistry("localhost", 5959);
     	
     	// 서버에서 공유한 객체의 Alias를 이용하여 사용할 객체를 구한다.
     	proService = (ProjectServiceInf)reg.lookup("pro_server");
     	teamService = (TeamServiceInf)reg.lookup("tea_server");
     	CheckListService = (CheckListServiceInf)reg.lookup("che_server");
     	List<TeamVO> team_List = teamService.getAllTeam(MemberInfo.pvo);

     	chkBoxCol.setCellValueFactory(new PropertyValueFactory<>("chk_wtr"));
     	contentCol.setCellValueFactory(new PropertyValueFactory<>("chk_task_cont"));
        memberCol.setCellValueFactory(new PropertyValueFactory<>("chk_task_mem"));
        
        listSet();

        
        for (int i = 0; i < team_List.size(); i++) {
        	selectMem.getItems().add(team_List.get(i).getMem_nm());
		}
    }
    
    public void listSet() throws RemoteException {
    	list.clear();
    	listView.setItems(list);
        List<CheckListVO> chkSetList = CheckListService.checkList_Allget(MemberInfo.pvo.getPj_id());
        if(chkSetList!= null && chkSetList.size()!=0) {
        	list.addAll(chkSetList);
        }else {
        	System.out.println("없어용");
        }
    }
    
    private void showAlertInfo(String string) {
		Alert alertInfomation = new Alert(AlertType.INFORMATION);
		alertInfomation.setTitle("");
		alertInfomation.setHeaderText("알림");
		alertInfomation.setContentText(string);
		alertInfomation.showAndWait(); // Alert창 보여주기 (창을 띄우고 기다려줌) -> 꼭 해줘야야 뜸.
	}
}
