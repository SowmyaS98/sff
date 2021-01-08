package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Crop;
import com.lti.entity.Farmer;
import com.lti.entity.SellRequest;
import com.lti.repository.BidRepository;
import com.lti.repository.CropRepository;
import com.lti.repository.FarmerRepository;
import com.lti.repository.SellRequestRepository;
@Service
public class SellRequestServiceImpl implements SellRequestService {
	@Autowired
	SellRequestRepository sellRequestRepository;
	@Autowired
	FarmerRepository farmerRepository;
	@Autowired
	CropRepository cropRepository;
	@Autowired
	BidRepository bidRepository;
	public long placeSellRequest(double quantity,String farmerEmail,String cropType,String cropName)
	{
		Farmer farmer=farmerRepository.fetchFarmerByEmail(farmerEmail);
		Crop crop=cropRepository.findCropByCropNameAndCropType(cropName, cropType);
		System.out.println(farmer.getFarmerId());
		boolean isFarmerIdPresent=sellRequestRepository.isFarmerIdPresent(farmer.getFarmerId());
		System.out.println("hi"+isFarmerIdPresent);
		boolean isCropIdPresent=sellRequestRepository.isCropIdPresent(crop.getCropId());
		/*if(isFarmerIdPresent )
		{
			return 0;
		}
		else if(isCropIdPresent)
		{
			return 0;
		}*/
		//else
		//{
			SellRequest sellRequest=new SellRequest();
			sellRequest.setApprove("no");
			sellRequest.setQuantity(quantity);
			sellRequest.setFarmer(farmer);
			sellRequest.setCrop(crop);
			SellRequest s=sellRequestRepository.addOrUpdateSellRequest(sellRequest);
			return s.getRequestId();
		//}
	}


}
