package com.endava.doctorsapi.tables.doctor;

import com.endava.doctorsapi.dto.mappers.DoctorMapper;
import com.endava.doctorsapi.dto.response.DoctorResponse;
import com.endava.doctorsapi.general.base.BaseController;
import com.endava.doctorsapi.general.exceptions.CmsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/doctor")
public class DoctorController extends BaseController<Doctor, DoctorService, DoctorResponse, DoctorMapper> {

	@Autowired
	public DoctorController(DoctorService doctorService, DoctorMapper mapper) {
		super(doctorService, mapper);
	}

	@PostMapping()
	public ResponseEntity<DoctorResponse> onPost(@RequestBody(required = false) Optional<Doctor> doctor) {
		validate(doctor);
		return new ResponseEntity<>(mapper.map(service.postDoctor(doctor.get())), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<DoctorResponse> onPut(@PathVariable(value = "id") Long id, @RequestBody() Optional<Doctor> doctor) {
		if (id == null) {
			throw new CmsException("Invalid param id is null");
		}

		validate(doctor);
		return new ResponseEntity<>(mapper.map(service.putDoctor(id, doctor.get())), HttpStatus.OK);

	}

	@PatchMapping("/{docId}/facility/{facId}/department/{depId}")
	public ResponseEntity<DoctorResponse> onPatch(@PathVariable("docId") Optional<Long> docId,
	                                              @PathVariable("facId") Optional<Long> facId,
	                                              @PathVariable("depId") Optional<Long> depId) {

		return new ResponseEntity<>(mapper.map(service.patchDoctorFacilityDepartment(docId.get(), facId.get(), depId.get())), HttpStatus.OK);
	}

	private void validate(Optional<Doctor> doctor) {
		if (doctor.isEmpty()) {
			throw new CmsException("Please provide a RequestBody withe attributs firstName, lastName");
		}

		if ((doctor.get().getFirstName() == null || doctor.get().getFirstName().length() < 2) ||
				(doctor.get().getLastName() == null || doctor.get().getFirstName().length() < 2)) {
			throw new CmsException("Please provide a First and Lastname in RequestBody (min 2 chars)");
		}
	}
}
