package com.qa.persistence.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Account {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	@Pattern (regexp = "^[a-zA-Z]{3,20}$", message = "Error, First Name has to be more than 2 and less than 20 english characters")
	private String firstName;
	@Pattern (regexp = "^[a-zA-Z]{3,20}$", message = "Error, Last Name has to be more than 2 and less than 20 english characters")
	private String lastName;
	@Size (min=6, max = 6, message="Error, Account Number has to be 6 digits")
	private String accountNumber;
	
	//regex pattern for 11 digit number starting with 0
	//	^[0][1-9]\d{9}$
	
	@JoinColumn(name = "account_id")
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Transaction> transaction;

	public Account() {

	}

	public Account(String firstName, String lastName, String accountNumber, List<Transaction> transaction) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountNumber = accountNumber;
		this.transaction = transaction;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getlastName() {
		return lastName;
	}

	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Transaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}

}
