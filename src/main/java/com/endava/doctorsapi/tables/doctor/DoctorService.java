package com.endava.doctorsapi.tables.doctor;

import com.endava.doctorsapi.general.base.BaseService;
import com.endava.doctorsapi.general.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DoctorService extends BaseService<Doctor, DoctorRepo> {

	@Autowired
	public DoctorService(DoctorRepo doctorRepo) {
		super(doctorRepo);
	}

	public Doctor postDoctor(Doctor doctor) {
		return this.postDoctor(doctor.getFirstName(), doctor.getLastName());
	}

	public Doctor postDoctor(String firstName, String lastName) {
		return repo.save(new Doctor(firstName, lastName));
	}

	public Doctor putDoctor(Long id, Doctor doc) {
		return this.putDoctor(id, doc.getFirstName(), doc.getLastName());
	}

	public Doctor putDoctor(Long id, String firstName, String lastName) {
		Doctor doc = repo.findById(id)
				.orElseThrow(() -> {
					throw new ServiceException(this, "id not found");
				});
		doc.setFirstName(firstName);
		doc.setLastName(lastName);
		return repo.save(doc);
	}
}
