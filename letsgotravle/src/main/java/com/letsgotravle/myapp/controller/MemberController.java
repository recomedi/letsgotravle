package com.letsgotravle.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
//	@Autowired(required=false)
//	private UserIp userip;
//	
//	@Autowired
//	private MemberService memberService;
//	
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = "/memberFind.do")
	public String memberFind() {
		logger.info("memberFindµé¾î¿È");
		return "WEB-INF/member/memberFind";
	}
	
}
