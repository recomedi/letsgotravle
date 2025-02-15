package com.letsgotravel.myapp.persistance;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.letsgotravel.myapp.domain.DrugVo;
import com.letsgotravel.myapp.domain.PrescriptionVo;

@Mapper
public interface PrescriptionMapper {
	
	
	

    // 특정 처방전 상세 정보 조회
    PrescriptionVo selectPrescriptionDetail(@Param("pidx") int pidx);
    
    int insertPrescription(PrescriptionVo prescription);
    
    void saveDrug(DrugVo drug);

	List<PrescriptionVo> findPrescriptionsByMidx(Integer midx);
	 // 🔹 특정 회원(midx)의 기존 처방 데이터 삭제
    int resetPrescriptionsByMidx(int midx);

    // 🔹 특정 회원(midx)의 기존 약물 데이터 삭제
    int resetDrugsByMidx(int midx);


    // 🔹 새로운 약물 데이터 삽입
    void insertDrug(DrugVo drug);
	
    
    
}
