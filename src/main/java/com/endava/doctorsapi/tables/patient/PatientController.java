package com.endava.doctorsapi.tables.patient;

import com.endava.doctorsapi.dto.mappers.PatientMapper;
import com.endava.doctorsapi.dto.request.PatientRequest;
import com.endava.doctorsapi.dto.response.PatientResponse;
import com.endava.doctorsapi.general.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/patient")
public class PatientController extends BaseController<Patient, PatientService, PatientResponse, PatientMapper> {

	@Autowired
	public PatientController(PatientService service, PatientMapper mapper) {
		super(service, mapper);
	}

	@PostMapping()
	public ResponseEntity<PatientResponse> onPost(@RequestBody(required = false) PatientRequest patientRequest) {
		return new ResponseEntity<>(mapper.map(service.postPatient(patientRequest)), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PatientResponse> onPut(@PathVariable(value = "id") Long id,
	                                             @RequestBody() PatientRequest patientRequest) {
		return new ResponseEntity<>(mapper.map(service.putPatient(id, patientRequest)), HttpStatus.OK);

	}

	@PatchMapping("/{facilityId}/address/{addressId}")
	public ResponseEntity<PatientResponse> onPatch(@PathVariable(value = "facilityId") long facilityId,
	                                               @PathVariable(value = "addressId") long addressId) {
		return new ResponseEntity<>(mapper.map(service.patchAddress(facilityId, addressId)), HttpStatus.OK);
	}
}
