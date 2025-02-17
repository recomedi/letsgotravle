package com.letsgotravel.myapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.letsgotravel.myapp.controller.NaverImageController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class NaverImageSearchService {

    @Value("${naver.api.client-id}")
    private String clientId;

    @Value("${naver.api.client-secret}")
    private String clientSecret;

    private static final Logger logger = LoggerFactory.getLogger(NaverImageController.class);
    private static final String NAVER_IMAGE_SEARCH_URL = "https://openapi.naver.com/v1/search/image";

    public String searchImages(String query) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Naver-Client-Id", clientId);
            headers.set("X-Naver-Client-Secret", clientSecret);
            headers.setContentType(MediaType.APPLICATION_JSON);

            // ✅ 한글 검색어 인코딩 (띄어쓰기 포함)
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);

            // ✅ 네이버 API 요청 URL 확인
            String url = UriComponentsBuilder.fromHttpUrl(NAVER_IMAGE_SEARCH_URL)
                    .queryParam("query", encodedQuery) 
                    .queryParam("display", "10") 
                    .queryParam("start", "1")
                    .queryParam("sort", "sim")
                    .toUriString();

            logger.info("🔵 네이버 API 요청 URL: {}", url); // ✅ 최종 URL 확인

            // API 호출
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
            );

            logger.info("🟢 네이버 API 응답: {}", responseEntity.getBody());  // ✅ 응답 확인
            return responseEntity.getBody();
        } catch (Exception e) {
            logger.error("❌ 네이버 이미지 검색 API 호출 실패", e);
            return "{\"error\":\"네이버 이미지 검색 API 호출 중 오류 발생\"}";
        }
    }
}

