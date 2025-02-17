package com.letsgotravle.myapp.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession; // 로그인 세션 사용
import com.letsgotravle.myapp.domain.Criteria;
import com.letsgotravle.myapp.domain.PageMaker;
import com.letsgotravle.myapp.service.ScrapService;
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
        //세션에서 midx 가져오기
        Integer midx = (Integer) session.getAttribute("midx");

        //로그인 안 한 경우 로그인 페이지로 리디렉트
        if (midx == null) {
            logger.warn("로그인이 필요합니다.");
            return "redirect:/login.do"; 
        }

        logger.info("scrapList 실행됨! midx: " + midx + ", page: " + cri.getPage());

        //특정 사용자의 스크랩 목록 가져오기
        model.addAttribute("scrapList", scrapService.getScrapList(midx, cri));

        //특정 사용자의 전체 스크랩 개수 가져오기 (페이징 처리용)
        int totalCount = scrapService.scrapTotalCount(midx);
        pageMaker.setCri(cri);
        pageMaker.setTotalCount(totalCount);
        model.addAttribute("pageMaker", pageMaker);

        return "WEB-INF/scrap/scrapList";
    }
}
