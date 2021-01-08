package com.lti.service;

import com.lti.entity.SellRequest;

public interface SellRequestService {
	public long placeSellRequest(double quantity,String farmerEmail,String cropType,String cropName);

}
