package com.lti.repository;

import java.util.List;

import com.lti.entity.Bid;
import com.lti.entity.Farmer;

public interface BidRepository {
	public Bid addOrUpdateBid(Bid bid);
	public double findMaximumBidAmount(long cropId);
	public List<Double> previousBidsByCropId(long cropId);
	public List<Object[]> fetchBidsByBidApproveNo();
	public Bid fetchBidByBidId(long bidId);
	public Bid updateBidBybidId(long bidId);
	public List<Object[]> fetchAllBidsByApproveYes(long cropId);
	public Bid updateBidSold(long bidId);
	public List<Object[]> viewAllSoldCrops(List<Long> cropId);
	public boolean isCropSold(long cropId);
}
