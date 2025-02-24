package com.letsgotravel.myapp.persistance;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.letsgotravel.myapp.domain.DrugVo;
import com.letsgotravel.myapp.domain.PrescriptionVo;
import com.letsgotravel.myapp.domain.SearchCriteria;

@Mapper
public interface PrescriptionMapper {


    // 특정 처방전 상세 정보 조회
    PrescriptionVo selectPrescriptionDetail(@Param("pidx") int pidx);

    int insertPrescription(PrescriptionVo prescription);

    void saveDrug(DrugVo drug);

	List<PrescriptionVo> findPrescriptionsByMidx(Integer midx);
	// ✅ 특정 회원의 기존 약물 정보 삭제
    int resetDrugsByMidx(@Param("midx") int midx);

    // ✅ 특정 회원의 기존 처방전 삭제
    int resetPrescriptionsByMidx(@Param("midx") int midx);

    int getTotalPrescriptionsCount(@Param("midx") int midx, @Param("cri") SearchCriteria cri);

	List<PrescriptionVo> getPrescriptionsByMidxWithPaging(Map<String, Object> paramMap);



}