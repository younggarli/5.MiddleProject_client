package pcws.vo;

import java.io.Serializable;

public class TeamVO implements Serializable{

	private static final long serialVersionUID = -2160739976626164629L;

	private String tm_id;			// 팀 ID
	private String tm_pj_id;		// 프로젝트 ID
	private String tm_mem_id;		// 회원 ID
	private String mem_nm;
	
	
	public String getMem_nm() {
		return mem_nm;
	}

	public void setMem_nm(String mem_nm) {
		this.mem_nm = mem_nm;
	}

	public String getTm_id() {
		return tm_id;
	}
	
	public void setTm_id(String tm_id) {
		this.tm_id = tm_id;
	}
	
	public String getTm_pj_id() {
		return tm_pj_id;
	}
	
	public void setTm_pj_id(String tm_pj_id) {
		this.tm_pj_id = tm_pj_id;
	}

	public String getTm_mem_id() {
		return tm_mem_id;
	}
	
	public void setTm_mem_id(String tm_mem_id) {
		this.tm_mem_id = tm_mem_id;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
