package com.letsgotravel.myapp.api;

/**
 * <pre>
 * io.codef.easycodef
 *   |_ EasyCodefConstant.java
 * </pre>
 * 
 * Desc : EasyCodef�? ?��?��?���? ?��?�� ?��?��?�� ?��?�� ?���? �??�� ?���? ?��?��?��
 * @Company : ©CODEF corp.
 * @Author  : notfound404@codef.io
 * @Date    : Jun 26, 2020 3:36:32 PM
 */
public class EasyCodefConstant {
	
	/**	OAUTH ?���? ?��메인	*/
	protected static final String OAUTH_DOMAIN = "https://oauth.codef.io";
	
	/**	OAUTH ?��?��?�� ?��?�� 발급 URL PATH	*/
	protected static final String GET_TOKEN = "/oauth/token";
	
	
	/**	?��?��박스 ?���? ?��메인	*/
	protected static final String SANDBOX_DOMAIN = "https://sandbox.codef.io";
	
	/**	?��?��박스 ?��?��?�� ?��?�� 발급?�� ?��?�� ?��?��?��?��?�� ?��?��?��	*/
	protected static final String SANDBOX_CLIENT_ID 	= "ef27cfaa-10c1-4470-adac-60ba476273f9";
	
	/**	?��?��박스 ?��?��?�� ?��?�� 발급?�� ?��?�� ?��?��?��?��?�� ?��?���?	*/
	protected static final String SANDBOX_CLIENT_SECRET 	= "83160c33-9045-4915-86d8-809473cdf5c3";
	
	
	/**	?���? ?���? ?��메인	*/
	protected static final String DEMO_DOMAIN = "https://development.codef.io";
	
	/**	?��?�� ?���? ?��메인	*/
	protected static final String API_DOMAIN = "https://api.codef.io";
	
	
	/** ?��?���? ?��?�� 결과 ?��?��?��	*/
	protected static final String RESULT = "result";
	
	/** ?��?���? ?��?�� 결과 메시�? 코드 ?��?��?��	*/
	protected static final String CODE = "code";

	/** ?��?���? ?��?�� 결과 메시�? ?��?��?��	*/
	protected static final String MESSAGE = "message";
	
	/** ?��?���? ?��?�� 결과 추�? 메시�? ?��?��?��	*/
	protected static final String EXTRA_MESSAGE = "extraMessage";
	
	/**	?��?���? ?��?�� 결과 ?��?��?�� ?��?��?��	*/
	protected static final String DATA = "data";
	
	/** 계정 목록  ?��?��?��	*/
	protected static final String ACCOUNT_LIST = "accountList";
	
	protected static final String CONNECTED_ID = "connectedId";
	
	
	/**	?��?��?�� ?��?�� 거절 ?��?��1	*/
	protected static String INVALID_TOKEN = "invalid_token";
	
	/**	?��?��?�� ?��?�� 거절 ?��?��2	*/
	protected static String ACCESS_DENIED = "access_denied";
	
	/**	계정 ?���? URL	*/
	protected static final String CREATE_ACCOUNT = "/v1/account/create";
	
	/**	계정 추�? URL	*/
	protected static final String ADD_ACCOUNT = "/v1/account/add";
	
	/**	계정 ?��?�� URL	*/
	protected static final String UPDATE_ACCOUNT = "/v1/account/update";
	
	/**	계정 ?��?�� URL	*/
	protected static final String DELETE_ACCOUNT = "/v1/account/delete";
	
	/**	계정 목록 조회 URL	*/
	protected static final String GET_ACCOUNT_LIST = "/v1/account/list";
	
	/**	커넥?��?�� ?��?��?�� 목록 조회 URL	*/
	protected static final String GET_CID_LIST = "/v1/account/connectedId-list"; 
	
}