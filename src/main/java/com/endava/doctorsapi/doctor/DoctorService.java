package com.endava.doctorsapi.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DoctorService {
	private final DoctorRepo doctorRepo;

	@Autowired
	public DoctorService(DoctorRepo doctorRepo) {
		this.doctorRepo = doctorRepo;
	}

	public void postDoctor(Doctor doctor) {
		this.postDoctor(doctor.getFirstName(), doctor.getLastName());
	}

	public void postDoctor(String firstName, String lastName) {
		long length = doctorRepo.findAll()
				.stream()
				.filter(doctor -> doctor.getFirstName().equals(firstName) && doctor.getLastName().equals(lastName))
				.count();
		if (length != 0) {
			throw new DoctorManagementException("Doctor with the name " + firstName + " " + lastName + " already exists");
		}
		doctorRepo.save(new Doctor(firstName, lastName));
	}

	public void put(Long id, Doctor doc) {
		this.put(id, doc.getFirstName(), doc.getLastName());
	}

	public void put(Long id, String firstName, String lastName) {
		Doctor doc = doctorRepo.findById(id)
				.orElseThrow(() -> {
					throw new DoctorManagementException("id not found");
				});
		doc.setFirstName(firstName);
		doc.setLastName(lastName);
		doctorRepo.save(doc);
	}

	public Doctor get(Long id) {
		return doctorRepo.findById(id)
				.orElseThrow(() -> {
					throw new DoctorManagementException("id not found");
				});
	}

	public Doctor get(String firstName, String lastName) {
		return doctorRepo.findAll()
				.stream()
				.filter(doctor -> doctor.getFirstName().equals(firstName) && doctor.getLastName().equals(lastName))
				.findFirst()
				.orElseThrow(() -> {
					throw new DoctorManagementException("Doctor with " + firstName + "  " + lastName + "not found");
				});
	}

	public List<Doctor> getAll() {
		return doctorRepo.findAll();
	}

	public void delete(String firstName, String lastName) {
		doctorRepo.delete(this.get(firstName, lastName));
	}

	public void delete(Long id) {
		doctorRepo.deleteById(id);
	}

	public void deleteAllById(Iterable<? extends Long> longs) {
		doctorRepo.deleteAllById(longs);
	}

	public void deleteAll() {
		doctorRepo.deleteAll();
	}

}
