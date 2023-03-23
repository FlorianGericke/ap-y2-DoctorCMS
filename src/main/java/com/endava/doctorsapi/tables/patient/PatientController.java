package com.endava.doctorsapi.tables.patient;

import com.endava.doctorsapi.dto.mappers.PatientMapper;
import com.endava.doctorsapi.dto.response.FacilityResponse;
import com.endava.doctorsapi.dto.response.PatientResponse;
import com.endava.doctorsapi.general.base.BaseController;
import com.endava.doctorsapi.general.exceptions.CmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/patient")
public class PatientController extends BaseController<Patient, PatientService, PatientResponse, PatientMapper> {

	@Autowired
	public PatientController(PatientService service, PatientMapper mapper) {
		super(service, mapper);
	}

	@PostMapping()
	public ResponseEntity<PatientResponse> onPost(@RequestBody(required = false) Optional<Patient> patient) {
		validate(patient);

		return new ResponseEntity<>(mapper.map(service.postPatient(patient.get())), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PatientResponse> onPut(@PathVariable(value = "id") Long id, @RequestBody() Optional<Patient> patient) {
		if (id == null) {
			throw new CmsException("Invalid param id is null");
		}

		validate(patient);
		return new ResponseEntity<>(mapper.map(service.putPatient(id, patient.get())), HttpStatus.OK);

	}

	@PatchMapping("/{facilityId}/address/{addressId}")
	public ResponseEntity<PatientResponse> onPatch(@PathVariable(value = "facilityId") long facilityId,
	                                               @PathVariable(value = "addressId") long addressId) {
		return new ResponseEntity<>(mapper.map(service.patchAddress(facilityId, addressId)), HttpStatus.OK);
	}

	private void validate(Optional<Patient> patient) {
		if (patient.isEmpty()) {
			throw new CmsException("Please provide a RequestBody withe attributs insuranceNumber, firstName, lastName and age");
		}

		if ((patient.get().getInsuranceNumber() == null || patient.get().getInsuranceNumber().length() < 2) ||
				(patient.get().getFirstName() == null || patient.get().getFirstName().length() < 2) ||
				(patient.get().getLastName() == null || patient.get().getFirstName().length() < 2) ||
				patient.get().getAge() < 0
		) {
			throw new CmsException("Please provide a First and Lastname in RequestBody (min 2 chars)");
		}
	}
}
