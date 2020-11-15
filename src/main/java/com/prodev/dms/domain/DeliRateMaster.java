package com.prodev.dms.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="DeliRateMaster")
public class DeliRateMaster extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 458677793427411881L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String priceRateName;
	
	private long cityId;	  
	@ManyToOne(fetch =FetchType.EAGER,optional=false)	 
	@JoinColumn(name="City_cityId")
	private City city;	

	private double price;
	
	

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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
