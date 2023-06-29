package com.example.am.service;

import java.util.List;

import com.example.am.entity.Doctor;

public interface DoctorService 
{
	public List<Doctor> getAllDoctors();
	
	public Doctor getDoctorById(Long id);
	
	public Doctor createDoctor(Doctor doctor);
	
	public Doctor updateDoctor(Long id, Doctor updatedDoctor);
	
	public void deleteDoctor(Long id);
}
