package com.example.am.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.am.entity.Appointments;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointments, Long>
{

}
