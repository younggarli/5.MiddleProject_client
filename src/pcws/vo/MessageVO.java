package pcws.vo;

import java.io.Serializable;

public class MessageVO implements Serializable{

	private static final long serialVersionUID = 6167351108367595189L;
	/** MSG_ID */
	private String msg_id;
	
	/** MSG_WT */
	private String msg_wt;
	
	/** MSG_CONT */
	private String msg_cont;
	
	/** MSG_RECT */
	private String msg_rect;
	
	/** MSG_DATE */
	private String msg_date;
	
	/** MSG_FILENM */
	private String msg_filenm;
	
	/** MSG_FILEPATH */
	private String msg_filepath;
	
	private byte[] fileData;
	
	/** MSG_CHECK */
	private String msg_check;
	
	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	private String num;	// 행 번호
	
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
	
	/**
	 * @return MSG_ID
	 */
	public String getMsg_id(){
	    return msg_id;
	}

	/**
	 * @param msg_id MSG_ID
	 */
	public void setMsg_id(String msg_id){
	    this.msg_id = msg_id;
	}
	
	/**
	 * @return MSG_WT
	 */
	public String getMsg_wt(){
	    return msg_wt;
	}

	/**
	 * @param msg_wt MSG_WT
	 */
	public void setMsg_wt(String msg_wt){
	    this.msg_wt = msg_wt;
	}
	
	/**
	 * @return MSG_CONT
	 */
	public String getMsg_cont(){
	    return msg_cont;
	}

	/**
	 * @param msg_cont MSG_CONT
	 */
	public void setMsg_cont(String msg_cont){
	    this.msg_cont = msg_cont;
	}
	
	/**
	 * @return MSG_RECT
	 */
	public String getMsg_rect(){
	    return msg_rect;
	}

	/**
	 * @param msg_rect MSG_RECT
	 */
	public void setMsg_rect(String msg_rect){
	    this.msg_rect = msg_rect;
	}
	
	/**
	 * @return MSG_DATE
	 */
	public String getMsg_date(){
	    return msg_date;
	}

	/**
	 * @param msg_date MSG_DATE
	 */
	public void setMsg_date(String msg_date){
	    this.msg_date = msg_date;
	}
	
	/**
	 * @return MSG_FILENM
	 */
	public String getMsg_filenm(){
	    return msg_filenm;
	}

	/**
	 * @param msg_filenm MSG_FILENM
	 */
	public void setMsg_filenm(String msg_filenm){
	    this.msg_filenm = msg_filenm;
	}
	
	/**
	 * @return MSG_FILEPATH
	 */
	public String getMsg_filepath(){
	    return msg_filepath;
	}

	/**
	 * @param msg_filepath MSG_FILEPATH
	 */
	public void setMsg_filepath(String msg_filepath){
	    this.msg_filepath = msg_filepath;
	}
	
	/**
	 * @return MSG_CHECK
	 */
	public String getMsg_check(){
	    return msg_check;
	}

	/**
	 * @param msg_check MSG_CHECK
	 */
	public void setMsg_check(String msg_check){
	    this.msg_check = msg_check;
	}
	
	
}
