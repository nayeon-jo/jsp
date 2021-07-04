package kr.code.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class dbtestmain {
	public static void main(String[] args) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet res=null;
		
		try {
			conn=DBconnection.getConnection();
			if(conn!=null) {
				StringBuilder sql=new StringBuilder();  
				//sql쓰기
				sql.append("select*from std_info");   
				//db에 보낼 문서 만들기
				pstmt=conn.prepareStatement(sql.toString());
				//문서를 보내고 결과받기
				res=pstmt.executeQuery();
				
				//결과가 여러개일수있으니
				while(res.next()) {
					System.out.print("학번:"+res.getString("std_num")+"\t");
					System.out.print("이름:"+res.getString("std_name")+"\t");
					System.out.print("나이:"+res.getInt("std_age")+"\t");
					System.out.print("성별:"+res.getString("std_gender")+"\t");
					System.out.println("학년:"+res.getInt("std_grade"));
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBconnection.closeConnection(pstmt,res);
			DBconnection.closeConnection(conn);
		}
	}
}
