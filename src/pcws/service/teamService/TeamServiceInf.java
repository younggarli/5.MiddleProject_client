package pcws.service.teamService;

import java.rmi.RemoteException;
import java.util.List;

import pcws.vo.ProjectVO;
import pcws.vo.TeamVO;

public interface TeamServiceInf {
	/**
	 * 프로젝트에 멤버 추가 메서드	 
	 * @param team
	 * @return 추가 성공시 true, 실패시 false
	 * @throws RemoteException
	 */
	public boolean Team_Create(TeamVO team) throws RemoteException;
	
	/**
	 * 프로젝트 정보로 팀 리스트를 가져오는 메서드 
	 * @param mem
	 * @return team 리스트 반환
	 * @throws RemoteException
	 */
	public List<TeamVO> getAllTeam(ProjectVO project) throws RemoteException;
	
	/**
	 * 팀 정보를 삭제하는 메서드
	 * @param team
	 * @return 삭제 성공시 true, 실패시 false
	 * @throws RemoteException
	 */
	public boolean Team_Delete(TeamVO team) throws RemoteException;
}
