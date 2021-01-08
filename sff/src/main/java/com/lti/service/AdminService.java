package com.lti.service;

import java.util.List;

import com.lti.entity.Admin;
import com.lti.entity.Bid;
import com.lti.entity.Bidder;
import com.lti.entity.Farmer;
import com.lti.entity.SellRequest;

public interface AdminService {
	public long addOrUpdateAdmin(Admin admin);
	public boolean isValidUser(String adminEmail,String adminPassword);
	public List<Farmer> fetchApprovalPendingFarmers();
	public long approveFarmer(String farmerEmail);
	public List<Bidder> fetchApprovalPendingBidders();
	public long approveBidder(String bidderEmail);
	public long rejectFarmer(String farmerEmail);
	public long rejectBidder(String bidderEmail);
	public String forgotPassword(String adminEmail);
	public List<Object[]> fetchApprovalPendingSellRequest();
	public long approveSellRequest(long requestId);
	public long rejectSellRequestApproval(long requestId);
	public List<Object[]> fetchApprovalPendingBids();
	public long approveBid(long bidId);
	public List<Object[]> viewBid(long cropId);
	public long sellCropToBidder(long bidId);
}
