package kr.code.service;

import java.util.List;

import kr.code.dao.BoardDao;
import kr.code.vo.BoardVO;

public class BoardService {

	private BoardDao dao;
	
	public BoardService() {
		dao = new BoardDao();
	}
	
	public int insertBoard(BoardVO vo) throws Exception{
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("INSERT INTO board_info(board_title, board_contents, board_author, origin_filename, stored_filename, file_path) ");
		sb.append("  VALUES(?, ?, ?, ?, ?, ?) ");
		
		return dao.insertBoard(sb.toString(), vo);
	}
	
	public List<BoardVO> getBoardList() throws Exception {
		List<BoardVO> list =  null;
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT board_id, \n");
		sb.append("       board_title, \n");
		sb.append("       board_author, \n");
		sb.append("       DATE_FORMAT(create_date, '%Y-%m-%d %H:%i:%s') AS create_date, \n");
		sb.append("        board_count \n");
		sb.append("FROM board_info \n");
		sb.append("ORDER BY board_id DESC \n");
		
		list = dao.getBoardList(sb.toString());
		
		return list;
	}
	
	
	public BoardVO getReadBoard(int boardId) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("SELECT board_id, \n");
		sb.append("       board_title, \n");
		sb.append("       board_contents, \n");
		sb.append("       board_author, \n");
		sb.append("       stored_filename, \n");
		sb.append("       origin_filename, \n");
		sb.append("       file_path, \n");
		sb.append("       DATE_FORMAT(create_date, '%Y-%m-%d %H:%i:%s') AS create_date, \n");
		sb.append("        board_count \n");
		sb.append("FROM board_info \n");
		sb.append("WHERE board_id = ? ");
		
		BoardVO vo = dao.getBoard(sb.toString(), boardId);
		
		return vo;
	}
	
	
	
	public int updateBoard(BoardVO vo) throws Exception{
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("UPDATE board_info");
		sb.append(" set board_title = ?, \n ");
		sb.append("     board_contents = ?, \n ");
		sb.append("     stored_filename = ?, \n ");
		sb.append("     origin_filename = ? \n ");
		sb.append("WHERE board_id = ? ");
		return dao.updateBoard(sb.toString(), vo);
	}
	
	
	
public int deleteBoard(int boardId) throws Exception{
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("delete from  board_info WHERE board_id = ?");
		return dao.deleteBoard(sb.toString(), boardId);
	}
}
