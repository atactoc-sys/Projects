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

	public DoctorServiceImpl(DoctorRepository doctorRepository)
	{
		this.doctorRepository = doctorRepository;
	}

	@Override
	public List<Doctor> getAllDoctors() 
	{
		return doctorRepository.findAll();
	}

	@Override
	public Doctor getDoctorById(Long id) 
	{
		return doctorRepository.findById(id)
                .orElseThrow(() -> new GlobalException("Doctor not found"));
	}

	@Override
	public Doctor createDoctor(Doctor doctor) 
	{
		return doctorRepository.save(doctor);
	}

	@Override
	public Doctor updateDoctor(Long id, Doctor updatedDoctor) {
		Doctor doctor = getDoctorById(id);
        doctor.setName(updatedDoctor.getName());
        return doctorRepository.save(doctor);
	}

	@Override
	public void deleteDoctor(Long id) 
	{
		doctorRepository.deleteById(id);
		
	}

}
