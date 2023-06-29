package com.example.am.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.am.entity.Appointments;
import com.example.am.repository.AppointmentRepository;
import com.example.am.serviceImpl.AppointmentsServiceImol;

import ch.qos.logback.core.spi.AppenderAttachableImpl;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController 
{
//	private AppointmentRepository appointmentRepository;
//
//    public AppointmentsController(AppointmentRepository appointmentRepository)
//    {
//    	this.appointmentRepository = appointmentRepository;
//    }
//	private final AppointmentsServiceimpl appointmentService;
//
//    public AppointmentController(AppointmentService appointmentService) {
//        this.appointmentService = appointmentService;
//    }
	private AppointmentsServiceImol appointmentsServiceImol;
	
	public AppointmentsController(AppointmentsServiceImol appointmentsServiceImol)
	{
		this.appointmentsServiceImol = appointmentsServiceImol;
	}
    
    @GetMapping
    public List<Appointments> getAllAppointments() {
        return appointmentsServiceImol.getAllAppointments();
    }

    @GetMapping("/{id}")
    public Appointments getAppointmentById(@PathVariable Long id) {
        return appointmentsServiceImol.getAppointmentById(id);
    }

    @PostMapping
    public Appointments createAppointment(@RequestBody Appointments appointments) {
        return appointmentsServiceImol.createAppointment(appointments);
    }

    @PutMapping("/{id}")
    public Appointments updateAppointment(@PathVariable Long id, @RequestBody Appointments updatedAppointment) {
        return appointmentsServiceImol.updateAppointment(id, updatedAppointment);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentsServiceImol.deleteAppointment(id);
    }
    
}
