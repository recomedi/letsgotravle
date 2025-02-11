package com.letsgotravel.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letsgotravel.myapp.domain.DrugVo;
import com.letsgotravel.myapp.domain.PrescriptionVo;
import com.letsgotravel.myapp.persistance.PrescriptionMapper;


@Service
public interface PrescriptionService {


	// 회원별 처방 목록 조회
    List<PrescriptionVo> getPrescriptionsByMember(int midx);

    // 특정 처방전 상세 정보 조회
    PrescriptionVo getPrescriptionDetail(int pidx);

	int savePrescription(PrescriptionVo prescription);
	
	void saveDrug(DrugVo drug);

	int savePrescriptionAndDrugs(PrescriptionVo prescription, List<DrugVo> drugs);
	

	
    
    
}
