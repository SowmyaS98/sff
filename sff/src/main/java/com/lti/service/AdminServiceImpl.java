package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lti.entity.Admin;
import com.lti.entity.Bid;
import com.lti.entity.Bidder;
import com.lti.entity.Farmer;
import com.lti.entity.SellRequest;
import com.lti.entity.SoldCrops;
import com.lti.repository.AdminRepository;
import com.lti.repository.BidRepository;
import com.lti.repository.BidderRepository;
import com.lti.repository.FarmerRepository;
import com.lti.repository.SellRequestRepository;
import com.lti.repository.SoldCropsRepository;
@Component
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	FarmerRepository farmerRepository;
	@Autowired
	BidderRepository bidderRepository;
	@Autowired
	SellRequestRepository sellRequestRepository;
	@Autowired
	BidRepository bidRepository;
	@Autowired
	SoldCropsRepository soldCropsRepository;
	public long addOrUpdateAdmin(Admin admin) {
		return adminRepository.addOrUpdateAdmin(admin);
	}
	public boolean isValidUser(String adminEmail,String adminPassword) {
		Admin admin= adminRepository.fetchAdminByEmailAndPassword(adminEmail, adminPassword);
		if(admin!=null)
			return true;
		else
			return false;
	}
	public List<Farmer> fetchApprovalPendingFarmers(){
		return farmerRepository.fetchApprovalPendingFarmers();
	}
	public long approveFarmer(String farmerEmail) {
		Farmer farmer= farmerRepository.updateFarmerByEmail(farmerEmail);
		return farmer.getFarmerId();
	}
	public List<Bidder> fetchApprovalPendingBidders (){
		return bidderRepository.fetchApprovalPendingBidders();
	}
	public long approveBidder(String bidderEmail) {
		Bidder bidder= bidderRepository.updateBidderByEmail(bidderEmail);
		return bidder.getBidderId();
	}
	public long approveSellRequest(long requestId)
	{
		SellRequest sellRequest=sellRequestRepository.updateSellRequestByRequestId(requestId);
		if(sellRequest!=null)
		{
			return sellRequest.getRequestId();
		}
		else
			return 0;
	}
	public long rejectFarmer(String farmerEmail) {
		return farmerRepository.rejectFarmer(farmerEmail);
	}
	public long rejectBidder(String bidderEmail) {
		return bidderRepository.rejectBidder(bidderEmail);
	}
	public String forgotPassword(String adminEmail) {
		Admin admin= adminRepository.fetchAdminByEmail(adminEmail);
		if(admin!=null)
		{
			System.out.println(admin.getAdminPassword());
			return admin.getAdminPassword();
		}
		else
			return null;
	}
	public List<Object[]> fetchApprovalPendingSellRequest() {
		
		return sellRequestRepository.fetchApprovalPendingSellRequest();
	}
	public long rejectSellRequestApproval(long requestId) {
		SellRequest sellRequest=sellRequestRepository.removeSellRequestByRequestId(requestId);
		if(sellRequest!=null)
			return sellRequest.getRequestId();
		return 0;
	}
	public List<Object[]> fetchApprovalPendingBids() {
		return bidRepository.fetchBidsByBidApproveNo();
	}
	
	public long approveBid(long bidId)
	{
		Bid bid=bidRepository.updateBidBybidId(bidId);
		if(bid!=null)
		{
			return bid.getBidId();
		}
		else
			return 0;
	}
	public List<Object[]> viewBid(long cropId) {
		return bidRepository.fetchAllBidsByApproveYes(cropId);
	}
	public long sellCropToBidder(long bidId) {
		Bid bid = bidRepository.updateBidSold(bidId);
		SellRequest sellRequest=sellRequestRepository.removeSellRequestForCropId(bid.getCrop().getCropId());
		//SellRequest sellRequest=sellRequestRepository.getSellRequestByCropId();
		SoldCrops soldCrop=new SoldCrops();
		soldCrop.setBidAmount(bid.getBidAmount());
		soldCrop.setBidDate(bid.getBidDate());
		soldCrop.setCropId(bid.getCrop().getCropId());
		soldCrop.setFarmerEmail(sellRequest.getFarmer().getFarmerEmail());
		soldCrop.setQuantity(sellRequest.getQuantity());
		//soldCrop.setTotalAmount(bid.getBidAmount() * sellRequest.getQuantity());
		long id=soldCropsRepository.addSoldCrop(soldCrop);
		//soldHistoryRepository.add(bid.getBidAmount(),bid.getBidDate(),bid.getCrop().getCropId(),sellRequest.getQuantity(),sellRequest.getFarmer().getFarmerEmail());
		return bid.getBidder().getBidderId();
	}
}
