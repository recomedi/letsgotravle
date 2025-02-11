package com.letsgotravel.myapp.persistance;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.letsgotravel.myapp.domain.DrugVo;
import com.letsgotravel.myapp.domain.PrescriptionVo;

@Mapper
public interface PrescriptionMapper {
	
	
	// 회원별 처방 목록 조회
    List<PrescriptionVo> selectPrescriptionsByMember(@Param("midx") int midx);

    // 특정 처방전 상세 정보 조회
    PrescriptionVo selectPrescriptionDetail(@Param("pidx") int pidx);
    
    int insertPrescription(PrescriptionVo prescription);
    
    void saveDrug(DrugVo drug);
    
    
}
