package pcws.service.memberService;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import pcws.vo.MemberVO;

public interface MemberServiceInf extends Remote{
	
	/**
	 * 아이디 중복 확인
	 * @param mem_id
	 * @return 중복시 true, 없을시 false
	 */
	public boolean checkMember(String mem_id) throws RemoteException;
	
	/**
	 * 회원가입 메서드
	 * @param memVo
	 * @return 성공시 true, 실패시 false
	 * @throws RemoteException 
	 */
	public boolean insertMember(MemberVO memVo) throws RemoteException;
	
	/**
	 * 아이디 찾기 메서드
	 * @param memVo
	 * @return 성공시 아이디, 실패시 null 반환
	 */
	public String getIdSearchMember(MemberVO memVo) throws RemoteException;
	
	/**
	 * 비밀번호 찾기 메서드
	 * @param memVo
	 * @return 성공시 임시비밀번호, 실패시 null 반환
	 */
	public String getPwSearchMember(MemberVO memVo) throws RemoteException;
	
	/**
	 * 사용자가 입력한 값과 DB에 저장되어 있는 정보와 일치하는지 여부를 판단하는 메서드
	 * @param memVo
	 * @return 일치하면 true, 일치하지 않으면 false를 반환
	 * @throws RemoteException
	 */
	public boolean checkIdSearch(MemberVO memVo) throws RemoteException;
	
	/**
	 * 사용자가 입력한 값과 DB에 저장되어 있는 정보와 일치하는지 여부를 판단하는 메서드
	 * @param memVo
	 * @return 일치하면 true, 일치하지 않으면 false를 반환
	 * @throws RemoteException
	 */
	public boolean checkPwSearch(MemberVO memVo) throws RemoteException;
	
	/**
	 * 비밀번호 찾기를 실행할 때 DB에 임시비밀번호를 저장하기 위한 메서드
	 * @param memVo
	 * @return 성공하면 true, 실패하면 false
	 * @throws RemoteException
	 */
	public boolean updateMemberPw(MemberVO memVo) throws RemoteException;
	
	/**
	 * 로그인 기능
	 * @param mem_id 사용자가 입력한 아이디
	 * @param mem_pw 사용자가 입력한 비밀번호
	 * @return 로그인한 사용자 정보
	 */
	public MemberVO logIn(String mem_id, String mem_pw) throws RemoteException;
	
	/**
	 * 사용자가 입력한 정보를 가지고 member테이블 DB정보와 비교하여 해당되는 member를 반환하기 위한 메서드
	 * @param memberInfo
	 * @return 해당 member의 이름, 아이디, 전화번호 정보
	 * @throws RemoteException
	 */
	public List<MemberVO> searchAllMember(String memberInfo) throws RemoteException;

	/**
	 * 사용자가 입력한 정보를 가지고 member테이블 DB정보와 비교하여 해당되는 member를 반환하기 위한 메서드
	 * @param memberInfo
	 * @return 해당 member의 정보
	 * @throws RemoteException
	 */
	public List<MemberVO> searchGetMember(String memberInfo) throws RemoteException;
	
	/**
	 * 사용자가 탈퇴여부를 바꾸는 메서드
	 * @param memVo
	 * @return 탈퇴성공이면 true 아니면 false
	 */
	public boolean memberOut(MemberVO memVo) throws RemoteException;
	
	/**
	 * 회원정보를 수정하는 메서드
	 * @param memVo
	 * @return 정보수정이면true 실패면 false
	 * @throws RemoteException
	 */
	public boolean updateMember(MemberVO memVo) throws RemoteException;
	
	
}

