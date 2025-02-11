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
	
	
	 // CODEF API 愿��젴 �긽�닔
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

	//png file �쑀�슚�꽦寃��궗硫붿꽌�뱶 
	
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
	        // �꽭�뀡�뿉�꽌 �븘�닔 �뜲�씠�꽣 媛��졇�삤湲�
	        @SuppressWarnings("unchecked")
	        HashMap<String, Object> requestData = (HashMap<String, Object>) session.getAttribute("secureNoRequestData");

	        if (requestData == null) {
	            response.put("error", "�븘�닔 �엯�젰媛믪씠 �늻�씫�릺�뿀�뒿�땲�떎. �떎�떆 �씤利앹쓣 吏꾪뻾�븯�꽭�슂.");
	            return response;
	        }

	        // �깉濡쒓퀬移� �뵆�옒洹� 異붽�
	        requestData.put("refresh", true);

	        // CODEF API �샇異� 以�鍮�
	        EasyCodefToken tokenService = new EasyCodefToken();
	        String clientId = "339dc4d8-9138-44a1-a2e3-7cf740b089a9";
	        String clientSecret = "06ab49ab-0fb7-42af-991c-49cc18a76a3f";
	        String accessToken = tokenService.getAccessToken(clientId, clientSecret);

	        if (accessToken.isEmpty()) {
	            response.put("error", "�넗�겙 諛쒓툒 �떎�뙣");
	            return response;
	        }

	        // CODEF API �샇異�
	        EasyCodefConnector connector = new EasyCodefConnector();
	        ObjectMapper objectMapper = new ObjectMapper();
	        String requestBody = objectMapper.writeValueAsString(requestData);

	        HashMap<String, Object> apiResponse = connector.getRequestProduct(
	                "https://development.codef.io/v1/kr/public/hw/hira-list/my-medicine",
	                accessToken,
	                requestBody
	        );

	        System.out.println("[DEBUG] CODEF �쓳�떟 �뜲�씠�꽣: " + apiResponse);

	        // �쓳�떟�뿉�꽌 �깉濡쒖슫 蹂댁븞臾몄옄 �뜲�씠�꽣 異붿텧
	        if (apiResponse.containsKey("data")) {
	            HashMap<String, Object> data = (HashMap<String, Object>) apiResponse.get("data");
	            HashMap<String, Object> extraInfo = (HashMap<String, Object>) data.get("extraInfo");

	            if (extraInfo != null && extraInfo.containsKey("reqSecureNo")) {
	                String reqSecureNo = (String) extraInfo.get("reqSecureNo");

	                // �닚�닔 Base64 �뜲�씠�꽣留� 諛섑솚
	                response.put("reqSecureNoDecoded", reqSecureNo);
	                logger.info("[DEBUG] �깉濡쒖슫 蹂댁븞臾몄옄 �깮�꽦 �꽦怨�.");
	            } else {
	                response.put("error", "�깉濡쒖슫 蹂댁븞臾몄옄瑜� 媛��졇�삱 �닔 �뾾�뒿�땲�떎.");
	                logger.error("[ERROR] �깉濡쒖슫 蹂댁븞臾몄옄 �깮�꽦 �떎�뙣.");
	            }


	        } else {
	            response.put("error", "API �쓳�떟�뿉 �뜲�씠�꽣媛� �뾾�뒿�땲�떎.");
	            logger.error("[ERROR] API �쓳�떟�뿉 �뜲�씠�꽣媛� �뾾�뒿�땲�떎.");
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        response.put("error", "蹂댁븞臾몄옄 �깮�꽦 以� �삤瑜� 諛쒖깮");
	    }
	    return response;
	}

	
	private HashMap<String, Object> callCodefApi(String accessToken, HashMap<String, Object> requestData) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            EasyCodefConnector connector = new EasyCodefConnector();
            ObjectMapper objectMapper = new ObjectMapper();

            // CODEF API �샇異�
            HashMap<String, Object> apiResponse = connector.getRequestProduct(
                    API_URL,
                    accessToken,
                    objectMapper.writeValueAsString(requestData)
            );

            logger.info("[DEBUG] CODEF �쓳�떟 �뜲�씠�꽣: {}", apiResponse);

            // �쓳�떟 泥섎━
            if (apiResponse.containsKey("data")) {
                response.putAll((HashMap<String, Object>) apiResponse.get("data"));
            } else {
                response.put("error", "API �쓳�떟�뿉 �뜲�씠�꽣媛� �뾾�뒿�땲�떎.");
            }
        } catch (Exception e) {
            logger.error("CODEF API �샇異� 以� �삤瑜� 諛쒖깮: {}", e.getMessage());
            response.put("error", "CODEF API �샇異� 以� �삤瑜� 諛쒖깮: " + e.getMessage());
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
	    	
	        // 二쇰�쇰벑濡앸쾲�샇 諛� �쑕���룿 踰덊샇 �빀移섍린
	        String fullIdNumber = idNumberFront + idNumberBack;
	        String fullPhoneNumber = phoneNumber;

	        // CODEF �슂泥� �뜲�씠�꽣 援ъ꽦
	        HashMap<String, Object> requestData = new HashMap<>();
	        requestData.put("organization", "0020"); // 湲곌� 肄붾뱶
	        requestData.put("loginType", "2");
	        requestData.put("identity", fullIdNumber); // 二쇰�쇰벑濡앸쾲�샇
	        requestData.put("loginTypeLevel", "1");
	        requestData.put("userName", name);
	        requestData.put("telecom", telecom);
	        requestData.put("phoneNo", fullPhoneNumber);
	        requestData.put("authMethod", "0"); // SMS �씤利�

	        // �꽭�뀡�뿉 �븘�닔 �뜲�씠�꽣 ���옣 (蹂댁븞臾몄옄 �깉濡쒓퀬移⑥슜)
	        session.setAttribute("secureNoRequestData", requestData);
	        session.setAttribute("phoneNumber", fullPhoneNumber);

	        // CODEF API �샇異� 以�鍮�
	        EasyCodefToken tokenService = new EasyCodefToken();
	        String clientId = "339dc4d8-9138-44a1-a2e3-7cf740b089a9";
	        String clientSecret = "06ab49ab-0fb7-42af-991c-49cc18a76a3f";
	        String accessToken = tokenService.getAccessToken(clientId, clientSecret);
	        
	        

	        if (accessToken.isEmpty()) {
	            response.put("error", "�넗�겙 諛쒓툒 �떎�뙣");
	            return response;
	        }

	        // CODEF API �샇異�
	        EasyCodefConnector connector = new EasyCodefConnector();
	        ObjectMapper objectMapper = new ObjectMapper();

	        String requestBody = objectMapper.writeValueAsString(requestData);

	        HashMap<String, Object> apiResponse = connector.getRequestProduct(
	                "https://development.codef.io/v1/kr/public/hw/hira-list/my-medicine",
	                accessToken,
	                requestBody
	        );

	        System.out.println("[DEBUG] CODEF �쓳�떟 �뜲�씠�꽣: " + apiResponse);

	        // 蹂댁븞臾몄옄 泥섎━
	        if (apiResponse.containsKey("data")) {
	            HashMap<String, Object> data = (HashMap<String, Object>) apiResponse.get("data");

	            if (Boolean.TRUE.equals(data.get("continue2Way"))) {
	                // 異붽� �씤利앹씠 �븘�슂�븳 寃쎌슦
	                HashMap<String, Object> extraInfo = (HashMap<String, Object>) data.get("extraInfo");

	                if (extraInfo != null) {
	                    System.out.println("[DEBUG] extraInfo 媛앹껜 �솗�씤: " + extraInfo);

	                    String reqSecureNo = (String) extraInfo.get("reqSecureNo"); // 蹂댁븞臾몄옄 �뜲�씠�꽣 異붿텧
	                    System.out.println("[DEBUG] reqSecureNo 媛�: " + reqSecureNo);

	                    if (reqSecureNo != null && !reqSecureNo.isEmpty()) {
	                        if (reqSecureNo.startsWith("data:image/png;base64,")) {
	                            reqSecureNo = reqSecureNo.substring("data:image/png;base64,".length());
	                        }

	                        byte[] decodedBytes = Base64.getDecoder().decode(reqSecureNo);

	                        if (!isPng(decodedBytes)) {
	                            response.put("error", "�쑀�슚�븯吏� �븡�� PNG �씠誘몄�");
	                            return response;
	                        }

	                        String reEncodedBase64 = "data:image/png;base64," + Base64.getEncoder().encodeToString(decodedBytes);
	                        response.put("reqSecureNoDecoded", reEncodedBase64); // �겢�씪�씠�뼵�듃濡� 諛섑솚
	                        response.put("redirectToSecureInput", true); // 異붽� �씤利� �뵆�옒洹� �꽕�젙

	                        // �꽭�뀡�뿉 異붽� �씤利� 愿��젴 �뜲�씠�꽣 ���옣
	                        session.setAttribute("jobIndex", data.get("jobIndex"));
	                        session.setAttribute("threadIndex", data.get("threadIndex"));
	                        session.setAttribute("jti", data.get("jti"));
	                        session.setAttribute("twoWayTimestamp", data.get("twoWayTimestamp"));
	                    } else {
	                        System.err.println("[ERROR] 蹂댁븞臾몄옄 �뜲�씠�꽣媛� �뾾�뒿�땲�떎.");
	                    }
	                } else {
	                    System.err.println("[ERROR] extraInfo 媛앹껜媛� null�엯�땲�떎.");
	                }
	            } else {
	                // 異붽� �씤利앹씠 �븘�슂�븯吏� �븡�� 寃쎌슦
	                response.put("redirectToSecureInput", false);
	            }

	            response.putAll(data); // �븘�슂�븳 異붽� �뜲�씠�꽣 �룷�븿
	            response.put("success", true);
	            return response;
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    response.put("error", "�슂泥� 泥섎━ 以� �삤瑜� 諛쒖깮");
	    return response;
	}


	private boolean isBase64(String str) {
	    try {
	        Base64.getDecoder().decode(str); // �뵒肄붾뵫 �떆�룄
	        return true; // �뵒肄붾뵫 �꽦怨� �떆 �쑀�슚�븳 Base64 臾몄옄�뿴
	    } catch (IllegalArgumentException e) {
	        return false; // �뵒肄붾뵫 �떎�뙣 �떆 �쑀�슚�븯吏� �븡�� 臾몄옄�뿴
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
			        // �꽭�뀡�뿉�꽌 �븘�닔 �뜲�씠�꽣 媛��졇�삤湲�
			        Integer jobIndex = (Integer) session.getAttribute("jobIndex");
			        Integer threadIndex = (Integer) session.getAttribute("threadIndex");
			        String jti = (String) session.getAttribute("jti");
			        Long twoWayTimestamp = (Long) session.getAttribute("twoWayTimestamp");
			        String phoneNumber = (String) session.getAttribute("phoneNumber");
		
			        if (phoneNumber == null) {
			            response.put("error", "�꽭�뀡�뿉 �쟾�솕踰덊샇 �젙蹂닿� �뾾�뒿�땲�떎. �떎�떆 �씤利앹쓣 吏꾪뻾�븯�꽭�슂.");
			            return response;
			        }
		
			        if (jobIndex == null || threadIndex == null || jti == null || twoWayTimestamp == null) {
			            response.put("error", "�븘�닔 �엯�젰媛믪씠 �늻�씫�릺�뿀�뒿�땲�떎. �떎�떆 �씤利앹쓣 吏꾪뻾�븯�꽭�슂.");
			            return response;
			        }
		
			        // �꽭�뀡�뿉 ���옣�맂 �슂泥� �뜲�씠�꽣 媛��졇�삤湲�
			        @SuppressWarnings("unchecked")
			        HashMap<String, Object> requestData = (HashMap<String, Object>) session.getAttribute("secureNoRequestData");
		
			        if (requestData == null || !requestData.containsKey("organization")) {
			            response.put("error", "�븘�닔 �엯�젰媛믪씠 �늻�씫�릺�뿀�뒿�땲�떎.");
			            return response;
			        }
		
			        // 異붽� �씤利� �슂泥� �뜲�씠�꽣 援ъ꽦
			        HashMap<String, Object> twoWayData = new HashMap<>();
			        twoWayData.put("secureNo", secureNo); // 蹂댁븞臾몄옄 �젙蹂�
			        twoWayData.put("secureNoRefresh", secureNoRefresh); // �깉濡쒓퀬移� �젙蹂�
			        twoWayData.put("is2Way", true); // 異붽� �슂泥� �뿬遺�
		
			        // �몢�썾�씠 �젙蹂� �룷�븿
			        HashMap<String, Object> twoWayInfo = new HashMap<>();
			        twoWayInfo.put("jobIndex", jobIndex);
			        twoWayInfo.put("threadIndex", threadIndex);
			        twoWayInfo.put("jti", jti);
			        twoWayInfo.put("twoWayTimestamp", twoWayTimestamp);
			        response.put("jti", jti);
			        
			        twoWayData.put("twoWayInfo", twoWayInfo);
			        twoWayData.put("organization", requestData.get("organization"));
		
			        // CODEF API �샇異� 以�鍮�
			        EasyCodefToken tokenService = new EasyCodefToken();
			        String accessToken = tokenService.getAccessToken(CLIENT_ID, CLIENT_SECRET);
		
			        if (accessToken.isEmpty()) {
			            response.put("error", "�넗�겙 諛쒓툒 �떎�뙣");
			            return response;
			        }
		
			        // CODEF API �샇異�
			        EasyCodefConnector connector = new EasyCodefConnector();
			        ObjectMapper objectMapper = new ObjectMapper();
			        String requestBody = objectMapper.writeValueAsString(twoWayData);
		
			        HashMap<String, Object> apiResponse = connector.getRequestProduct(
			                API_URL,
			                accessToken,
			                requestBody
			        );
		
			        System.out.println("[DEBUG] 異붽� �씤利� �쓳�떟 �뜲�씠�꽣: " + apiResponse);
		
			        // �쓳�떟 泥섎━
			        if (apiResponse.containsKey("data")) {
			            HashMap<String, Object> data = (HashMap<String, Object>) apiResponse.get("data");
		
			            if (Boolean.TRUE.equals(data.get("continue2Way"))) {
			                HashMap<String, Object> extraInfo = (HashMap<String, Object>) data.get("extraInfo");
		
			                String reqSMSAuthNo = "";
			                if (extraInfo != null && extraInfo.containsKey("reqSMSAuthNo")) {
			                    reqSMSAuthNo = (String) extraInfo.get("reqSMSAuthNo");
			                }
		
			                if (reqSMSAuthNo == null || reqSMSAuthNo.isEmpty()) {
			                    System.out.println("[WARN] SMS �씤利앸쾲�샇媛� 鍮꾩뼱 �엳吏�留�, API �슂泥��씠 �젙�긽�쟻�쑝濡� 泥섎━�맖.");
			                    response.put("success", true);
			                    response.put("message", "SMS �씤利앸쾲�샇媛� �쟾�넚�릺�뿀�뒿�땲�떎. �씤利앸쾲�샇瑜� �엯�젰�븯�꽭�슂.");
			                } else {
			                    response.put("success", true);
			                    response.put("reqSMSAuthNo", reqSMSAuthNo);
			                    response.put("message", "SMS �씤利앸쾲�샇媛� �쟾�넚�릺�뿀�뒿�땲�떎.");
			                }
			            }
			            return response;
			        } else {
			            response.put("success", false);
			            response.put("errorMessage", "API �쓳�떟�뿉 result �뜲�씠�꽣媛� �뾾�뒿�땲�떎.");
			            return response;
			        }
		
			    } catch (Exception e) {
			        e.printStackTrace();
			        response.put("success", false);
			        response.put("errorMessage", "異붽� �씤利� 以� �삤瑜� 諛쒖깮: " + e.getMessage());
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
			    	// �꽭�뀡�뿉�꽌 異붽� �씤利� �젙蹂� 媛��졇�삤湲�
			        Integer jobIndex = (Integer) session.getAttribute("jobIndex");
			        Integer threadIndex = (Integer) session.getAttribute("threadIndex");
			        String jti = (String) session.getAttribute("jti");
			        Long twoWayTimestamp = (Long) session.getAttribute("twoWayTimestamp");

			        // �뵒踰꾧퉭 濡쒓렇: �꽭�뀡 媛� 寃�利�
			        System.out.println("[DEBUG] �꽭�뀡 媛� 寃�利�:");
			        System.out.println("jobIndex: " + jobIndex);
			        System.out.println("threadIndex: " + threadIndex);
			        System.out.println("jti: " + jti);
			        System.out.println("twoWayTimestamp: " + twoWayTimestamp);

			        // �꽭�뀡 媛� 寃�利� �떎�뙣 �떆 泥섎━
			        if (jobIndex == null || threadIndex == null || jti == null || twoWayTimestamp == null) {
			            response.put("verified", false);
			            response.put("message", "�븘�닔 �씤利� �젙蹂닿� �늻�씫�릺�뿀�뒿�땲�떎. �떎�떆 �떆�룄�븯�꽭�슂.");
			            System.err.println("[ERROR] �꽭�뀡 媛믪씠 �늻�씫�릺�뿀�뒿�땲�떎.");
			            return response;
			        }
			        
				        // �꽭�뀡�뿉�꽌 secureNoRequestData 媛��졇�삤湲�
				        @SuppressWarnings("unchecked")
				        HashMap<String, Object> requestData = (HashMap<String, Object>) session.getAttribute("secureNoRequestData");
				        System.out.println("[DEBUG] �꽭�뀡�뿉�꽌 媛��졇�삩 requestData: " + requestData);
			
				        if (requestData == null || !requestData.containsKey("organization")) {
				            response.put("verified", false);
				            response.put("message", "�븘�닔 �엯�젰媛믪씠 �늻�씫�릺�뿀�뒿�땲�떎. �떎�떆 �떆�룄�븯�꽭�슂.");
				            System.err.println("[ERROR] �꽭�뀡 �뜲�씠�꽣 �늻�씫 �삉�뒗 organization �궎 �뾾�쓬.");
				            return response;
				        }
			
				        // 臾몄옄 諛쒖넚 �꽌踰꾩뿉 蹂대궪 �뜲�씠�꽣 援ъ꽦
				        HashMap<String, Object> verificationRequest = new HashMap<>();
				        verificationRequest.put("smsAuthNo", smsAuthNo);
				        verificationRequest.put("is2Way", is2Way);
			
				        HashMap<String, Object> twoWayInfo = new HashMap<>();
				        twoWayInfo.put("jobIndex", jobIndex);
				        twoWayInfo.put("threadIndex", threadIndex);
				        twoWayInfo.put("jti", jti);
				        twoWayInfo.put("twoWayTimestamp", twoWayTimestamp);
			
				        verificationRequest.put("twoWayInfo", twoWayInfo); // �몢�썾�씠 �젙蹂� �룷�븿
			
				        // �븘�닔 �뙆�씪誘명꽣 異붽�
				        verificationRequest.put("organization", requestData.get("organization")); // 議곗쭅 �젙蹂� 異붽�
			
				        // �뵒踰꾧퉭 濡쒓렇: CODEF API �슂泥� �뜲�씠�꽣 異쒕젰
				        System.out.println("[DEBUG] CODEF API �슂泥� �뜲�씠�꽣: " + verificationRequest);
			
				        // CODEF API �샇異� 以�鍮�
				        EasyCodefToken tokenService = new EasyCodefToken();
				        String clientId = "339dc4d8-9138-44a1-a2e3-7cf740b089a9";
				        String clientSecret = "06ab49ab-0fb7-42af-991c-49cc18a76a3f";
				        String accessToken = tokenService.getAccessToken(clientId, clientSecret);
				        
				        
				        if (accessToken.isEmpty()) {
				            response.put("error", "�넗�겙 諛쒓툒 �떎�뙣");
				            return response;
				        }

				        // CODEF API �샇異�
				        EasyCodefConnector connector = new EasyCodefConnector();
				        ObjectMapper objectMapper = new ObjectMapper();
				        
				        String requestBody = objectMapper.writeValueAsString(verificationRequest);

				        HashMap<String, Object> apiResponse = connector.getRequestProduct(
				                "https://development.codef.io/v1/kr/public/hw/hira-list/my-medicine",
				                accessToken,
				                requestBody
				        );

				        // �뵒踰꾧퉭 濡쒓렇: CODEF API �쓳�떟 �뜲�씠�꽣 異쒕젰
				        System.out.println("[DEBUG] CODEF API �쓳�떟 �뜲�씠�꽣: " + apiResponse);

			        // CODEF API �쓳�떟 泥섎━
			        HashMap<String, Object> result = (HashMap<String, Object>) apiResponse.get("result");
			        if (result != null && "CF-00000".equals(result.get("code"))) { // �꽦怨� 肄붾뱶 �솗�씤
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

			                    // �빟臾� 由ъ뒪�듃 留ㅽ븨
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
			                    prescription.setDrugs(drugs); // �빟臾� 由ъ뒪�듃 異붽�
			                    prescriptions.add(prescription);
			                }

			                session.setAttribute("finalResultData", prescriptions); // �꽭�뀡�뿉 ���옣
			                response.put("verified", true);
			                response.put("message", "SMS �씤利� �꽦怨�");
			            } else {
			                response.put("verified", false);
			                response.put("message", "CODEF API �쓳�떟 �뜲�씠�꽣媛� �뾾�뒿�땲�떎.");
			            }
			        } else {
			            response.put("verified", false);
			            response.put("message", "CODEF API �슂泥� �떎�뙣");
			        }

			    } catch (Exception e) {
			        e.printStackTrace();
			        response.put("verified", false);
			        response.put("message", "SMS �씤利� 泥섎━ 以� �삤瑜� 諛쒖깮: " + e.getMessage());
			    }

			    return response;
			}


	
	
	
	@RequestMapping(value = "prescriptionList.do", method = RequestMethod.GET)
	public String prescriptionList(HttpSession session, Model model) {
	    List<PrescriptionVo> prescriptions = (List<PrescriptionVo>) session.getAttribute("finalResultData");
	    if (prescriptions == null || prescriptions.isEmpty()) {
	        return "redirect:/prescription/certification.do"; // �뜲�씠�꽣媛� �뾾�쓣 寃쎌슦 �씤利� �럹�씠吏�濡� 由щ떎�씠�젆�듃
	    }
	    model.addAttribute("prescriptions", prescriptions); // �뜲�씠�꽣瑜� 紐⑤뜽�뿉 異붽�
	    return "WEB-INF/prescription/prescriptionList";
	}







	
	@RequestMapping(value = "prescriptionDetail.do", method = RequestMethod.GET)
	public String getPrescriptionDetail(@RequestParam("id") int pidx, HttpSession session, Model model) {
	    System.out.println("�윋� 諛쏆� 泥섎갑�쟾 ID 媛�: " + pidx); // �슂泥��맂 ID �솗�씤

	    List<PrescriptionVo> prescriptions = (List<PrescriptionVo>) session.getAttribute("finalResultData");

	    if (prescriptions == null || prescriptions.isEmpty()) {
	        return "redirect:/prescription/prescriptionList.do";
	    }

	    PrescriptionVo selectedPrescription = null;

	    for (PrescriptionVo prescription : prescriptions) {
	        if (prescription.getPidx() == pidx) {  // �떎�젣 pidx 媛믪쑝濡� 議고쉶
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
	
	// �엫�떆 �쉶�썝 ���옣
	@RequestMapping(value = "savePrescriptionWithoutMember.do", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> savePrescriptionWithoutMember(
	        @RequestParam("name") String name,
	        @RequestParam("phoneNumber") String phoneNumber,
	        @RequestBody List<PrescriptionVo> prescriptions) {

	    HashMap<String, Object> response = new HashMap<>();
	    try {
	        // �엫�떆 �궗�슜�옄 ID �깮�꽦
	        String tempId = "TEMP_" + java.util.UUID.randomUUID().toString();

	        // �엫�떆 �궗�슜�옄 �젙蹂� DB ���옣
	        MemberVo tempMember = new MemberVo();
	        tempMember.setId(tempId);
	        tempMember.setName(name);
	        tempMember.setPhone(phoneNumber);
	        
	        tempMember.setDelyn("N");
	        
	        

	        int midx = memberService.saveMemberInfo(tempMember); // �궫�엯�맂 midx 媛��졇�삤湲�

	        // �궗�슜�옄 �젙蹂� ���옣 �꽦怨� �떆 泥섎갑�쟾 ���옣
	        if (midx > 0) {
	            for (PrescriptionVo prescription : prescriptions) {
	                prescription.setMidx(midx); // midx瑜� PrescriptionVo�� �뿰寃�
	                
	                prescriptionService.savePrescription(prescription);
	            }
	            response.put("success", true);
	            response.put("message", "�엫�떆 �궗�슜�옄 諛� 泥섎갑�쟾 �젙蹂닿� ���옣�릺�뿀�뒿�땲�떎.");
	        } else {
	            response.put("success", false);
	            response.put("message", "�엫�떆 �궗�슜�옄 �젙蹂대�� ���옣�븯�뒗 �뜲 �떎�뙣�뻽�뒿�땲�떎.");
	        }

	    } catch (Exception e) {
	        response.put("success", false);
	        response.put("error", "�뜲�씠�꽣 ���옣 以� �삤瑜� 諛쒖깮: " + e.getMessage());
	    }

	    return response;
	}








	
}