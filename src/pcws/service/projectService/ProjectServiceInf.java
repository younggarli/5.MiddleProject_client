package pcws.service.projectService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import pcws.vo.ChathisVO;
import pcws.vo.CheckListVO;
import pcws.vo.FilesVO;
import pcws.vo.MemberVO;
import pcws.vo.ProjectVO;
import pcws.vo.ScheduleVO;


public interface ProjectServiceInf extends Remote{
	/**
	 * 프로젝트 생성(PL)
	 * @param id, pvo
	 * @return 프로젝트 정보
	 * @throws RemoteException
	 */
	public ProjectVO project_Create(String mem_id, ProjectVO pvo) throws RemoteException;
	/**
	 * 유저 아이디로 프로젝트 리스트를 가져옴
	 * @param mem_id
	 * @return 유저의 프로젝트 리스트
	 * @throws RemoteException
	 */
	public List<ProjectVO> chkProjectCount(String mem_id) throws RemoteException;
	
	/**
	 * 프로젝트 내용을 수정
	 * @param pvo
	 * @return 업데이트 성공시 true, 실패시 false
	 * @throws RemoteException
	 */
	public boolean project_update(ProjectVO pvo) throws RemoteException;
	
	/**
	 * 프로젝트 삭제
	 * @param pvo
	 * @return 삭제 성공시 true, 실패시 false
	 * @throws RemoteException
	 */
	public boolean project_delete(ProjectVO pvo) throws RemoteException;
	
	/**
	 * 모든 회원의 아이디, 이름, 전화번호 출력
	 * @return 모든 회원의 정보리스트
	 * @throws RemoteException
	 */
	public List<MemberVO> getAllMember() throws RemoteException;
	
	/**
	 * 이름으로 회원 정보 검색
	 * @param name 회원 이름
	 * @return 검색한 이름과 일치하는 회원리스트
	 * @throws RemoteException
	 */
	public List<MemberVO> searchMem(String name) throws RemoteException;
	
	/**
	 * 프로젝트 생성 시 알람 생성
	 * @param pj_id
	 * @return 생성 성공시 true, 실패시 false
	 * @throws RemoteException
	 */
	public boolean insert_notice(String pj_id) throws RemoteException;
	
	/**
	 * 멤버가 가장 나중에 생성한 프로젝트
	 * @param mem
	 * @return 프로젝트
	 * @throws RemoteException
	 */
	public ProjectVO getProject(MemberVO mem) throws RemoteException;
	
	/**
	 * 모든 프로젝트 리스트 가져오기
	 * @return 모든 프로젝트 리스트
	 * @throws RemoteException
	 */
	public List<ProjectVO> getAllPro() throws RemoteException;
	
	/**
	 * 프로젝트명으로 프로젝트 검색하기
	 * @param proNm 프로젝트 이름
	 * @return 리스트
	 * @throws RemoteException
	 */
	public List<ProjectVO> selectPro(String proNm) throws RemoteException;
	
	/**
	 * 멤버 추가(PL)
	 * @return true이면 성공, false이면 실패
	 * @throws RemoteException
	 */
//	public int project_Mem_Insert() throws RemoteException;
	
	/**
	 * 멤버 삭제(PL)
	 * @param mem_id 삭제할 멤버 아이디
	 * @return true이면 성공, false이면 실패
	 * @throws RemoteException
	 */
//	public boolean project_Mem_Delete(String mem_id) throws RemoteException;
	
	// 업무 추가(PL)
	
	// 업무 수정(PL)
	
	// 멤버 배정 삭제(PL)
	
	// 일정 관리
//	public ScheduleVO commonTask_Administration(String start, String end) throws RemoteException;
	
	// 현황체크
//	public CheckListVO commonTask_Current(String chk_wtr) throws RemoteException;
	
	// 채팅
//	public ChathisVO commonTask_Chatting() throws RemoteException;
	
	// 파일 전송
//	public FilesVO commonTask_File_Transmission(String file_nm) throws RemoteException;
	
}
