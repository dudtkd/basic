package vo;

public class FreeBoard {
	int board_no;
	String board_name;
	String board_writer;
	String board_date;
	String board_content;
	String board_delyn;
	
	
	
	@Override
	public String toString() {
		return board_no + "\t" + board_name + "\t" + board_writer
				+ "\t" + board_date + "\t" + board_content  ;
	}

	
	
	public int getBoard_no() {
		return board_no;
	}



	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}



	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_delyn() {
		return board_delyn;
	}
	public void setBoard_delyn(String board_delyn) {
		this.board_delyn = board_delyn;
	}
	
	
}
