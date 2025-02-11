package com.letsgotravel.myapp.persistance;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.letsgotravel.myapp.domain.PrescriptionVo;

@Mapper
public interface PrescriptionMapper {
	
	
	// �쉶�썝蹂� 泥섎갑 紐⑸줉 議고쉶
    List<PrescriptionVo> selectPrescriptionsByMember(@Param("midx") int midx);

    // �듅�젙 泥섎갑�쟾 �긽�꽭 �젙蹂� 議고쉶
    PrescriptionVo selectPrescriptionDetail(@Param("pidx") int pidx);
    
    int insertPrescription(PrescriptionVo prescription);
    
    
    
    
}
