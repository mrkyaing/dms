package com.prodev.dms.controller;

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

import com.prodev.dms.domain.City;
import com.prodev.dms.domain.TownShip;
import com.prodev.dms.repository.ICityRepository;
import com.prodev.dms.repository.ITownShipRepository;

@Controller
@RequestMapping("/township")
public class TownshipController {
	@Autowired
	ITownShipRepository repo;
	@Autowired
	ICityRepository cityrepo;
	@RequestMapping(value = { "/entry" }, method = RequestMethod.GET)
	public String Entry(Model model) {
		model.addAttribute("township", new TownShip());
		model.addAttribute("citydata",cityrepo.findAll());
		return "township/entry";//view's path
	}
	//saving data to the database.
	@PostMapping("/entry")
	public String Entry(@Valid TownShip entity,BindingResult bindingResult) {
		entity.setActive(true);
		entity.setCreatedDate(new Date());
		entity.setCreatedUserID(1l);	
		City c=cityrepo.findById(entity.getCityid()).orElse(null);
		entity.setCity(c);
		 if (bindingResult.hasErrors()) {
	          return "city/entry";
	      }							 
		repo.save(entity);
		return "redirect:list";
	}
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String List(Model model) {
		model.addAttribute("townshipdata", repo.findAll());
		return "township/list";//view's path.
	}
	
	//editing the data by City id
			//https://www.mkyong.com/ to reference for java project 
		    @RequestMapping("/edit/{Id}")
		    public String edit(@PathVariable long Id, Model model){
		    	TownShip data=repo.findById(Id).orElse(null);
		        model.addAttribute("township",data);
		        model.addAttribute("citydata",cityrepo.findAll());
		        return "township/entry";
		    }
		    
		    //updating the record by the city id>>
		    @PostMapping("/township")
			public String UpdateTownshipById(@Valid TownShip entity,BindingResult bindingResult) {	
				entity.setUpdatedDate(new Date());
				entity.setUpdatedUserID(1l);
				 if (bindingResult.hasErrors()) {
			          return "city/entry";
			      }							 
				repo.save(entity);
				return "redirect:list";
			}	    
		  //deleting data from the database.
		    @RequestMapping("/delete/{Id}")
		    public String delete(@PathVariable long Id){
		    	repo.deleteById(Id);
		    	return "redirect:list";//redirect to action name(List)
		    }
}
