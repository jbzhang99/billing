package com.ai.baas.bmc.srv.entity;

import java.math.BigDecimal;

import com.ai.baas.bmc.srv.persistence.entity.ResBook;

public class ResBookUpdater {
	private BigDecimal changeAmount; //变化量
	private BigDecimal balance; //剩余量
	private BigDecimal changeBefore; //变化前值
	private ResBook resBook;

	public BigDecimal getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(BigDecimal changeAmount) {
		this.changeAmount = changeAmount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public ResBook getResBook() {
		return resBook;
	}

	public void setResBook(ResBook resBook) {
		this.resBook = resBook;
	}

	public BigDecimal getChangeBefore() {
		return changeBefore;
	}

	public void setChangeBefore(BigDecimal changeBefore) {
		this.changeBefore = changeBefore;
	}
	
}
