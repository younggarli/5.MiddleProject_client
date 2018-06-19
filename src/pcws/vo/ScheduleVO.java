package pcws.vo;

import java.io.Serializable;

public class ScheduleVO implements Serializable{

	private static final long serialVersionUID = 5480260009312959468L;

	private String scdu_date;			// 날짜
	private String scdu_memo;			// 메모
	private String scdu_mem_id;			// 회원 ID (로그인한 사용자 아이디)
	
	public String getScdu_date() {
		return scdu_date;
	}
	
	public void setScdu_date(String scdu_date) {
		this.scdu_date = scdu_date;
	}
	
	public String getScdu_memo() {
		return scdu_memo;
	}
	
	public void setScdu_memo(String scdu_memo) {
		this.scdu_memo = scdu_memo;
	}
	
	public String getScdu_mem_id() {
		return scdu_mem_id;
	}

	public void setScdu_mem_id(String scdu_mem_id) {
		this.scdu_mem_id = scdu_mem_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
