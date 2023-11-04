package print;

import java.util.List;

import vo.FreeBoard;

public class Print {
	

	public void printHome() {
		System.out.println("----------------------------------");
		System.out.println("1. 전체 게시판 조회");
		System.out.println("2. 게시판 신규 등록");
		System.out.println("----------------------------------");
	}
	
	public void printList(List<FreeBoard> l) {
		// 게시판 내용 출력
		System.out.println("----------------------------------");
		System.out.println("게시번호	제목	작성자	작성시간	내용");
		for (FreeBoard freeBoard : l) {
			System.out.println(freeBoard);
//			System.out.println(freeBoard.getBoard_no()+"\t"+freeBoard.getBoard_name()+"\t"+freeBoard.getBoard_writer()+"\t"+freeBoard.getBoard_date()+"\t"+freeBoard.getBoard_content());
		}
		System.out.println("----------------------------------");
		// 선택 메뉴 출력
		System.out.println("1. 상세 페이지 조회");
		System.out.println("2. 홈");
	}
	
	public void printView(FreeBoard view) {
		System.out.println("----------------------------------");
		System.out.println(view);
		System.out.println("----------------------------------");
		System.out.println("1. 게시판 수정");
		System.out.println("2. 게시판 삭제");
	}
}
