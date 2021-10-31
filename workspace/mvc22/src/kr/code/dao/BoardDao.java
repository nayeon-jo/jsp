package kr.code.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.code.db.DBConnection;
import kr.code.vo.BoardVO;

public class BoardDao {

	private Connection conn;  // 커넥션
	private PreparedStatement pstmt; // SQL 문서전송 객체
	private ResultSet res; // 결과 객체
	
	
	public int insertBoard(String sql, BoardVO vo) throws Exception {
		conn = DBConnection.getConnection(); // 커넥션 가져오기 
		pstmt = conn.prepareStatement(sql);
		
		int queryIndex = 1;
		int result = 0;
		
		pstmt.setString(queryIndex++, vo.getBoardTitle());
		pstmt.setString(queryIndex++, vo.getBoardContents());
		pstmt.setString(queryIndex++, vo.getBoardAuthor());
		pstmt.setString(queryIndex++, vo.getOriginFileName());
		pstmt.setString(queryIndex++, vo.getStoredFileName());
		pstmt.setString(queryIndex++, vo.getFilePath());
		
		//쿼리 실행 
		result = pstmt.executeUpdate();
		
		/**
		 * 데이터베이스를 삽입/삭제/수정할 시  Auto Commit 모드를 true(자동) 로 하면 자동으로 실제 DB 에 반영된다.
		 * 그러나 해당기능은 실수하거나 오류가 날수 있기 때문에 설정 시 수동으로 하게 끔 false 처리 해야한다.
		 * 쿼리가 정상 실행을 한 후에 수동 으로 commit  하게 해야 개발자의 실수로 인한 불상사를 막을 수 있다.
		 * DB 는 실행 후 바로 반영되는 것이 아니라 메모리에 적용되었다가 commit 기능을 해야 실제 테이블에 반영된다.
		 */
		conn.commit();
		
		DBConnection.closeConnection(conn);
		DBConnection.closeConnection(pstmt);
		
		return result;
	}
	
	
	public List<BoardVO> getBoardList(String sql) throws Exception  {
		//결과를 담을 리스트
		List<BoardVO> list = new ArrayList<>();
		
		conn = DBConnection.getConnection(); // 커넥션 가져오기 
		//sql 문서를 적은 객체받기 
		pstmt = conn.prepareStatement(sql);
		//쿼리 실행하여 결과 받아오기 
		res = pstmt.executeQuery();

		//쿼리 결과가 있을때까지 루프 돌린다.
		while(res.next())  {
			
			BoardVO vo = new BoardVO();
			
			vo.setBoardId(res.getInt("board_id"));
			vo.setBoardTitle(res.getString("board_title"));
			vo.setBoardAuthor(res.getString("board_author"));
			vo.setCreateDate(res.getString("create_date"));
			vo.setBoardCount(res.getInt("board_count"));
			list.add(vo);
		}
		
		
		DBConnection.closeConnection(conn);
		DBConnection.closeConnection(pstmt, res);
		
		return list;
	}
	
	
	public  BoardVO getBoard(String sql, int boardId) throws Exception  {
		//결과를 담을 리스트
		BoardVO vo  = null;
		
		conn = DBConnection.getConnection(); // 커넥션 가져오기 
		//sql 문서를 적은 객체받기 
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boardId);
		//쿼리 실행하여 결과 받아오기 
		res = pstmt.executeQuery();

		//쿼리 결과가 있을때까지 루프 돌린다.
		if(res.next())  {
			vo = new BoardVO();
			vo.setBoardId(res.getInt("board_id"));
			vo.setBoardTitle(res.getString("board_title"));
			vo.setBoardContents(res.getString("board_contents"));
			vo.setBoardAuthor(res.getString("board_author"));
			vo.setCreateDate(res.getString("create_date"));
			vo.setBoardCount(res.getInt("board_count"));
			vo.setOriginFileName(res.getString("origin_filename"));
			vo.setStoredFileName(res.getString("stored_filename"));
			vo.setFilePath(res.getString("file_path"));
		}
		
		DBConnection.closeConnection(conn);
		DBConnection.closeConnection(pstmt, res);
		
		return vo;
	}
	
	
	
	
	public int updateBoard(String sql, BoardVO vo) throws Exception {
		conn = DBConnection.getConnection(); // 커넥션 가져오기 
		pstmt = conn.prepareStatement(sql);
		
		int queryIndex = 1;
		int result = 0;
		
		pstmt.setString(queryIndex++, vo.getBoardTitle());
		pstmt.setString(queryIndex++, vo.getBoardContents());
		pstmt.setString(queryIndex++, vo.getOriginFileName());
		pstmt.setString(queryIndex++, vo.getStoredFileName());
		pstmt.setInt(queryIndex++, vo.getBoardId());
		
		//쿼리 실행 
		result = pstmt.executeUpdate();
	
		//수동커밋 
		conn.commit();
		
		DBConnection.closeConnection(conn);
		DBConnection.closeConnection(pstmt);
		
		return result;
	}
	
	public int deleteBoard(String sql, int boardId) throws Exception {
	
		conn = DBConnection.getConnection(); // 커넥션 가져오기 
		pstmt = conn.prepareStatement(sql);
		
		int queryIndex = 1;
		int result = 0;
		
		pstmt.setInt(queryIndex++, boardId);
		
		//쿼리 실행 
		result = pstmt.executeUpdate();
	
		//수동커밋 
		conn.commit();
		
		DBConnection.closeConnection(conn);
		DBConnection.closeConnection(pstmt);
		
		return result;
	}
	
}
