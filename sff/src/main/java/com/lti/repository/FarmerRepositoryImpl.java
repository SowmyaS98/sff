package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Farmer;
@Repository
public class FarmerRepositoryImpl implements FarmerRepository {
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public Farmer addOrUpdateFarmer(Farmer farmer) {
		Farmer f = em.merge(farmer);
		return f;
	}
	/*@Transactional
	public boolean isValidUser(String farmerEmail,String farmerPassword) {
		try {
			String jpql = "select f from Farmer f where farmerEmail=:fEmail and farmerPassword=:fPassword";
			Query query = em.createQuery(jpql);
			query.setParameter("fEmail", farmerEmail);
			query.setParameter("fPassword", farmerPassword);
			Farmer farmers = (Farmer) query.getSingleResult();
			if(farmers!=null)
				return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}*/
	@Transactional
	public Farmer isValidUser(String farmerEmail,String farmerPassword) {
		try {
			String jpql = "select f from Farmer f where farmerEmail=:fEmail and farmerPassword=:fPassword";
			Query query = em.createQuery(jpql);
			query.setParameter("fEmail", farmerEmail);
			query.setParameter("fPassword", farmerPassword);
			Farmer farmers = (Farmer) query.getSingleResult();
			return farmers;
		} catch (Exception e) {
			return null;
		}
	}
	@Transactional
	public Farmer fetchFarmerByEmail(String farmerEmail) {
			try {
				String jpql = "select f from Farmer f where farmerEmail=:fEmail";
				Query query = em.createQuery(jpql);
				query.setParameter("fEmail", farmerEmail);
				Farmer farmers = (Farmer) query.getSingleResult();
				return farmers;
			} catch (Exception e) {
				return null;
			}
	}
	@Transactional
	public List<Farmer> fetchApprovalPendingFarmers(){    // to display farmers with approve="No"
		
		String jpql = "select f from Farmer f where f.farmerApprove='no'";
		TypedQuery<Farmer> query = em.createQuery(jpql,Farmer.class);
		return query.getResultList();
	}
	@Transactional
	public Farmer updateFarmerByEmail(String farmerEmail) {
		String jpql = "update Farmer f set f.farmerApprove='Yes' where f.farmerEmail=:fEmail";
		Query query = em.createQuery(jpql);
		query.setParameter("fEmail", farmerEmail);
		query.executeUpdate();
		Farmer farmer = fetchFarmerByEmail(farmerEmail);
		return farmer;
	}
	@Transactional
	public long rejectFarmer(String farmerEmail) {
		String jpql1 = "select f from Farmer f where f.farmerEmail=:fEmail";
		TypedQuery<Farmer> query1 = em.createQuery(jpql1,Farmer.class);
		query1.setParameter("fEmail", farmerEmail);
		Farmer f = (Farmer)query1.getSingleResult();
		long farmerId = f.getFarmerId();
		em.remove(f);
		return farmerId;

	}
}
