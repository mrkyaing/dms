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
@Table(name="Customer")
public class Customer extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 1850140765005234738L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String phoneNo;
	private String email;
	private String address;
	private String type;
	private String bankAccount;
	private long townshipId;
	//one City can have many township
    @ManyToOne(fetch =FetchType.EAGER,optional=false)
    @JoinColumn(name="Township_townshipId")
	private TownShip township;
    
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
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
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
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

    
}
