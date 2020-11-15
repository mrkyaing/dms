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
import com.prodev.dms.domain.City;
import com.prodev.dms.repository.ICityRepository;

@Controller
@RequestMapping("/city")
public class CityController {
	@Autowired
	ICityRepository repo;
	
	@RequestMapping(value = { "/entry" }, method = RequestMethod.GET)
	public String Entry(Model model) {
		model.addAttribute("city", new City());
		return "city/entry";//view's path
	}
	//saving data to the database.
			@PostMapping("/entry")
			public String Entry(@Valid City entity,BindingResult bindingResult) {
				entity.setActive(true);
				entity.setCreatedDate(new Date());
				entity.setCreatedUserID(1l);			
				 if (bindingResult.hasErrors()) {
			          return "city/entry";
			      }							 
				repo.save(entity);
				return "redirect:list";
			}
	
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String List(Model model) {
		model.addAttribute("citydata", repo.findAll());
		return "city/list";//view's path.
	}
	
	
	
	//editing the data by City id
		//https://www.mkyong.com/ to reference for java project 
	    @RequestMapping("/edit/{Id}")
	    public String edit(@PathVariable long Id, Model model){
	    	City p=repo.findById(Id).orElse(null);
	        model.addAttribute("city",p);
	        return "city/entry";
	    }
	    
	    //updating the record by the city id>>
	    @PostMapping("/city")
		public String UpdateCityById(@Valid City entity,BindingResult bindingResult) {	
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
	    	return "redirect:/city/list";//redirect to action name(list)
	    }
}
