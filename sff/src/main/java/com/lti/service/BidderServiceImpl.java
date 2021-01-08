package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lti.entity.Bidder;
import com.lti.entity.BidderAddress;
import com.lti.entity.BidderBank;
import com.lti.repository.BidderRepository;
@Component
public class BidderServiceImpl implements BidderService {
	@Autowired
	BidderRepository bidderRepository;
	/*public long addOrUpdateBidder(Bidder bidder) {
		return bidderRepository.addOrUpdateBidder(bidder);
	}*/

	public String isValidUser(String bidderEmail, String bidderPassword) {
		// TODO Auto-generated method stub
		Bidder bidder= bidderRepository.fetchBidderByEmailAndPassword(bidderEmail, bidderPassword);
		if(bidder!=null)
		{
			if(bidder.getBidderApprove().equals("yes"))
				return "valid";
			else
				return "pending";
		}
		else
			return "invalid";
	}
	public String forgotPassword(String bidderEmail) {
		Bidder bidder= bidderRepository.fetchBidderByEmail(bidderEmail);
		if(bidder!=null)
			return bidder.getBidderPassword();
		else
			return null;
	}
	public long registerBidder(String bidderName, String bidderEmail, String bidderContactNo, String bidderPassword,
			String addressLine1, String addressLine2, String city, String state, String bidderPincode, String ifsc) {
		Bidder bidder=new Bidder();
		bidder.setBidderName(bidderName);
		bidder.setBidderEmail(bidderEmail);
		bidder.setBidderContactNo(bidderContactNo);
		bidder.setBidderPassword(bidderPassword);
		bidder.setBidderApprove("no".toLowerCase());
		
		BidderAddress bidderAddress=new BidderAddress();
		bidderAddress.setAddressLine1(addressLine1);
		bidderAddress.setAddressLine2(addressLine2);
		bidderAddress.setCity(city);
		bidderAddress.setState(state);
		bidderAddress.setPincode(ifsc);
		bidderAddress.setBidder(bidder);
		
		bidder.setBidderAddress(bidderAddress);
		
		BidderBank bidderBank=new BidderBank();
		bidderBank.setIFSC_code(ifsc);
		bidderBank.setBidder(bidder);
		
		bidder.setBidderBank(bidderBank);
		
		Bidder newBidder=bidderRepository.addOrUpdateBidder(bidder);
		if(newBidder!=null)
		{
			return newBidder.getBidderId();
		}
		return 0;
	}

}
