package pcws.vo;

import java.io.Serializable;

public class FriendVO implements Serializable{

	private static final long serialVersionUID = 8245475091469074934L;

	private String frd_reg_date;			// 등록일자
	private String frd_id;					// 친구 ID
	private String frd_mem_id;				// 본인 ID
	
	public String getFrd_reg_date() {
		return frd_reg_date;
	}
	
	public void setFrd_reg_date(String frd_reg_date) {
		this.frd_reg_date = frd_reg_date;
	}
	
	public String getFrd_id() {
		return frd_id;
	}
	
	public void setFrd_id(String frd_id) {
		this.frd_id = frd_id;
	}
	
	public String getFrd_mem_id() {
		return frd_mem_id;
	}
	
	public void setFrd_mem_id(String frd_mem_id) {
		this.frd_mem_id = frd_mem_id;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
