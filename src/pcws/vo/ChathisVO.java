package pcws.vo;

import java.io.Serializable;

public class ChathisVO implements Serializable{

	private static final long serialVersionUID = 6334260377279439691L;

	private String chat_mem_id;			// 회원 ID
	private String chat_tm_id;			// 팀 ID
	private String chat_cont;			// 내용
	
	public String getChat_mem_id() {
		return chat_mem_id;
	}
	
	public void setChat_mem_id(String chat_mem_id) {
		this.chat_mem_id = chat_mem_id;
	}
	
	public String getChat_tm_id() {
		return chat_tm_id;
	}
	
	public void setChat_tm_id(String chat_tm_id) {
		this.chat_tm_id = chat_tm_id;
	}
	
	public String getChat_cont() {
		return chat_cont;
	}
	
	public void setChat_cont(String chat_cont) {
		this.chat_cont = chat_cont;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
