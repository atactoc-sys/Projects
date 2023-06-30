package com.example.am.service;

import java.util.List;

import com.example.am.entity.Doctor;

public interface DoctorService 
{
	//Retrieves a list of all doctors.
	public List<Doctor> getAllDoctors();
	
	//Retrieves a doctor by their ID.
	public Doctor getDoctorById(Long id);
	
	//Creates a new doctor.
	public Doctor createDoctor(Doctor doctor);
	
	//Updates an existing doctor.
	public Doctor updateDoctor(Long id, Doctor updatedDoctor);
	
	//Deletes a doctor by their ID.
	public void deleteDoctor(Long id);
}
