package pcws.main;

import java.util.ArrayList;
import java.util.List;

import pcws.vo.MemberVO;
import pcws.vo.ProjectVO;

public class MemberInfo {
	static MemberVO mvo;	// 로그인한 사용자 정보
	static ProjectVO pvo;	// 프로젝트 정보
	static List<ProjectVO> proList = new ArrayList<>();
	static String txtSearch; // 검색문
}
