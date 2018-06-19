package pcws.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pcws.service.friendService.FriendServiceInf;
import pcws.service.memberService.MemberServiceInf;
import pcws.vo.FriendVO;
import pcws.vo.YouAndMeVO;

public class Friend_Controller {
	
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
    private Button alarm;

    @FXML
    private Button mes;

    @FXML
    private Button project;

    @FXML
    private Button member;

    @FXML
    private Button mypage;


    @FXML
    private ComboBox<String> category;

    @FXML
    private Button search;

    @FXML
    private ComboBox<String> loginAndLogout;

    @FXML
    private Button myImage;

    @FXML
    private TableView<YouAndMeVO> friTableView;

    @FXML
    private TableColumn<YouAndMeVO, String> num;

    @FXML
    private TableColumn<YouAndMeVO, String> firId;

    @FXML
    private TableColumn<YouAndMeVO, String> friName;

    @FXML
    private TableColumn<YouAndMeVO, String> friTel;

    @FXML
    private TableColumn<YouAndMeVO, String> friEmail;

    @FXML
    private Button friSearch;

    @FXML
    private Button friDelete;
    
    private YouAndMeVO yamVo = null;
	private Friend_Search_Controller friSearchCtr;

    @FXML
    void alarmClick(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/WorkAlarm.fxml"));
    		Parent change = loader.load();
    		
    		Scene scene = new Scene(change);
    		primaryStage.setScene(scene);
    		
    		WorkAlarm_Controller workAlarm_controller = loader.getController();
    		workAlarm_controller.setPrimaryStage(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void deleteMemberAction(ActionEvent event) {
    	yamVo = friTableView.getSelectionModel().getSelectedItem();
    }

    private Friend_Del_Controller controller;
    @FXML
    void friendDelete(MouseEvent event) throws IOException {
    	if(yamVo == null) {
    		showAlertInfo("삭제할 멤버가 선택되지 않았습니다.");
    		return;
    	}
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Friend_Del.fxml"));
		Parent change = loader.load();

		controller = loader.getController();
		controller.setFc(this);
		
		Stage friDelStage = new Stage(StageStyle.UTILITY);
		friDelStage.initModality(Modality.WINDOW_MODAL);
		friDelStage.initOwner(primaryStage);
    	
    	Scene scene = new Scene(change);
    	friDelStage.setScene(scene);
    	friDelStage.show();
    
    }

    @FXML
    void friendSearch(MouseEvent event) {

    }

    @FXML
    void logClick(MouseEvent event) throws IOException {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Mypage.fxml"));
    		Parent change = loader.load();
    		
    		Scene scene = new Scene(change);
    		primaryStage.setScene(scene);
    		
    		Mypage_Controller mypage_controller = loader.getController();
    		mypage_controller.setPrimaryStage(primaryStage);
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
    		
    		Friend_Controller friend_controller = loader.getController();
    		friend_controller.setPrimaryStage(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void myImageClick(ActionEvent event) {

    }
    
    @FXML
    void mypageClick(ActionEvent event) {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Mypage.fxml"));
    		Parent change = loader.load();
    		
    		Scene scene = new Scene(change);
    		primaryStage.setScene(scene);
    		
    		Mypage_Controller mypage_controller = loader.getController();
    		mypage_controller.setPrimaryStage(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void projectClick(ActionEvent event) throws IOException {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ProjectTap.fxml"));
    		Parent change = loader.load();
    		
    		Scene scene = new Scene(change);
    		primaryStage.setScene(scene);
    		
    		ProjectTap_Controller project_controller = loader.getController();
    		project_controller.setPrimaryStage(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }

    @FXML
    void searchClick(ActionEvent event) {
    	if(category.getValue() == "친구") {
    		try {
    			Parent changeProSearch = FXMLLoader.load(getClass().getResource("../view/FriendListSearch.fxml"));
    			Scene proSearchScene = new Scene(changeProSearch);
    			primaryStage.setScene(proSearchScene);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else if(category.getValue() == "프로젝트명"){
    		try {
    			Parent changeProSearch = FXMLLoader.load(getClass().getResource("../view/ProListSearch.fxml"));
    			Scene proSearchScene = new Scene(changeProSearch);
    			primaryStage.setScene(proSearchScene);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }

    @FXML
    void searchMemberAction(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Friend_Search.fxml"));
    	Parent change = loader.load();
    	
    	friSearchCtr = loader.getController();
    	friSearchCtr.setFr(this);
    	
    	Stage friSearchStage = new Stage(StageStyle.UTILITY);
    	friSearchStage.initModality(Modality.WINDOW_MODAL);
    	friSearchStage.initOwner(primaryStage);
    	
    	Scene scene = new Scene(change);
    	friSearchStage.setScene(scene);
    	friSearchStage.setTitle("멤버 검색");
    	friSearchStage.show();
    }

    @FXML
    void mesClick(ActionEvent event) throws IOException {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Message.fxml"));
    		Parent change = loader.load();
    		
    		Scene scene = new Scene(change);
    		primaryStage.setScene(scene);
    		
    		Message_Controller message_controller = loader.getController();
    		message_controller.setPrimaryStage(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    // service 연동
    private FriendServiceInf friendService;
    private MemberServiceInf memberService;
    
    // List
    ObservableList<YouAndMeVO> friTableList = FXCollections.observableArrayList();
    
    @FXML
    public void initialize() throws RemoteException, NotBoundException {
    	
        assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'Friend.fxml'.";
        assert category != null : "fx:id=\"category\" was not injected: check your FXML file 'Friend.fxml'.";
        assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'Friend.fxml'.";
        assert loginAndLogout != null : "fx:id=\"loginAndLogout\" was not injected: check your FXML file 'Friend.fxml'.";
        assert myImage != null : "fx:id=\"myImage\" was not injected: check your FXML file 'Friend.fxml'.";
        assert alarm != null : "fx:id=\"alarm\" was not injected: check your FXML file 'Friend.fxml'.";
        assert mes != null : "fx:id=\"mes\" was not injected: check your FXML file 'Friend.fxml'.";
        assert project != null : "fx:id=\"project\" was not injected: check your FXML file 'Friend.fxml'.";
        assert member != null : "fx:id=\"member\" was not injected: check your FXML file 'Friend.fxml'.";
        assert mypage != null : "fx:id=\"mypage\" was not injected: check your FXML file 'Friend.fxml'.";
        assert friTableView != null : "fx:id=\"friTableView\" was not injected: check your FXML file 'Friend.fxml'.";
        assert num != null : "fx:id=\"num\" was not injected: check your FXML file 'Friend.fxml'.";
        assert firId != null : "fx:id=\"firId\" was not injected: check your FXML file 'Friend.fxml'.";
        assert friName != null : "fx:id=\"friName\" was not injected: check your FXML file 'Friend.fxml'.";
        assert friTel != null : "fx:id=\"friTel\" was not injected: check your FXML file 'Friend.fxml'.";
        assert friEmail != null : "fx:id=\"friEmail\" was not injected: check your FXML file 'Friend.fxml'.";
        assert friSearch != null : "fx:id=\"friSearch\" was not injected: check your FXML file 'Friend.fxml'.";
        assert friDelete != null : "fx:id=\"friDelete\" was not injected: check your FXML file 'Friend.fxml'.";
        
        
        if(MemberInfo.mvo.getMem_admin().equals("true")) {
        	friSearch.setVisible(false);
        }
        if(MemberInfo.mvo.getMem_admin().equals("true")) {
        	alarm.setVisible(false);
        }
        if(MemberInfo.mvo.getMem_admin().equals("true")) {
        	mes.setVisible(false);
        }
        if(MemberInfo.mvo.getMem_admin().equals("true")) {
        	project.setVisible(false);
        }
        if(MemberInfo.mvo.getMem_admin().equals("true")) {
        	member.setVisible(false);
        }
        if(MemberInfo.mvo.getMem_admin().equals("true")) {
        	mypage.setVisible(false);
        }


     // 등록된 서버를 찾기 위해 Registry 객체를 생성한다.
        Registry reg = null;
        try {
			reg = LocateRegistry.getRegistry("localhost",5959);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
//        Registry reg = LocateRegistry.getRegistry("192.168.1.3", 5959);
        
     // 서버에서 공유한 객체의 Alias를 이용하여 사용할 객체를 구한다.
        friendService = (FriendServiceInf) reg.lookup("fri_server");
        memberService = (MemberServiceInf) reg.lookup("mem_server");
        
     // 콤보박스 셋팅
        category.getItems().addAll("친구", "프로젝트명");
        
        
     // friTableList 컬럼 셋팅
        num.setCellValueFactory(new PropertyValueFactory<>("num"));
        firId.setCellValueFactory(new PropertyValueFactory<>("frd_id"));
        friName.setCellValueFactory(new PropertyValueFactory<>("name"));
        friTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        friEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        friTableView.setItems(friTableList);
        
        List<YouAndMeVO> allMember = friendService.getAllMember(MemberInfo.mvo.getMem_id());
        for(YouAndMeVO member : allMember) {
        	System.out.println(member.getName());
        }
        
        friTableList.addAll(friendService.getAllMember(MemberInfo.mvo.getMem_id()));	// 로그인한 아이디
        
    }
    
    public void reset() {
    	friTableList.clear();
    }
    
    public void del() throws RemoteException {
    	List<YouAndMeVO> searchFriendList = friendService.getAllMember(MemberInfo.mvo.getMem_id());
    	
    	boolean friDelFlag = false;	// 친구를 삭제할 수 있는지 여부(구분 변수)
    	for (YouAndMeVO friendVo : searchFriendList) {
			if(friendVo.getFrd_id().equals(yamVo.getFrd_id())) {
				friDelFlag = true;
				break;
			}
		}
    	if(friDelFlag) {
    		friendService.getFriend_Delete(MemberInfo.mvo.getMem_id(), yamVo.getFrd_id());
    		showAlertInfo("멤버가 삭제되었습니다.");
    	}else {
    		showAlertInfo("삭제가 불가능합니다. 다시 시도해 주세요.");
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











