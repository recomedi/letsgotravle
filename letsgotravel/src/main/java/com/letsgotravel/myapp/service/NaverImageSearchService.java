package com.letsgotravel.myapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.core.JsonGenerator;

@Service
public class NaverImageSearchService {
    private static final Logger logger = LoggerFactory.getLogger(NaverImageSearchService.class);
    private static final String NAVER_IMAGE_SEARCH_URL = "https://openapi.naver.com/v1/search/image";

    @Value("${naver.api.client-id}")
    private String clientId;

    @Value("${naver.api.client-secret}")
    private String clientSecret;

    public String searchImages(String query) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Naver-Client-Id", clientId);
            headers.set("X-Naver-Client-Secret", clientSecret);
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Accept-Charset", "UTF-8");

            // ✅ 한글 검색어를 UTF-8로 인코딩
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8).replace("+", "%20");
            logger.info("🔵 네이버 API 요청을 위한 인코딩된 검색어: {}", encodedQuery);

            // ✅ 네이버 API 요청 URL 확인
            String url = NAVER_IMAGE_SEARCH_URL + "?query=" + encodedQuery + "&display=10&start=1&sort=sim";
            logger.info("🔵 네이버 API 요청 URL: {}", url);

            // API 호출
            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class
            );

            String responseBody = responseEntity.getBody();
            logger.info("🟢 네이버 API 응답 (디코딩 전): {}", responseBody);

            // ✅ title 값 디코딩 (한글 깨짐 해결)
            responseBody = decodeNaverResponse(responseBody);
            logger.info("🟢 네이버 API 응답 (디코딩 후): {}", responseBody);

            return responseBody;
        } catch (Exception e) {
            logger.error("❌ 네이버 이미지 검색 API 호출 실패", e);
            return "{\"error\":\"네이버 이미지 검색 API 호출 중 오류 발생\"}";
        }
    }

    private String decodeNaverResponse(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, false);
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            JsonNode rootNode = objectMapper.readTree(responseBody);
            JsonNode items = rootNode.path("items");

            for (JsonNode item : items) {
                String encodedTitle = item.path("title").asText();
                logger.info("🔵 응답에서 받은 원본 title: {}", encodedTitle);

                // ✅ 1. HTML 엔티티 디코딩
                String decodedTitle = StringEscapeUtils.unescapeHtml4(encodedTitle);
                logger.info("🔵 HTML 엔티티 디코딩 후 title: {}", decodedTitle);

                // ✅ 2. URL 퍼센트 인코딩된 경우 추가 디코딩
                if (decodedTitle.contains("%")) {
                    try {
                        decodedTitle = URLDecoder.decode(decodedTitle, StandardCharsets.UTF_8);
                        logger.info("🟢 URL 디코딩 성공: {}", decodedTitle);
                    } catch (IllegalArgumentException e) {
                        logger.warn("🚨 URL 디코딩 실패 또는 불필요: {}", decodedTitle);
                    }
                }

                // ✅ 3. 한글이 정상적으로 존재하는지 확인 후 적용
                if (decodedTitle.matches(".*[가-힣]+.*")) {
                    logger.info("✅ 최종 디코딩된 title: {}", decodedTitle);
                } else {
                    logger.warn("⚠️ 디코딩 후에도 한글이 포함되지 않음, title 유지: {}", decodedTitle);
                }

                ((ObjectNode) item).put("title", decodedTitle);
            }

            // ✅ JSON 응답을 UTF-8로 변환
            return objectMapper.writeValueAsString(rootNode);

        } catch (Exception e) {
            logger.error("❌ 응답 디코딩 실패", e);
            return responseBody;
        }
    }
}
