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

import com.prodev.dms.domain.Role;
import com.prodev.dms.domain.Staff;
import com.prodev.dms.services.RoleServices;

@Controller
@RequestMapping("/role")
public class RoleController {
	@Autowired
	RoleServices service;
	@RequestMapping(value = { "/entry" }, method = RequestMethod.GET)
	public String Entry(Model model) {
		model.addAttribute("role",new Role());
		return "role/entry";//view's path
	}
	//saving data to the database.
	@PostMapping("/entry")
	public String Entry(@Valid Role r,BindingResult bindingResult) {
		r.setActive(true);
		r.setCreatedDate(new Date());
		r.setCreatedUserID(1l);
		 if (bindingResult.hasErrors()) {
	          return "role/entry";
	      }
		service.SaveRole(r);
		return "redirect:list";
	}
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String List(Model model) {
		Iterable<Role> data=service.getAllRole();
		model.addAttribute("roledata",data);
		return "role/list";//view's path.
	}
	//editing the data by  id
			//https://www.mkyong.com/ to reference for java project 
		    @RequestMapping("/edit/{Id}")
		    public String edit(@PathVariable long Id, Model model){
		    	model.addAttribute("role", service.getRoleId(Id));			
				return "role/entry";//view's path
		    }
}
