package pcws.vo;

import java.io.Serializable;

public class CheckListVO implements Serializable{

	private static final long serialVersionUID = -8993753509159614780L;
	
	private String chk_list_id;		// 체크리스트ID
	private String chk_task_cont;	// 체크리스트 내용
	private String chk_wtr;			// 체크여부
	private Integer chk_task_num;	// 업무번호
	private String chk_task_mem;	// 배정 멤버

	/**
	 * @return CHK_LIST_ID
	 */
	public String getChk_list_id(){
	    return chk_list_id;
	}

	/**
	 * @param chk_list_id CHK_LIST_ID
	 */
	public void setChk_list_id(String chk_list_id){
	    this.chk_list_id = chk_list_id;
	}
	
	/**
	 * @return CHK_TASK_CONT
	 */
	public String getChk_task_cont(){
	    return chk_task_cont;
	}

	/**
	 * @param chk_task_cont CHK_TASK_CONT
	 */
	public void setChk_task_cont(String chk_task_cont){
	    this.chk_task_cont = chk_task_cont;
	}
	
	/**
	 * @return CHK_WTR
	 */
	public String getChk_wtr(){
	    return chk_wtr;
	}

	/**
	 * @param chk_wtr CHK_WTR
	 */
	public void setChk_wtr(String chk_wtr){
	    this.chk_wtr = chk_wtr;
	}
	
	/**
	 * @return CHK_TASK_NUM
	 */
	public Integer getChk_task_num(){
	    return chk_task_num;
	}

	/**
	 * @param chk_task_num CHK_TASK_NUM
	 */
	public void setChk_task_num(Integer chk_task_num){
	    this.chk_task_num = chk_task_num;
	}
	
	/**
	 * @return CHK_TASK_MEM
	 */
	public String getChk_task_mem(){
	    return chk_task_mem;
	}

	/**
	 * @param chk_task_mem CHK_TASK_MEM
	 */
	public void setChk_task_mem(String chk_task_mem){
	    this.chk_task_mem = chk_task_mem;
	}
	
	
	

}
