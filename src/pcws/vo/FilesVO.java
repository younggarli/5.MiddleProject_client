package pcws.vo;

import java.io.Serializable;

public class FilesVO implements Serializable{

	private static final long serialVersionUID = -7280341780385285563L;

	private String file_mem_id;			// 회원 ID
	private String file_wt;				// 작성자
	private String file_nm;				// 파일명
	private String file_path;			// 파일경로
	
	public String getFile_mem_id() {
		return file_mem_id;
	}
	
	public void setFile_mem_id(String file_mem_id) {
		this.file_mem_id = file_mem_id;
	}
	
	public String getFile_wt() {
		return file_wt;
	}
	
	public void setFile_wt(String file_wt) {
		this.file_wt = file_wt;
	}
	
	public String getFile_nm() {
		return file_nm;
	}
	
	public void setFile_nm(String file_nm) {
		this.file_nm = file_nm;
	}
	
	public String getFile_path() {
		return file_path;
	}
	
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
