package com.prodev.dms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prodev.dms.common.DeliRateMasterVM;
import com.prodev.dms.domain.City;
import com.prodev.dms.domain.Customer;
import com.prodev.dms.domain.DeliRateDetail;
import com.prodev.dms.domain.DeliRateMaster;
import com.prodev.dms.domain.TownShip;
import com.prodev.dms.repository.ICityRepository;
import com.prodev.dms.repository.IDeliRateDetailRepository;
import com.prodev.dms.repository.IDeliRateMasterRepository;
import com.prodev.dms.repository.ITownShipRepository;

@Controller
@RequestMapping("/delipricerate")
public class DeliPriceRateController {

	@Autowired
	ICityRepository cityrepo;
	@Autowired
	ITownShipRepository townshiprepo;
	@Autowired
	IDeliRateMasterRepository masterrepo;
	@Autowired
	IDeliRateDetailRepository detailrepo;

	@RequestMapping(value = { "/entry" }, method = RequestMethod.GET)
	public String Entry(Model model) {
		model.addAttribute("DeliRateMasterVM", new DeliRateMasterVM());
		model.addAttribute("citydata", cityrepo.findAll());
		model.addAttribute("townshipdata", townshiprepo.findAll());
		return "delipriceRate/entry";
	}

	// saving data to the database.
	@PostMapping("/entry")
	public String Entry(@Valid DeliRateMasterVM Entity, 
			ModelMap model, 
			BindingResult bindingResult) {
		DeliRateMaster masterEntity = new DeliRateMaster();
		masterEntity.setPriceRateName(Entity.getPriceRateName());
		masterEntity.setPrice(Entity.getPrice());
		masterEntity.setCityId(Entity.getCityId());
		City c = cityrepo.findById(Entity.getCityId()).orElse(null);
		masterEntity.setCity(c);
		masterEntity.setActive(true);
		masterEntity.setCreatedDate(new Date());
		masterEntity.setCreatedUserID(1l);
		masterrepo.save(masterEntity);
		model.addAttribute("townshipList", Entity.getTownshipIDList());
		ArrayList<DeliRateDetail> detaillist = new ArrayList<DeliRateDetail>();
		for (TownShip item : getTLists()) {
			DeliRateDetail d = new DeliRateDetail();
			d.setDeliRateMasterID(masterEntity.getId());
			d.setDeliRateMaster(masterEntity);
			d.setTownshipId(item.getId());// get township id
			d.setTownship(item);// set township data
			d.setActive(true);
			d.setCreatedDate(new Date());
			d.setCreatedUserID(1l);
			detaillist.add(d);
		}
		detailrepo.saveAll(detaillist);
		if (bindingResult.hasErrors()) {
			return "delipriceRate/entry";
		}

		return "redirect:list";
	}

	@ModelAttribute("townshipList")
	public List<TownShip> getTLists() {
		return (List<TownShip>) townshiprepo.findAll();
	}

	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String List(Model model) {
		List<DeliRateMaster> master = (List<DeliRateMaster>) masterrepo.findAll();
		model.addAttribute("deliratemasterdata", master);
		List<TownShip> townshiplist = new ArrayList<TownShip>();
		for (DeliRateMaster m : master) {
			List<DeliRateDetail> detailList = detailrepo.findAllDeliRateDetailBymasterId(m.getId());
			for (DeliRateDetail detail : detailList) {
				TownShip t = new TownShip();
				t = townshiprepo.findById(detail.getTownshipId()).orElse(null);
				townshiplist.add(t);
			}
		}
		model.addAttribute("townshipdata", townshiplist);
		return "delipriceRate/list";
	}

	// editing the data by City id
	// https://www.mkyong.com/ to reference for java project
	@RequestMapping("/edit/{Id}")
	public String edit(@PathVariable long Id, Model model) {
		DeliRateMaster m = masterrepo.findById(Id).orElse(null);

		model.addAttribute("DeliRateMasterVM", new DeliRateMasterVM());
		model.addAttribute("citydata", cityrepo.findAll());
		model.addAttribute("townshipdata", townshiprepo.findAll());
		return "delipriceRate/entry";
	}

	// updating the record by the city id>>
	@PostMapping("/delipricerate")
	public String UpdateTownshipById(@Valid DeliRateMasterVM Entity, BindingResult bindingResult, ModelMap model) {
		DeliRateMaster masterEntity = new DeliRateMaster();
		masterEntity.setPriceRateName(Entity.getPriceRateName());
		masterEntity.setPrice(Entity.getPrice());
		masterEntity.setCityId(Entity.getCityId());
		City c = cityrepo.findById(Entity.getCityId()).orElse(null);
		masterEntity.setCity(c);
		masterEntity.setActive(true);
		masterEntity.setCreatedDate(new Date());
		masterEntity.setCreatedUserID(1l);
		masterrepo.save(masterEntity);
		model.addAttribute("townshipList", Entity.getTownshipIDList());
		ArrayList<DeliRateDetail> detaillist = new ArrayList<DeliRateDetail>();
		for (TownShip item : getTLists()) {
			DeliRateDetail d = new DeliRateDetail();
			d.setDeliRateMasterID(masterEntity.getId());
			d.setDeliRateMaster(masterEntity);
			d.setTownshipId(item.getId());// get township id
			d.setTownship(item);// set township data
			d.setActive(true);
			d.setCreatedDate(new Date());
			d.setCreatedUserID(1l);
			detaillist.add(d);
		}
		detailrepo.saveAll(detaillist);
		if (bindingResult.hasErrors()) {
			return "delipriceRate/entry";
		}

		return "redirect:list";
	}

	// deleting data from the database.
	@RequestMapping("/delete/{Id}")
	public String delete(@PathVariable long Id) {
		DeliRateMaster m = masterrepo.findById(Id).orElse(null);
		detailrepo.DeliRateDetailDeleteBymasterId(m.getId());
		masterrepo.deleteById(Id);
		return "delipriceRate/list";
	}

}
