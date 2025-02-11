package com.letsgotravel.myapp.persistance;

import org.apache.ibatis.annotations.Select;

import com.letsgotravel.myapp.domain.MemberVo;

public interface MemberMapper {
	
	    String getCurrentTime();
	 int insertMember(MemberVo member);
	
}
