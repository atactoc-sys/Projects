package com.anudip.bms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "User_Details")
@NoArgsConstructor
@AllArgsConstructor
public class Customer 
{
	@Id
	@Column(name="account_number", length=10, nullable=false, unique=true)
	@NotNull(message = "It can't be left blank")
	@Size(max = 10)
	private int accountNo;
	
	@Column(name="account_holder", length=25, nullable=false)
	@NotNull(message = "It can't be left blank")
	@Size(max = 25)
	private String accountHolder;
	
	@Column(name="account_type", length=7, nullable=false)
	@NotNull(message = "It can't be left blank")
	@Size(max = 7)
	private String accountType;
	
	@Column(name="branch_name", length=15, nullable=false)
	@NotNull(message = "It can't be left blank")
	@Size(max = 15)
	private String branch;
	
	@Column(name="pin", length=4, nullable=false)
	@NotNull(message = "It can't be left blank")
	@Size(max = 4)
	private int pin;
	
	@Column(name="email", length=25, nullable=false, unique=true)
	@Email
	@NotNull(message = "It can't be left blank")
	@Size(max = 10)
	private String email;
	
	@Column(name="contact_no", length=10, nullable=false ,unique=true)
	private long contact ;
	
	@Column(name="account_balance", length=10, nullable=false)
	@NotNull(message = "It can't be left blank")
	@Size(max = 10, min = 3)
	private double balance ;
}
