package pcws.vo;

import java.io.Serializable;

public class TaskVO implements Serializable{

	private static final long serialVersionUID = -3915126163253648631L;

	private int task_num;				// 업무 번호
	private String task_cont;			// 업무 내용
	private String task_tag;			// 태그
	private int task_ipt;				// 중요도
	private int task_eptn_time;			// 업무예상시간
	private String task_mem_id;			// 회원 ID
	private String task_pj_id;			// 프로젝트 ID
	private String task_chk_list_id;	// 체크리스트 ID
	private String task_color_lbl;		// 색상라벨
	private String task_nm;				// 업무명
	private String task_str_date;		// 업무 시작일
	private String task_end_date;		// 업무 마감일
	
	public int getTask_num() {
		return task_num;
	}
	
	public void setTask_num(int task_num) {
		this.task_num = task_num;
	}
	
	public String getTask_cont() {
		return task_cont;
	}
	
	public void setTask_cont(String task_cont) {
		this.task_cont = task_cont;
	}
	
	public String getTask_tag() {
		return task_tag;
	}
	
	public void setTask_tag(String task_tag) {
		this.task_tag = task_tag;
	}
	
	public int getTask_ipt() {
		return task_ipt;
	}
	
	public void setTask_ipt(int task_ipt) {
		this.task_ipt = task_ipt;
	}
	
	public int getTask_eptn_time() {
		return task_eptn_time;
	}
	
	public void setTask_eptn_time(int task_eptn_time) {
		this.task_eptn_time = task_eptn_time;
	}
	
	public String getTask_mem_id() {
		return task_mem_id;
	}
	
	public void setTask_mem_id(String task_mem_id) {
		this.task_mem_id = task_mem_id;
	}
	
	public String getTask_pj_id() {
		return task_pj_id;
	}
	
	public void setTask_pj_id(String task_pj_id) {
		this.task_pj_id = task_pj_id;
	}
	
	public String getTask_chk_list_id() {
		return task_chk_list_id;
	}
	
	public void setTask_chk_list_id(String task_chk_list_id) {
		this.task_chk_list_id = task_chk_list_id;
	}
	
	public String getTask_color_lbl() {
		return task_color_lbl;
	}
	
	public void setTask_color_lbl(String task_color_lbl) {
		this.task_color_lbl = task_color_lbl;
	}
	
	public String getTask_nm() {
		return task_nm;
	}
	
	public void setTask_nm(String task_nm) {
		this.task_nm = task_nm;
	}
	
	public String getTask_str_date() {
		return task_str_date;
	}
	
	public void setTask_str_date(String task_str_date) {
		this.task_str_date = task_str_date;
	}
	
	public String getTask_end_date() {
		return task_end_date;
	}
	
	public void setTask_end_date(String task_end_date) {
		this.task_end_date = task_end_date;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
