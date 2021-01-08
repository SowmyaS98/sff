package com.lti.repository;

import java.util.List;

import com.lti.entity.SoldCrops;

public interface SoldCropsRepository {
	public long addSoldCrop(SoldCrops soldCrop);
	public List<SoldCrops> findSoldCropByFarmerEmail(String farmerEmail);

}
