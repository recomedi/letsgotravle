package com.letsgotravel.myapp.controller;

import com.letsgotravel.myapp.service.NaverImageSearchService;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        logger.info("✅ 네이버 이미지 검색 페이지 로드");
        return "/WEB-INF/naverImageTest/naverImageTest";
    }

    // 네이버 이미지 검색 API
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> searchImages(@RequestParam String query) {
        logger.info("🟢 컨트롤러에서 받은 검색어 (디코딩 전): {}", query);

        // ✅ URL 디코딩 적용 (띄어쓰기 복구)
        String decodedQuery = URLDecoder.decode(query, StandardCharsets.UTF_8);
        logger.info("🟢 컨트롤러에서 받은 검색어 (디코딩 적용): {}", decodedQuery);

        String result = naverImageSearchService.searchImages(decodedQuery);
        logger.info("🟢 네이버 API 결과 (서비스에서 반환된 데이터): {}", result);

        return ResponseEntity.ok(result);
    }
}
