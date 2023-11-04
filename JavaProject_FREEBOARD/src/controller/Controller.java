package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import print.Print;
import service.FreeBoardService;
import util.ScanUtil;
import util.View;
import vo.FreeBoard;

public class Controller extends Print {
	// 세션
	static public Map<String, Object> sessionStorage = new HashMap<>();

	FreeBoardService boardService = FreeBoardService.getInstance();

	public static void main(String[] args) {
		new Controller().start();
	}

	private void start() {
		sessionStorage.put("login", false); // false: 로그인 안됨
		sessionStorage.put("loginInfo", null);
		View view = View.HOME;
		while (true) {
			switch (view) {
			case HOME:
				view = home();
				break;
			case BOARD_LIST:
				view = boardList();
				break;
			case BOARD_DETAIL:
				view = boardView();
				break;
			case BOARD_INSERT:
				view = boardInsert();
				break;
			case BOARD_DELETE:
				view = boardDelete();
				break;
			case BOARD_UPDATE:
				view = boardUpdate();
				break;
			}
		}
	}

	private View boardUpdate() {
		int board_no = (int)sessionStorage.get("board_no");
		
		String name = ScanUtil.nextLine("제목");
		String writer = ScanUtil.nextLine("작성자");
		String content = ScanUtil.nextLine("내용");
		
		List<Object> param = new ArrayList();
		param.add(name);
		param.add(writer);
		param.add(content);
		param.add(board_no);
		
		boardService.boardUpdate(param);
		return View.BOARD_LIST;
	}

	private View boardDelete() {
		
		int board_no = (int)sessionStorage.get("board_no");
		String del = ScanUtil.nextLine(board_no + "게시글을 정말 삭제하시겠습니가?(y/n)");
		if(del.equals("y")) {
			boardService.boardDelete(board_no);
			
		}
		
		return View.BOARD_LIST;
	}

	private View boardInsert() {
		
		String name = ScanUtil.nextLine("제목 작성");
		String writer = ScanUtil.nextLine("작성자");
		String content = ScanUtil.nextLine("내용");
		
		List<Object>param = new ArrayList();
		param.add(name);
		param.add(writer);
		param.add(content);
		
		boardService.boardInsert(param);
		return View.BOARD_LIST;
	}

	private View boardView() {
		int board_no = ScanUtil.nextInt("게시판을 선택하세요.");
		sessionStorage.put("board_no",board_no);
		FreeBoard fb = boardService.boardView(board_no);
		printView(fb);
		int select = ScanUtil.nextInt("메뉴를 선택하세요");
		switch (select) {
		case 1:
			return View.BOARD_UPDATE;
		case 2:
			return View.BOARD_DELETE;
		default :
			return View.BOARD_DETAIL;
		}
	}

	private View boardList() {
		List<FreeBoard> list = boardService.boardList();
		printList(list);
		int select = ScanUtil.nextInt("메뉴를 선택하세요.");
		switch (select) {
		case 1:
			return View.BOARD_DETAIL;
		case 2:
			return View.HOME;
		default :
			return View.BOARD_LIST;
			
		}
	}
	
	private View home() {
		printHome();
		int select = ScanUtil.nextInt("메뉴를 선택하세요.");
		switch (select) {
		case 1:
			return View.BOARD_LIST;
		case 2:
			return View.BOARD_INSERT;
		default :
			return View.HOME;
		}
	}
}