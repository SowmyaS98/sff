package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Insurance;
@Component
public class InsuranceRepositoryImpl implements InsuranceRepository {
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public long addOrUpdateInsurance(Insurance insurance) {
		Insurance i=em.merge(insurance);
		return i.getInsuranceId();
	}

}
