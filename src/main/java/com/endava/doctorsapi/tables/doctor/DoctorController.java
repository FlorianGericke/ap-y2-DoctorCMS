package com.endava.doctorsapi.tables.doctor;

import com.endava.doctorsapi.dto.mappers.DtoDoctorMapper;
import com.endava.doctorsapi.dto.response.DoctorResponse;
import com.endava.doctorsapi.general.base.BaseController;
import com.endava.doctorsapi.general.exceptions.CmsException;
import org.springframework.beans.factory.annotation.Autowired;
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
	public void onPost(@RequestBody(required = false) Optional<Doctor> doctor) {
		validate(doctor);
		service.postDoctor(doctor.get());
	}

	@PutMapping("/{id}")
	public void onPut(@PathVariable(value = "id") Long id, @RequestBody() Optional<Doctor> doctor) {
		if (id == null) {
			throw new CmsException("Invalid param id is null");
		}

		validate(doctor);
		service.putDoctor(id,doctor.get());
	}

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
