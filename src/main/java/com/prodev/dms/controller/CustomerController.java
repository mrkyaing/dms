package com.prodev.dms.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.prodev.dms.domain.Customer;
import com.prodev.dms.domain.TownShip;
import com.prodev.dms.repository.ICityRepository;
import com.prodev.dms.repository.ICustomerRepository;
import com.prodev.dms.repository.ITownShipRepository;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	ITownShipRepository townshiprepo;
	@Autowired
	ICustomerRepository custorepo;
	@Autowired
	ICityRepository cityrepo;
	@RequestMapping(value = { "/entry" }, method = RequestMethod.GET)
	public String Entry(Model model) {
		model.addAttribute("customer", new Customer());
		model.addAttribute("townshipdata", townshiprepo.findAll());
		model.addAttribute("citydata", cityrepo.findAll());
		return "customer/entry";//view's path
	}
	//saving data to the database.
		@PostMapping("/entry")
		public String Entry(@Valid Customer entity,BindingResult bindingResult) {
			entity.setActive(true);
			entity.setCreatedDate(new Date());
			entity.setCreatedUserID(1l);	
			TownShip t=townshiprepo.findById(entity.getTownshipId()).orElse(null);
			entity.setTownship(t);
			 if (bindingResult.hasErrors()) {
		          return "customer/entry";
		      }							 
			 custorepo.save(entity);
			return "redirect:list";
		}
		
		//@ResponseBody
		//@RequestMapping(value = "getTownshipByCityID/{cityID}", method = RequestMethod.GET)
		//public String loadStatesByCountry(@PathVariable("cityID") int Cityd) {
		//	Gson gson = new Gson();
		//	return gson.toJson(townshiprepo.findByCityID(Cityd));
		//}

		
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String List(Model model) {
		model.addAttribute("customerdata", custorepo.findAll());
		return "customer/list";//view's path.
	}
	
	//editing the data by  id
	//https://www.mkyong.com/ to reference for java project 
    @RequestMapping("/edit/{Id}")
    public String edit(@PathVariable long Id, Model model){
    	Customer c=custorepo.findById(Id).orElse(null);
    	model.addAttribute("customer",c);
		model.addAttribute("townshipdata", townshiprepo.findAll());
		model.addAttribute("citydata", cityrepo.findAll());
		return "customer/entry";//view's path
    }
  //deleting data from the database.
    @RequestMapping("/delete/{Id}")
    public String delete(@PathVariable long Id){
    	custorepo.deleteById(Id);
    	return "redirect:/customer/list";//redirect to action name(list)
    }
}
