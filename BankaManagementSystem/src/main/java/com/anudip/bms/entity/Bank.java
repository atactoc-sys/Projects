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
@Table(name = "Bank_Admin_Details")
@NoArgsConstructor
@AllArgsConstructor
public class Bank 
{
	@Id
	@Column(name="Admin_username", length=15, nullable=false, unique=true)
	@NotNull(message = "It can't be left blank")
	@Size(max = 15)
	private String username;
	
	@Column(name="First_name", length=10, nullable=false)
	@NotNull(message = "It can't be left blank")
	@Size(max = 10)
	private String fname;
	
	@Column(name="Last_name", length=10, nullable=false)
	@NotNull(message = "It can't be left blank")
	@Size(max = 10)
	private String lname;
	
	@Column(name="password", length=15, nullable=false)
	@NotNull(message = "It can't be left blank")
	@Size(max = 15)
	private String password;
}
