package com.lti.repository;

import java.util.List;

import com.lti.entity.Admin;
import com.lti.entity.Bidder;
import com.lti.entity.Farmer;

public interface AdminRepository {
	public long addOrUpdateAdmin(Admin admin);
	public Admin fetchAdminByEmailAndPassword(String adminEmail,String adminPassword);
	public Admin fetchAdminByEmail(String adminEmail);
}
