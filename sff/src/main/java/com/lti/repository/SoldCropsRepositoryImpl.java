package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.SoldCrops;
@Repository
public class SoldCropsRepositoryImpl implements SoldCropsRepository {
	@PersistenceContext
	EntityManager em;
	@Transactional
	public long addSoldCrop(SoldCrops soldCrop) {
		// TODO Auto-generated method stub
		SoldCrops soldCrrop=em.merge(soldCrop);
		return soldCrop.getCropId();
	}
	public List<SoldCrops> findSoldCropByFarmerEmail(String farmerEmail) {
		String jpql="select s from SoldCrops s where s.farmerEmail=:fmail";
		Query query=em.createQuery(jpql);
		query.setParameter("fmail", farmerEmail);
		List<SoldCrops> s=query.getResultList();
		return s;
	}

}
