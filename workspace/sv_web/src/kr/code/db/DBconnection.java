package kr.code.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBconnection {
	private static Connection conn;	//DB연결객체 정의
	private DBconnection() {}	//생성자를 통한 객체생성을 막기위해, 싱글톤 구현을 위해 생성자는 감춘다
	
	public static Connection getConnection() throws Exception{	//DB연결 객체반환메서드
		if(conn==null||conn.isClosed()) {	//db연결 없거나, 종료되었을 경우, 다시 맺는다
			getConn();  	//커넥션 연결해라
		}return conn;		//DB연결 객체반환
	}
	
	
	/* 
	 * private static void getConn() throws Exception{ //실제 자바와 DB를 연결하는 메서드
	 * Class.forName("com.mysql.jdbc.Driver"); //DB연결 //db접속경로지정 jdbc:mariadb:// +
	 * ip주소:port/스키마?옵션1&옵션2&......; String
	 * url="jdbc:mysql://localhost:3306/jsp?useUnicode=true"+
	 * "&characterEncoding=UTF-8"; String id="root"; String passwd="051600";
	 * 
	 * conn=DriverManager.getConnection(url,id,passwd); //DB연결 if(conn!=null)
	 * {System.out.println("DB연결됨");} else {System.out.println("DB연결 실패");} }
	 */
	
	//위의 getConn()보다 dbcp 사용한 getConn()이 훨씬 로그인이 빠르게 된다
	private static void getConn() throws Exception{		//실제 자바와 DB를 연결하는 메서드	
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
	
	
	
	