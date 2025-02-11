package com.letsgotravel.myapp.persistance;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.letsgotravel.myapp.domain.MemberVo;

public interface MemberMapper { 
	 String getCurrentTime();
	 int insertMember(MemberVo member);
	 Integer findMidxByPhone(@Param("phone") String phone);
	 MemberVo memberlogin(@Param("id") String id);
	 MemberVo findById(@Param("id") String id);
	}