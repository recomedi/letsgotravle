package com.letsgotravel.myapp.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.letsgotravel.myapp.domain.MemberVo;
import com.letsgotravel.myapp.persistance.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	
	private MemberMapper mm;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
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

	@Override
	public boolean updateProfile(String id, String currentPassword, String newPassword, String nickname, String email, String phone) {
	    boolean isUpdated = false;

	    try {
	        MemberVo mv = mm.getUserById(id);

	        if (newPassword != null && !newPassword.isEmpty()) {
	            if (mv == null) {
	                throw new IllegalArgumentException("사용자 정보를 찾을 수 없습니다.");
	            }
	            if (!bCryptPasswordEncoder.matches(currentPassword, mv.getPassword())) {
	                throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
	            }
	            String encryptedPassword = bCryptPasswordEncoder.encode(newPassword);
	            int updatedRows = mm.changePassword(id, encryptedPassword);

	            if (updatedRows > 0) {
	                isUpdated = true;
	                System.out.println("비밀번호 변경 성공: " + encryptedPassword);
	            } else {
	                System.out.println("비밀번호 변경 실패");
	            }
	        }

	        Map<String, Object> updateParams = new HashMap<>();
	        updateParams.put("id", id);

	        if (nickname != null && !nickname.isEmpty()) {
	            updateParams.put("nickname", nickname);
	        }
	        if (email != null && !email.isEmpty()) {
	            updateParams.put("email", email);
	        }
	        if (phone != null && !phone.isEmpty()) {
	            updateParams.put("phone", phone);
	        }

	        if (!updateParams.isEmpty()) {
	            mm.updateUserProfile(updateParams);
	            isUpdated = true;
	        }

	    } catch (Exception e) {
	        throw new RuntimeException("회원 정보 변경 중 오류 발생", e);
	    }

	    return isUpdated;
	}
	
	@Override
	public boolean deleteAccount(String id) {
	    int result = mm.updateDelyn(id, "Y"); 
	    return result > 0;
	}
}
