package com.lti.test;
import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.lti.controller.Controller;
import com.lti.entity.Admin;
import com.lti.entity.Bid;
import com.lti.entity.Bidder;
import com.lti.entity.Crop;
import com.lti.entity.Farmer;
import com.lti.entity.Insurance;


public class MainTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
	Controller controller = context.getBean(Controller.class);

	@Test
	public void pendingApprovalFarmers() {
		List<Farmer> farmers = controller.pendingApprovalFarmers();
		if(!farmers.isEmpty())
		{
			for(Farmer f : farmers) {
				System.out.println(f.getFarmerId() + " " + f.getFarmerEmail()+" "+f.getFarmerApprove());
			}
		}
		else
		{
			System.out.println("no pending approval");
		}

	}
}
