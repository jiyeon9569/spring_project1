package kr.co.megait.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.megait.common.CommonUtil;
import kr.co.megait.dao.MemberDAO;


/**
 * Handles requests for the application home page.
 */
@Controller
public class KakaoController {
	
	private static final Logger logger = LoggerFactory.getLogger(KakaoController.class);
	
	/**
	 * 첫번째 메인 홈
	 * @param request
	 * @param respone
	 * @return
	 */
	@RequestMapping(value = "/kakao_default", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView KakaoDefault(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/Kakao/kakao_default");
		return mv;
		
	}

	
	@RequestMapping(value = "/kakao_cluster_default", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView KakaoClusterDefault(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/Kakao/kakao_cluster_default");
		return mv;
		
	}

	@RequestMapping(value = "/chicken", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView KakaoChickenDefault(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/Kakao/chicken.json");
		return mv;
		
	}

	
	/**
	 * 구글 맵 디폴트
	 * @param request
	 * @param respone
	 * @return
	 */
	@RequestMapping(value = "/google_map_default", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView GoogleDefault(HttpServletRequest request, HttpServletResponse respone) {
		ModelAndView mv = new ModelAndView("/google/google_map_default");
		return mv;
		
	}

	
}
