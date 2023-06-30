package com.example.am.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.am.entity.Doctor;
import com.example.am.exception.GlobalException;
import com.example.am.repository.DoctorRepository;
import com.example.am.serviceImpl.DoctorServiceImpl;



public class DoctorTest 
{




	@Mock
	private DoctorRepository doctorRepository;

	@InjectMocks
	private DoctorServiceImpl doctorService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetAllDoctors() {
		List<Doctor> doctorList = new ArrayList<>();
		doctorList.add(new Doctor());
		doctorList.add(new Doctor());

		when(doctorRepository.findAll()).thenReturn(doctorList);

		List<Doctor> result = doctorService.getAllDoctors();

		assertEquals(2, result.size());
	}

	@Test
	void testGetDoctorById() {
		Doctor doctor = new Doctor();
		doctor.setId(1L);

		when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));

		Doctor result = doctorService.getDoctorById(1L);

		assertNotNull(result);
		assertEquals(1L, result.getId());
	}

	@Test
	void testGetDoctorByIdNotFound() {
		when(doctorRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(GlobalException.class, () -> doctorService.getDoctorById(1L));
	}

	@Test
	void testCreateDoctor() {
		Doctor doctor = new Doctor();

		when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

		Doctor result = doctorService.createDoctor(doctor);

		assertNotNull(result);
	}

	@Test
	void testUpdateDoctor() {
		Doctor existingDoctor = new Doctor();
		existingDoctor.setId(1L);
		existingDoctor.setName("John Doe");

		Doctor updatedDoctor = new Doctor();
		updatedDoctor.setName("Jane Smith");

		when(doctorRepository.findById(1L)).thenReturn(Optional.of(existingDoctor));
		when(doctorRepository.save(any(Doctor.class))).thenReturn(updatedDoctor);

		Doctor result = doctorService.updateDoctor(1L, updatedDoctor);

		assertNotNull(result);
		assertEquals("Jane Smith", result.getName());
	}

	@Test
	void testUpdateDoctorNotFound() {
		Doctor updatedDoctor = new Doctor();
		updatedDoctor.setName("Jane Smith");

		when(doctorRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(GlobalException.class, () -> doctorService.updateDoctor(1L, updatedDoctor));
	}

	@Test
	void testDeleteDoctor() {
		doNothing().when(doctorRepository).deleteById(1L);

		doctorService.deleteDoctor(1L);
	}
}




