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

import org.json.JSONException;
import org.json.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.code.service.BoardService;
import kr.code.vo.BoardVO;
import kr.code.vo.UserInfo;

@WebServlet("/board/modify.ajax")
public class BoardUpdateAction extends HttpServlet{

	private static final long serialVersionUID = 3780127458309815219L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		executeAction(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		executeAction(req, resp);
	}
	

	public void executeAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		HttpSession session = request.getSession();
		
		UserInfo userInfo  = (UserInfo)session.getAttribute("userInfo");
		JSONObject  obj  = new JSONObject();  // 반환데이터는 JSON 타입으로 한다.
		
		//db로부터 게시판리스트 정보를 가져올 서비스 단....
		BoardService boardService  = new BoardService();
		
		int resultCode  =0;
		String resultMsg  = null;
		
		try{
	
			String defaultUrl ="D:\\upload_test";
			String encType  = "UTF-8";
			int sizeLimt = 1024 * 1024* 1024 ; // 20메가 제한
			
			
			MultipartRequest multi = new MultipartRequest(request, defaultUrl, sizeLimt, encType,  new DefaultFileRenamePolicy());
			//업로드된 파일에 대한 객체를 가져온다.
			File newFile = multi.getFile("upfile");    
			
			//새로 저장할 데이터 
			String boardTitle  = multi.getParameter("title");
			String boardContents  = multi.getParameter("contents");
		
			int boardId  = Integer.parseInt(multi.getParameter("boardId").toString());
			
			//새로 저장할 데이터 객체 
			BoardVO newBoard  = new BoardVO();
			newBoard.setBoardId(boardId);
			newBoard.setBoardTitle(boardTitle);
			newBoard.setBoardContents(boardContents);
			newBoard.setBoardAuthor(userInfo.getUserName());
	
			/*기존의 게시판 정보를 가져와서 기존 파일에 대한 정보를 찾는다.....*/
			BoardVO preBoard  = boardService.getReadBoard(boardId);
			
			
			/*수정할 파일이 존재한다면   */
			if(newFile !=null){
				
				String newStoredFileName = multi.getFilesystemName("upfile");
				String newOriginFileName = multi.getOriginalFileName("upfile");
				
				newBoard.setOriginFileName(newOriginFileName);
				newBoard.setStoredFileName(newStoredFileName);
				
				//기존에 저장한 정보가 존재한다면 기존파일 삭제 
				if(preBoard !=null){
					String deletePath = defaultUrl +"/" + preBoard.getStoredFileName();
					File deleteFile = new File(deletePath);
					/*기존에 게시글이 첨부파일을 가지고있다면 */
					if(deleteFile.exists()){
						/*기존에 존재했던 파일은 지운다.*/
						deleteFile.delete();
					}
				}
				
			}else {
				
				//새로 저장할 파일이 없다면 기존 이름을 유지한다.
				newBoard.setOriginFileName(preBoard.getOriginFileName());
				newBoard.setStoredFileName(preBoard.getStoredFileName());
			}
			
			
		    /* 파일을 업데이트 한다.*/
			resultCode  = boardService.updateBoard(newBoard);
			resultMsg  = null;

			
			if(resultCode <  0){
				obj.put("resultCode", 400);
				obj.put("resultMsg", "게시물 수정이 실패하였습니다");	
			}else{
				obj.put("resultCode", 200);
				obj.put("resultMsg", "게시물 수정이 성공하였습니다");	
			}

		}catch(Exception e){
			obj.put("resultCode", 500);
			obj.put("resultMsg", e.getMessage());	
			e.printStackTrace();
		}finally {
			
			// 요청받은 파리미터  인코딩
			request.setCharacterEncoding("UTF-8"); 
			// 응답 시 데이터 타입을 JSON 으로 하고 인코딩을 UTF-8 로한다.
			response.setContentType("application/x-json; charset=UTF-8"); 
			// 응답 시 건내줄 데이터를 쓰기 위한 연필	

			PrintWriter out = response.getWriter();		// 응답 시 건내줄 데이터를 쓰기 위한 연필	
			out.print(obj);  // 응답 쓰기...
			
			
		}
	}
}
