package com.example.am.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.am.entity.Appointments;
import com.example.am.exception.GlobalException;
import com.example.am.repository.AppointmentRepository;
import com.example.am.service.AppointmentsService;

@Service
public class AppointmentsServiceImol implements AppointmentsService
{
	private AppointmentRepository appointmentRepository;
	
	public void AppointmentsService(AppointmentRepository appointmentRepository)
	{
		this.appointmentRepository = appointmentRepository;
	}

	@Override
	public List<Appointments> getAllAppointments() 
	{
		return appointmentRepository.findAll();
	}

	@Override
	public Appointments getAppointmentById(Long id) 
	{
		return appointmentRepository.findById(id)
                .orElseThrow(() -> new GlobalException("Appointment not found"));
	}

	@Override
	public Appointments createAppointment(Appointments appointments) 
	{
		return appointmentRepository.save(appointments);
	}

	@Override
	public Appointments updateAppointment(Long id, Appointments updatedAppointment) {
		Appointments appointments = getAppointmentById(id);
        appointments.setDateTime(updatedAppointment.getDateTime());
        return appointmentRepository.save(appointments);
	}

	@Override
	public void deleteAppointment(Long id) {
		appointmentRepository.deleteById(id);
		
	}

}
