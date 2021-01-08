package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lti.entity.Insurance;
import com.lti.repository.InsuranceRepository;
@Component
public class InsuranceServiceImpl implements InsuranceService{
	@Autowired
	InsuranceRepository insuranceRepository;
	public long addOrUpdateInsurance(Insurance insurance) {
		return insuranceRepository.addOrUpdateInsurance(insurance);
	}

}
