package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.ConvertUtils;
import util.JDBCUtil;
import vo.FreeBoard;

// 데이터베이스로 쿼리를 날려서 결과를 얻는다.
public class FreeBoardDAO {
   // 싱글톤 패턴을 만든다.
   private static FreeBoardDAO instance = null;
   private FreeBoardDAO() {}
   public static FreeBoardDAO getInstance() {
      if(instance == null) 
         instance = new FreeBoardDAO();
      return instance;
   }
   
   // JDBC를 부른다.
   JDBCUtil jdbc = JDBCUtil.getInstance();
   
   public List<FreeBoard> boardList() {   //전체 출력하니까 파라미터 필요없음
      /*
       * 변경해서 가져올거니까 select 파라미터 입력 일일히 다 해준거
       */
      String sql = "SELECT board_no, board_name, board_writer,"
    		  + "TO_CHAR(BOARD_DATE,'YYYY-MM-DD') BOARD_DATE, board_content"
    		  + " FROM FREE_BOARD "
    		  + "WHERE BOARD_DELYN IS NULL "
    		  + "order by board_no desc";
      List<Map<String, Object>> l = jdbc.selectList(sql);
      return ConvertUtils.convertToList(l, FreeBoard.class);
   }
   public FreeBoard boardView(int board_no) {
      String sql = "select board_no, board_name, board_writer, "
            + "          TO_CHAR(BOARD_DATE,'YYYY-MM-DD') BOARD_DATE, board_content "
            + "     from free_board "
            + "     where board_no = ?";
      List l = new ArrayList<>();
      l.add(board_no);
      Map<String, Object>   map = jdbc.selectOne(sql, l);
      return ConvertUtils.convertToVo(map, FreeBoard.class);
   }
   public Object boardInsert(List<Object>param) {
      String sql = "insert into free_board"
            +     "(board_no, board_name, board_writer, board_content)\r\n"
            +     "values ((select max(board_no) from free_board)+1, "
            +     "?, ?, ?)";
      jdbc.update(sql, param);
      return null;
   }
   public void boardDelete(int board_no) {
      String sql = "update free_board\r\n" + 
                "set board_delyn = 'y'\r\n" + 
                "where board_no = ?";
      List<Object> param = new ArrayList<Object>();
      param.add(board_no);
      jdbc.update(sql,param);
   }
   public void boardUpdate(List<Object> param) {
      String sql = "update free_board\r\n" + 
                 "set board_name = ?,\r\n" + 
                 "board_writer = ?,\r\n" + 
                 "board_content = ?\r\n" + 
                 "where board_no = ?";
      jdbc.update(sql,param);
   }
   
   
}