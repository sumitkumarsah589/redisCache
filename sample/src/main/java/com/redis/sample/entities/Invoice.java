package com.redis.sample.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class Invoice implements Serializable {

	private static final long serialVersionUID = -4439114469417994311L;

	@Id
	@GeneratedValue
	private Integer invId;
	private String invName;
	private Double invAmount;
	public String getInvName() {
		return invName;
	}
	public void setInvName(String invName) {
		this.invName = invName;
	}
	public Double getInvAmount() {
		return invAmount;
	}
	public void setInvAmount(Double invAmount) {
		this.invAmount = invAmount;
	}
}


// Running in standalone mode
//Port: 6379
//PID: 12272