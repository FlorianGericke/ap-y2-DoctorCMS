package com.endava.doctorsapi.tables.doctor;

import com.endava.doctorsapi.tables.general.ServiceBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class DoctorService extends ServiceBase<Doctor,Long,DoctorRepo> {

	@Autowired
	public DoctorService(DoctorRepo doctorRepo) {
		super(doctorRepo);
	}

	public void postDoctor(Doctor doctor) {
		this.postDoctor(doctor.getFirstName(), doctor.getLastName());
	}

	public void postDoctor(String firstName, String lastName) {
		repo.save(new Doctor(firstName, lastName));
	}

	public void put(Long id, Doctor doc) {
		this.put(id, doc.getFirstName(), doc.getLastName());
	}

	public void put(Long id, String firstName, String lastName) {
		Doctor doc = repo.findById(id)
				.orElseThrow(() -> {
					throw new DoctorManagementException("id not found");
				});
		doc.setFirstName(firstName);
		doc.setLastName(lastName);
		repo.save(doc);
	}
}
