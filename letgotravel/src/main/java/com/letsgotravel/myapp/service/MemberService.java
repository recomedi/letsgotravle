package com.letsgotravel.myapp.service;

import com.letsgotravel.myapp.domain.MemberVo;

public interface MemberService {
	public int memberInsert(MemberVo mv);
	public int idCheck(String id);
	public int nicknameCheck(String nickname);
	public MemberVo LoginCheck(String id);
	public MemberVo findId(String name, String phone);
	public boolean updateProfile(String id, String currentPassword, String newPassword, String nickname, String email,
			String phone);
	public boolean deleteAccount(String id);

}
