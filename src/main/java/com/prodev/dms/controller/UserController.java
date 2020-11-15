package com.prodev.dms.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import com.prodev.dms.domain.Role;
import com.prodev.dms.domain.User;
import com.prodev.dms.domain.UserRole;
import com.prodev.dms.enums.RolesEnum;
import com.prodev.dms.services.RoleServices;
import com.prodev.dms.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService service;
	
	@Autowired
	RoleServices roleservice;
	@RequestMapping(value = { "/entry" }, method = RequestMethod.GET)
	public String Entry(Model model) {
		 model.addAttribute("user", new User());
		return "user/entry";//view's path
	}
	 //saving data to the database.
		@PostMapping("/entry")
		public String Entry(@Valid User entity,BindingResult bindingResult) {
			entity.setEnabled(true);
			entity.setActive(true);
			entity.setCreatedDate(new Date());
			entity.setCreatedUserID(1l);			
			 if (bindingResult.hasErrors()) {
		          return "user/entry";
		      }			
			 else if (service.findByUserName(entity.getUsername()) == null) {
				   Set<UserRole> userRoles = new HashSet<>();
				 Role r=roleservice.getRoleBy(RolesEnum.DELIVERY.getRoleName());
				userRoles.add(new UserRole(entity, r));
				 entity.getUserRoles().addAll(userRoles);
				 service.createUser(entity);
			}
			 return "redirect:list";
		}
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String List(Model model) {
		Iterable<User> data=service.getUserList();
		model.addAttribute("userdata",data);
		return "user/list";//view's path.
	}
	
	       //editing the data by  id
			//https://www.mkyong.com/ to reference for java project 
		    @RequestMapping("/edit/{Id}")
		    public String edit(@PathVariable long Id, Model model){
		    	User u=service.UsergetByUserId(Id);
		        model.addAttribute("user",u);
		        return "user/entry";
		    }
		    
		    
		   		    
		    //deleting data from the database.
		    @RequestMapping("/delete/{Id}")
		    public String delete(@PathVariable long Id){
		    	service.DeleteByUserId(Id);
		    	return "redirect:list";//redirect to action name(List)
		    }
}
