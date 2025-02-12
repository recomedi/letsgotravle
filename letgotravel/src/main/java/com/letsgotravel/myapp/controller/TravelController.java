package com.letsgotravel.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.letsgotravel.myapp.domain.TravelConditionsVo;

@Controller
@RequestMapping(value="/travel")
public class TravelController {
	
	private static final Logger logger = LoggerFactory.getLogger(TravelController.class);
	
//	@Autowired(required=false)
//	private UserIp userip;
//	
//	@Autowired
//	private MemberService memberService;

//	@Autowired
//	private ScrapService scrapService;
//	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = "/travelInput.do")
	public String travelInput() {
		logger.info("travelInput들어옴");				
		return "WEB-INF/travel/travelInput";
	}

	@RequestMapping(value = "/travelConditions.do")
	public String travelConditions() {
		logger.info("travelConditions들어옴");
		return "WEB-INF/travel/travelConditions";	
	}

	@RequestMapping(value = "/travelSelect.do")
	public String travelSelect() {
		logger.info("travelSelect들어옴");
		return "WEB-INF/travel/travelSelect";
	}

	@RequestMapping(value = "/travelSights.do")
	public String travelSights() {
		logger.info("travelSights들어옴");
		return "WEB-INF/travel/travelSights";
	}

	@RequestMapping(value = "/travelModify.do")
	public String travelModify(
			HttpServletRequest request,
			// @PathVariable("bidx") int bidx,
			Model model) {
		
		logger.info("travelModify들어옴");
		
//		//세션 저장
//		sessionStorage.setItem("key", value);
//
//		//특정 세션 값 불러오기
//		sessionStorage.getItem("key");
//
//		//특정세션 삭제
//		sessionStorage.removeItem("key");
//
//		//세션 전체 삭제
//		sessionStorage.clear();
//		
		/*
		 * BoardVo bv = scrapService.boardSelectOne(bidx); // 해당되는 bidx의 게시물 데이터 가져옴
		 * ArrayList<CalendarVo> clist = calendarService.calendarSelectAll(bidx);
		 * 
		 * String id = request.getSession().getAttribute("id").toString(); MemberVo mv =
		 * memberService.memberSelect(id);
		 * 
		 * String adminyn = request.getSession().getAttribute("adminyn").toString();
		 */
//		
//		model.addAttribute("bv", bv);
//		model.addAttribute("clist", clist);	
//		model.addAttribute("mv", mv);		
		
		return "WEB-INF/travel/travelModify";
	}

	@RequestMapping(value = "/travelDetails.do")
	public String travelDetails() {
		logger.info("travelDetails들어옴");
		return "WEB-INF/travel/travelDetails";
	}
	
}
