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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "DeliStaffAssignToRoute")
public class DeliStaffAssignToRoute extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;

	private long DeliStaffID;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeliStaffID", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private Staff DeliStaff;

	private long DeliProductID;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "DeliProductID", insertable = false, updatable = false)
	@Fetch(FetchMode.JOIN)
	private DeliProduct DeliProduct;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	@Column(name = "DueDate", nullable = true)
	private Date DueDate;

	@NotNull
	private int DeliFrequencyCount;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public long getDeliStaffID() {
		return DeliStaffID;
	}

	public void setDeliStaffID(long deliStaffID) {
		DeliStaffID = deliStaffID;
	}

	public Staff getDeliStaff() {
		return DeliStaff;
	}

	public void setDeliStaff(Staff deliStaff) {
		DeliStaff = deliStaff;
	}

	public long getDeliProductID() {
		return DeliProductID;
	}

	public void setDeliProductID(long deliProductID) {
		DeliProductID = deliProductID;
	}

	public DeliProduct getDeliProduct() {
		return DeliProduct;
	}

	public void setDeliProduct(DeliProduct deliProduct) {
		DeliProduct = deliProduct;
	}

	public Date getDueDate() {
		return DueDate;
	}

	public void setDueDate(Date dueDate) {
		DueDate = dueDate;
	}

	public int getDeliFrequencyCount() {
		return DeliFrequencyCount;
	}

	public void setDeliFrequencyCount(int deliFrequencyCount) {
		DeliFrequencyCount = deliFrequencyCount;
	}

}
