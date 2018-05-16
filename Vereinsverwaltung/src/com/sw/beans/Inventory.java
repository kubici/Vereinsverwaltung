package com.sw.beans;

import java.util.Date;

public class Inventory 
{

	private int inventoryId;
	private String Category;
	private String description;
	private String purchaseValue;
	private Date lastAudit;
	private Date nextAudit;
	private Date acquisitionDate;
	private String lastauditby;

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPurchaseValue() {
		return purchaseValue;
	}

	public void setPurchaseValue(String purchaseValue) {
		this.purchaseValue = purchaseValue;
	}

	public Date getLastAudit() {
		return lastAudit;
	}

	public void setLastAudit(Date lastAudit) {
		this.lastAudit = lastAudit;
	}

	public Date getNextAudit() {
		return nextAudit;
	}

	public void setNextAudit(Date nextAudit) {
		this.nextAudit = nextAudit;
	}

	public Date getAcquisitionDate() {
		return acquisitionDate;
	}

	public void setAcquisitionDate(Date acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}

	public String getLastauditby() {
		return lastauditby;
	}

	public void setLastauditby(String lastauditby) {
		this.lastauditby = lastauditby;
	}
	
}
