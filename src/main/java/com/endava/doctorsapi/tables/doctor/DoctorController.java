package com.endava.doctorsapi.tables.doctor;

import com.endava.doctorsapi.dto.mappers.DtoDoctorMapper;
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
public class DoctorController extends BaseController<Doctor,DoctorService, DoctorResponse, DtoDoctorMapper> {

	@Autowired
	public DoctorController(DoctorService doctorService, DtoDoctorMapper mapper) {
		super(doctorService, mapper);
	}

	@PostMapping()
	public ResponseEntity<DoctorResponse> onPost(@RequestBody(required = false) Optional<Doctor> doctor) {
		validate(doctor);
		return new ResponseEntity<>(mapper.apply(service.postDoctor(doctor.get())), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public  ResponseEntity<DoctorResponse> onPut(@PathVariable(value = "id") Long id, @RequestBody() Optional<Doctor> doctor) {
		if (id == null) {
			throw new CmsException("Invalid param id is null");
		}

		validate(doctor);
		return new ResponseEntity<>(mapper.apply(service.putDoctor(id,doctor.get())), HttpStatus.OK);

	}

//	@PatchMapping("/{docID}/{facID}/{depID}")
//	public void onPatch(@PathVariable("docID") Optional<Long> docID,
//	                    @PathVariable("docID") Optional<Long> facID,
//	                    @PathVariable("docID") Optional<Long> depID){
//		service.patchWorkSpace(docID.get(), facID.get(), depID.get());
//
//	}

	private void validate(Optional<Doctor> doctor) {
		if (doctor.isEmpty()) {
			throw new CmsException( "Please provide a RequestBody withe attributs firstName, lastName");
		}

		if ((doctor.get().getFirstName() == null || doctor.get().getFirstName().length() < 2) ||
				(doctor.get().getLastName() == null || doctor.get().getFirstName().length() < 2)) {
			throw new CmsException("Please provide a First and Lastname in RequestBody (min 2 chars)");
		}
	}
}
