package com.letsgotravel.myapp.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession; // 🔹 세션을 사용하기 위해 추가
import com.letsgotravel.myapp.domain.Criteria;
import com.letsgotravel.myapp.domain.PageMaker;
import com.letsgotravel.myapp.service.ScrapService;
import org.slf4j.Logger;

@Controller
@RequestMapping(value = "/scrap")
public class ScrapController {
		
    private static final Logger logger = LoggerFactory.getLogger(ScrapController.class);

    @Autowired
    private ScrapService scrapService;

    @Autowired
    private PageMaker pageMaker;

    @RequestMapping(value = "/scrapList.do")
    public String scrapList(HttpSession session, Criteria cri, Model model) {
        Integer midx = (Integer) session.getAttribute("midx"); // 🔹 세션에서 midx 가져오기

        if (midx == null) {  //로그인 안 했으면 로그인 페이지로 이동
            logger.warn("로그인이 필요합니다.");
            return "redirect:/login.do"; 
        }

        logger.info("scrapList 실행됨! midx: " + midx + ", page: " + cri.getPage());

        // 특정 사용자의 스크랩 목록 가져오기
        model.addAttribute("scrapList", scrapService.getScrapList(midx, cri));

        int totalCount = scrapService.scrapTotalCount(midx);
        pageMaker.setCri(cri);
        pageMaker.setTotalCount(totalCount);
        model.addAttribute("pageMaker", pageMaker);

        return "WEB-INF/scrap/scrapList";
    }
    
	@RequestMapping(value = "scrapWriteAction.do", method = RequestMethod.POST)
	public String scrapWriteAction() {
		logger.info("scrapWriteAction들어옴");

		//int value = scrapService.scrapInsert(sv);	    
//
//		String path = "";
//		if (value == 1) {
//			path = "redirect:/";
//		} else if (value == 0) {
//			path = "redirect:/member/memberSignup.do";
//		}
		
		return "WEB-INF/scrap/scrapList";
	}

	@RequestMapping(value = "scrapList.do", method = RequestMethod.GET)
	public String scrapList() {
		logger.info("scrapList들어옴");

		return "WEB-INF/scrap/scrapList";
	}
	
	
}