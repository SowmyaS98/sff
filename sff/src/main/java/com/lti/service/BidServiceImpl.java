package com.lti.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.lti.entity.Bid;
import com.lti.entity.Bidder;
import com.lti.entity.Crop;
import com.lti.repository.BidRepository;
import com.lti.repository.BidderRepository;
import com.lti.repository.CropRepository;
import com.lti.repository.SellRequestRepository;
@Component
public class BidServiceImpl implements BidService {
	@Autowired
	BidRepository bidRepository;
	@Autowired
	CropRepository cropRepository;
	@Autowired
	BidderRepository bidderRepository;
	@Autowired
	SellRequestRepository sellRequestRepository;

	public Object[] displayBasePriceAndCurrentBid(String cropName,String cropType)
	{
		Crop crop=cropRepository.findCropByCropNameAndCropType(cropName, cropType);
		//System.out.println(crop.getBasePrice());
		double maxBidAmount=bidRepository.findMaximumBidAmount(crop.getCropId());
		Object[] obj={crop.getBasePrice(),maxBidAmount};
		return obj;
	}


	public long placeBid(String cropType, String cropName, double bidAmount, String bidderEmail) {
		Crop crop=cropRepository.findCropByCropNameAndCropType(cropName, cropType);
		double maxBidAmount=bidRepository.findMaximumBidAmount(crop.getCropId());
		if(bidAmount>maxBidAmount)
		{
			Bid bid=new Bid();
			bid.setBidAmount(bidAmount);
			bid.setBidDate(LocalDate.now());
			bid.setBidApprove("no");
			bid.setBidSold("no");
			Bidder bidder=bidderRepository.fetchBidderByEmail(bidderEmail);
			bid.setBidder(bidder);
			bid.setCrop(crop);
			Bid newbid=bidRepository.addOrUpdateBid(bid);
			return newbid.getBidId();
		}
		else
		{
			return 0;
		}
	}
	public List<Object[]> viewSoldCrops(List<Long> cropId){
		return bidRepository.viewAllSoldCrops(cropId);
	}
	public List<Object[]> fetchCropTypeAndCropNameForBid(){
		return sellRequestRepository.fetchCrops();
	}
}
