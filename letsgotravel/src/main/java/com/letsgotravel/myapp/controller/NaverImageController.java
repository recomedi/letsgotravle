package com.letsgotravel.myapp.controller;

import com.letsgotravel.myapp.service.NaverImageSearchService;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

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
    	  try {
    	        logger.info("🟢 컨트롤러에서 받은 검색어 (디코딩 전): {}", query);

    	        // ✅ JSON 형태의 문자열이 query에 들어가는지 확인
    	        if (query.startsWith("{") && query.endsWith("}")) {
    	            logger.error("❌ 잘못된 query 값 (JSON 데이터 포함됨): {}", query);
    	            return "{\"error\":\"잘못된 검색어 형식입니다.\"}";
    	        }

    	        // ✅ 한 번만 디코딩 (이중 인코딩 방지)
    	        String decodedQuery = URLDecoder.decode(query, StandardCharsets.UTF_8);
    	        logger.info("🟢 컨트롤러에서 받은 검색어 (디코딩 적용): {}", decodedQuery);

    	        // ✅ 한글 포함 여부 확인
    	        if (!decodedQuery.matches(".*[가-힣]+.*")) {
    	            logger.warn("⚠️ 한글이 포함되지 않음. query: {}", decodedQuery);
    	        }

    	        // ✅ 네이버 API 호출
    	        String result = naverImageSearchService.searchImages(decodedQuery);
    	        logger.info("🟢 네이버 API 결과: {}", result);

    	        return result;
    	    } catch (Exception e) {
    	        logger.error("❌ 이미지 검색 실패", e);
    	        return "{\"error\":\"이미지 검색 중 오류가 발생했습니다.\"}";
    	    }
    	}

}
