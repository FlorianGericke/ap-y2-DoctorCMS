package com.endava.doctorsapi.tables.patient;

import com.endava.doctorsapi.general.base.BaseService;
import com.endava.doctorsapi.general.exceptions.ServiceException;
import com.endava.doctorsapi.tables.address.Address;
import com.endava.doctorsapi.tables.address.AddressRepo;
import com.endava.doctorsapi.tables.facility.Facility;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PatientService extends BaseService<Patient, PatientRepo> {

	private final AddressRepo addressRepo;

	public PatientService(PatientRepo patientRepo, AddressRepo addressRepo) {
		super(patientRepo);
		this.addressRepo = addressRepo;
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

	@Transactional
	public Patient patchAddress(long facilityId, long addressId) {
		Patient patientRef = repo.findById(facilityId)
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, this, "Facility not found"));
		Address addressRef = addressRepo.findById(addressId)
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, this, "Address not found"));

		patientRef.setAddress(addressRef);
		return repo.save(patientRef);
	}
}
