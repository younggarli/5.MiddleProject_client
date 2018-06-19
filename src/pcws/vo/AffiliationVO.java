package pcws.vo;

import java.io.Serializable;

public class AffiliationVO implements Serializable{

	private static final long serialVersionUID = 5909698847228107679L;
	
	private String afl_mem_id;			// 회원 ID
	private String afl_tm_id;			// 팀 ID
	private String afl_date;			// 소속일자
	
	public String getAfl_mem_id() {
		return afl_mem_id;
	}
	
	public void setAfl_mem_id(String afl_mem_id) {
		this.afl_mem_id = afl_mem_id;
	}
	
	public String getAfl_tm_id() {
		return afl_tm_id;
	}
	
	public void setAfl_tm_id(String afl_tm_id) {
		this.afl_tm_id = afl_tm_id;
	}
	
	public String getAfl_date() {
		return afl_date;
	}
	
	public void setAfl_date(String afl_date) {
		this.afl_date = afl_date;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
