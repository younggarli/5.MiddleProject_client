package pcws.service.checkListService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import pcws.vo.CheckListVO;
import pcws.vo.ProjectVO;
/**
 * 체크리스트 인터페이스 선언
 * @author YUJISUN
 * @since 2018-04-02
 */
public interface CheckListServiceInf extends Remote{
	
	/**
	 * 체크리스트 추가
	 * @param chklist
	 * @return 추가 성공시 true, 실패시 false
	 * @throws RemoteException
	 */
	public boolean checkList_Insert(CheckListVO chklist) throws RemoteException;
	
	/**
	 * 업무 삭제
	 * @param chklist
	 * @return 삭제 성공시 true, 실패시 false
	 * @throws RemoteException
	 */
	public boolean checkList_Delete(CheckListVO chklist) throws RemoteException;
	
	/**
	 * 업무에 맞는 체크리스트 가져오기
	 * @param task
	 * @return 업무의 체크리스트
	 * @throws RemoteException
	 */
	public List<CheckListVO> checkList_Allget(String pj_id) throws RemoteException;
	
	/**
	 * 리스트 동기화
	 * @param list
	 * @return 동기화 성공시 true, 실패시 false
	 * @throws RemoteException
	 */
	public boolean checkList_Synchro(List<CheckListVO> list) throws RemoteException;
	
	/**
	 * 체크 여부 변경
	 * @param chk
	 * @return 변경 성공시 true, 실패시 false
	 * @throws RemoteException
	 */
	public boolean checkList_update(CheckListVO chk) throws RemoteException;
	
	/**
	 * 
	 * @param pvo
	 * @return
	 * @throws RemoteException
	 */
	public int getChkList(ProjectVO pvo) throws RemoteException;
	
	/**
	 * 
	 * @param pvo
	 * @return
	 * @throws RemoteException
	 */
	public int getNoNChkList(ProjectVO pvo) throws RemoteException;
}
