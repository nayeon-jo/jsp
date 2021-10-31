package kr.code.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import kr.code.db.DBConnection;
import kr.code.vo.UserInfo;

public class LoginDao {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet res;
	
	// 로그인 처리 메서드 
	public UserInfo loginUser(String sql, Map<String, Object> param) throws Exception{
		
		String userId = param.get("userId").toString();
		String userPw = param.get("userPw").toString();
		
		//커넥션 맺기
		conn = DBConnection.getConnection();
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, userId);
		pstmt.setString(2, userPw);
		
		res = pstmt.executeQuery(); // 쿼리를 실행	
		
		UserInfo user = null;
		
		//DB 조회후 객체가 있을 겨우 
		if(res != null && res.next())  {
			
			user = new UserInfo();
			
			user.setUserId(res.getString("user_id"));
	    	user.setUserName(res.getString("user_name"));
	    	user.setUserGender(res.getString("user_gender"));
		    user.setUserAge(res.getInt("user_age"));
		    user.setUserHobby(res.getString("user_hobby"));
		}
		
		//사용후 닫기 
		DBConnection.closeConnection(conn);
		DBConnection.closeConnection(pstmt, res);
		
		return user;
	}
}
