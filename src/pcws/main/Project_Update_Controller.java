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
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pcws.service.projectService.ProjectServiceInf;
import pcws.vo.ProjectVO;

public class Project_Update_Controller {
	private ProjectVO saveProject;
	private Stage st;
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
    private ColorPicker colorPick;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField txtProName;

    @FXML
    private TextField txtStartDate;

    @FXML
    private ComboBox<String> comboTag;

    @FXML
    private Button okBtn;

    @FXML
    void okAction(ActionEvent event) throws IOException {
    	
    	saveProject.setPj_nm(txtProName.getText());
    	saveProject.setPj_str_date(txtStartDate.getText()+"");
    	saveProject.setPj_end_date(txtEndDate.getText()+"");
    	saveProject.setPj_tag(comboTag.getValue());
    	saveProject.setPj_color(colorPick.getValue()+"");
    	
    	if(saveProject.getPj_tag()==null) {
    		saveProject.setPj_tag("tag");
    	}
    	
    	projectService.project_update(saveProject);
    	MemberInfo.pvo=saveProject;
    	pjController.settingList();
    	pjController.clearCal();
    	pjController.printCal();
    	st = (Stage) okBtn.getScene().getWindow();
    	st.close();
    }

    @FXML
    void cancelAction(ActionEvent event) {
    	st = (Stage) cancelBtn.getScene().getWindow();
    	st.close();
    }

    ProjectServiceInf projectService;
    
    @FXML
    void initialize() throws Exception {
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Project_Update.fxml'.";
        assert colorPick != null : "fx:id=\"colorPick\" was not injected: check your FXML file 'Project_Update.fxml'.";
        assert cancelBtn != null : "fx:id=\"cancelBtn\" was not injected: check your FXML file 'Project_Update.fxml'.";
        assert txtProName != null : "fx:id=\"txtProName\" was not injected: check your FXML file 'Project_Update.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Project_Update.fxml'.";
        assert comboTag != null : "fx:id=\"comboTag\" was not injected: check your FXML file 'Project_Update.fxml'.";
        assert okBtn != null : "fx:id=\"okBtn\" was not injected: check your FXML file 'Project_Update.fxml'.";
        comboTag.getItems().addAll("important","normal","hard","excisiting","imminect","for a white", "repetitive",
        						"test", "multiple");
        
     // 등록된 서버를 찾기 위해 Registry객체를 생성한다.
     		Registry reg = LocateRegistry.getRegistry("localhost", 5959);
//     		Registry reg = LocateRegistry.getRegistry("192.168.0.60", 5959);
     		
     		// 서버에서 공유한 객체의 Alias를 이용하여 사용할 객체를 구한다.
     		projectService = (ProjectServiceInf) reg.lookup("pro_server");
    }
    
    public void setData(ProjectVO proVo) {
    	saveProject = proVo;
    	txtProName.setText(proVo.getPj_nm());
    	txtStartDate.setText(proVo.getPj_str_date().substring(0, 10));
    	txtEndDate.setText(proVo.getPj_end_date().substring(0, 10));
    }
}
