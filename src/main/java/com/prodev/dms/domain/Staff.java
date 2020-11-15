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
@Table(name = "Staff")
public class Staff extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1850140765005237738L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String nrc;
	private String email;
	private String phone;
	private double basicsalary;
	private double phonebillallowance;
	private String address;	
	
	private long townshipId; 
	//one Staff can have many township	  
	@ManyToOne(fetch =FetchType.EAGER,optional=false)	 
	@JoinColumn(name="Township_townshipId") 
	private TownShip township;
	 
	public long getTownshipId() {
		return townshipId;
	}

	public void setTownshipId(long townshipId) {
		this.townshipId = townshipId;
	}

	public TownShip getTownship() {
		return township;
	}

	public void setTownship(TownShip township) {
		this.township = township;
	}

	public String getNrc() {
		return nrc;
	}

	public void setNrc(String nrc) {
		this.nrc = nrc;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getBasicsalary() {
		return basicsalary;
	}

	public void setBasicsalary(double basicsalary) {
		this.basicsalary = basicsalary;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public double getPhonebillallowance() {
		return phonebillallowance;
	}

	public void setPhonebillallowance(double phonebillallowance) {
		this.phonebillallowance = phonebillallowance;
	}

}
