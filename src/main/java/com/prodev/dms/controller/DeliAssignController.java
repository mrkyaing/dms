package com.prodev.dms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prodev.dms.domain.DeliStaffAssignToRoute;
import com.prodev.dms.repository.IDeliProductRepository;
import com.prodev.dms.repository.IDeliStaffAssignRepository;
import com.prodev.dms.repository.IStaffRepository;

@Controller
@RequestMapping("/deliassign")
public class DeliAssignController {
	@Autowired
	IDeliStaffAssignRepository repo;
	@Autowired
	IStaffRepository staffrepo;
	@Autowired
	IDeliProductRepository deliproductrepo;
	@RequestMapping(value = { "/entry" }, method = RequestMethod.GET)
	public String Entry(Model model) {
		model.addAttribute("DeliStaffAssignToRoute", new DeliStaffAssignToRoute());
		model.addAttribute("StaffList",staffrepo.findAll());
		model.addAttribute("DeliProductList",deliproductrepo.findAll());
		return "deliassign/entry";//view's path
	}
	//saving data to the database.
	@RequestMapping(value="/entry",method = RequestMethod.POST)
	public String Entry(@RequestParam String DeliStaffID, @RequestParam String DeliProductID) throws ParseException {
	    System.out.println("hi");
		System.out.println(DeliStaffID);
		System.out.println(DeliProductID);
		System.out.println("hi");
		return "redirect:list";
	}
	
	@RequestMapping(value = "/getDefectCount",method=RequestMethod.POST)
	public  DeliStaffAssignToRoute postEmployeeData(@RequestBody DeliStaffAssignToRoute ok) {
		
		// process the developer object
		// Your implementation. For demo I hard-coded the Defect counts
		return ok;
	}
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String List() {
		return "deliassign/list";
	}
}
