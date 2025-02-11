package com.letsgotravel.myapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.letsgotravel.myapp.domain.MemberVo;
import com.letsgotravel.myapp.persistance.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	 private final MemberMapper memberMapper;
	 private final BCryptPasswordEncoder passwordEncoder;
	 
	 
	 
	 @Autowired
	    public MemberServiceImpl(MemberMapper memberMapper) {
	        this.memberMapper = memberMapper;
	        this.passwordEncoder = new BCryptPasswordEncoder(); // 비밀번호 암호화
	    }

	
	 

	    @Override
	    public String getCurrentTime() {
	        return memberMapper.getCurrentTime();
	    }

		@Override
		public int saveMemberInfo(MemberVo member) {
			 memberMapper.insertMember(member);
		        return member.getMidx(); // 삽입된 midx 반환
		    }

		@Override
		public Integer getMemberByPhone(String phoneNumber) {
	        return memberMapper.findMidxByPhone(phoneNumber); // 📌 DB에서 midx 조회
	    }

		@Override
		public MemberVo memberlogin(String id, String password) {
		    MemberVo member = memberMapper.memberlogin(id); // 📌 Mapper의 memberlogin 사용
		    if (member != null) {
		        if (member.getPassword() == null) {
		            System.out.println("🚨 DB에 저장된 비밀번호가 없습니다.");
		            return null;
		        }

		        if (passwordEncoder.matches(password, member.getPassword())) {
		            return member; // 로그인 성공
		        }
		    }
		    return null; // 로그인 실패
		}
	
}
