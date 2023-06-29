package com.example.am.service;

import java.util.List;

import com.example.am.entity.Appointments;

public interface AppointmentsService 
{
	public List<Appointments> getAllAppointments();
	
	public Appointments getAppointmentById(Long id);
	
	public Appointments createAppointment(Appointments appointments);
	
	public Appointments updateAppointment(Long id, Appointments updatedAppointment);
	
	public void deleteAppointment(Long id);
}
