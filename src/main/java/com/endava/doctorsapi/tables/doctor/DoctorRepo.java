package com.endava.doctorsapi.tables.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {
	List<Doctor> findAllByStateIsNot(String state);
}
