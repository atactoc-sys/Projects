package com.example.am.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.am.entity.Doctor;

//The DoctorRepository interface is responsible 
//for accessing and managing doctor data in the database.

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>
{
	
}
