package com.letsgotravel.myapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.letsgotravel.myapp.api.TravelRecommendation;
import com.letsgotravel.myapp.domain.TravelConditionsVo;
import com.letsgotravel.myapp.service.OpenAiService;

@Controller
@RequestMapping(value="/travel")
public class TravelController {
	
	private static final Logger logger = LoggerFactory.getLogger(TravelController.class);
	
	TravelRecommendation travelRecommendation = new TravelRecommendation();
	
//	@Autowired(required=false)
//	private UserIp userip;
//	
//	@Autowired
//	private MemberService memberService;

//	@Autowired
//	private ScrapService scrapService;
//	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = "/travelInput.do")
	public String travelInput() {
		logger.info("travelInput 들어옴");
		return "WEB-INF/travel/travelInput";
	}

	@RequestMapping(value = "/travelConditions.do")
	public String travelConditions() {
		logger.info("travelConditions 들어옴");
		return "WEB-INF/travel/travelConditions";
	}

	@RequestMapping(value = "/travelSelect.do")
	public String travelSelect(TravelConditionsVo tv, Model model) throws Exception {

		logger.info("travelSelect 들어옴");
				
	    int peopleCount = tv.getPeopleCount();
	    String departureMonth = tv.getDepartureMonth();
	    int duration = tv.getDuration();
	    String groupType = tv.getGroupType();
	    int budgetMin = tv.getBudgetMin();
	    int budgetMax = tv.getBudgetMax();
	    String thema = tv.getThema();
	    
	    // 축제 제외 prompt
	    StringBuilder prompt1 = new StringBuilder();
	    prompt1.append("너는 ");
	    prompt1.append(groupType);
	    prompt1.append(" 여행전문가야. 내가 말하는 조건에 맞는 여행 일정을 추천해줘. 총 인원은 ");
	    prompt1.append(peopleCount);
	    prompt1.append("명이고 여행 기간은 ");
	    prompt1.append(duration);
	    prompt1.append("일, 예산은 ");
	    prompt1.append(budgetMin + "만원 ~ " + budgetMax + "만원");
	    prompt1.append("이야. 키워드는 ");
	    prompt1.append(thema);
	    prompt1.append("이고 여행경보 2단계초과 지역인 ");
	    prompt1.append("러시아");  // 수정예정
	    prompt1.append("와 내가 살고있는 한국은 제외하고 추천해줘.");
	    prompt1.append(departureMonth);
	    prompt1.append(" 기준의 일정을 추천해줬으면 좋겠어. 여행지의 대표관광지(3개), 대표음식(3개), 날씨, 성수기여부, 물가, 치안, 위생, 교통, ");
	    prompt1.append(departureMonth);
	    prompt1.append(" 기간에 라마단같이 문화적으로 주의해야하는 기간이 있으면 알려줘. 위의 내용을 json 형식으로 6개의 도시를 알려줘. 예시를 보여줄게.");
	    prompt1.append("{" +
		    	    "  \"나라/도시\": \"일본/오사카\"," +
		    	    "  \"대표관광지\", [\"오사카 성\", \"유니버셜 스튜디오 재팬\"]," +
		    	    "  \"대표음식\": [\"오코노미야키\", \"타코야키\", \"스시\"]," +
		    	    "  \"날씨(섭씨)\": \"18~25°C\"," +
		    	    "  \"성수기여부\": \"비성수기\"," +
		    	    "  \"한국 대비 물가\": \"약간 비쌈\"," +
		    	    "  \"치안\": \"매우 안전\"," +
		    	    "  \"위생\": \"우수, 청결한 환경\"," +
		    	    "  \"교통\": \"지하철, JR 등 대중교통이 잘 발달\"," +
		    	    "  \"주의해야 하는 기간\": \"없음\"" +
		    	    "}");
	    System.out.println(prompt1);
	    String openAIResult1 = openAiService.getTravelRecommendation(prompt1.toString());
	    ArrayList<Map<String, Object>> openAIResult1Array = travelRecommendation.changeArray(openAIResult1);
	    
	    for(Map<String, Object> openAIResult : openAIResult1Array) {
	    	String city = openAIResult.get("나라/도시").toString();
	    	
	    	// 축제 prompt
		    StringBuilder prompt2 = new StringBuilder();
		    prompt2.append("현재 시간 기준으로 ");
		    prompt2.append(city);
		    prompt2.append("의 ");
		    prompt2.append(departureMonth);
		    prompt2.append("에 공식 일정이 확정된 페스티벌 정보를 최대 3개까지 배열 형식으로 알려줘. 기간은 제외하고 명칭만 알려줘. 예시를 보여줄게.");
		    prompt2.append("{\"축제\" : [\"시부야 요요기 공원 이벤트 광장\", \"사카나 앤 재팬 페스티벌\"]}");
		    prompt2.append("없을 경우 없다고 말해줘.");
		    System.out.println(prompt2);
			String openAIResult2 = openAiService.getTravelRecommendation(prompt2.toString());
			//String openAIResult2 = "{\"축제\" : [\"시부야 요요기 공원 이벤트 광장\", \"사카나 앤 재팬 페스티벌\"]}";			
			ArrayList<String> openAIResultString = travelRecommendation.changeArray2(openAIResult2);
		    
		    if(!(openAIResultString.isEmpty())) {
		    	openAIResult.put("축제, 이벤트", openAIResultString);
		    } else {
		    	ArrayList<String> nothing = new ArrayList<>();
		    	nothing.add("없음");		    	
		    	openAIResult.put("축제, 이벤트", nothing);
		    }
	    }
	    
	    model.addAttribute("cityList", openAIResult1Array);
		
		return "WEB-INF/travel/travelSelect";
	}

	@RequestMapping(value = "/travelSights.do")
	public String travelSights(TravelConditionsVo tv, Model model) throws Exception {
		
		logger.info("travelSights 들어옴");
		
		String destination = tv.getDestination();
		int peopleCount = tv.getPeopleCount();
	    String departureMonth = tv.getDepartureMonth();
	    String groupType = tv.getGroupType();
	    int budgetMin = tv.getBudgetMin();
	    int budgetMax = tv.getBudgetMax();
	    String thema = tv.getThema();
	    
	    // 추천 장소 prompt
	    StringBuilder prompt1 = new StringBuilder();
	    prompt1.append("너는 ");
	    prompt1.append(groupType);
	    prompt1.append(" 여행전문가야. 내가 말하는 조건에 맞는 관광지 10개 이상과 음식점 2개 이상 추천해줘. 도시는 ");  // 20개씩
	    prompt1.append(destination);
	    prompt1.append("이고 총 인원은 ");
	    prompt1.append(peopleCount);
	    prompt1.append("명이고 예산은 ");	    
	    prompt1.append(budgetMin + "만원 ~ " + budgetMax + "만원");
	    prompt1.append("이야. 키워드는 ");
	    prompt1.append(thema);
	    prompt1.append("이고 ");  // 수정예정
	    prompt1.append(departureMonth);
	    prompt1.append("에 방문하기 좋은 장소로 추천해줬으면 좋겠어. 설명 없이 명칭만 알려줘. 위의 내용을 json 형식으로 부탁해. 예시를 보여줄게.");
	    prompt1.append("[{" +
		    	    "  \"추천관광지\": [\"시부야 스크램블 교차로\", \"도쿄 스카이트리\"]}," +
		    	    "  {\"추천음식점\": [\"이치란라멘\", \"멘야 무사시\"]" +
		    	    "}]");
	    System.out.println(prompt1);
	    String openAIResult1 = openAiService.getTravelRecommendation(prompt1.toString());
	    ArrayList<Map<String, Object>> openAIResult1Array = travelRecommendation.changeArray3(openAIResult1);
	    
	    HashMap<String, String> sightListArray = getSightArray(openAIResult1Array, "추천관광지");
	    HashMap<String, String> restaurantListArray = getSightArray(openAIResult1Array, "추천음식점");
	   
		model.addAttribute("destination", destination);
		model.addAttribute("openAIResult1Array", openAIResult1Array);
		model.addAttribute("sightListArray", sightListArray);
		model.addAttribute("restaurantListArray", restaurantListArray);
		
		return "WEB-INF/travel/travelSights";
	}

	@RequestMapping(value = "/travelModify.do")
	public String travelModify(
			TravelConditionsVo tv, 
			@RequestParam("sights") String signts, 
			@RequestParam("restaurants") String restaurants, 
			Model model) throws Exception {
		
		logger.info("travelModify 들어옴");
		
		String destination = tv.getDestination();
		int peopleCount = tv.getPeopleCount();
	    String departureMonth = tv.getDepartureMonth();
	    String groupType = tv.getGroupType();
	    int budgetMin = tv.getBudgetMin();
	    int budgetMax = tv.getBudgetMax();
	    String thema = tv.getThema();
	    
	    // 추천 장소 prompt
	    StringBuilder prompt1 = new StringBuilder();
	    prompt1.append("너는 ");
	    prompt1.append(groupType);
	    prompt1.append(" 여행전문가야. 내가 말하는 조건에 맞는 여행 일정을 추천해줘. 총 인원은 ");  // 20개씩
	    prompt1.append(peopleCount);
	    prompt1.append("명이고 여행 기간은 ");
	    prompt1.append(destination);
	    prompt1.append("일, 예산은 ");	    
	    prompt1.append(budgetMin + "만원 ~ " + budgetMax + "만원");
	    prompt1.append("이야. 키워드는 ");
	    prompt1.append(thema);
	    prompt1.append("이고 여행 장소는 ");
	    prompt1.append(thema);
	    prompt1.append("야. ");
	    prompt1.append(departureMonth);
	    prompt1.append(" 기준의 일정을 추천해줬으면 좋겠어. 방문할 장소는 ");
	    prompt1.append(signts);
	    prompt1.append(" 이고 방문할 음식점은 ");
	    prompt1.append(restaurants);
	    prompt1.append("이야. 대중교통을 이용할 경우걸리는 소요시간까지 반영해서 알려줘. 한번 갔던 장소는 다시 방문하지 않을래. 위의 내용을 json 형식으로 알려줘. 예시를 보여줄게.");
	    prompt1.append("[{" + 
	    				"title: \"도쿄 디즈니랜드\", " +
		                "start: \"1900-01-01T12:30:00\", " +
		                "end: \"1900-01-01T13:30:00\", " +
						    "extendedProps: {\" " +
			                    "category: \"sight\" " +
			                "}" +
						"},{" + 
					    "title: \"라멘 츠케멘\"," +
		                "start: \"1900-01-01T12:30:00\"," +
		                "end: \"1900-01-01T13:30:00\"," +
		                	"extendedProps: {" +
		                    	"category: \"restaurant\"" +
		                    "}" +
	    				"}]");
	    System.out.println(prompt1);
	    String openAIResult1 = openAiService.getTravelRecommendation(prompt1.toString());
	    ArrayList<Map<String, Object>> openAIResult1Array = travelRecommendation.changeArray4(openAIResult1);
		System.out.println("openAIResult1 : " + openAIResult1);
		System.out.println("openAIResult1Array : " + openAIResult1Array);
		
		model.addAttribute("openAIResult1Array", openAIResult1Array);
		
		return "WEB-INF/travel/travelModify";
	}

	@RequestMapping(value = "/travelDetails.do")
	public String travelDetails() {
		logger.info("travelDetails 들어옴");
		
//		//占쏙옙占쏙옙 占쏙옙占쏙옙
//		sessionStorage.setItem("key", value);
//
//		//특占쏙옙 占쏙옙占쏙옙 占쏙옙 占쌀뤄옙占쏙옙占쏙옙
//		sessionStorage.getItem("key");
//
//		//특占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
//		sessionStorage.removeItem("key");
//
//		//占쏙옙占쏙옙 占쏙옙체 占쏙옙占쏙옙
//		sessionStorage.clear();		
		
		return "WEB-INF/travel/travelDetails";
	}
	
	@Autowired
    private OpenAiService openAiService;


	    // 占쎌맅 JSP 占쎈읂占쎌뵠筌욑옙 占쎌깈�빊占� (GET 占쎌뒄筌ｏ옙)
	    @GetMapping("/openAiTest.do")
	    public String showOpenAiTestPage() {
	        return "WEB-INF/travel/openAiTest";  // 占쎌맅 `openAiTest.jsp`嚥∽옙 占쎌뵠占쎈짗
	    }

	    // 占쎌맅 OpenAI API 占쎌깈�빊占� (POST 占쎌뒄筌ｏ옙)
	    @PostMapping("/openAiTest.do")
	    @ResponseBody
	    public String getTravelRecommendation(@RequestBody Map<String, String> requestBody) {
	        String prompt = requestBody.get("prompt");
	        return openAiService.getTravelRecommendation(prompt);
	    }
	    
	    
	/* 장소 검색 */
	public HashMap<String, String> getSightArray(ArrayList<Map<String, Object>> openAIResult1Array, String sightType) throws Exception {
		
		ArrayList<String> sights = (ArrayList<String>)openAIResult1Array.get(0).get(sightType);
		HashMap<String, String> returnSights = new HashMap<>();
	
	    for(String sight : sights) {
	    	
	    	// 상세설명 prompt
		    StringBuilder prompt2 = new StringBuilder();
		    prompt2.append(sight);
		    prompt2.append("에 대해 String 형식으로 설명해줘. \"물론입니다\"나 \"알겠습니다\" 같은 부가적인 말은 하지 말아줘. 길고 자세하게 설명 부탁해. 존댓말로 해줘.");
		    System.out.println(prompt2);
			String openAIResult2 = openAiService.getTravelRecommendation(prompt2.toString());
			
		    String openAIResult1String = travelRecommendation.changeString(openAIResult2);
			returnSights.put(sight, openAIResult1String);
	    }
	
	    return returnSights;
	}

	
}

