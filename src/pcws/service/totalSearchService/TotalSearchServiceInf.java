package pcws.service.totalSearchService;

import java.rmi.Remote;
import java.util.List;

import pcws.vo.MemberVO;
import pcws.vo.ProjectVO;
/**
 * 검색 인터페이스 선언
 * @author YUJISUN
 * @since 2018-04-02
 */
public interface TotalSearchServiceInf extends Remote{
	/**
	 * 이름의 일부또는 전체를 가지고 있는 멤버들을 가져오는 메서드
	 * @param mem_id
	 * @return 멤버 리스트
	 */
	public List<MemberVO> memberSearch(String mem_id);
	
	/**
	 * 프로젝트 이름에서 일부나 전체인 경우의 프로젝트를 가져오는 메서드
	 * @param mem_id
	 * @return 프로젝트 리스트
	 */
	public List<ProjectVO> projectSearch(String mem_id);
}
