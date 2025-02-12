package com.letsgotravel.myapp.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <pre>
 * io.codef.easycodef
 *   |_ EasyCodefConnector.java
 * </pre>
 * 
 * Desc : CODEF ?��?��?�� ?��?�� �? ?��?�� 조회�? ?��?�� HTTP ?���? ?��?��?��
 * @Company : ©CODEF corp.
 * @Author  : notfound404@codef.io
 * @Date    : Jun 26, 2020 3:35:17 PM
 */
public class EasyCodefConnector {
	private static ObjectMapper mapper = new ObjectMapper();
	private static final int REPEAT_COUNT = 3;
	
	/**
	 * Desc : CODEF ?��?�� 조회 ?���?
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:35:26 PM
	 * @param urlPath
	 * @param serviceType
	 * @param bodyMap
	 * @param properties
	 * @return
	 * @throws InterruptedException
	 */
	@SuppressWarnings("unchecked")
	protected static EasyCodefResponse execute(String urlPath, int serviceType, HashMap<String, Object> bodyMap, EasyCodefProperties properties) throws InterruptedException {
		/**	#1.?��?�� 체크	*/
		String domain;
		String clientId;
		String clientSecret;

		if(serviceType == 0) {
			domain = EasyCodefConstant.API_DOMAIN;
			clientId = properties.getClientId();
			clientSecret = properties.getClientSecret();
		} else if(serviceType == 1) {
			domain = EasyCodefConstant.DEMO_DOMAIN;
			clientId = properties.getDemoClientId();
			clientSecret = properties.getDemoClientSecret();
		} else {
			domain = EasyCodefConstant.SANDBOX_DOMAIN;
			clientId = EasyCodefConstant.SANDBOX_CLIENT_ID;
			clientSecret = EasyCodefConstant.SANDBOX_CLIENT_SECRET;
		}
		
		String accessToken = getToken(clientId, clientSecret); // ?��?�� 반환
		
		/**	#2.?���? ?��?��미터 ?��코딩	*/
		String bodyString;
		try {
			bodyString = mapper.writeValueAsString(bodyMap);
			bodyString = URLEncoder.encode(bodyString, "UTF-8");
		} catch (JsonProcessingException e) {
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.INVALID_JSON); 
			return response;
		} catch (UnsupportedEncodingException e) {
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.UNSUPPORTED_ENCODING); 
			return response;
		}
		
		/**	#3.?��?�� 조회 ?���?	*/
		HashMap<String, Object> responseMap = requestProduct(domain + urlPath, accessToken, bodyString);
		if(EasyCodefConstant.INVALID_TOKEN.equals(responseMap.get("error")) || "CF-00401".equals(((HashMap<String, Object>)responseMap.get(EasyCodefConstant.RESULT)).get(EasyCodefConstant.CODE))){	// ?��?��?�� ?��?�� ?��?��기간 만료?��?��?�� 경우 ?��?�� ?��발급 ?�� ?��?�� 조회 ?���? 진행
			EasyCodefTokenMap.setToken(clientId, null);		// ?��?�� ?���? 초기?��
			accessToken = getToken(clientId, clientSecret); // ?��?�� ?��?��
			responseMap = requestProduct(domain + urlPath, accessToken, bodyString);
		} else if (EasyCodefConstant.ACCESS_DENIED.equals(responseMap.get("error")) || "CF-00403".equals(((HashMap<String, Object>)responseMap.get(EasyCodefConstant.RESULT)).get(EasyCodefConstant.CODE))) {	// ?���? 권한?�� ?��?�� 경우 - ?��류코?�� 반환
			EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.UNAUTHORIZED, EasyCodefConstant.ACCESS_DENIED); 
			return response;
		}
		
		/**	#4.?��?�� 조회 결과 반환	*/
		EasyCodefResponse response = new EasyCodefResponse(responseMap); 
		return response;
	}
	
	/**
	 * Desc : CODEF HTTP POST ?���?
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:35:34 PM
	 * @param urlPath
	 * @param token
	 * @param bodyString
	 * @return
	 */

	    // 기존 requestProduct 메서?�� (private ?���?)
	    private static HashMap<String, Object> requestProduct(String urlPath, String token, String bodyString) {
	        BufferedReader br = null;
	        try {
	            // HTTP ?���??�� ?��?�� URL ?��브젝?�� ?��?��
	            URL url = new URL(urlPath);
	            HttpURLConnection con = (HttpURLConnection) url.openConnection();
	            con.setDoOutput(true);
	            con.setRequestMethod("POST");
	            con.setRequestProperty("Accept", "application/json");

	            if (token != null && !"".equals(token)) {
	                con.setRequestProperty("Authorization", "Bearer " + token); // ?��?��?�� ?��?�� ?��?�� ?��?��
	            }

	            // 리�?�스?�� 바디 ?��?��
	            OutputStream os = con.getOutputStream();
	            if (bodyString != null && !"".equals(bodyString)) {
	                os.write(bodyString.getBytes());
	            }
	            os.flush();
	            os.close();

	            // ?��?�� 코드 ?��?��
	            int responseCode = con.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) {
	                br = new BufferedReader(new InputStreamReader(con.getInputStream())); 
	            } else if (responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
	                EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.BAD_REQUEST, urlPath); 
	                return response;
	            } else if (responseCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
	                EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.UNAUTHORIZED, urlPath); 
	                return response; 
	            } else if (responseCode == HttpURLConnection.HTTP_FORBIDDEN) {
	                EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.FORBIDDEN, urlPath); 
	                return response; 
	            } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
	                EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.NOT_FOUND, urlPath); 
	                return response; 
	            } else {
	                EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.SERVER_ERROR, urlPath); 
	                return response;
	            }

	            // ?��?�� 바디 read
	            String inputLine;
	            StringBuffer responseStr = new StringBuffer();
	            while ((inputLine = br.readLine()) != null) {
	                responseStr.append(inputLine);
	            }
	            br.close();

	            // 결과 반환
	            return mapper.readValue(URLDecoder.decode(responseStr.toString(), "UTF-8"), new TypeReference<HashMap<String, Object>>() {});
	        } catch (Exception e) {
	            EasyCodefResponse response = new EasyCodefResponse(EasyCodefMessageConstant.LIBRARY_SENDER_ERROR, e.getMessage()); 
	            return response;
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {}
	            }
	        }
	    }

	    // Getter 메서?�� 추�?
	    public static HashMap<String, Object> getRequestProduct(String urlPath, String token, String bodyString) {
	        return requestProduct(urlPath, token, bodyString);
	    }

	
	/**
	 * Desc : ?��?��?�� ?��?�� 반환
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:35:47 PM
	 * @param clientId
	 * @param clientSecret
	 * @return
	 * @throws InterruptedException
	 */
	private static String getToken(String clientId, String clientSecret) throws InterruptedException {
		int i = 0;
		String accessToken = EasyCodefTokenMap.getToken(clientId);
		if(accessToken == null || "".equals(accessToken) || !checkToken(accessToken)) { //만료 조건 추�?
			while(i < REPEAT_COUNT) {	// ?��?�� 발급 ?���??? 최�? 3?��까�? ?��?��?��
				HashMap<String, Object> tokenMap = publishToken(clientId, clientSecret);	// ?��?�� 발급 ?���?
				if(tokenMap != null) {
					String newToken = (String)tokenMap.get("access_token");
					EasyCodefTokenMap.setToken(clientId, newToken);	// ?��?�� ???��
					accessToken = newToken;
				}
				
				if(accessToken != null || !"".equals(accessToken)) {
					break;	// ?��?�� 발급?�� 반복�? 종료
				}
				
				Thread.sleep(20);
				i++;
			}
		}
		
		return accessToken;
	}
	
	/**
	 * Desc : CODEF ?��?��?�� ?��?�� 발급 ?���?
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:36:01 PM
	 * @param clientId
	 * @param clientSecret
	 * @return
	 */
	protected static HashMap<String, Object> publishToken(String clientId, String clientSecret) {
		BufferedReader br = null;
		try {
			// HTTP ?���??�� ?��?�� URL ?��브젝?�� ?��?��
			URL url = new URL(EasyCodefConstant.OAUTH_DOMAIN + EasyCodefConstant.GET_TOKEN);
			String params = "grant_type=client_credentials&scope=read";	// Oauth2.0 ?��?��?�� ?��격증�? 방식(client_credentials) ?��?�� ?���? ?��?��
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			// ?��?��?��?��?��?��?��?��, ?��?��릿코?�� Base64 ?��코딩
			String auth = clientId + ":" + clientSecret;
			byte[] authEncBytes = Base64.encodeBase64(auth.getBytes());
			String authStringEnc = new String(authEncBytes);
			String authHeader = "Basic " + authStringEnc;
			
			con.setRequestProperty("Authorization", authHeader);
			con.setDoInput(true);
			con.setDoOutput(true);
			
			// 리�?�스?�� 바디 ?��?��
			OutputStream os = con.getOutputStream();
			os.write(params.getBytes());
			os.flush();
			os.close();
	
			// ?��?�� 코드 ?��?��
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {	// ?��?�� ?��?��
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {	 // ?��?�� 발생
				return null;
			}
			
			// ?��?�� 바디 read
			String inputLine;
			StringBuffer responseStr = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				responseStr.append(inputLine);
			}
			br.close();
			
			HashMap<String, Object> tokenMap = mapper.readValue(URLDecoder.decode(responseStr.toString(), "UTF-8"), new TypeReference<HashMap<String, Object>>(){});
			return tokenMap;
		} catch (Exception e) {
			return null;
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) { }
			}
		}
	}

	/**
	 * ?��?�� ?��?��기간 ?��?��
	 * @param accessToken
	 * @return
	 */
	private static boolean checkToken(String accessToken) {
        HashMap<String, Object> tokenMap = null;
        try {
            tokenMap = EasyCodefUtil.getTokenMap(accessToken);
        } catch (IOException e) {
            // ?��?�� �? ?���? 발생 ?��
            return false;
        }
        // ?��?��?�� ?��?�� 기간 ?��?��
        return EasyCodefUtil.checkValidity((int) (tokenMap.get("exp")));
    }
}