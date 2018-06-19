package pcws.vo;

import java.io.Serializable;

public class YouAndMeVO implements Serializable{
	
	private static final long serialVersionUID = -7974945750099695212L;
	
	private String num; 	// 행 번호
	private String my_id;  	// 내 아이디
	private String frd_id;	// 친구 아이디
	private String name;  	// 친구 이름
	private String tel;  	// 친구 전화번호
	private String email;  	// 친구 이메일
	
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getMy_id() {
		return my_id;
	}
	
	public void setMy_id(String my_id) {
		this.my_id = my_id;
	}
	
	public String getFrd_id() {
		return frd_id;
	}
	
	public void setFrd_id(String frd_id) {
		this.frd_id = frd_id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTel() {
		return tel;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
}
