package com.letsgotravel.myapp.service;

import org.apache.ibatis.annotations.Select;

import com.letsgotravel.myapp.domain.MemberVo;

public interface MemberService {

	 @Select("SELECT NOW()")
	    String getCurrentTime();
	 
	 
	 int saveMemberInfo(MemberVo member);

		
}
