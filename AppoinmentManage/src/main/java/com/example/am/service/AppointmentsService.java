package com.example.am.service;

import java.util.List;

import com.example.am.entity.Appointments;

public interface AppointmentsService 
{
	//Retrieves a list of all appointments.
	public List<Appointments> getAllAppointments();
	
	//Retrieves an appointment by its ID.
	public Appointments getAppointmentById(Long id);
	
	//Creates a new appointment.
	public Appointments createAppointment(Appointments appointments);
	
	//Updates an existing appointment.
	public Appointments updateAppointment(Long id, Appointments updatedAppointment);
	
	//Deletes an appointment by its ID.
	public void deleteAppointment(Long id);
}
