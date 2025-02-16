package com.letsgotravel.myapp.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.letsgotravel.myapp.util.UserIp;

@Controller
@RequestMapping(value = "/scrap")
public class ScrapController {
		
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ScrapController.class);
	
	@Autowired(required=false)
	private UserIp userip;
	
//	@Autowired
//	private ScrapService scrapService;

//	@Autowired
//	private ScrapVo sv;
	
	@RequestMapping(value = "scrapWriteAction.do", method = RequestMethod.POST)
	public String scrapWriteAction(
			@RequestParam("calendarData") String calendarData,
			@RequestParam("sights") String sights,
			@RequestParam("restaurants") String restaurants,
			@RequestParam("destination") String destination,
			Model model) {
		
		logger.info("scrapWriteAction들어옴");

		//int value = scrapService.scrapInsert(sv);
		logger.info("calendarData:" + calendarData);
	    
//
//		String path = "";
//		if (value == 1) {
//			path = "redirect:/";
//		} else if (value == 0) {
//			path = "redirect:/member/memberSignup.do";
//		}

		model.addAttribute("destination", destination);
		model.addAttribute("calendarData", calendarData);
		
		return "WEB-INF/travel/travelDetails";
	}
	
	
}