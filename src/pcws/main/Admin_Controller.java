package pcws.main;

import java.net.URL;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pcws.service.friendService.FriendServiceInf;
import pcws.service.memberService.MemberServiceInf;

public class Admin_Controller {
	
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
    private ImageView log;

    @FXML
    private ComboBox<?> category;

    @FXML
    private Button search;

    @FXML
    private ComboBox<?> loginAndLogout;

    @FXML
    private Button myImage;

    @FXML
    private TableView<?> friTableView;

    @FXML
    private TableColumn<?, ?> num;

    @FXML
    private TableColumn<?, ?> firId;

    @FXML
    private TableColumn<?, ?> friName;

    @FXML
    private TableColumn<?, ?> friTel;

    @FXML
    private TableColumn<?, ?> friEmail;

    @FXML
    private Button memberSearch;

    @FXML
    void friendSearch(MouseEvent event) {

    }

    @FXML
    void logClick(MouseEvent event) {

    }

    @FXML
    void memberClick(ActionEvent event) {

    }

    @FXML
    void myImageClick(ActionEvent event) {

    }

    @FXML
    void searchClick(ActionEvent event) {
    	
    }

    @FXML
    void searchMemAction(ActionEvent event) {

    }

// service 연동
    private FriendServiceInf friendService;
    private MemberServiceInf memberService;
    
    @FXML
    void initialize() throws AccessException, RemoteException, NotBoundException {
        assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'Admin.fxml'.";
        assert category != null : "fx:id=\"category\" was not injected: check your FXML file 'Admin.fxml'.";
        assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'Admin.fxml'.";
        assert loginAndLogout != null : "fx:id=\"loginAndLogout\" was not injected: check your FXML file 'Admin.fxml'.";
        assert myImage != null : "fx:id=\"myImage\" was not injected: check your FXML file 'Admin.fxml'.";
        assert friTableView != null : "fx:id=\"friTableView\" was not injected: check your FXML file 'Admin.fxml'.";
        assert num != null : "fx:id=\"num\" was not injected: check your FXML file 'Admin.fxml'.";
        assert firId != null : "fx:id=\"firId\" was not injected: check your FXML file 'Admin.fxml'.";
        assert friName != null : "fx:id=\"friName\" was not injected: check your FXML file 'Admin.fxml'.";
        assert friTel != null : "fx:id=\"friTel\" was not injected: check your FXML file 'Admin.fxml'.";
        assert friEmail != null : "fx:id=\"friEmail\" was not injected: check your FXML file 'Admin.fxml'.";
        assert memberSearch != null : "fx:id=\"memberSearch\" was not injected: check your FXML file 'Admin.fxml'.";

// 등록된 서버를 찾기 위해 Registry 객체를 생성한다.
        Registry reg = null;
        try {
        	reg = LocateRegistry.getRegistry(5959);
        } catch (RemoteException e) {
        	e.printStackTrace();
        }
        
// 서버에서 공유한 객체의 Alias를 이용하여 사용할 객체를 구한다.
        friendService = (FriendServiceInf) reg.lookup("fri_server");
        memberService = (MemberServiceInf) reg.lookup("mem_server");
    }
}



