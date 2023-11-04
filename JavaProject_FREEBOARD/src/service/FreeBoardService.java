package service;

import java.util.List;

import dao.FreeBoardDAO;
import vo.FreeBoard;

public class FreeBoardService {
	// 싱글톤 패턴을 만든다.
	private static FreeBoardService instance = null;
	private FreeBoardService() {}
	public static FreeBoardService getInstance() {
		if(instance == null) 
			instance = new FreeBoardService();
		return instance;
	}
	
	public static int loginCount = 0;
	
	// Dao를 부른다
	FreeBoardDAO dao = FreeBoardDAO.getInstance();

	private Object board_no;
	public List<FreeBoard> boardList() {
		return dao.boardList();
	}
	public FreeBoard boardView(int board_no) {
		return dao.boardView(board_no);
	}
	public void boardInsert(List<Object> param) {
		dao.boardInsert(param);
	}
	public void boardDelete(int board_no) {
		dao.boardDelete(board_no);
	}
	public void boardUpdate(List<Object> param) {
		dao.boardUpdate(param);
	}
}