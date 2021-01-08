package com.lti.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

import com.lti.entity.Bid;
import com.lti.entity.Farmer;
import com.lti.entity.SellRequest;
@Repository
public class BidRepositoryImpl implements BidRepository {
	@PersistenceContext
	EntityManager em;

	@Transactional
	public Bid addOrUpdateBid(Bid bid) {
		//Bid b=em.merge(bid);
		return em.merge(bid); 
	}
	@Transactional
	public double findMaximumBidAmount(long cropId) {
		try {
			String jpql="select max(b.bidAmount) from Bid b where b.crop.CropId=:cId";
			Query query=em.createQuery(jpql);
			query.setParameter("cId", cropId);
			double maxBidAmount=(Double)query.getSingleResult();
			return maxBidAmount;
		} catch (Exception e) {
			return 0;
		}
	}
	@Transactional
	public List<Double> previousBidsByCropId(long cropId) {
		String jpql = "select b.bidAmount from Bid b where b.crop.CropId=:cId";
		Query query=em.createQuery(jpql);
		query.setParameter("cId", cropId);
		return query.getResultList();
	}
	@Transactional
	public List<Object[]> fetchBidsByBidApproveNo() {
		String jpql = "select b.bidId,b1.bidderId,c.CropId,b.bidAmount from Bid b join b.bidder b1 join b.crop c where b.bidApprove='no'";
		TypedQuery<Object[]> query = em.createQuery(jpql,Object[].class);
		return query.getResultList();
	}
	@Transactional
	public Bid fetchBidByBidId(long bidId) {
		String jpql = "select b from Bid b where b.bidId=:bId";
		Query query = em.createQuery(jpql);
		query.setParameter("bId", bidId);
		Bid bid=(Bid)query.getSingleResult();
		return bid;
	}
	@Transactional
	public Bid updateBidBybidId(long bidId) {
		String jpql = "update Bid b set b.bidApprove='yes' where b.bidId=:bId";
		Query query = em.createQuery(jpql);
		query.setParameter("bId", bidId);
		query.executeUpdate();
		Bid bid = fetchBidByBidId(bidId);
		return bid;
	}
	public List<Object[]> fetchAllBidsByApproveYes(long cropId) {
		String jpql = "select b.bidId,b.bidder.bidderId,b.bidAmount,b.bidSold from Bid b where b.bidApprove='yes' and b.crop.CropId=:cId and b.bidSold='no'";
		TypedQuery<Object[]> query = em.createQuery(jpql,Object[].class);
		query.setParameter("cId", cropId);
		return query.getResultList();
	}
	@Transactional
	public Bid updateBidSold(long bidId) {
		String jpql = "update Bid b set b.bidSold='yes' where b.bidId=:bId";
		Query query = em.createQuery(jpql);
		query.setParameter("bId", bidId);
		query.executeUpdate();
		Bid bid = fetchBidByBidId(bidId);
		return bid;
	}
	@Transactional
	public List<Object[]> viewAllSoldCrops(List<Long> cropId){
		List<Object[]> sold = new ArrayList<Object[]>();
		try {
			Object[] obj =null;
			for(Long c : cropId) {
				String jpql = "select b.bidDate,b.bidAmount,b.crop.basePrice,b.crop.CropName,b.crop.CropType,s.quantity,s.requestId from Bid b join sellRequest s where b.bidSold='yes' and b.crop.CropId=:cId";
				Query query = em.createQuery(jpql);
				query.setParameter("cId",c);
				obj = (Object[]) query.getSingleResult();
				sold.add(obj);
			}
		} catch (Exception e) {
		}
		return sold;
	}
	public boolean isCropSold(long cropId) {
		try {
			String jpql="select b.crop.CropId from bid b where b.bidSold='yes' and b.crop.CropId=:cId";
			Query query = em.createQuery(jpql);
			query.setParameter("cId",cropId);
			long cropid=(Long)query.getSingleResult();
			if(cropid>0)
				return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}
