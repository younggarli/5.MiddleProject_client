package pcws.main;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import pcws.service.friendService.FriendServiceInf;
import pcws.service.memberService.MemberServiceInf;
import pcws.vo.FriendVO;
import pcws.vo.MemberVO;
import pcws.vo.YouAndMeVO;

public class Friend_Search_Controller {

	private Stage primaryStage;
	private Stage st;
	private Friend_Controller fr;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void setFr(Friend_Controller fr) {
		this.fr = fr;
	}

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button add;

	@FXML
	private Button confirm;

	@FXML
	private TextField searchText;

	@FXML
	private Button search;

	@FXML
	private ListView<MemberVO> listView;

	@FXML
	void addAction(ActionEvent event) throws RemoteException {
		memVo = listView.getSelectionModel().getSelectedItem();
		if (memVo == null) {
			showAlertInfo("멤버가 선택되지 않았습니다. 다시 추가해주세요.");
			return;
		}

		List<YouAndMeVO> searchFriendList = friendService.getAllMember(MemberInfo.mvo.getMem_id());

		boolean friAddFlag = true; // 친구를 등록할 수 있는지 여부(구분 변수)
		for (YouAndMeVO friendVo : searchFriendList) {
			if (friendVo.getFrd_id().equals(memVo.getMem_id())) {
				friAddFlag = false;
				break;
			}
		}

		if (friAddFlag) {
			friendService.getFriend_Add(MemberInfo.mvo.getMem_id(), memVo.getMem_id());
			showAlertInfo("친구를 추가하였습니다.");

			try {
				fr.reset();
				fr.initialize();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}

			st = (Stage) add.getScene().getWindow();
			st.close();
		} else {
			showAlertInfo("이미 추가되어있는 친구입니다.");
		}
	}

	@FXML
	void confirmAction(ActionEvent event) {
		st = (Stage) confirm.getScene().getWindow();
		st.close();
	}

	private MemberVO memVo = null;

	@FXML
	void onMouseClick(MouseEvent arg) {
		memVo = listView.getSelectionModel().getSelectedItem();
	}

	@FXML
	void searchAction(ActionEvent event) throws RemoteException {
		searchList.clear();
		listView.getItems().clear();
		List<MemberVO> list = null;
		list = memberService.searchAllMember(searchText.getText());
		setting();
		searchList.addAll(list);
		listView.setItems(searchList);
	}

	// service 연동
	private MemberServiceInf memberService;
	private FriendServiceInf friendService;

	// List
	ObservableList<MemberVO> searchList = FXCollections.observableArrayList();

	@FXML
	void initialize() {
		assert add != null : "fx:id=\"add\" was not injected: check your FXML file 'Friend_Search.fxml'.";
		assert confirm != null : "fx:id=\"confirm\" was not injected: check your FXML file 'Friend_Search.fxml'.";
		assert searchText != null : "fx:id=\"searchText\" was not injected: check your FXML file 'Friend_Search.fxml'.";
		assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'Friend_Search.fxml'.";
		assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'Friend_Search.fxml'.";

		try {
			// 등록된 서버를 찾기 위해 Registry객체를 생성한다.
			Registry reg = LocateRegistry.getRegistry("localhost", 5959);

			// 서버에서 공유한 객체의 Alias를 이용하여 사용할 객체를 구한다.
			memberService = (MemberServiceInf) reg.lookup("mem_server");
			friendService = (FriendServiceInf) reg.lookup("fri_server");
		} catch (Exception e) {
		}

	}

	public void setting() {
		listView.setCellFactory(new Callback<ListView<MemberVO>, ListCell<MemberVO>>() {

			@Override
			public ListCell<MemberVO> call(ListView<MemberVO> param) {
				return new ListCell<MemberVO>() {
					@Override
					protected void updateItem(MemberVO item, boolean empty) {
						super.updateItem(item, empty);
						if (item != null) {
							// System.out.println(String.format("%-15s%-35s%-20s", item.getMem_nm(),
							// item.getMem_id(), item.getMem_tel()));
							// setText(String.format("%-15s%-35s%-20s", item.getMem_nm(), item.getMem_id(),
							// item.getMem_tel()));
							HBox hbox = new HBox(5);
							Label lblNm = new Label(item.getMem_nm());
							lblNm.setPrefWidth(100);
							Label lblId = new Label(item.getMem_id());
							lblId.setPrefWidth(100);
							Label lblTel = new Label(item.getMem_tel());
							lblTel.setPrefWidth(100);
							hbox.getChildren().addAll(lblNm, lblId, lblTel);
							setGraphic(hbox);
						}
					}
				};
			}
		});
	}

	private void showAlertInfo(String string) {
		Alert alertInfomation = new Alert(AlertType.INFORMATION);
		alertInfomation.setTitle("");
		alertInfomation.setHeaderText("알림");
		alertInfomation.setContentText(string);
		alertInfomation.showAndWait(); // Alert창 보여주기 (창을 띄우고 기다려줌) -> 꼭 해줘야야 뜸.
	}

}
