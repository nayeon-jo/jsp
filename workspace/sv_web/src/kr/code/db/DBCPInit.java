package kr.code.db;

import java.sql.DriverManager;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/*서블릿 기반 웹 개발할 때 DBCP는 이 코드 응용하면됨!*/



//name은 서블릿 이름, asyncSupported 비동기 처리, loadOnStartup 프로그램 실행시 자동실행여부(0만 아니면 됨), urlPatterns는 dbcp접근주소
@WebServlet(name="dbcpinit", asyncSupported=true, loadOnStartup=1, urlPatterns= {"/dbinit"})

public class DBCPInit extends HttpServlet{
	private static final long serialVersionUID=1L;	//데이터 직렬화 시 같은 데이터를 검증하기위한 ID값(나중에 내부에서 재생되기때문에 default값만 주면됨)
	
	public DBCPInit() {		//생성자
		loadDBDriver();
		initDBConnection();
	}
	
	private void loadDBDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");		//mysql드라이버 호출
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void initDBConnection() {
		try {
			String url="jdbc:mysql://localhost:3306/jsp?useUnicode=true"+"&characterEncoding=UTF-8";
			String id="root";	
			String passwd="051600";
			
			//DB 커넥션을 생성할 공장 만들기
			ConnectionFactory connFactory=new DriverManagerConnectionFactory(url,id,passwd);
			//커넥션을 보관할 pool을 만들 공장, 커넥션을 관리하게됨, pool에서 커넥션을 생성하기때문에 커넥션 만들 공장을 넣어줌
			PoolableConnectionFactory poolFactory=new PoolableConnectionFactory(connFactory,null);
			
			poolFactory.setValidationQuery("select 1");		//커넥션이 살아있는지 테스트로 날려볼 sql 지정 (sql 구문은 DB마다 조금씩 다르다.)
			
			GenericObjectPoolConfig poolConfig=new GenericObjectPoolConfig();	//풀의 설정을 제어
			
			poolConfig.setTimeBetweenEvictionRunsMillis(60*1000);		//커넥션 유효 검사주기설정 -단위는 1/100초  //1분 주기
			poolConfig.setTestWhileIdle(true);		//유효테스트를 할지말지 결정
			poolConfig.setMinIdle(10);		//최소 커넥션 개수
			poolConfig.setMaxTotal(100);		//최대 커넥션 개수
			
			GenericObjectPool<PoolableConnection> connectionPool=new GenericObjectPool<>(poolFactory,poolConfig);	//커넥션풀객체 생성
			poolFactory.setPool(connectionPool);	//PoolableConnectionFactory에도 커넥션 풀을 연결
			
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");	//2차로 커넥션풀을 위한 드라이버를 설정
			
			PoolingDriver driver=(PoolingDriver)DriverManager.getDriver("jdbc:apache:commons:dbcp:");		//커넥션풀 등록
			
			driver.registerPool("dbpool", connectionPool);		//풀 드라이버에 커넥션 등록 
			
			System.out.println("---------------------------");	
			System.out.println("dbcp Connection Pool이 상주되었습니다");	
			System.out.println("---------------------------");	
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
