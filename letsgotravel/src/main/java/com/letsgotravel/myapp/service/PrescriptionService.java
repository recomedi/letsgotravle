package com.letsgotravel.myapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.letsgotravel.myapp.domain.PrescriptionVo;


@Service
public interface PrescriptionService {


	// �쉶�썝蹂� 泥섎갑 紐⑸줉 議고쉶
    List<PrescriptionVo> getPrescriptionsByMember(int midx);

    // �듅�젙 泥섎갑�쟾 �긽�꽭 �젙蹂� 議고쉶
    PrescriptionVo getPrescriptionDetail(int pidx);

	int savePrescription(PrescriptionVo prescription);

	
    
    
}
