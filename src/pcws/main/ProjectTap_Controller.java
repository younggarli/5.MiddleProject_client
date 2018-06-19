package pcws.main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.sun.javafx.scene.control.skin.LabeledText;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pcws.service.checkListService.CheckListServiceInf;
import pcws.service.projectService.ProjectServiceInf;
import pcws.service.scheduleService.ScheduleServiceInf;
import pcws.service.teamService.TeamServiceInf;
import pcws.vo.CheckListVO;
import pcws.vo.ProjectVO;
import pcws.vo.ScheduleVO;

public class ProjectTap_Controller {
	
    
    @FXML
    private PieChart piegraph;
    
    @FXML
    private BarChart<String, Number> barchart;
    
//    CategoryAxis xAxis = new CategoryAxis();
//    NumberAxis yAxis = new NumberAxis();
//    
//    XYChart.Series<String, Number> series1 =
//    		new XYChart.Series<String, Number>();
//    XYChart.Series<String, Number> series2 =
//    		new XYChart.Series<String, Number>();
//    XYChart.Series<String, Number> series3 =
//    		new XYChart.Series<String, Number>();
//    
//    Object label;
//    
//    public Object getLabel() {
//    	return label;
//    }
//    
//    public void setLabel(Object label) {
//    	this.label = label;
//    }
    
	private Stage primaryStage;
	
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
    private ComboBox<String> comboCate;

    @FXML
    private TextField txtSearch;

    @FXML
    private ComboBox<String> comboLog;

    @FXML
    private Tab proTap;

    @FXML
    private ImageView refreshTask;

    @FXML
    private HBox proHbox;

    @FXML
    private Tab calTap;

    @FXML
    private ImageView refreshPro;

    @FXML
    private Button btnBefore;

    @FXML
    private Label lblYear;

    @FXML
    private Label lblMonth;

    @FXML
    private Button btnNext;

    @FXML
    private Label lblDate15;

    @FXML
    private Label lblDate8;

    @FXML
    private Label lblDate1;

    @FXML
    private Label lblDate22;

    @FXML
    private Label lblDate29;

    @FXML
    private Label lblDate2;

    @FXML
    private Label lblDate9;

    @FXML
    private Label lblDate16;

    @FXML
    private Label lblDate23;

    @FXML
    private Label lblDate30;

    @FXML
    private Label lblDate3;

    @FXML
    private Label lblDate10;

    @FXML
    private Label lblDate17;

    @FXML
    private Label lblDate24;

    @FXML
    private Label lblDate31;

    @FXML
    private Label lblDate4;

    @FXML
    private Label lblDate11;

    @FXML
    private Label lblDate18;

    @FXML
    private Label lblDate25;

    @FXML
    private Label lblDate32;

    @FXML
    private Label lblDate5;

    @FXML
    private Label lblDate12;

    @FXML
    private Label lblDate19;

    @FXML
    private Label lblDate26;

    @FXML
    private Label lblDate33;

    @FXML
    private Label lblDate6;

    @FXML
    private Label lblDate13;

    @FXML
    private Label lblDate20;

    @FXML
    private Label lblDate27;

    @FXML
    private Label lblDate34;

    @FXML
    private Label lblDate7;

    @FXML
    private Label lblDate14;

    @FXML
    private Label lblDate21;

    @FXML
    private Label lblDate28;

    @FXML
    private Label lblDate35;

    @FXML
    private Label lblDate36;

    @FXML
    private Label lblDate37;

    @FXML
    private Label lblDate38;

    @FXML
    private Label lblDate39;

    @FXML
    private Label lblDate40;

    @FXML
    private Label lblDate41;

    @FXML
    private Label lblDate42;

    @FXML
    private Label lblToday;

    @FXML
    private TextArea txtMemo;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnDelete;

    @FXML
    private Tab statusTap;

    @FXML
    private ImageView refreshMes;

    @FXML
    void BeforeClick(ActionEvent event) throws RemoteException {
    	if (month == 0) {
			month = 12;
			year--;
		}
		month = month - 1;
		clearCal();
		printCal();
    }

    @FXML
    void DeleteClick(ActionEvent event) throws RemoteException {
    	ScheduleVO svo = new ScheduleVO();
    	svo.setScdu_mem_id(MemberInfo.mvo.getMem_id());
    	svo.setScdu_date(lblToday.getText());
    	svo.setScdu_memo(txtMemo.getText());
    	
    	if(scheduleService.memoDelete(svo)) {
    		showAlertInfo("삭제되었습니다.");
    		txtMemo.clear();
    	}else{
    		showAlertInfo("xxxxxxxx");
    	}
    }

    @FXML
    void NextClick(ActionEvent event) throws RemoteException {
    	if (month == 11) {
			month = -1;
			year++;
		}
		month = month + 1;
		clearCal();
		printCal();
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

    @FXML
    void dayClick(MouseEvent event) throws RemoteException {
    	txtMemo.clear();
    	LabeledText label = (LabeledText) event.getTarget();
    	lblToday.setText(year + "년 " + (month + 1) + "월 " + label.getText() + "일");
//		System.out.println(label.getText());
    	
    	String date = lblToday.getText();
    	String id = MemberInfo.mvo.getMem_id();
    	String memo = scheduleService.showMemo(id, date);
    	txtMemo.setText(memo);
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
    		
    		Mypage_Controller controller = loader.getController();
    		controller.setPrimaryStage(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void pcwsClick(MouseEvent event) throws RemoteException {
    	piegraph.getData().clear();
    	printPie();
    	barchart.getData().clear();
    	barchartSetting();
    }

    @FXML
    void projectClick(ActionEvent event) {

    }

    @FXML
    void saveClick(ActionEvent event) throws RemoteException {
    	ScheduleVO svo = new ScheduleVO();
    	svo.setScdu_mem_id(MemberInfo.mvo.getMem_id());
    	svo.setScdu_date(lblToday.getText());
    	svo.setScdu_memo(txtMemo.getText());
    	
    	if(scheduleService.memoSave(svo)) {
    		showAlertInfo("저장되었습니다.");
    	}else{
    		showAlertInfo("xxxxxxxx");
    	}
    }

    @FXML
    void searchClick(ActionEvent event) {
    	if(comboCate.getValue().equals("Project")) {
    		try {
    			MemberInfo.txtSearch = txtSearch.getText();
        		FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ProListSearch.fxml"));
        		Parent change = loader.load();
        		
        		Scene scene = new Scene(change);
        		primaryStage.setScene(scene);
        		
        		ProListSearch_Controller controller = loader.getController();
        		controller.setPrimaryStage(primaryStage);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}else {
    		
    	}
    }

    // 쪽지함 창으로 변경됨
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

    private int firstWeek;
	private int lastDay;
	private int year;
	private int month;
	private int day;
	private Calendar today;
	private Label[] arrLabel;
    
    ProjectServiceInf projectService;
    ScheduleServiceInf scheduleService;
    CheckListServiceInf checkService;
 	TeamServiceInf teamService;
    
    public void clearCal() {
		for (int i = 0; i < arrLabel.length; i++) {
			arrLabel[i].setText("");
			arrLabel[i].setBackground(lblYear.getBackground());
		}
	}

	public void printCal() throws RemoteException {
		lblYear.setText(year + "년");
		if(month < 10) {
			lblMonth.setText("0" + (month + 1) + "월"); // month 0~11
		}else {
			lblMonth.setText((month + 1) + "월"); // month 0~11
		}
		lblToday.setText(year + "년 " + (month + 1) + "월 " + day + "일");

		today.set(year, month, 1);
		firstWeek = today.get(Calendar.DAY_OF_WEEK); // 월 시작 요일 (1: 일요일)
		lastDay = today.getActualMaximum(Calendar.DATE);// 월 마지막 날짜
		
		for (int i = 1; i <= lastDay; i++) {
			int mod = i % 7;

			arrLabel[firstWeek - 2 + i].setText(String.valueOf(i));
		}
		
		MemberInfo.proList.clear();
		MemberInfo.proList.addAll(projectService.chkProjectCount(MemberInfo.mvo.getMem_id())); // 로그인한 회원의 프로젝트 정보를 가져옴
		for(int z=0; z<MemberInfo.proList.size(); z++) {
			String str = lblYear.getText().split("년")[0] + "-" + lblMonth.getText().split("월")[0];
			System.out.println(str);
			System.out.println("==" + MemberInfo.proList.get(z).getPj_str_date().substring(0, 7));
			if(str.equals(MemberInfo.proList.get(z).getPj_str_date().substring(0, 7))) {
				int[] startDate = new int[3]; // 프로젝트는 최대 3개이므로
				int[] endDate = new int[3];
				for(int i=0; i<MemberInfo.proList.size(); i++) {
					startDate[i] = Integer.parseInt(MemberInfo.proList.get(i).getPj_str_date().substring(8, 10));
					endDate[i] = Integer.parseInt(MemberInfo.proList.get(i).getPj_end_date().substring(8, 10));
					for(int k=1; k<=lastDay; k++) {
						if(arrLabel[k].getText().equals(String.valueOf(startDate[i]))) {
							for(int j=k; j<=endDate[i]-1; j++) {
								arrLabel[j].setBackground(new Background(new BackgroundFill(Color.web(MemberInfo.proList.get(i).getPj_color()), CornerRadii.EMPTY, Insets.EMPTY)));
							}
						}
					}
				}
			}
		}
		
	}

	private void showAlertInfo(String string) {
		Alert alertInfomation = new Alert(AlertType.INFORMATION);
		alertInfomation.setTitle("");
		alertInfomation.setHeaderText("알림");
		alertInfomation.setContentText(string);
		alertInfomation.showAndWait();	 // Alert창 보여주기 (창을 띄우고 기다려줌) -> 꼭 해줘야야 뜸.
	}
	
    @FXML
    void initialize() throws IOException, NotBoundException {
    	 // 등록된 서버를 찾기 위해 Registry객체를 생성한다.
     	Registry reg = LocateRegistry.getRegistry("localhost", 5959);
//     	Registry reg = LocateRegistry.getRegistry("192.168.0.60", 5959);
     	
     	// 서버에서 공유한 객체의 Alias를 이용하여 사용할 객체를 구한다.
     	projectService = (ProjectServiceInf)reg.lookup("pro_server"); 
     	scheduleService = (ScheduleServiceInf)reg.lookup("sch_server"); 
     	checkService = (CheckListServiceInf)reg.lookup("che_server");
     	teamService = (TeamServiceInf)reg.lookup("tea_server");
     	//=======================================프로젝트==============================================
     	settingList();
     	
        //==============================달 력 =============================================
        
        arrLabel = new Label[] { lblDate1, lblDate2, lblDate3, lblDate4, lblDate5, lblDate6, lblDate7, lblDate8,
				lblDate9, lblDate10, lblDate11, lblDate12, lblDate13, lblDate14, lblDate15, lblDate16, lblDate17,
				lblDate18, lblDate19, lblDate20, lblDate21, lblDate22, lblDate23, lblDate24, lblDate25, lblDate26,
				lblDate27, lblDate28, lblDate29, lblDate30, lblDate31, lblDate32, lblDate33, lblDate34, lblDate35,
				lblDate36, lblDate37, lblDate38, lblDate39, lblDate40, lblDate41, lblDate42 };

		today = Calendar.getInstance(Locale.KOREA);
		year = today.get(Calendar.YEAR);
		month = today.get(Calendar.MONTH);
		day = today.get(Calendar.DAY_OF_MONTH);

		printCal();
    	
		printPie();
    	
    	
        assert log != null : "fx:id=\"log\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert comboCate != null : "fx:id=\"comboCate\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert txtSearch != null : "fx:id=\"txtSearch\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert comboLog != null : "fx:id=\"comboLog\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert proTap != null : "fx:id=\"proTap\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert refreshTask != null : "fx:id=\"refreshTask\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert proHbox != null : "fx:id=\"proHbox\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert calTap != null : "fx:id=\"calTap\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert refreshPro != null : "fx:id=\"refreshPro\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert btnBefore != null : "fx:id=\"btnBefore\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblYear != null : "fx:id=\"lblYear\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblMonth != null : "fx:id=\"lblMonth\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert btnNext != null : "fx:id=\"btnNext\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate15 != null : "fx:id=\"lblDate15\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate8 != null : "fx:id=\"lblDate8\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate1 != null : "fx:id=\"lblDate1\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate22 != null : "fx:id=\"lblDate22\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate29 != null : "fx:id=\"lblDate29\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate2 != null : "fx:id=\"lblDate2\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate9 != null : "fx:id=\"lblDate9\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate16 != null : "fx:id=\"lblDate16\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate23 != null : "fx:id=\"lblDate23\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate30 != null : "fx:id=\"lblDate30\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate3 != null : "fx:id=\"lblDate3\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate10 != null : "fx:id=\"lblDate10\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate17 != null : "fx:id=\"lblDate17\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate24 != null : "fx:id=\"lblDate24\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate31 != null : "fx:id=\"lblDate31\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate4 != null : "fx:id=\"lblDate4\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate11 != null : "fx:id=\"lblDate11\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate18 != null : "fx:id=\"lblDate18\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate25 != null : "fx:id=\"lblDate25\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate32 != null : "fx:id=\"lblDate32\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate5 != null : "fx:id=\"lblDate5\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate12 != null : "fx:id=\"lblDate12\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate19 != null : "fx:id=\"lblDate19\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate26 != null : "fx:id=\"lblDate26\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate33 != null : "fx:id=\"lblDate33\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate6 != null : "fx:id=\"lblDate6\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate13 != null : "fx:id=\"lblDate13\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate20 != null : "fx:id=\"lblDate20\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate27 != null : "fx:id=\"lblDate27\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate34 != null : "fx:id=\"lblDate34\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate7 != null : "fx:id=\"lblDate7\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate14 != null : "fx:id=\"lblDate14\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate21 != null : "fx:id=\"lblDate21\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate28 != null : "fx:id=\"lblDate28\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate35 != null : "fx:id=\"lblDate35\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate36 != null : "fx:id=\"lblDate36\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate37 != null : "fx:id=\"lblDate37\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate38 != null : "fx:id=\"lblDate38\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate39 != null : "fx:id=\"lblDate39\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate40 != null : "fx:id=\"lblDate40\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate41 != null : "fx:id=\"lblDate41\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblDate42 != null : "fx:id=\"lblDate42\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert lblToday != null : "fx:id=\"lblToday\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert txtMemo != null : "fx:id=\"txtMemo\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert btnSave != null : "fx:id=\"btnSave\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert statusTap != null : "fx:id=\"statusTap\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert refreshMes != null : "fx:id=\"refreshMes\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert barchart != null : "fx:id=\"barchart\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        assert piegraph != null : "fx:id=\"piegraph\" was not injected: check your FXML file 'ProjectTap.fxml'.";
        comboCate.getItems().addAll("Member","Project");
        comboLog.getItems().addAll("LogIn", "LogOut");
        
        barchartSetting();
    }
    
    public void settingList() throws IOException {
    	Parent[] roots = new Parent[3];
        
        List<ProjectVO> list = projectService.chkProjectCount(MemberInfo.mvo.getMem_id());
        
        for(int i=0; i<list.size(); i++) {
        	FXMLLoader tempLoader = new FXMLLoader(getClass().getResource("../view/Project_Task_List.fxml"));
        	roots[i] =  tempLoader.load();
        	ProjectTaskList_Controller tempController = tempLoader.getController();
        	tempController.setData(list.get(i));
        	
        	
        	tempController.setPjController(this);
        }
        int tempCnt =  list.size();
        
        for(int i=tempCnt; i<3; i++) {
        	FXMLLoader tempLoader = new FXMLLoader(getClass().getResource("../view/TaskAdd.fxml"));
        	roots[i] =  tempLoader.load();
        	
        	TaskAdd_Controller controller = tempLoader.getController();
        	
        	controller.setPjController(this);
        	
        }
        
        proHbox.getChildren().clear();
        proHbox.getChildren().addAll(roots);
    }
    
    
    
    public void printPie() throws RemoteException {
    	List<ProjectVO> plist = new ArrayList<>();
		plist.addAll(projectService.chkProjectCount(MemberInfo.mvo.getMem_id()));
		
		String[] pjNm = new String[3]; // 프젝 이름
		for(int i=0; i<plist.size(); i++) {
			pjNm[i] = plist.get(i).getPj_nm();
		}
		int[] cnt = new int[3]; // 프로젝트의 업무리스트 수 
		for(int i=0; i<MemberInfo.proList.size(); i++) {
			List<CheckListVO> clist = new ArrayList<>();
			clist.addAll(checkService.checkList_Allget(MemberInfo.proList.get(i).getPj_id()));
			cnt[i] = clist.size();
		}
		ObservableList<PieChart.Data> pieCharData = FXCollections.observableArrayList(
				new PieChart.Data(pjNm[0], cnt[0]),
    			new PieChart.Data(pjNm[1], cnt[1]),
    			new PieChart.Data(pjNm[2], cnt[2])
				);
		piegraph.setTitle("각 프로젝트의 총 업무 수 비교 그래프");
		piegraph.setLabelLineLength(70); //선 길이
		piegraph.setLegendSide(Side.BOTTOM);// 범례가 놓이는 위치
		piegraph.getData().addAll(pieCharData);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    List<ProjectVO> pvoList;

	public void barchartSetting() throws RemoteException {
//		BarChart<String, Number> temp=new BarChart<>(xAxis, yAxis);
//		pvoList.addAll(projectService.chkProjectCount(MemberInfo.mvo.getMem_id()));
//		xAxis.setLabel("Project");
//	    xAxis.setCategories(FXCollections.<String>observableArrayList(
//	    Arrays.asList("thgus3022")));
//	    int i = 1;
//	    for (ProjectVO pvo : pvoList) {
//			if(i==1) {
//				series1.setName("1번");
//				series1.getData().add(new XYChart.Data<String, Number>(pvo.getPj_id()+"미완료 수",checkService.getChkList(pvo)));
//				series1.getData().add(new XYChart.Data<String, Number>(pvo.getPj_id()+"완료 수",checkService.getNoNChkList(pvo)));
//			}else if(i==2) {
//				series2.setName("2번");
//				series2.getData().add(new XYChart.Data<String, Number>(pvo.getPj_id()+"미완료 수",checkService.getChkList(pvo)));
//				series2.getData().add(new XYChart.Data<String, Number>(pvo.getPj_id()+"완료 수",checkService.getNoNChkList(pvo)));
//			}else if(i==3){
//				series3.setName("3번");
//				series3.getData().add(new XYChart.Data<String, Number>(pvo.getPj_id()+"미완료 수",checkService.getChkList(pvo)));
//				series3.getData().add(new XYChart.Data<String, Number>(pvo.getPj_id()+"완료 수",checkService.getNoNChkList(pvo)));
//			}
//			i++;
//		}
//	    if(i==2) {
//	    	barchart.getData().addAll(series1);
//	    }else if(i==3) {
//	    	barchart.getData().addAll(series1, series2);
//	    }else if(i==4) {
//	    	barchart.getData().addAll(series1, series2, series3);
//	    }
//	    barchart=temp;
//		List<ProjectVO> pvoList = new ArrayList<>();
//		pvoList.addAll(projectService.chkProjectCount(MemberInfo.mvo.getMem_id()));
		CategoryAxis xAxis = new CategoryAxis();
    	NumberAxis yAxis = new NumberAxis();
    	
//    	BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);
//    	barchart = new BarChart<>(xAxis, yAxis);
    	
    	barchart.setTitle("프로젝트별 체크여부");
    	xAxis.setLabel("프로젝트");
    	yAxis.setLabel("개수");
    	
    	XYChart.Series<String, Number> series1 = new XYChart.Series<>();
    	XYChart.Series<String, Number> series2 = new XYChart.Series<>();
    	XYChart.Series<String, Number> series3 = new XYChart.Series<>();
    	XYChart.Series<String, Number> series4 = new XYChart.Series<>();
    	XYChart.Series<String, Number> series5 = new XYChart.Series<>();
    	XYChart.Series<String, Number> series6 = new XYChart.Series<>();
    	
    	series1.setName("false");
//    	for(int i=0; i<MemberInfo.proList.size(); i++) {
    		series1.getData().add(new XYChart.Data<String, Number>(MemberInfo.proList.get(0).getPj_nm(), checkService.getChkList(MemberInfo.proList.get(0))));
    		series2.getData().add(new XYChart.Data<String, Number>(MemberInfo.proList.get(0).getPj_nm(), checkService.getNoNChkList(MemberInfo.proList.get(0))));
//    	}
    	
    	series2.setName("true");
//    	for(int i=0; i<MemberInfo.proList.size(); i++) {
    		series3.getData().add(new XYChart.Data<String, Number>(MemberInfo.proList.get(1).getPj_nm(), checkService.getChkList(MemberInfo.proList.get(1))));
    		series4.getData().add(new XYChart.Data<String, Number>(MemberInfo.proList.get(1).getPj_nm(), checkService.getNoNChkList(MemberInfo.proList.get(1))));
//    	}
    	
    	series3.setName("false");
    	series4.setName("true");
//    	for(int i=0; i<MemberInfo.proList.size(); i++) {
//    		series5.getData().add(new XYChart.Data<String, Number>(MemberInfo.proList.get(2).getPj_nm(), checkService.getChkList(MemberInfo.proList.get(2))));
//    		series6.getData().add(new XYChart.Data<String, Number>(MemberInfo.proList.get(2).getPj_nm(), checkService.getNoNChkList(MemberInfo.proList.get(2))));
//    	}
    	
    	barchart.getData().addAll(series1, series2, series3, series4);
		
//    	Stage barChart = new Stage();
//    	barChart.initModality(Modality.WINDOW_MODAL);
//    	barChart.initOwner(primaryStage);
    	
//		Scene scene = new Scene(bc);
//		barChart.setTitle("막대 그래프");
//		barChart.setScene(scene);
//		barChart.show();
		
	 }
}
