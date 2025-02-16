package com.letsgotravel.myapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class NaverImageSearchService {

    @Value("${naver.api.client-id}") 
    private String clientId;

    @Value("${naver.api.client-secret}") 
    private String clientSecret;

    private static final String NAVER_IMAGE_SEARCH_URL = "https://openapi.naver.com/v1/search/image";

    public String searchImages(String query) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Naver-Client-Id", clientId);
            headers.set("X-Naver-Client-Secret", clientSecret);
            headers.setContentType(MediaType.APPLICATION_JSON);

            // 한글 검색어 인코딩
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);

            // 안전한 URL 생성
            String url = UriComponentsBuilder.fromHttpUrl(NAVER_IMAGE_SEARCH_URL)
                    .queryParam("query", encodedQuery)
                    .queryParam("display", "10")  // 최대 10개 이미지
                    .queryParam("start", "1")
                    .queryParam("sort", "sim")  // 정확도순 정렬
                    .toUriString();

            // API 호출
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
            );

            return responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\":\"네이버 이미지 검색 API 호출 중 오류 발생\"}";
        }
    }
}
