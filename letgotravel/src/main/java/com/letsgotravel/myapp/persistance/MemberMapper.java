package com.letsgotravel.myapp.persistance;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.letsgotravel.myapp.domain.MemberVo;

public interface MemberMapper {
	public int memberInsert(MemberVo mv);
	public int idCheck(String id);
	public int nicknameCheck(String nickname);
	public MemberVo LoginCheck(String id);
	public MemberVo findId(@Param("name")String name, @Param("phone")String phone);
    
	public MemberVo getUserById(@Param("id") String id);
	public int changePassword(@Param("id") String id, @Param("password") String password);
    public void updateUserProfile(Map<String, Object> updateParams);
    public int updateDelyn(@Param("id") String id, @Param("delyn") String delyn);

}
