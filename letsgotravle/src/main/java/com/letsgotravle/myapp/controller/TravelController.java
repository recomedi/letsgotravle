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
		logger.info("travelInputµé¾î¿È");				
		return "WEB-INF/travel/travelInput";
	}

	@RequestMapping(value = "/travelConditions.do")
	public String travelConditions() {
		logger.info("travelConditionsµé¾î¿È");
		return "WEB-INF/travel/travelConditions";	
	}

	@RequestMapping(value = "/travelSelect.do")
	public String travelSelect() {
		logger.info("travelSelectµé¾î¿È");
		return "WEB-INF/travel/travelSelect";
	}

	@RequestMapping(value = "/travelSights.do")
	public String travelSights() {
		logger.info("travelSightsµé¾î¿È");
		return "WEB-INF/travel/travelSights";
	}

	@RequestMapping(value = "/travelModify.do")
	public String travelModify() {
		logger.info("travelModifyµé¾î¿È");
		return "WEB-INF/travel/travelModify";
	}

	@RequestMapping(value = "/travelDetails.do")
	public String travelDetails() {
		logger.info("travelDetailsµé¾î¿È");
		return "WEB-INF/travel/travelDetails";
	}
	
}
