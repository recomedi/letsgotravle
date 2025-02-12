package com.letsgotravel.myapp.controller;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/scrap")
public class ScrapController {
		
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ScrapController.class);
	
	@RequestMapping(value = "/scrapList.do")
	public String scrapList() {
		logger.info("scrapListµé¾î¿È");
		return "WEB-INF/scrap/scrapList";
	}
}