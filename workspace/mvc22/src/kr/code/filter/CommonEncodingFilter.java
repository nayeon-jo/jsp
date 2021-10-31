package kr.code.filter;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
/*
 * urlPatterns : 필터를 거칠 경로지정 -> /* 하면 모든경로는 필터를 거쳐야한다.
 */
@WebFilter(filterName="commonEncodingFilter", urlPatterns = "/*",
             initParams = {
            		         @WebInitParam(name="encoding", value="UTF-8")
            		      })
public class CommonEncodingFilter implements Filter{
	//인코딩값을 받아올 변수
	private String encoding = null;
	//필터 설정정보 객체 선언
	private FilterConfig filterConf;
	//필터가 최초 실행될때만 구동된다.
	// servlet 의 init 메서드와 동일한 기능 
	@Override
	public void init(FilterConfig filterConf) throws ServletException {
		this.filterConf = filterConf;
		this.encoding = filterConf.getInitParameter("encoding");
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		//별도의 텍스트 인코딩이 지정되지 않았다면 
		if(req.getCharacterEncoding() == null) {
			if(this.encoding != null) { // 그리고 인코딩값이 존재하면
				req.setCharacterEncoding(this.encoding); // 세팅해라 
			}
		}
		//필터 체인을 진행
		chain.doFilter(req, resp);
	}
}
