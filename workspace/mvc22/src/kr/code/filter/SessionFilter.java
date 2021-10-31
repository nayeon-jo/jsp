package kr.code.filter;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.code.vo.UserInfo;
@WebFilter(filterName="SessionFilter", urlPatterns="/*")
public class SessionFilter implements Filter{

	@Override
	public void destroy(){
   
    }
	
	@Override
	public void init(FilterConfig filterconfig) throws ServletException{
  
	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{
		HttpServletResponse res = (HttpServletResponse)servletResponse;
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpSession httpsession = req.getSession();

        String context  = req.getContextPath();
        String uri = req.getRequestURI();
        
        //서블릿을 타고....처음 로딩되는 화면들은 세션체크를 자체적으로 하기때문에 필터에서 굳이 할필요가없다...
    	if(uri.endsWith(".do") && !uri.equals(context +
    			  "/layout/layout.do") && !uri.equals(context + "/login/loginForm.do") ) {
        	//url 검사 로그인할때도 세션체크하면 로그인이 안되니..
        	UserInfo user = (UserInfo)httpsession.getAttribute("userInfo");
        	//세션에 담긴 userVO를 받아옴
        	if(user == null){ //null 일경우 
        		
                res.setContentType("text/html; charset=utf-8");       // 전송할 타입은 html로 전송한다..      
                res.setCharacterEncoding("UTF-8");

         		PrintWriter out = res.getWriter(); // 결과를 전송한다..

                out.println("<html><head></head><body>");
            	out.println("<script>");
        		out.println("alert('로그인이 필요합니다....!!!');");
        		out.println("location.href='"+context+"/index.jsp';");  // 해당 경로로 이동하라....
        		out.println("</script>");
        		  out.println("</body></html>");
                
                return;
            }
        }
        
        filterChain.doFilter(servletRequest, servletResponse);
	}
}