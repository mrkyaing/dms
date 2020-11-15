package com.prodev.dms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/delireceive")
public class DeliReceiveController {
	
	@RequestMapping(value = { "/entry" }, method = RequestMethod.GET)
	public String Entry() {
		return "delireceive/entry";//view's path
	}
	
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String List() {
		return "delireceive/list";//view's path
	}
}
