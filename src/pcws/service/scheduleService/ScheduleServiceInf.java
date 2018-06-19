package pcws.service.scheduleService;

import java.rmi.Remote;
import java.rmi.RemoteException;

import pcws.vo.ScheduleVO;

public interface ScheduleServiceInf extends Remote {

	/**
	 * 선택한 날짜와 메모내용을 저장
	 * @param svo 사용자id, 날짜, 메모내용
	 * @return 성공적으로 저장하면 true, 실패하면 false
	 * @throws RemoteException
	 */
	public boolean memoSave(ScheduleVO svo) throws RemoteException;
	
	/**
	 * 선택한 날짜와 메모내용을 삭제
	 * @param svo 사용자id, 날짜, 메모내용
	 * @return 성공적으로 삭제하면 true, 실패하면 false
	 * @throws RemoteException
	 */
	public boolean memoDelete(ScheduleVO svo) throws RemoteException;
	
	/**
	 * 사용자가 선택한 날짜의 메모내용 출력
	 * @param id 사용자 아이디
	 * @param date 선택한 날짜
	 * @return 메모내용
	 * @throws RemoteException
	 */
	public String showMemo(String id, String date) throws RemoteException;
}
