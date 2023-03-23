package com.endava.doctorsapi.tables.patient;

import com.endava.doctorsapi.general.base.BaseService;
import com.endava.doctorsapi.general.exceptions.ServiceException;
import org.springframework.stereotype.Service;

@Service
public class PatientService extends BaseService<Patient, PatientRepo> {
	public PatientService(PatientRepo patientRepo) {
		super(patientRepo);
	}

	public Patient postPatient(Patient patient) {
		return this.postPatient(patient.getInsuranceNumber(), patient.getFirstName(), patient.getLastName(), patient.getAge());
	}

	public Patient postPatient(String insuranceNumber, String firstName, String lastName, int age) {
		return repo.save(new Patient(insuranceNumber, firstName, lastName, age));
	}

	public Patient putPatient(Long id, Patient patient) {
		return this.putPatient(id, patient.getInsuranceNumber(), patient.getFirstName(), patient.getLastName(), patient.getAge());
	}

	public Patient putPatient(Long id, String insuranceNumber, String firstName, String lastName, int age) {
		Patient patient = repo.findById(id)
				.orElseThrow(() -> {
					throw new ServiceException(this, "id not found");
				});
		patient.setInsuranceNumber(insuranceNumber);
		patient.setFirstName(firstName);
		patient.setLastName(lastName);
		patient.setAge(age);
		return repo.save(patient);
	}
}
