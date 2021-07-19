package kr.code.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main")
public class MainServlet extends HttpServlet{
	
	/*
	 * 서블릿과 jsp는 결국 동일한 web server 파일이다
	 * 단, jsp는 디자인+서버코드로 구성된다. 디자인을 최대한 해치지않고 코드를 실행하려고 만든것
	 * 서블릿은 jsp 이전에 사용된 web 서버 파일이다(java코드 안에 디자인(view)코드를 입력하기 때문에)
	 * 디자이너들이 완성되기전에 보기힘들었고, 완성되었어도 빠지는 내용이 많아 서로 협업에 어려운 점이 있던 파일
	 * 그래서 자바 코드와 view를 최대한 분리하기원해서 기존에 썼던 서블릿과 jsp를 결합하여 mvc1모델을 만들고 상용하게 되었다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}
	
	//실제 동작코드를 쓴다
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=null;
		
		try {
			resp.setContentType("text/html; charset=utf-8");	//리스펀드개체가 어떤 타입인지 지정
			out=resp.getWriter();
			
			out.println("<html>");
			out.println("	<body>");
			out.println("		<h1>서블릿을 호출하여 페이지 생성</h1>");
			out.println("	</body>");
			out.println("</html>");
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(out!=null) {
				out.close();
			}
		}
	}

}
