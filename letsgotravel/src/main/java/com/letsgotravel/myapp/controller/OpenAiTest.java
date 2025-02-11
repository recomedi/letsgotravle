package com.letsgotravel.myapp.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;

public class OpenAiTest {

    @Value("${openAI_key}")
    private static String OPENAI_KEY;
    
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public static void main(String[] args) {
        try {
            String prompt = "�꼫�뒗 媛�議� �뿬�뻾 �뿬�뻾�쟾臾멸��빞 �씤�썝�� 4紐낆씠怨� 異쒕컻�� 2025�뀈 5�썡 湲곌컙�� 7�씪 �삁�궛�� 200留뚯썝~300留뚯썝�씠怨� �뿬�뻾�뀒留덉�(�뒗) �옄留�,�떇�룄�씫,�븸�떚鍮꾪떚 �뿬�뻾寃쎈낫 2�떒怨꾩큹怨� 吏��뿭�� 異붿쿇�븯吏� 留먯븘以� �뿬�뻾吏��쓽 ���몴愿�愿묒� ���몴�쓬�떇 �꽦�닔湲곗뿬遺� 臾쇨�, 移섏븞, �궇�뵪(5�썡) �쐞�깮 �뿬�뻾吏��쓽 援먰넻�젙蹂�,二쇱쓽�빐�빞�븯�뒗 湲곌컙(�씪留덈떒媛숈�) �빐�떦�썡�쓽 異뺤젣 諛� �뻾�궗媛� �엳�쑝硫� �븣�젮以� �쐞�쓽 �궡�슜�쓣  異붿쿇�룷�씤�듃 : ���몴愿�愿묒� : (3媛쒖젙�룄) ���몴�쓬�떇 : (3媛쒖젙�룄) �궇�뵪 : (�꽠�뵪濡� �븣�젮以�) �꽦�닔湲곗뿬遺� :  臾쇨� : (�븳援�怨� 鍮꾧탳) 移섏븞 : �쐞�깮 : 援먰넻 : (吏��븯泥�,�젋�듃,踰꾩뒪�벑 �렪由ы븳 援먰넻)二쇱쓽�빐�빞 �븯�뒗 湲곌컙 : 異뺤젣&�뻾�궗(5�썡 吏꾩쭨濡� 5�썡�뿉 �뿴�젮�빞�빐) :  �씠�윴�떇�쑝濡� 6援곕뜲 �븣�젮以� �븳援��� 鍮쇱쨾 �쐞�쓽 �궡�슜�� 5�썡湲곗��씠�빞 5�썡�뿉 異뺤젣媛� �뾾�쑝硫� �뾾�떎怨� 留먰빐以� �씠嫄� �뜲�씠�꽣踰좎씠�뒪�뿉 �꽔�쓣 嫄곕씪�꽌 json�삎�떇�쑝濡� �븣�젮以꾩닔�엳�뼱? 2025�뀈 湲곗��쑝濡� �븣�젮以꾩닔�엳�뼱?";

            // JSON �룷留� �닔�젙 (�씠�뒪耳��씠�봽 臾몄젣 �빐寃�)
            String payload = String.format(
                "{ \"model\": \"gpt-4o\", \"messages\": [ { \"role\": \"user\", \"content\": \"%s\" } ] }",
                prompt.replace("\"", "\\\"") // �뵲�샂�몴 �씠�뒪耳��씠�봽 泥섎━
            );

            // API �슂泥�
            String response = sendPostRequest(API_URL, payload);
            System.out.println("Response: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String sendPostRequest(String apiUrl, String payload) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Bearer " + OPENAI_KEY);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json"); // 異붽�

        // �슂泥� �뜲�씠�꽣 �쟾�넚
        try (OutputStream os = connection.getOutputStream()) {
            os.write(payload.getBytes("UTF-8"));
            os.flush();
        }

        // �쓳�떟 �씫湲�
        int responseCode = connection.getResponseCode();
        StringBuilder response = new StringBuilder();

        if (responseCode == 200) { // �젙�긽 �쓳�떟
            try (Scanner scanner = new Scanner(connection.getInputStream())) {
                while (scanner.hasNext()) {
                    response.append(scanner.nextLine());
                }
            }
        } else { // �삤瑜� �쓳�떟
            try (Scanner scanner = new Scanner(connection.getErrorStream())) {
                while (scanner.hasNext()) {
                    response.append(scanner.nextLine());
                }
            }
            throw new IOException("Error Response: " + response.toString()); // �삤瑜� 異쒕젰
        }

        return response.toString();
    }
}
