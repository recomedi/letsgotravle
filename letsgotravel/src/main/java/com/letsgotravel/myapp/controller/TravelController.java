package com.letsgotravel.myapp.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	    prompt1.append(" 기준의 일정을 추천해줬으면 좋겠어. 여행지의 대표관광지, 대표음식, 날씨, 성수기여부, 물가, 치안, 위생, 교통, 라마단같이 문화적으로 주의해야하는 기간이 있으면 알려줘. 위의 내용을 json 형식으로 6개의 도시를 알려줘. 예시를 보여줄게.");
	    prompt1.append("{" +
		    	    "  \"나라/도시\": \"일본/오사카\"," +
		    	    "  \"대표관광지(3개)\", \"오사카 성\", \"유니버셜 스튜디오 재팬\"]," +
		    	    "  \"대표음식(3개)\": [\"오코노미야키\", \"타코야키\", \"스시\"]," +
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
		    prompt2.append("에 공식 일정이 확정된 페스티벌 정보를 배열 형식으로 알려줘. 예시를 보여줄게.");
		    prompt2.append("[" +
		    			"  \"교토 아오이 마츠리\"" +
			    	    "]");
		    prompt2.append("없을 경우 없다고 말해줘.");
		    System.out.println(prompt2);
			String openAIResult2 = openAiService.getTravelRecommendation(prompt2.toString());
			
		    ArrayList<Map<String, Object>> openAIResult1Array2 = travelRecommendation.changeArray(openAIResult2);
		    
		    if(!openAIResult1Array2.isEmpty()) {
		    	openAIResult.put("축제, 이벤트", openAIResult1Array2.get(0).get("축제, 이벤트"));
		    } else {
		    	openAIResult.put("축제, 이벤트", "없음");
		    }
	    }
	    
	    model.addAttribute("cityList", openAIResult1Array);
		
		return "WEB-INF/travel/travelSelect";
	}

	@RequestMapping(value = "/travelSights.do")
	public String travelSights(TravelConditionsVo tv, Model model) {
		logger.info("travelSights 들어옴");
		
		String destination = tv.getDestination();
		
		
		
		model.addAttribute("destination", destination);
//		model.addAttribute("sightsList", sightsList);
//		model.addAttribute("restaurantList", restaurantList);
		
		return "WEB-INF/travel/travelSights";
	}

	@RequestMapping(value = "/travelModify.do")
	public String travelModify(
			HttpServletRequest request,
			// @PathVariable("bidx") int bidx,
			Model model) {
		
		logger.info("travelModify 들어옴");
		
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
//		
		/*
		 * BoardVo bv = scrapService.boardSelectOne(bidx); // 占쌔댐옙풔占� bidx占쏙옙 占쌉시뱄옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙
		 * ArrayList<CalendarVo> clist = calendarService.calendarSelectAll(bidx);
		 * 
		 * String id = request.getSession().getAttribute("id").toString(); MemberVo mv =
		 * memberService.memberSelect(id);
		 * 
		 * String adminyn = request.getSession().getAttribute("adminyn").toString();
		 */
//		
//		model.addAttribute("bv", bv);
//		model.addAttribute("clist", clist);	
//		model.addAttribute("mv", mv);		
		
		return "WEB-INF/travel/travelModify";
	}

	@RequestMapping(value = "/travelDetails.do")
	public String travelDetails() {
		logger.info("travelDetails 들어옴");
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
	
}
