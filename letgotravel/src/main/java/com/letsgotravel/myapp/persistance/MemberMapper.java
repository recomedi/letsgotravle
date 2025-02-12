package com.letsgotravel.myapp.persistance;

import com.letsgotravel.myapp.domain.MemberVo;

public interface MemberMapper {
	public int memberInsert(MemberVo mv);
	public int idCheck(String id);
	public int nicknameCheck(String nickname);
	public MemberVo LoginCheck(String id);
}
