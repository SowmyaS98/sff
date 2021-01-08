package com.lti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lti.entity.Admin;
import com.lti.entity.Bid;
import com.lti.entity.Bidder;
import com.lti.entity.Crop;
import com.lti.entity.Farmer;
import com.lti.entity.Insurance;
import com.lti.entity.SellRequest;
import com.lti.entity.SoldCrops;
import com.lti.service.AdminService;
import com.lti.service.BidService;
import com.lti.service.BidderService;
import com.lti.service.CropService;
import com.lti.service.FarmerService;
import com.lti.service.InsuranceService;
import com.lti.service.SellRequestService;

@Component
public class Controller {
	@Autowired
	FarmerService farmerService;
	@Autowired
	BidderService bidderService;
	@Autowired
	AdminService adminService;
	@Autowired
	CropService cropService;
	@Autowired
	InsuranceService insuranceService;
	@Autowired
	SellRequestService sellRequestService;
	@Autowired
	BidService bidService;
	
	/*public long addOrUpdateFarmer(Farmer farmer) {
		return farmerService.addOrUpdateFarmer(farmer);
	}*/
	/*public long registerFarmer(String farmerName,String farmerEmail,String farmerContactNo,String farmerPassword,String addressLine1,String addressLine2,String city,String state,String farmerPincode,String ifsc,String area,String address,String pincode)
	{
		return farmerService.registerFarmer(farmerName, farmerEmail, farmerContactNo, farmerPassword, addressLine1, addressLine2, city, state, farmerPincode, ifsc, area, address, pincode);
	}*/
	public long registerBidder(String bidderName,String bidderEmail,String bidderContactNo,String bidderPassword,String addressLine1,String addressLine2,String city,String state,String bidderPincode,String ifsc)
	{
		return bidderService.registerBidder(bidderName, bidderEmail, bidderContactNo, bidderPassword, addressLine1, addressLine2, city, state, bidderPincode, ifsc);
	}
	public String isValidUser(String Email,String Password)
	{
		String farmer=farmerService.isValidUser(Email, Password);
		if(farmer.equals("valid"))
		{
			return "farmer";
		}
		else if(farmer.equals("pending"))
		{
			return "approval pending farmer";
		}
		else
		{
			String bidder=bidderService.isValidUser(Email, Password);
			if(bidder.equals("valid"))
			{
				return "bidder";
			}
			else if(bidder.equals("pending"))
			{
				return "approval pending bidder";
			}
			else
			{
				boolean admin=adminService.isValidUser(Email, Password);
				if(admin)
				{
					return "admin";
				}
				else
				{
					return null;
				}
			}
		}
	}
	public String forgotPassword(String Email) {
		String s = farmerService.forgotPassword(Email);
		if(s!=null)
			return s;
		else {
			String s1 = bidderService.forgotPassword(Email);
			if(s1!=null)
				return s1;
			else {
				String s2 = adminService.forgotPassword(Email);
				if(s2!=null)
					return s2;
				else
					return "invalid";
			}
		}
	}
	/*public long addOrUpdateBidder(Bidder bidder) {
		return bidderService.addOrUpdateBidder(bidder);
	}*/
	public long addOrUpdateAdmin(Admin admin) {
		return adminService.addOrUpdateAdmin(admin);
	}
	public long addOrUpdateCrop(Crop crop)
	{
		return cropService.addOrUpdateCrop(crop);
	}
	public long addOrUpdateInsurance(Insurance insurance)
	{
		return insuranceService.addOrUpdateInsurance(insurance);
	}
	public Crop findCropById(long cropId)
	{
		return cropService.findCropById(cropId);
	}
	public List<Farmer> pendingApprovalFarmers(){
		return adminService.fetchApprovalPendingFarmers();
	}
	public long approveFarmer(String farmerEmail) {
		return adminService.approveFarmer(farmerEmail);
	}
	public List<Bidder> pendingApprovalBidders(){
		return adminService.fetchApprovalPendingBidders();
	}
	public long approveBidder(String bidderEmail) {
		return adminService.approveBidder(bidderEmail);
	}
	public long rejectFarmer(String farmerEmail) {
		return adminService.rejectFarmer(farmerEmail);
	}
	public long rejectBidder(String bidderEmail) {
		return adminService.rejectBidder(bidderEmail);
	}
	public Crop findCropByCropNameAndCropType(String cropName,String cropType) {
		return cropService.findCropByCropNameAndCropType(cropName, cropType);
	}
	public long placeSellRequest(double quantity,String farmerEmail,String cropType,String cropName)
	{
		//Farmer farmer=farmerService.fetchFarmerByEmail(farmerEmail);
		return sellRequestService.placeSellRequest(quantity, farmerEmail, cropType, cropName);
	}
	//public Farmer findFarmerByEmail
	public List<Object[]> fetchApprovalPendingSellRequest()
	{
		return adminService.fetchApprovalPendingSellRequest();
	}
	public long approveSellRequest(long requestId)
	{
		return adminService.approveSellRequest(requestId);
	}
	public long rejectSellRequestApproval(long requestId)
	{
		return adminService.rejectSellRequestApproval(requestId);
	}
	public Object[] displayBasePriceAndMaxAmount(String CropName,String CropType)
	{
		return bidService.displayBasePriceAndCurrentBid(CropName, CropType);
	}
	public Crop fetch(String cropName,String cropType)
	{
		return cropService.findCropByCropNameAndCropType(cropName, cropType);
	}
	public long PlaceBidRequest(String cropType, String cropName, double bidAmount, String bidderEmail)
	{
		return bidService.placeBid(cropType, cropName, bidAmount, bidderEmail);
	}
	public List<List<Double>> viewMarketPlace(String cropName, String cropType){
		return farmerService.viewMarketPlace(cropName, cropType);
	}
	public List<Object[]> fetchApprovalPendingBids(){
		return adminService.fetchApprovalPendingBids();
	}
	public long approveBidRequest(long bidId) {
		return adminService.approveBid(bidId);
	}
	public List<Object[]> viewAllBids(long cropId){
		return adminService.viewBid(cropId);
	}
	public long sellCropToBidder(long bidId) {
		return adminService.sellCropToBidder(bidId);
	}
	public List<SoldCrops> viewSoldHistory(String farmerEmail){
		return farmerService.viewSoldHistory(farmerEmail);
	}
}
