package com.letsgotravel.myapp.controller;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.letsgotravel.myapp.domain.MemberVo;
import com.letsgotravel.myapp.service.MemberService;
import com.letsgotravel.myapp.util.UserIp;

@Controller
@RequestMapping(value="/member/")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired(required=false)
	private UserIp userip;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = "memberFind.do")
	public String memberFind() {
		logger.info("memberFind����");
		return "WEB-INF/member/memberFind";
	}
	
	@RequestMapping(value = "memberLogin.do")
	public String memberLogin() {
		logger.info("member로그인 들어옴");
		return "WEB-INF/member/memberLogin";
	}
	
	@RequestMapping(value = "loginAction.do", method = RequestMethod.POST)
	public String LoginAction(

			@RequestParam("id") String id, @RequestParam("password") String password,
			RedirectAttributes rttr, HttpSession session) {
			System.out.println("아이디" + id);
			System.out.println("비번" + password);

		MemberVo mv = memberService.LoginCheck(id);
		// 저장된 비밀번호를 가져온다

		String path = "";
		if (mv != null) { // 객체값이 있으면
			String reservedPassword = mv.getPassword();
			if (bCryptPasswordEncoder.matches(password, reservedPassword)) {
				System.out.println("비밀번호 일치");
//				rttr.addAttribute("midx", mv.getMidx());
//				rttr.addAttribute("memberId", mv.getId());
//				rttr.addAttribute("memberNickName", mv.getNickname());
				
	            session.setAttribute("midx", mv.getMidx());
	            session.setAttribute("memberId", mv.getId());
	            session.setAttribute("memberNickName", mv.getNickname());
	            
				logger.info("로그인 성공 midx 번호" + mv.getMidx());
				
				logger.info("saveUrl : "  + session.getAttribute("saveUrl"));

				if (session.getAttribute("saveUrl") != null) {
					path = "redirect:" + session.getAttribute("saveUrl").toString();
				} else
					path = "redirect:/";

			} else {
				rttr.addAttribute("midx","");
				rttr.addAttribute("id", "");
				rttr.addAttribute("nickName", "");	
				rttr.addFlashAttribute("msg", "아이디/비밀번호를 확인해주세요");
				path = "redirect:/member/memberLogin.do";
			}
		} else {
			rttr.addAttribute("midx","");
			rttr.addAttribute("id", "");
			rttr.addAttribute("nickname", "");	
			rttr.addFlashAttribute("msg", "아이디/비밀번호를 확인해주세요");
			path = "redirect:/member/memberLogin.do";
		}
		// 회원정보를 세션에 담는다
		return path;
	}
	
	@RequestMapping(value = "Logout.do", method = RequestMethod.GET)
	public String Logout(HttpSession session) {

		session.removeAttribute("midx");
		session.removeAttribute("nickname");
		session.removeAttribute("id");
		session.invalidate(); // 세션값 초기화

		return "redirect:/";
	}
	
	@RequestMapping(value = "memberSignUp.do")
	public String memberSignUp() {
		logger.info("memberSignUp들어옴");
		return "WEB-INF/member/memberSignUp";
	}
	
	@RequestMapping(value = "signupAction.do", method = RequestMethod.POST)
	public String signupAction(MemberVo mv) {
		//logger.info("signupAction들어옴");
		//logger.info("bCryptPasswordEncoder==>" + bCryptPasswordEncoder);

		String password_enc = bCryptPasswordEncoder.encode(mv.getPassword());
		mv.setPassword(password_enc);

		int value = memberService.memberInsert(mv);
		logger.info("value:" + value);

		String path = "";
		if (value == 1) {
			path = "redirect:/";
		} else if (value == 0) {
			path = "redirect:/member/memberSignup.do";
		}
		return path;
	}
	
	@ResponseBody
	@RequestMapping(value = "idCheck.do", method = RequestMethod.POST)
	public JSONObject IdCheck(@RequestParam("id") String id) {
		
		int cnt = memberService.idCheck(id);

		JSONObject obj = new JSONObject();
		
		obj.put("cnt", cnt);
		logger.info("cnt" + cnt);

		return obj;
	}
	
	@ResponseBody
	@RequestMapping(value = "nicknameCheck.do", method = RequestMethod.POST)
	public JSONObject NicknameCheck(@RequestParam("nickname") String nickname) {
		
		int cnt = memberService.nicknameCheck(nickname);

		JSONObject obj = new JSONObject();
		
		obj.put("cnt", cnt);
		logger.info("cnt" + cnt);

		return obj;
	}
	
	@RequestMapping(value = "memberMypage.do")
	public String memberMypage() {
		logger.info("memberMyPage들어옴");
		return "WEB-INF/member/memberMypage";
	}
	
}
