package kr.code.service;

import java.util.Map;

import kr.code.dao.LoginDao;
import kr.code.vo.UserInfo;

public class LoginService {

	//데이터를 가져올 DAO 선언
	private final LoginDao dao;
	
	public LoginService() {
		dao = new LoginDao();
	}
	
	public UserInfo loginUser(Map<String, Object> param) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		
		 sb.append("select *              \n");
		 sb.append("from  user_info       \n");
		 sb.append("where user_id =     ?  \n");
		 sb.append("    and  user_pw =  ?  \n");
		
		 UserInfo vo = dao.loginUser(sb.toString(), param);
		
		return vo;
	}
	
}
