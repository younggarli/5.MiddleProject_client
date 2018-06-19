package pcws.service.taskService;

import java.rmi.RemoteException;
import java.util.List;

import pcws.vo.TaskVO;
/**
 * 업무 인터페이스 선언
 * @author YUJISUN
 * @since 2018-04-02
 */
public interface TaskServiceInf {
	/**
	 * 업무를 추가하는 메서드
	 * @param task
	 * @return 추가에 성공하면 true, 실패시 false
	 * @throws RemoteException
	 */
	public boolean individualTask_Create(TaskVO task) throws RemoteException;
	
	/**
	 * 등록되어 있는 업무를 수정
	 * @param task
	 * @return 추가에 성공하면 true, 실패시 false
	 * @throws RemoteException
	 */
	public boolean individualTask_Modification(TaskVO task) throws RemoteException;
	
	/**
	 * 등록되어 있는 업무를 삭제
	 * @param task
	 * @return 삭제 성공시 true, 실패시 false
	 * @throws RemoteException
	 */
	public boolean individualTask_Delete(TaskVO task) throws RemoteException;
	
	/**
	 * 업무 리스트를 가져오는 메서드
	 * @param mem_id
	 * @return 업무 리스트
	 * @throws RemoteException
	 */
	public List<TaskVO> individualTask_Read(String mem_id) throws RemoteException;
	
	/**
	 * 특정 업무를 가져오는 메서드
	 * @param task
	 * @return 특정 업무
	 * @throws RemoteException
	 */
	public TaskVO getTask(String task) throws RemoteException;
}