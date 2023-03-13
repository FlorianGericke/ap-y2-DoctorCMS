package com.endava.doctorsapi.tables.doctor;

import com.endava.doctorsapi.general.base.ControllerBase;
import com.endava.doctorsapi.general.exceptions.ControllerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/doctor")
public class DoctorController extends ControllerBase<Doctor, DoctorRepo, DoctorService> {

	@Autowired
	public DoctorController(DoctorService doctorService) {
		super(doctorService);
	}

	@PostMapping()
	public void onPost(@RequestBody(required = false) Optional<Doctor> doctor) {
		validate(doctor);
		service.postDoctor(doctor.get());
	}

	@PutMapping("/{id}")
	public void onPut(@PathVariable(value = "id") Long id, @RequestBody() Optional<Doctor> doctor) {
		if (id == null) {
			throw new ControllerException(this, "Invalid param id is null");
		}

		validate(doctor);
		service.putDoctor(id,doctor.get());
	}

	private void validate(Optional<Doctor> doctor) {
		if (doctor.isEmpty()) {
			throw new ControllerException(this, "Please provide a RequestBody withe attributs firstName, lastName");
		}

		if ((doctor.get().getFirstName() == null || doctor.get().getFirstName().length() < 2) ||
				(doctor.get().getLastName() == null || doctor.get().getFirstName().length() < 2)) {
			throw new ControllerException(this, "Please provide a First and Lastname in RequestBody (min 2 chars)");
		}
	}
}
