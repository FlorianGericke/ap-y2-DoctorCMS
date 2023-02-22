package com.endava.doctorsapi.tabels.doctor;

import com.endava.doctorsapi.tabels.general.EntityStates;
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

	public List<Doctor> getAll() {
		return doctorRepo.findAllByStateIsNot(EntityStates.DELETED.toString());
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
