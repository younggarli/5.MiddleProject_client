package pcws.service.noticeService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import pcws.main.MemberInfo;
import pcws.vo.NoTaPrMeVO;
/**
 * 사용자가 실시간으로 알림을 확인할 수 있도록 해주는 인터페이스 기능
 * @author UmSoHyun
 * @since 2018-04-02
 */
public interface NoticeServiceInf extends Remote{
	
	/**
	 * Notice 테이블에서 업무 번호로 알람 List를 가져오기 위한 메서드
	 * @param mem_id
	 * @return 알람 List
	 * @throws RemoteException
	 */
	public List<NoTaPrMeVO> getAlarm_Task_Dday(String mem_id) throws RemoteException;
	
	/**
	 * Notice 테이블에서 프로젝트 id로 프로젝트 List를 가져오기 위한 메서드
	 * @param mem_id
	 * @return 프로젝트 List
	 * @throws RemoteException
	 */
	public List<NoTaPrMeVO> getAlarm_NewProject(String mem_id) throws RemoteException;
	
	/**
	 * Notice 테이블에서 쪽지 id로 쪽지 List를 가져오기 위한 메서드
	 * @param mem_id
	 * @return 쪽지 List
	 * @throws RemoteExcepiton
	 */
	public List<NoTaPrMeVO> getAlarm_NewMessage(String mem_id) throws RemoteException;

	/**
	 * 알림 번호로 Notice 테이블의 List를 삭제하기 위한 메서드
	 * @param ntc_num
	 * @return DB에서 작업이 성공하면 true, 실패하면 false
	 * @throws RemoteException
	 */
	public boolean Del_Alarm_Task(int ntc_num) throws RemoteException;
	
	/**
	 * 프로젝트 id로 Notice 테이블의 List를 삭제하기 위한 메서드
	 * @param pj_id
	 * @return DB에서 작업이 성공하면 true, 실패하면 false 
	 * @throws RemoteException
	 */
	public boolean Del_Alarm_Project(int ntc_num) throws RemoteException;
	
	/**
	 * 쪽지 id로 Notice 테이블의 List를 삭제하기 위한 메서드
	 * @param msg_id
	 * @return DB에서 작업이 성공하면 true, 실패하면 false
	 * @throws RemoteException
	 */
	public boolean Del_Alarm_Message(int ntc_num) throws RemoteException;

}
