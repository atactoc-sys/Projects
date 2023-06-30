package com.example.am.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.am.entity.Doctor;
import com.example.am.exception.GlobalException;
import com.example.am.repository.DoctorRepository;
import com.example.am.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService
{
	private DoctorRepository doctorRepository;

	//Constructs an instance of DoctorServiceImpl with the specified DoctorRepository.
	public DoctorServiceImpl(DoctorRepository doctorRepository)
	{
		this.doctorRepository = doctorRepository;
	}

	// Retrieves a list of all doctors from the repository
	@Override
	public List<Doctor> getAllDoctors() 
	{
		return doctorRepository.findAll();
	}

	// Retrieves a doctor by their ID from the repository
	@Override
	public Doctor getDoctorById(Long id) 
	{
		// Throws a GlobalException if the doctor is not found
		return doctorRepository.findById(id)
				.orElseThrow(() -> new GlobalException("Doctor not found"));
	}

	// Creates a new doctor by saving it to the repository
	@Override
	public Doctor createDoctor(Doctor doctor) 
	{
		return doctorRepository.save(doctor);
	}

	// Updates an existing doctor by retrieving it from the repository,
	@Override
	public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
		// updating its name with the provided value, and saving it back to the repository
		Doctor doctor = getDoctorById(id);
		doctor.setName(updatedDoctor.getName());
		return doctorRepository.save(doctor);
	}

	// Deletes a doctor by their ID from the repository
	@Override
	public void deleteDoctor(Long id) 
	{
		doctorRepository.deleteById(id);

	}

}
