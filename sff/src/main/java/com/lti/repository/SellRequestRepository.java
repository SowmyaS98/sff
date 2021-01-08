package com.lti.repository;

import java.util.List;

import com.lti.entity.SellRequest;

public interface SellRequestRepository {
	public SellRequest addOrUpdateSellRequest(SellRequest sellRequest);
	public List<Object[]> fetchApprovalPendingSellRequest();
	public SellRequest updateSellRequestByRequestId(long requestId);
	public SellRequest fetchSellRequestByRequestId(long requestId);
	public SellRequest removeSellRequestByRequestId(long requestId);
	public List<Long> fetchCropIdByFarmerEmail(String farmerEmail);
	public List<Object[]> fetchCrops();
	public boolean isFarmerIdPresent(long farmerId);
	public boolean isCropIdPresent(long cropId);
	public SellRequest removeSellRequestForCropId(long cropId);
}
