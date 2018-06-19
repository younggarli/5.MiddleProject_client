package pcws.vo;

import java.io.Serializable;

public class MemberVO implements Serializable{
	
	private static final long serialVersionUID = -8682397061418140799L;
	
	private String mem_id;			// 회원 ID
	private String mem_pw;			// 회원 PW
	private String mem_nm;			// 이름
	private String mem_birth;		// 생년월일
	private String mem_gen;			// 성별
	private String mem_email;		// 이메일
	private String mem_tel;			// 전화번호
	private String mem_admin;		// 관리자 여부
	private String mem_wdw;			// 탈퇴 여부
	
	public String getMem_id() {
		return mem_id;
	}



	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}



	public String getMem_pw() {
		return mem_pw;
	}



	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}



	public String getMem_nm() {
		return mem_nm;
	}



	public void setMem_nm(String mem_nm) {
		this.mem_nm = mem_nm;
	}



	public String getMem_birth() {
		return mem_birth;
	}



	public void setMem_birth(String mem_birth) {
		this.mem_birth = mem_birth;
	}



	public String getMem_gen() {
		return mem_gen;
	}



	public void setMem_gen(String mem_gen) {
		this.mem_gen = mem_gen;
	}



	public String getMem_email() {
		return mem_email;
	}



	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}



	public String getMem_tel() {
		return mem_tel;
	}



	public void setMem_tel(String mem_tel) {
		this.mem_tel = mem_tel;
	}



	public String getMem_admin() {
		return mem_admin;
	}



	public void setMem_admin(String mem_admin) {
		this.mem_admin = mem_admin;
	}



	public String getMem_wdw() {
		return mem_wdw;
	}



	public void setMem_wdw(String mem_wdw) {
		this.mem_wdw = mem_wdw;
	}



	

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
