package com.prodev.dms.domain;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class BaseEntity {
	@Column(name = "active", nullable = false)
    private boolean active;
	
	@Column(name = "createdUserID", nullable = false)
    private long createdUserID;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdDate", nullable = false,updatable = false)  
	private Date createdDate;
	
	@Column(name = "updatedUserID", nullable = true)
    private long updatedUserID;
    

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatedDate", nullable = true)
    private Date updatedDate;
    
    public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public long getCreatedUserID() {
		return createdUserID;
	}

	public void setCreatedUserID(long createdUserID) {
		this.createdUserID = createdUserID;
	}

	public long getUpdatedUserID() {
		return updatedUserID;
	}

	public void setUpdatedUserID(long updatedUserID) {
		this.updatedUserID = updatedUserID;
	}

	

    @PrePersist
    protected void onCreatedDate() {
    	updatedDate = createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdatedDate() {
    	updatedDate = new Date();
    }

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date created) {
		this.createdDate = created;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((updatedDate == null) ? 0 : updatedDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (updatedDate == null) {
			if (other.updatedDate != null)
				return false;
		} else if (!updatedDate.equals(other.updatedDate))
			return false;
		return true;
	}
    
	
    
}