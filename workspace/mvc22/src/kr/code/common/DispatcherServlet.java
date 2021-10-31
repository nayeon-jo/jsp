package kr.code.common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * name = 서블릿 이름
 * initParams = 서블릿 등록시 제공하는 데이터
 * 
 * @WebInitParam 제공되는 데이터 정의  {name = 별칭(key 가 된다)  , value 파라미터 값}

 * loadOnStartup = 프로그램 실행 시 자동 실행(호출)  > 0 만 아니면 자동실행 요청
 * urlPatterns = 해당  패턴을 지닌 요청일 경우 서블릿으로 진입한다.( 요청경로가 do 로 끝나면 서블릿을 실행하게된다.)
 */
@WebServlet(name="dispathcerServlet",
			initParams = {@WebInitParam(name="propertiesPath", value="WEB-INF/conf/command.properties")},
			loadOnStartup = 1,
			urlPatterns = {"*.do"})
public class DispatcherServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//요청경로와 실행할 객체를 저장할 맵을 지정한다.
    private Map<String, Object> commonMap = new HashMap<>();

    /**
     * 서블릿이 최초 실행될 때 1회만 실행된다.
     * 최초 실행 이후 다시 호출되어도 init 은 실행되지 않음.
     * 보통 서블릿 실행 시 필요한 기본정보 설정 시 사용 
     */
    @Override
	public void init(ServletConfig config) throws ServletException {

    	//해당 어플리케이션의 기본경로를 가져온다
    	// ex) http://localhost:9090/mvc2  
    	String defaultPath = config.getServletContext().getRealPath("/");
    	
    	//프로퍼티 파일 경로
    	String propertiesPath = config.getInitParameter("propertiesPath");
    	//전체 경로
    	String fullPath = defaultPath + propertiesPath;
    	
    	//파일을 읽기 위한 스트림 선언
    	FileInputStream is = null;
    	//보조스트림
    	BufferedInputStream buf = null;
    	
    	try {
    		
    		is = new FileInputStream(fullPath);
    		buf = new BufferedInputStream(is);
    		
    		//프러퍼티 파일을 담을 수 있는 객체가 존재
    		//Map 처럼 key value 로 이루어짐
    		Properties p = new Properties();
    		//파일을 읽어서 객체화 해라 
    		p.load(buf);
    		
    		//iterator 이용하여 루프 돌리기 
    		Iterator iter = p.keySet().iterator();
    		
    		//꺼낼 값이 있을 때까지 반복한다
    		while(iter.hasNext()) {
    			
    			String key  = (String)iter.next();
    			// key 에 해당하는 값을 가져온다.
    			String classPath = p.getProperty(key);
    			/**
    			 * 자바에 리플렉션이란 기능이 있다.
    			 * 리플렉션 기능은 자바를 객체화 할 떄
    			 * 클래스가 아닌 클래스의 이름으로 호출하여 만드는 것을 말한다.
    			 */
    			Class<?> className = Class.forName(classPath);
    			//생성자 객체를 호출하고 메모리에 올린다.
    			Constructor<?> constructer = className.getConstructor(null); 
    			//객체를 이름으로 부터 생성.
    			Object obj = constructer.newInstance();  
    			
    			//주소에 관련된 객체를 생성하여 주소는 key에 생성된 객체는 value 에 넣는다.
    			commonMap.put(key, obj);
    		}
    		
    		System.out.println("----------------------------------------------------------------");
			System.out.println("app의 사용경로가 모두 등록되었습니다.");
			System.out.println("----------------------------------------------------------------");
    		
    		
    	}catch (Exception e) {
    		
    		e.printStackTrace();
    		
		}finally {
			
			try {
				
				if(buf != null) {
					buf.close();
				}
				
				if(is !=  null) {
					is.close();
				}
			
			}catch (Exception e) {
	    		e.printStackTrace();
			}
			
		}
	}
    
    /**
     * doPost 또는 doGet 이전에 실행되는 메서드 
     * 사용자 요청에 맞는 서비스 처리를 위해서 해당 메서드에서
     * 로직을 구현한다.
     */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

		
		//화면 이름변수
		String viewName = "";
		//공통 클래스
		CommonAction action = null;
		
		try {
			
			
			//사용자가 요청한 경로를 가져온다.
			String requestURI = req.getRequestURI();
			//rootPath 가져오기
			String conxtextPath = req.getContextPath();
			
			/**
			 * URL 은 어떤 정보가 위치한 로케이션(넓은 범위)
			 * URI 는 어떤 정보에 접근하기 위한 식별주소(식별 범위)
			 * 인터넷 창에 쓰는 주소 전체는
			 * URL 도되고 URI 도 될수 있음 이유는 위치이자 식별주소이기 떄문.
			 * 그러나 보톤 전체 주소는 URL 이라고 대부분 칭함
			 * 
			 */
			
			//contextPath 이후 경로만 가져오기 
			String commandPath = requestURI.substring(conxtextPath.length());
			
			System.out.println("RequestURL: " + req.getRequestURL());
			System.out.println("RequestURI: " + req.getRequestURI());
			System.out.println("추출된 URL command: " + commandPath);
			
			
			/**
			 * 앞으로 우리가 만들 모든 Action 은 CommonAction을 상속하게 된다.
			 * 서블릿이 최초 실행될 때 map 에 담은 객체는
			 * 각각이 어떤 형태인지 알기 힘들어서
			 * 객체를 형변환할떄 부모객체의 타입으로 가져오게 된다.
			 * 이렇게 하는 이유는 
			 * 모든 Action은 인터페이스인 CommonAction 을 상속하게  될 것이고,
			 * CommonAction이 가지고 있는 excuteAction 을 구현하게 된다.
			 * 즉 어떤 객체인지 정확히 몰라도
			 * excuteACtion을 실행함으로써 해당 객체의 로직이 실행될 것이다.
			 * 
			 */
			action = (CommonAction)commonMap.get(commandPath);
			
			if(action == null) {
			
				action = new CommonErrorAction();
				viewName = action.excuteAction(req, resp);
				resp.sendRedirect(viewName);
			
			}else {
				
				//정상진행이니까 데이터를 지니고 이동할수 있도록 함. 
				viewName = action.excuteAction(req, resp);
				RequestDispatcher rd = req.getRequestDispatcher(viewName);
				rd.forward(req, resp);	
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
    
    
    
    
}
