package com.lti.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lti.entity.Farmer;
import com.lti.service.AdminService;
import com.lti.service.BidService;
import com.lti.service.BidderService;
import com.lti.service.CropService;
import com.lti.service.FarmerService;
import com.lti.service.InsuranceService;
import com.lti.service.SellRequestService;

@RestController
@CrossOrigin
public class SchemeController {
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
	
	@RequestMapping(value="/registerUser",method=RequestMethod.POST) //http://localhost:8080/sff/registerUser
	public long registerFarmer(@RequestBody Farmer farmer)
	{
		return farmerService.registerFarmer(farmer);
	}
	@RequestMapping(value="/viewPendingApprovalFarmers",method=RequestMethod.GET)
	public List<Farmer> pendingApprovalFarmers(){ //http://localhost:8080/sff//viewPendingApprovalFarmers
		return adminService.fetchApprovalPendingFarmers();
	}

}
