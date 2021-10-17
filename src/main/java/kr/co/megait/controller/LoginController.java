package kr.co.megait.controller;

import java.io.PrintWriter;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.megait.common.CommonUtil;
import kr.co.megait.dao.LoginDAO;
import kr.co.megait.dao.MemberDAO;


/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * 로그인 페이지
	 * @param request
	 * @param respone
	 * @return
	 */
	@RequestMapping(value = "/login_default", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView LoginDefault(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/Login/login_default");
		return mv;
		
	}
	
	/**
	 * 로그인 완료
	 * @param request
	 * @param respone
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/login_ok", method = {RequestMethod.GET, RequestMethod.POST})
	public void LoginOk(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		//html을 만들기 위해
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		//세션을 만들기 위해
		HttpSession session = request.getSession();
		
		//로그인 완료
		String member_id = request.getParameter("member_id");
		String member_pwd = request.getParameter("member_pwd");
		
		CommonUtil commonUtil = new CommonUtil();
		member_pwd = commonUtil.getEncrypt(member_pwd);
		
		int nflag = 0;
		
		try {
			LoginDAO loginDAO = new LoginDAO();
			
			nflag = loginDAO.LoginCheck(member_id, member_pwd);
			
			if(nflag==2) {
				
				MemberDAO memberDAO = new MemberDAO();
				LinkedHashMap member_info = new LinkedHashMap();
				member_info = memberDAO.MemberInfo2(member_id, member_pwd);
				
				System.out.println("로그인이 되었습니다.");
				session.setAttribute("member_idx", (Integer)member_info.get("member_idx") );
				session.setAttribute("member_id", member_id);
				session.setAttribute("member_pwd", member_pwd);
				session.setAttribute("member_name", (String)member_info.get("member_name") );
				session.setAttribute("member_phone", (String)member_info.get("member_phone") );
				session.setAttribute("member_birth", (String)member_info.get("member_birth") );
				
				response.sendRedirect("/");
				
			}else if(nflag==1) {
				System.out.println("로그인 실패 : 비밀번호가 틀립니다.");
				out.println("<script>");
				out.println("alert('비밀번호가 틀립니다. 확인해 주세요');");
				out.println("location.href='/login_default.do'");
				out.println("</script>");

			}else {
				
				System.out.println("로그인 실패 : 회원 아이디가 틀립니다.");
				out.println("<script>");
				out.println("alert('아이디 틀립니다. 확인해 주세요');");
				out.println("location.href='/login_default.do'");
				out.println("</script>");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	/**
	 * 로그아웃
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout_ok", method = {RequestMethod.GET, RequestMethod.POST})
	public void LogoutOk(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		//html을 만들기 위해
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//세션을 만들기 위해
		HttpSession session = request.getSession();
		
		try {
			
			System.out.println("로그아웃되었습니다.");
			
			session.invalidate();
			out.println("<script>");
			out.println("alert('회원님 로그아웃되었습니다.');");
			out.println("location.href='/'");
			out.println("</script>");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
