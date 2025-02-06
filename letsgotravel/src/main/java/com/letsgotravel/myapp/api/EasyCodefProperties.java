package com.letsgotravel.myapp.api;
/**
 * <pre>
 * io.codef.easycodef
 *   |_ EasyCodefProperties.java
 * </pre>
 * 
 * Desc : 코드?��?��?�� ?��?�� ?��?��?�� ?��?�� ?��로퍼?�� ?��?��?�� 
 * @Company : ©CODEF corp.
 * @Author  : notfound404@codef.io
 * @Date    : Jun 26, 2020 3:36:51 PM
 */
public class EasyCodefProperties {
	
	//	?���? ?��?��?�� ?��?�� 발급?�� ?��?�� ?��?��?��?��?�� ?��?��?��
	private String demoClientId 	= "";
	
	//	?���? ?��?��?�� ?��?�� 발급?�� ?��?�� ?��?��?��?��?�� ?��?���?
	private String demoClientSecret 	= "";	
	
	//	OAUTH2.0 ?���? ?��?��
	private String demoAccessToken = "";
	
	//	?��?�� ?��?��?�� ?��?�� 발급?�� ?��?�� ?��?��?��?��?�� ?��?��?��
	private String clientId 	= "";
	
	//	?��?�� ?��?��?�� ?��?�� 발급?�� ?��?�� ?��?��?��?��?�� ?��?���?
	private String clientSecret 	= "";	
	
	//	OAUTH2.0 ?��?��
	private String accessToken = "";
	
	//	RSA?��?��?���? ?��?�� ?��블릭?��
	private String publicKey 	= "";

	
	/**
	 * Desc : ?��?��?���? ?��?��?�� ?��?�� ?��?��?��?��?�� ?���? ?��?��
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:37:02 PM
	 * @param clientId
	 * @param clientSecret
	 */
	public void setClientInfo(String clientId, String clientSecret) {
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}
	
	/**
	 * Desc : ?��모서�? ?��?��?�� ?��?�� ?��?��?��?��?�� ?���? ?��?��
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:37:10 PM
	 * @param demoClientId
	 * @param demoClientSecret
	 */
	public void setClientInfoForDemo(String demoClientId, String demoClientSecret) {
		this.demoClientId = demoClientId;
		this.demoClientSecret = demoClientSecret;
	}
	
	/**
	 * Desc : ?���? ?��?��?��?��?�� ?��?��?�� 반환
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:37:17 PM
	 * @return
	 */
	public String getDemoClientId() {
		return demoClientId;
	}

	/**
	 * Desc : ?���? ?��?��?��?��?�� ?��?���? 반환
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:37:23 PM
	 * @return
	 */
	public String getDemoClientSecret() {
		return demoClientSecret;
	}

	/**
	 * Desc : ?���? ?��?�� ?��?�� 반환
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:37:30 PM
	 * @return
	 */
	public String getDemoAccessToken() {
		return demoAccessToken;
	}

	/**
	 * Desc : ?���? ?��?��?��?��?�� ?��?���? 반환
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:37:36 PM
	 * @Version : 1.0.1
	 * @return
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * Desc : API ?��?��?��?��?�� ?��?���? 반환
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:37:44 PM
	 * @return
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	/**
	 * Desc : API ?��?�� ?��?�� 반환
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:37:50 PM
	 * @Version : 1.0.1
	 * @return
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Desc : RSA?��?��?���? ?��?�� ?��블릭?�� 반환
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:37:59 PM
	 * @return
	 */
	public String getPublicKey() {
		return publicKey;
	}

	/**
	 * Desc : RSA?��?��?���? ?��?�� ?��블릭?�� ?��?��
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:38:07 PM
	 * @Version : 1.0.1
	 * @param publicKey
	 */
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	/**
	 * Desc : ?���? ?��?�� ?��?�� ?��?��
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:38:14 PM
	 * @param demoAccessToken
	 */
	public void setDemoAccessToken(String demoAccessToken) {
		this.demoAccessToken = demoAccessToken;
	}

	/**
	 * Desc : API ?��?�� ?��?�� ?��?��
	 * @Company : ©CODEF corp.
	 * @Author  : notfound404@codef.io
	 * @Date    : Jun 26, 2020 3:38:21 PM
	 * @param accessToken
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
}