package pcws.vo;

import java.io.Serializable;

public class NoticeVO implements Serializable{

	private static final long serialVersionUID = 5345013172743045927L;
	
	private int ntc_num;			// 알림 번호
	private String ntc_date;		// 알림 날짜
	private String ntc_msg_id;		// 쪽지 ID
	private String ntc_pj_id;		// 프로젝트 ID
	private int ntc_tasg_num;		// 업무 번호
	
	public int getNtc_num() {
		return ntc_num;
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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
