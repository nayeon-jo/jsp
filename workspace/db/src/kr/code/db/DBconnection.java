package kr.code.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBconnection {
	
	/*
	 * db연결객체 정의
	 */
	private static Connection conn;
	
	//싱글톤 구현을 위해 생성자는 감춘다
	//생성자를 통한 객체생성을 막기위해
	private DBconnection() {}
	
	//db연결 객체반환메서드
	public static Connection getConnection() throws Exception{
		
		//db연결 없거나, 종료되었을 경우, 다시 맺는다
		if(conn==null||conn.isClosed()) {
			getConn();  //커넥션 연결해라
		}
		//db연결 객체반환
		return conn;
	}
	
	//실제 자바와 DB를 연결하는 메서드
	private static void getConn() throws Exception{
		//db접속로직구현
		
		//db연결 
		//4줄만 변경 mariadb->mysql
		Class.forName("org.mariadb.jdbc.Driver");
		//db접속경로지정 jdbc:mariadb:// + ip주소:port/스키마?옵션1&옵션2&......;
		String url="jdbc:mariadb://localhost:13306/jsp?useUnicode=true"+"&characterEncoding=UTF-8";
		
		String id="root";
		String passwd="0516";
		
		
		conn=DriverManager.getConnection(url,id,passwd);
		if(conn!=null) {
			System.out.println("db연결됨");
		}else {
			System.out.println("db연결 실패");
		}	
	}
	
	/**
	 * 커넥션 닫기
	 * @param conn
	 */
	public static void closeConnection(Connection conn){
		try {
			if(conn!=null) {
				conn.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * select 후 클로즈
	 * @param conn
	 * @param pstmt
	 * @param res
	 */
	public static void closeConnection(PreparedStatement pstmt,ResultSet res){
		try {
			if(res!=null) {
				res.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * update/delete 후 클로즈
	 * @param pstmt
	 */
	public static void closeConnection(PreparedStatement pstmt){
		try {
			if(pstmt!=null) {
				pstmt.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
}
