package com.letsgotravel.myapp.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letsgotravel.myapp.domain.MemberVo;
import com.letsgotravel.myapp.persistance.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
    private MemberMapper memberMapper;
	
	/*
	 * @Autowired public MemberServiceImpl(SqlSession sqlSession) { this.mm =
	 * sqlSession.getMapper(MemberMapper.class); }
	 */

    @Override
    public String getCurrentTime() {
        return memberMapper.getCurrentTime();
    }

	@Override
	public int saveMemberInfo(MemberVo member) {
		 memberMapper.insertMember(member);
	        return member.getMidx(); // �궫�엯�맂 midx 諛섑솚
	    }
	
}
