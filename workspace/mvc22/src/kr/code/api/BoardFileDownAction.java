package kr.code.api;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/file/downLoadFile")
public class BoardFileDownAction extends HttpServlet{

	private static final long serialVersionUID = 4250972357184199057L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		fileDown(request, response);
	}
	
	public void fileDown(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String defaultUrl =this.getServletContext().getRealPath("WEB-INF/upload/");
		String defaultUrl ="D:\\upload_test";
	    // 파라미터로 받은 파일 이름.
	    String UTF8FileName = request.getParameter("fileName");

	    // 다운로드 알림창이 뜨도록 하기 위해서 컨텐트 타입을 8비트 바이너리로 설정한다.
	    response.setContentType("application/octet-stream");
	    // 저장될 파일 이름을 설정한다.
	    
	    System.out.println("downLoad Url : " + defaultUrl);
	    
	    String downName = null;
	    
	    /**
	     * 한글 깨짐을 위한 조치
	     * 
	     */
		if(request.getHeader("user-agent").indexOf("MSIE") == -1) {
			//브라우저가 ie 가 아닐경우
			downName = URLDecoder.decode((URLDecoder.decode(UTF8FileName, "UTF-8")), "8859_1"); 
		}
		else {
			//브라우저가  IE 일 경우 
			downName = URLDecoder.decode((URLDecoder.decode(UTF8FileName, "8859_1")), "UTF-8"); 
		}
	    
	    
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + downName + "\";");
	    // 파일패스 및 파일명을 지정한다.
	    String filePathAndName = defaultUrl +"/"+ UTF8FileName;

	    File file = new File(filePathAndName);
	    // 버퍼 크기 설정
	    byte bytestream[] = new byte[2048000];
	    // response out에 파일 내용을 출력한다.
	    if (file.isFile() && file.length() > 0){

	        FileInputStream fis = new FileInputStream(file);
	        BufferedInputStream bis = new BufferedInputStream(fis);
	        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());


	        int read = 0;

	        while ((read = bis.read(bytestream)) != -1){
	            bos.write(bytestream , 0, read);
	        }
	        
	        bos.close();
	        bis.close();
	    }else{
	    	
	    	throw new IOException("파일 객체가 Null 혹은 존재하지 않거나 길이가 0, 혹은 파일이 아닌 디렉토리이다.");	
	    }

	}

}
