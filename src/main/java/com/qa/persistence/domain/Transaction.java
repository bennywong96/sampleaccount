package com.qa.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Transaction {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@Size (max = 50, message = "Transaction Name can only have 50 characters")
	private String transactionName;

	public Transaction() {

	}

	public Transaction(String transactionName) {
		this.transactionName = transactionName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTransactionName() {
		return transactionName;
	}

	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}

	
}
