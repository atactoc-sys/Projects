//package com.example.am.controller;
//
//
//
//import java.util.List;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import com.example.am.entity.Appointments;
//import com.example.am.serviceImpl.AppointmentsServiceImpl;
//
//@RestController
//@RequestMapping("/appointments")
//public class AppointmentsController 
//{
//
////	private AppointmentsServiceImol appointmentsServiceImol;
////
////
////	//Constructs a new instance of AppointmentsController with the specified AppointmentsServiceImol.
////	public AppointmentsController(AppointmentsServiceImol appointmentsServiceImol) 
////	{
////		this.appointmentsServiceImol = appointmentsServiceImol;
////	}
////
////
////	//Handles a GET request to retrieve all appointments.
////	@GetMapping
////	public ResponseEntity<List<Appointments>> getAllAppointments() 
////	{
////		List<Appointments> appointments = appointmentsServiceImol.getAllAppointments();
////		return ResponseEntity.ok(appointments);
////	}
////
////
////	//Handles a GET request to retrieve an appointment by its ID.
////	@GetMapping("/getById/{id}")
////	public ResponseEntity<Appointments> getAppointmentById(@PathVariable Long id) 
////	{
////		Appointments appointment = appointmentsServiceImol.getAppointmentById(id);
////		return ResponseEntity.ok(appointment);
////	}
////
////
////	//Handles a POST request to create a new appointment. 
////	@PostMapping("/createAppointment")
////	public ResponseEntity<Appointments> createAppointment(@RequestBody Appointments appointments) 
////	{
////		Appointments createdAppointment = appointmentsServiceImol.createAppointment(appointments);
////		return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointment);
////	}
////
////
////	//Handles a PUT request to update an existing appointment.
////	@PutMapping("/updateAppointment/{id}")
////	public ResponseEntity<Appointments> updateAppointment(@PathVariable Long id, @RequestBody Appointments updatedAppointment) 
////	{
////		Appointments appointment = appointmentsServiceImol.updateAppointment(id, updatedAppointment);
////		return ResponseEntity.ok(appointment);
////	}
////
////
////	//Handles a DELETE request to delete an appointment.
////	@DeleteMapping("/deleteAppointment/{id}")
////	public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) 
////	{
////		appointmentsServiceImol.deleteAppointment(id);
////		return ResponseEntity.noContent().build();
////	}
//	
//private AppointmentsServiceImpl appointmentsServiceImpl;
//	
//	public AppointmentsController(AppointmentsServiceImpl appointmentsServiceImpl)
//	{
//		this.appointmentsServiceImpl = appointmentsServiceImpl;
//	}
//    
//    @GetMapping
//    public List<Appointments> getAllAppointments() {
//        return appointmentsServiceImpl.getAllAppointments();
//    }
//
//    @GetMapping("/{id}")
//    public Appointments getAppointmentById(@PathVariable Long id) {
//        return appointmentsServiceImpl.getAppointmentById(id);
//    }
//
//    @PostMapping
//    public Appointments createAppointment(@RequestBody Appointments appointments) {
//        return appointmentsServiceImpl.createAppointment(appointments);
//    }
//
//    @PutMapping("/{id}")
//    public Appointments updateAppointment(@PathVariable Long id, @RequestBody Appointments updatedAppointment) {
//        return appointmentsServiceImpl.updateAppointment(id, updatedAppointment);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteAppointment(@PathVariable Long id) {
//        appointmentsServiceImpl.deleteAppointment(id);
//    }
//}
//
//
//

package com.example.am.controller;

import com.example.am.entity.Appointments;
import com.example.am.service.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {

    private final AppointmentsService appointmentsService;

    @Autowired
    public AppointmentsController(AppointmentsService appointmentsService) {
        this.appointmentsService = appointmentsService;
    }

    @GetMapping
    public ResponseEntity<List<Appointments>> getAllAppointments() {
        List<Appointments> appointments = appointmentsService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointments> getAppointmentById(@PathVariable Long id) {
        Appointments appointment = appointmentsService.getAppointmentById(id);
        return ResponseEntity.ok(appointment);
    }

    @PostMapping
    public ResponseEntity<Appointments> createAppointment(@RequestBody Appointments appointments) {
        Appointments createdAppointment = appointmentsService.createAppointment(appointments);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointments> updateAppointment(
            @PathVariable Long id,
            @RequestBody Appointments updatedAppointment
    ) {
        Appointments appointment = appointmentsService.updateAppointment(id, updatedAppointment);
        return ResponseEntity.ok(appointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentsService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}

