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

	public void postDoctor(String vorname, String nachname) {
		long length = doctorRepo.findAll()
				.stream()
				.filter(doctor -> doctor.getFirstName().equals(vorname) && doctor.getLastName().equals(nachname))
				.count();
		if (length != 0) {
			throw new DoctorManagementException("Doctor with the name already exists");
		}
		doctorRepo.save(new Doctor(vorname, nachname));
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
		return doctorRepo.findAll()
				.stream()
				.filter(doctor -> doctor.getFirstName().equals(vorname) && doctor.getLastName().equals(nachname))
				.findFirst()
				.orElseThrow(() -> {
					throw new DoctorManagementException("Doctor with " + vorname + "  " + nachname + "not found");
				});
	}

	public List<Doctor> getAll() {
		return doctorRepo.findAll();
	}

	public void delete(Doctor doctor) {
		doctorRepo.delete(doctor);
	}

	public void delete(String vorname, String nachname) {
		doctorRepo.delete(this.get(vorname, nachname));
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
