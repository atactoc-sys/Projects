package com.example.am.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.am.entity.Appointments;
import com.example.am.exception.GlobalException;
import com.example.am.repository.AppointmentRepository;
import com.example.am.service.AppointmentsService;

@Service
public class AppointmentsServiceImpl implements AppointmentsService
{
	private AppointmentRepository appointmentRepository;
	
	//Constructs an instance of AppointmentsServiceImol with the specified AppointmentRepository.
	public void AppointmentsService(AppointmentRepository appointmentRepository)
	{
		this.appointmentRepository = appointmentRepository;
	}

	// Retrieves a list of all appointments from the repository
	@Override
	public List<Appointments> getAllAppointments() 
	{
		return appointmentRepository.findAll();
	}

	// Retrieves an appointment by its ID from the repository
	@Override
	public Appointments getAppointmentById(Long id) 
	{
		// Throws a GlobalException if the appointment is not found
		return appointmentRepository.findById(id)
                .orElseThrow(() -> new GlobalException("Appointment not found"));
	}

	// Creates a new appointment by saving it to the repository
	@Override
	public Appointments createAppointment(Appointments appointments) 
	{
		return appointmentRepository.save(appointments);
	}

	// Updates an existing appointment by retrieving it from the repository,
	@Override
	public Appointments updateAppointment(Long id, Appointments updatedAppointment) {
		// updating its date and time with the provided values, and saving it back to the repository
		Appointments appointments = getAppointmentById(id);
        appointments.setDateTime(updatedAppointment.getDateTime());
        return appointmentRepository.save(appointments);
	}

	// Deletes an appointment by its ID from the repository
	@Override
	public void deleteAppointment(Long id) {
		appointmentRepository.deleteById(id);
		
	}

}
