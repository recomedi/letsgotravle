package com.letsgotravel.myapp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.System.Logger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.letsgotravel.myapp.api.EasyCodefConnector;
import com.letsgotravel.myapp.api.EasyCodefToken;
import com.letsgotravel.myapp.domain.DrugVo;
import com.letsgotravel.myapp.domain.MemberVo;
import com.letsgotravel.myapp.domain.PrescriptionVo;
import com.letsgotravel.myapp.service.MemberService;
import com.letsgotravel.myapp.service.PrescriptionService;

@Controller
@RequestMapping(value = "/prescription/")
public class PrescriptionController {
	
	
	 // CODEF API 관련 상수
    private static final String CLIENT_ID = "339dc4d8-9138-44a1-a2e3-7cf740b089a9";
    private static final String CLIENT_SECRET = "06ab49ab-0fb7-42af-991c-49cc18a76a3f";
    private static final String API_URL = "https://development.codef.io/v1/kr/public/hw/hira-list/my-medicine";

    private final PrescriptionService prescriptionService;
    private final MemberService memberService;

    @Autowired
    public PrescriptionController(PrescriptionService prescriptionService,MemberService memberService) {
        this.prescriptionService = prescriptionService;
		this.memberService = memberService;
    }
	
	 private HashMap<String, Object> requestData = new HashMap<>();
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PrescriptionController.class);

	//png file 유효성검사메서드 
	
	private boolean isPng(byte[] data) {
	    byte[] pngHeader = new byte[] {(byte) 0x89, 'P', 'N', 'G', (byte) 0x0D, (byte) 0x0A, (byte) 0x1A, (byte) 0x0A};
	    if (data.length < pngHeader.length) {
	        return false;
	    }
	    for (int i = 0; i < pngHeader.length; i++) {
	        if (data[i] != pngHeader[i]) {
	            return false;
	        }
	    }
	    return true;
	}
	


	
	
	
	@RequestMapping(value = "refreshSecureNo.do", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> refreshSecureNo(HttpSession session) {
	    logger.info("[DEBUG] refreshSecureNo enter?");
	    HashMap<String, Object> response = new HashMap<>();
	    try {
	        // 세션에서 필수 데이터 가져오기
	        @SuppressWarnings("unchecked")
	        HashMap<String, Object> requestData = (HashMap<String, Object>) session.getAttribute("secureNoRequestData");

	        if (requestData == null) {
	            response.put("error", "필수 입력값이 누락되었습니다. 다시 인증을 진행하세요.");
	            return response;
	        }

	        // 새로고침 플래그 추가
	        requestData.put("refresh", true);

	        // CODEF API 호출 준비
	        EasyCodefToken tokenService = new EasyCodefToken();
	        String clientId = "339dc4d8-9138-44a1-a2e3-7cf740b089a9";
	        String clientSecret = "06ab49ab-0fb7-42af-991c-49cc18a76a3f";
	        String accessToken = tokenService.getAccessToken(clientId, clientSecret);

	        if (accessToken.isEmpty()) {
	            response.put("error", "토큰 발급 실패");
	            return response;
	        }

	        // CODEF API 호출
	        EasyCodefConnector connector = new EasyCodefConnector();
	        ObjectMapper objectMapper = new ObjectMapper();
	        String requestBody = objectMapper.writeValueAsString(requestData);

	        HashMap<String, Object> apiResponse = connector.getRequestProduct(
	                "https://development.codef.io/v1/kr/public/hw/hira-list/my-medicine",
	                accessToken,
	                requestBody
	        );

	        System.out.println("[DEBUG] CODEF 응답 데이터: " + apiResponse);

	        // 응답에서 새로운 보안문자 데이터 추출
	        if (apiResponse.containsKey("data")) {
	            HashMap<String, Object> data = (HashMap<String, Object>) apiResponse.get("data");
	            HashMap<String, Object> extraInfo = (HashMap<String, Object>) data.get("extraInfo");

	            if (extraInfo != null && extraInfo.containsKey("reqSecureNo")) {
	                String reqSecureNo = (String) extraInfo.get("reqSecureNo");

	                // 순수 Base64 데이터만 반환
	                response.put("reqSecureNoDecoded", reqSecureNo);
	                logger.info("[DEBUG] 새로운 보안문자 생성 성공.");
	            } else {
	                response.put("error", "새로운 보안문자를 가져올 수 없습니다.");
	                logger.error("[ERROR] 새로운 보안문자 생성 실패.");
	            }


	        } else {
	            response.put("error", "API 응답에 데이터가 없습니다.");
	            logger.error("[ERROR] API 응답에 데이터가 없습니다.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        response.put("error", "보안문자 생성 중 오류 발생");
	    }
	    return response;
	}

	
	private HashMap<String, Object> callCodefApi(String accessToken, HashMap<String, Object> requestData) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            EasyCodefConnector connector = new EasyCodefConnector();
            ObjectMapper objectMapper = new ObjectMapper();

            // CODEF API 호출
            HashMap<String, Object> apiResponse = connector.getRequestProduct(
                    API_URL,
                    accessToken,
                    objectMapper.writeValueAsString(requestData)
            );

            logger.info("[DEBUG] CODEF 응답 데이터: {}", apiResponse);

            // 응답 처리
            if (apiResponse.containsKey("data")) {
                response.putAll((HashMap<String, Object>) apiResponse.get("data"));
            } else {
                response.put("error", "API 응답에 데이터가 없습니다.");
            }
        } catch (Exception e) {
            logger.error("CODEF API 호출 중 오류 발생: {}", e.getMessage());
            response.put("error", "CODEF API 호출 중 오류 발생: " + e.getMessage());
        }
        return response;
    }

	
	


	@RequestMapping(value = "certification.do", method = RequestMethod.GET)
	public String certification() {
		return "WEB-INF/prescription/certification";
	}
	

	@RequestMapping(value = "processCertification.do", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> processCertification(
	        @RequestParam("idNumberFront") String idNumberFront,
	        @RequestParam("idNumberBack") String idNumberBack,
	        @RequestParam("name") String name,
	        @RequestParam("phoneNumber") String phoneNumber,
	        @RequestParam("telecom") String telecom,
	        HttpSession session) throws JsonProcessingException {

	    HashMap<String, Object> response = new HashMap<>();
	    try {
	    	
	        // 주민등록번호 및 휴대폰 번호 합치기
	        String fullIdNumber = idNumberFront + idNumberBack;
	        String fullPhoneNumber = phoneNumber;

	        // CODEF 요청 데이터 구성
	        HashMap<String, Object> requestData = new HashMap<>();
	        requestData.put("organization", "0020"); // 기관 코드
	        requestData.put("loginType", "2");
	        requestData.put("identity", fullIdNumber); // 주민등록번호
	        requestData.put("loginTypeLevel", "1");
	        requestData.put("userName", name);
	        requestData.put("telecom", telecom);
	        requestData.put("phoneNo", fullPhoneNumber);
	        requestData.put("authMethod", "0"); // SMS 인증

	        // 세션에 필수 데이터 저장 (보안문자 새로고침용)
	        session.setAttribute("secureNoRequestData", requestData);
	        session.setAttribute("phoneNumber", fullPhoneNumber);

	        // CODEF API 호출 준비
	        EasyCodefToken tokenService = new EasyCodefToken();
	        String clientId = "339dc4d8-9138-44a1-a2e3-7cf740b089a9";
	        String clientSecret = "06ab49ab-0fb7-42af-991c-49cc18a76a3f";
	        String accessToken = tokenService.getAccessToken(clientId, clientSecret);
	        
	        

	        if (accessToken.isEmpty()) {
	            response.put("error", "토큰 발급 실패");
	            return response;
	        }

	        // CODEF API 호출
	        EasyCodefConnector connector = new EasyCodefConnector();
	        ObjectMapper objectMapper = new ObjectMapper();

	        String requestBody = objectMapper.writeValueAsString(requestData);

	        HashMap<String, Object> apiResponse = connector.getRequestProduct(
	                "https://development.codef.io/v1/kr/public/hw/hira-list/my-medicine",
	                accessToken,
	                requestBody
	        );

	        System.out.println("[DEBUG] CODEF 응답 데이터: " + apiResponse);

	        // 보안문자 처리
	        if (apiResponse.containsKey("data")) {
	            HashMap<String, Object> data = (HashMap<String, Object>) apiResponse.get("data");

	            if (Boolean.TRUE.equals(data.get("continue2Way"))) {
	                // 추가 인증이 필요한 경우
	                HashMap<String, Object> extraInfo = (HashMap<String, Object>) data.get("extraInfo");

	                if (extraInfo != null) {
	                    System.out.println("[DEBUG] extraInfo 객체 확인: " + extraInfo);

	                    String reqSecureNo = (String) extraInfo.get("reqSecureNo"); // 보안문자 데이터 추출
	                    System.out.println("[DEBUG] reqSecureNo 값: " + reqSecureNo);

	                    if (reqSecureNo != null && !reqSecureNo.isEmpty()) {
	                        if (reqSecureNo.startsWith("data:image/png;base64,")) {
	                            reqSecureNo = reqSecureNo.substring("data:image/png;base64,".length());
	                        }

	                        byte[] decodedBytes = Base64.getDecoder().decode(reqSecureNo);

	                        if (!isPng(decodedBytes)) {
	                            response.put("error", "유효하지 않은 PNG 이미지");
	                            return response;
	                        }

	                        String reEncodedBase64 = "data:image/png;base64," + Base64.getEncoder().encodeToString(decodedBytes);
	                        response.put("reqSecureNoDecoded", reEncodedBase64); // 클라이언트로 반환
	                        response.put("redirectToSecureInput", true); // 추가 인증 플래그 설정

	                        // 세션에 추가 인증 관련 데이터 저장
	                        session.setAttribute("jobIndex", data.get("jobIndex"));
	                        session.setAttribute("threadIndex", data.get("threadIndex"));
	                        session.setAttribute("jti", data.get("jti"));
	                        session.setAttribute("twoWayTimestamp", data.get("twoWayTimestamp"));
	                    } else {
	                        System.err.println("[ERROR] 보안문자 데이터가 없습니다.");
	                    }
	                } else {
	                    System.err.println("[ERROR] extraInfo 객체가 null입니다.");
	                }
	            } else {
	                // 추가 인증이 필요하지 않은 경우
	                response.put("redirectToSecureInput", false);
	            }

	            response.putAll(data); // 필요한 추가 데이터 포함
	            response.put("success", true);
	            return response;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    response.put("error", "요청 처리 중 오류 발생");
	    return response;
	}


	private boolean isBase64(String str) {
	    try {
	        Base64.getDecoder().decode(str); // 디코딩 시도
	        return true; // 디코딩 성공 시 유효한 Base64 문자열
	    } catch (IllegalArgumentException e) {
	        return false; // 디코딩 실패 시 유효하지 않은 문자열
	    }
	}
	
	

	
			@RequestMapping(value = "processSecureInput.do", method = RequestMethod.POST)
			@ResponseBody
			public HashMap<String, Object> processSecureInput(
			        @RequestParam("secureNo") String secureNo,
			        @RequestParam("secureNoRefresh") String secureNoRefresh,
			        @RequestParam("is2Way") boolean is2Way,
			        HttpSession session) throws JsonProcessingException {
		
			    HashMap<String, Object> response = new HashMap<>();
		
			    try {
			        // 세션에서 필수 데이터 가져오기
			        Integer jobIndex = (Integer) session.getAttribute("jobIndex");
			        Integer threadIndex = (Integer) session.getAttribute("threadIndex");
			        String jti = (String) session.getAttribute("jti");
			        Long twoWayTimestamp = (Long) session.getAttribute("twoWayTimestamp");
			        String phoneNumber = (String) session.getAttribute("phoneNumber");
		
			        if (phoneNumber == null) {
			            response.put("error", "세션에 전화번호 정보가 없습니다. 다시 인증을 진행하세요.");
			            return response;
			        }
		
			        if (jobIndex == null || threadIndex == null || jti == null || twoWayTimestamp == null) {
			            response.put("error", "필수 입력값이 누락되었습니다. 다시 인증을 진행하세요.");
			            return response;
			        }
		
			        // 세션에 저장된 요청 데이터 가져오기
			        @SuppressWarnings("unchecked")
			        HashMap<String, Object> requestData = (HashMap<String, Object>) session.getAttribute("secureNoRequestData");
		
			        if (requestData == null || !requestData.containsKey("organization")) {
			            response.put("error", "필수 입력값이 누락되었습니다.");
			            return response;
			        }
		
			        // 추가 인증 요청 데이터 구성
			        HashMap<String, Object> twoWayData = new HashMap<>();
			        twoWayData.put("secureNo", secureNo); // 보안문자 정보
			        twoWayData.put("secureNoRefresh", secureNoRefresh); // 새로고침 정보
			        twoWayData.put("is2Way", true); // 추가 요청 여부
		
			        // 두웨이 정보 포함
			        HashMap<String, Object> twoWayInfo = new HashMap<>();
			        twoWayInfo.put("jobIndex", jobIndex);
			        twoWayInfo.put("threadIndex", threadIndex);
			        twoWayInfo.put("jti", jti);
			        twoWayInfo.put("twoWayTimestamp", twoWayTimestamp);
			        response.put("jti", jti);
			        
			        twoWayData.put("twoWayInfo", twoWayInfo);
			        twoWayData.put("organization", requestData.get("organization"));
		
			        // CODEF API 호출 준비
			        EasyCodefToken tokenService = new EasyCodefToken();
			        String accessToken = tokenService.getAccessToken(CLIENT_ID, CLIENT_SECRET);
		
			        if (accessToken.isEmpty()) {
			            response.put("error", "토큰 발급 실패");
			            return response;
			        }
		
			        // CODEF API 호출
			        EasyCodefConnector connector = new EasyCodefConnector();
			        ObjectMapper objectMapper = new ObjectMapper();
			        String requestBody = objectMapper.writeValueAsString(twoWayData);
		
			        HashMap<String, Object> apiResponse = connector.getRequestProduct(
			                API_URL,
			                accessToken,
			                requestBody
			        );
		
			        System.out.println("[DEBUG] 추가 인증 응답 데이터: " + apiResponse);
		
			        // 응답 처리
			        if (apiResponse.containsKey("data")) {
			            HashMap<String, Object> data = (HashMap<String, Object>) apiResponse.get("data");
		
			            if (Boolean.TRUE.equals(data.get("continue2Way"))) {
			                HashMap<String, Object> extraInfo = (HashMap<String, Object>) data.get("extraInfo");
		
			                String reqSMSAuthNo = "";
			                if (extraInfo != null && extraInfo.containsKey("reqSMSAuthNo")) {
			                    reqSMSAuthNo = (String) extraInfo.get("reqSMSAuthNo");
			                }
		
			                if (reqSMSAuthNo == null || reqSMSAuthNo.isEmpty()) {
			                    System.out.println("[WARN] SMS 인증번호가 비어 있지만, API 요청이 정상적으로 처리됨.");
			                    response.put("success", true);
			                    response.put("message", "SMS 인증번호가 전송되었습니다. 인증번호를 입력하세요.");
			                } else {
			                    response.put("success", true);
			                    response.put("reqSMSAuthNo", reqSMSAuthNo);
			                    response.put("message", "SMS 인증번호가 전송되었습니다.");
			                }
			            }
			            return response;
			        } else {
			            response.put("success", false);
			            response.put("errorMessage", "API 응답에 result 데이터가 없습니다.");
			            return response;
			        }
		
			    } catch (Exception e) {
			        e.printStackTrace();
			        response.put("success", false);
			        response.put("errorMessage", "추가 인증 중 오류 발생: " + e.getMessage());
			    }
		
			    return response;
			}

	
	
			@RequestMapping(value = "verifySmsCode.do", method = RequestMethod.POST)
			@ResponseBody
			public HashMap<String, Object> verifySmsCode(
			        @RequestParam("smsAuthNo") String smsAuthNo,
			        @RequestParam("is2Way") boolean is2Way,
			        HttpSession session) {

			    HashMap<String, Object> response = new HashMap<>();
			    try {
			    	// 세션에서 추가 인증 정보 가져오기
			        Integer jobIndex = (Integer) session.getAttribute("jobIndex");
			        Integer threadIndex = (Integer) session.getAttribute("threadIndex");
			        String jti = (String) session.getAttribute("jti");
			        Long twoWayTimestamp = (Long) session.getAttribute("twoWayTimestamp");

			        // 디버깅 로그: 세션 값 검증
			        System.out.println("[DEBUG] 세션 값 검증:");
			        System.out.println("jobIndex: " + jobIndex);
			        System.out.println("threadIndex: " + threadIndex);
			        System.out.println("jti: " + jti);
			        System.out.println("twoWayTimestamp: " + twoWayTimestamp);

			        // 세션 값 검증 실패 시 처리
			        if (jobIndex == null || threadIndex == null || jti == null || twoWayTimestamp == null) {
			            response.put("verified", false);
			            response.put("message", "필수 인증 정보가 누락되었습니다. 다시 시도하세요.");
			            System.err.println("[ERROR] 세션 값이 누락되었습니다.");
			            return response;
			        }
			        
				        // 세션에서 secureNoRequestData 가져오기
				        @SuppressWarnings("unchecked")
				        HashMap<String, Object> requestData = (HashMap<String, Object>) session.getAttribute("secureNoRequestData");
				        System.out.println("[DEBUG] 세션에서 가져온 requestData: " + requestData);
			
				        if (requestData == null || !requestData.containsKey("organization")) {
				            response.put("verified", false);
				            response.put("message", "필수 입력값이 누락되었습니다. 다시 시도하세요.");
				            System.err.println("[ERROR] 세션 데이터 누락 또는 organization 키 없음.");
				            return response;
				        }
			
				        // 문자 발송 서버에 보낼 데이터 구성
				        HashMap<String, Object> verificationRequest = new HashMap<>();
				        verificationRequest.put("smsAuthNo", smsAuthNo);
				        verificationRequest.put("is2Way", is2Way);
			
				        HashMap<String, Object> twoWayInfo = new HashMap<>();
				        twoWayInfo.put("jobIndex", jobIndex);
				        twoWayInfo.put("threadIndex", threadIndex);
				        twoWayInfo.put("jti", jti);
				        twoWayInfo.put("twoWayTimestamp", twoWayTimestamp);
			
				        verificationRequest.put("twoWayInfo", twoWayInfo); // 두웨이 정보 포함
			
				        // 필수 파라미터 추가
				        verificationRequest.put("organization", requestData.get("organization")); // 조직 정보 추가
			
				        // 디버깅 로그: CODEF API 요청 데이터 출력
				        System.out.println("[DEBUG] CODEF API 요청 데이터: " + verificationRequest);
			
				        // CODEF API 호출 준비
				        EasyCodefToken tokenService = new EasyCodefToken();
				        String clientId = "339dc4d8-9138-44a1-a2e3-7cf740b089a9";
				        String clientSecret = "06ab49ab-0fb7-42af-991c-49cc18a76a3f";
				        String accessToken = tokenService.getAccessToken(clientId, clientSecret);
				        
				        
				        if (accessToken.isEmpty()) {
				            response.put("error", "토큰 발급 실패");
				            return response;
				        }

				        // CODEF API 호출
				        EasyCodefConnector connector = new EasyCodefConnector();
				        ObjectMapper objectMapper = new ObjectMapper();
				        
				        String requestBody = objectMapper.writeValueAsString(verificationRequest);

				        HashMap<String, Object> apiResponse = connector.getRequestProduct(
				                "https://development.codef.io/v1/kr/public/hw/hira-list/my-medicine",
				                accessToken,
				                requestBody
				        );

				        // 디버깅 로그: CODEF API 응답 데이터 출력
				        System.out.println("[DEBUG] CODEF API 응답 데이터: " + apiResponse);

			        // CODEF API 응답 처리
			        HashMap<String, Object> result = (HashMap<String, Object>) apiResponse.get("result");
			        if (result != null && "CF-00000".equals(result.get("code"))) { // 성공 코드 확인
			            List<HashMap<String, Object>> data = (List<HashMap<String, Object>>) apiResponse.get("data");

			            if (data != null && !data.isEmpty()) {
			                List<PrescriptionVo> prescriptions = new ArrayList<>();

			                for (HashMap<String, Object> item : data) {
			                    PrescriptionVo prescription = new PrescriptionVo();
			                    prescription.setResMenufactureDate((String) item.get("resManufactureDate"));
			                    prescription.setResPrescribeOrg((String) item.get("resPrescribeOrg"));
			                    prescription.setResTelNo((String) item.get("resTelNo"));
			                    prescription.setCommBrandName((String) item.get("commBrandName"));
			                    prescription.setCommTelNo((String) item.get("resTelNo1"));

			                    // 약물 리스트 매핑
			                    List<HashMap<String, Object>> drugList = (List<HashMap<String, Object>>) item.get("resDrugList");
			                    List<DrugVo> drugs = new ArrayList<>();
			                    for (HashMap<String, Object> drugItem : drugList) {
			                        DrugVo drug = new DrugVo();
			                        drug.setResNumber((String) drugItem.get("resNumber"));
			                        drug.setResDrugName((String) drugItem.get("resDrugName"));
			                        drug.setResDrugCode((String) drugItem.get("resDrugCode"));
			                        drug.setResIngredients((String) drugItem.get("resIngredients"));
			                        drug.setResPrescribeDrugEffect((String) drugItem.get("resPrescribeDrugEffect"));
			                        drug.setResContent((String) drugItem.get("resContent"));
			                        drug.setResOneDose((String) drugItem.get("resOneDose"));
			                        drug.setResDailyDosesNumber((String) drugItem.get("resDailyDosesNumber"));
			                        drug.setResTotalDosingdays((String) drugItem.get("resTotalDosingdays"));

			                        drugs.add(drug);
			                    }
			                    prescription.setDrugs(drugs); // 약물 리스트 추가
			                    prescriptions.add(prescription);
			                }

			                session.setAttribute("finalResultData", prescriptions); // 세션에 저장
			                response.put("verified", true);
			                response.put("message", "SMS 인증 성공");
			            } else {
			                response.put("verified", false);
			                response.put("message", "CODEF API 응답 데이터가 없습니다.");
			            }
			        } else {
			            response.put("verified", false);
			            response.put("message", "CODEF API 요청 실패");
			        }

			    } catch (Exception e) {
			        e.printStackTrace();
			        response.put("verified", false);
			        response.put("message", "SMS 인증 처리 중 오류 발생: " + e.getMessage());
			    }

			    return response;
			}


	
	
	
	@RequestMapping(value = "prescriptionList.do", method = RequestMethod.GET)
	public String prescriptionList(HttpSession session, Model model) {
	    List<PrescriptionVo> prescriptions = (List<PrescriptionVo>) session.getAttribute("finalResultData");
	    if (prescriptions == null || prescriptions.isEmpty()) {
	        return "redirect:/prescription/certification.do"; // 데이터가 없을 경우 인증 페이지로 리다이렉트
	    }
	    model.addAttribute("prescriptions", prescriptions); // 데이터를 모델에 추가
	    return "WEB-INF/prescription/prescriptionList";
	}







	
	@RequestMapping(value = "prescriptionDetail.do", method = RequestMethod.GET)
	public String getPrescriptionDetail(@RequestParam("id") int pidx, HttpSession session, Model model) {
	    System.out.println("📌 받은 처방전 ID 값: " + pidx); // 요청된 ID 확인

	    List<PrescriptionVo> prescriptions = (List<PrescriptionVo>) session.getAttribute("finalResultData");

	    if (prescriptions == null || prescriptions.isEmpty()) {
	        return "redirect:/prescription/prescriptionList.do";
	    }

	    PrescriptionVo selectedPrescription = null;

	    for (PrescriptionVo prescription : prescriptions) {
	        if (prescription.getPidx() == pidx) {  // 실제 pidx 값으로 조회
	            selectedPrescription = prescription;
	            break;
	        }
	    }

	    if (selectedPrescription == null) {
	        return "redirect:/prescription/prescriptionList.do";
	    }

	    model.addAttribute("prescription", selectedPrescription);
	    model.addAttribute("drugs", selectedPrescription.getDrugs());

	    return "WEB-INF/prescription/prescriptionDetail";
	}
	
	







	
}