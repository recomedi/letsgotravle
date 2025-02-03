package com.letsgotravle.myapp.api;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <pre>
 * io.codef.easycodef
 *   |_ EasyCodef.java
 * </pre>
 * 
 * Desc : 코드?��?��?�� ?��?�� ?��?��?�� ?��?�� ?��?�� ?��?��브러�? ?��?��?�� 
 * @Company : ©CODEF corp.
 * @Author  : notfound404@codef.io
 * @Date    : Jun 26, 2020 3:28:31 PM
 */
public class EasyCodef {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * EasyCodef ?��?��?�� ?��?�� �??�� ?��?�� ?��브젝?��
	 */
	private EasyCodefProperties properties = new EasyCodefProperties();

	/**
	 * Desc : ?��?��?���? ?��?��?�� ?��?�� ?��?��?��?��?�� ?���? ?��?��
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:30:59 PM
	 * @param clientId
	 * @param clientSecret
	 */
	public void setClientInfo(String clientId, String clientSecret) {
		properties.setClientInfo(clientId, clientSecret);
	}
	
	/**
	 * Desc : ?��모서�? ?��?��?�� ?��?�� ?��?��?��?��?�� ?���? ?��?��
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:31:12 PM
	 * @param demoClientId
	 * @param demoClientSecret
	 */
	public void setClientInfoForDemo(String demoClientId, String demoClientSecret) {
		properties.setClientInfoForDemo(demoClientId, demoClientSecret);
	}
	
	/**
	 * Desc : RSA?��?��?���? ?��?�� ?��블릭?�� ?��?��
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:31:24 PM
	 * @param publicKey
	 */
	public void setPublicKey(String publicKey) {
		properties.setPublicKey(publicKey);
	}
	
	/**
	 * Desc : RSA?��?��?���? ?��?�� ?��블릭?�� 반환
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:32:25 PM
	 * @return
	 */
	public String getPublicKey() {
		return properties.getPublicKey();
	}
	
	/**
	 * Desc : ?��?�� ?���? 
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:32:31 PM
	 * @param productUrl
	 * @param serviceType
	 * @param parameterMap
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String requestProduct(String productUrl, EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		boolean validationFlag = true;
		
		/**	#1.?��?�� ?���? 체크 - ?��?��?��?��?�� ?���?	*/
		validationFlag = checkClientInfo(serviceType.getServiceType());
		if(!validationFlag) {
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.EMPTY_CLIENT_INFO);
			return mapper.writeValueAsString(response);
		}
		
		/**	#2.?��?�� ?���? 체크 - ?��블릭 ?��	*/
		validationFlag = checkPublicKey();
		if(!validationFlag) {
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.EMPTY_PUBLIC_KEY);
			return mapper.writeValueAsString(response);
		}
		
		/**	#3.추�??���? ?��?��?�� 체크	*/
		validationFlag = checkTwoWayKeyword(parameterMap);
		if(!validationFlag) {
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.INVALID_2WAY_KEYWORD);
			return mapper.writeValueAsString(response);
		}
		
		/**	#4.?��?�� 조회 ?���?	*/
		EasyCodefResponse response = EasyCodefConnector.execute(productUrl, serviceType.getServiceType(), parameterMap, properties);
		
		/**	#5.결과 반환	*/
		return mapper.writeValueAsString(response);
	}
	
	/**
	 * Desc : ?��?�� 추�??���? ?���?
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:32:41 PM
	 * @param productUrl
	 * @param serviceType
	 * @param parameterMap
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String requestCertification(String productUrl, EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		boolean validationFlag = true;
		
		/**	#1.?��?�� ?���? 체크 - ?��?��?��?��?�� ?���?	*/
		validationFlag = checkClientInfo(serviceType.getServiceType());
		if(!validationFlag) {
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.EMPTY_CLIENT_INFO);
			return mapper.writeValueAsString(response);
		}
		
		/**	#2.?��?�� ?���? 체크 - ?��블릭 ?��	*/
		validationFlag = checkPublicKey();
		if(!validationFlag) {
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.EMPTY_PUBLIC_KEY);
			return mapper.writeValueAsString(response);
		}
		
		/**	#3.추�??���? ?��?��미터 ?��?�� ?��?�� 체크	*/
		validationFlag = checkTwoWayInfo(parameterMap);
		if(!validationFlag) {
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.INVALID_2WAY_INFO);
			return mapper.writeValueAsString(response);
		}
		
		/**	#4.?��?�� 조회 ?���?	*/
		EasyCodefResponse response = EasyCodefConnector.execute(productUrl, serviceType.getServiceType(), parameterMap, properties);
		
		/**	#5.결과 반환	*/
		return mapper.writeValueAsString(response);
	}
	
	
	/**
	 * Desc : ?��비스 ???��?�� ?���? ?��?��?��?��?�� ?���? ?��?�� ?��?��
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:33:23 PM
	 * @param serviceType
	 * @return
	 */
	private boolean checkClientInfo(int serviceType) {
		if(serviceType == 0) {
			if(properties.getClientId() == null || "".equals(properties.getClientId().trim())) {
				return false;
			}
			if(properties.getClientSecret() == null || "".equals(properties.getClientSecret().trim())) {
				return false;
			}
		} else if(serviceType == 1) {
			if(properties.getDemoClientId() == null || "".equals(properties.getDemoClientId().trim())) {
				return false;
			}
			if(properties.getDemoClientSecret() == null || "".equals(properties.getDemoClientSecret().trim())) {
				return false;
			}
		} else {
			if(EasyCodefConstant.SANDBOX_CLIENT_ID == null || "".equals(EasyCodefConstant.SANDBOX_CLIENT_ID.trim())) {
				return false;
			}
			if(EasyCodefConstant.SANDBOX_CLIENT_SECRET == null || "".equals(EasyCodefConstant.SANDBOX_CLIENT_SECRET.trim())) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Desc : ?��블릭?�� ?���? ?��?�� ?��?��
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:33:31 PM
	 * @return
	 */
	private boolean checkPublicKey() {
		if(properties.getPublicKey() == null || "".equals(properties.getPublicKey().trim())) {
			return false;
		}
		return true;
	}
	
	/**
	 * Desc : 추�??���? ?��?��미터 ?��?�� ?��?��
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:33:39 PM
	 * @param parameterMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private boolean checkTwoWayInfo(HashMap<String, Object> parameterMap) {
		if(!parameterMap.containsKey("is2Way") || !(parameterMap.get("is2Way") instanceof Boolean) || !(boolean)parameterMap.get("is2Way")){
			return false;
		}
		
		if(!parameterMap.containsKey("twoWayInfo")) {
			return false;
		}
		
		HashMap<String, Object> twoWayInfoMap = (HashMap<String, Object>)parameterMap.get("twoWayInfo");
		if(!twoWayInfoMap.containsKey("jobIndex") || !twoWayInfoMap.containsKey("threadIndex") || !twoWayInfoMap.containsKey("jti") || !twoWayInfoMap.containsKey("twoWayTimestamp")){
			return false;
		}
		
		return true;
	}
	
	/**
	 * Desc : 추�??���? ?��?��?�� ?��?��
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:33:45 PM
	 * @param parameterMap
	 * @return
	 */
	private boolean checkTwoWayKeyword(HashMap<String, Object> parameterMap) {
		if(parameterMap != null && (parameterMap.containsKey("is2Way") || parameterMap.containsKey("twoWayInfo"))) {
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * Desc : connectedId 발급?�� ?��?�� 계정 ?���?
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:34:02 PM
	 * @param serviceType
	 * @param parameterMap
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String createAccount(EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.CREATE_ACCOUNT, serviceType, parameterMap);
	}
	
	/**
	 * Desc : 계정 ?���? 추�?
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:34:11 PM
	 * @param serviceType
	 * @param parameterMap
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String addAccount(EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.ADD_ACCOUNT, serviceType, parameterMap);
	}
	
	/**
	 * Desc : 계정 ?���? ?��?�� 
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:34:21 PM
	 * @param serviceType
	 * @param parameterMap
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String updateAccount(EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.UPDATE_ACCOUNT, serviceType, parameterMap);
	}
	
	/**
	 * Desc : 계정 ?���? ?��?��
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:34:30 PM
	 * @param serviceType
	 * @param parameterMap
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String deleteAccount(EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.DELETE_ACCOUNT, serviceType, parameterMap);
	}
	
	/**
	 * Desc : connectedId�? ?��록된 계정 목록 조회
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:34:37 PM
	 * @param serviceType
	 * @param parameterMap
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String getAccountList(EasyCodefServiceType serviceType, HashMap<String, Object> parameterMap) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.GET_ACCOUNT_LIST, serviceType, parameterMap);
	}
	
	/**
	 * Desc : ?��?��?��?��?�� ?��보로 ?��록된 모든 connectedId 목록 조회
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:34:44 PM
	 * @param serviceType
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws InterruptedException
	 */
	public String getConnectedIdList(EasyCodefServiceType serviceType) throws UnsupportedEncodingException, JsonProcessingException, InterruptedException {
		return requestProduct(EasyCodefConstant.GET_CID_LIST, serviceType, null);
	}
	
	/**
	 * Desc : ?��?�� 반환 ?���? - 보유 중인 ?��?��?�� ?��?��?�� ?��?�� 경우 반환, ?��?�� 경우 ?���? 발급 ?�� 반환
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:35:03 PM
	 * @param serviceType
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public String requestToken(EasyCodefServiceType serviceType) throws JsonParseException, JsonMappingException, IOException {
		String clientId = null;
		String clientSecret = null;
		
		if(serviceType.getServiceType() == 0) {
			clientId = properties.getClientId();
			clientSecret = properties.getClientSecret();
		} else if(serviceType.getServiceType() == 1) {
			clientId = properties.getDemoClientId();
			clientSecret = properties.getDemoClientSecret();
		} else {
			clientId = EasyCodefConstant.SANDBOX_CLIENT_ID;
			clientSecret = EasyCodefConstant.SANDBOX_CLIENT_SECRET;
		}
		
		String accessToken = EasyCodefTokenMap.getToken(clientId); // 보유 중인 ?��?��?�� ?��?�� 경우 반환
		if(accessToken != null) {
			HashMap<String, Object> tokenMap = EasyCodefUtil.getTokenMap(accessToken);
			if(EasyCodefUtil.checkValidity((int)(tokenMap.get("exp")))) {	// ?��?��?�� ?��?�� 기간 ?��?��
				return accessToken;	// ?��?�� ?��?��?�� 경우 반환
			}
		}
		
		HashMap<String, Object> tokenMap = EasyCodefConnector.publishToken(clientId, clientSecret);	// 보유 중인 ?��?��?�� ?��거나 ?���? 발급 조건?�� ?��?��?��?�� 경우 발급 ?�� 반환(만료?��?���? �??��거나 ?��?���? ?��?���? ?��?��?�� 경우 ?���? 발급)
		if(tokenMap != null) {
			accessToken = (String)tokenMap.get("access_token");
			EasyCodefTokenMap.setToken(clientId, accessToken);	// 발급 ?��?�� ???��
			return accessToken;
		} else {
			return null;
		}
	}
	
	/**
	 * Desc : ?��?�� ?���? 발급 ?�� 반환(코드?��?�� ?��?�� �? 추�? ?���? ?��?��?�� ?��?�� ?�� ?��?�� 권한 �?경이 ?��?��?��거나 ?���? ?��?��?�� ?��?��?�� 경우?�� ?��?��)
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Sep 16, 2020 11:58:32 AM
	 * @param serviceType
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public String requestNewToken(EasyCodefServiceType serviceType) throws JsonParseException, JsonMappingException, IOException {
		String clientId = null;
		String clientSecret = null;
		
		if(serviceType.getServiceType() == 0) {
			clientId = properties.getClientId();
			clientSecret = properties.getClientSecret();
		} else if(serviceType.getServiceType() == 1) {
			clientId = properties.getDemoClientId();
			clientSecret = properties.getDemoClientSecret();
		} else {
			clientId = EasyCodefConstant.SANDBOX_CLIENT_ID;
			clientSecret = EasyCodefConstant.SANDBOX_CLIENT_SECRET;
		}
		
		String accessToken = null;
		HashMap<String, Object> tokenMap = EasyCodefConnector.publishToken(clientId, clientSecret);	// ?��?�� ?���? 발급
		if(tokenMap != null) {
			accessToken = (String)tokenMap.get("access_token");
			EasyCodefTokenMap.setToken(clientId, accessToken);	// 발급 ?��?�� ???��
			return accessToken;
		} else {
			return null;
		}
	}
}
