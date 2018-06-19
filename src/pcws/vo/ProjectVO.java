package pcws.vo;

import java.io.Serializable;

public class ProjectVO implements Serializable{

	private static final long serialVersionUID = -1627463048368636911L;

	private String pj_id;			// 프로젝트 ID
	private String pj_nm;			// 프로젝트 명
	private String pj_str_date;		// 시작일
	private String pj_end_date;		// 종료일
	private String pj_tag;			// 태그
	private String pj_color=null; // 색깔
	private String num;				// 행 번호
	
	public String getPj_tag() {
		return pj_tag;
	}

	public void setPj_tag(String pj_tag) {
		this.pj_tag = pj_tag;
	}

	public String getPj_color() {
		return pj_color;
	}

	public void setPj_color(String pj_color) {
		this.pj_color = pj_color;
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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
