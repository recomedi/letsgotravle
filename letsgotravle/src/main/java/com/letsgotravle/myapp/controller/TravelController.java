package com.letsgotravle.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/travel")
public class TravelController {
	
	private static final Logger logger = LoggerFactory.getLogger(TravelController.class);
	
//	@Autowired(required=false)
//	private UserIp userip;
//	
//	@Autowired
//	private MemberService memberService;
//	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = "/travelInput.do")
	public String travelInput(Model model) {
		logger.info("travelInput����");				
		return "WEB-INF/travel/travelInput";
	}

	@RequestMapping(value = "/travelConditions.do")
	public String travelConditions() {
		logger.info("travelConditions����");
		return "WEB-INF/travel/travelConditions";	
	}

	@RequestMapping(value = "/travelSelect.do")
	public String travelSelect() {
		logger.info("travelSelect����");
		return "WEB-INF/travel/travelSelect";
	}

	@RequestMapping(value = "/travelSights.do")
	public String travelSights() {
		logger.info("travelSights����");
		return "WEB-INF/travel/travelSights";
	}

	@RequestMapping(value = "/travelModify.do")
	public String travelModify() {
		logger.info("travelModify����");
		return "WEB-INF/travel/travelModify";
	}

	@RequestMapping(value = "/travelDetails.do")
	public String travelDetails() {
		logger.info("travelDetails����");
		return "WEB-INF/travel/travelDetails";
	}
	
}
