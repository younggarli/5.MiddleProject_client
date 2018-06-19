package pcws.service.friendService;

import java.rmi.RemoteException;
import java.util.List;

import pcws.vo.FriendVO;
import pcws.vo.YouAndMeVO;

/**
 * 사용자가 자신의 멤버들을 추가, 검색, 삭제할 수 있도록 해주는 인터페이스 기능
 * @author UmSoHyun
 * @since 2018-04-02
 */
public interface FriendServiceInf {
	
	/**
	 * 멤버의 친구 목록을 전부 가져오기 위한 메서드
	 * @param frd_mem_id
	 * @return 친구의 정보 List (id, name, tel, e-mail)
	 * @throws RemoteException
	 */
	public List<YouAndMeVO> getAllMember(String frd_mem_id) throws RemoteException;
	
	/**
	 * 검색할 멤버의 이름과 전화번호 정보를 가져오기 위한 메서드
	 * @param frd_mem_id
	 * @return 친구의 정보 List (name, tel)
	 * @throws RemoteException
	 */
	public List<FriendVO> getFriendSearch (String frd_mem_id) throws RemoteException;
	
	/**
	 * 친구 id와 자신의 id를 매개변수로 받아 검색하여 멤버 목록에 추가할 친구의 정보를  DB에서 가져와 List에 저장하기 위한 메서드
	 * @param frd_id
	 * @return 멤버 목록에 친구 추가를 성공하면 true, 실패하면 false를 반환
	 * @throws RemoteException
	 */
	public boolean getFriend_Add (String frd_mem_id, String frd_id) throws RemoteException;
	
	/**
	 * 친구 id와 자신의 id를 매개변수로 받아 List 목록에서 삭제하기 위한 메서드
	 * @param frd_mem_id
	 * @param frd_id
	 * @return 멤버 목록에 멤버 삭제를 성공하면 true, 실패하면 false를 반환
	 * @throws RemoteException
	 */
	public boolean getFriend_Delete (String frd_mem_id, String frd_id) throws RemoteException;
	
	
}
