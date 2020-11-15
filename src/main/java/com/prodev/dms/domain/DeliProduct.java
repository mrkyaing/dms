package com.prodev.dms.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "DeliProduct")
public class DeliProduct extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;

	@NotNull
	private String DeliProductCode;
	@NotNull
	private String DeliProductName;
	@NotNull
	private double DeliProductPrice;

	private String DeliProductSize;
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@Column(name = "DeliDate", nullable = true)
	private Date DeliDate;
	private int DeliTimeInHours;
	private int DeliTimeInMinutes;

	private String Remark;

	private long FromCustomerID;
	// many deli-product can have in one customer
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FromCustomerID", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private Customer fromCustomer;

	private long ToCustomerID;
	// many deli-product can have in one customer
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ToCustomerID", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private Customer toCustomer;

	private long TownshipID;
	// many deli product can have one township
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TownshipID", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private TownShip Township;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getProductCode() {
		return DeliProductCode;
	}

	public void setProductCode(String deliproductCode) {
		DeliProductCode = deliproductCode;
	}

	public String getProductName() {
		return DeliProductName;
	}

	public void setProductName(String deliproductName) {
		DeliProductName = deliproductName;
	}

	public String getDeliProductCode() {
		return DeliProductCode;
	}

	public void setDeliProductCode(String deliProductCode) {
		DeliProductCode = deliProductCode;
	}

	public String getDeliProductName() {
		return DeliProductName;
	}

	public void setDeliProductName(String deliProductName) {
		DeliProductName = deliProductName;
	}

	public double getDeliProductPrice() {
		return DeliProductPrice;
	}

	public void setDeliProductPrice(double deliProductPrice) {
		DeliProductPrice = deliProductPrice;
	}

	public String getDeliProductSize() {
		return DeliProductSize;
	}

	public void setDeliProductSize(String deliProductSize) {
		DeliProductSize = deliProductSize;
	}

	public Date getDeliDate() {
		return DeliDate;
	}

	public void setDeliDate(Date deliDate) {
		DeliDate = deliDate;
	}

	public int getDeliTimeInHours() {
		return DeliTimeInHours;
	}

	public void setDeliTimeInHours(int deliTimeInHours) {
		DeliTimeInHours = deliTimeInHours;
	}

	public int getDeliTimeInMinutes() {
		return DeliTimeInMinutes;
	}

	public void setDeliTimeInMinutes(int deliTimeInMinutes) {
		DeliTimeInMinutes = deliTimeInMinutes;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public long getFromCustomerID() {
		return FromCustomerID;
	}

	public void setFromCustomerID(long fromCustomerID) {
		FromCustomerID = fromCustomerID;
	}

	public Customer getFromCustomer() {
		return fromCustomer;
	}

	public void setFromCustomer(Customer fromCustomer) {
		this.fromCustomer = fromCustomer;
	}

	public long getToCustomerID() {
		return ToCustomerID;
	}

	public void setToCustomerID(long toCustomerID) {
		ToCustomerID = toCustomerID;
	}

	public Customer getToCustomer() {
		return toCustomer;
	}

	public void setToCustomer(Customer toCustomer) {
		this.toCustomer = toCustomer;
	}

	public long getTownshipID() {
		return TownshipID;
	}

	public void setTownshipID(long townshipID) {
		TownshipID = townshipID;
	}

	public TownShip getTownship() {
		return Township;
	}

	public void setTownship(TownShip township) {
		Township = township;
	}

}
