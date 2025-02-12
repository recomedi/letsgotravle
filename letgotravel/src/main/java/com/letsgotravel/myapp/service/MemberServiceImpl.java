package com.letsgotravel.myapp.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letsgotravel.myapp.domain.MemberVo;
import com.letsgotravel.myapp.persistance.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	private MemberMapper mm;
	
	@Autowired
	public MemberServiceImpl(SqlSession sqlSession) {
		this.mm = sqlSession.getMapper(MemberMapper.class);
	}
	
	@Override
	public int memberInsert(MemberVo mv) {
		int value = mm.memberInsert(mv);
		
		return value;
	}
	@Override
	public int idCheck(String id) {
			int value = mm.idCheck(id);		
		return value;
	}

	@Override
	public int nicknameCheck(String nickname) {
		int value = mm.nicknameCheck(nickname);
		return value;
	}

	@Override
	public MemberVo LoginCheck(String id) {
		MemberVo mv = mm.LoginCheck(id);
		
		return mv;
	}

	@Override
	public MemberVo findId(String name, String phone) {
		MemberVo mv = mm.findId(name,phone);
		return mv;
	}
}
