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
import com.prodev.dms.domain.Staff;
import com.prodev.dms.domain.TownShip;
import com.prodev.dms.repository.ICityRepository;
import com.prodev.dms.repository.IRoleRepository;
import com.prodev.dms.repository.IStaffRepository;
import com.prodev.dms.repository.ITownShipRepository;

@Controller
@RequestMapping("/staff")
public class StaffController {
	@Autowired
	ITownShipRepository townshiprepo;
	@Autowired
	IStaffRepository staffrepo;
	@Autowired
	ICityRepository cityrepo;
	@Autowired
	IRoleRepository rolerepo;
	
	
	@RequestMapping(value = { "/entry" }, method = RequestMethod.GET)
	public String Entry(Model model) {
		model.addAttribute("staff", new Staff());
		model.addAttribute("townshipdata", townshiprepo.findAll());
		model.addAttribute("citydata", cityrepo.findAll());
		return "staff/entry";//view's path
	}
	       //saving data to the database.
			@PostMapping("/entry")
			public String Entry(@Valid Staff entity,BindingResult bindingResult) {
				entity.setActive(true);
				entity.setCreatedDate(new Date());
				entity.setCreatedUserID(1l);	
				TownShip t=townshiprepo.findById(entity.getTownshipId()).orElse(null);
				entity.setTownship(t);
				 if (bindingResult.hasErrors()) {
			          return "staff/entry";
			      }							 
				 staffrepo.save(entity);
				return "redirect:list";
			}
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String List(Model model) {
		model.addAttribute("staffdata", staffrepo.findAll());
		return "staff/list";//view's path.
	}
	
	//editing the data by  id
		//https://www.mkyong.com/ to reference for java project 
	    @RequestMapping("/edit/{Id}")
	    public String edit(@PathVariable long Id, Model model){
	    	Staff s=staffrepo.findById(Id).orElse(null);
	    	model.addAttribute("staff", s);
			model.addAttribute("townshipdata", townshiprepo.findAll());
			model.addAttribute("citydata", cityrepo.findAll());
			return "staff/entry";//view's path
	    }
	  //deleting data from the database.
	    @RequestMapping("/delete/{Id}")
	    public String delete(@PathVariable long Id){
	    	staffrepo.deleteById(Id);
	    	return "redirect:/staff/list";//redirect to action name(list)
	    }
}
