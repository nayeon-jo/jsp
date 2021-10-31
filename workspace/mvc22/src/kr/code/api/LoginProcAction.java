package kr.code.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import kr.code.service.LoginService;
import kr.code.vo.UserInfo;

@WebServlet("/login/loginProc.ajax")
public class LoginProcAction extends HttpServlet{
	
	private LoginService loginService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		excuteAction(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		excuteAction(req, resp);
	}

	
	public void excuteAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//서비스 객체 선언 
		loginService = new LoginService();
		
		//리턴타입 양식 - json 코드로 보냄 
		resp.setContentType("application/x-json; charset=UTF-8");
		String userId  = req.getParameter("userId");
		String userPw  = req.getParameter("userPw");
		
		try {
			
			Map<String, Object> param = new HashMap<>();
			
			param.put("userId", userId);
			param.put("userPw", userPw);
			
			//서비스로부터 사용자 정보 받아오기 
			UserInfo userInfo = loginService.loginUser(param);
			
			//JSON 타입 객체 선언 
			JSONObject obj = new JSONObject();
			
			//null 이면 로그인 실패 
			if(userInfo == null) {
				obj.put("resultCode", 400);
				obj.put("resultMsg", "아이디 또는 패스워드가 잘못되었습니다");
			}else {
				//로그인 성공 
				obj.put("resultCode", 200);
				obj.put("resultMsg", "OK");
				obj.put("userName", userInfo.getUserName());
				
				//로그인을 유지하기 위해서 세션에 정보를 저장한다.
				HttpSession session = req.getSession();
				session.setAttribute("userInfo", userInfo);
				// 30분동안 아무동작없을 시 로그아웃 처리 - 초단위 
				session.setMaxInactiveInterval(30*60); 
			}
			
			PrintWriter out = resp.getWriter();
			out.print(obj);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
