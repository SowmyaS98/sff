package com.lti.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Admin;
import com.lti.entity.Bidder;
import com.lti.entity.Farmer;

@Repository
public class AdminRepositoryImpl implements AdminRepository {
	@PersistenceContext
	EntityManager em;

	@Transactional
	public long addOrUpdateAdmin(Admin admin) {
		Admin a = em.merge(admin);
		return a.getAdminId();
	}

	@Transactional
	public Admin fetchAdminByEmailAndPassword(String adminEmail, String adminPassword) {
		try {
			String jpql = "select a from Admin a where adminEmail=:aEmail and adminPassword=:aPassword";
			Query query = em.createQuery(jpql);
			query.setParameter("aEmail", adminEmail);
			query.setParameter("aPassword", adminPassword);
			Admin admin = (Admin) query.getSingleResult();
			return admin;
		} catch (Exception e) {
			return null;
		}
	}
	@Transactional
	public Admin fetchAdminByEmail(String adminEmail) {
		try {
			String jpql = "select a from Admin a where adminEmail=:aEmail";
			Query query = em.createQuery(jpql);
			query.setParameter("aEmail", adminEmail);
			Admin admin = (Admin) query.getSingleResult();
			return admin;
		} catch (Exception e) {
			return null;
		}
	}

}
