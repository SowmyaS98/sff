package com.lti.service;

import com.lti.entity.Bidder;

public interface BidderService {
	//public long addOrUpdateBidder(Bidder bidder);
	public String isValidUser(String bidderEmail,String bidderPassword);
	public String forgotPassword(String bidderEmail);
	public long registerBidder(String bidderName,String bidderEmail,String bidderContactNo,String bidderPassword,String addressLine1,String addressLine2,String city,String state,String bidderPincode,String ifsc);
}
