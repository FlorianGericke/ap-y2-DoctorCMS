package com.endava.doctorsapi.doctor;

import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.print.Doc;
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
		throw new DoctorManagementException("Doctor with the name already exists");
	}

	public void put(Long id, String vorname, String nachname) {
		Doctor doc = doctorRepo.findById(id)
				.orElseThrow(() -> {
					throw new DoctorManagementException("id not found");
				});
		doc.setFirstName(vorname);
		doc.setLastName(nachname);
		doctorRepo.save(doc);
	}

	public Doctor get(Long id) {
		return doctorRepo.findById(id)
				.orElseThrow(() -> {
					throw new DoctorManagementException("id not found");
				});
	}

	public Doctor get(String vorname, String nachname) {
		return doctorRepo.findById(id)
				.orElseThrow(() -> {
					throw new DoctorManagementException("id not found");
				});
	}

}
