// 패키지 정의
package CommuB;

// 서블릿 패키지 import
import java.io.*;
// import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.annotation.WebServlet;

// 자바 클래스 import
import java.util.ArrayList;


// 어너테이션 서블릿과 URL 정의, urlPatters는 절대 Path
@WebServlet(description = "commu Controller 서블릿", urlPatterns = { "/CommuB/CommuController" })
public class CommuController extends HttpServlet {

	// 객체 직렬화(Serializable), 이클립스에서 자동 생성은 클래스 이름에서 Ctrl + 1
	private static final long serialVersionUID = 1L;

	// _jspxFactory 생성
	private static final javax.servlet.jsp.JspFactory _jspxFactory = javax.servlet.jsp.JspFactory.getDefaultFactory();
	
 	// GET 요청을 처리하기 위한 메서드
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// doPost()로 포워딩.
		doPost(request, response);
	}

	
	// POST 요청을 처리하기 위한 메서드, doGet()에서도 호출하고 있기 때문에 모든 요청은 doPost()에서 처리되는 구조이다.
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    // pageContext 참조변수 선언
		// javax.servlet.jsp.PageContext pageContext = null;
		PageContext pageContext = null;
		
		try {

		   // pageContext 참조변수 객체 생성
			pageContext = _jspxFactory.getPageContext(this, request, response, null, true, 8192, true);
		
			
			// 한글 인코딩
			request.setCharacterEncoding("UTF-8");
			
			// 클라이언트 응답시 전달될 컨텐트에 대한 타잎 설정과 캐릿터셋 지정
			response.setContentType("text/html; charset=UTF-8");
	
			// action구분 등 파라메터
			String action = request.getParameter("action");
System.out.println("Controller action = " + action);


			//클라이언트 응답을 위한 출력 스트림 확보(alert 메세지용)
			PrintWriter out = response.getWriter();
			
			// accountTransferDTO 변수 정의
			CommuDTO commuDTO = new CommuDTO();
			
			/* action에 따라 동작            */
			
			if((action.equals("insert")) || (action.equals("update"))) {
			
				// setProperty accountTransferDTO에 해당하는 java 코드
				commuDTO.setWritehead(request.getParameter("writehead"));
				commuDTO.setWriter(request.getParameter("writer"));
				commuDTO.setWriteDate(request.getParameter("writeDate"));
				commuDTO.setWritetext(request.getParameter("writetext"));
			}
				
			// accountTransferDAO 변수 정의
			CommuDAO commuDAO = new CommuDAO();
			
			/* action에 따라 동작            */			
			if(action.equals("list")) {
				
				// 계좌이체 조회결과
				ArrayList<CommuDTO> commuList = commuDAO.getDBList();

				
				// List를 setAttribute
				request.setAttribute("commuList", commuList);

				pageContext.forward("commu_list.jsp");

			} else if(action.equals("add")) {
			
				// 입력화면 오픈
				request.setAttribute("action", action);
				
				pageContext.forward("commu_view.jsp");
			
			} else if(action.equals("insert")) {
				
				// 입력
				if(commuDAO.insertDB(commuDTO)) {
					// 조회결과
					ArrayList<CommuDTO> commuList = commuDAO.getDBList();
					
					// List를 setAttribute
					request.setAttribute("commuList", commuList);

					// 결과 조회를 위하여 조회화면 호출
					pageContext.forward("commu_list.jsp");
					
				} else {
					throw new Exception("DB 입력오류");
				}
				
			} else if(action.equals("edit")) {
				
				// edit용 1건을 select
				commuDTO = commuDAO.getDB(Integer.parseInt((String)request.getParameter("id")));
				
				// edit를 setAttribute
				request.setAttribute("action", action);
				
				request.setAttribute("commuDTO", commuDTO);
				pageContext.forward("commu_view.jsp");
				
			} else if(action.equals("view")) {
					
					// edit용 1건을 select
					commuDTO = commuDAO.getDB(Integer.parseInt((String)request.getParameter("id")));
					
					// edit를 setAttribute
					request.setAttribute("action", action);
					
					request.setAttribute("commuDTO", commuDTO);
					pageContext.forward("commu_view_sub.jsp");
					
			} else if(action.equals("go_update")) {
				
				// edit용 1건을 select
				commuDTO = commuDAO.getDB(Integer.parseInt((String)request.getParameter("id")));
				
				// edit를 setAttribute
				request.setAttribute("action", action);
				
				request.setAttribute("commuDTO", commuDTO);
				pageContext.forward("commu_view.jsp");
				
		}
			else if(action.equals("update")) {

				// 수정
				if(commuDAO.updateDB(Integer.parseInt((String)request.getParameter("id")), commuDTO)) {

					//조회결과
					ArrayList<CommuDTO> commuList = commuDAO.getDBList();
					
					// List를 setAttribute
					request.setAttribute("commuList", commuList);

					// 결과 조회를 위하여 조회화면 호출
					pageContext.forward("commu_list.jsp");
				} else {
					throw new Exception("DB 수정오류");
				}
				
			} else if(action.equals("delete")) {

				//삭제
				if(commuDAO.deleteDB(Integer.parseInt((String)request.getParameter("id")))) {

					// 조회결과
					ArrayList<CommuDTO> commuList = commuDAO.getDBList();
					
					// List를 setAttribute
					request.setAttribute("commuList", commuList);

					// 결과 조회를 위하여 조회화면 호출
					pageContext.forward("commu_list.jsp");
				} else {
					throw new Exception("DB 삭제오류");
				}
				
			} else {
				
				out.println("<script>alert('action 파라미터를 확인해 주세요!!!')</script>");
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
