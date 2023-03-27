package com.endava.doctorsapi.tables.doctor;

import com.endava.doctorsapi.dto.mappers.DoctorMapper;
import com.endava.doctorsapi.dto.request.DoctorRequest;
import com.endava.doctorsapi.dto.response.DoctorResponse;
import com.endava.doctorsapi.general.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/doctor")
public class DoctorController extends BaseController<Doctor, DoctorService, DoctorResponse, DoctorMapper> {

	@Autowired
	public DoctorController(DoctorService doctorService, DoctorMapper mapper) {
		super(doctorService, mapper);
	}

	@PostMapping()
	public ResponseEntity<DoctorResponse> onPost(@RequestBody(required = false) DoctorRequest doctorRequest) {
		return new ResponseEntity<>(mapper.map(service.postDoctor(doctorRequest)), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<DoctorResponse> onPut(@PathVariable(value = "id") Long id,
	                                            @RequestBody() DoctorRequest doctorRequest) {
		return new ResponseEntity<>(mapper.map(service.putDoctor(id, doctorRequest)), HttpStatus.OK);

	}

	@PatchMapping("/{docId}/facility/{facId}/department/{depId}")
	public ResponseEntity<DoctorResponse> onPatch(@PathVariable("docId") long docId,
	                                              @PathVariable("facId") long facId,
	                                              @PathVariable("depId") long depId) {

		return new ResponseEntity<>(mapper.map(service.patchDoctorFacilityDepartment(docId, facId, depId)), HttpStatus.OK);
	}
}
