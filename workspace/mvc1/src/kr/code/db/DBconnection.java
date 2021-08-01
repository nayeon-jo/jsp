package kr.code.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBconnection {
	private static Connection conn;	//DB연결객체 정의
	private DBconnection() {}	//생성자를 통한 객체생성을 막기위해, 싱글톤 구현을 위해 생성자는 감춘다
	
	public static Connection getConnection() throws Exception{	//DB연결 객체반환메서드
		if(conn==null||conn.isClosed()) {	//DB 연결 없거나, 종료되었을 경우, 다시 맺는다
			getConn();  	//커넥션 연결해라
		}return conn;		//DB연결 객체반환
	}
	
	private static void getConn() throws Exception{		
		String driver="jdbc:apache:commons:dbcp:dbpool";	//커넥션 연결을 위한 driver명 쓰기, 마지막 이름은 dbcp 생성할때 register에 썼던 이름 그대로 쓴다
		conn=DriverManager.getConnection(driver);	//driver명으로 커넥션 가져오기, 커넥션을 pool에서 가져오도록 된다	
		conn.setAutoCommit(false);		//update나 delete 실수 방지를 위해 AutoCommit을 끈다
	}
	
	
	public static void closeConnection(Connection conn){	//커넥션 닫기
		try {
			if(conn!=null) {conn.close();}
		}catch(Exception e) {e.printStackTrace();}
	}
	
	public static void closeConnection(PreparedStatement pstmt,ResultSet res){ 	//select 후 클로즈
		try {
			if(res!=null) {res.close();}
			if(pstmt!=null) {pstmt.close();}
		}catch(Exception e) {e.printStackTrace();}
	}
	
	public static void closeConnection(PreparedStatement pstmt){	//update/delete 후 클로즈
		try {
			if(pstmt!=null) {pstmt.close();}
		}catch(Exception e) {e.printStackTrace();}
	}
}
	
	
	
	