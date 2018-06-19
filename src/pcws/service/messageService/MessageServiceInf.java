package pcws.service.messageService;

import java.io.File;
import java.io.FileOutputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

import pcws.vo.MessageVO;

public interface MessageServiceInf extends Remote {

	/**
	 * 쪽지함
	 * @param id 사용자 아이디
	 * @return 쪽지정보 리스트
	 * @throws RemoteException
	 */
	public List<MessageVO> getAllmsg(String id) throws RemoteException;

	/**
	 * 쪽지 보내기
	 * @param 쪽지번호, 작성자o, 내용o, 수신자o, 보낸날짜o
	 * @return 성공적으로 보내면 true, 그렇지 않으면 false
	 * @throws RemoteException
	 */
	public boolean sendMsg(HashMap<String, String> msgMap) throws RemoteException;
	
	public MessageVO setFiles(MessageVO msgFile) throws RemoteException;
	public MessageVO getFile(String fileNm) throws RemoteException;
	public String getMsgId(HashMap<String, String> msgMap) throws RemoteException;
	
	/**
	 * 메세지 생성시 알람 생성
	 * @param msg_id
	 * @return 생성 성공시 true, 실패시 false
	 * @throws RemoteException
	 */
	public boolean insert_notice(String msg_id) throws RemoteException;
	
}
