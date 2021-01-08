package com.lti.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.lti.entity.Crop;

@Repository
public class CropRepositoryImpl implements CropRepository {
	@PersistenceContext
	EntityManager em;

	@Transactional
	public long addOrUpdateCrop(Crop crop) {
		Crop c=em.merge(crop);
		return c.getCropId();
	}
	@Transactional
	public Crop findCropById(long cropId) {
		return em.find(Crop.class,cropId);
	}
	@Transactional
	public Crop findCropByCropNameAndCropType(String cropName, String cropType) {
			String jpql = "select c from Crop c where c.CropName=:cName and c.CropType=:cType";
			Query query = em.createQuery(jpql);
			query.setParameter("cName", cropName);
			query.setParameter("cType", cropType);
			Crop c = (Crop) query.getSingleResult();
			System.out.println(c.getCropName());
			return c;
	}
}
