package com.prodev.dms.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "TownShip")
public class TownShip extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1850140765005234738L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;	
	private String name;
	private String description;
	private double gPSX;
	private double gPSY;
	private int postalCode;
	
	private long cityid;
	//one City can have many township
    @ManyToOne(fetch =FetchType.EAGER,optional=false)
    @JoinColumn(name="Township_cityid")
    private City city;
      
	public long getCityid() {
		return cityid;
	}

	public void setCityid(long cityid) {
		this.cityid = cityid;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public TownShip() {
		
	}

	public TownShip(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public double getgPSX() {
		return gPSX;
	}

	public void setgPSX(double gPSX) {
		this.gPSX = gPSX;
	}

	public double getgPSY() {
		return gPSY;
	}

	public void setgPSY(double gPSY) {
		this.gPSY = gPSY;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	

}
