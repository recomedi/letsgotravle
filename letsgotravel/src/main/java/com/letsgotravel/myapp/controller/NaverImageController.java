package com.letsgotravel.myapp.controller;

import com.letsgotravel.myapp.service.NaverImageSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/naverImageTest") // 기본 경로 설정
public class NaverImageController {

    private final NaverImageSearchService naverImageSearchService;
    private static final Logger logger = LoggerFactory.getLogger(NaverImageController.class);

    @Autowired
    public NaverImageController(NaverImageSearchService naverImageSearchService) {
        this.naverImageSearchService = naverImageSearchService;
    }

    // 네이버 이미지 검색 JSP 페이지 반환
    @GetMapping("/naverImageTest.do")
    public String getImageSearchPage() {
        logger.info("네이버 이미지 검색 페이지 로드");
        return "/WEB-INF/naverImageTest/naverImageTest";  // ✅ ViewResolver가 자동으로 "/WEB-INF/views/naverImageTest/naverImageTest.jsp"로 매핑
    }

    // 네이버 이미지 검색 API
    @GetMapping(value = "/search", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String searchImages(@RequestParam("query") String query) {
        logger.info("이미지 검색 요청: {}", query);
        try {
            return naverImageSearchService.searchImages(query);
        } catch (Exception e) {
            logger.error("이미지 검색 실패", e);
            return "{\"error\":\"이미지 검색 중 오류가 발생했습니다.\"}";
        }
    }
}
