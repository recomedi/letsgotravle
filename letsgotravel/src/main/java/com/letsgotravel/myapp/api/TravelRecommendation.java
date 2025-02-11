package com.letsgotravel.myapp.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class TravelRecommendation {
	
    public ArrayList<Map<String, Object>> changeArray(String openAIResult) throws Exception {
    	        
    	// JSON 객체 생성
        JSONObject jsonObject = new JSONObject(openAIResult);
        JSONArray choices = jsonObject.getJSONArray("choices");
        JSONObject message = choices.getJSONObject(0).getJSONObject("message");
        String content = message.getString("content");

        // "```json" 부분 제거 및 JSON 배열 파싱
        String jsonArrayString = content.replace("```json\n", "").replace("\n```", "");
                
        ArrayList<Map<String, Object>> travelCityList = new ArrayList<Map<String, Object>>();
        
        // content가 JSON 배열로 시작하는지 확인
        if (jsonArrayString.contains("[")) {
	        String cleanJson = jsonArrayString.substring(jsonArrayString.indexOf("["), jsonArrayString.lastIndexOf("]") + 1);
	        
	        // JSON 배열 파싱 후 ArrayList로 변환
	        try {
	            JSONArray jsonArray = new JSONArray(cleanJson);
	
	            for (int i = 0; i < jsonArray.length(); i++) {
	                JSONObject destination = jsonArray.getJSONObject(i);
	                Map<String, Object> destinationMap = new HashMap<String, Object>();
	
	                // JSONObject의 각 데이터를 Map으로 변환하여 ArrayList에 추가
	                destinationMap.put("나라/도시", destination.getString("나라/도시"));
	                destinationMap.put("대표관광지(3개)", destination.getJSONArray("대표관광지(3개)").toList());
	                destinationMap.put("대표음식(3개)", destination.getJSONArray("대표음식(3개)").toList());
	                destinationMap.put("날씨(섭씨)", destination.getString("날씨(섭씨)"));
	                destinationMap.put("성수기여부", destination.getString("성수기여부"));
	                destinationMap.put("한국 대비 물가", destination.getString("한국 대비 물가"));
	                destinationMap.put("치안", destination.getString("치안"));
	                destinationMap.put("위생", destination.getString("위생"));
	                destinationMap.put("교통", destination.getString("교통"));
	                destinationMap.put("주의해야 하는 기간", destination.getString("주의해야 하는 기간"));
	
	                // Map을 ArrayList에 추가
	                travelCityList.add(destinationMap);
	            }
	
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        }
        
        return travelCityList;
//      }
	//          System.out.println("Invalid JSON format: expected array.");
	//      }
        
//        // content가 JSON 배열로 시작하는지 확인
//        if (jsonArrayString.trim().startsWith("[")) {
//            // 정상적인 JSON 배열 형식으로 파싱
//            JSONArray travelDataArray = new JSONArray(jsonArrayString);
//
//            // 나라/도시 값 추출            
//            for (int i = 0; i < travelDataArray.length(); i++) {
//                JSONObject cityInfo = travelDataArray.getJSONObject(i);
//                String countryCity = cityInfo.getString("나라/도시");
//                countryCityArray.add(countryCity);
//            }
//            
//        } else {
//            System.out.println("Invalid JSON format: expected array.");
//        }
    }
}
    