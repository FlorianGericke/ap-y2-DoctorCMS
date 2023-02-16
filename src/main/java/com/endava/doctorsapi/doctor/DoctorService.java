package com.endava.doctorsapi.doctor;

import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class DoctorService {
	private final DoctorRepo doctorRepo;

	@Autowired
	public DoctorService(DoctorRepo doctorRepo) {
		this.doctorRepo = doctorRepo;
	}

	public void postDoctor(String vorname, String nachname) {
		long length = doctorRepo.findAll()
				.stream()
				.filter(doctor -> doctor.getFirstName().equals(vorname) && doctor.getLastName().equals(nachname))
				.count();
		if (length == 0) {
			doctorRepo.save(new Doctor(vorname, nachname));
		}
	}

	public void put(Long id, String vorname, String nachname) {
		Doctor doc = doctorRepo.findAll().stream().filter(doctor -> Objects.equals(doctor.getId(), id))
				.findFirst().orElse(null);
		doc.setFirstName(vorname);
		doc.setLastName(nachname);
		doctorRepo.save(doc);
	}

}
