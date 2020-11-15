package com.prodev.dms.common;

import java.util.List;

import com.prodev.dms.domain.BaseEntity;
import com.prodev.dms.domain.TownShip;

public class DeliRateMasterVM extends BaseEntity{
	private long id;
	private String priceRateName;	
	private long cityId;	  
	private double price;
	
	private List<TownShip> townshipIDList;
	public List<TownShip> getTownshipIDList() {
		return townshipIDList;
	}

	public void setTownshipIDList(List<TownShip> townshipIDList) {
		this.townshipIDList = townshipIDList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPriceRateName() {
		return priceRateName;
	}

	public void setPriceRateName(String priceRateName) {
		this.priceRateName = priceRateName;
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
