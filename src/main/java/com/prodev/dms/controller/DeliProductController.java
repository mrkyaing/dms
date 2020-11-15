/**
 * 
 */
package com.prodev.dms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.prodev.dms.domain.Customer;
import com.prodev.dms.domain.DeliProduct;
import com.prodev.dms.domain.Staff;
import com.prodev.dms.domain.TownShip;
import com.prodev.dms.repository.ICustomerRepository;
import com.prodev.dms.repository.IDeliProductRepository;
import com.prodev.dms.repository.IStaffRepository;
import com.prodev.dms.repository.ITownShipRepository;


@Controller
@RequestMapping("/deliproduct")
public class DeliProductController  {
	
	@Autowired
	IDeliProductRepository deliproductRepo;
	@Autowired
	ITownShipRepository townshiprepo;
	@Autowired
	ICustomerRepository customerrepo;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model) {
		Iterable<DeliProduct> data=deliproductRepo.findAll();
		model.addAttribute("productdata",data);
		return "deliproduct/list";
	}
	@RequestMapping(value="/entry",method=RequestMethod.GET)
	public String Entry(Model model) {
	    pageLoad(model);
		return "deliproduct/entry";
	}
	private void pageLoad(Model model) {
		model.addAttribute("deliproduct", new DeliProduct());
	    model.addAttribute("townshipdata", townshiprepo.findAll());
	    model.addAttribute("fromcustomerdata", customerrepo.findAll());
	    model.addAttribute("tocustomerdata", customerrepo.findAll());
	   
	}
	  //saving data to the database.
	@PostMapping("/entry")
	public String Entry(@Valid DeliProduct entity,@RequestParam("delidate") String delidate ,BindingResult bindingResult) throws ParseException {
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 Date okidelidate=formatter.parse(delidate);
		entity.setDeliDate(okidelidate);
		entity.setActive(true);
		entity.setCreatedDate(new Date());
		entity.setCreatedUserID(1L);
		TownShip t=townshiprepo.findById(entity.getTownshipID()).orElse(null);
		entity.setTownship(t);
		Customer fromcustomer=customerrepo.findById(entity.getFromCustomerID()).orElse(null);
		entity.setFromCustomer(fromcustomer);
		
		Customer tocustomer=customerrepo.findById(entity.getToCustomerID()).orElse(null);
		entity.setToCustomer(tocustomer);
		
		
		
		if (bindingResult.hasErrors()) {
	         // return "deliproduct/entry";
			return "redirect:entry";
	      }
		 deliproductRepo.save(entity);
		return "redirect:list";
	}
	
	//editing the data by product id
	//https://www.mkyong.com/ to reference for java project 
    @RequestMapping("/edit/{Id}")
    public String edit(@PathVariable Integer Id, Model model){
    	Optional<DeliProduct> p=deliproductRepo.findById(Id);
    	model.addAttribute("townshipdata", townshiprepo.findAll());
 	    model.addAttribute("fromcustomerdata", customerrepo.findAll());
 	    model.addAttribute("tocustomerdata", customerrepo.findAll());	   
        model.addAttribute("deliproduct",p);
        return "/deliproduct/entry";
    }
  //Updating data to the database.
    @PostMapping("/product")//action name >>product
    public String ProductUpdateById(@Valid DeliProduct entity,@RequestParam("delidate") String delidate,BindingResult bindingResult) throws ParseException{
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date okidelidate=formatter.parse(delidate);
		entity.setDeliDate(okidelidate);
		entity.setActive(true);
		entity.setCreatedDate(new Date());
		entity.setCreatedUserID(1L);
		TownShip t=townshiprepo.findById(entity.getTownshipID()).orElse(null);
		entity.setTownship(t);
		Customer fromcustomer=customerrepo.findById(entity.getFromCustomerID()).orElse(null);
		entity.setFromCustomer(fromcustomer);
		
		Customer tocustomer=customerrepo.findById(entity.getToCustomerID()).orElse(null);
		entity.setToCustomer(tocustomer);
		
	
		
		if (bindingResult.hasErrors()) {
			return "redirect:entry";
	      }
    	 deliproductRepo.save(entity);
        return "redirect:list";//redirect to action name(list)
    }
  //deleting data from the database.
    @RequestMapping("/delete/{Id}")
    public String delete(@PathVariable Integer Id){
    	deliproductRepo.deleteById(Id);
    	return "redirect:/deliproduct/list";//redirect to action name(list)
    }

}
