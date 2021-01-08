package com.lti.service;

import java.util.List;

import com.lti.entity.Bid;

public interface BidService {
	public long placeBid(String cropType,String cropName,double bidAmount, String bidderEmail);
	public Object[] displayBasePriceAndCurrentBid(String cropName,String cropType);
	public List<Object[]> viewSoldCrops(List<Long> cropId);
	public List<Object[]> fetchCropTypeAndCropNameForBid();
}
