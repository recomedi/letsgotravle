package com.letsgotravel.myapp.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.letsgotravel.myapp.domain.DrugVo;
import com.letsgotravel.myapp.domain.PrescriptionVo;
import com.letsgotravel.myapp.persistance.PrescriptionMapper;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {


	@Autowired
 private JdbcTemplate jdbcTemplate; // ⬅ 이 부분 추가!

    private PrescriptionMapper pm;

    @Autowired
    public PrescriptionServiceImpl (SqlSession sqlSession) {
    	this.pm =sqlSession.getMapper(PrescriptionMapper.class);
    }


    
    @Override
    public PrescriptionVo getPrescriptionDetail(int pidx) {
        PrescriptionVo prescription = pm.selectPrescriptionDetail(pidx);

        if (prescription == null) {
            System.out.println("❌ 해당 처방전이 존재하지 않음.");
            return null;
        }

        System.out.println("📌 처방전 정보: " + prescription.getCommBrandName() + ", " + prescription.getResPrescribeOrg());

        if (prescription.getDrugs() == null || prescription.getDrugs().isEmpty()) {
            System.out.println("❌ 처방전에 약물 정보가 없음.");
        } else {
            System.out.println("✅ 약물 개수: " + prescription.getDrugs().size());
            for (DrugVo drug : prescription.getDrugs()) {
                System.out.println("📌 약물 이름: " + drug.getResDrugName());
            }
        }

        return prescription;
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
        Integer generatedPidx = keyHolder.getKey() != null ? keyHolder.getKey().intValue() : null;
        System.out.println("📌 저장된 처방전 pidx: " + generatedPidx);

        return generatedPidx != null ? generatedPidx : -1; // `-1` 반환 시 KeyHolder 문제 발생
    }


	@Override
	public void saveDrug(DrugVo drug) {
		 String sql = "INSERT INTO DRUG (pidx, resDrugName, resPrescribeDrugEffect, resIngredients, resDrugCode, resContent, resOneDose, resDailyDosesNumber, resTotalDosingdays, date, ip, delyn) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), '127.0.0.1', 'N')";
	        jdbcTemplate.update(sql, drug.getPidx(), drug.getResDrugName(), drug.getResPrescribeDrugEffect(), drug.getResIngredients(), drug.getResDrugCode(), drug.getResContent(), drug.getResOneDose(), drug.getResDailyDosesNumber(), drug.getResTotalDosingdays());
	    }

	
	@Override
	public int savePrescriptionAndDrugs(PrescriptionVo prescription, List<DrugVo> drugs) {
		System.out.println("📌 savePrescriptionAndDrugs() 실행됨");
	    int pidx = savePrescription(prescription);  // 처방전 저장 후 pidx 반환
	    System.out.println("📌 처방전 저장 완료, pidx: " + pidx);
	    for (DrugVo drug : drugs) {
	        drug.setPidx(pidx);  // 처방전 ID와 연동
	        saveDrug(drug);      // 약물 정보 저장
	        System.out.println("📌 저장된 약물: " + drug.getResDrugName());
	    }
	    return pidx;  // 저장된 처방전 ID 반환
	}


	@Override
	public List<PrescriptionVo> getPrescriptionsByMidx(Integer midx) {
	    System.out.println("📌 getPrescriptionsByMidx() 실행됨, midx: " + midx);
	    
	    List<PrescriptionVo> prescriptions = pm.findPrescriptionsByMidx(midx);

	    if (prescriptions == null || prescriptions.isEmpty()) {
	        System.out.println("❌ DB에 저장된 처방전이 없음.");
	    } else {
	        System.out.println("✅ DB에서 가져온 처방전 개수: " + prescriptions.size());
	    }

	    return prescriptions;
	}





}