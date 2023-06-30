package com.example.am.serviceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.am.entity.Appointments;
import com.example.am.exception.GlobalException;
import com.example.am.repository.AppointmentRepository;
import com.example.am.serviceImpl.AppointmentsServiceImpl;

public class AppointmentTest 
{
	@Mock
	private AppointmentRepository appointmentRepository;

	@InjectMocks
	private AppointmentsServiceImpl appointmentsService;

	@SuppressWarnings("deprecation")
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllAppointments() {
		List<Appointments> appointmentsList = new ArrayList<>();
		appointmentsList.add(new Appointments());
		appointmentsList.add(new Appointments());

		when(appointmentRepository.findAll()).thenReturn(appointmentsList);

		List<Appointments> result = appointmentsService.getAllAppointments();

		assertEquals(2, result.size());
	}

	@Test
	public void testGetAppointmentById() {
		Appointments appointment = new Appointments();
		appointment.setId(1L);

		when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));

		Appointments result = appointmentsService.getAppointmentById(1L);

		assertNotNull(result);
		assertEquals(1L, result.getId().longValue());
	}

	@Test(expected = GlobalException.class)
	public void testGetAppointmentByIdNotFound() {
		when(appointmentRepository.findById(anyLong())).thenReturn(Optional.empty());

		appointmentsService.getAppointmentById(1L);
	}

	@Test
	public void testCreateAppointment() {
		Appointments appointment = new Appointments();

		when(appointmentRepository.save(any(Appointments.class))).thenReturn(appointment);

		Appointments result = appointmentsService.createAppointment(appointment);

		assertNotNull(result);
	}

//	@Test
//	public void testUpdateAppointment() {
//		Appointments existingAppointment = new Appointments();
//		existingAppointment.setId(1L);
//		existingAppointment.setDateTime("2023-06-30 10:00 AM");
//
//		Appointments updatedAppointment = new Appointments();
//		updatedAppointment.setDateTime("2023-07-01 11:00 AM");
//
//		when(appointmentRepository.findById(1L)).thenReturn(Optional.of(existingAppointment));
//		when(appointmentRepository.save(any(Appointments.class))).thenReturn(updatedAppointment);
//
//		Appointments result = appointmentsService.updateAppointment(1L, updatedAppointment);
//
//		assertNotNull(result);
//		assertEquals("2023-07-01 11:00 AM", result.getDateTime());
//	}

//	@Test(expected = GlobalException.class)
//	public void testUpdateAppointmentNotFound() {
//		Appointments updatedAppointment = new Appointments();
//		updatedAppointment.setDateTime("2023-07-01 11:00 AM");
//
//		when(appointmentRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//		appointmentsService.updateAppointment(1L, updatedAppointment);
//	}

	@Test
	public void testDeleteAppointment() {
		doNothing().when(appointmentRepository).deleteById(1L);

		appointmentsService.deleteAppointment(1L);
	}
}
