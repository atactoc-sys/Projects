package com.example.am.entity;

import java.util.List;

import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Table(name = "Doctor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Doctor 
{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", length = 15, nullable = false, unique = true)
	@NotBlank(message = "id should not be blank")
	@Size(min = 4, max = 15, message = "username size should be minimum 4 and max 15 letters.")
	private Long id;
	
	@Column(name = "name", length = 15, nullable = false, unique = true)
	@NotBlank(message = "name should not be blank")
	@Size(min = 4, max = 15, message = "username size should be minimum 4 and max 15 letters.")
	private String name;
	
	@Column(name = "speciality", length = 15, nullable = false)
	@NotBlank(message = "speciality should not be blank")
	@Size(min = 4, max = 15, message = "speciality size should be minimum 4 and max 15 letters.")
	private String speciality;
	
	
	@Column(name = "contact", length = 10, nullable = false, unique = true)
	@NotBlank(message = "contact should not be blank")
	@Size(min = 10, max = 10, message = "contact size should be 10.")
	private long contact;

	// Other doctor properties

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	private List<Appointments> appointments;



}
