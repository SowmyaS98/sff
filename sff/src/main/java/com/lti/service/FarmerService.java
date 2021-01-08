package com.lti.service;

import java.util.List;

import com.lti.entity.Farmer;
import com.lti.entity.SoldCrops;

public interface FarmerService {
	//public long addOrUpdateFarmer(Farmer farmer);
	public long registerFarmer(Farmer farmer);
	public String isValidUser(String farmerEmail,String farmerPassword);
	public String forgotPassword(String farmerEmail);
	public Farmer fetchFarmerByEmail(String farmerEmail);
	public List<List<Double>> viewMarketPlace(String cropName,String cropType);
	public List<SoldCrops> viewSoldHistory(String farmerEmail);
}
