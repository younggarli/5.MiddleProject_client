package pcws.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Shadow;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import pcws.service.messageService.MessageServiceInf;
import pcws.service.noticeService.NoticeServiceInf;
import pcws.service.projectService.ProjectServiceInf;
import pcws.vo.NoTaPrMeVO;

public class WorkAlarm_Controller {

	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@FXML
	private TableColumn<NoTaPrMeVO, CheckBox> procheckCol;

	@FXML
	private TableColumn<NoTaPrMeVO, CheckBox> mescheckCol;

	@FXML
	private TableView<NoTaPrMeVO> mesTableView;

	@FXML
	private TableView<NoTaPrMeVO> proTableView;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView log;

	@FXML
	private Button proRefresh;

	@FXML
	private TableColumn<NoTaPrMeVO, String> mesNotificationDate;

	@FXML
	private TableColumn<NoTaPrMeVO, String> proNo;

	@FXML
	private TextField searchText;

	@FXML
	private Button search;

	@FXML
	private TableColumn<NoTaPrMeVO, String> mesNo;

	@FXML
	private Button mesRefresh;

	@FXML
	private ComboBox<String> loginAndLogout;

	@FXML
	private Tab projectTap;

	@FXML
	private Tab messageTap;

	@FXML
	private TableColumn<NoTaPrMeVO, String> proStartDate;

	@FXML
	private TableColumn<NoTaPrMeVO, String> mesCon;

	@FXML
	private TableColumn<NoTaPrMeVO, String> proNotificationDate;

	@FXML
	private Button myImage;

	@FXML
	private TableColumn<NoTaPrMeVO, String> proDeadLine;

	@FXML
	private TableColumn<NoTaPrMeVO, String> mesWriter;

	@FXML
	private TableColumn<NoTaPrMeVO, String> projectName;

	@FXML
	private ComboBox<String> category;

	@FXML
	private TabPane tabPane;

	@FXML
	private CheckBox mesCheck;

	@FXML
	private TableColumn<NoTaPrMeVO, String> mesRec;

	@FXML
	void proDeleteButtonAction(ActionEvent event) throws RemoteException {
		for(NoTaPrMeVO vo : proTableView.getItems()) {
			boolean bool = vo.isChk();
			if(bool == true) {
				noticeService.Del_Alarm_Project(vo.getNtc_num());
				showAlertInfo("알람이 삭제되었습니다.");
			}
		}
	}

	@FXML
	void mesDeleteButtonAction(ActionEvent event) throws RemoteException {
		for(NoTaPrMeVO vo : mesTableView.getItems()) {
			boolean bool = vo.isChk();
			if(bool == true) {
				noticeService.Del_Alarm_Message(vo.getNtc_num());
				showAlertInfo("알람이 삭제되었습니다.");
			}
		}
	}

	@FXML
	void proRefreshBtn(ActionEvent event) throws RemoteException {
		proTableList.clear();
		proTableView.setItems(proTableList);
		proTableList.addAll(noticeService.getAlarm_NewProject(MemberInfo.mvo.getMem_id()));
	}

	@FXML
	void mesRefreshBtn(ActionEvent event) throws RemoteException {
		mesTableList.clear();
		mesTableView.setItems(mesTableList);
		mesTableList.addAll(noticeService.getAlarm_NewMessage(MemberInfo.mvo.getMem_id()));
	}

	@FXML
	void alarmClick(ActionEvent event) {
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
	void messageClick(ActionEvent event) {
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

	@FXML
	void projectClick(ActionEvent event) {
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

			Mypage_Controller mypage_controller = loader.getController();
			mypage_controller.setPrimaryStage(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void logClick(ActionEvent event) {
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
	void searchClick(ActionEvent event) {
		if (category.getValue() == "친구") {
			try {
				Parent changeProSearch = FXMLLoader.load(getClass().getResource("../view/FriendListSearch.fxml"));
				Scene proSearchScene = new Scene(changeProSearch);
				primaryStage.setScene(proSearchScene);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (category.getValue() == "프로젝트명") {
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
	void myImageAction(ActionEvent event) {

	}

	// service 연동
	private NoticeServiceInf noticeService;
	private ProjectServiceInf projectService;
	private MessageServiceInf messageService;

	// List
	ObservableList<NoTaPrMeVO> proTableList = FXCollections.observableArrayList();
	ObservableList<NoTaPrMeVO> mesTableList = FXCollections.observableArrayList();
	

	@FXML
	void initialize() throws RemoteException, NotBoundException {
		assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert proRefresh != null : "fx:id=\"proRefresh\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert mesNotificationDate != null : "fx:id=\"mesNotificationDate\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert proNo != null : "fx:id=\"proNo\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert searchText != null : "fx:id=\"searchText\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert mesNo != null : "fx:id=\"mesNo\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert mesRefresh != null : "fx:id=\"mesRefresh\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert loginAndLogout != null : "fx:id=\"loginAndLogout\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert projectTap != null : "fx:id=\"projectTap\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert messageTap != null : "fx:id=\"messageTap\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert proStartDate != null : "fx:id=\"proStartDate\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert mesTableView != null : "fx:id=\"mesTableView\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert proTableView != null : "fx:id=\"proTableView\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert mesCon != null : "fx:id=\"mesCon\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert proNotificationDate != null : "fx:id=\"proNotificationDate\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert myImage != null : "fx:id=\"myImage\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert proDeadLine != null : "fx:id=\"proDeadLine\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert mesWriter != null : "fx:id=\"mesWriter\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert projectName != null : "fx:id=\"projectName\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert category != null : "fx:id=\"category\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert tabPane != null : "fx:id=\"tabPane\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert mesCheck != null : "fx:id=\"mesCheck\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert mesRec != null : "fx:id=\"mesRec\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert procheckCol != null : "fx:id=\"procheckCol\" was not injected: check your FXML file 'WorkAlarm.fxml'.";
		assert mescheckCol != null : "fx:id=\"mescheckCol\" was not injected: check your FXML file 'WorkAlarm.fxml'.";

		// 등록된 서버를 찾기 위해 Registry객체를 생성한다.
		Registry reg = null;
		try {
//			reg = LocateRegistry.getRegistry("192.168.0.60", 5959);
			reg = LocateRegistry.getRegistry("localhost", 5959);
		} catch (RemoteException e) {
			System.out.println(e.getMessage());
		}

		// 서버에서 공유한 객체의 Alias를 이용하여 사용할 객체를 구한다.
		noticeService = (NoticeServiceInf) reg.lookup("not_server");
		projectService = (ProjectServiceInf) reg.lookup("pro_server");
		messageService = (MessageServiceInf) reg.lookup("mes_server");

		// 콤보박스 셋팅
		category.getItems().addAll("친구", "프로젝트명");

		// proTableList 컬럼 셋팅
		proNo.setCellValueFactory(new PropertyValueFactory<>("num"));
		projectName.setCellValueFactory(new PropertyValueFactory<>("pj_nm"));
		proStartDate.setCellValueFactory(new PropertyValueFactory<>("pj_str_date"));
		proDeadLine.setCellValueFactory(new PropertyValueFactory<>("pj_end_date"));
		proNotificationDate.setCellValueFactory(new PropertyValueFactory<>("ntc_date"));
		procheckCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<NoTaPrMeVO, CheckBox>, ObservableValue<CheckBox>>() {

					@Override
					public ObservableValue<CheckBox> call(CellDataFeatures<NoTaPrMeVO, CheckBox> param) {
						NoTaPrMeVO data = param.getValue();
						CheckBox chkBox = new CheckBox();

						chkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
							public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue,
									Boolean newValue) {
								data.setChk(newValue);
							}
						});
						return new SimpleObjectProperty<CheckBox>(chkBox);
					}
				});

		proTableView.setItems(proTableList);
		proTableList.addAll(noticeService.getAlarm_NewProject(MemberInfo.mvo.getMem_id()));

		// mesTableList 컬럼 셋팅
		mesNo.setCellValueFactory(new PropertyValueFactory<>("num"));
		mesRec.setCellValueFactory(new PropertyValueFactory<>("msg_rect"));
		mesCon.setCellValueFactory(new PropertyValueFactory<>("msg_cont"));
		mesWriter.setCellValueFactory(new PropertyValueFactory<>("msg_wt"));
		mesNotificationDate.setCellValueFactory(new PropertyValueFactory<>("ntc_date"));
		mescheckCol.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<NoTaPrMeVO, CheckBox>, ObservableValue<CheckBox>>() {

					@Override
					public ObservableValue<CheckBox> call(CellDataFeatures<NoTaPrMeVO, CheckBox> param) {
						NoTaPrMeVO data = param.getValue();
						CheckBox chkBox = new CheckBox();

						chkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
							public void changed(ObservableValue<? extends Boolean> ov, Boolean oldValue,
									Boolean newValue) {
								data.setChk(newValue);
							}
						});
						return new SimpleObjectProperty<CheckBox>(chkBox);
					}
				});

		mesTableView.setItems(mesTableList);
		mesTableList.addAll(noticeService.getAlarm_NewMessage(MemberInfo.mvo.getMem_id()));
		
	}
	
	private void showAlertInfo(String string) {
		Alert alertInfomation = new Alert(AlertType.INFORMATION);
		alertInfomation.setTitle("");
		alertInfomation.setHeaderText("알림");
		alertInfomation.setContentText(string);
		alertInfomation.showAndWait(); // Alert창 보여주기 (창을 띄우고 기다려줌) -> 꼭 해줘야야 뜸.
	}
}
