package com.example.am.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Table(name = "Appointments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointments 
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
//	@Column(name = "dateTime", nullable = false, unique = true)
//	@NotBlank(message = "dateTime should not be blank")
//    private LocalDateTime dateTime;
	@Column(name = "dateTime", nullable = false, unique = true)
	@NotBlank(message = "date should not be blank")
	private LocalDateTime dateTime;
	
	

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;



	
}
