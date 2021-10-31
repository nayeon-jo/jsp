package kr.code.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {
	
	/*
	 * DB 연결 객체 정의
	 */
	private static Connection conn;
	
	//싱클톤 구현을 위해서 생성자는 감춘다.
	//생성자를 통한 객체 생성을 막기 위해...
	private DBConnection(){}
	
	//DB연결 객체 반환 메서드
	public static Connection getConnection() throws Exception {
		
		//DB 연결이 없거나, 종료되었을 경우, 다시 맺는다.
		if(conn == null || conn.isClosed()) {
			getConn(); // 커넥션 연결해라.
		}		
		//DB 연결 객체 반환 
		return conn;
	}
	
	//실제 java 와 DB를 연결하는 메서드
//	private static void getConn() throws Exception {
//		//DB접속 로직 구현
//		//DB 연결 드라이버 로드
//		Class.forName("org.mariadb.jdbc.Driver");
//		//DB 접속 경로 지정 jdbc:mariadb:// + ip:port/스키마?옵션1&옵션2&.....;
//		String url = "jdbc:mariadb://localhost:13306/jsp?useUnicode=true" +
//		                   "&characterEncoding=UTF-8";
//		
//		String id = "root";
//		String passwd = "1234"; // 여러분이 등록한 패스워드 써주세요.
//		
//		//DB 연결
//		conn = DriverManager.getConnection(url, id, passwd);
//		
//		if(conn != null) {
//			System.out.println("데이터베이스가 연결되었습니다.");
//			
//		}else {
//			System.out.println("DB 연결이 실패하였습니다.");
//	
//			}
//	}
//	
	
	private static void getConn() throws Exception {
		//커넥션 연결을 위한 driver명 쓰기
		//마지막 이름은 dbcp 생성할떄 register 에 썼던 이름 그대로 쓴다. 
		String driver = "jdbc:apache:commons:dbcp:dbpool";
		//driver 명으로 커넥션 가져오기 
		// 커넥션을 pool에서 가져오도록 된다.
		conn = DriverManager.getConnection(driver);
		//update 나 delete 실수 방지를 위해서 autoCommit 을 끈다
		conn.setAutoCommit(false);
	}
	
	/**
	 * 커넥션 닫기
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		
		try {
			if(conn != null) {
				conn.close();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * select 후 클로즈 
	 * @param conn
	 * @param pstmt
	 * @param res
	 */
	public static void closeConnection( PreparedStatement pstmt, ResultSet res) {
		
		try {
			
			if(res != null) {
				res.close();
			}
			
			if(pstmt != null) {
				pstmt.close();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 보통 update / delete 후 클로즈
	 * @param pstmt
	 */
	public static void closeConnection(PreparedStatement pstmt) {
		
		try {
						
			if(pstmt != null) {
				pstmt.close();
			}			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
