package com.lti.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Bidder;
import com.lti.entity.Crop;
import com.lti.entity.Farmer;
import com.lti.entity.SellRequest;
@Repository
public class SellRequestRepositoryImpl implements SellRequestRepository {
@PersistenceContext
EntityManager em;
@Autowired
FarmerRepository farmerRepository;
@Autowired
CropRepository cropRepository;

	@Transactional
	public SellRequest addOrUpdateSellRequest(SellRequest sellRequest) {
		SellRequest s=em.merge(sellRequest);
		return s;
	}

	public List<Object[]> fetchApprovalPendingSellRequest() {
		String jpql = "select s.requestId,s.quantity,f.farmerId,c.CropId from SellRequest s join s.farmer f join s.crop c where s.approve='no'";
		TypedQuery<Object[]> query = em.createQuery(jpql,Object[].class);
		return query.getResultList();
	}
	@Transactional
	public SellRequest updateSellRequestByRequestId(long requestId) {
		String jpql = "update SellRequest s set s.approve='yes' where s.requestId=:rid";
		Query query = em.createQuery(jpql);
		query.setParameter("rid", requestId);
		query.executeUpdate();
		SellRequest sellRequest=fetchSellRequestByRequestId(requestId);
		return sellRequest;
	}

	public SellRequest fetchSellRequestByRequestId(long requestId) {
		
		String jpql = "select s from SellRequest s where s.requestId=:rid";
		Query query = em.createQuery(jpql);
		query.setParameter("rid", requestId);
		SellRequest sellRequest=(SellRequest)query.getSingleResult();
		return sellRequest;
	}
	@Transactional
	public SellRequest removeSellRequestByRequestId(long requestId) {
		// TODO Auto-generated method stub
		SellRequest sellRequest=fetchSellRequestByRequestId(requestId);
		//em.remove(sellRequest);
		String jpql="delete from SellRequest s where s.requestId=:rid";
		Query query = em.createQuery(jpql);
		query.setParameter("rid", requestId);
		query.executeUpdate();
		return sellRequest;
	}
	@Transactional
	public List<Long> fetchCropIdByFarmerEmail(String farmerEmail) {
		String jpql = "select s.crop.CropId from SellRequest s where s.farmer.farmerEmail=:fEmail";
		Query query = em.createQuery(jpql);
		query.setParameter("fEmail", farmerEmail);
		return query.getResultList();
	}

	public List<Object[]> fetchCrops() {
		List<Object[]> obj= new ArrayList<Object[]>();
		String jpql="select distinct s.crop.CropId from SellRequest s";
		Query query = em.createQuery(jpql);
		Crop crop;
		List<Long> cropId = query.getResultList();
		for(Long c : cropId) {
			crop = cropRepository.findCropById(c);
			System.out.println(crop.getCropName()+" "+crop.getCropType());
			Object[] o = {crop.getCropType(),crop.getCropName()};
			obj.add(o);
		}
		return obj;
	}

	public boolean isFarmerIdPresent(long farmerId) {
		try {
			String jpql="select s from SellRequest s where s.farmer.farmerId=:fid";
			Query query = em.createQuery(jpql);
			query.setParameter("fid", farmerId);
			SellRequest sellrequest=(SellRequest) query.getSingleResult();
			System.out.println("repos:"+sellrequest.getFarmer().getFarmerId());
				return true;
		} catch (Exception e) {
			return false;
		}
	}
	@Transactional
	public SellRequest removeSellRequestForCropId(long cropId) {
		String jpql="select s from SellRequest s where s.crop.CropId=:cid";
		Query query = em.createQuery(jpql);
		query.setParameter("cid", cropId);
		SellRequest sellRequest=(SellRequest) query.getSingleResult();
		em.remove(sellRequest);
		return sellRequest;
	}
	

	public boolean isCropIdPresent(long cropId) {
		try {
			String jpql="select s.crop.CropId from SellRequest s where s.crop.CropId=:cid";
			Query query = em.createQuery(jpql);
			query.setParameter("cid", cropId);
			long cId=(Long) query.getSingleResult();
			if(cId>0)
			{
				return true;
			}
			
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}
