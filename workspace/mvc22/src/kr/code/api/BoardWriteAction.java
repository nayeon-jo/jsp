package kr.code.api;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.code.service.BoardService;
import kr.code.vo.BoardVO;
import kr.code.vo.UserInfo;

@WebServlet("/board/create.ajax")
public class BoardWriteAction extends HttpServlet{

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
		
		// 응답 시 데이터 타입을 JSON 으로 하고 인코딩을 UTF-8 로한다.
		resp.setContentType("application/x-json; charset=UTF-8");
		//세션객체 불러오기 
		HttpSession session = req.getSession();
	
		UserInfo userInfo  = (UserInfo)session.getAttribute("userInfo");
		
		//결과값 반환을 위한 Json 객체 선언 
		JSONObject  obj  = new JSONObject(); 
		
		String defaultUrl ="D:\\upload_test";
		String encType  = "UTF-8";
		int sizeLimt = 100 * 1024 * 1024 ; // 100메가 제한 - 기본단위가 byte
		int result = 0;
		
		try {
			
			File file = new File(defaultUrl);	
			
			//폴더가 없을 경우 만들어라 
			if(!file.exists()) {
				file.mkdirs();
			}
		
			/**
			 * (리퀘스트객체, 경로, 파일제한크기, 인코딩타입, 정책- 기본은 중복이름있을시 (1) 처럼 변경하기 )
			 * 해당 객체를 사용하면 자동으로 파일 업로드가 이루어짐 
			 */
			MultipartRequest   multi = new MultipartRequest(req, defaultUrl, sizeLimt, encType, new DefaultFileRenamePolicy());
			
			//파라미터 이름은 html 에서 file input tage 이름 을 쓰면된다.
			String storedFileName = multi.getFilesystemName("upfile");
			String originFileName = multi.getOriginalFileName("upfile");
			
			String boardTitle  = multi.getParameter("title");
			String boardContents  = multi.getParameter("contents");
			String boardAuthor = userInfo.getUserName();
			
			
			BoardVO vo = new BoardVO();
			
			vo.setBoardTitle(boardTitle);
			vo.setBoardContents(boardContents);
			vo.setOriginFileName(originFileName);
			vo.setStoredFileName(storedFileName);
			vo.setBoardAuthor(boardAuthor);
			vo.setFilePath(defaultUrl);
			
			
			
			BoardService service = new BoardService();
			
			result = service.insertBoard(vo);
			
			if(result > 0) {
				obj.put("resultCode", 200);
				obj.put("resultMsg", "OK");
			}else {
				obj.put("resultCode", 400);
				obj.put("resultMsg", "게시글 등록을 실패했습니다.");
			}
				
		}catch (Exception e) {
			obj.put("resultCode", 500);
			obj.put("resultMsg", "게시글 업로드중에 문제가 발생하였습다.");
			e.printStackTrace();
		}
		
		PrintWriter out = resp.getWriter();		// 응답 시 건내줄 데이터를 쓰기 위한 연필	
		out.print(obj);  // 응답 쓰기...
		
	}
}
