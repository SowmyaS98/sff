package com.lti.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lti.entity.Crop;
import com.lti.entity.Farmer;
import com.lti.entity.FarmerAddress;
import com.lti.entity.FarmerBank;
import com.lti.entity.FarmerLand;
import com.lti.entity.SoldCrops;
import com.lti.repository.BidRepository;
import com.lti.repository.CropRepository;
import com.lti.repository.FarmerRepository;
import com.lti.repository.SellRequestRepository;
import com.lti.repository.SoldCropsRepository;
@Component
public class FarmerServiceImpl implements FarmerService {
	@Autowired
	FarmerRepository farmerRepository;
	@Autowired
	CropRepository cropRepository;
	@Autowired
	BidRepository bidRepository;
	@Autowired
	SellRequestRepository sellRequestRepository;
	@Autowired
	SoldCropsRepository soldCropsRepository;
	
	public String isValidUser(String farmerEmail,String farmerPassword) {
		Farmer farmer= farmerRepository.isValidUser(farmerEmail, farmerPassword);
		if(farmer!=null)
		{
			if(farmer.getFarmerApprove().equals("yes"))
				return "valid";
			else
				return "pending";
		}
		else
			return "invalid";
	}
	public String forgotPassword(String farmerEmail) {
		Farmer farmer= farmerRepository.fetchFarmerByEmail(farmerEmail);
		if(farmer!=null)
			return farmer.getFarmerPassword();
		else
			return null;
	}

	public Farmer fetchFarmerByEmail(String farmerEmail) {
		Farmer farmer= farmerRepository.fetchFarmerByEmail(farmerEmail);
		if(farmer!=null)
			return farmer;
		else
			return null;
	}

	public long registerFarmer(Farmer farmer) {
		Farmer newFarmer=farmerRepository.addOrUpdateFarmer(farmer);
		if(newFarmer!=null)
			return newFarmer.getFarmerId(); //sending id 
		return 0; //else 0
	}
	public List<List<Double>> viewMarketPlace(String cropName, String cropType) {
		Crop crop = cropRepository.findCropByCropNameAndCropType(cropName, cropType);
		System.out.println(crop.getCropId());
		List<Double> previousBid = bidRepository.previousBidsByCropId(crop.getCropId());
		List<Double> BasePriceMaxBid = new ArrayList<Double>();
		double maxBidAmount=bidRepository.findMaximumBidAmount(crop.getCropId());
		BasePriceMaxBid.add(maxBidAmount);
		BasePriceMaxBid.add(crop.getBasePrice());
		List<List<Double>> obj = new ArrayList<List<Double>>();
		obj.add(previousBid);
		obj.add(BasePriceMaxBid);
		return obj;
	}
	public List<SoldCrops> viewSoldHistory(String farmerEmail) {
		return soldCropsRepository.findSoldCropByFarmerEmail(farmerEmail);
	}
}
