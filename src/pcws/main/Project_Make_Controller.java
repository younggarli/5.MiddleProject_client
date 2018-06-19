package pcws.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import pcws.service.noticeService.NoticeServiceInf;
import pcws.service.projectService.ProjectServiceInf;
import pcws.service.teamService.TeamServiceInf;
import pcws.vo.ProjectVO;
import pcws.vo.TeamVO;

public class Project_Make_Controller {
	
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
    private TextField txtProName;

    @FXML
    private DatePicker startDay;

    @FXML
    private DatePicker lastDay;

    @FXML
    private Button btnCreate;

    @FXML
    private Button btnCancel;

    @FXML
    void cancelClick(ActionEvent event) {
    	Stage st = (Stage) btnCancel.getScene().getWindow();
    	st.close();
    }

    @FXML
    void createClick(ActionEvent event) throws IOException {
    	ProjectVO pvo = new ProjectVO();
    	pvo.setPj_nm(txtProName.getText());
//    	pvo.setPj_id("memIDN1");
    	pvo.setPj_str_date(startDay.getValue()+"");
    	pvo.setPj_end_date(lastDay.getValue()+"");
    	pvo.setPj_color("0xffffffff");
    	pvo.setPj_tag("없음");
    	if(pvo.getPj_nm().equals(null)||pvo.getPj_str_date().equals("")||pvo.getPj_end_date().equals("")) {
			showAlertInfo("입력한 정보를 다시 확인해주세요.");
    	}else {
    		String userID = MemberInfo.mvo.getMem_id();
    		
    		MemberInfo.pvo = proservice.project_Create(userID, pvo);
    		ProjectVO newPvo = proservice.getProject(MemberInfo.mvo);
    		proservice.insert_notice(MemberInfo.pvo.getPj_id());
    		TeamVO newTeam = new TeamVO();
        	newTeam.setTm_pj_id(newPvo.getPj_id());
        	newTeam.setTm_mem_id(MemberInfo.mvo.getMem_id());
        	newTeam.setTm_id(newPvo.getPj_id()+MemberInfo.mvo.getMem_id());
        	teamService.Team_Create(newTeam);
        	
    		pjController.settingList();
    		
    		Stage st = (Stage) btnCreate.getScene().getWindow();
    		st.close();
    	}
    }                                               

    private ProjectServiceInf proservice;
    private TeamServiceInf teamService;
    
    @FXML
    void initialize() throws RemoteException, NotBoundException{
    	
        assert txtProName != null : "fx:id=\"txtProName\" was not injected: check your FXML file 'Project_Make.fxml'.";
        assert startDay != null : "fx:id=\"startDay\" was not injected: check your FXML file 'Project_Make.fxml'.";
        assert lastDay != null : "fx:id=\"lastDay\" was not injected: check your FXML file 'Project_Make.fxml'.";
        assert btnCreate != null : "fx:id=\"btnCreate\" was not injected: check your FXML file 'Project_Make.fxml'.";
        assert btnCancel != null : "fx:id=\"btnCancel\" was not injected: check your FXML file 'Project_Make.fxml'.";

        // 등록된 서버를 찾기 위해 Registry객체를 생성한다.
		Registry reg = LocateRegistry.getRegistry("localhost", 5959);
//		Registry reg = LocateRegistry.getRegistry("192.168.0.60", 5959);
		
		// 서버에서 공유한 객체의 Alias를 이용하여 사용할 객체를 구한다.
		proservice = (ProjectServiceInf) reg.lookup("pro_server");
		teamService = (TeamServiceInf) reg.lookup("tea_server");
    }
    
    private void showAlertInfo(String string) {
		Alert alertInfomation = new Alert(AlertType.INFORMATION);
		alertInfomation.setTitle("");
		alertInfomation.setHeaderText("알림");
		alertInfomation.setContentText(string);
		alertInfomation.showAndWait();	 // Alert창 보여주기 (창을 띄우고 기다려줌) -> 꼭 해줘야야 뜸.
	}
    
}

