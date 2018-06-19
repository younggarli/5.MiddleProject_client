package pcws.vo;

import java.io.Serializable;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;

public class NoTaPrMeVO implements Serializable {

	private static final long serialVersionUID = 6523173247428290688L;
	
	private boolean chk;	// 체크 박스
	private int ntc_num; // 알림 번호
	private String ntc_date; // 알림 날짜
	private String ntc_msg_id; // 쪽지 ID
	private String ntc_pj_id; // 프로젝트 ID
	private int ntc_tasg_num; // 업무 번호
	private String num;	// 행 번호
	
	private String pj_id; // 프로젝트 ID
	private String pj_nm; // 프로젝트 명
	private String pj_str_date; // 시작일
	private String pj_end_date; // 종료일

	private String msg_id;
	private String msg_wt;
	private String msg_cont;
	private String msg_rect;
	private String msg_date;
	private String msg_filenm;
	private String msg_filepath;
	private String msg_check;

	public boolean isChk() {
		return chk;
	}
	
	public void setChk(boolean chk) {
		this.chk = chk;
	}
	
	public int getNtc_num() {
		return ntc_num;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public void setNtc_num(int ntc_num) {
		this.ntc_num = ntc_num;
	}

	public String getNtc_date() {
		return ntc_date;
	}

	public void setNtc_date(String ntc_date) {
		this.ntc_date = ntc_date;
	}

	public String getNtc_msg_id() {
		return ntc_msg_id;
	}

	public void setNtc_msg_id(String ntc_msg_id) {
		this.ntc_msg_id = ntc_msg_id;
	}

	public String getNtc_pj_id() {
		return ntc_pj_id;
	}

	public void setNtc_pj_id(String ntc_pj_id) {
		this.ntc_pj_id = ntc_pj_id;
	}

	public int getNtc_tasg_num() {
		return ntc_tasg_num;
	}

	public void setNtc_tasg_num(int ntc_tasg_num) {
		this.ntc_tasg_num = ntc_tasg_num;
	}

	public String getPj_id() {
		return pj_id;
	}

	public void setPj_id(String pj_id) {
		this.pj_id = pj_id;
	}

	public String getPj_nm() {
		return pj_nm;
	}

	public void setPj_nm(String pj_nm) {
		this.pj_nm = pj_nm;
	}

	public String getPj_str_date() {
		return pj_str_date;
	}

	public void setPj_str_date(String pj_str_date) {
		this.pj_str_date = pj_str_date;
	}

	public String getPj_end_date() {
		return pj_end_date;
	}

	public void setPj_end_date(String pj_end_date) {
		this.pj_end_date = pj_end_date;
	}

	public String getMsg_id() {
		return msg_id;
	}

	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
	}

	public String getMsg_wt() {
		return msg_wt;
	}

	public void setMsg_wt(String msg_wt) {
		this.msg_wt = msg_wt;
	}

	public String getMsg_cont() {
		return msg_cont;
	}

	public void setMsg_cont(String msg_cont) {
		this.msg_cont = msg_cont;
	}

	public String getMsg_rect() {
		return msg_rect;
	}

	public void setMsg_rect(String msg_rect) {
		this.msg_rect = msg_rect;
	}

	public String getMsg_date() {
		return msg_date;
	}

	public void setMsg_date(String msg_date) {
		this.msg_date = msg_date;
	}

	public String getMsg_filenm() {
		return msg_filenm;
	}

	public void setMsg_filenm(String msg_filenm) {
		this.msg_filenm = msg_filenm;
	}

	public String getMsg_filepath() {
		return msg_filepath;
	}

	public void setMsg_filepath(String msg_filepath) {
		this.msg_filepath = msg_filepath;
	}

	public String getMsg_check() {
		return msg_check;
	}

	public void setMsg_check(String msg_check) {
		this.msg_check = msg_check;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
