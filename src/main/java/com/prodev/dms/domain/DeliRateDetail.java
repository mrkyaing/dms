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
@Table(name="DeliRateDetail")
public class DeliRateDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 48677793427431881L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private long deliRateMasterID;	  
	//many  detail can have one master
	@ManyToOne(fetch =FetchType.EAGER,optional=false)	 
	@JoinColumn(name="DeliRateMaster_deliRateMasterID")
	private DeliRateMaster DeliRateMaster;	
	
	private long townshipId;
	//many township can have one detail
	@ManyToOne(fetch =FetchType.EAGER,optional=false)	 
	@JoinColumn(name="Township_townshipId")
	private TownShip Township;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getDeliRateMasterID() {
		return deliRateMasterID;
	}
	public void setDeliRateMasterID(long deliRateMasterID) {
		this.deliRateMasterID = deliRateMasterID;
	}
	public DeliRateMaster getDeliRateMaster() {
		return DeliRateMaster;
	}
	public void setDeliRateMaster(DeliRateMaster deliRateMaster) {
		DeliRateMaster = deliRateMaster;
	}
	public long getTownshIp() {
		return townshipId;
	}
	
	public void setTownship(TownShip township) {
		Township = township;
	}
	public long getTownshipId() {
		return townshipId;
	}
	public void setTownshipId(long townshipId) {
		this.townshipId = townshipId;
	}	
	
	
}
