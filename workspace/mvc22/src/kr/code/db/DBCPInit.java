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


/*
 * name = 서블릿 이름
 * asyncSupported 비동기 처리 
 * loadOnStartup  프로그램 실행시 자동실행 여부  0 만 아니면됨.
 * urlPatterns = dbcp 접근 주소 
 */
@WebServlet(name="dbcpInit", asyncSupported = true,
             loadOnStartup = 1, urlPatterns = {"/dbinit"}) 
public class DBCPInit extends HttpServlet{
	//데이터 직렬화 시 같은 데이터를 검증하기위한 ID 값
	// 나중에 내부에서 재생성되기떄문에 default 값만 부여하면됨.
	private static final long serialVersionUID = 1L;
	
	//생성자
	public DBCPInit() {
		loadDBDriver();
		initDBConnection();
	}
	
	private void loadDBDriver() {
		try {
			//마리아 DB 드라이버 호출 
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initDBConnection () {
		
		try {
		
			String url = "jdbc:mariadb://localhost:13306/jsp?useUnicode=true" +
	                   "&characterEncoding=UTF-8";
			String id = "root";
			String passwd = "1234"; // 여러분이 등록한 패스워드 써주세요.
			
			//DB 커넥션을 생성할 공장 만들기 
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(url, id, passwd);
			//커넥션을 보관할 pool 을 만들 공장
			//커넥션을 관리하게됨.
			//pool에서 커넥션을 생성하기 때문에 커넥션 만들 공장을 넣어줌 
			PoolableConnectionFactory poolFactory = new PoolableConnectionFactory(connFactory, null);
			
			//커넥션이 살아있는지 테스트로 날려볼 sql 지정. sql 구문은 DB마다 조금씩 다르다.
			poolFactory.setValidationQuery("select 1");
			
			//풀의 설정을 제어 
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			
			//커넥션 유효 검사주기설정 - 단위는  1/100 초 
			poolConfig.setTimeBetweenEvictionRunsMillis(60*1000); // 1분주기
			//유효테스트를 할지말지 결정
			poolConfig.setTestWhileIdle(true);
			//최소 커넥션 개수
			poolConfig.setMinIdle(10);
			//최대 커넥션 개수
			poolConfig.setMaxTotal(100);
			
			//커넥션풀객체 생성
			GenericObjectPool<PoolableConnection> connectionPool 
			                             = new GenericObjectPool<>(poolFactory, poolConfig);
			
			//PoolabeConnectionFactory에도 커넥션 풀을 연결
			poolFactory.setPool(connectionPool);
			
			//2차로..커넥션풀을 위한 드라이버 
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			
			//커넥션풀 등록
			PoolingDriver driver = (PoolingDriver)DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			//풀 드라이버에 커넥션 등록 
			driver.registerPool("dbpool", connectionPool);
			
			
			System.out.println(  "----------------------------------------------------------"); 
			System.out.println("dbcp Connection Pool이 상주 되었습니다.");
			System.out.println("------------------------------------------------------------");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
