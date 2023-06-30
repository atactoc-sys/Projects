//package com.example.am.controller;
//
//import java.util.List;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.am.entity.Doctor;
//import com.example.am.repository.DoctorRepository;
//
//@RestController
//@RequestMapping("/doctors")
//public class DoctorController 
//{
//	private DoctorRepository doctorRepository;
//
//	//Constructs a new instance of DoctorController with the specified DoctorRepository.
//    public DoctorController(DoctorRepository doctorRepository) {
//        this.doctorRepository = doctorRepository;
//    }
//    
//   
//
//    //Handles a POST request to create a new doctor.
//    @PostMapping
//    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
//    	// Save the doctor to the repository
//        Doctor savedDoctor = doctorRepository.save(doctor);
//        
//     // Return a ResponseEntity with the created doctor and HTTP status code 200 (OK)
//        return ResponseEntity.ok(savedDoctor);
//    }
//}
package com.example.am.controller;

import com.example.am.entity.Doctor;
import com.example.am.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctor);
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        Doctor createdDoctor = doctorService.createDoctor(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDoctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor updatedDoctor) {
        Doctor doctor = doctorService.updateDoctor(id, updatedDoctor);
        return ResponseEntity.ok(doctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}

