package com.letsgotravel.myapp.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.letsgotravel.myapp.domain.DrugVo;
import com.letsgotravel.myapp.domain.PrescriptionVo;
import com.letsgotravel.myapp.persistance.PrescriptionMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {


	private JdbcTemplate jdbcTemplate;

    private PrescriptionMapper pm;
    
    @Autowired
    public PrescriptionServiceImpl (SqlSession sqlSession) {
    	this.pm =sqlSession.getMapper(PrescriptionMapper.class);
    }
    

    @Override
    public List<PrescriptionVo> getPrescriptionsByMember(int midx) {
        return pm.selectPrescriptionsByMember(midx);
    }

    @Override
    public PrescriptionVo getPrescriptionDetail(int pidx) {
        return pm.selectPrescriptionDetail(pidx);
    }


    @Override
    public int savePrescription(PrescriptionVo prescription) {
    String sql = "INSERT INTO PRESCRIPTION (midx, resMenufactureDate, resPrescribeOrg, resTelNo, commBrandName, commTelNo, date, ip, delyn) VALUES (?, ?, ?, ?, ?, ?, NOW(), '127.0.0.1', 'N')";
        
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 자동 생성된 pidx 받기
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, prescription.getMidx());
            ps.setString(2, prescription.getResMenufactureDate());
            ps.setString(3, prescription.getResPrescribeOrg());
            ps.setString(4, prescription.getResTelNo());
            ps.setString(5, prescription.getCommBrandName());
            ps.setString(6, prescription.getCommTelNo());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue(); // 생성된 pidx 반환
    }


	@Override
	public void saveDrug(DrugVo drug) {
		 String sql = "INSERT INTO DRUG (pidx, resDrugName, resPrescribeDrugEffect, resIngredients, resDrugCode, resContent, resOneDose, resDailyDosesNumber, resTotalDosingdays, date, ip, delyn) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), '127.0.0.1', 'N')";
	        jdbcTemplate.update(sql, drug.getPidx(), drug.getResDrugName(), drug.getResPrescribeDrugEffect(), drug.getResIngredients(), drug.getResDrugCode(), drug.getResContent(), drug.getResOneDose(), drug.getResDailyDosesNumber(), drug.getResTotalDosingdays());
	    }
	
	@Override
	public int savePrescriptionAndDrugs(PrescriptionVo prescription, List<DrugVo> drugs) {
	    int pidx = savePrescription(prescription);  // 처방전 저장 후 pidx 반환
	    for (DrugVo drug : drugs) {
	        drug.setPidx(pidx);  // 처방전 ID와 연동
	        saveDrug(drug);      // 약물 정보 저장
	    }
	    return pidx;  // 저장된 처방전 ID 반환
	}
	
	
	
	

}
